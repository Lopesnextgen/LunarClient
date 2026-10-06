package com.moonsworth.lunar.client.driver;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuButton;
import com.moonsworth.lunar.client.config.profile.importer.ExternalProfileLocator;
import com.moonsworth.lunar.client.config.profile.importer.BadlionProfileImporter;
import com.moonsworth.lunar.client.config.profile.importer.FeatherProfileImporter;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.io.File;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class ProfileImportProvider implements DriverGuiExtension {
   private static final int field1 = 8;

   public ProfileImportProvider() {
   }

   @Nullable
   @Override
   public JsonElement method128() {
      return null;
   }

   @Override
   public JsonElement provide() {
      JsonObject json1 = new JsonObject();
      json1.add("feather", method2(ExternalProfileLocator.findFeatherProfiles(), ".json"));
      json1.add("badlion", method2(ExternalProfileLocator.findBadlionProfiles(), ".zip"));
      json1.addProperty("maxImports", Math.max(0, 8 - Ref.method4().method61().method2().size()));
      return json1;
   }

   private static JsonArray method2(List<File> list0, String text1) {
      JsonArray array2 = new JsonArray();

      for (File file4 : list0) {
         String text5 = method3(file4, text1);
         JsonObject json6 = new JsonObject();
         json6.addProperty("name", text5);
         json6.addProperty("file", file4.getAbsolutePath());
         array2.add(json6);
      }

      return array2;
   }

   private static String method3(File file0, String text1) {
      if (text1.equals(".zip")) {
         String text2 = BadlionProfileImporter.readProfileName(file0);
         if (text2 != null && !text2.isBlank()) {
            return text2;
         }
      }

      String text3 = file0.getName();
      return text3.length() > text1.length() ? text3.substring(0, text3.length() - text1.length()) : text3;
   }

   @CallbackJS("importProfiles")
   public static String method4(String text0, String text1) {
      boolean flag2 = "badlion".equals(text0);
      int index3 = 0;
      int index4 = 0;

      for (JsonElement element6 : JsonParser.parseString(text1).getAsJsonArray()) {
         if (Ref.method4().method61().method2().size() >= 8) {
            index4++;
         } else {
            File file7 = new File(element6.getAsString());

            try {
               String text8 = method3(file7, flag2 ? ".zip" : ".json");
               if (flag2) {
                  BadlionProfileImporter.importProfile(file7, text8);
               } else {
                  FeatherProfileImporter.importProfile(file7, text8);
               }

               index3++;
            } catch (Exception exception9) {
               index4++;
               LunarLogger.method7("Failed to import profile from %s: %s", new Object[]{file7, exception9.getMessage()});
            }
         }
      }

      JsonObject json10 = new JsonObject();
      json10.addProperty("imported", index3);
      json10.addProperty("failed", index4);
      return json10.toString();
   }

   @CallbackJS("close")
   public static void close() {
      LcuiScreen.method15();
      com.moonsworth.lunar.client.driver.core.DriverViewportLegacy highlight3iterator0 = com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50();
      highlight3iterator0.method16(DriverRouteRegistry.field3);
      highlight3iterator0.method75()
         .add(
            () -> {
               com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen bridge7iterator0x = new com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen(null);
               Ref.method3()
                  .bridge$displayScreen(Bridge.method8().method18((Bridge7_8)(Ref.method8() == null ? new MainMenuButton(bridge7iterator0x) : bridge7iterator0x)));
            }
         );
   }
}
