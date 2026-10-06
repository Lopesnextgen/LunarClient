package com.moonsworth.lunar.genesis;

import java.util.AbstractCollection;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Multimap;

abstract class Multimaps$Entries<K, V> extends AbstractCollection<Entry<K, V>> {
   Multimaps$Entries() {
   }

   abstract Multimap<K, V> method1();

   @Override
   public int size() {
      return this.method1().size();
   }

   @Override
   public boolean contains(@Nullable Object obj1) {
      if (obj1 instanceof Entry) {
         Entry entry2 = (Entry)obj1;
         return this.method1().containsEntry(entry2.getKey(), entry2.getValue());
      } else {
         return false;
      }
   }

   @Override
   public boolean remove(@Nullable Object obj1) {
      if (obj1 instanceof Entry) {
         Entry entry2 = (Entry)obj1;
         return this.method1().remove(entry2.getKey(), entry2.getValue());
      } else {
         return false;
      }
   }

   @Override
   public void clear() {
      this.method1().clear();
   }
}
