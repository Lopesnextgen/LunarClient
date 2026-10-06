package com.moonsworth.lunar.client.framework.feature.rewind.highlight;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind2_3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator;
import com.moonsworth.lunar.client.config.migration.ConfigMigrator;
import java.util.Map.Entry;
import lombok.Generated;

public class LinkedHashMapImpl extends com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.LinkedHashMapImpl<String, Fishing2Iterator> {
   public LinkedHashMapImpl(Nameplate2 var1) {
      super(var1);
   }

   public static class Data extends TypeAdapter<LinkedHashMapImpl> {
      private final Rewind2_3 field1;

      public void method1(JsonWriter var1, LinkedHashMapImpl var2) {
         JsonObject var3 = new JsonObject();

         for (Entry var5 : var2.entrySet()) {
            var3.add((String)var5.getKey(), this.method2((Fishing2Iterator)var5.getValue()));
         }

         this.field1.method29().toJson(var3, var1);
      }

      private JsonObject method2(Fishing2Iterator var1) {
         JsonObject var2 = new JsonObject();
         if (var1.isEnabled() != var1.method13()) {
            var2.addProperty("enabled", var1.isEnabled());
         }

         if (var1.isExtended()) {
            var2.addProperty("extended", true);
         }

         JsonObject var3 = new JsonObject();

         for (Fishing2Iterator var5 : var1.method11().values()) {
            JsonObject var6 = this.method2(var5);
            if (var1.method17() || !var6.isEmpty()) {
               var3.add(var5.type(), var6);
            }
         }

         if (!var3.isEmpty()) {
            var2.add("childProperties", var3);
         }

         JsonObject var8 = new JsonObject();

         for (Fishing2Loader var10 : var1.method12().values()) {
            JsonObject var7 = new JsonObject();
            var10.method17(var7);
            if (!var7.isEmpty() || var1.method18()) {
               var8.add(var10.type(), var7);
            }
         }

         if (!var8.isEmpty()) {
            var2.add("keyframes", var8);
         }

         return var2;
      }

      public LinkedHashMapImpl method3(JsonReader var1) {
         LinkedHashMapImpl var2 = new LinkedHashMapImpl(this.field1.method40());
         JsonObject var3 = (JsonObject)this.field1.method29().fromJson(var1, JsonObject.class);
         ConfigMigrator.method3(var2, var3);

         for (Entry var5 : var3.entrySet()) {
            Fishing2Iterator var6 = this.field1.method38().method2(this.field1.method40(), (String)var5.getKey());
            if (var6 != null) {
               var6.method19(false);
               this.method4(var6, ((JsonElement)var5.getValue()).getAsJsonObject());
               var2.method3((String)var5.getKey(), var6);
            }
         }

         return var2;
      }

      private void method4(Fishing2Iterator var1, JsonObject var2) {
         if (var2.has("enabled")) {
            var1.setEnabled(var2.get("enabled").getAsBoolean());
         }

         if (var2.has("extended")) {
            var1.method19(var2.get("extended").getAsBoolean());
         }

         if (var2.has("childProperties")) {
            JsonObject var3 = var2.getAsJsonObject("childProperties");

            for (Entry var5 : var3.entrySet()) {
               Fishing2Iterator var6 = var1.method11().get(var5.getKey());
               if (var6 == null) {
                  if (!var1.method17()) {
                     continue;
                  }

                  var6 = this.field1.method38().method2(this.field1.method40(), (String)var5.getKey());
                  if (var6 == null) {
                     continue;
                  }

                  var1.method11().put((String)var5.getKey(), var6);
               }

               JsonObject var7 = ((JsonElement)var5.getValue()).getAsJsonObject();
               if (!var7.isEmpty()) {
                  this.method4(var6, var7);
               }
            }
         }

         if (var2.has("keyframes")) {
            JsonObject var8 = var2.getAsJsonObject("keyframes");

            for (Entry var10 : var8.entrySet()) {
               Fishing2Loader var11 = var1.method12().get(var10.getKey());
               if (var11 == null) {
                  if (!var1.method18()) {
                     continue;
                  }

                  var11 = this.field1.method39().method2((String)var10.getKey());
                  if (var11 == null) {
                     continue;
                  }

                  var1.method12().put((String)var10.getKey(), var11);
               }

               JsonObject var12 = ((JsonElement)var10.getValue()).getAsJsonObject();
               if (!var12.isEmpty()) {
                  var11.load(var12);
               }
            }
         }
      }

      @Generated
      public Data(Rewind2_3 var1) {
         this.field1 = var1;
      }
   }
}
