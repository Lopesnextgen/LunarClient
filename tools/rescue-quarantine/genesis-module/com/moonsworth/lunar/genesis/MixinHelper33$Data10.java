package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Multiset;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;
import com.google.common.base.Preconditions;

final class MixinHelper33$Data10<E> extends MixinHelper33$Data5<E> {
   final Multiset<E> field1;
   final PredicateExtension<? super E> field2;

   MixinHelper33$Data10(Multiset<E> var1, PredicateExtension<? super E> var2) {
      this.field1 = Preconditions.checkNotNull(var1);
      this.field2 = Preconditions.checkNotNull(var2);
   }

   public MixinHelperIterator3<E> method1() {
      return Iterators.method9(this.field1.iterator(), this.field2);
   }

   @Override
   Set<E> createElementSet() {
      return Sets.method7(this.field1.elementSet(), this.field2);
   }

   @Override
   Iterator<E> elementIterator() {
      throw new AssertionError("should never be called");
   }

   @Override
   Set<Multiset.Extension<E>> createEntrySet() {
      return Sets.method7(this.field1.entrySet(), new PredicateExtension<Multiset.Extension<E>>() {
         public boolean method1(Multiset.Extension<E> var1) {
            return MixinHelper33$Data10.this.field2.apply((E)var1.getElement());
         }
      });
   }

   @Override
   Iterator<Multiset.Extension<E>> entryIterator() {
      throw new AssertionError("should never be called");
   }

   @Override
   public int count(@Nullable Object var1) {
      int var2 = this.field1.count(var1);
      if (var2 > 0) {
         Object var3 = var1;
         return this.field2.apply((E)var3) ? var2 : 0;
      } else {
         return 0;
      }
   }

   @Override
   public int add(@Nullable E var1, int var2) {
      Preconditions.checkArgument(this.field2.apply((E)var1), "Element %s does not match predicate %s", var1, this.field2);
      return this.field1.add((E)var1, var2);
   }

   @Override
   public int remove(@Nullable Object var1, int var2) {
      MixinHelper18_3.checkNonnegative(var2, "occurrences");
      if (var2 == 0) {
         return this.count(var1);
      } else {
         return this.contains(var1) ? this.field1.remove(var1, var2) : 0;
      }
   }
}
