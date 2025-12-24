package org.escamilla.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Example of annotation
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface ConvertsTo {
    Class<?> targetClass();
    String setterPrefix() default "set";
}

/**
 * If we want to use the annotations...
    @ConvertsTo(targetClass = NewDataContainer.class)
    public class LegacyDataContainer {...}
 */