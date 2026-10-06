package com.moonsworth.lunar.genesis;

import com.google.j2objc.annotations.Weak;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;
import com.google.common.collect.Maps;
import com.google.common.base.Preconditions;

class MixinHelper19$Data25<K, V> extends MixinHelper10$Data14<K> {
   @Weak
   final Map<K, V> field1;

   MixinHelper19$Data25(Map<K, V> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   Map<K, V> map() {
      return this.field1;
   }

   @Override
   public Iterator<K> iterator() {
      return Maps.keyIterator(this.map().entrySet().iterator());
   }

   @Override
   public void forEach(Consumer<? super K> var1) {
      Preconditions.checkNotNull(var1);
      this.field1.forEach((var1x, var2) -> var1.accept(var1x));
   }

   @Override
   public int size() {
      return this.map().size();
   }

   @Override
   public boolean isEmpty() {
      return this.map().isEmpty();
   }

   @Override
   public boolean contains(Object var1) {
      return this.map().containsKey(var1);
   }

   @Override
   public boolean remove(Object var1) {
      if (this.contains(var1)) {
         this.map().remove(var1);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void clear() {
      this.map().clear();
   }
}
