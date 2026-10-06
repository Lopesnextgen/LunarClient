package com.moonsworth.lunar.genesis;

import com.google.j2objc.annotations.RetainedWith;
import java.io.Serializable;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

@Annotation4
class MixinHelper$Data34<K, V> extends MixinHelper$Data25<K, V> implements MapExtension<K, V>, Serializable {
   private transient @Nullable Set<V> valueSet;
   @RetainedWith
   private transient @Nullable MapExtension<V, K> field5;
   private static final long field6 = 0L;

   private MixinHelper$Data34(MapExtension<K, V> var1, @Nullable Object var2, @Nullable MapExtension<V, K> var3) {
      super(var1, var2);
      this.field5 = var3;
   }

   MapExtension<K, V> method1() {
      return (MapExtension<K, V>)super.delegate();
   }

   @Override
   public Set<V> values() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         if (this.valueSet == null) {
            this.valueSet = MixinHelper_8.set(this.method1().values(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
         }

         return this.valueSet;
      }
   }

   @Override
   public V forcePut(K var1, V var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().forcePut((K)var1, (V)var2);
      }
   }

   @Override
   public MapExtension<V, K> method2() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         if (this.field5 == null) {
            this.field5 = new MixinHelper$Data34<>(this.method1().method2(), this.RRCCHORICIIHRICRICOROHRHOCHRIC, this);
         }

         return this.field5;
      }
   }
}
