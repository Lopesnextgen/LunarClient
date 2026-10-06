package com.moonsworth.lunar.genesis;

import java.util.Spliterator;
import java.util.Spliterators;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;

@GwtCompatible(serializable = true, emulated = true)
class AbstractCollectionIterator34<E> extends ImmutableList<E> {
   static final ImmutableList<Object> field3 = new AbstractCollectionIterator34<>(new Object[0]);
   @Annotation4
   final transient Object[] field4;

   AbstractCollectionIterator34(Object[] var1) {
      this.field4 = var1;
   }

   @Override
   public int size() {
      return this.field4.length;
   }

   @Override
   boolean isPartialView() {
      return false;
   }

   @Override
   Object[] internalArray() {
      return this.field4;
   }

   @Override
   int internalArrayStart() {
      return 0;
   }

   @Override
   int internalArrayEnd() {
      return this.field4.length;
   }

   @Override
   int copyIntoArray(Object[] var1, int var2) {
      System.arraycopy(this.field4, 0, var1, var2, this.field4.length);
      return var2 + this.field4.length;
   }

   @Override
   public E get(int var1) {
      return (E)this.field4[var1];
   }

   @Override
   public MixinHelperIterator34<E> method25(int var1) {
      return Iterators.method19((E[])this.field4, 0, this.field4.length, var1);
   }

   @Override
   public Spliterator<E> spliterator() {
      return Spliterators.spliterator(this.field4, 1296);
   }
}
