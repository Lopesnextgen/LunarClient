package com.moonsworth.lunar.client.util;

final class Util$Data {
   private int field1 = 0;
   private long field2 = 0L;

   private Util$Data() {
   }

   private void method1(int var1) {
      this.field1++;
      this.field2 += var1;
   }

   private void method2(int var1) {
      this.field1--;
      this.field2 -= var1;
   }

   public String method3() {
      return this.field1 + " resources, " + Util_2.method8(this.field2);
   }
}
