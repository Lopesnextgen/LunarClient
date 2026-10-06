package com.moonsworth.lunar.client.framework.feature.rewind.highlight;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind2_3;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui5;
import java.io.File;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import lombok.Generated;

public class Highlight$Data extends TypeAdapter<Highlight_4> {
   private final Rewind2_3 field1;

   public void method1(JsonWriter var1, Highlight_4 var2) {
      JsonObject var3 = new JsonObject();
      JsonObject var4 = new JsonObject();

      for (Entry var6 : var2.field5.entrySet()) {
         String var7 = Gui5.method2((File)var6.getValue(), this.field1.method32());
         var4.addProperty(((UUID)var6.getKey()).toString(), var7);
      }

      var3.add("media", var4);
      JsonObject var10 = new JsonObject();

      for (Entry var13 : var2.field6.entrySet()) {
         var10.addProperty(((UUID)var13.getKey()).toString(), (String)var13.getValue());
      }

      var3.add("folders", var10);
      JsonObject var12 = new JsonObject();

      for (Entry var8 : var2.field7.entrySet()) {
         var12.addProperty(((UUID)var8.getKey()).toString(), ((UUID)var8.getValue()).toString());
      }

      var3.add("parents", var12);
      JsonObject var15 = new JsonObject();

      for (Entry var9 : var2.field8.entrySet()) {
         var15.addProperty(((UUID)var9.getKey()).toString(), (String)var9.getValue());
      }

      var3.add("names", var15);
      this.field1.method29().toJson(var3, var1);
   }

   public Highlight_4 method2(JsonReader var1) {
      Highlight_4 var2 = new Highlight_4();
      JsonObject var3 = (JsonObject)this.field1.method29().fromJson(var1, JsonObject.class);
      if (var3 == null) {
         return var2;
      }

      if (var3.has("media") && var3.get("media").isJsonObject()) {
         this.method3(var2, var3.getAsJsonObject("media"));
         this.method4(var3, "folders", var2.field6);
         this.method4(var3, "names", var2.field8);
         if (var3.has("parents") && var3.get("parents").isJsonObject()) {
            for (Entry var5 : var3.getAsJsonObject("parents").entrySet()) {
               try {
                  var2.field7.put(UUID.fromString((String)var5.getKey()), UUID.fromString(((JsonElement)var5.getValue()).getAsString()));
               } catch (Exception var7) {
                  var7.printStackTrace();
               }
            }
         }
      } else {
         this.method3(var2, var3);
      }

      return var2;
   }

   private void method3(Highlight_4 var1, JsonObject var2) {
      for (Entry var4 : var2.entrySet()) {
         File var5 = this.field1.method32().toPath().resolve(((JsonElement)var4.getValue()).getAsString()).toFile();

         try {
            var1.field5.put(UUID.fromString((String)var4.getKey()), var5);
         } catch (Exception var7) {
            var7.printStackTrace();
         }
      }
   }

   private void method4(JsonObject var1, String var2, Map<UUID, String> var3) {
      if (var1.has(var2) && var1.get(var2).isJsonObject()) {
         for (Entry var5 : var1.getAsJsonObject(var2).entrySet()) {
            try {
               var3.put(UUID.fromString((String)var5.getKey()), ((JsonElement)var5.getValue()).getAsString());
            } catch (Exception var7) {
               var7.printStackTrace();
            }
         }
      }
   }

   @Generated
   public Highlight$Data(Rewind2_3 var1) {
      this.field1 = var1;
   }
}
