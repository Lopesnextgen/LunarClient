package com.moonsworth.lunar.genesis;

import java.util.concurrent.TimeUnit;
import com.google.common.base.Preconditions;

class CacheBuilderSpec$WriteDurationParser extends MixinHelper11$Data8 {
   CacheBuilderSpec$WriteDurationParser() {
   }

   @Override
   protected void method1(MixinHelper11_8 mixinhelper11_81, long number2, TimeUnit timeunit4) {
      Preconditions.checkArgument(mixinhelper11_81.writeExpirationTimeUnit == null, "expireAfterWrite already set");
      mixinhelper11_81.writeExpirationDuration = number2;
      mixinhelper11_81.writeExpirationTimeUnit = timeunit4;
   }
}
