package com.moonsworth.lunar.genesis;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.base.Preconditions;

class MixinHelper11$Data5 extends MixinHelper11$Data15 {
   @Override
   protected void method1(CacheBuilderSpec var1, int var2) {
      Preconditions.checkArgument(var1.concurrencyLevel == null, "concurrency level was already set to ", var1.concurrencyLevel);
      var1.concurrencyLevel = var2;
   }
}
