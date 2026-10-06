package com.moonsworth.lunar.client.cosmetics.skin;

import com.moonsworth.lunar.bridge.AutoCloseableExtension;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.client.framework.Ref;

public class SkinLayerFactory {
   public SkinLayerFactory() {
   }

   static boolean method1(EntityPlayerBridge entity) {
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      return bridge5extension_51 == null ? false : bridge5extension_51.bridge$getLocationSkinDefault().equals(entity.bridge$getLocationSkin());
   }

   public static com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3[] method2(AutoCloseableExtension autocloseableextension0, boolean flag) {
      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3[] items2 = new com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3[]{
         com.moonsworth.lunar.client.cosmetics.skin.SolidPixelWrapper.method1(autocloseableextension0, 4, 12, 4, 0, 48, true, 0.0F),
         com.moonsworth.lunar.client.cosmetics.skin.SolidPixelWrapper.method1(autocloseableextension0, 4, 12, 4, 0, 32, true, 0.0F),
         null,
         null,
         null
      };
      if (flag) {
         items2[2] = com.moonsworth.lunar.client.cosmetics.skin.SolidPixelWrapper.method1(autocloseableextension0, 3, 12, 4, 48, 48, true, -2.5F);
         items2[3] = com.moonsworth.lunar.client.cosmetics.skin.SolidPixelWrapper.method1(autocloseableextension0, 3, 12, 4, 40, 32, true, -2.5F);
      } else {
         items2[2] = com.moonsworth.lunar.client.cosmetics.skin.SolidPixelWrapper.method1(autocloseableextension0, 4, 12, 4, 48, 48, true, -2.5F);
         items2[3] = com.moonsworth.lunar.client.cosmetics.skin.SolidPixelWrapper.method1(autocloseableextension0, 4, 12, 4, 40, 32, true, -2.5F);
      }

      items2[4] = com.moonsworth.lunar.client.cosmetics.skin.SolidPixelWrapper.method1(autocloseableextension0, 8, 12, 4, 16, 32, true, -0.8F);
      return items2;
   }

   public static com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 method3(AutoCloseableExtension autocloseableextension0) {
      return com.moonsworth.lunar.client.cosmetics.skin.SolidPixelWrapper.method1(autocloseableextension0, 8, 8, 8, 32, 0, false, 0.6F);
   }
}
