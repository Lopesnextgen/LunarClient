package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public class MixinHelperException extends RuntimeException {
   public MixinHelperException() {
   }

   public MixinHelperException(@Nullable String var1) {
      super(var1);
   }

   public MixinHelperException(@Nullable Throwable var1) {
      super(var1);
   }

   public MixinHelperException(@Nullable String var1, @Nullable Throwable var2) {
      super(var1, var2);
   }
}
