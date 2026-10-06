package com.moonsworth.lunar.genesis;

import java.util.Set;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;

class MixinHelper$Data47<K, V> extends MixinHelper$Data39<K, V> implements MixinHelper132_2<K, V> {
   transient @Nullable Set<Entry<K, V>> entrySet;
   private static final long field6 = 0L;

   MixinHelper$Data47(MixinHelper132_2<K, V> var1, @Nullable Object var2) {
      super(var1, var2);
   }

   MixinHelper132_2<K, V> method3() {
      return (MixinHelper132_2<K, V>)super.method1();
   }

   @Override
   public Set<V> get(K var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.set(this.method3().get((K)var1), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public Set<V> removeAll(Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method3().removeAll(var1);
      }
   }

   @Override
   public Set<V> replaceValues(K var1, Iterable<? extends V> var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method3().replaceValues((K)var1, var2);
      }
   }

   @Override
   public Set<Entry<K, V>> entries() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         if (this.entrySet == null) {
            this.entrySet = MixinHelper_8.set(this.method3().entries(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
         }

         return this.entrySet;
      }
   }
}
