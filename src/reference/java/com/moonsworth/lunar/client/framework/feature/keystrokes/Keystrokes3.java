package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;

public interface Keystrokes3 {
   void method1(MixinCore9Extension var1, float var2, int var3, MixinHelper_4 var4);

   default void method2(MixinHelper_4 var1, float var2, float var3, float var4, float var5, int var6) {
      LcuiScreen.method94(var1, var2, var3, var4, var5, var6);
   }

   default void method3(MixinHelper_4 var1, float var2, float var3, float var4, float var5, float var6, float value, int value2) {
      LcuiScreen.method26(var1, var2, var3, var4, var5, var6, value, value2);
   }
}
