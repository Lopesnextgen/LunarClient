package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiHandler2;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import org.jetbrains.annotations.Nullable;

public class PlayerBridgeLegacy implements DriverGuiExtensionLegacy {
   @Nullable
   @Override
   public JsonElement method128() {
      JsonObject var1 = new JsonObject();
      Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
      if (var2 != null) {
         JsonObject var3 = new JsonObject();
         var3.addProperty("x", var2.bridge$getPosX());
         var3.addProperty("y", var2.bridge$getPosY());
         var3.addProperty("z", var2.bridge$getPosZ());
         JsonObject var4 = new JsonObject();
         var4.addProperty("name", Client.method109().getWorld());
         var3.add("world", var4);
         var1.add("location", var3);
      }

      if (ThreadModuleDump63.method8() != null) {
         var1.addProperty("dimension", ThreadModuleDump63.method8().bridge$getDimensionId());
         var1.addProperty("dimensionKey", GuiHandler2.method4(ThreadModuleDump63.method8().bridge$getDimensionKey()));
      }

      return var1;
   }

   @Override
   public JsonElement provide() {
      return null;
   }
}
