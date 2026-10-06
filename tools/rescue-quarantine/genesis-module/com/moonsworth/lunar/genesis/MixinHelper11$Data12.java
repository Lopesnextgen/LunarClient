package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.base.Preconditions;

class MixinHelper11$Data12 implements MixinHelper11$Extension {
   private final AbstractMapLoader$Type4 field1;

   public MixinHelper11$Data12(AbstractMapLoader$Type4 var1) {
      this.field1 = var1;
   }

   @Override
   public void method1(CacheBuilderSpec var1, String var2, @Nullable String var3) {
      Preconditions.checkArgument(var3 == null, "key %s does not take values", var2);
      Preconditions.checkArgument(var1.field4 == null, "%s was already set to %s", var2, var1.field4);
      var1.field4 = this.field1;
   }
}
