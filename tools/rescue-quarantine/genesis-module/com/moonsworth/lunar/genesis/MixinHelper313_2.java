package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public abstract class MixinHelper313_2<T> extends MixinHelper31_3 implements Iterator<T> {
   protected MixinHelper313_2() {
   }

   protected abstract Iterator<T> delegate();

   @Override
   public boolean hasNext() {
      return this.delegate().hasNext();
   }

   @CanIgnoreReturnValue
   @Override
   public T next() {
      return this.delegate().next();
   }

   @Override
   public void remove() {
      this.delegate().remove();
   }
}
