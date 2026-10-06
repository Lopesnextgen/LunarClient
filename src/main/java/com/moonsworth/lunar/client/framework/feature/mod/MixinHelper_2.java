package com.moonsworth.lunar.client.framework.feature.mod;

public class MixinHelper_2 {
   private final int field1;
   private final int field2;
   private final int field3;

   public MixinHelper_2(int var1, int var2, int var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
   }

   public boolean method1(int var1, int var2, int var3, int value) {
      return this.field1 >= var1 && this.field1 <= var1 + var3 && this.field2 >= var2 && this.field2 <= var2 + value;
   }

   public int x() {
      return this.field1;
   }

   public int y() {
      return this.field2;
   }

   public int method2() {
      return this.field3;
   }
}
