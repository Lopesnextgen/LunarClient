package com.moonsworth.lunar.genesis;

import java.util.Spliterator;
import java.util.Spliterators;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;

@GwtCompatible(serializable = true, emulated = true)
final class AbstractCollectionIterator55<E> extends ImmutableSet<E> {
   static final AbstractCollectionIterator55<Object> field10 = new AbstractCollectionIterator55<>(new Object[0], 0, null, 0);
   private final transient Object[] field11;
   @Annotation4
   final transient Object[] field12;
   private final transient int field13;
   private final transient int field14;

   AbstractCollectionIterator55(Object[] var1, int var2, Object[] var3, int var4) {
      this.field11 = var1;
      this.field12 = var3;
      this.field13 = var4;
      this.field14 = var2;
   }

   @Override
   public boolean contains(@Nullable Object var1) {
      Object[] var2 = this.field12;
      if (var1 != null && var2 != null) {
         int var5 = MixinHelper36_2.smearedHash(var1);

         while (true) {
            var5 &= this.field13;
            Object var4 = var2[var5];
            if (var4 == null) {
               return false;
            }

            if (var4.equals(var1)) {
               return true;
            }

            var5++;
         }
      } else {
         return false;
      }
   }

   @Override
   public int size() {
      return this.field11.length;
   }

   @Override
   public MixinHelperIterator3<E> method1() {
      return Iterators.method18((E[])this.field11);
   }

   @Override
   public Spliterator<E> spliterator() {
      return Spliterators.spliterator(this.field11, 1297);
   }

   @Override
   Object[] internalArray() {
      return this.field11;
   }

   @Override
   int internalArrayStart() {
      return 0;
   }

   @Override
   int internalArrayEnd() {
      return this.field11.length;
   }

   @Override
   int copyIntoArray(Object[] var1, int var2) {
      System.arraycopy(this.field11, 0, var1, var2, this.field11.length);
      return var2 + this.field11.length;
   }

   @Override
   ImmutableList<E> method17() {
      return this.field12 == null ? ImmutableList.method3() : new AbstractCollectionIterator332<>(this, this.field11);
   }

   @Override
   boolean isPartialView() {
      return false;
   }

   @Override
   public int hashCode() {
      return this.field14;
   }

   @Override
   boolean isHashCodeFast() {
      return true;
   }
}
