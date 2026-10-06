package com.moonsworth.lunar.client.driver.gui;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;

public class Gui implements com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy, GuiIterator.Extension {
   @Override
   public GuiIterator getProvider() {
      return Client.method109().method40().method12();
   }
}
