package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;

public class Keystrokes16 implements Keystrokes3 {
   @Override
   public void method1(MixinCore9Extension mixinCore9, float value, int value2, MixinHelper_4 mixinHelper_4) {
      float var5 = mixinCore9.getWidth() * value;
      float var6 = mixinCore9.getHeight() * value;
      float var7 = Math.min(var5, var6) / 2.0F;
      float var8 = (mixinCore9.getWidth() - var5) / 2.0F;
      float var9 = (mixinCore9.getHeight() - var6) / 2.0F;
      LcuiScreen.method117(mixinHelper_4, var8, var9, var5, var6, var7, value2);
   }
}
