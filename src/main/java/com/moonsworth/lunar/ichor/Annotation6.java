package com.moonsworth.lunar.ichor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.spongepowered.asm.mixin.Shadow;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD})
@com.moonsworth.lunar.ichor.util.Annotation2
public @interface Annotation6 {
   boolean smuggle() default true;

   Class<? extends java.lang.annotation.Annotation>[] remove() default {};

   Shadow shadow() default @Shadow;
}
