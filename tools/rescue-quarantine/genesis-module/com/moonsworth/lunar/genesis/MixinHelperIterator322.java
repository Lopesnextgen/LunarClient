package com.moonsworth.lunar.genesis;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
class MixinHelperIterator322<T> extends MixinHelperIterator32_2<T> {
   private final Queue<T> field2;

   MixinHelperIterator322(T... var1) {
      this.field2 = new ArrayDeque<>(var1.length);
      Collections.addAll(this.field2, (T[])var1);
   }

   MixinHelperIterator322(Queue<T> var1) {
      this.field2 = Preconditions.checkNotNull(var1);
   }

   @Override
   public T computeNext() {
      return (T)(this.field2.isEmpty() ? this.computeNext() : this.field2.remove());
   }
}
