package tools;

import anotation.Campo;
import anotation.DataType;
import anotation.LerFicheiro;
import anotation.FileReaderListener;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

//@SuppressWarnings("unchecked")
public class Config<T> {
    private final Class<T> clazz;
    private final FileReaderListener<T> listener;
    private final List<T> lista;

    public Config(Class<T> clazz, FileReaderListener<T> listener) {
        this.listener = listener;
        this.clazz = clazz;
        this.lista = new ArrayList<>();
        ler();
    }

    private void ler() {
        String filename = clazz.getAnnotation(LerFicheiro.class).filename();
        String separador = clazz.getAnnotation(LerFicheiro.class).separador();
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            var next = bufferedReader.readLine();
            StringTokenizer tok;

            while (next != null) {
                tok = new StringTokenizer(next, separador);
                T data = clazz.getConstructor().newInstance();

                for (Field field : clazz.getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(Campo.class)) {
                        var type = field.getAnnotation(Campo.class).type();
                        var token = tok.nextToken();
                        var current = DataType.convert(type, token);
                        field.set(data, current);
                    }
                }
                lista.add(data);
                next = bufferedReader.readLine();
            }
            listener.result(lista);
            fileReader.close();
            bufferedReader.close();
        } catch (IllegalAccessException | IOException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void save(T object) {
        if (validar(object)) {
            Class<?> clazz = object.getClass();
            var filename = clazz.getAnnotation(LerFicheiro.class).filename();
            var separador = clazz.getAnnotation(LerFicheiro.class).separador();
            try {
                var file = new FileWriter(filename, true);
                var buff = new BufferedWriter(file);
                StringBuilder line = new StringBuilder();
                saveData(separador, line, buff, object, clazz);
                buff.close();
                file.close();

            } catch (IOException | IllegalAccessException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Impossivel Gravar no ficheiro com dados invalidos");
        }

    }
    final public boolean validar(T object) {
        Class<?> clzx = object.getClass();
        var state = true;
        for (Field field : clzx.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Campo.class)) {
                try {
                    var type = field.getAnnotation(Campo.class).type();
                    var min = field.getAnnotation(Campo.class).min();
                    var max = field.getAnnotation(Campo.class).max();

                    var x = switch (type) {
                        case INTEGER -> (Integer) field.get(object);
                        case BYTE -> (Byte) field.get(object);
                        case DOUBLE -> (Double) field.get(object);
                        case SHORT -> (Short) field.get(object);
                        case FLOAT -> (Float) field.get(object);
                        case LONG -> (Long) field.get(object);
                        case BOOLEAN -> String.valueOf((boolean) field.get(object)).length();
                        case STRING -> ((String) field.get(object)).length();
                    };

                    if (valid(min, max, (long) x)) {
                        System.out.println("Dado invalido no campo " + field.getName() + " Min:" + min + " Max:" + max + " Input:" + ((long) x));
                        state = false;
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }

        return state;
    }

    public void save(List<T> objects){
        var filename = clazz.getAnnotation(LerFicheiro.class).filename();
        //var separador = clazz.getAnnotation(LerFicheiro.class).separador();
        //StringBuilder line = new StringBuilder();
        var state = true;
        try{
            var file = new FileWriter(filename, true);
            var buff = new BufferedWriter(file);
            for (T data : objects) {
                if (validar(data)) {
                    save(data);
                } else {
                    System.out.println("Informacao nao gravada {" + data + "}");
                    state = false;
                }
            }
            buff.close();
            file.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        if (state) System.out.println("Dados Gravados com Sucesso");
    }

    private void saveData(String separador, StringBuilder line, BufferedWriter buff, T data, Class<?> clazzy)
            throws IllegalAccessException, IOException {
        int count = 0;
        for (Field field : clazzy.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Campo.class)) {
                if (count == clazzy.getDeclaredFields().length - 1) {
                    line.append(field.get(data));
                    break;
                } else {
                    line.append(field.get(data)).append(separador);
                }
            }
            count++;
        }
        buff.write(line.toString());
        buff.newLine();
    }

    private boolean valid(long min, long max, long current) {
        return current < min || current > max;
    }

}
