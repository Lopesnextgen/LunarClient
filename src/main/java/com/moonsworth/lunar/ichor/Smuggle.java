package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.KeepName;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.spongepowered.asm.mixin.Shadow;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD})
@KeepName
public @interface Smuggle {
   boolean smuggle() default true;

   Class<? extends Annotation>[] remove() default {};

   Shadow shadow() default @Shadow;
}
