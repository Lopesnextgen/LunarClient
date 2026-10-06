package com.moonsworth.lunar.client.config.option;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
public @interface ParameterPosition {
   ParameterPosition.Type method1();

   enum Type {
      X,
      Y,
      POSITION;

      Type() {
      }
   }
}
