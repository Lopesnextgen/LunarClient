package com.moonsworth.lunar.client.framework.feature.tiertagger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.config.Config;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nullable;
import lombok.Generated;

public abstract class Tiertagger6 {
   private final String field1;
   private final Map<String, String> field2 = new HashMap<>();
   private final Map<String, Integer> field3 = new HashMap<>();
   private boolean loaded = false;

   protected Tiertagger6(String var1) {
      this.field1 = var1;
   }

   protected abstract boolean method1(JsonObject var1);

   public void init() {
      String var1 = "tier-tagger.json";
      Path var2 = ThreadModuleDump48.field12.resolve(var1);
      if (!var2.toFile().exists()) {
         Slayer.method5("Unable to load %s, file doesn't exist", new Object[]{var1});
      } else {
         String var3 = Files.readString(var2);
         JsonElement var4 = JsonParser.parseString(var3);
         if (!var4.isJsonObject()) {
            Slayer.method5("Unable to load %s, not a json object", new Object[]{var1});
         } else {
            JsonObject var5 = var4.getAsJsonObject();
            this.loaded = this.method1(var5);
         }
      }
   }

   @Nullable
   public String method2(String var1) {
      return this.field2.get(var1.toLowerCase(Locale.ROOT));
   }

   public int method3(String var1) {
      return this.field3.getOrDefault(var1.toLowerCase(Locale.ROOT), 16777215);
   }

   protected void method4(JsonObject var1) {
      if (!var1.has("gameModeData")) {
         Slayer.method5("No game mode data for %s", new Object[]{this.field1});
      } else {
         var1 = var1.getAsJsonObject("gameModeData");

         for (String var3 : var1.keySet()) {
            JsonObject var4 = var1.getAsJsonObject(var3);
            String var5 = this.method6(var4);
            Integer var6 = this.method7(var4);
            if (var5 != null) {
               this.field2.put(var3.toLowerCase(Locale.ROOT), var5);
            } else {
               Slayer.method5("Missing icon for %s for %s", new Object[]{var3, this.field1});
            }

            if (var6 != null) {
               this.field3.put(var3.toLowerCase(Locale.ROOT), var6);
            } else {
               Slayer.method5("Missing color for %s for %s", new Object[]{var3, this.field1});
            }
         }

         Slayer.method3("[%s] Loaded %s icons, %s colors", new Object[]{this.field1, this.field2.size(), this.field3.size()});
      }
   }

   protected List<Tiertagger_2> method5(JsonObject var1) {
      ArrayList var2 = new ArrayList();

      for (String var4 : var1.keySet()) {
         var2.add(new Tiertagger_2(var4, var1.get(var4).getAsString(), Optional.ofNullable(this.method2(var4)), this.method3(var4)));
      }

      return var2;
   }

   @Nullable
   protected String method6(JsonObject var1) {
      if (!var1.has("icon")) {
         return null;
      }

      JsonElement var2 = var1.get("icon");
      String var3 = null;
      if (var2.isJsonObject()) {
         JsonObject var4 = var2.getAsJsonObject();
         if (var4.has("minVersion")) {
            String var5 = var4.get("minVersion").getAsString();
            Optional var6 = Config.get(var5);
            if (var6.isEmpty()) {
               Slayer.method5("Invalid MinecraftVersion? %s", new Object[]{var5});
               var6 = Optional.of(Config.field41);
            }

            if (ThreadModuleDump63.MC_VERSION >= ((Config)var6.get()).getOrdinal()) {
               var3 = var4.get("value").getAsString();
            } else if (var4.has("orElse")) {
               var3 = var4.get("orElse").getAsString();
            } else {
               Slayer.method5("Missing 'orElse' in icon object", new Object[0]);
            }
         } else {
            var3 = var4.get("value").getAsString();
         }
      } else {
         var3 = var2.getAsString();
      }

      return var3;
   }

   @Nullable
   protected Integer method7(JsonObject var1) {
      if (var1.has("color")) {
         String var2 = var1.get("color").getAsString();
         return ThreadModuleDump23.method47(var2);
      } else {
         return null;
      }
   }

   @Generated
   public boolean isLoaded() {
      return this.loaded;
   }
}
