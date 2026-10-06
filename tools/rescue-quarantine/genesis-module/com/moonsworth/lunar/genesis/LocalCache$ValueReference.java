package com.moonsworth.lunar.genesis;

import java.lang.ref.ReferenceQueue;
import org.checkerframework.checker.nullness.qual.Nullable;

interface LocalCache$ValueReference<K, V> {
   @Nullable V get();

   V waitForValue();

   int getWeight();

   @Nullable MixinHelper6_5<K, V> method1();

   LocalCache$ValueReference<K, V> method2(ReferenceQueue<V> referencequeue1, @Nullable V value2, MixinHelper6_5<K, V> mixinhelper6_53);

   void notifyNewValue(@Nullable V value1);

   boolean isLoading();

   boolean isActive();
}
