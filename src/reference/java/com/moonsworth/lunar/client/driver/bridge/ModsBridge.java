package com.moonsworth.lunar.client.driver.bridge;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;

public class ModsBridge implements com.moonsworth.lunar.client.driver.DriverGuiExtension, GuiIterator.Extension {
   public ModsBridge() {
   }

   @Override
   public GuiIterator getProvider() {
      return Client.method109().method40().method12();
   }
}
