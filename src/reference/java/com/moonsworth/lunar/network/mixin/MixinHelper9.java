package com.moonsworth.lunar.network.mixin;

public class MixinHelper9 {
   public static final String field1 = "0.1.0-SNAPSHOT";
   private static MixinHelper field2 = new MixinHelper();

   public static MixinHelper method1() {
      return field2;
   }

   public static void method2(MixinHelper mixinHelper) {
      field2 = mixinHelper;
   }
}
