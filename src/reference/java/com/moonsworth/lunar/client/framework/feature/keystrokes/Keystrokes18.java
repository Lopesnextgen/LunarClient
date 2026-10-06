package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class Keystrokes18 implements Keystrokes3 {
   @Override
   public void method1(MixinCore9Extension mixinCore9, float value, int value2, MixinHelper_4 mixinHelper_4) {
      if (ThreadModuleDump63.method7() != null) {
         ResourceLocationBridge var5 = ThreadModuleDump63.method7().bridge$getLocationSkin();
         if (var5 != null) {
            float var6 = mixinCore9.getWidth() * value;
            float var7 = mixinCore9.getHeight() * value;
            float var8 = (mixinCore9.getWidth() - var6) / 2.0F;
            float var9 = (mixinCore9.getHeight() - var7) / 2.0F;
            LcuiScreen.method33(mixinHelper_4, var5, var8, var9, var6, var7, 0.125F, 0.125F, 0.25F, 0.25F);
            if (ThreadModuleDump63.method3().bridge$getPlayer().bridge$showHat()) {
               LcuiScreen.method33(mixinHelper_4, var5, var8, var9, var6, var7, 0.625F, 0.125F, 0.75F, 0.25F);
            }
         }
      }
   }
}
