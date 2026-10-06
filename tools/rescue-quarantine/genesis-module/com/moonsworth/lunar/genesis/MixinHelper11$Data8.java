package com.moonsworth.lunar.genesis;

import java.util.concurrent.TimeUnit;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.base.Preconditions;

abstract class MixinHelper11$Data8 implements MixinHelper11$Extension {
   protected abstract void method1(CacheBuilderSpec var1, long var2, TimeUnit var4);

   @Override
   public void method1(CacheBuilderSpec var1, String var2, String var3) {
      Preconditions.checkArgument(var3 != null && !var3.isEmpty(), "value of key %s omitted", var2);

      try {
         char var4 = var3.charAt(var3.length() - 1);
         TimeUnit var5;
         switch (var4) {
            case 'd':
               var5 = TimeUnit.DAYS;
               break;
            case 'h':
               var5 = TimeUnit.HOURS;
               break;
            case 'm':
               var5 = TimeUnit.MINUTES;
               break;
            case 's':
               var5 = TimeUnit.SECONDS;
               break;
            default:
               throw new IllegalArgumentException(
                  CacheBuilderSpec.access$000("key %s invalid format.  was %s, must end with one of [dDhHmMsS]", new Object[]{var2, var3})
               );
         }

         long var6 = Long.parseLong(var3.substring(0, var3.length() - 1));
         this.method1(var1, var6, var5);
      } catch (NumberFormatException var8) {
         throw new IllegalArgumentException(CacheBuilderSpec.access$000("key %s value set to %s, must be integer", new Object[]{var2, var3}));
      }
   }
}
