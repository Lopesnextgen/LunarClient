package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
public abstract class MixinHelperIterator32_2<T> extends MixinHelperIterator3<T> {
   private MixinHelperIterator32$Type field1 = MixinHelperIterator32$Type.NOT_READY;
   private @Nullable T next;

   protected MixinHelperIterator32_2() {
   }

   protected abstract T computeNext();

   @CanIgnoreReturnValue
   protected final T method1() {
      this.field1 = MixinHelperIterator32$Type.DONE;
      return null;
   }

   @CanIgnoreReturnValue
   @Override
   public final boolean hasNext() {
      Preconditions.checkState(this.field1 != MixinHelperIterator32$Type.FAILED);
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
      this.field1 = MixinHelperIterator32$Type.FAILED;
      this.next = this.computeNext();
      if (this.field1 != MixinHelperIterator32$Type.DONE) {
         this.field1 = MixinHelperIterator32$Type.READY;
         return true;
      } else {
         return false;
      }
   }

   @CanIgnoreReturnValue
   @Override
   public final T next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      }

      this.field1 = MixinHelperIterator32$Type.NOT_READY;
      Object var1 = this.next;
      this.next = null;
      return (T)var1;
   }

   public final T method2() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         return this.next;
      }
   }
}
