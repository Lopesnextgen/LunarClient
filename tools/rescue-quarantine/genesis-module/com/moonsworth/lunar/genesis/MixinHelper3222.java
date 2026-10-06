package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;

@Annotation3
class MixinHelper3222<K, V> extends MixinHelper322<K, V> {
   static <K, V> MixinHelper3222<K, V>[] method1(int var0) {
      return new MixinHelper3222[var0];
   }

   MixinHelper3222(K var1, V var2) {
      super((K)var1, (V)var2);
      MixinHelper18_3.checkEntryNotNull(var1, var2);
   }

   MixinHelper3222(MixinHelper3222<K, V> var1) {
      super((K)var1.getKey(), (V)var1.getValue());
   }

   @Nullable MixinHelper3222<K, V> method2() {
      return null;
   }

   @Nullable MixinHelper3222<K, V> method3() {
      return null;
   }

   boolean isReusable() {
      return true;
   }

   static class Data<K, V> extends MixinHelper3222<K, V> {
      private final transient MixinHelper3222<K, V> field4;

      Data(K var1, V var2, MixinHelper3222<K, V> var3) {
         super((K)var1, (V)var2);
         this.field4 = var3;
      }

      @Override
      final @Nullable MixinHelper3222<K, V> method2() {
         return this.field4;
      }

      @Override
      final boolean isReusable() {
         return false;
      }
   }

   static final class Data2<K, V> extends MixinHelper3222.Data<K, V> {
      private final transient MixinHelper3222<K, V> field5;

      Data2(K var1, V var2, MixinHelper3222<K, V> var3, MixinHelper3222<K, V> var4) {
         super((K)var1, (V)var2, var3);
         this.field5 = var4;
      }

      @Override
      @Nullable MixinHelper3222<K, V> method3() {
         return this.field5;
      }
   }
}
