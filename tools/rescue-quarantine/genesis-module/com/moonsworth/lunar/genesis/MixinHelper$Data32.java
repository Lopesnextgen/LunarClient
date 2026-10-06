package com.moonsworth.lunar.genesis;

import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;

class MixinHelper$Data32<K, V> extends MixinHelper$Data39<K, V> implements MixinHelper133<K, V> {
   private static final long field6 = 0L;

   MixinHelper$Data32(MixinHelper133<K, V> var1, @Nullable Object var2) {
      super(var1, var2);
   }

   MixinHelper133<K, V> method3() {
      return (MixinHelper133<K, V>)super.method1();
   }

   @Override
   public List<V> get(K var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.access$200(this.method3().get((K)var1), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public List<V> removeAll(Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method3().removeAll(var1);
      }
   }

   @Override
   public List<V> replaceValues(K var1, Iterable<? extends V> var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method3().replaceValues((K)var1, var2);
      }
   }
}
