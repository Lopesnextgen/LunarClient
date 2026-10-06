package com.moonsworth.lunar.genesis;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.base.Preconditions;

abstract class MixinHelper11$Data15 implements MixinHelper11$Extension {
   protected abstract void method1(CacheBuilderSpec var1, int var2);

   @Override
   public void method1(CacheBuilderSpec var1, String var2, String var3) {
      Preconditions.checkArgument(var3 != null && !var3.isEmpty(), "value of key %s omitted", var2);

      try {
         this.method1(var1, Integer.parseInt(var3));
      } catch (NumberFormatException var5) {
         throw new IllegalArgumentException(CacheBuilderSpec.access$000("key %s value set to %s, must be integer", new Object[]{var2, var3}), var5);
      }
   }
}
