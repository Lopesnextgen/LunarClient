package com.moonsworth.lunar.client.framework.feature.rewind.gui;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind2_3;
import com.moonsworth.lunar.client.framework.feature.rewind.holograms.Holograms4;
import java.lang.reflect.Type;
import lombok.Generated;

public class Gui2$Data implements JsonDeserializer<Gui2_2> {
   private final Rewind2_3 field1;

   public Gui2_2 method1(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      Gui2_2 var4 = new Gui2_2(this.field1.method40());
      JsonObject var5 = var1.getAsJsonObject();
      if (var5.has("effects")) {
         for (JsonElement var8 : var5.getAsJsonArray("effects")) {
            String var9 = var8.getAsJsonObject().get("type").getAsString();
            GuiImpl var10 = (GuiImpl)var3.deserialize(var8, Holograms4.getType(var9));
            var4.method1(var10);
            var4.field1.add(var10);
         }
      }

      if (var5.has("gameplay")) {
         JsonElement var11 = var5.get("gameplay");
         if (var11.isJsonObject()) {
            GuiImpl3 var14 = (GuiImpl3)var3.deserialize(var11, GuiImpl3.class);
            var4.method1(var14);
            var4.field2.add(var14);
         } else if (var11.isJsonArray()) {
            for (JsonElement var20 : var11.getAsJsonArray()) {
               GuiImpl3 var22 = (GuiImpl3)var3.deserialize(var20, GuiImpl3.class);
               var4.method1(var22);
               var4.field2.add(var22);
            }
         }
      }

      if (var5.has("audios")) {
         for (JsonElement var19 : var5.getAsJsonArray("audios")) {
            GuiImpl2 var21 = (GuiImpl2)var3.deserialize(var19, GuiImpl2.class);
            var4.method1(var21);
            var4.field3.add(var21);
         }
      }

      for (Gui_2 var17 : var4) {
         var17.method3(null);
      }

      return var4;
   }

   @Generated
   public Gui2$Data(Rewind2_3 var1) {
      this.field1 = var1;
   }
}
