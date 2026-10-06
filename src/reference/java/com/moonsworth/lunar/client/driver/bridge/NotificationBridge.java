package com.moonsworth.lunar.client.driver.bridge;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import org.jetbrains.annotations.Nullable;

public class NotificationBridge implements DriverGuiExtension {
   public NotificationBridge() {
   }

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
