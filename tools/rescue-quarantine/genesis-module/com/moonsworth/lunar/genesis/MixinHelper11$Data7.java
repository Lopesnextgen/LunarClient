package com.moonsworth.lunar.genesis;

import java.util.concurrent.TimeUnit;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.base.Preconditions;

class MixinHelper11$Data7 extends MixinHelper11$Data8 {
   @Override
   protected void method1(CacheBuilderSpec var1, long var2, TimeUnit var4) {
      Preconditions.checkArgument(var1.accessExpirationTimeUnit == null, "expireAfterAccess already set");
      var1.accessExpirationDuration = var2;
      var1.accessExpirationTimeUnit = var4;
   }
}
