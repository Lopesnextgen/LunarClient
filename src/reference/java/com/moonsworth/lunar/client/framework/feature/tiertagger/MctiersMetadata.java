package com.moonsworth.lunar.client.framework.feature.tiertagger;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.LunarLogger;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class MctiersMetadata extends TierMetadataRegistry {
   private static MctiersMetadata field4;
   private final List<TierGameMode> field5 = new ArrayList<>();
   private final List<TierGameMode> field6 = new ArrayList<>();

   private MctiersMetadata() {
      super("MCTiers");
   }

   @Override
   protected boolean method1(JsonObject json1) {
      if (!json1.has("mctiers")) {
         LunarLogger.method5("No MCTiers entry in tier tagger data!", new Object[0]);
         return false;
      } else {
         json1 = json1.getAsJsonObject("mctiers");
         this.method4(json1);
         this.method2(json1);
         LunarLogger.method3("[MCTiers] Loaded %s (.com) and %s (.io) fallback modes", new Object[]{this.field5.size(), this.field6.size()});
         return true;
      }
   }

   private void method2(JsonObject json1) {
      if (json1.has("fallbackGameModes_com")) {
         JsonObject json2 = json1.getAsJsonObject("fallbackGameModes_com");
         this.field5.addAll(this.method5(json2));
      } else {
         LunarLogger.method5("No fallback game modes for mctiers.com", new Object[0]);
      }

      if (json1.has("fallbackGameModes_io")) {
         JsonObject json3 = json1.getAsJsonObject("fallbackGameModes_io");
         this.field6.addAll(this.method5(json3));
      } else {
         LunarLogger.method5("No fallback game modes for mctiers.io", new Object[0]);
      }
   }

   public static MctiersMetadata method3() {
      if (field4 == null) {
         field4 = new MctiersMetadata();
      }

      return field4;
   }

   @Generated
   public List<TierGameMode> method4() {
      return this.field5;
   }

   @Generated
   public List<TierGameMode> method5() {
      return this.field6;
   }
}
