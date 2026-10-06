package com.moonsworth.lunar.ichor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({})
@com.moonsworth.lunar.ichor.util.Annotation2
public @interface Annotation12 {
   Class<?> value();

   Annotation2 available();
}
