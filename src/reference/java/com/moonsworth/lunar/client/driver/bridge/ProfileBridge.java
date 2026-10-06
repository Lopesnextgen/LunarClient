package com.moonsworth.lunar.client.driver.bridge;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import org.jetbrains.annotations.Nullable;

public class ProfileBridge implements DriverGuiExtension {
   public ProfileBridge() {
   }

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
