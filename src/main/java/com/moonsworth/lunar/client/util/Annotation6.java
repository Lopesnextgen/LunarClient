package com.moonsworth.lunar.client.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
public @interface Annotation6 {
   Annotation6.Type method1();

   enum Type {
      X,
      Y,
      POSITION;
   }
}
