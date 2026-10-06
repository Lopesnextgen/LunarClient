package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.lunarclient.common.v1.Color;
import com.lunarclient.websocket.cosmetic.v2.UpdateLunarPlusColorRequest;
import com.moonsworth.lunar.client.account.LunarPlusManager;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;

public class LunarPlusBridgeLegacy implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   @Override
   public GuiIterator getProvider() {
      return ThreadModuleDump63.method4().method54().method8();
   }

   @CallbackJS("setColor")
   public static void method2(Integer var0) {
      LunarPlusManager var1 = ThreadModuleDump63.method4().method54();
      if (var1.method9() && var1.method3().contains(var0)) {
         ThreadModuleDump63.method4().method41().method9().method8(ThreadModuleDump63.method3().bridge$getSession().bridge$getProfile().getId(), var0);
         var1.method7();
         ThreadModuleDump63.method4()
            .method35()
            .method87()
            .updateLunarPlusColor(null, UpdateLunarPlusColorRequest.newBuilder().setPlusColor(Color.newBuilder().setColor(var0).build()).build(), var0x -> {});
      }
   }
}
