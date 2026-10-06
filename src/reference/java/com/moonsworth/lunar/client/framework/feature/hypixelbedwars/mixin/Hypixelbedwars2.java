package com.moonsworth.lunar.client.framework.feature.hypixelbedwars.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler23;
import com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers.Rewindhandlers2;
import com.moonsworth.lunar.client.mod.misc.hypixelbedwars.HypixelBedwars;
import java.util.HashMap;
import java.util.Map.Entry;
import lombok.Generated;

public class Hypixelbedwars2 {
   private final HypixelBedwars field1;
   private boolean field2;
   private int[] field3 = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
   private boolean field4;
   private static final HashMap<String, Integer> field5 = new HashMap<>();

   public Hypixelbedwars2(HypixelBedwars var1) {
      this.field1 = var1;
   }

   public void method1() {
      if (this.field1.method38() && this.field1.isEnabled() && (Boolean)this.field1.method24().get()) {
         Rewindhandlers2 var1 = GuiRewindhandlersHandler23.field7.method7();
         this.field2 = var1.field2.equals("BEDWARS") && var1.field4 != null;
      } else {
         this.field2 = false;
      }
   }

   private int[] method2(JsonArray var1) {
      int[] var2 = new int[8];

      for (int var3 = 0; var3 < var1.size(); var3++) {
         JsonPrimitive var4 = var1.get(var3).getAsJsonPrimitive();
         if (var4.isString()) {
            var2[var3] = field5.getOrDefault(var4.getAsString(), 5);
         } else {
            var2[var3] = var4.getAsInt();
         }
      }

      return var2;
   }

   private boolean method3(JsonObject var1, JsonElement var2) {
      if (var2 == null) {
         return false;
      } else if (var2 instanceof JsonArray) {
         this.field3 = this.method2(var2.getAsJsonArray());
         return true;
      } else {
         String var3 = var2.getAsString();
         var2 = var1.get(var3);
         if (var2 instanceof JsonArray) {
            this.field3 = this.method2(var2.getAsJsonArray());
            return true;
         } else {
            return false;
         }
      }
   }

   public void method4() {
      if (this.field1.method22() != null) {
         Rewindhandlers2 var1 = GuiRewindhandlersHandler23.field7.method7();
         if (var1.field4 != null) {
            JsonObject var2 = this.field1.method22().get("map").getAsJsonObject();
            if (this.method3(this.field1.method22(), var2.get(var1.field4))) {
               return;
            }
         }

         if (var1.field3 != null) {
            JsonObject var5 = this.field1.method22().get("modes").getAsJsonObject();

            for (Entry var4 : var5.entrySet()) {
               if (var1.field3.contains((CharSequence)var4.getKey()) && this.method3(this.field1.method22(), (JsonElement)var4.getValue())) {
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
   public void method7(boolean var1) {
      this.field4 = var1;
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
