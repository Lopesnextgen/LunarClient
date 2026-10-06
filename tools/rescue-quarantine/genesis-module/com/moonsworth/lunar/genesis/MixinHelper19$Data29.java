package com.moonsworth.lunar.genesis;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Set;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
abstract class MixinHelper19$Data29<K, V> extends AbstractMap<K, V> {
   private transient @Nullable Set<Entry<K, V>> entrySet;
   private transient @Nullable Set<K> keySet;
   private transient @Nullable Collection<V> values;

   abstract Set<Entry<K, V>> createEntrySet();

   @Override
   public Set<Entry<K, V>> entrySet() {
      Set var1 = this.entrySet;
      return var1 == null ? (this.entrySet = this.createEntrySet()) : var1;
   }

   @Override
   public Set<K> keySet() {
      Set var1 = this.keySet;
      return var1 == null ? (this.keySet = this.createKeySet()) : var1;
   }

   Set<K> createKeySet() {
      return new MixinHelper19$Data25<>(this);
   }

   @Override
   public Collection<V> values() {
      Collection var1 = this.values;
      return var1 == null ? (this.values = this.createValues()) : var1;
   }

   Collection<V> createValues() {
      return new MixinHelper19$Data35<>(this);
   }
}
