package com.moonsworth.lunar.client.config.profile.importer;

import com.google.gson.JsonObject;
import java.util.List;

public class FeatherConvertedProfile {
   private final JsonObject mods;
   private final JsonObject general;
   private final JsonObject controls;
   private final JsonObject performance;
   private final List<String> mappedModIds;
   private final List<String> skippedModIds;

   public FeatherConvertedProfile(JsonObject json1, JsonObject json2, JsonObject json3, JsonObject json4, List<String> list, List<String> list2) {
      this.mods = json1;
      this.general = json2;
      this.controls = json3;
      this.performance = json4;
      this.mappedModIds = list;
      this.skippedModIds = list2;
   }

   public JsonObject getMods() {
      return this.mods;
   }

   public JsonObject getGeneral() {
      return this.general;
   }

   public JsonObject getControls() {
      return this.controls;
   }

   public JsonObject getPerformance() {
      return this.performance;
   }

   public List<String> getMappedModIds() {
      return this.mappedModIds;
   }

   public List<String> getSkippedModIds() {
      return this.skippedModIds;
   }
}
