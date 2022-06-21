package di;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public final class AppInject<T> {

    public AppInject(Class<T> clazz) {
        var xs = Arrays.stream(getClass().getDeclaredMethods()).filter(p -> p.getName().equals("engine")).findFirst().get();

        if (xs.isAnnotationPresent(Provide.class)) {
            try {
                var cn = clazz.getConstructor();
                var ob = cn.newInstance();

                for (Field declaredField : clazz.getDeclaredFields()) {
                    var present = declaredField.isAnnotationPresent(Inject.class);
                    var instance = engine();
                    System.out.println("In");
                    if (present) {
                        declaredField.setAccessible(true);

                        declaredField.set(ob, instance);
                    }
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }

        }
    }


    @Provide
    private Engine engine() {
        return new Engine("V8");
    }

    public static void main(String[] args) {
        var car = new Car();
        new AppInject<Car>(Car.class);
        car.print();
    }

}
