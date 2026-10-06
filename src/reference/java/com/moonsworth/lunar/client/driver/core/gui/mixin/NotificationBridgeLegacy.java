package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import org.jetbrains.annotations.Nullable;

public class NotificationBridgeLegacy implements DriverGuiExtensionLegacy {
   @Nullable
   @Override
   public JsonElement method128() {
      return Client.method109().method69().method128();
   }

   @Override
   public boolean method6() {
      return true;
   }

   @Override
   public JsonElement provide() {
      return null;
   }
}
