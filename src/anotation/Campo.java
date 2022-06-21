package anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Campo {
    DataType type() default DataType.STRING;

    long min() default 1;

    long max() default Long.MAX_VALUE;

    String[] valid() default {};

}
