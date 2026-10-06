package com.moonsworth.lunar.client.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.feature.debug.optimizationdebugmod.OptimizationdebugmodType;
import java.io.File;
import java.io.FileReader;

public class ThreadModuleDump14 {
   private static boolean initialized = false;
   private static boolean field2 = true;

   public static boolean isNoErrorContextEnabled() {
      if (!field1) {
         initialize();
      }

      return field2;
   }

   private static void initialize() {
      field1 = true;

      try {
         File var0 = new File(new File(ThreadModuleDump48.field25, getActiveProfileName()), "performance.json");
         if (!var0.exists()) {
            return;
         }

         try (FileReader var1 = new FileReader(var0)) {
            JsonElement var2 = JsonParser.parseReader(var1);
            if (var2.isJsonObject()) {
               JsonElement var3 = var2.getAsJsonObject().get("noErrorContext");
               if (var3 != null && var3.isJsonPrimitive()) {
                  field2 = var3.getAsBoolean();
               }
            }
         }
      } catch (Exception var6) {
         Slayer.error("Unable to read early Lunar options", var6);
      }
   }

   private static String getActiveProfileName() {
      File var0 = new File(ThreadModuleDump48.field25, "profile_manager.json");
      if (var0.exists()) {
         try (FileReader var1 = new FileReader(var0)) {
            JsonElement var2 = JsonParser.parseReader(var1);
            if (var2.isJsonArray()) {
               for (JsonElement var4 : var2.getAsJsonArray()) {
                  JsonObject var5 = var4.getAsJsonObject();
                  if (var5.get("active").getAsBoolean()) {
                     return var5.get("name").getAsString();
                  }
               }
            }
         } catch (Exception var9) {
            Slayer.error("Unable to read active profile name for early Lunar options", var9);
         }
      }

      return "Default";
   }

   public static boolean isFastTextEnabled() {
      return OptimizationdebugmodType.FAST_TEXT.isEnabled();
   }
}
