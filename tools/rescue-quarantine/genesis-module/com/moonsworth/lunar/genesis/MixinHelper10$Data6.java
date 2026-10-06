package com.moonsworth.lunar.genesis;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableList;

final class MixinHelper10$Data6<E> extends AbstractSet<E> {
   private final ImmutableMap<E, Integer> field1;
   private final int field2;

   MixinHelper10$Data6(ImmutableMap<E, Integer> var1, int var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @Override
   public Iterator<E> iterator() {
      return new MixinHelperIterator3<E>() {
         final ImmutableList<E> field1 = MixinHelper10$Data6.this.field1.method14().method2();
         int remainingSetBits = MixinHelper10$Data6.this.field2;

         @Override
         public boolean hasNext() {
            return this.remainingSetBits != 0;
         }

         @Override
         public E next() {
            int var1 = Integer.numberOfTrailingZeros(this.remainingSetBits);
            if (var1 == 32) {
               throw new NoSuchElementException();
            }

            this.remainingSetBits &= ~(1 << var1);
            return this.field1.get(var1);
         }
      };
   }

   @Override
   public int size() {
      return Integer.bitCount(this.field2);
   }

   @Override
   public boolean contains(@Nullable Object var1) {
      Integer var2 = this.field1.get(var1);
      return var2 != null && (this.field2 & 1 << var2) != 0;
   }
}
