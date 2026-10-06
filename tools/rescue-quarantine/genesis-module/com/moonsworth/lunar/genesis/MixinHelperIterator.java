package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
abstract class MixinHelperIterator<T> implements Iterator<T> {
   private MixinHelperIterator.Type field1 = MixinHelperIterator.Type.NOT_READY;
   private @Nullable T next;

   protected MixinHelperIterator() {
   }

   protected abstract T computeNext();

   @CanIgnoreReturnValue
   protected final @Nullable T method1() {
      this.field1 = MixinHelperIterator.Type.DONE;
      return null;
   }

   @Override
   public final boolean hasNext() {
      Preconditions.checkState(this.field1 != MixinHelperIterator.Type.FAILED);
      switch (this.field1) {
         case DONE:
            return false;
         case READY:
            return true;
         default:
            return this.tryToComputeNext();
      }
   }

   private boolean tryToComputeNext() {
      this.field1 = MixinHelperIterator.Type.FAILED;
      this.next = this.computeNext();
      if (this.field1 != MixinHelperIterator.Type.DONE) {
         this.field1 = MixinHelperIterator.Type.READY;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public final T next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      }

      this.field1 = MixinHelperIterator.Type.NOT_READY;
      Object var1 = this.next;
      this.next = null;
      return (T)var1;
   }

   @Override
   public final void remove() {
      throw new UnsupportedOperationException();
   }

   private enum Type {
      READY,
      NOT_READY,
      DONE,
      FAILED;
   }
}
