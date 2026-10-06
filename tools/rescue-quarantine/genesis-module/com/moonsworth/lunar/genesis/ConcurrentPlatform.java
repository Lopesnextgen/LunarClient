package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(emulated = true)
final class ConcurrentPlatform {
   static boolean isInstanceOfThrowableClass(@Nullable Throwable exception0, Class<? extends Throwable> clazz1) {
      return clazz1.isInstance(exception0);
   }

   private ConcurrentPlatform() {
   }
}
