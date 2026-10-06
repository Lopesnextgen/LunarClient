package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.client.config.GeneralSettings;
import com.moonsworth.lunar.client.config.PerformanceSettings;
import com.moonsworth.lunar.client.config.GlobalOptionsSettings;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModLoadState;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class OverlayCleanupMigration implements ConfigMigration {
   private final List<Consumer<JsonObject>> field1 = new ArrayList<>();

   public OverlayCleanupMigration() {
      if (Ref.method4() != null) {
         Ref.method4().method40().method84().IHOOOOOOHOHIIHHIIICHOHHHOCOOHH().method1(ModTraits.field8, (arg1, arg2, arg3) -> {
            if (arg3 == ModLoadState.LOADED_CONFIG) {
               this.method5(Ref.method4().method40().method84());
            }
         });
      }
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object obj2, JsonObject json3) {
      if (obj2 instanceof GeneralSettings) {
         this.method4("clearGlass", json3, this::method2);
         this.method4("redString", json3, (arg0, arg1x) -> arg1x.add("coloredString", arg0));
         this.method3("fireHeight", json3);
         this.method3("shieldHeight", json3);
         this.method3("minimalViewBobbing", json3);
      } else if (obj2 instanceof PerformanceSettings) {
         this.method3("glint", json3);
         this.method3("hideFoliage", json3);
         this.method3("groundArrows", json3);
         this.method3("stuckArrows", json3);
         this.method3("hideSkulls", json3);
         this.method3("hideHelmet", json3);
         this.method3("hideChest", json3);
         this.method3("hideLeggings", json3);
         this.method3("hideBoots", json3);
         this.method3("selfOnly", json3);
         this.method3("hideEndportals", json3);
         this.method3("entityShadow", json3);
      } else if (obj2 instanceof GlobalOptionsSettings) {
         json3.addProperty("seenOverlayMigration", false);
      }

      if (obj2 instanceof OverlayMod) {
         if (this.method6(json3)) {
            if (json3.has("enabled")) {
               json3.remove("enabled");
            }

            json3.add("enabled", new JsonPrimitive(true));
         }
      } else if (Ref.method4() != null) {
         OverlayMod overlaymod4 = Ref.method4().method40().method84();
         if (overlaymod4.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field8) == ModLoadState.LOADED_CONFIG) {
            this.method5(overlaymod4);
         }
      }
   }

   private void method2(JsonElement element1, JsonObject json2) {
      String text3 = element1.getAsString();
      if (text3.equalsIgnoreCase("regular")) {
         json2.addProperty("clearGlass", true);
      } else if (text3.equalsIgnoreCase("all")) {
         json2.addProperty("clearGlass", true);
         json2.addProperty("clearColoredGlass", true);
      }
   }

   private void method3(String text1, JsonObject json2) {
      if (json2.has(text1)) {
         JsonElement element3 = json2.remove(text1);
         this.field1.add(arg2x -> arg2x.add(text1, element3));
      }
   }

   private void method4(String text1, JsonObject json2, OverlayCleanupMigration.Extension extension3) {
      if (json2.has(text1)) {
         JsonElement element4 = json2.remove(text1);
         this.field1.add(arg2x -> extension3.addOption(element4, arg2x));
      }
   }

   private void method5(OverlayMod overlaymod1) {
      if (!this.field1.isEmpty()) {
         JsonObject json2 = new JsonObject();
         overlaymod1.method41(json2);
         if (this.method6(json2)) {
            if (json2.has("enabled")) {
               json2.remove("enabled");
            }

            json2.add("enabled", new JsonPrimitive(true));
         }

         if (json2.has("version")) {
            json2.remove("version");
         }

         json2.addProperty("version", ConfigMigrator.field2);
         overlaymod1.load(json2);
      }
   }

   private boolean method6(JsonObject json1) {
      if (this.field1.isEmpty()) {
         return false;
      }

      JsonObject json2;
      if (json1.has("options")) {
         json2 = json1.getAsJsonObject("options");
      } else {
         json2 = new JsonObject();
         json1.add("options", json2);
      }

      Iterator iterator3 = this.field1.iterator();

      while (iterator3.hasNext()) {
         ((Consumer)iterator3.next()).accept(json2);
         iterator3.remove();
      }

      return true;
   }

   @FunctionalInterface
   private interface Extension {
      void addOption(JsonElement element1, JsonObject json2);
   }
}
