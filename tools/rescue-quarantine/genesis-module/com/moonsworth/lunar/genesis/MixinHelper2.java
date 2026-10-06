package com.moonsworth.lunar.genesis;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import com.google.common.base.Preconditions;

abstract class MixinHelper2<T> {
   final Type method1() {
      Type var1 = this.getClass().getGenericSuperclass();
      Preconditions.checkArgument(var1 instanceof ParameterizedType, "%s isn't parameterized", var1);
      return ((ParameterizedType)var1).getActualTypeArguments()[0];
   }
}
