package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import java.util.HashSet;

public class JsonDeserializerIterator implements JsonDeserializer<com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.Gui5> {
   public com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.Gui5 method1(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = var1.getAsJsonObject();
      com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.Gui2 var5 = this.method2(var4.getAsJsonObject("dungeons"));
      com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.Gui2 var6 = this.method2(var4.getAsJsonObject("kuudra"));
      return new com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.Gui5(var5, var6);
   }

   private com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.Gui2 method2(JsonObject var1) {
      HashSet var2 = this.method3(var1.getAsJsonArray("weapons"));
      HashSet var3 = this.method3(var1.getAsJsonArray("items"));
      HashSet var4 = this.method3(var1.getAsJsonArray("pets"));
      return new com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.Gui2(var2, var3, var4);
   }

   private HashSet<String> method3(JsonArray var1) {
      HashSet var2 = new HashSet();

      for (JsonElement var4 : var1) {
         var2.add(var4.getAsString());
      }

      return var2;
   }
}
