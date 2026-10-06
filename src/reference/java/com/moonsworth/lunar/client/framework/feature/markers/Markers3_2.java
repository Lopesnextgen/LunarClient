package com.moonsworth.lunar.client.framework.feature.markers;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

public class Markers3_2 {
   @SerializedName("value")
   @NotNull
   private final String field1;
   @SerializedName("state")
   @NotNull
   private final Markers2_3 field2;
   public static final JsonSerializer<Markers3_2> field3 = (var0, var1, var2) -> {
      JsonObject var3 = new JsonObject();
      var3.addProperty("value", var0.field1);
      if (var0.field2 != Markers2_3.field2 && var0.field2.isEmpty()) {
         var3.addProperty("state", var0.field2.value());
      }

      return var3;
   };
   public static final JsonDeserializer<Markers3_2> field4 = (var0, var1, var2) -> {
      JsonObject var3 = var0.getAsJsonObject();
      return new Markers3_2(var3.get("value").getAsString(), var3.has("state") ? Markers2_3.method3(var3.get("state").getAsString()) : Markers2_3.method2());
   };

   public Markers3_2(@NotNull String var1, @NotNull Markers2_3 var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @SerializedName("value")
   @NotNull
   public String value() {
      return this.field1;
   }

   @SerializedName("state")
   @NotNull
   public Markers2_3 method1() {
      return this.field2;
   }
}
