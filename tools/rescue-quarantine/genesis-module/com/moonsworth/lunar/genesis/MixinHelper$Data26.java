package com.moonsworth.lunar.genesis;

import java.util.SortedSet;
import org.checkerframework.checker.nullness.qual.Nullable;

class MixinHelper$Data26<K, V> extends MixinHelper$Data47<K, V> implements MixinHelper1322<K, V> {
   private static final long field7 = 0L;

   MixinHelper$Data26(MixinHelper1322<K, V> var1, @Nullable Object var2) {
      super(var1, var2);
   }

   MixinHelper1322<K, V> method4() {
      return (MixinHelper1322<K, V>)super.method3();
   }

   @Override
   public SortedSet<V> get(K var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.access$100(this.method4().get((K)var1), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public SortedSet<V> removeAll(Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method4().removeAll(var1);
      }
   }

   @Override
   public SortedSet<V> replaceValues(K var1, Iterable<? extends V> var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method4().replaceValues((K)var1, var2);
      }
   }

   @Override
   public java.util.Comparator<? super V> valueComparator() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method4().valueComparator();
      }
   }
}
