package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(emulated = true)
final class MixinHelper25_3 {
   static boolean isInstanceOfThrowableClass(@Nullable Throwable var0, Class<? extends Throwable> var1) {
      return var1.isInstance(var0);
   }

   private MixinHelper25_3() {
   }
}
