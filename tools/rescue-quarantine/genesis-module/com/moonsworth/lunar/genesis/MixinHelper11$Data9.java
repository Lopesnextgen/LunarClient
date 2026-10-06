package com.moonsworth.lunar.genesis;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.base.Preconditions;

class MixinHelper11$Data9 extends MixinHelper11$Data13 {
   @Override
   protected void method1(CacheBuilderSpec var1, long var2) {
      Preconditions.checkArgument(var1.maximumWeight == null, "maximum weight was already set to ", var1.maximumWeight);
      Preconditions.checkArgument(var1.maximumSize == null, "maximum size was already set to ", var1.maximumSize);
      var1.maximumWeight = var2;
   }
}
