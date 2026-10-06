package com.moonsworth.lunar.ichor;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@com.moonsworth.lunar.ichor.util.Annotation2
public @interface Annotation2 {
   int[] value() default {};

   int min() default -1;

   int max() default -1;

   boolean inverted() default false;

   boolean onReturn() default false;
}
