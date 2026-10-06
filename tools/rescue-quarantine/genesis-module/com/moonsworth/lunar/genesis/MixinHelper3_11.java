package com.moonsworth.lunar.genesis;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(emulated = true)
final class MixinHelper3_11 {
   private static final SupplierExtension<MixinHelper9_4> field1;

   public static MixinHelper9_4 method1() {
      return field1.get();
   }

   static {
      SupplierExtension var0;
      try {
         new NumberBase2();
         var0 = new SupplierExtension<MixinHelper9_4>() {
            public MixinHelper9_4 method1() {
               return new NumberBase2();
            }
         };
      } catch (Throwable var2) {
         var0 = new SupplierExtension<MixinHelper9_4>() {
            public MixinHelper9_4 method1() {
               return new MixinHelper3$Data28();
            }
         };
      }

      field1 = var0;
   }
}
