package com.moonsworth.lunar.client.framework.feature.tiertagger.rewindhandlers;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.feature.tiertagger.Tiertagger6;
import com.moonsworth.lunar.client.framework.feature.tiertagger.Tiertagger_2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nullable;
import lombok.Generated;

public class Tiertagger6Iterator extends Tiertagger6 {
   private static Tiertagger6Iterator field4;
   private final List<Tiertagger_2> field5 = new ArrayList<>();
   private final Map<String, Tiertagger6Iterator.Data> field6 = new HashMap<>();
   private String field7;
   private String field8;
   private String field9;
   private String field10;

   private Tiertagger6Iterator() {
      super("TierTests");
   }

   @Nullable
   public Tiertagger6Iterator.Data method1(String var1) {
      return this.field6.get(var1.toLowerCase(Locale.ROOT));
   }

   @Nullable
   public String method2(int var1, int var2) {
      Optional var3 = this.field6.values().stream().filter(var2x -> var2x.field3 == var1 && var2x.field4 == var2).findFirst();
      return var3.<String>map(var0 -> var0.field2).orElse(null);
   }

   @Override
   protected boolean method1(JsonObject var1) {
      if (!var1.has("tiertests")) {
         Slayer.method5("No TierTests entry in tier tagger data!");
         return false;
      } else {
         var1 = var1.getAsJsonObject("tiertests");
         if ((this.field7 = this.method4("apiUrl", var1)) != null
            && (this.field8 = this.method4("gamemodesEndpointLegacy", var1)) != null
            && (this.field9 = this.method4("gamemodesEndpointModern", var1)) != null
            && (this.field10 = this.method4("tierEndpoint", var1)) != null) {
            this.method4(var1);
            this.method8(var1);
            this.method9(var1);
            Slayer.method3("[TierTests] Loaded %s fallback modes and %s tier format data", this.field5.size(), this.field6.size());
            return true;
         } else {
            return false;
         }
      }
   }

   private String method4(String var1, JsonObject var2) {
      if (var2.has(var1) && var2.get(var1).isJsonPrimitive()) {
         String var3 = var2.get(var1).getAsString();
         if (var3.startsWith("/")) {
            var3 = var3.substring(1);
         }

         if (!var3.endsWith("/")) {
            var3 = var3 + "/";
         }

         return var3;
      } else {
         Slayer.method3("!! [TierTests] No " + var1 + " in tier tagger data !!");
         return null;
      }
   }

   private void method8(JsonObject var1) {
      if (!var1.has("mcTiersFormat")) {
         Slayer.method5("No tier format data for TierTests");
      } else {
         var1 = var1.getAsJsonObject("mcTiersFormat");

         for (String var3 : var1.keySet()) {
            JsonObject var4 = var1.getAsJsonObject(var3);
            Tiertagger6Iterator.Data var5 = new Tiertagger6Iterator.Data(
               var3, var4.get("displayName").getAsString(), var4.get("tier").getAsInt(), var4.get("pos").getAsInt()
            );
            this.field6.put(var3.toLowerCase(Locale.ROOT), var5);
         }
      }
   }

   private void method9(JsonObject var1) {
      String var2 = ThreadModuleDump63.MC_VERSION > 1 ? "fallbackGameModesModern" : "fallbackGameModes";
      if (!var1.has(var2)) {
         Slayer.method5("No fallback game modes for TierTests");
      } else {
         this.field5.addAll(this.method5(var1.getAsJsonObject(var2)));
      }
   }

   public static Tiertagger6Iterator method7() {
      if (field4 == null) {
         field4 = new Tiertagger6Iterator();
      }

      return field4;
   }

   @Generated
   public List<Tiertagger_2> method8() {
      return this.field5;
   }

   @Generated
   public Map<String, Tiertagger6Iterator.Data> method9() {
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

      public Data(String var1, String var2, int var3, int var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
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
