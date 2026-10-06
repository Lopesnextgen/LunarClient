package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.Nullable;

class MixinHelper$Data31<V> extends MixinHelper$Data44<Collection<V>> {
   private static final long field5 = 0L;

   MixinHelper$Data31(Collection<Collection<V>> var1, @Nullable Object var2) {
      super(var1, var2);
   }

   @Override
   public Iterator<Collection<V>> iterator() {
      return new MixinHelperIterator2<Collection<V>, Collection<V>>(super.iterator()) {
         Collection<V> transform(Collection<V> var1) {
            return MixinHelper_8.access$400(var1, MixinHelper$Data31.this.RRCCHORICIIHRICRICOROHRHOCHRIC);
         }
      };
   }
}
