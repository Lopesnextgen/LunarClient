package com.moonsworth.lunar.genesis;

import java.util.Set;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Sets;

class MixinHelper19$Data17<K, V> extends MixinHelper19$Data38<K, V> implements Set<Entry<K, V>> {
   MixinHelper19$Data17(Set<Entry<K, V>> var1) {
      super(var1);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      return Sets.equalsImpl(this, var1);
   }

   @Override
   public int hashCode() {
      return Sets.hashCodeImpl(this);
   }
}
