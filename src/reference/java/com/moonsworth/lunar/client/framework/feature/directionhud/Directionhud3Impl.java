package com.moonsworth.lunar.client.framework.feature.directionhud;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.mod.hud.directionhud.DirectionHud;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

class Directionhud3Impl implements DirectionHud.Extension {
   private static final String[] field1 = new String[]{"S", "SW", "W", "NW", "N", "NE", "E", "SE"};

   @Override
   public void render(DirectionHud var1, MixinHelper_4 var2, float var3, float var4, double var5) {
      MixinCore9Extension var7 = (MixinCore9Extension)var1.method7(Framework.field1);
      String var8 = field1[(int)Math.floor(var5 * 4.0 / 180.0 + 0.5) & 7];
      var1.directionColor
         .HHRROIIHRRICIIHIIHICRHHRHOHHOO(
            var2,
            var8,
            var3 + var7.getWidth() / 2.0F - ThreadModuleDump63.method10().bridge$getStringWidth(var8) / 2.0F,
            var4 + var7.getHeight() / 2.0F - ThreadModuleDump63.method10().method19() / 2.0F + 1.0F,
            var1.textShadow.get()
         );
   }
}
