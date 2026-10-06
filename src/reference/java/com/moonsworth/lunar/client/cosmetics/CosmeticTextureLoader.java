package com.moonsworth.lunar.client.cosmetics;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.cosmetics.AbstractCosmetic;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;
import com.moonsworth.lunar.client.framework.Ref;

public class CosmeticTextureLoader {
   public CosmeticTextureLoader() {
   }

   public static boolean method1(AbstractCosmetic module, OwnedCosmetic handler, boolean flag) {
      ResourceLocationBridge horsestats143;
      if (module.isDynamic()) {
         horsestats143 = handler.method3().bridge$getPath().lastIndexOf(46) != -1 ? handler.method3() : module.method3();
      } else {
         horsestats143 = module.method3();
      }

      return method2(horsestats143, flag);
   }

   public static boolean method2(ResourceLocationBridge horsestats140, boolean flag) {
      return Ref.method3().bridge$getTextureManager().method2(horsestats140).map(arg1x -> {
         arg1x.method3(flag);
         return arg1x.method4();
      }).orElse(true);
   }
}
