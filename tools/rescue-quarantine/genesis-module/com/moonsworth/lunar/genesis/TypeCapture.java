package com.moonsworth.lunar.genesis;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import com.google.common.base.Preconditions;

abstract class TypeCapture<T> {
   TypeCapture() {
   }

   final Type method1() {
      Type type1 = this.getClass().getGenericSuperclass();
      Preconditions.checkArgument(type1 instanceof ParameterizedType, "%s isn't parameterized", type1);
      return ((ParameterizedType)type1).getActualTypeArguments()[0];
   }
}
