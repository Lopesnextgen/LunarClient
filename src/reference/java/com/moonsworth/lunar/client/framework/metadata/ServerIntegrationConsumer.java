package com.moonsworth.lunar.client.framework.metadata;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.ThreadModuleDump3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;

public class ServerIntegrationConsumer extends com.moonsworth.lunar.client.framework.metadata.MetadataConsumer {
   @Override
   public void method4(JsonElement var1) {
      for (JsonElement var3 : var1.getAsJsonArray()) {
         JsonObject var4 = var3.getAsJsonObject();
         List var5 = method3(var4);
         String var6 = method2(var4);
         Predicate var7 = method4(var6, var5);
         if (var4.has("modSettings")) {
            ((ModSettingsConsumer)ThreadModuleDump63.method4().method72().method4("modSettings")).method2(var4.get("modSettings").getAsJsonObject(), var7);
         }

         if (var4.has("clientSettings")) {
            ((ClientSettingsConsumer)ThreadModuleDump63.method4().method72().method4("clientSettings")).method2(var4.get("clientSettings").getAsJsonObject(), var7);
         }
      }
   }

   @Nullable
   private static String method2(JsonObject var0) {
      return var0.has("brand") ? var0.get("brand").getAsString() : null;
   }

   @Nullable
   private static List<String> method3(JsonObject var0) {
      if (var0.has("ip")) {
         JsonArray var1 = null;
         if (var0.get("ip").isJsonPrimitive()) {
            var1 = new JsonArray();
            var1.add(var0.get("ip"));
         } else if (var0.get("ip").isJsonArray()) {
            var1 = var0.get("ip").getAsJsonArray();
         }

         if (var1 != null) {
            ArrayList var2 = new ArrayList();

            for (JsonElement var4 : var1) {
               if (var4.isJsonPrimitive() && var4.getAsJsonPrimitive().isString()) {
                  var2.add(var4.getAsJsonPrimitive().getAsString().toLowerCase());
               }
            }

            return var2;
         }
      }

      return null;
   }

   @Nullable
   private static Predicate<String> method4(@Nullable String var0, @Nullable List<String> var1) {
      Predicate var2;
      if (var0 != null) {
         if (var1 != null && !var1.isEmpty()) {
            var2 = var2x -> {
               if (ThreadModuleDump3.method5(var0)) {
                  return true;
               }

               if (var2x != null) {
                  for (String var4 : var1) {
                     if (var2x.toLowerCase().endsWith(var4)) {
                        return true;
                     }
                  }
               }

               return false;
            };
         } else {
            var2 = var1x -> ThreadModuleDump3.method5(var0);
         }
      } else if (var1 != null && !var1.isEmpty()) {
         var2 = var1x -> {
            if (var1x != null) {
               for (String var3 : var1) {
                  if (var1x.toLowerCase().endsWith(var3)) {
                     return true;
                  }
               }
            }

            return false;
         };
      } else {
         var2 = null;
      }

      return var2;
   }
}
