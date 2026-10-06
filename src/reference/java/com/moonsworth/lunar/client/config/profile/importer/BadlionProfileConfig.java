package com.moonsworth.lunar.client.config.profile.importer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import com.moonsworth.lunar.client.config.profile.HudPosition;
import com.moonsworth.lunar.client.config.profile.ProfileData;

public final class BadlionProfileConfig {
   private static final Set<String> NON_MOD_KEYS = Set.of(
      "recentColors",
      "recentModColors",
      "modCategories",
      "version",
      "originalVersion",
      "mcVersionCreatedOn",
      "badlionVersionCreatedOn",
      "guiScale",
      "boxes",
      "successNotification",
      "warningNotification",
      "infoNotification",
      "errorNotification",
      "lastDisplayWidth",
      "lastDisplayHeight",
      "defaultModMode",
      "textModeSettings",
      "graphicModeSettings",
      "hideModsInF3"
   );
   private final Map<String, ProfileData> modData;
   private final Map<String, JsonObject> boxesById;
   private final JsonObject graphicModeSettings;
   private final JsonObject textModeSettings;
   private final String defaultModMode;

   private BadlionProfileConfig(Map<String, ProfileData> map1, Map<String, JsonObject> map2, JsonObject json3, JsonObject json4, String text5) {
      this.modData = map1;
      this.boxesById = map2;
      this.graphicModeSettings = json3;
      this.textModeSettings = json4;
      this.defaultModMode = text5;
   }

   public static BadlionProfileConfig parse(JsonObject json0) {
      LinkedHashMap map1 = new LinkedHashMap();

      for (Entry entry3 : json0.entrySet()) {
         if (!field1.contains(entry3.getKey()) && ((JsonElement)entry3.getValue()).isJsonObject()) {
            map1.put((String)entry3.getKey(), new ProfileData(((JsonElement)entry3.getValue()).getAsJsonObject()));
         }
      }

      LinkedHashMap map7 = new LinkedHashMap();
      JsonElement element8 = json0.get("boxes");
      if (element8 != null && element8.isJsonArray()) {
         for (JsonElement element5 : element8.getAsJsonArray()) {
            if (element5.isJsonObject()) {
               JsonObject json6 = element5.getAsJsonObject();
               if (json6.has("identifier")) {
                  map7.put(json6.get("identifier").getAsString().toLowerCase(Locale.ROOT), json6);
               }
            }
         }
      }

      return new BadlionProfileConfig(
         map1,
         map7,
         asJsonObject(json0.get("graphicModeSettings")),
         asJsonObject(json0.get("textModeSettings")),
         json0.has("defaultModMode") ? json0.get("defaultModMode").getAsString() : "GRAPHICS"
      );
   }

   private static JsonObject asJsonObject(JsonElement element0) {
      return element0 != null && element0.isJsonObject() ? element0.getAsJsonObject() : new JsonObject();
   }

   public Set<String> getModIds() {
      return Collections.unmodifiableSet(this.modData.keySet());
   }

   public ProfileData getModData(String text1) {
      return this.modData.get(text1);
   }

   public HudPosition getHudPosition(String text1) {
      JsonObject json2 = this.boxesById.get(text1.toLowerCase(Locale.ROOT));
      return json2 == null ? null : HudPosition.parse(json2);
   }

   public boolean isTextMode() {
      return "TEXT".equalsIgnoreCase(this.defaultModMode);
   }

   public JsonElement getGraphicSetting(String text1, String text2) {
      return this.resolveSetting(text1, text2, "graphicModeSettings", this.graphicModeSettings);
   }

   public JsonElement getTextSetting(String text1, String text2) {
      return this.resolveSetting(text1, text2, "textModeSettings", this.textModeSettings);
   }

   private JsonElement resolveSetting(String text1, String text2, String text3, JsonObject json4) {
      ProfileData horsestats$data5 = this.modData.get(text1);
      if (horsestats$data5 != null) {
         JsonElement element6 = horsestats$data5.json.get(text3);
         if (element6 != null && element6.isJsonObject()) {
            JsonElement element7 = element6.getAsJsonObject().get(text2);
            if (element7 != null && element7.isJsonObject() && element7.getAsJsonObject().has("set") && element7.getAsJsonObject().get("set").getAsBoolean()) {
               return element7;
            }
         }
      }

      return json4.get(text2);
   }
}
