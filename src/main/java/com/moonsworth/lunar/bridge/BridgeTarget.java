package com.moonsworth.lunar.bridge;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface BridgeTarget {
   String[] value() default {""};
}
