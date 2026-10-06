package com.moonsworth.lunar.client.driver.core.holograms;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.driver.holograms.CosmeticHologramLegacy;
import com.moonsworth.lunar.client.driver.core.holograms.mixin.EmoteHologramLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.webosr.javascript.CallbackJS;
import lombok.Generated;

public class Holograms {
   private final HologramRendererLegacy field1;

   @CallbackJS("addModel")
   public void method1(String var1) {
      JsonArray var2 = (JsonArray)ThreadModuleDump48.field22.fromJson(var1, JsonArray.class);
      var2.forEach(var1x -> {
         Holograms2 var2x = this.method11(var1x);
         if (var2x != null && !this.field1.method15().containsKey(var2x.getId())) {
            Holograms2 var3 = this.field1.method10(var2x.getId());
            if (var3 != null) {
               this.field1.method11(var2x.getId());
            }

            var2x.method53(this.field1);
            this.field1.method9(var2x);
         }
      });
   }

   @CallbackJS("updateModel")
   public void method2(String var1) {
      JsonArray var2 = (JsonArray)ThreadModuleDump48.field22.fromJson(var1, JsonArray.class);
      var2.forEach(var1x -> {
         Holograms2 var2x = this.method11(var1x);
         if (var2x != null) {
            Holograms2 var3 = this.field1.method15().get(var2x.getId());
            if (var3 != null) {
               var3.method8(var2x);
            } else {
               Holograms2 var4 = this.field1.method10(var2x.getId());
               if (var4 != null) {
                  var4.method8(var2x);
               }
            }
         }
      });
   }

   @CallbackJS("removeModel")
   public void method3(String var1) {
      String[] var2 = (String[])ThreadModuleDump48.field22.fromJson(var1, String[].class);

      for (String var6 : var2) {
         this.remove(var6);
      }
   }

   @CallbackJS("setHighlightingEnabled")
   public void method4(Boolean var1) {
      this.field1.method17(var1);
   }

   @CallbackJS("forceHighlightCosmetic")
   public void method5(Integer var1) {
      this.field1.method13().method21(var1);
   }

   @CallbackJS("selectCosmetic")
   public void method6(Integer var1) {
      this.field1.method13().method22(var1.intValue());
      if (var1 == 0) {
         this.field1.method13().method25(null);
      } else {
         this.field1.method13().method21(0);
      }
   }

   @CallbackJS("playEmote")
   public void method7(String var1) {
      for (Holograms2 var3 : this.field1.method15().values()) {
         if (var3 instanceof EmoteHologramLegacy var4 && var4.getId().equals(var1)) {
            var4.method28(true);
            return;
         }
      }
   }

   @CallbackJS("stopEmote")
   public void method8(String var1) {
      for (Holograms2 var3 : this.field1.method15().values()) {
         if (var3 instanceof EmoteHologramLegacy var4 && var4.getId().equals(var1)) {
            var4.method28(false);
            return;
         }
      }
   }

   @CallbackJS("setToggleButtonHoveredState")
   public void method9(Boolean var1) {
      this.field1.method13().method24(var1);
   }

   @CallbackJS("setHoveredModel")
   public void method10(String var1, String var2) {
      if (var1 != null && !var1.isEmpty()) {
         if (var2 != null && !var2.isEmpty()) {
            HologramBoundsLegacy var3 = (HologramBoundsLegacy)ThreadModuleDump48.field22.fromJson(var2, HologramBoundsLegacy.class);
            Holograms2 var4 = this.field1.method15().get(var1);
            if (var4 != null) {
               var4.method14().setX(var3.getX());
               var4.method14().setY(var3.getY());
            }
         }

         this.field1.method1(var1);
      } else {
         this.field1.method1(null);
         this.field1.method13().method10();
      }
   }

   private void remove(String var1) {
      Holograms2 var2 = this.field1.method15().get(var1);
      if (var2 != null) {
         var2.remove();
         this.field1.processQueue();
      } else {
         this.field1.method11(var1);
      }
   }

   private Holograms2<?> method11(JsonElement var1) {
      Holograms2 var2 = null;
      if (var1.isJsonObject()) {
         JsonObject var3 = var1.getAsJsonObject();
         String var4 = var3.has("type") ? var3.get("type").getAsString() : "";
         if (var4.equals("PLAYER")) {
            var2 = (Holograms2)ThreadModuleDump48.field22.fromJson(var1, EmoteHologramLegacy.class);
         } else if (var4.equals("COSMETIC")) {
            var2 = (Holograms2)ThreadModuleDump48.field22.fromJson(var1, CosmeticHologramLegacy.class);
         }
      }

      return var2;
   }

   @Generated
   public Holograms(HologramRendererLegacy var1) {
      this.field1 = var1;
   }
}
