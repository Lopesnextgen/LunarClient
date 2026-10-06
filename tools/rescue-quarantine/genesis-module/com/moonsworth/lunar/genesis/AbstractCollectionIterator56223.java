package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.collect.Range;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.ImmutableSortedSet;

@GwtCompatible(emulated = true)
final class AbstractCollectionIterator56223<C extends Comparable> extends ContiguousSet<C> {
   AbstractCollectionIterator56223(MixinHelper40<C> var1) {
      super(var1);
   }

   public C first() {
      throw new NoSuchElementException();
   }

   public C last() {
      throw new NoSuchElementException();
   }

   @Override
   public int size() {
      return 0;
   }

   @Override
   public ContiguousSet<C> method15(ContiguousSet<C> var1) {
      return this;
   }

   @Override
   public Range<C> method16() {
      throw new NoSuchElementException();
   }

   @Override
   public Range<C> method17(MixinHelperType_3 var1, MixinHelperType_3 var2) {
      throw new NoSuchElementException();
   }

   @Override
   ContiguousSet<C> method12(C var1, boolean var2) {
      return this;
   }

   @Override
   ContiguousSet<C> method13(C var1, boolean var2, C var3, boolean var4) {
      return this;
   }

   @Override
   ContiguousSet<C> method14(C var1, boolean var2) {
      return this;
   }

   @Override
   public boolean contains(Object var1) {
      return false;
   }

   @Annotation3
   @Override
   int indexOf(Object var1) {
      return -1;
   }

   @Override
   public MixinHelperIterator3<C> method1() {
      return Iterators.method1();
   }

   @Annotation3
   @Override
   public MixinHelperIterator3<C> method33() {
      return Iterators.method1();
   }

   @Override
   boolean isPartialView() {
      return false;
   }

   @Override
   public boolean isEmpty() {
      return true;
   }

   @Override
   public ImmutableList<C> method2() {
      return ImmutableList.method3();
   }

   @Override
   public String toString() {
      return "[]";
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof Set) {
         Set var2 = (Set)var1;
         return var2.isEmpty();
      } else {
         return false;
      }
   }

   @Annotation3
   @Override
   boolean isHashCodeFast() {
      return true;
   }

   @Override
   public int hashCode() {
      return 0;
   }

   @Annotation3
   @Override
   Object writeReplace() {
      return new AbstractCollectionIterator56223.Data(this.field13);
   }

   @Annotation3
   @Override
   ImmutableSortedSet<C> method32() {
      return ImmutableSortedSet.method1(Ordering.method1().method9());
   }

   @Annotation3
   private static final class Data<C extends Comparable> implements Serializable {
      private final MixinHelper40<C> field1;
      private static final long field2 = 0L;

      private Data(MixinHelper40<C> var1) {
         this.field1 = var1;
      }

      private Object readResolve() {
         return new AbstractCollectionIterator56223<>(this.field1);
      }
   }
}
