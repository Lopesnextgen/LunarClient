package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multisets;
import com.google.common.collect.Multiset;

@GwtCompatible
abstract class AbstractCollectionBase<E> extends AbstractCollection<E> implements Multiset<E> {
   @LazyInit
   private transient @Nullable Set<E> elementSet;
   @LazyInit
   private transient @Nullable Set<Multiset.Extension<E>> entrySet;

   @Override
   public boolean isEmpty() {
      return this.entrySet().isEmpty();
   }

   @Override
   public boolean contains(@Nullable Object var1) {
      return this.count(var1) > 0;
   }

   @CanIgnoreReturnValue
   @Override
   public final boolean add(@Nullable E var1) {
      this.add((E)var1, 1);
      return true;
   }

   @CanIgnoreReturnValue
   @Override
   public int add(@Nullable E var1, int var2) {
      throw new UnsupportedOperationException();
   }

   @CanIgnoreReturnValue
   @Override
   public final boolean remove(@Nullable Object var1) {
      return this.remove(var1, 1) > 0;
   }

   @CanIgnoreReturnValue
   @Override
   public int remove(@Nullable Object var1, int var2) {
      throw new UnsupportedOperationException();
   }

   @CanIgnoreReturnValue
   @Override
   public int setCount(@Nullable E var1, int var2) {
      return Multisets.method20(this, (E)var1, var2);
   }

   @CanIgnoreReturnValue
   @Override
   public boolean setCount(@Nullable E var1, int var2, int var3) {
      return Multisets.method21(this, (E)var1, var2, var3);
   }

   @CanIgnoreReturnValue
   @Override
   public final boolean addAll(Collection<? extends E> var1) {
      return Multisets.method16(this, var1);
   }

   @CanIgnoreReturnValue
   @Override
   public final boolean removeAll(Collection<?> var1) {
      return Multisets.method18(this, var1);
   }

   @CanIgnoreReturnValue
   @Override
   public final boolean retainAll(Collection<?> var1) {
      return Multisets.method19(this, var1);
   }

   @Override
   public abstract void clear();

   @Override
   public Set<E> elementSet() {
      Set var1 = this.elementSet;
      if (var1 == null) {
         this.elementSet = var1 = this.createElementSet();
      }

      return var1;
   }

   Set<E> createElementSet() {
      return new AbstractCollectionBase.Data2();
   }

   abstract Iterator<E> elementIterator();

   @Override
   public Set<Multiset.Extension<E>> entrySet() {
      Set var1 = this.entrySet;
      if (var1 == null) {
         this.entrySet = var1 = this.createEntrySet();
      }

      return var1;
   }

   Set<Multiset.Extension<E>> createEntrySet() {
      return new AbstractCollectionBase.Data();
   }

   abstract Iterator<Multiset.Extension<E>> entryIterator();

   abstract int distinctElements();

   @Override
   public final boolean equals(@Nullable Object var1) {
      return Multisets.method15(this, var1);
   }

   @Override
   public final int hashCode() {
      return this.entrySet().hashCode();
   }

   @Override
   public final String toString() {
      return this.entrySet().toString();
   }

   class Data extends MixinHelper33$Data6<E> {
      @Override
      Multiset<E> method1() {
         return AbstractCollectionBase.this;
      }

      @Override
      public Iterator<Multiset.Extension<E>> iterator() {
         return AbstractCollectionBase.this.entryIterator();
      }

      @Override
      public int size() {
         return AbstractCollectionBase.this.distinctElements();
      }
   }

   class Data2 extends MixinHelper33$Data4<E> {
      @Override
      Multiset<E> method1() {
         return AbstractCollectionBase.this;
      }

      @Override
      public Iterator<E> iterator() {
         return AbstractCollectionBase.this.elementIterator();
      }
   }
}
