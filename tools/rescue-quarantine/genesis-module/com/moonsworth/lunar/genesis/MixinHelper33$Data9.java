package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ForwardingMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Iterators;

class MixinHelper33$Data9<E> extends ForwardingMultiset<E> implements Serializable {
   final Multiset<? extends E> field1;
   transient @Nullable Set<E> elementSet;
   transient @Nullable Set<Multiset.Extension<E>> entrySet;
   private static final long field2 = 0L;

   MixinHelper33$Data9(Multiset<? extends E> var1) {
      this.field1 = var1;
   }

   @Override
   protected Multiset<E> method1() {
      return (Multiset<E>)this.field1;
   }

   Set<E> createElementSet() {
      return Collections.unmodifiableSet(this.field1.elementSet());
   }

   @Override
   public Set<E> elementSet() {
      Set var1 = this.elementSet;
      return var1 == null ? (this.elementSet = this.createElementSet()) : var1;
   }

   @Override
   public Set<Multiset.Extension<E>> entrySet() {
      Set var1 = this.entrySet;
      return var1 == null ? (this.entrySet = Collections.unmodifiableSet(this.field1.entrySet())) : var1;
   }

   @Override
   public Iterator<E> iterator() {
      return Iterators.method3(this.field1.iterator());
   }

   @Override
   public boolean add(E var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public int add(E var1, int var2) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean addAll(Collection<? extends E> var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean remove(Object var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public int remove(Object var1, int var2) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean removeAll(Collection<?> var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean retainAll(Collection<?> var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void clear() {
      throw new UnsupportedOperationException();
   }

   @Override
   public int setCount(E var1, int var2) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean setCount(E var1, int var2, int var3) {
      throw new UnsupportedOperationException();
   }
}
