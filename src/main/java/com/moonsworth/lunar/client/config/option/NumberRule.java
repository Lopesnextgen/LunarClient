package com.moonsworth.lunar.client.config.option;

import com.google.gson.JsonObject;

public interface NumberRule<T extends Number & Comparable<T>> {
   T getMin();

   T getMax();

   boolean method1();

   boolean method2();

   int method3();

   String method4();

   T method5(double value1);

   default boolean method6() {
      return this.method3() <= 1;
   }

   default String method7(T value1) {
      return this.method6() ? Math.round(value1.doubleValue()) + "" : String.format("%.2f", value1.doubleValue());
   }

   default void method8(JsonObject json1) {
      json1.addProperty("min", this.getMin());
      json1.addProperty("max", this.getMax());
      json1.addProperty("forceMin", this.method1());
      json1.addProperty("forceMax", this.method2());
      json1.addProperty("value_type", this.method4());
      json1.addProperty("roundTo", this.method6() ? 1 : this.method3());
   }
}
