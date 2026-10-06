package com.moonsworth.lunar.network;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Map.Entry;
import javax.annotation.Nonnull;

public class GameRewindLayerGameplay extends MixinHelper16 implements Serializable {
   private static final long field8 = 1L;
   public static final String SERIALIZED_NAME_TYPE = "type";
   @SerializedName("type")
   private GameRewindLayerGameplay.Type type = GameRewindLayerGameplay.Type.GAMEPLAY;
   public static HashSet<String> field11 = new HashSet<>();
   public static HashSet<String> field12 = new HashSet<>();

   public GameRewindLayerGameplay method1(GameRewindLayerGameplay.Type var1) {
      this.type = var1;
      return this;
   }

   @Nonnull
   public GameRewindLayerGameplay.Type method3() {
      return this.type;
   }

   public void method3(GameRewindLayerGameplay.Type var1) {
      this.type = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         GameRewindLayerGameplay var2 = (GameRewindLayerGameplay)var1;
         return Objects.equals(this.type, var2.type) && super.equals(var1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.type, super.hashCode());
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("class GameRewindLayerGameplay {\n");
      var1.append("    ").append(this.toIndentedString(super.toString())).append("\n");
      var1.append("    type: ").append(this.toIndentedString(this.type)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String toIndentedString(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement var0) {
      if (var0 == null && !field12.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameRewindLayerGameplay is not found in the empty JSON string", field12.toString())
         );
      }

      for (Entry var3 : var0.getAsJsonObject().entrySet()) {
         if (!field11.contains(var3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `GameRewindLayerGameplay` properties. JSON: %s", var3.getKey(), var0.toString()
               )
            );
         }
      }

      for (String var6 : field12) {
         if (var0.getAsJsonObject().get(var6) == null) {
            throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", var6, var0.toString()));
         }
      }

      JsonObject var5 = var0.getAsJsonObject();
      if (!var5.get("type").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `type` to be a primitive type in the JSON string but got `%s`", var5.get("type").toString())
         );
      }

      GameRewindLayerGameplay.Type.validateJsonElement(var5.get("type"));
   }

   public static GameRewindLayerGameplay method6(String var0) {
      return (GameRewindLayerGameplay)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, GameRewindLayerGameplay.class);
   }

   @Override
   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field11.add("duration_ms");
      field11.add("properties");
      field11.add("type");
      field12.add("type");
      field12.add("duration_ms");
   }

   public static class Data implements TypeAdapterFactory {
      public <T> TypeAdapter<T> create(Gson var1, TypeToken<T> var2) {
         if (!GameRewindLayerGameplay.class.isAssignableFrom(var2.getRawType())) {
            return null;
         }

         final TypeAdapter var3 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var4 = var1.getDelegateAdapter(this, TypeToken.get(GameRewindLayerGameplay.class));
         return (new TypeAdapter<GameRewindLayerGameplay>() {
            public void method1(JsonWriter var1, GameRewindLayerGameplay var2x) {
               JsonObject var3x = var4.toJsonTree(var2x).getAsJsonObject();
               var3.write(var1, var3x);
            }

            public GameRewindLayerGameplay method2(JsonReader var1) {
               JsonElement var2x = (JsonElement)var3.read(var1);
               return (GameRewindLayerGameplay)var4.fromJsonTree(var2x);
            }
         }).nullSafe();
      }
   }

   @JsonAdapter(GameRewindLayerGameplay.Type.Data.class)
   public enum Type {
      GAMEPLAY("gameplay");

      private String value;

      Type(String var3) {
         this.value = var3;
      }

      public String getValue() {
         return this.value;
      }

      @Override
      public String toString() {
         return String.valueOf(this.value);
      }

      public static GameRewindLayerGameplay.Type fromValue(String var0) {
         for (GameRewindLayerGameplay.Type var4 : values()) {
            if (var4.value.equals(var0)) {
               return var4;
            }
         }

         throw new IllegalArgumentException("Unexpected value '" + var0 + "'");
      }

      public static void validateJsonElement(JsonElement var0) {
         String var1 = var0.getAsString();
         fromValue(var1);
      }

      public static class Data extends TypeAdapter<GameRewindLayerGameplay.Type> {
         public void method1(JsonWriter var1, GameRewindLayerGameplay.Type var2) {
            var1.value(var2.getValue());
         }

         public GameRewindLayerGameplay.Type method2(JsonReader var1) {
            String var2 = var1.nextString();
            return GameRewindLayerGameplay.Type.fromValue(var2);
         }
      }
   }
}
