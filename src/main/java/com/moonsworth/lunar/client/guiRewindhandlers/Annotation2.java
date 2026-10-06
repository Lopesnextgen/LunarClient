package com.moonsworth.lunar.client.guiRewindhandlers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface Annotation2 {
   Annotation2.Type method1();

   enum Type {
      DYNAMICLISTENER_ISENABLED;
   }
}
