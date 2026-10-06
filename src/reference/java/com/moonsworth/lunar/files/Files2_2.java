package com.moonsworth.lunar.files;

import javax.annotation.Nullable;

public class Files2_2 {
   @Nullable
   private final Files3 field1;
   private final byte[] field2;

   public Files2_2(@Nullable Files3 var1, byte[] items) {
      this.field1 = var1;
      this.field2 = items;
   }

   @Nullable
   public Files3 method1() {
      return this.field1;
   }

   public byte[] method2() {
      return this.field2;
   }
}
