package com.moonsworth.lunar.ichor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@com.moonsworth.lunar.ichor.util.Annotation2
public @interface Annotation_2 {
   String[] present() default {};

   String[] absent() default {};

   Annotation2 available() default @Annotation2;
}
