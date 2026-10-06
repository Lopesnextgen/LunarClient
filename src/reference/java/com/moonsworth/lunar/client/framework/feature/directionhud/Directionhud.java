package com.moonsworth.lunar.client.framework.feature.directionhud;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.MathHelperBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.mod.hud.directionhud.DirectionHud;

class Directionhud implements DirectionHud.Extension {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create("lunar", "icons/compass.png");

   @Override
   public void render(DirectionHud var1, MixinHelper_4 var2, float var3, float var4, double var5) {
      float var7 = (int)MathHelperBridge.method3((float)(var5 * 256.0 / 360.0 + 0.5)) & 0xFF;
      int var8 = var1.field20.method14(var3 + var4);
      if (var7 < 128.0F) {
         LcuiScreen.method47(var2, field1, var3, var4, (int)var7, 0, 65, 12, -1);
      } else {
         LcuiScreen.method47(var2, field1, var3, var4, (int)(var7 - 128.0F), 12, 65, 12, -1);
      }

      var8 = var8 & 16777215 | 0xFF000000;
      if (var7 < 128.0F) {
         LcuiScreen.method47(var2, field1, var3, var4, (int)var7, 24, 65, 12, var8);
      } else {
         LcuiScreen.method47(var2, field1, var3, var4, (int)(var7 - 128.0F), 36, 65, 12, var8);
      }

      if (var1.showMarker.get()) {
         var1.markerColor.ICRHORIIHOHROHOHOCOOHOOCOORRHO(var2, "|", var3 + 32.0F, var4 + 1.0F);
         var1.markerColor.ICRHORIIHOHROHOHOCOOHOOCOORRHO(var2, "|", var3 + 32.0F, var4 + 5.0F);
      }
   }
}
