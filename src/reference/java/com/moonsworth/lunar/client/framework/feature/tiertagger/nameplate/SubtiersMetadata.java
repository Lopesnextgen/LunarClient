package com.moonsworth.lunar.client.framework.feature.tiertagger.nameplate;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.feature.tiertagger.TierMetadataRegistry;
import com.moonsworth.lunar.client.framework.feature.tiertagger.TierGameMode;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class SubtiersMetadata extends TierMetadataRegistry {
   private static SubtiersMetadata field4;
   private final List<TierGameMode> field5 = new ArrayList<>();

   private SubtiersMetadata() {
      super("SubTiers");
   }

   @Override
   protected boolean method1(JsonObject json1) {
      if (!json1.has("subtiers")) {
         LunarLogger.method5("No SubTiers entry in tier tagger data!", new Object[0]);
         return false;
      } else {
         json1 = json1.getAsJsonObject("subtiers");
         this.method4(json1);
         this.method2(json1);
         LunarLogger.method3("[SubTiers] Loaded %s fallback modes", new Object[]{this.field5.size()});
         return true;
      }
   }

   private void method2(JsonObject json1) {
      if (json1.has("fallbackGameModes")) {
         JsonObject json2 = json1.getAsJsonObject("fallbackGameModes");
         this.field5.addAll(this.method5(json2));
      } else {
         LunarLogger.method5("No fallback game modes for subtiers.net", new Object[0]);
      }
   }

   public static SubtiersMetadata method3() {
      if (field4 == null) {
         field4 = new SubtiersMetadata();
      }

      return field4;
   }

   @Generated
   public List<TierGameMode> method4() {
      return this.field5;
   }
}
