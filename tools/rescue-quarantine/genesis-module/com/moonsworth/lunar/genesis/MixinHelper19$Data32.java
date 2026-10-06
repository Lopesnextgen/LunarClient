package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import com.google.common.collect.Maps;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

abstract class MixinHelper19$Data32<K, V> extends MixinHelper10$Data14<Entry<K, V>> {
   abstract Map<K, V> map();

   @Override
   public int size() {
      return this.map().size();
   }

   @Override
   public void clear() {
      this.map().clear();
   }

   @Override
   public boolean contains(Object var1) {
      if (!(var1 instanceof Entry)) {
         return false;
      }

      Entry var2 = (Entry)var1;
      Object var3 = var2.getKey();
      Object var4 = Maps.safeGet(this.map(), var3);
      return MixinHelper72.equal(var4, var2.getValue()) && (var4 != null || this.map().containsKey(var3));
   }

   @Override
   public boolean isEmpty() {
      return this.map().isEmpty();
   }

   @Override
   public boolean remove(Object var1) {
      if (this.contains(var1)) {
         Entry var2 = (Entry)var1;
         return this.map().keySet().remove(var2.getKey());
      } else {
         return false;
      }
   }

   @Override
   public boolean removeAll(Collection<?> var1) {
      try {
         return super.removeAll(Preconditions.checkNotNull(var1));
      } catch (UnsupportedOperationException var3) {
         return Sets.removeAllImpl(this, var1.iterator());
      }
   }

   @Override
   public boolean retainAll(Collection<?> var1) {
      try {
         return super.retainAll(Preconditions.checkNotNull(var1));
      } catch (UnsupportedOperationException var7) {
         HashSet var3 = Sets.newHashSetWithExpectedSize(var1.size());

         for (Object var5 : var1) {
            if (this.contains(var5)) {
               Entry var6 = (Entry)var5;
               var3.add(var6.getKey());
            }
         }

         return this.map().keySet().retainAll(var3);
      }
   }
}
