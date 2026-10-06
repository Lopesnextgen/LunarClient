package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.base.Preconditions;

@GwtCompatible(serializable = true, emulated = true)
final class AbstractCollectionIterator52<E> extends ImmutableSet<E> {
   final transient E field10;
   @LazyInit
   private transient int cachedHashCode;

   AbstractCollectionIterator52(E var1) {
      this.field10 = Preconditions.checkNotNull((E)var1);
   }

   AbstractCollectionIterator52(E var1, int var2) {
      this.field10 = (E)var1;
      this.cachedHashCode = var2;
   }

   @Override
   public int size() {
      return 1;
   }

   @Override
   public boolean contains(Object var1) {
      return this.field10.equals(var1);
   }

   @Override
   public MixinHelperIterator3<E> method1() {
      return Iterators.method20(this.field10);
   }

   @Override
   ImmutableList<E> method17() {
      return ImmutableList.method2(this.field10);
   }

   @Override
   boolean isPartialView() {
      return false;
   }

   @Override
   int copyIntoArray(Object[] var1, int var2) {
      var1[var2] = this.field10;
      return var2 + 1;
   }

   @Override
   public final int hashCode() {
      int var1 = this.cachedHashCode;
      if (var1 == 0) {
         this.cachedHashCode = var1 = this.field10.hashCode();
      }

      return var1;
   }

   @Override
   boolean isHashCodeFast() {
      return this.cachedHashCode != 0;
   }

   @Override
   public String toString() {
      return '[' + this.field10.toString() + ']';
   }
}
