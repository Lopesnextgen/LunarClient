package com.moonsworth.lunar.bridge;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface BridgeVersionTarget {
   int version();

   BridgeTarget[] targets() default {};
}
