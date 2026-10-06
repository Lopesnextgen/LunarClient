package com.moonsworth.lunar.genesis;

import java.util.SortedSet;
import com.google.common.collect.Iterators;

class MixinHelper10$Data13<E> extends MixinHelper10$Data16<E> implements SortedSet<E> {
   MixinHelper10$Data13(SortedSet<E> var1, PredicateExtension<? super E> var2) {
      super(var1, var2);
   }

   @Override
   public java.util.Comparator<? super E> comparator() {
      return ((SortedSet)this.field1).comparator();
   }

   @Override
   public SortedSet<E> subSet(E var1, E var2) {
      return new MixinHelper10$Data13<>((SortedSet<E>)((SortedSet)this.field1).subSet(var1, var2), this.field2);
   }

   @Override
   public SortedSet<E> headSet(E var1) {
      return new MixinHelper10$Data13<>((SortedSet<E>)((SortedSet)this.field1).headSet(var1), this.field2);
   }

   @Override
   public SortedSet<E> tailSet(E var1) {
      return new MixinHelper10$Data13<>((SortedSet<E>)((SortedSet)this.field1).tailSet(var1), this.field2);
   }

   @Override
   public E first() {
      return Iterators.method13(this.field1.iterator(), this.field2);
   }

   @Override
   public E last() {
      SortedSet var1 = (SortedSet)this.field1;

      while (true) {
         Object var2 = var1.last();
         if (this.field2.apply(var2)) {
            return (E)var2;
         }

         var1 = var1.headSet(var2);
      }
   }
}
