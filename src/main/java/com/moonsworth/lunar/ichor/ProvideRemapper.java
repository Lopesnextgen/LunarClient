package com.moonsworth.lunar.ichor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProvideRemapper {
   boolean method1() default false;

   boolean method2() default false;

   boolean method3() default false;

   boolean method4() default false;

   boolean method5() default false;

   boolean method6() default true;

   boolean optional() default true;
}
