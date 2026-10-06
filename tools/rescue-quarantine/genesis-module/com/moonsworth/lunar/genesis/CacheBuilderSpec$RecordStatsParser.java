package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

class CacheBuilderSpec$RecordStatsParser implements CacheBuilderSpec$ValueParser {
   CacheBuilderSpec$RecordStatsParser() {
   }

   @Override
   public void method1(MixinHelper11_8 mixinhelper11_81, String text2, @Nullable String text3) {
      Preconditions.checkArgument(text3 == null, "recordStats does not take values");
      Preconditions.checkArgument(mixinhelper11_81.recordStats == null, "recordStats already set");
      mixinhelper11_81.recordStats = true;
   }
}
