package com.moonsworth.lunar.client.gui;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.JsonFileConfig;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Generated;

public class UiStateStore extends com.moonsworth.lunar.client.framework.ItemMapHandler<String, JsonObject> implements JsonFileConfig {
   private final GuiIterator field2 = new GuiIterator();

   @Override
   protected Map<String, JsonObject> method3() {
      return new HashMap<>();
   }

   @Override
   public String method5() {
      return "ui-state.json";
   }

   @Override
   public void init() {
      super.init();
      this.method4();
   }

   public void load(JsonObject var1) {
      this.method3().clear();

      for (Entry var3 : var1.entrySet()) {
         if (((JsonElement)var3.getValue()).isJsonObject()) {
            this.method3().put((String)var3.getKey(), ((JsonElement)var3.getValue()).getAsJsonObject());
         }
      }

      this.method10();
   }

   public void method1(JsonObject var1) {
      for (Entry var3 : this.method3().entrySet()) {
         var1.add((String)var3.getKey(), (JsonElement)var3.getValue());
      }
   }

   public void method4(String var1, Double var2, Double var3, Double var4, Double var5, Boolean var6, Boolean var7) {
      if (var1 != null && !var1.isBlank()) {
         JsonObject var8 = this.method7("windows");
         JsonObject var9 = var8.has(var1) && var8.get(var1).isJsonObject() ? var8.getAsJsonObject(var1).deepCopy() : new JsonObject();
         if (var2 != null) {
            var9.addProperty("width", var2);
         }

         if (var3 != null) {
            var9.addProperty("height", var3);
         }

         if (var4 != null) {
            var9.addProperty("x", var4);
         }

         if (var5 != null) {
            var9.addProperty("y", var5);
         }

         if (var6 != null) {
            var9.addProperty("fullscreen", var6);
         }

         if (var7 != null) {
            var9.addProperty("shown", var7);
         }

         var8.add(var1, var9);
         this.OHOOCIIHRRIRCHOIIHHROORHIOIORC();
         this.method10();
      }
   }

   public void method5(String var1, String var2) {
      if (var1 != null && !var1.isBlank()) {
         JsonObject var3 = this.method7("sorts");
         var3.addProperty(var1, var2);
         this.OHOOCIIHRRIRCHOIIHHROORHIOIORC();
         this.method10();
      }
   }

   public void method6(JsonObject var1) {
      if (var1 != null) {
         this.method3().put("quickNavigation", var1);
         this.OHOOCIIHRRIRCHOIIHHROORHIOIORC();
         this.method10();
      }
   }

   private JsonObject method7(String var1) {
      return this.method3().computeIfAbsent(var1, var0 -> new JsonObject());
   }

   private void method10() {
      for (Entry var2 : this.method3().entrySet()) {
         this.field2.method3((String)var2.getKey(), ((JsonObject)var2.getValue()).deepCopy());
      }
   }

   @Generated
   public GuiIterator method11() {
      return this.field2;
   }
}
