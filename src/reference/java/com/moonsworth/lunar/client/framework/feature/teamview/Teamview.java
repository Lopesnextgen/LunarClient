package com.moonsworth.lunar.client.framework.feature.teamview;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.listener.HypixelLocation;
import com.moonsworth.lunar.client.framework.LunarConstants;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import lombok.Generated;

class Teamview {
   private boolean loaded = false;
   private List<Teamview.Data> field1;
   private List<Teamview.Data> field2;

   Teamview() {
   }

   public void load() {
      this.field1 = new ArrayList<>();
      this.field2 = new ArrayList<>();
      this.loaded = true;
      String text1 = "hypixel/teamview.json";
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
            if (json5.has("partySupport")) {
               this.method1(json5.getAsJsonArray("partySupport"), this.field1);
            }

            if (json5.has("blacklist")) {
               this.method1(json5.getAsJsonArray("blacklist"), this.field2);
            }
         }
      }
   }

   private void method1(JsonArray array1, List<Teamview.Data> list2) {
      for (JsonElement element4 : array1) {
         if (element4 instanceof JsonObject json5) {
            Teamview.TeamViewMatcher data26 = null;
            if (json5.has("gametype")) {
               data26 = new Teamview.TeamViewMatcher(new String[]{json5.get("gametype").getAsString()}, false);
            } else if (json5.has("gametypeRegex")) {
               data26 = new Teamview.TeamViewMatcher(new String[]{json5.get("gametypeRegex").getAsString()}, true);
            } else if (json5.has("gametypes")) {
               data26 = new Teamview.TeamViewMatcher(this.method2(json5.get("gametypes")), false);
            }

            Teamview.TeamViewMatcher data27 = null;
            if (json5.has("mode")) {
               data27 = new Teamview.TeamViewMatcher(new String[]{json5.get("mode").getAsString()}, false);
            } else if (json5.has("modeRegex")) {
               data27 = new Teamview.TeamViewMatcher(new String[]{json5.get("modeRegex").getAsString()}, true);
            } else if (json5.has("modes")) {
               data27 = new Teamview.TeamViewMatcher(this.method2(json5.get("modes")), false);
            }

            Teamview.TeamViewMatcher data28 = null;
            if (json5.has("map")) {
               data28 = new Teamview.TeamViewMatcher(new String[]{json5.get("map").getAsString()}, false);
            } else if (json5.has("mapRegex")) {
               data28 = new Teamview.TeamViewMatcher(new String[]{json5.get("mapRegex").getAsString()}, true);
            } else if (json5.has("maps")) {
               data28 = new Teamview.TeamViewMatcher(this.method2(json5.get("maps")), false);
            }

            list2.add(new Teamview.Data(data26, data27, data28));
         }
      }
   }

   private String[] method2(JsonElement element1) {
      if (element1 instanceof JsonArray array2) {
         String[] items3 = new String[array2.size()];

         for (int index4 = 0; index4 < array2.size(); index4++) {
            JsonElement element5 = array2.get(index4);
            if (element5.isJsonPrimitive()) {
               items3[index4] = element5.getAsString();
            }
         }

         return items3;
      } else {
         return new String[0];
      }
   }

   public boolean method3(HypixelLocation rewindhandlers21) {
      if (!rewindhandlers21.method1() && rewindhandlers21.field3 != null) {
         for (Teamview.Data data3 : this.field1) {
            if (data3.method1(rewindhandlers21)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public boolean method4(HypixelLocation rewindhandlers21) {
      if (rewindhandlers21.method1()) {
         return false;
      }

      for (Teamview.Data data3 : this.field2) {
         if (data3.method1(rewindhandlers21)) {
            return true;
         }
      }

      return false;
   }

   @Generated
   public boolean isLoaded() {
      return this.loaded;
   }

   private class Data {
      @Nullable
      private final Teamview.TeamViewMatcher field1;
      @Nullable
      private final Teamview.TeamViewMatcher field2;
      @Nullable
      private final Teamview.TeamViewMatcher field3;

      private Data(@Nullable Teamview.TeamViewMatcher data21, @Nullable Teamview.TeamViewMatcher data22, @Nullable Teamview.TeamViewMatcher data23) {
         this.field1 = data21;
         this.field2 = data22;
         this.field3 = data23;
      }

      public boolean method1(HypixelLocation rewindhandlers21) {
         return this.method2(this.field1, rewindhandlers21.field2) && this.method2(this.field2, rewindhandlers21.field3) && this.method2(this.field3, rewindhandlers21.field4);
      }

      private boolean method2(Teamview.TeamViewMatcher data21, String text2) {
         if (data21 == null || data21.field1.length == 0) {
            return true;
         }

         if (text2 == null) {
            return false;
         }

         for (String text6 : data21.field1) {
            if (data21.field2 ? text2.matches(text6) : text2.equals(text6)) {
               return true;
            }
         }

         return false;
      }

      @Nullable
      public Teamview.TeamViewMatcher method3() {
         return this.field1;
      }

      @Nullable
      public Teamview.TeamViewMatcher method4() {
         return this.field2;
      }

      @Nullable
      public Teamview.TeamViewMatcher method5() {
         return this.field3;
      }
   }

   private class TeamViewMatcher {
      private final String[] field1;
      private final boolean field2;

      private TeamViewMatcher(String[] items1, boolean flag2) {
         this.field1 = items1;
         this.field2 = flag2;
      }

      public String[] method1() {
         return this.field1;
      }

      public boolean regex() {
         return this.field2;
      }
   }
}
