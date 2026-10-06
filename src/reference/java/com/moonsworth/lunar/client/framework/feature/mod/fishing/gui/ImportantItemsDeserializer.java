package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import java.util.HashSet;

public class ImportantItemsDeserializer implements JsonDeserializer<com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.ImportantItems> {
   public ImportantItemsDeserializer() {
   }

   public com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.ImportantItems method1(JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3) {
      JsonObject json4 = element1.getAsJsonObject();
      com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.ImportantItemCategory gui25 = this.method2(json4.getAsJsonObject("dungeons"));
      com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.ImportantItemCategory gui26 = this.method2(json4.getAsJsonObject("kuudra"));
      return new com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.ImportantItems(gui25, gui26);
   }

   private com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.ImportantItemCategory method2(JsonObject json1) {
      HashSet set2 = this.method3(json1.getAsJsonArray("weapons"));
      HashSet set3 = this.method3(json1.getAsJsonArray("items"));
      HashSet set4 = this.method3(json1.getAsJsonArray("pets"));
      return new com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.ImportantItemCategory(set2, set3, set4);
   }

   private HashSet<String> method3(JsonArray array1) {
      HashSet set2 = new HashSet();

      for (JsonElement element4 : array1) {
         set2.add(element4.getAsString());
      }

      return set2;
   }
}
