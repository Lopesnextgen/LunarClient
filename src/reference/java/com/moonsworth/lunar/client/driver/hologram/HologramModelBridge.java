package com.moonsworth.lunar.client.driver.hologram;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.driver.hologram.CosmeticHologram;
import com.moonsworth.lunar.client.driver.hologram.EmoteHologram;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.webosr.javascript.CallbackJS;
import lombok.Generated;
import com.moonsworth.lunar.client.driver.core.holograms.Holograms2;

public class HologramModelBridge {
   private final HologramRenderer field1;

   @CallbackJS("addModel")
   public void method1(String text1) {
      JsonArray array2 = (JsonArray)LunarConstants.field22.fromJson(text1, JsonArray.class);
      array2.forEach(arg1x -> {
         Holograms2 holograms22x = this.method11(arg1x);
         if (holograms22x != null && !this.field1.method15().containsKey(holograms22x.getId())) {
            Holograms2 holograms23 = this.field1.method10(holograms22x.getId());
            if (holograms23 != null) {
               this.field1.method11(holograms22x.getId());
            }

            holograms22x.method53(this.field1);
            this.field1.method9(holograms22x);
         }
      });
   }

   @CallbackJS("updateModel")
   public void method2(String text1) {
      JsonArray array2 = (JsonArray)LunarConstants.field22.fromJson(text1, JsonArray.class);
      array2.forEach(arg1x -> {
         Holograms2 holograms22x = this.method11(arg1x);
         if (holograms22x != null) {
            Holograms2 holograms23 = this.field1.method15().get(holograms22x.getId());
            if (holograms23 != null) {
               holograms23.method8(holograms22x);
            } else {
               Holograms2 holograms24 = this.field1.method10(holograms22x.getId());
               if (holograms24 != null) {
                  holograms24.method8(holograms22x);
               }
            }
         }
      });
   }

   @CallbackJS("removeModel")
   public void method3(String text1) {
      String[] items2 = (String[])LunarConstants.field22.fromJson(text1, String[].class);

      for (String text6 : items2) {
         this.remove(text6);
      }
   }

   @CallbackJS("setHighlightingEnabled")
   public void method4(Boolean flag1) {
      this.field1.method17(flag1);
   }

   @CallbackJS("forceHighlightCosmetic")
   public void method5(Integer number1) {
      this.field1.method13().method21(number1);
   }

   @CallbackJS("selectCosmetic")
   public void method6(Integer number1) {
      this.field1.method13().method22(number1.intValue());
      if (number1 == 0) {
         this.field1.method13().method25(null);
      } else {
         this.field1.method13().method21(0);
      }
   }

   @CallbackJS("playEmote")
   public void method7(String text1) {
      for (Holograms2 holograms23 : this.field1.method15().values()) {
         if (holograms23 instanceof EmoteHologram holograms2iterator4 && holograms2iterator4.getId().equals(text1)) {
            holograms2iterator4.method28(true);
            return;
         }
      }
   }

   @CallbackJS("stopEmote")
   public void method8(String text1) {
      for (Holograms2 holograms23 : this.field1.method15().values()) {
         if (holograms23 instanceof EmoteHologram holograms2iterator4 && holograms2iterator4.getId().equals(text1)) {
            holograms2iterator4.method28(false);
            return;
         }
      }
   }

   @CallbackJS("setToggleButtonHoveredState")
   public void method9(Boolean flag1) {
      this.field1.method13().method24(flag1);
   }

   @CallbackJS("setHoveredModel")
   public void method10(String text1, String text2) {
      if (text1 != null && !text1.isEmpty()) {
         if (text2 != null && !text2.isEmpty()) {
            HologramBounds holograms53 = (HologramBounds)LunarConstants.field22.fromJson(text2, HologramBounds.class);
            Holograms2 holograms24 = this.field1.method15().get(text1);
            if (holograms24 != null) {
               holograms24.method14().setX(holograms53.getX());
               holograms24.method14().setY(holograms53.getY());
            }
         }

         this.field1.method1(text1);
      } else {
         this.field1.method1(null);
         this.field1.method13().method10();
      }
   }

   private void remove(String text1) {
      Holograms2 holograms22 = this.field1.method15().get(text1);
      if (holograms22 != null) {
         holograms22.remove();
         this.field1.processQueue();
      } else {
         this.field1.method11(text1);
      }
   }

   private Holograms2<?> method11(JsonElement element1) {
      Holograms2 holograms22 = null;
      if (element1.isJsonObject()) {
         JsonObject json3 = element1.getAsJsonObject();
         String text4 = json3.has("type") ? json3.get("type").getAsString() : "";
         if (text4.equals("PLAYER")) {
            holograms22 = (Holograms2)LunarConstants.field22.fromJson(element1, EmoteHologram.class);
         } else if (text4.equals("COSMETIC")) {
            holograms22 = (Holograms2)LunarConstants.field22.fromJson(element1, CosmeticHologram.class);
         }
      }

      return holograms22;
   }

   @Generated
   public HologramModelBridge(HologramRenderer holograms41) {
      this.field1 = holograms41;
   }
}
