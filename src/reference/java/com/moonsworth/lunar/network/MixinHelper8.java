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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Map.Entry;
import javax.annotation.Nonnull;

public class MixinHelper8 implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_DURATION_MS = "duration_ms";
   @SerializedName("duration_ms")
   private BigDecimal durationMs;
   public static final String SERIALIZED_NAME_LAYERS = "layers";
   @SerializedName("layers")
   private List<MixinHelper63> layers = new ArrayList<>();
   public static HashSet<String> field5 = new HashSet<>();
   public static HashSet<String> field6 = new HashSet<>();

   public MixinHelper8 method1(BigDecimal var1) {
      this.durationMs = var1;
      return this;
   }

   @Nonnull
   public BigDecimal method2() {
      return this.durationMs;
   }

   public void setDurationMs(BigDecimal var1) {
      this.durationMs = var1;
   }

   public MixinHelper8 layers(List<MixinHelper63> var1) {
      this.layers = var1;
      return this;
   }

   public MixinHelper8 addLayersItem(MixinHelper63 var1) {
      if (this.layers == null) {
         this.layers = new ArrayList<>();
      }

      this.layers.add(var1);
      return this;
   }

   @Nonnull
   public List<MixinHelper63> getLayers() {
      return this.layers;
   }

   public void setLayers(List<MixinHelper63> var1) {
      this.layers = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         MixinHelper8 var2 = (MixinHelper8)var1;
         return Objects.equals(this.durationMs, var2.durationMs) && Objects.equals(this.layers, var2.layers);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.durationMs, this.layers);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("class GameRewindEditorTimeline {\n");
      var1.append("    durationMs: ").append(this.toIndentedString(this.durationMs)).append("\n");
      var1.append("    layers: ").append(this.toIndentedString(this.layers)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String toIndentedString(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement var0) {
      if (var0 == null && !field6.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameRewindEditorTimeline is not found in the empty JSON string", field6.toString())
         );
      }

      for (Entry var3 : var0.getAsJsonObject().entrySet()) {
         if (!field5.contains(var3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `GameRewindEditorTimeline` properties. JSON: %s", var3.getKey(), var0.toString()
               )
            );
         }
      }

      for (String var7 : field6) {
         if (var0.getAsJsonObject().get(var7) == null) {
            throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", var7, var0.toString()));
         }
      }

      JsonObject var6 = var0.getAsJsonObject();
      if (!var6.get("layers").isJsonArray()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `layers` to be an array in the JSON string but got `%s`", var6.get("layers").toString())
         );
      }

      JsonArray var8 = var6.getAsJsonArray("layers");

      for (int var4 = 0; var4 < var8.size(); var4++) {
         MixinHelper63.validateJsonElement(var8.get(var4));
      }
   }

   public static MixinHelper8 method8(String var0) {
      return (MixinHelper8)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, MixinHelper8.class);
   }

   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field5.add("duration_ms");
      field5.add("layers");
      field6.add("duration_ms");
      field6.add("layers");
   }

   public static class Data implements TypeAdapterFactory {
      public <T> TypeAdapter<T> create(Gson var1, TypeToken<T> var2) {
         if (!MixinHelper8.class.isAssignableFrom(var2.getRawType())) {
            return null;
         }

         final TypeAdapter var3 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var4 = var1.getDelegateAdapter(this, TypeToken.get(MixinHelper8.class));
         return (new TypeAdapter<MixinHelper8>() {
            public void method1(JsonWriter var1, MixinHelper8 var2x) {
               JsonObject var3x = var4.toJsonTree(var2x).getAsJsonObject();
               var3.write(var1, var3x);
            }

            public MixinHelper8 method2(JsonReader var1) {
               JsonElement var2x = (JsonElement)var3.read(var1);
               return (MixinHelper8)var4.fromJsonTree(var2x);
            }
         }).nullSafe();
      }
   }
}
