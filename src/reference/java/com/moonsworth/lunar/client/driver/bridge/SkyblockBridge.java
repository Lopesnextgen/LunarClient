package com.moonsworth.lunar.client.driver.bridge;

import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.framework.Ref;

public class SkyblockBridge implements DriverGuiExtension, GuiIterator.Extension {
   public SkyblockBridge() {
   }

   @Override
   public GuiIterator getProvider() {
      return Ref.method4().method40().method82().method14();
   }
}
