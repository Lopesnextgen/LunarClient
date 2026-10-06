package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.webosr.javascript.CallbackJS;

public class AlertBridgeLegacy implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   @Override
   public GuiIterator getProvider() {
      return Client.method109().method66().method15();
   }

   @CallbackJS("dismiss")
   public static void method2(Integer integer) {
      Client.method109().method66().method6(integer);
   }
}
