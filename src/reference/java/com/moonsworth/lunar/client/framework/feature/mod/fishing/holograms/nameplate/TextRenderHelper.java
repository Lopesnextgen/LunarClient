package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import java.util.List;
import net.kyori.adventure.text.Component;

public class TextRenderHelper {
   public TextRenderHelper() {
   }

   public static void method1(MixinHelper_4 mixinhelper_40, float value1, float value2, List<String> list3) {
      LcuiScreen.method85(mixinhelper_40, list3, (int)value1, (int)value2);
   }

   public static void method2(MixinHelper_4 mixinhelper_40, float value1, float value2, List<String> list3) {
      LcuiScreen.method82(mixinhelper_40, list3, (int)value1, (int)value2);
   }

   public static void method3(MixinHelper_4 mixinhelper_40, float value1, float value2, List<Component> list3) {
      LcuiScreen.method84(mixinhelper_40, list3, (int)value1, (int)value2);
   }

   public static void method4(MixinHelper_4 mixinhelper_40, float value1, float value2, List<Component> list3) {
      LcuiScreen.method83(mixinhelper_40, list3, (int)value1, (int)value2);
   }
}
