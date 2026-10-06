package com.moonsworth.lunar.genesis;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.base.Preconditions;

final class Sets$PowerSet<E> extends AbstractSet<Set<E>> {
   final ImmutableMap<E, Integer> field1;

   Sets$PowerSet(Set<E> set1) {
      Preconditions.checkArgument(set1.size() <= 30, "Too many elements to create power set: %s > 30", set1.size());
      this.field1 = Maps.method50(set1);
   }

   @Override
   public int size() {
      return 1 << this.field1.size();
   }

   @Override
   public boolean isEmpty() {
      return false;
   }

   @Override
   public Iterator<Set<E>> iterator() {
      return new Data15$1(this, this.size());
   }

   @Override
   public boolean contains(@Nullable Object obj1) {
      if (obj1 instanceof Set) {
         Set set2 = (Set)obj1;
         return this.field1.method14().containsAll(set2);
      } else {
         return false;
      }
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof Sets$PowerSet) {
         Sets$PowerSet mixinhelper10$data152 = (Sets$PowerSet)obj1;
         return this.field1.equals(mixinhelper10$data152.field1);
      } else {
         return super.equals(obj1);
      }
   }

   @Override
   public int hashCode() {
      return this.field1.method14().hashCode() << this.field1.size() - 1;
   }

   @Override
   public String toString() {
      return "powerSet(" + this.field1 + ")";
   }
}
