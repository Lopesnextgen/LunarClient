package com.moonsworth.lunar.genesis;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import com.google.common.base.Function;

final class FuturesGetChecked$1 implements Function<Constructor<?>, Boolean> {
   FuturesGetChecked$1() {
   }

   public Boolean apply(Constructor<?> constructor1) {
      return Arrays.asList(constructor1.getParameterTypes()).contains(String.class);
   }
}
