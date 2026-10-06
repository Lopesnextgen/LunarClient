package com.moonsworth.lunar.client.driver.bridge;

import com.lunarclient.websocket.badge.v1.EquipBadgeRequest;
import com.moonsworth.lunar.client.account.Badge;
import com.moonsworth.lunar.client.account.BadgeManager;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;

public class BadgeBridge implements DriverGuiExtension, GuiIterator.Extension {
   public BadgeBridge() {
   }

   @Override
   public GuiIterator getProvider() {
      return Ref.method4().method95().method15();
   }

   @CallbackJS("equip")
   public static void method2(Integer index0) {
      BadgeManager holograms131 = Ref.method4().method95();
      Badge gui2handler22 = (Badge)holograms131.method2().get(index0);
      if (gui2handler22 != null || index0 == -1) {
         holograms131.method4(index0);
         Ref.method4().method35().method92().equipBadge(null, EquipBadgeRequest.newBuilder().setBadgeId(index0).build(), arg0x -> {});
      }
   }
}
