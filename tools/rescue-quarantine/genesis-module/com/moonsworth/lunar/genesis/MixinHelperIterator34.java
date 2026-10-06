package com.moonsworth.lunar.genesis;

import java.util.ListIterator;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public abstract class MixinHelperIterator34<E> extends MixinHelperIterator3<E> implements ListIterator<E> {
   protected MixinHelperIterator34() {
   }

   @Deprecated
   @Override
   public final void add(E var1) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   @Override
   public final void set(E var1) {
      throw new UnsupportedOperationException();
   }
}
