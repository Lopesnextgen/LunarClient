package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Iterator;
import com.google.common.collect.Multiset;

abstract class MixinHelper33$Data4<E> extends MixinHelper10$Data14<E> {
   abstract Multiset<E> method1();

   @Override
   public void clear() {
      this.method1().clear();
   }

   @Override
   public boolean contains(Object var1) {
      return this.method1().contains(var1);
   }

   @Override
   public boolean containsAll(Collection<?> var1) {
      return this.method1().containsAll(var1);
   }

   @Override
   public boolean isEmpty() {
      return this.method1().isEmpty();
   }

   @Override
   public abstract Iterator<E> iterator();

   @Override
   public boolean remove(Object var1) {
      return this.method1().remove(var1, Integer.MAX_VALUE) > 0;
   }

   @Override
   public int size() {
      return this.method1().entrySet().size();
   }
}
