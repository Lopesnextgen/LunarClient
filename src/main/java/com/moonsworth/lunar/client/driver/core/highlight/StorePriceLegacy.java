package com.moonsworth.lunar.client.driver.core.highlight;

import org.jspecify.annotations.Nullable;

public class StorePriceLegacy {
   private final @Nullable Integer field1;
   private final double field2;
   private final Double field3;

   public StorePriceLegacy(@Nullable Integer var1, double value, Double doubleValue) {
      this.field1 = var1;
      this.field2 = value;
      this.field3 = doubleValue;
   }

   public @Nullable Integer method1() {
      return this.field1;
   }

   public double value() {
      return this.field2;
   }

   public Double method2() {
      return this.field3;
   }
}
