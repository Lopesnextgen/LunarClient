package com.moonsworth.lunar.client.framework.feature.f3display;

import com.moonsworth.lunar.bridge.ProfilerResultBridge;

public class F3PieSlice implements ProfilerResultBridge {
   private final double field1;
   private final double field2;
   private final String field3;

   public F3PieSlice(double value, double value2, String text) {
      this.field1 = value;
      this.field2 = value2;
      this.field3 = text;
   }

   public double method1() {
      return this.field1;
   }

   public double method2() {
      return this.field2;
   }

   public String bridge$getName() {
      return this.field3;
   }
}
