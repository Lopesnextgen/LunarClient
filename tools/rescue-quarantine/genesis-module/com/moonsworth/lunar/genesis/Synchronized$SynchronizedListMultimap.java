package com.moonsworth.lunar.genesis;

import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ListMultimap;

class Synchronized$SynchronizedListMultimap<K, V> extends MixinHelper$Data39<K, V> implements ListMultimap<K, V> {
   private static final long field6 = 0L;

   Synchronized$SynchronizedListMultimap(ListMultimap<K, V> mixinhelper1331, @Nullable Object obj2) {
      super(mixinhelper1331, obj2);
   }

   ListMultimap<K, V> method3() {
      return (ListMultimap<K, V>)super.method1();
   }

   @Override
   public List<V> get(K value1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.access$200(this.method3().get((K)value1), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public List<V> removeAll(Object obj1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method3().removeAll(obj1);
      }
   }

   @Override
   public List<V> replaceValues(K value1, Iterable<? extends V> list2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method3().replaceValues((K)value1, list2);
      }
   }
}
