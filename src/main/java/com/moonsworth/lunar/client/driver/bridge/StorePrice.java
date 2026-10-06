package com.moonsworth.lunar.client.driver.bridge;

import org.jspecify.annotations.Nullable;

public class StorePrice {
   private final @Nullable Integer field1;
   private final double field2;
   private final Double field3;

   public StorePrice(@Nullable Integer number1, double value, Double doubleValue) {
      this.field1 = number1;
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
