package com.moonsworth.lunar.genesis;

import java.util.NoSuchElementException;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
abstract class MixinHelperIterator342<E> extends MixinHelperIterator34<E> {
   private final int field1;
   private int position;

   protected abstract E get(int var1);

   protected MixinHelperIterator342(int var1) {
      this(var1, 0);
   }

   protected MixinHelperIterator342(int var1, int var2) {
      Preconditions.checkPositionIndex(var2, var1);
      this.field1 = var1;
      this.position = var2;
   }

   @Override
   public final boolean hasNext() {
      return this.position < this.field1;
   }

   @Override
   public final E next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         return this.get(this.position++);
      }
   }

   @Override
   public final int nextIndex() {
      return this.position;
   }

   @Override
   public final boolean hasPrevious() {
      return this.position > 0;
   }

   @Override
   public final E previous() {
      if (!this.hasPrevious()) {
         throw new NoSuchElementException();
      } else {
         return this.get(--this.position);
      }
   }

   @Override
   public final int previousIndex() {
      return this.position - 1;
   }
}
