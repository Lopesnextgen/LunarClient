package com.moonsworth.lunar.genesis;

final class MixinHelper6_11 {
   private static final SupplierExtension<MixinHelper_12> field1;

   public static MixinHelper_12 method1() {
      return field1.get();
   }

   static {
      SupplierExtension var0;
      try {
         new NumberBase3();
         var0 = new SupplierExtension<MixinHelper_12>() {
            public MixinHelper_12 method1() {
               return new NumberBase3();
            }
         };
      } catch (Throwable var2) {
         var0 = new SupplierExtension<MixinHelper_12>() {
            public MixinHelper_12 method1() {
               return new MixinHelper6$Data28();
            }
         };
      }

      field1 = var0;
   }
}
