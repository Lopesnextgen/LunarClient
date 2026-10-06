package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public abstract class MixinHelperIterator3<E> implements Iterator<E> {
   protected MixinHelperIterator3() {
   }

   @Deprecated
   @Override
   public final void remove() {
      throw new UnsupportedOperationException();
   }
}
