package com.moonsworth.lunar.client.util.concurrent;

final class ResourceUsageCounter {
   private int field1 = 0;
   private long field2 = 0L;

   private ResourceUsageCounter() {
   }

   private void method1(int number1) {
      this.field1++;
      this.field2 += number1;
   }

   private void method2(int number1) {
      this.field1--;
      this.field2 -= number1;
   }

   public String method3() {
      return this.field1 + " resources, " + AsyncResourceManager.method8(this.field2);
   }
}
