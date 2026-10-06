package com.moonsworth.lunar.genesis;

import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;

class MixinHelper52_2<K, V> extends MixinHelper5_6<K, V> {
   private transient volatile @Nullable MixinHelper52.MixinHelper52$Data4<K, V> field2;
   private transient volatile @Nullable MixinHelper52.MixinHelper52$Data4<K, V> field3;

   MixinHelper52_2(Map<K, V> var1) {
      super(var1);
   }

   @Override
   public V get(@Nullable Object var1) {
      Object var2 = this.getIfCached(var1);
      if (var2 != null) {
         return (V)var2;
      }

      var2 = this.IIRIOOOHIHOIIRRRHCRORHIHHIHHIO(var1);
      if (var2 != null) {
         this.addToCache((K)var1, (V)var2);
      }

      return (V)var2;
   }

   @Override
   protected V getIfCached(@Nullable Object var1) {
      Object var2 = super.getIfCached(var1);
      if (var2 != null) {
         return (V)var2;
      } else {
         MixinHelper52$Data4 var3 = this.field2;
         if (var3 != null && var3.field1 == var1) {
            return var3.field2;
         } else {
            var3 = this.field3;
            if (var3 != null && var3.field1 == var1) {
               this.method1(var3);
               return var3.field2;
            } else {
               return null;
            }
         }
      }
   }

   @Override
   protected void clearCache() {
      super.clearCache();
      this.field2 = null;
      this.field3 = null;
   }

   private void addToCache(K var1, V var2) {
      this.method1(new MixinHelper52$Data4<>((K)var1, (V)var2));
   }

   private void method1(MixinHelper52$Data4<K, V> var1) {
      this.field3 = this.field2;
      this.field2 = var1;
   }
}
