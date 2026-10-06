package com.moonsworth.lunar.client.driver.bridge;

import com.lunarclient.common.v1.Color;
import com.lunarclient.websocket.cosmetic.v2.UpdateLunarPlusColorRequest;
import com.moonsworth.lunar.client.account.LunarPlusManager;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;

public class LunarPlusBridge implements DriverGuiExtension, GuiIterator.Extension {
   public LunarPlusBridge() {
   }

   @Override
   public GuiIterator getProvider() {
      return Ref.method4().method54().method8();
   }

   @CallbackJS("setColor")
   public static void method2(Integer number0) {
      LunarPlusManager holograms71 = Ref.method4().method54();
      if (holograms71.method9() && holograms71.method13().contains(number0)) {
         Ref.method4().method41().method9().method8(Ref.method3().bridge$getSession().bridge$getProfile().getId(), number0);
         holograms71.method7();
         Ref.method4()
            .method35()
            .method87()
            .updateLunarPlusColor(null, UpdateLunarPlusColorRequest.newBuilder().setPlusColor(Color.newBuilder().setColor(number0).build()).build(), arg0x -> {});
      }
   }
}
