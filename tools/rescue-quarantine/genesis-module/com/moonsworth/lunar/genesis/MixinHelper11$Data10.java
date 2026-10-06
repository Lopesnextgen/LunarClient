package com.moonsworth.lunar.genesis;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.base.Preconditions;

class MixinHelper11$Data10 extends MixinHelper11$Data15 {
   @Override
   protected void method1(CacheBuilderSpec var1, int var2) {
      Preconditions.checkArgument(var1.initialCapacity == null, "initial capacity was already set to ", var1.initialCapacity);
      var1.initialCapacity = var2;
   }
}
