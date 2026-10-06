package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.lunarclient.websocket.badge.v1.EquipBadgeRequest;
import com.moonsworth.lunar.client.Gui2Handler2;
import com.moonsworth.lunar.client.account.BadgeManager;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;

public class BadgeBridgeLegacy implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   @Override
   public GuiIterator getProvider() {
      return ThreadModuleDump63.method4().method95().method15();
   }

   @CallbackJS("equip")
   public static void method2(Integer integer) {
      BadgeManager var1 = ThreadModuleDump63.method4().method95();
      Gui2Handler2 var2 = (Gui2Handler2)var1.method2().get(integer);
      if (var2 != null || integer == -1) {
         var1.method4(integer);
         ThreadModuleDump63.method4().method35().method92().equipBadge(null, EquipBadgeRequest.newBuilder().setBadgeId(integer).build(), var0x -> {});
      }
   }
}
