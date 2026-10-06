package com.moonsworth.lunar.client.framework.feature.tiertagger.rewindhandlers;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.feature.tiertagger.TierMetadataRegistry;
import com.moonsworth.lunar.client.framework.feature.tiertagger.TierGameMode;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nullable;
import lombok.Generated;

public class TiertestsMetadata extends TierMetadataRegistry {
   private static TiertestsMetadata field4;
   private final List<TierGameMode> field5 = new ArrayList<>();
   private final Map<String, TiertestsMetadata.Data> field6 = new HashMap<>();
   private String field7;
   private String field8;
   private String field9;
   private String field10;

   private TiertestsMetadata() {
      super("TierTests");
   }

   @Nullable
   public TiertestsMetadata.Data method1(String text1) {
      return this.field6.get(text1.toLowerCase(Locale.ROOT));
   }

   @Nullable
   public String method2(int number1, int number2) {
      Optional optional3 = this.field6.values().stream().filter(arg2x -> arg2x.field3 == number1 && arg2x.field4 == number2).findFirst();
      return optional3.<String>map(arg0 -> arg0.field2).orElse(null);
   }

   @Override
   protected boolean method1(JsonObject json1) {
      if (!json1.has("tiertests")) {
         LunarLogger.method5("No TierTests entry in tier tagger data!", new Object[0]);
         return false;
      } else {
         json1 = json1.getAsJsonObject("tiertests");
         if ((this.field7 = this.method4("apiUrl", json1)) != null
            && (this.field8 = this.method4("gamemodesEndpointLegacy", json1)) != null
            && (this.field9 = this.method4("gamemodesEndpointModern", json1)) != null
            && (this.field10 = this.method4("tierEndpoint", json1)) != null) {
            this.method4(json1);
            this.method8(json1);
            this.method9(json1);
            LunarLogger.method3("[TierTests] Loaded %s fallback modes and %s tier format data", new Object[]{this.field5.size(), this.field6.size()});
            return true;
         } else {
            return false;
         }
      }
   }

   private String method4(String text1, JsonObject json2) {
      if (json2.has(text1) && json2.get(text1).isJsonPrimitive()) {
         String text3 = json2.get(text1).getAsString();
         if (text3.startsWith("/")) {
            text3 = text3.substring(1);
         }

         if (!text3.endsWith("/")) {
            text3 = text3 + "/";
         }

         return text3;
      } else {
         LunarLogger.method3("!! [TierTests] No " + text1 + " in tier tagger data !!", new Object[0]);
         return null;
      }
   }

   private void method8(JsonObject json1) {
      if (!json1.has("mcTiersFormat")) {
         LunarLogger.method5("No tier format data for TierTests", new Object[0]);
      } else {
         json1 = json1.getAsJsonObject("mcTiersFormat");

         for (String text3 : json1.keySet()) {
            JsonObject json4 = json1.getAsJsonObject(text3);
            TiertestsMetadata.Data data5 = new TiertestsMetadata.Data(
               text3, json4.get("displayName").getAsString(), json4.get("tier").getAsInt(), json4.get("pos").getAsInt()
            );
            this.field6.put(text3.toLowerCase(Locale.ROOT), data5);
         }
      }
   }

   private void method9(JsonObject json1) {
      String text2 = Ref.MC_VERSION > 1 ? "fallbackGameModesModern" : "fallbackGameModes";
      if (!json1.has(text2)) {
         LunarLogger.method5("No fallback game modes for TierTests", new Object[0]);
      } else {
         this.field5.addAll(this.method5(json1.getAsJsonObject(text2)));
      }
   }

   public static TiertestsMetadata method7() {
      if (field4 == null) {
         field4 = new TiertestsMetadata();
      }

      return field4;
   }

   @Generated
   public List<TierGameMode> method8() {
      return this.field5;
   }

   @Generated
   public Map<String, TiertestsMetadata.Data> method9() {
      return this.field6;
   }

   @Generated
   public String method10() {
      return this.field7;
   }

   @Generated
   public String method11() {
      return this.field8;
   }

   @Generated
   public String method12() {
      return this.field9;
   }

   @Generated
   public String method13() {
      return this.field10;
   }

   public class Data {
      private final String field1;
      private final String field2;
      private final int field3;
      private final int field4;

      public Data(String text1, String text2, int number3, int number4) {
         this.field1 = text1;
         this.field2 = text2;
         this.field3 = number3;
         this.field4 = number4;
      }

      public String apiName() {
         return this.field1;
      }

      public String displayName() {
         return this.field2;
      }

      public int tier() {
         return this.field3;
      }

      public int method1() {
         return this.field4;
      }
   }
}
