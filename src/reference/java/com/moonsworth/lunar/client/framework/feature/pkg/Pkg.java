package com.moonsworth.lunar.client.framework.feature.pkg;

import com.moonsworth.lunar.bridge.AutoCloseableExtension;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;

public class Pkg {
   private static final Pkg2<Boolean> field1 = new Pkg2<>(64, 64);

   public static boolean method1(EntityPlayerBridge var0) {
      return field1.method1(var0, Pkg::method2).orElse(false);
   }

   private static boolean method2(AutoCloseableExtension var0) {
      if (var0.bridge$getHeight() == 64 && var0.bridge$getWidth() == 64) {
         for (int var1 = 32; var1 < 64; var1++) {
            for (int var2 = 0; var2 < 16; var2++) {
               if (var0.method1(var1, var2)) {
                  return true;
               }
            }
         }
      }

      return false;
   }
}
