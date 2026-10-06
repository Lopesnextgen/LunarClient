package com.moonsworth.lunar.client.util.concurrent;

import lombok.Generated;

public class ResourceUsageTracker {
   private static final int field1 = 60000;
   private static final int field2 = 15000;
   private static final long field3 = 60000000000L;
   private static final long field4 = 15000000000L;
   private int field5 = 0;
   private long field6;

   public ResourceUsageTracker() {
      this.method1();
   }

   public void method1() {
      this.field6 = System.nanoTime();
   }

   public boolean method2(long number1) {
      return number1 - this.field6 >= 60000000000L;
   }

   public float method3(long number1) {
      long number3 = number1 - this.field6;
      if (number3 > 60000000000L) {
         return 1.0F;
      }

      if (number3 < 15000000000L) {
         return 0.0F;
      }

      float value5 = (int)(number3 / 1000000L);
      return value5 / 45000.0F;
   }

   @Generated
   public void method4(int number1) {
      this.field5 = number1;
   }

   @Generated
   public int method5() {
      return this.field5;
   }
}
