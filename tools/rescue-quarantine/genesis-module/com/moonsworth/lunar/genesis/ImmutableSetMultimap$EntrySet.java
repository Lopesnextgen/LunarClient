package com.moonsworth.lunar.genesis;

import com.google.j2objc.annotations.Weak;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;

final class ImmutableSetMultimap$EntrySet<K, V> extends ImmutableSet<Entry<K, V>> {
   @Weak
   private final transient MixinHelper134522<K, V> field10;

   ImmutableSetMultimap$EntrySet(MixinHelper134522<K, V> mixinhelper1345221) {
      this.field10 = mixinhelper1345221;
   }

   public boolean contains(@Nullable Object obj1) {
      if (obj1 instanceof Entry) {
         Entry entry2 = (Entry)obj1;
         return this.field10.containsEntry(entry2.getKey(), entry2.getValue());
      } else {
         return false;
      }
   }

   public int size() {
      return this.field10.size();
   }

   public UnmodifiableIterator<Entry<K, V>> method1() {
      return this.field10.COCHCHCIRCCCCIHOORCRRCIOIOHHRC();
   }

   boolean isPartialView() {
      return false;
   }
}
