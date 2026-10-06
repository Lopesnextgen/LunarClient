package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.KeepName;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@KeepName
public @interface Implements {
   Interface[] value();
}
