package com.moonsworth.lunar.client.framework.feature.hypixelbedwars.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.framework.listener.HypixelLocation;
import com.moonsworth.lunar.client.mod.misc.hypixelbedwars.HypixelBedwars;
import java.util.HashMap;
import java.util.Map.Entry;
import lombok.Generated;

public class BedwarsTeamColorMapper {
   private final HypixelBedwars field1;
   private boolean field2;
   private int[] field3 = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
   private boolean field4;
   private static final HashMap<String, Integer> field5 = new HashMap<>();

   public BedwarsTeamColorMapper(HypixelBedwars hypixelbedwars1) {
      this.field1 = hypixelbedwars1;
   }

   public void method1() {
      if (this.field1.method38() && this.field1.isEnabled() && (Boolean)this.field1.method24().get()) {
         HypixelLocation rewindhandlers21 = HypixelLocationListener.field7.method7();
         this.field2 = rewindhandlers21.field2.equals("BEDWARS") && rewindhandlers21.field4 != null;
      } else {
         this.field2 = false;
      }
   }

   private int[] method2(JsonArray array1) {
      int[] items2 = new int[8];

      for (int index3 = 0; index3 < array1.size(); index3++) {
         JsonPrimitive json4 = array1.get(index3).getAsJsonPrimitive();
         if (json4.isString()) {
            items2[index3] = field5.getOrDefault(json4.getAsString(), 5);
         } else {
            items2[index3] = json4.getAsInt();
         }
      }

      return items2;
   }

   private boolean method3(JsonObject json1, JsonElement element2) {
      if (element2 == null) {
         return false;
      } else if (element2 instanceof JsonArray) {
         this.field3 = this.method2(element2.getAsJsonArray());
         return true;
      } else {
         String text3 = element2.getAsString();
         element2 = json1.get(text3);
         if (element2 instanceof JsonArray) {
            this.field3 = this.method2(element2.getAsJsonArray());
            return true;
         } else {
            return false;
         }
      }
   }

   public void method4() {
      if (this.field1.method22() != null) {
         HypixelLocation rewindhandlers21 = HypixelLocationListener.field7.method7();
         if (rewindhandlers21.field4 != null) {
            JsonObject json2 = this.field1.method22().get("map").getAsJsonObject();
            if (this.method3(this.field1.method22(), json2.get(rewindhandlers21.field4))) {
               return;
            }
         }

         if (rewindhandlers21.field3 != null) {
            JsonObject json5 = this.field1.method22().get("modes").getAsJsonObject();

            for (Entry entry4 : json5.entrySet()) {
               if (rewindhandlers21.field3.contains((CharSequence)entry4.getKey()) && this.method3(this.field1.method22(), (JsonElement)entry4.getValue())) {
                  return;
               }
            }
         }

         this.field3 = this.method2(this.field1.method22().get("default").getAsJsonArray());
      }
   }

   @Generated
   public boolean method5() {
      return this.field2;
   }

   @Generated
   public int[] method6() {
      return this.field3;
   }

   @Generated
   public void method7(boolean flag) {
      this.field4 = flag;
   }

   @Generated
   public boolean method8() {
      return this.field4;
   }

   static {
      field5.put("yellow", 0);
      field5.put("cyan", 1);
      field5.put("aqua", 1);
      field5.put("white", 2);
      field5.put("pink", 3);
      field5.put("gray", 4);
      field5.put("red", 5);
      field5.put("blue", 6);
      field5.put("green", 7);
   }
}
