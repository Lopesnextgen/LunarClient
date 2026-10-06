package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import org.jetbrains.annotations.Nullable;

public class ProfileBridgeLegacy implements DriverGuiExtensionLegacy {
   @Nullable
   @Override
   public JsonElement method128() {
      return null;
   }

   @Override
   public JsonElement provide() {
      return Client.method109().method61().provide();
   }
}
