package com.moonsworth.lunar.client.framework.feature.tiertagger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
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

public abstract class TierMetadataRegistry {
   private final String field1;
   private final Map<String, String> field2 = new HashMap<>();
   private final Map<String, Integer> field3 = new HashMap<>();
   private boolean loaded = false;

   protected TierMetadataRegistry(String text1) {
      this.field1 = text1;
   }

   protected abstract boolean method1(JsonObject json1);

   public void init() {
      String text1 = "tier-tagger.json";
      Path path2 = LunarConstants.field12.resolve(text1);
      if (!path2.toFile().exists()) {
         LunarLogger.method5("Unable to load %s, file doesn't exist", new Object[]{text1});
      } else {
         String text3 = Files.readString(path2);
         JsonElement element4 = JsonParser.parseString(text3);
         if (!element4.isJsonObject()) {
            LunarLogger.method5("Unable to load %s, not a json object", new Object[]{text1});
         } else {
            JsonObject json5 = element4.getAsJsonObject();
            this.loaded = this.method1(json5);
         }
      }
   }

   @Nullable
   public String method2(String text1) {
      return this.field2.get(text1.toLowerCase(Locale.ROOT));
   }

   public int method3(String text1) {
      return this.field3.getOrDefault(text1.toLowerCase(Locale.ROOT), 16777215);
   }

   protected void method4(JsonObject json1) {
      if (!json1.has("gameModeData")) {
         LunarLogger.method5("No game mode data for %s", new Object[]{this.field1});
      } else {
         json1 = json1.getAsJsonObject("gameModeData");

         for (String text3 : json1.keySet()) {
            JsonObject json4 = json1.getAsJsonObject(text3);
            String text5 = this.method6(json4);
            Integer number6 = this.method7(json4);
            if (text5 != null) {
               this.field2.put(text3.toLowerCase(Locale.ROOT), text5);
            } else {
               LunarLogger.method5("Missing icon for %s for %s", new Object[]{text3, this.field1});
            }

            if (number6 != null) {
               this.field3.put(text3.toLowerCase(Locale.ROOT), number6);
            } else {
               LunarLogger.method5("Missing color for %s for %s", new Object[]{text3, this.field1});
            }
         }

         LunarLogger.method3("[%s] Loaded %s icons, %s colors", new Object[]{this.field1, this.field2.size(), this.field3.size()});
      }
   }

   protected List<TierGameMode> method5(JsonObject json1) {
      ArrayList list2 = new ArrayList();

      for (String text4 : json1.keySet()) {
         list2.add(new TierGameMode(text4, json1.get(text4).getAsString(), Optional.ofNullable(this.method2(text4)), this.method3(text4)));
      }

      return list2;
   }

   @Nullable
   protected String method6(JsonObject json1) {
      if (!json1.has("icon")) {
         return null;
      }

      JsonElement element2 = json1.get("icon");
      String text3 = null;
      if (element2.isJsonObject()) {
         JsonObject json4 = element2.getAsJsonObject();
         if (json4.has("minVersion")) {
            String text5 = json4.get("minVersion").getAsString();
            Optional optional6 = Config.get(text5);
            if (optional6.isEmpty()) {
               LunarLogger.method5("Invalid MinecraftVersion? %s", new Object[]{text5});
               optional6 = Optional.of(Config.field41);
            }

            if (Ref.MC_VERSION >= ((Config)optional6.get()).getOrdinal()) {
               text3 = json4.get("value").getAsString();
            } else if (json4.has("orElse")) {
               text3 = json4.get("orElse").getAsString();
            } else {
               LunarLogger.method5("Missing 'orElse' in icon object", new Object[0]);
            }
         } else {
            text3 = json4.get("value").getAsString();
         }
      } else {
         text3 = element2.getAsString();
      }

      return text3;
   }

   @Nullable
   protected Integer method7(JsonObject json1) {
      if (json1.has("color")) {
         String text2 = json1.get("color").getAsString();
         return ColorUtils.method47(text2);
      } else {
         return null;
      }
   }

   @Generated
   public boolean isLoaded() {
      return this.loaded;
   }
}
