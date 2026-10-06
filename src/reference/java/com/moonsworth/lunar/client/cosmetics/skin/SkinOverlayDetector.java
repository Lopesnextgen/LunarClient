package com.moonsworth.lunar.client.cosmetics.skin;

import com.moonsworth.lunar.bridge.AutoCloseableExtension;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;

public class SkinOverlayDetector {
   private static final SkinDataCache<Boolean> field1 = new SkinDataCache<>(64, 64);

   public SkinOverlayDetector() {
   }

   public static boolean method1(EntityPlayerBridge entity) {
      return field1.method1(entity, SkinOverlayDetector::method2).orElse(false);
   }

   private static boolean method2(AutoCloseableExtension autoCloseable) {
      if (autoCloseable.bridge$getHeight() == 64 && autoCloseable.bridge$getWidth() == 64) {
         for (int index1 = 32; index1 < 64; index1++) {
            for (int index2 = 0; index2 < 16; index2++) {
               if (autoCloseable.method1(index1, index2)) {
                  return true;
               }
            }
         }
      }

      return false;
   }
}
