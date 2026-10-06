package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import com.google.common.collect.Lists;

final class MixinHelper19$Data34<K, V> extends MixinHelper19$Data35<K, V> {
   final Map<K, V> field2;
   final PredicateExtension<? super Entry<K, V>> field3;

   MixinHelper19$Data34(Map<K, V> var1, Map<K, V> var2, PredicateExtension<? super Entry<K, V>> var3) {
      super(var1);
      this.field2 = var2;
      this.field3 = var3;
   }

   @Override
   public boolean remove(Object var1) {
      Iterator var2 = this.field2.entrySet().iterator();

      while (var2.hasNext()) {
         Entry var3 = (Entry)var2.next();
         if (this.field3.apply(var3) && MixinHelper72.equal(var3.getValue(), var1)) {
            var2.remove();
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean removeAll(Collection<?> var1) {
      Iterator var2 = this.field2.entrySet().iterator();
      boolean var3 = false;

      while (var2.hasNext()) {
         Entry var4 = (Entry)var2.next();
         if (this.field3.apply(var4) && var1.contains(var4.getValue())) {
            var2.remove();
            var3 = true;
         }
      }

      return var3;
   }

   @Override
   public boolean retainAll(Collection<?> var1) {
      Iterator var2 = this.field2.entrySet().iterator();
      boolean var3 = false;

      while (var2.hasNext()) {
         Entry var4 = (Entry)var2.next();
         if (this.field3.apply(var4) && !var1.contains(var4.getValue())) {
            var2.remove();
            var3 = true;
         }
      }

      return var3;
   }

   @Override
   public Object[] toArray() {
      return Lists.newArrayList(this.iterator()).toArray();
   }

   @Override
   public <T> T[] toArray(T[] var1) {
      return (T[])Lists.newArrayList(this.iterator()).toArray(var1);
   }
}
