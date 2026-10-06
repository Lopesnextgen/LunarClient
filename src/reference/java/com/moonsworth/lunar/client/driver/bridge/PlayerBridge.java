package com.moonsworth.lunar.client.driver.bridge;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.feature.waypoints.Waypoint;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.framework.Ref;
import org.jetbrains.annotations.Nullable;

public class PlayerBridge implements DriverGuiExtension {
   public PlayerBridge() {
   }

   @Nullable
   @Override
   public JsonElement method128() {
      JsonObject json1 = new JsonObject();
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         JsonObject json3 = new JsonObject();
         json3.addProperty("x", bridge5extension_52.bridge$getPosX());
         json3.addProperty("y", bridge5extension_52.bridge$getPosY());
         json3.addProperty("z", bridge5extension_52.bridge$getPosZ());
         JsonObject json4 = new JsonObject();
         json4.addProperty("name", Client.method109().getWorld());
         json3.add("world", json4);
         json1.add("location", json3);
      }

      if (Ref.method8() != null) {
         json1.addProperty("dimension", Ref.method8().bridge$getDimensionId());
         json1.addProperty("dimensionKey", Waypoint.method4(Ref.method8().bridge$getDimensionKey()));
      }

      return json1;
   }

   @Override
   public JsonElement provide() {
      return null;
   }
}
