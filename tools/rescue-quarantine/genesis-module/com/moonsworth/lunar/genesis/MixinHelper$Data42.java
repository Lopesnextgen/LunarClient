package com.moonsworth.lunar.genesis;

import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;

@Annotation3
class MixinHelper$Data42<K, V> extends MixinHelper$Data35 implements Entry<K, V> {
   private static final long field4 = 0L;

   MixinHelper$Data42(Entry<K, V> var1, @Nullable Object var2) {
      super(var1, var2);
   }

   Entry<K, V> delegate() {
      return (Entry<K, V>)super.delegate();
   }

   @Override
   public boolean equals(Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().equals(var1);
      }
   }

   @Override
   public int hashCode() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().hashCode();
      }
   }

   @Override
   public K getKey() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().getKey();
      }
   }

   @Override
   public V getValue() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().getValue();
      }
   }

   @Override
   public V setValue(V var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().setValue((V)var1);
      }
   }
}
