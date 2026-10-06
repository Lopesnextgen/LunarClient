package com.moonsworth.lunar.genesis;

import com.google.j2objc.annotations.Weak;
import java.util.Map.Entry;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.UnmodifiableIterator;

class ImmutableMultimap$EntryCollection<K, V> extends ImmutableCollection<Entry<K, V>> {
   @Weak
   final MixinHelper13452<K, V> field3;
   private static final long field4 = 0L;

   ImmutableMultimap$EntryCollection(MixinHelper13452<K, V> mixinhelper134521) {
      this.field3 = mixinhelper134521;
   }

   public UnmodifiableIterator<Entry<K, V>> method1() {
      return this.field3.method19();
   }

   boolean isPartialView() {
      return this.field3.isPartialView();
   }

   public int size() {
      return this.field3.size();
   }

   public boolean contains(Object obj1) {
      if (obj1 instanceof Entry) {
         Entry entry2 = (Entry)obj1;
         return this.field3.containsEntry(entry2.getKey(), entry2.getValue());
      } else {
         return false;
      }
   }
}
