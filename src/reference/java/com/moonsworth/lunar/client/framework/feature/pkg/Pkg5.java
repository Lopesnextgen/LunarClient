package com.moonsworth.lunar.client.framework.feature.pkg;

import com.moonsworth.lunar.bridge.AutoCloseableExtension;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class Pkg5 {
   static boolean method1(EntityPlayerBridge var0) {
      Bridge5Extension_5 var1 = ThreadModuleDump63.method7();
      return var1 == null ? false : var1.bridge$getLocationSkinDefault().equals(var0.bridge$getLocationSkin());
   }

   public static com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3[] method2(AutoCloseableExtension var0, boolean var1) {
      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3[] var2 = new com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3[]{
         com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg4.method1(var0, 4, 12, 4, 0, 48, true, 0.0F),
         com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg4.method1(var0, 4, 12, 4, 0, 32, true, 0.0F),
         null,
         null,
         null
      };
      if (var1) {
         var2[2] = com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg4.method1(var0, 3, 12, 4, 48, 48, true, -2.5F);
         var2[3] = com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg4.method1(var0, 3, 12, 4, 40, 32, true, -2.5F);
      } else {
         var2[2] = com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg4.method1(var0, 4, 12, 4, 48, 48, true, -2.5F);
         var2[3] = com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg4.method1(var0, 4, 12, 4, 40, 32, true, -2.5F);
      }

      var2[4] = com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg4.method1(var0, 8, 12, 4, 16, 32, true, -0.8F);
      return var2;
   }

   public static com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 method3(AutoCloseableExtension var0) {
      return com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg4.method1(var0, 8, 8, 8, 32, 0, false, 0.6F);
   }
}
