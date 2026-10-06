package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.base.Preconditions;

class MixinHelper11$Data4 implements MixinHelper11$Extension {
   @Override
   public void method1(CacheBuilderSpec var1, String var2, @Nullable String var3) {
      Preconditions.checkArgument(var3 == null, "recordStats does not take values");
      Preconditions.checkArgument(var1.recordStats == null, "recordStats already set");
      var1.recordStats = true;
   }
}
