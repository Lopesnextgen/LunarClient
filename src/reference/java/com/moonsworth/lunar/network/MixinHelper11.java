package com.moonsworth.lunar.network;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Map.Entry;
import javax.annotation.Nonnull;

public class MixinHelper11 implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_EVENTS = "events";
   @SerializedName("events")
   private List<MixinHelper62> events = new ArrayList<>();
   public static HashSet<String> field4 = new HashSet<>();
   public static HashSet<String> field5 = new HashSet<>();

   public MixinHelper11 method1(List<MixinHelper62> var1) {
      this.events = var1;
      return this;
   }

   public MixinHelper11 method2(MixinHelper62 var1) {
      if (this.events == null) {
         this.events = new ArrayList<>();
      }

      this.events.add(var1);
      return this;
   }

   @Nonnull
   public List<MixinHelper62> getEvents() {
      return this.events;
   }

   public void setEvents(List<MixinHelper62> var1) {
      this.events = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         MixinHelper11 var2 = (MixinHelper11)var1;
         return Objects.equals(this.events, var2.events);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.events);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("class GameEventBatchPostRequest {\n");
      var1.append("    events: ").append(this.toIndentedString(this.events)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String toIndentedString(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement var0) {
      if (var0 == null && !field5.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameEventBatchPostRequest is not found in the empty JSON string", field5.toString())
         );
      }

      for (Entry var3 : var0.getAsJsonObject().entrySet()) {
         if (!field4.contains(var3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `GameEventBatchPostRequest` properties. JSON: %s", var3.getKey(), var0.toString()
               )
            );
         }
      }

      for (String var7 : field5) {
         if (var0.getAsJsonObject().get(var7) == null) {
            throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", var7, var0.toString()));
         }
      }

      JsonObject var6 = var0.getAsJsonObject();
      if (!var6.get("events").isJsonArray()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `events` to be an array in the JSON string but got `%s`", var6.get("events").toString())
         );
      }

      JsonArray var8 = var6.getAsJsonArray("events");

      for (int var4 = 0; var4 < var8.size(); var4++) {
         MixinHelper62.validateJsonElement(var8.get(var4));
      }
   }

   public static MixinHelper11 method5(String var0) {
      return (MixinHelper11)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, MixinHelper11.class);
   }

   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field4.add("events");
      field5.add("events");
   }

   public static class Data implements TypeAdapterFactory {
      public <T> TypeAdapter<T> create(Gson var1, TypeToken<T> var2) {
         if (!MixinHelper11.class.isAssignableFrom(var2.getRawType())) {
            return null;
         }

         final TypeAdapter var3 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var4 = var1.getDelegateAdapter(this, TypeToken.get(MixinHelper11.class));
         return (new TypeAdapter<MixinHelper11>() {
            public void method1(JsonWriter var1, MixinHelper11 var2x) {
               JsonObject var3x = var4.toJsonTree(var2x).getAsJsonObject();
               var3.write(var1, var3x);
            }

            public MixinHelper11 method2(JsonReader var1) {
               JsonElement var2x = (JsonElement)var3.read(var1);
               return (MixinHelper11)var4.fromJsonTree(var2x);
            }
         }).nullSafe();
      }
   }
}
