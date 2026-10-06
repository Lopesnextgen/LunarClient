package com.moonsworth.lunar.genesis;

import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtIncompatible;

@GwtIncompatible
class Synchronized$SynchronizedEntry<K, V> extends Synchronized$SynchronizedObject implements Entry<K, V> {
   private static final long field4 = 0L;

   Synchronized$SynchronizedEntry(Entry<K, V> entry1, @Nullable Object obj2) {
      super(entry1, obj2);
   }

   Entry<K, V> delegate() {
      return (Entry<K, V>)super.delegate();
   }

   @Override
   public boolean equals(Object obj1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().equals(obj1);
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
   public V setValue(V value1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().setValue((V)value1);
      }
   }
}
