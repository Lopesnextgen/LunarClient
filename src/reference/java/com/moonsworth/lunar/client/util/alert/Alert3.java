package com.moonsworth.lunar.client.util.alert;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.cosmetics.AbstractCosmetic;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class Alert3 {
   public static boolean loadModuleTexture(AbstractCosmetic var0, OwnedCosmetic var1, boolean flag) {
      ResourceLocationBridge var3;
      if (var0.isDynamic()) {
         var3 = var1.method3().bridge$getPath().lastIndexOf(46) != -1 ? var1.method3() : var0.method3();
      } else {
         var3 = var0.method3();
      }

      return loadTexture(var3, flag);
   }

   public static boolean loadTexture(ResourceLocationBridge var0, boolean var1) {
      return ThreadModuleDump63.method3().bridge$getTextureManager().method2(var0).map(var1x -> {
         var1x.method3(var1);
         return var1x.method4();
      }).orElse(true);
   }
}
