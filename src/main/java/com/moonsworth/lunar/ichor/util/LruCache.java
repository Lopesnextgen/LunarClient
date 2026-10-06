package com.moonsworth.lunar.ichor.util;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class LruCache<K, V> extends LinkedHashMap<K, V> {
   private final int field1;

   public LruCache(int value) {
      super(value);
      this.field1 = value;
   }

   @Override
   protected boolean removeEldestEntry(Entry<K, V> entry) {
      return this.size() > this.field1;
   }
}
