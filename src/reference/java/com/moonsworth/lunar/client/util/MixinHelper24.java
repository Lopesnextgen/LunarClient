package com.moonsworth.lunar.client.util;

import lombok.Generated;

public class MixinHelper24<T> implements MixinHelper2 {
   private final String field1;
   private final T field2;

   @Override
   public String method1(int var1) {
      return Util.method7(var1) + (this.field2 instanceof MixinHelper2 var2 ? var2.method1(var1) : this.field2.toString()) + " " + this.field1;
   }

   @Generated
   public String method2() {
      return this.field1;
   }

   @Generated
   public T method3() {
      return this.field2;
   }

   @Generated
   private MixinHelper24(String var1, T var2) {
      this.field1 = var1;
      this.field2 = (T)var2;
   }

   @Generated
   public static <T> MixinHelper24<T> method4(String text, T var1) {
      return new MixinHelper24<>(text, (T)var1);
   }
}
