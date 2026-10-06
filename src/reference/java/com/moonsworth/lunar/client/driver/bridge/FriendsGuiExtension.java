package com.moonsworth.lunar.client.driver.bridge;

import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.framework.Ref;

public class FriendsGuiExtension implements DriverGuiExtension, GuiIterator.Extension {
   public FriendsGuiExtension() {
   }

   @Override
   public GuiIterator getProvider() {
      return Ref.method4().method35() != null ? Ref.method4().method35().method111() : null;
   }
}
