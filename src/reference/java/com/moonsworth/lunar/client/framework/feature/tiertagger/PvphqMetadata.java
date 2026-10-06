package com.moonsworth.lunar.client.framework.feature.tiertagger;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.LunarLogger;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class PvphqMetadata extends TierMetadataRegistry {
   private static PvphqMetadata field4;
   private final List<TierGameMode> field5 = new ArrayList<>();

   private PvphqMetadata() {
      super("PvpHQ");
   }

   @Override
   protected boolean method1(JsonObject json1) {
      if (!json1.has("pvphq")) {
         LunarLogger.method5("No PvpHQ entry in tier tagger data!", new Object[0]);
         return false;
      } else {
         json1 = json1.getAsJsonObject("pvphq");
         this.method4(json1);
         this.method2(json1);
         LunarLogger.method3("[PvpHQ] Loaded %s ladders", new Object[]{this.field5.size()});
         return true;
      }
   }

   private void method2(JsonObject json1) {
      if (!json1.has("gameModes")) {
         LunarLogger.method5("No game modes for PvpHQ", new Object[0]);
      } else {
         this.field5.addAll(this.method5(json1.getAsJsonObject("gameModes")));
      }
   }

   public static PvphqMetadata method3() {
      if (field4 == null) {
         field4 = new PvphqMetadata();
      }

      return field4;
   }

   @Generated
   public List<TierGameMode> method4() {
      return this.field5;
   }
}
