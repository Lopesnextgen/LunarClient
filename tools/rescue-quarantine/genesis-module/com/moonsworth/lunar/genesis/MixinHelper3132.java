package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ListIterator;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public abstract class MixinHelper3132<E> extends MixinHelper313_2<E> implements ListIterator<E> {
   protected MixinHelper3132() {
   }

   protected abstract ListIterator<E> delegate();

   @Override
   public void add(E var1) {
      this.delegate().add((E)var1);
   }

   @Override
   public boolean hasPrevious() {
      return this.delegate().hasPrevious();
   }

   @Override
   public int nextIndex() {
      return this.delegate().nextIndex();
   }

   @CanIgnoreReturnValue
   @Override
   public E previous() {
      return this.delegate().previous();
   }

   @Override
   public int previousIndex() {
      return this.delegate().previousIndex();
   }

   @Override
   public void set(E var1) {
      this.delegate().set((E)var1);
   }
}
