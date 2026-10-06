package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public class MixinHelperError extends Error {
   private static final long field1 = 0L;

   protected MixinHelperError() {
   }

   protected MixinHelperError(@Nullable String var1) {
      super(var1);
   }

   public MixinHelperError(@Nullable String var1, @Nullable Error var2) {
      super(var1, var2);
   }

   public MixinHelperError(@Nullable Error var1) {
      super(var1);
   }
}
