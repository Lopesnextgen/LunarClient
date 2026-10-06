package com.moonsworth.lunar.genesis;

import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public abstract class MixinHelperIterator33<T> extends MixinHelperIterator3<T> {
   private @Nullable T nextOrNull;

   protected MixinHelperIterator33(@Nullable T var1) {
      this.nextOrNull = (T)var1;
   }

   protected abstract @Nullable T computeNext(T var1);

   @Override
   public final boolean hasNext() {
      return this.nextOrNull != null;
   }

   @Override
   public final T next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      }

      try {
         return this.nextOrNull;
      } finally {
         this.nextOrNull = this.computeNext(this.nextOrNull);
      }
   }
}
