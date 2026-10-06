package com.moonsworth.lunar.genesis;

import com.google.j2objc.annotations.RetainedWith;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.BiMap;
import com.google.common.collect.ForwardingMap;

class Maps$UnmodifiableBiMap<K, V> extends ForwardingMap<K, V> implements BiMap<K, V>, Serializable {
   final Map<K, V> field1;
   final BiMap<? extends K, ? extends V> field2;
   @RetainedWith
   @Nullable BiMap<V, K> field3;
   transient @Nullable Set<V> values;
   private static final long field4 = 0L;

   Maps$UnmodifiableBiMap(BiMap<? extends K, ? extends V> mapextension1, @Nullable BiMap<V, K> mapextension2) {
      this.field1 = Collections.unmodifiableMap(mapextension1);
      this.field2 = mapextension1;
      this.field3 = mapextension2;
   }

   protected Map<K, V> delegate() {
      return this.field1;
   }

   public V forcePut(K value1, V value2) {
      throw new UnsupportedOperationException();
   }

   public BiMap<V, K> method2() {
      BiMap mapextension1 = this.field3;
      return mapextension1 == null ? (this.field3 = new Maps$UnmodifiableBiMap<>(this.field2.method2(), this)) : mapextension1;
   }

   public Set<V> values() {
      Set set1 = this.values;
      return set1 == null ? (this.values = Collections.unmodifiableSet(this.field2.values())) : set1;
   }
}
