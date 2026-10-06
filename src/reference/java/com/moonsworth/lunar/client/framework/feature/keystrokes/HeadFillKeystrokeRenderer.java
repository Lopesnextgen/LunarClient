package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.Ref;

public class HeadFillKeystrokeRenderer implements KeystrokeRenderer {
   public HeadFillKeystrokeRenderer() {
   }

   @Override
   public void method1(MixinCore9Extension mixinCore9, float value, int value2, MixinHelper_4 mixinhelper_44) {
      if (Ref.method7() != null) {
         ResourceLocationBridge horsestats145 = Ref.method7().bridge$getLocationSkin();
         if (horsestats145 != null) {
            float value6 = mixinCore9.getWidth() * value;
            float value7 = mixinCore9.getHeight() * value;
            float value8 = (mixinCore9.getWidth() - value6) / 2.0F;
            float value9 = (mixinCore9.getHeight() - value7) / 2.0F;
            LcuiScreen.method33(mixinhelper_44, horsestats145, value8, value9, value6, value7, 0.125F, 0.125F, 0.25F, 0.25F);
            if (Ref.method3().bridge$getPlayer().bridge$showHat()) {
               LcuiScreen.method33(mixinhelper_44, horsestats145, value8, value9, value6, value7, 0.625F, 0.125F, 0.75F, 0.25F);
            }
         }
      }
   }
}
