package com.moonsworth.lunar.ichor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@com.moonsworth.lunar.ichor.util.KeepName
public @interface MixinCondition {
   String[] present() default {};

   String[] absent() default {};

   VersionGate available() default @VersionGate;
}
