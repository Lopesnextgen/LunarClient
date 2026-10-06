package com.moonsworth.lunar.ichor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@com.moonsworth.lunar.ichor.util.Annotation2
public @interface Annotation9 {
   Annotation12[] value();
}
