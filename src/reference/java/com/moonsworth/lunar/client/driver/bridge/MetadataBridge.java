package com.moonsworth.lunar.client.driver.bridge;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.framework.Ref;
import org.jetbrains.annotations.Nullable;

public class MetadataBridge implements DriverGuiExtension {
   public MetadataBridge() {
   }

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
      JsonObject json1 = new JsonObject();
      json1.addProperty("branch", LunarBuildData.field1);
      json1.addProperty("fullGitHash", LunarBuildData.field3);
      json1.addProperty("production", LunarBuildData.field4);
      json1.addProperty("minecraftVersionStr", Bridge.getMinecraftVersion().method45());
      json1.addProperty("minecraftVersion", Ref.MC_VERSION);
      json1.addProperty("version", Client.method19());
      json1.addProperty("scale", LcuiScreen.method17() / LcuiScreen.field6);
      json1.addProperty("uiBranch", LunarBuildData.field7);
      json1.addProperty("uiGitHash", LunarBuildData.field8);
      json1.addProperty("modern", Bridge.getMinecraftVersion().method19());
      json1.addProperty("legacy", Bridge.getMinecraftVersion().method21());
      JsonObject json2 = new JsonObject();
      json2.add("lunarInfo", json1);
      return json2;
   }
}
