package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import java.util.List;
import net.kyori.adventure.text.Component;

public class Nameplate3 {
   public static void method1(MixinHelper_4 var0, float var1, float var2, List<String> var3) {
      LcuiScreen.method85(var0, var3, (int)var1, (int)var2);
   }

   public static void method2(MixinHelper_4 var0, float var1, float var2, List<String> var3) {
      LcuiScreen.method82(var0, var3, (int)var1, (int)var2);
   }

   public static void method3(MixinHelper_4 var0, float var1, float var2, List<Component> var3) {
      LcuiScreen.method84(var0, var3, (int)var1, (int)var2);
   }

   public static void method4(MixinHelper_4 var0, float var1, float var2, List<Component> var3) {
      LcuiScreen.method83(var0, var3, (int)var1, (int)var2);
   }
}
