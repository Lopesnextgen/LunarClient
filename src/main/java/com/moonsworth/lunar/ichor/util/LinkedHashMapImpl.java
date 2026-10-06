package com.moonsworth.lunar.ichor.util;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class LinkedHashMapImpl<K, V> extends LinkedHashMap<K, V> {
   private final int field1;

   public LinkedHashMapImpl(int var1) {
      super(var1);
      this.field1 = var1;
   }

   @Override
   protected boolean removeEldestEntry(Entry<K, V> var1) {
      return this.size() > this.field1;
   }
}
