package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import org.jetbrains.annotations.Nullable;

public class MetadataBridgeLegacy implements DriverGuiExtensionLegacy {
   @Override
   public JsonElement provide() {
      return this.method2();
   }

   @Nullable
   @Override
   public JsonElement method128() {
      return this.provide();
   }

   private JsonElement method2() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("branch", LunarBuildData.field1);
      var1.addProperty("fullGitHash", LunarBuildData.field3);
      var1.addProperty("production", LunarBuildData.field4);
      var1.addProperty("minecraftVersionStr", Bridge.getMinecraftVersion().method45());
      var1.addProperty("minecraftVersion", ThreadModuleDump63.MC_VERSION);
      var1.addProperty("version", Client.method19());
      var1.addProperty("scale", LcuiScreen.method17() / LcuiScreen.field6);
      var1.addProperty("uiBranch", LunarBuildData.field7);
      var1.addProperty("uiGitHash", LunarBuildData.field8);
      var1.addProperty("modern", Bridge.getMinecraftVersion().method19());
      var1.addProperty("legacy", Bridge.getMinecraftVersion().method21());
      JsonObject var2 = new JsonObject();
      var2.add("lunarInfo", var1);
      return var2;
   }
}
