package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Spliterator;
import java.util.function.Consumer;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Iterables;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;

@GwtCompatible(serializable = true, emulated = true)
final class AbstractCollectionIterator54<E extends Enum<E>> extends ImmutableSet<E> {
   private final transient EnumSet<E> field10;
   @LazyInit
   private transient int hashCode;

   static ImmutableSet method1(EnumSet var0) {
      switch (var0.size()) {
         case 0:
            return ImmutableSet.method3();
         case 1:
            return ImmutableSet.method2(Iterables.getOnlyElement(var0));
         default:
            return new AbstractCollectionIterator54(var0);
      }
   }

   private AbstractCollectionIterator54(EnumSet<E> var1) {
      this.field10 = var1;
   }

   @Override
   boolean isPartialView() {
      return false;
   }

   @Override
   public MixinHelperIterator3<E> method1() {
      return Iterators.method3(this.field10.iterator());
   }

   @Override
   public Spliterator<E> spliterator() {
      return this.field10.spliterator();
   }

   @Override
   public void forEach(Consumer<? super E> var1) {
      this.field10.forEach(var1);
   }

   @Override
   public int size() {
      return this.field10.size();
   }

   @Override
   public boolean contains(Object var1) {
      return this.field10.contains(var1);
   }

   @Override
   public boolean containsAll(Collection<?> var1) {
      if (var1 instanceof AbstractCollectionIterator54) {
         var1 = ((AbstractCollectionIterator54)var1).field10;
      }

      return this.field10.containsAll(var1);
   }

   @Override
   public boolean isEmpty() {
      return this.field10.isEmpty();
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      }

      if (var1 instanceof AbstractCollectionIterator54) {
         var1 = ((AbstractCollectionIterator54)var1).field10;
      }

      return this.field10.equals(var1);
   }

   @Override
   boolean isHashCodeFast() {
      return true;
   }

   @Override
   public int hashCode() {
      int var1 = this.hashCode;
      return var1 == 0 ? (this.hashCode = this.field10.hashCode()) : var1;
   }

   @Override
   public String toString() {
      return this.field10.toString();
   }

   @Override
   Object writeReplace() {
      return new AbstractCollectionIterator54.Data<>(this.field10);
   }

   private static class Data<E extends Enum<E>> implements Serializable {
      final EnumSet<E> field1;
      private static final long field2 = 0L;

      Data(EnumSet<E> var1) {
         this.field1 = var1;
      }

      Object readResolve() {
         return new AbstractCollectionIterator54(this.field1.clone());
      }
   }
}
