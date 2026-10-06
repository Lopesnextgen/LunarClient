package com.moonsworth.lunar.client.framework;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.feature.debug.optimizationdebugmod.OptimizationDebugOption;
import java.io.File;
import java.io.FileReader;

public class EarlyOptions {
   private static boolean field1 = false;
   private static boolean field2 = true;

   public EarlyOptions() {
   }

   public static boolean method1() {
      if (!field1) {
         initialize();
      }

      return field2;
   }

   private static void initialize() {
      field1 = true;

      try {
         File file0 = new File(new File(LunarConstants.field25, method2()), "performance.json");
         if (!file0.exists()) {
            return;
         }

         try (FileReader filereader1 = new FileReader(file0)) {
            JsonElement element2 = JsonParser.parseReader(filereader1);
            if (element2.isJsonObject()) {
               JsonElement element3 = element2.getAsJsonObject().get("noErrorContext");
               if (element3 != null && element3.isJsonPrimitive()) {
                  field2 = element3.getAsBoolean();
               }
            }
         }
      } catch (Exception exception6) {
         LunarLogger.error("Unable to read early Lunar options", exception6);
      }
   }

   private static String method2() {
      File file0 = new File(LunarConstants.field25, "profile_manager.json");
      if (file0.exists()) {
         try (FileReader filereader1 = new FileReader(file0)) {
            JsonElement element2 = JsonParser.parseReader(filereader1);
            if (element2.isJsonArray()) {
               for (JsonElement element4 : element2.getAsJsonArray()) {
                  JsonObject json5 = element4.getAsJsonObject();
                  if (json5.get("active").getAsBoolean()) {
                     return json5.get("name").getAsString();
                  }
               }
            }
         } catch (Exception exception9) {
            LunarLogger.error("Unable to read active profile name for early Lunar options", exception9);
         }
      }

      return "Default";
   }

   public static boolean method3() {
      return OptimizationDebugOption.FASTER_PACK_LOADING.isEnabled();
   }
}
