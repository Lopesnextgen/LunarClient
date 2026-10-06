package com.moonsworth.lunar.genesis;

import com.google.j2objc.annotations.RetainedWith;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ForwardingMap;

class MixinHelper19$Data31<K, V> extends ForwardingMap<K, V> implements MapExtension<K, V>, Serializable {
   final Map<K, V> field1;
   final MapExtension<? extends K, ? extends V> field2;
   @RetainedWith
   @Nullable MapExtension<V, K> field3;
   transient @Nullable Set<V> values;
   private static final long field4 = 0L;

   MixinHelper19$Data31(MapExtension<? extends K, ? extends V> var1, @Nullable MapExtension<V, K> var2) {
      this.field1 = Collections.unmodifiableMap(var1);
      this.field2 = var1;
      this.field3 = var2;
   }

   @Override
   protected Map<K, V> delegate() {
      return this.field1;
   }

   @Override
   public V forcePut(K var1, V var2) {
      throw new UnsupportedOperationException();
   }

   @Override
   public MapExtension<V, K> method2() {
      MapExtension var1 = this.field3;
      return var1 == null ? (this.field3 = new MixinHelper19$Data31<>(this.field2.method2(), this)) : var1;
   }

   @Override
   public Set<V> values() {
      Set var1 = this.values;
      return var1 == null ? (this.values = Collections.unmodifiableSet(this.field2.values())) : var1;
   }
}
