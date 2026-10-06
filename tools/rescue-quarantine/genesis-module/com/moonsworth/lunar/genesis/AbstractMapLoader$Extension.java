package com.moonsworth.lunar.genesis;

import java.lang.ref.ReferenceQueue;
import org.checkerframework.checker.nullness.qual.Nullable;

interface AbstractMapLoader$Extension<K, V> {
   @Nullable V get();

   V waitForValue();

   int getWeight();

   @Nullable MixinHelper6_5<K, V> method1();

   AbstractMapLoader$Extension<K, V> method2(ReferenceQueue<V> var1, @Nullable V var2, MixinHelper6_5<K, V> var3);

   void notifyNewValue(@Nullable V var1);

   boolean isLoading();

   boolean isActive();
}
