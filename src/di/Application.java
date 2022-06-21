package di;

import java.lang.instrument.ClassDefinition;
import java.lang.reflect.Field;
import java.lang.reflect.ReflectPermission;
import java.util.HashMap;
import java.util.Map;

public class Application {
    private final Map<Class<Object>, Object> instances = new HashMap<>();

    public Application() {

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public <T> T getInstance(Class<T> clazz) {
        try {
            var constructor = clazz.getConstructor();
            if (instances.get(clazz) == null) {

                System.out.println("First");
                var object = (T) constructor.newInstance();
                instances.put((Class<Object>) clazz, object);
                var fields = clazz.getDeclaredFields();
                inject(object, fields);
                return object;
            }
            System.out.println("Singleton");
            return (T) instances.get(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private <T> void inject(T object, Field... fields) {
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                var type = field.getType();
                System.out.println(type.getName());
                try {
                    //if (instances.get(type)==null){
                        var inject = type.getDeclaredConstructor().newInstance();
                        field.set(object, inject);
                        inject(inject,inject.getClass().getDeclaredFields());
                    //}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
