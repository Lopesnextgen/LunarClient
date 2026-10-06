package com.moonsworth.lunar.genesis;

import java.util.concurrent.TimeUnit;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.base.Preconditions;

class MixinHelper11$Data11 extends MixinHelper11$Data8 {
   @Override
   protected void method1(CacheBuilderSpec var1, long var2, TimeUnit var4) {
      Preconditions.checkArgument(var1.refreshTimeUnit == null, "refreshAfterWrite already set");
      var1.refreshDuration = var2;
      var1.refreshTimeUnit = var4;
   }
}
