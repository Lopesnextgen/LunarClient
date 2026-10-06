package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Multiset;

abstract class MixinHelper33$Data6<E> extends MixinHelper10$Data14<Multiset.Extension<E>> {
   abstract Multiset<E> method1();

   @Override
   public boolean contains(@Nullable Object var1) {
      if (var1 instanceof Multiset.Extension) {
         Multiset.Extension var2 = (Multiset.Extension)var1;
         if (var2.getCount() <= 0) {
            return false;
         }

         int var3 = this.method1().count(var2.getElement());
         return var3 == var2.getCount();
      } else {
         return false;
      }
   }

   @Override
   public boolean remove(Object var1) {
      if (var1 instanceof Multiset.Extension) {
         Multiset.Extension var2 = (Multiset.Extension)var1;
         Object var3 = var2.getElement();
         int var4 = var2.getCount();
         if (var4 != 0) {
            Multiset var5 = this.method1();
            return var5.setCount(var3, var4, 0);
         }
      }

      return false;
   }

   @Override
   public void clear() {
      this.method1().clear();
   }
}
