package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;

public interface KeystrokeRenderer {
   void method1(MixinCore9Extension mixincore9extension1, float value2, int number3, MixinHelper_4 mixinhelper_44);

   default void method2(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, float value5, int value) {
      LcuiScreen.method94(mixinhelper_41, value2, value3, value4, value5, value);
   }

   default void method3(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, float value5, float value, float value6, int value7) {
      LcuiScreen.method26(mixinhelper_41, value2, value3, value4, value5, value, value6, value7);
   }
}
