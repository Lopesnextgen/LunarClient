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
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Map.Entry;
import javax.annotation.Nonnull;

public class GameRewindExportAudio implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_CHANNELS = "channels";
   @SerializedName("channels")
   private GameRewindExportAudio.Type channels;
   public static final String SERIALIZED_NAME_FREQUENCY = "frequency";
   @SerializedName("frequency")
   private BigDecimal frequency;
   public static HashSet<String> field6 = new HashSet<>();
   public static HashSet<String> field7 = new HashSet<>();

   public GameRewindExportAudio method1(GameRewindExportAudio.Type var1) {
      this.channels = var1;
      return this;
   }

   @Nonnull
   public GameRewindExportAudio.Type method2() {
      return this.channels;
   }

   public void setChannels(GameRewindExportAudio.Type var1) {
      this.channels = var1;
   }

   public GameRewindExportAudio frequency(BigDecimal var1) {
      this.frequency = var1;
      return this;
   }

   @Nonnull
   public BigDecimal getFrequency() {
      return this.frequency;
   }

   public void setFrequency(BigDecimal var1) {
      this.frequency = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         GameRewindExportAudio var2 = (GameRewindExportAudio)var1;
         return Objects.equals(this.channels, var2.channels) && Objects.equals(this.frequency, var2.frequency);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.channels, this.frequency);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("class GameRewindExportAudio {\n");
      var1.append("    channels: ").append(this.toIndentedString(this.channels)).append("\n");
      var1.append("    frequency: ").append(this.toIndentedString(this.frequency)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String toIndentedString(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement var0) {
      if (var0 == null && !field7.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameRewindExportAudio is not found in the empty JSON string", field7.toString())
         );
      }

      for (Entry var3 : var0.getAsJsonObject().entrySet()) {
         if (!field6.contains(var3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `GameRewindExportAudio` properties. JSON: %s", var3.getKey(), var0.toString()
               )
            );
         }
      }

      for (String var6 : field7) {
         if (var0.getAsJsonObject().get(var6) == null) {
            throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", var6, var0.toString()));
         }
      }

      JsonObject var5 = var0.getAsJsonObject();
      if (!var5.get("channels").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `channels` to be a primitive type in the JSON string but got `%s`", var5.get("channels").toString())
         );
      }

      GameRewindExportAudio.Type.validateJsonElement(var5.get("channels"));
   }

   public static GameRewindExportAudio method8(String var0) {
      return (GameRewindExportAudio)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, GameRewindExportAudio.class);
   }

   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field6.add("channels");
      field6.add("frequency");
      field7.add("channels");
      field7.add("frequency");
   }

   public static class Data implements TypeAdapterFactory {
      public <T> TypeAdapter<T> create(Gson var1, TypeToken<T> var2) {
         if (!GameRewindExportAudio.class.isAssignableFrom(var2.getRawType())) {
            return null;
         }

         final TypeAdapter var3 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var4 = var1.getDelegateAdapter(this, TypeToken.get(GameRewindExportAudio.class));
         return (new TypeAdapter<GameRewindExportAudio>() {
            public void method1(JsonWriter var1, GameRewindExportAudio var2x) {
               JsonObject var3x = var4.toJsonTree(var2x).getAsJsonObject();
               var3.write(var1, var3x);
            }

            public GameRewindExportAudio method2(JsonReader var1) {
               JsonElement var2x = (JsonElement)var3.read(var1);
               return (GameRewindExportAudio)var4.fromJsonTree(var2x);
            }
         }).nullSafe();
      }
   }

   @JsonAdapter(GameRewindExportAudio.Type.Data.class)
   public enum Type {
      MONO("mono"),
      STEREO("stereo");

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

      public static GameRewindExportAudio.Type fromValue(String var0) {
         for (GameRewindExportAudio.Type var4 : values()) {
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

      public static class Data extends TypeAdapter<GameRewindExportAudio.Type> {
         public void method1(JsonWriter var1, GameRewindExportAudio.Type var2) {
            var1.value(var2.getValue());
         }

         public GameRewindExportAudio.Type method2(JsonReader var1) {
            String var2 = var1.nextString();
            return GameRewindExportAudio.Type.fromValue(var2);
         }
      }
   }
}
