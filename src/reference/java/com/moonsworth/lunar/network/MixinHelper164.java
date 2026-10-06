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

public class GameRewindLayerEffect extends MixinHelper16 implements Serializable {
   private static final long field8 = 1L;
   public static final String SERIALIZED_NAME_TYPE = "type";
   @SerializedName("type")
   private GameRewindLayerEffect.Type type = GameRewindLayerEffect.Type.EFFECT;
   public static final String SERIALIZED_NAME_SUB_TYPE = "sub_type";
   @SerializedName("sub_type")
   private String subType;
   public static HashSet<String> field13 = new HashSet<>();
   public static HashSet<String> field14 = new HashSet<>();

   public GameRewindLayerEffect method1(GameRewindLayerEffect.Type var1) {
      this.type = var1;
      return this;
   }

   @Nonnull
   public GameRewindLayerEffect.Type method3() {
      return this.type;
   }

   public void method3(GameRewindLayerEffect.Type var1) {
      this.type = var1;
   }

   public GameRewindLayerEffect subType(String var1) {
      this.subType = var1;
      return this;
   }

   @Nonnull
   public String getSubType() {
      return this.subType;
   }

   public void method6(String var1) {
      this.subType = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         GameRewindLayerEffect var2 = (GameRewindLayerEffect)var1;
         return Objects.equals(this.type, var2.type) && Objects.equals(this.subType, var2.subType) && super.equals(var1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.type, this.subType, super.hashCode());
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("class GameRewindLayerEffect {\n");
      var1.append("    ").append(this.getProperties((Object)super.toString())).append("\n");
      var1.append("    type: ").append(this.getProperties(this.type)).append("\n");
      var1.append("    subType: ").append(this.getProperties((Object)this.subType)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String method6(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement var0) {
      if (var0 == null && !field14.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameRewindLayerEffect is not found in the empty JSON string", field14.toString())
         );
      }

      for (Entry var3 : var0.getAsJsonObject().entrySet()) {
         if (!field13.contains(var3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `GameRewindLayerEffect` properties. JSON: %s", var3.getKey(), var0.toString()
               )
            );
         }
      }

      for (String var6 : field14) {
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

      GameRewindLayerEffect.Type.validateJsonElement(var5.get("type"));
      if (!var5.get("sub_type").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `sub_type` to be a primitive type in the JSON string but got `%s`", var5.get("sub_type").toString())
         );
      }
   }

   public static GameRewindLayerEffect method7(String var0) {
      return (GameRewindLayerEffect)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, GameRewindLayerEffect.class);
   }

   @Override
   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field13.add("duration_ms");
      field13.add("properties");
      field13.add("type");
      field13.add("sub_type");
      field14.add("type");
      field14.add("sub_type");
      field14.add("duration_ms");
   }

   public static class Data implements TypeAdapterFactory {
      public <T> TypeAdapter<T> create(Gson var1, TypeToken<T> var2) {
         if (!GameRewindLayerEffect.class.isAssignableFrom(var2.getRawType())) {
            return null;
         }

         final TypeAdapter var3 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var4 = var1.getDelegateAdapter(this, TypeToken.get(GameRewindLayerEffect.class));
         return (new TypeAdapter<GameRewindLayerEffect>() {
            public void method1(JsonWriter var1, GameRewindLayerEffect var2x) {
               JsonObject var3x = var4.toJsonTree(var2x).getAsJsonObject();
               var3.write(var1, var3x);
            }

            public GameRewindLayerEffect method2(JsonReader var1) {
               JsonElement var2x = (JsonElement)var3.read(var1);
               return (GameRewindLayerEffect)var4.fromJsonTree(var2x);
            }
         }).nullSafe();
      }
   }

   @JsonAdapter(GameRewindLayerEffect.Type.Data.class)
   public enum Type {
      EFFECT("effect");

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

      public static GameRewindLayerEffect.Type fromValue(String var0) {
         for (GameRewindLayerEffect.Type var4 : values()) {
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

      public static class Data extends TypeAdapter<GameRewindLayerEffect.Type> {
         public void method1(JsonWriter var1, GameRewindLayerEffect.Type var2) {
            var1.value(var2.getValue());
         }

         public GameRewindLayerEffect.Type method2(JsonReader var1) {
            String var2 = var1.nextString();
            return GameRewindLayerEffect.Type.fromValue(var2);
         }
      }
   }
}
