package tools;

import anotation.Init;
import anotation.Json;
import anotation.JsonSerializable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ToJson {

    public String convert(Object object) throws JsonException {
        try {
            checkObject(object);
            initializeObject(object);
            return getJsonString(object);
        } catch (Exception e) {
            throw new JsonException(e.getMessage());
        }
    }

    private String getJsonString(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        Map<String, String> jsonMap = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Json.class)) {
                jsonMap.put(getKey(field), (String) field.get(object));
            }
        }

        String jsonString = jsonMap.entrySet().stream().
                map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
                .collect(Collectors.joining(","));
        return "{" + jsonString + "}";
    }

    private String getKey(Field field) {
        String value = field.getAnnotation(Json.class).key();
        return value.isEmpty() ? field.getName() : value;
    }

    private void initializeObject(Object object) throws JsonException {
        if (Objects.isNull(object)) {
            throw new JsonException("O objecto nao pode ser null");
        }
        Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(JsonSerializable.class)) {
            throw new JsonException("A class " + clazz.getName() + " Nao contem a anotacao");
        }
    }

    private void checkObject(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Init.class)) {
                method.setAccessible(true);
                method.invoke(object);
            }
        }
    }

}
