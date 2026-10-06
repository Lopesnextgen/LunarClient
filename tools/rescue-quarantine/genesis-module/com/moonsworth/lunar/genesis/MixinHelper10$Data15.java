package com.moonsworth.lunar.genesis;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.base.Preconditions;

final class MixinHelper10$Data15<E> extends AbstractSet<Set<E>> {
   final ImmutableMap<E, Integer> field1;

   MixinHelper10$Data15(Set<E> var1) {
      Preconditions.checkArgument(var1.size() <= 30, "Too many elements to create power set: %s > 30", var1.size());
      this.field1 = Maps.method50(var1);
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
      return new MixinHelperIterator342<Set<E>>(this.size()) {
         protected Set<E> get(int var1) {
            return new MixinHelper10$Data6<>(MixinHelper10$Data15.this.field1, var1);
         }
      };
   }

   @Override
   public boolean contains(@Nullable Object var1) {
      if (var1 instanceof Set) {
         Set var2 = (Set)var1;
         return this.field1.method14().containsAll(var2);
      } else {
         return false;
      }
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof MixinHelper10$Data15) {
         MixinHelper10$Data15 var2 = (MixinHelper10$Data15)var1;
         return this.field1.equals(var2.field1);
      } else {
         return super.equals(var1);
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
