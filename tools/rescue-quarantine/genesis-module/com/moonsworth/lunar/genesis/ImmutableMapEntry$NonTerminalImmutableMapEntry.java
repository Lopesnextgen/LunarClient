package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;

class ImmutableMapEntry$NonTerminalImmutableMapEntry<K, V> extends ImmutableMapEntry<K, V> {
   private final transient ImmutableMapEntry<K, V> field4;

   ImmutableMapEntry$NonTerminalImmutableMapEntry(K value1, V value2, ImmutableMapEntry<K, V> mixinhelper32223) {
      super(value1, value2);
      this.field4 = mixinhelper32223;
   }

   final @Nullable ImmutableMapEntry<K, V> method2() {
      return this.field4;
   }

   final boolean isReusable() {
      return false;
   }
}
