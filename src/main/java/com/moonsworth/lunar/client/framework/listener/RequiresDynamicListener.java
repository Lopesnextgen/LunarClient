package com.moonsworth.lunar.client.framework.listener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface RequiresDynamicListener {
   DynamicListenerIsEnabled.Type method1();
}
