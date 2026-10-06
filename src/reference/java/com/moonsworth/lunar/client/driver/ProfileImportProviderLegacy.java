package com.moonsworth.lunar.client.driver;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuButton;
import com.moonsworth.lunar.client.profile.importer.ExternalProfileLocator;
import com.moonsworth.lunar.client.profile.importer.BadlionProfileImporter;
import com.moonsworth.lunar.client.profile.importer.FeatherProfileImporter;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.io.File;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class ProfileImportProviderLegacy implements DriverGuiExtensionLegacy {
   private static final int field1 = 8;

   @Nullable
   @Override
   public JsonElement method128() {
      return null;
   }

   @Override
   public JsonElement provide() {
      JsonObject var1 = new JsonObject();
      var1.add("feather", method2(ExternalProfileLocator.method6(), ".json"));
      var1.add("badlion", method2(ExternalProfileLocator.method5(), ".zip"));
      var1.addProperty("maxImports", Math.max(0, 8 - ThreadModuleDump63.method4().method61().method2().size()));
      return var1;
   }

   private static JsonArray method2(List<File> var0, String var1) {
      JsonArray var2 = new JsonArray();

      for (File var4 : var0) {
         String var5 = method3(var4, var1);
         JsonObject var6 = new JsonObject();
         var6.addProperty("name", var5);
         var6.addProperty("file", var4.getAbsolutePath());
         var2.add(var6);
      }

      return var2;
   }

   private static String method3(File var0, String var1) {
      if (var1.equals(".zip")) {
         String var2 = BadlionProfileImporter.method2(var0);
         if (var2 != null && !var2.isBlank()) {
            return var2;
         }
      }

      String var3 = var0.getName();
      return var3.length() > var1.length() ? var3.substring(0, var3.length() - var1.length()) : var3;
   }

   @CallbackJS("importProfiles")
   public static String method4(String var0, String var1) {
      boolean var2 = "badlion".equals(var0);
      int var3 = 0;
      int var4 = 0;

      for (JsonElement var6 : JsonParser.parseString(var1).getAsJsonArray()) {
         if (ThreadModuleDump63.method4().method61().method2().size() >= 8) {
            var4++;
         } else {
            File var7 = new File(var6.getAsString());

            try {
               String var8 = method3(var7, var2 ? ".zip" : ".json");
               if (var2) {
                  BadlionProfileImporter.method1(var7, var8);
               } else {
                  FeatherProfileImporter.method1(var7, var8);
               }

               var3++;
            } catch (Exception var9) {
               var4++;
               Slayer.method7("Failed to import profile from %s: %s", new Object[]{var7, var9.getMessage()});
            }
         }
      }

      JsonObject var10 = new JsonObject();
      var10.addProperty("imported", var3);
      var10.addProperty("failed", var4);
      return var10.toString();
   }

   @CallbackJS("close")
   public static void close() {
      LcuiScreen.method15();
      com.moonsworth.lunar.client.driver.core.DriverViewportLegacy var0 = com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50();
      var0.method16(DriverRouteRegistryLegacy.field3);
      var0.method75()
         .add(
            () -> {
               com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen var0x = new com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen(null);
               ThreadModuleDump63.method3()
                  .bridge$displayScreen(Bridge.method8().method18((Bridge7_8)(ThreadModuleDump63.method8() == null ? new MainMenuButton(var0x) : var0x)));
            }
         );
   }
}
