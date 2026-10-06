package com.moonsworth.lunar.genesis;

import com.google.j2objc.annotations.Weak;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.UnmodifiableIterator;

final class ImmutableMultimap$Values<K, V> extends ImmutableCollection<V> {
   @Weak
   private final transient MixinHelper13452<K, V> field3;
   private static final long field4 = 0L;

   ImmutableMultimap$Values(MixinHelper13452<K, V> mixinhelper134521) {
      this.field3 = mixinhelper134521;
   }

   public boolean contains(@Nullable Object obj1) {
      return this.field3.containsValue(obj1);
   }

   public UnmodifiableIterator<V> method1() {
      return this.field3.method24();
   }

   @GwtIncompatible
   int copyIntoArray(Object[] items1, int number2) {
      UnmodifiableIterator mixinhelperiterator33 = this.field3.field2.method17().method1();

      while (mixinhelperiterator33.hasNext()) {
         ImmutableCollection abstractcollectioniterator4 = (ImmutableCollection)mixinhelperiterator33.next();
         number2 = abstractcollectioniterator4.copyIntoArray(items1, number2);
      }

      return number2;
   }

   public int size() {
      return this.field3.size();
   }

   boolean isPartialView() {
      return true;
   }
}
