package com.moonsworth.lunar.client.driver.core.gui.mixin.nameplate;

import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class FriendsGuiExtensionLegacy implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   @Override
   public GuiIterator getProvider() {
      return ThreadModuleDump63.method4().method35() != null ? ThreadModuleDump63.method4().method35().method111() : null;
   }
}
