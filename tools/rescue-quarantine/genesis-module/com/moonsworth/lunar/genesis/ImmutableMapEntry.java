package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtIncompatible;

@GwtIncompatible
class ImmutableMapEntry<K, V> extends ImmutableEntry<K, V> {
   static <K, V> ImmutableMapEntry<K, V>[] method1(int index0) {
      return new ImmutableMapEntry[index0];
   }

   ImmutableMapEntry(K value1, V value2) {
      super(value1, value2);
      CollectPreconditions.checkEntryNotNull(value1, value2);
   }

   ImmutableMapEntry(ImmutableMapEntry<K, V> mixinhelper32221) {
      super(mixinhelper32221.getKey(), mixinhelper32221.getValue());
   }

   @Nullable ImmutableMapEntry<K, V> method2() {
      return null;
   }

   @Nullable ImmutableMapEntry<K, V> method3() {
      return null;
   }

   boolean isReusable() {
      return true;
   }
}
