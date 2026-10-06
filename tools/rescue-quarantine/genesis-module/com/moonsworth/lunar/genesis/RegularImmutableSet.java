package com.moonsworth.lunar.genesis;

import java.util.Spliterator;
import java.util.Spliterators;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.eventbus.Subscribe;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;

@GwtCompatible(serializable = true, emulated = true)
final class RegularImmutableSet<E> extends ImmutableSet<E> {
   static final RegularImmutableSet<Object> field10 = new RegularImmutableSet<>(new Object[0], 0, null, 0);
   private final transient Object[] field11;
   @Subscribe
   final transient Object[] field12;
   private final transient int field13;
   private final transient int field14;

   RegularImmutableSet(Object[] items1, int number2, Object[] items3, int number4) {
      this.field11 = items1;
      this.field12 = items3;
      this.field13 = number4;
      this.field14 = number2;
   }

   public boolean contains(@Nullable Object obj1) {
      Object[] items2 = this.field12;
      if (obj1 != null && items2 != null) {
         int index5 = Hashing.smearedHash(obj1);

         while (true) {
            index5 &= this.field13;
            Object obj4 = items2[index5];
            if (obj4 == null) {
               return false;
            }

            if (obj4.equals(obj1)) {
               return true;
            }

            index5++;
         }
      } else {
         return false;
      }
   }

   public int size() {
      return this.field11.length;
   }

   public UnmodifiableIterator<E> method1() {
      return Iterators.method18(this.field11);
   }

   public Spliterator<E> spliterator() {
      return Spliterators.spliterator(this.field11, 1297);
   }

   Object[] internalArray() {
      return this.field11;
   }

   int internalArrayStart() {
      return 0;
   }

   int internalArrayEnd() {
      return this.field11.length;
   }

   int copyIntoArray(Object[] items1, int number2) {
      System.arraycopy(this.field11, 0, items1, number2, this.field11.length);
      return number2 + this.field11.length;
   }

   ImmutableList<E> method17() {
      return (ImmutableList<E>)(this.field12 == null
         ? ImmutableList.method3()
         : new RegularImmutableAsList(this, this.field11));
   }

   boolean isPartialView() {
      return false;
   }

   public int hashCode() {
      return this.field14;
   }

   boolean isHashCodeFast() {
      return true;
   }
}
