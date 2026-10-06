package com.moonsworth.lunar.client.driver.bridge;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.webosr.javascript.CallbackJS;

public class AlertBridge implements DriverGuiExtension, GuiIterator.Extension {
   public AlertBridge() {
   }

   @Override
   public GuiIterator getProvider() {
      return Client.method109().method66().method15();
   }

   @CallbackJS("dismiss")
   public static void method2(Integer number0) {
      Client.method109().method66().method6(number0);
   }
}
