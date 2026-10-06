package com.moonsworth.lunar.client.framework.listener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface DynamicListenerIsEnabled {
   DynamicListenerIsEnabled.Type method1();

   enum Type {
      DYNAMICLISTENER_ISENABLED;

      Type() {
      }
   }
}
