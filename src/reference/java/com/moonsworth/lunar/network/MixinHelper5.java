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
import javax.annotation.Nullable;

public class GameRewindRecordingLocation implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_TYPE = "type";
   @SerializedName("type")
   private GameRewindRecordingLocation.Type type;
   public static final String SERIALIZED_NAME_NAME = "name";
   @SerializedName("name")
   private String name;
   public static final String SERIALIZED_NAME_IP = "ip";
   @SerializedName("ip")
   private String ip;
   public static final String SERIALIZED_NAME_SERVER_MAPPINGS_ID = "server_mappings_id";
   @SerializedName("server_mappings_id")
   private String serverMappingsId;
   public static final String SERIALIZED_NAME_LIVE_EXPERIENCE_ID = "live_experience_id";
   @SerializedName("live_experience_id")
   private String liveExperienceId;
   public static HashSet<String> field10 = new HashSet<>();
   public static HashSet<String> field11 = new HashSet<>();

   public GameRewindRecordingLocation method1(GameRewindRecordingLocation.Type var1) {
      this.type = var1;
      return this;
   }

   @Nonnull
   public GameRewindRecordingLocation.Type method2() {
      return this.type;
   }

   public void setType(GameRewindRecordingLocation.Type var1) {
      this.type = var1;
   }

   public GameRewindRecordingLocation name(String var1) {
      this.name = var1;
      return this;
   }

   @Nullable
   public String getName() {
      return this.name;
   }

   public void setName(String var1) {
      this.name = var1;
   }

   public GameRewindRecordingLocation ip(String var1) {
      this.ip = var1;
      return this;
   }

   @Nullable
   public String getIp() {
      return this.ip;
   }

   public void setIp(String var1) {
      this.ip = var1;
   }

   public GameRewindRecordingLocation serverMappingsId(String var1) {
      this.serverMappingsId = var1;
      return this;
   }

   @Nullable
   public String getServerMappingsId() {
      return this.serverMappingsId;
   }

   public void setServerMappingsId(String var1) {
      this.serverMappingsId = var1;
   }

   public GameRewindRecordingLocation liveExperienceId(String var1) {
      this.liveExperienceId = var1;
      return this;
   }

   @Nullable
   public String getLiveExperienceId() {
      return this.liveExperienceId;
   }

   public void setLiveExperienceId(String var1) {
      this.liveExperienceId = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         GameRewindRecordingLocation var2 = (GameRewindRecordingLocation)var1;
         return Objects.equals(this.type, var2.type)
            && Objects.equals(this.name, var2.name)
            && Objects.equals(this.ip, var2.ip)
            && Objects.equals(this.serverMappingsId, var2.serverMappingsId)
            && Objects.equals(this.liveExperienceId, var2.liveExperienceId);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.type, this.name, this.ip, this.serverMappingsId, this.liveExperienceId);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("class GameRewindRecordingLocation {\n");
      var1.append("    type: ").append(this.toIndentedString(this.type)).append("\n");
      var1.append("    name: ").append(this.toIndentedString(this.name)).append("\n");
      var1.append("    ip: ").append(this.toIndentedString(this.ip)).append("\n");
      var1.append("    serverMappingsId: ").append(this.toIndentedString(this.serverMappingsId)).append("\n");
      var1.append("    liveExperienceId: ").append(this.toIndentedString(this.liveExperienceId)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String toIndentedString(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement var0) {
      if (var0 == null && !field11.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameRewindRecordingLocation is not found in the empty JSON string", field11.toString())
         );
      }

      for (Entry var3 : var0.getAsJsonObject().entrySet()) {
         if (!field10.contains(var3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `GameRewindRecordingLocation` properties. JSON: %s", var3.getKey(), var0.toString()
               )
            );
         }
      }

      for (String var6 : field11) {
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

      GameRewindRecordingLocation.Type.validateJsonElement(var5.get("type"));
      if (var5.get("name") != null && !var5.get("name").isJsonNull() && !var5.get("name").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `name` to be a primitive type in the JSON string but got `%s`", var5.get("name").toString())
         );
      }

      if (var5.get("ip") != null && !var5.get("ip").isJsonNull() && !var5.get("ip").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `ip` to be a primitive type in the JSON string but got `%s`", var5.get("ip").toString())
         );
      }

      if (var5.get("server_mappings_id") != null && !var5.get("server_mappings_id").isJsonNull() && !var5.get("server_mappings_id").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `server_mappings_id` to be a primitive type in the JSON string but got `%s`", var5.get("server_mappings_id").toString()
            )
         );
      }

      if (var5.get("live_experience_id") != null && !var5.get("live_experience_id").isJsonNull() && !var5.get("live_experience_id").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `live_experience_id` to be a primitive type in the JSON string but got `%s`", var5.get("live_experience_id").toString()
            )
         );
      }
   }

   public static GameRewindRecordingLocation method14(String var0) {
      return (GameRewindRecordingLocation)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, GameRewindRecordingLocation.class);
   }

   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field10.add("type");
      field10.add("name");
      field10.add("ip");
      field10.add("server_mappings_id");
      field10.add("live_experience_id");
      field11.add("type");
   }

   public static class Data implements TypeAdapterFactory {
      public <T> TypeAdapter<T> create(Gson var1, TypeToken<T> var2) {
         if (!GameRewindRecordingLocation.class.isAssignableFrom(var2.getRawType())) {
            return null;
         }

         final TypeAdapter var3 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var4 = var1.getDelegateAdapter(this, TypeToken.get(GameRewindRecordingLocation.class));
         return (new TypeAdapter<GameRewindRecordingLocation>() {
            public void method1(JsonWriter var1, GameRewindRecordingLocation var2x) {
               JsonObject var3x = var4.toJsonTree(var2x).getAsJsonObject();
               var3.write(var1, var3x);
            }

            public GameRewindRecordingLocation method2(JsonReader var1) {
               JsonElement var2x = (JsonElement)var3.read(var1);
               return (GameRewindRecordingLocation)var4.fromJsonTree(var2x);
            }
         }).nullSafe();
      }
   }

   @JsonAdapter(GameRewindRecordingLocation.Type.Data.class)
   public enum Type {
      LIVE_EXPERIENCE("LIVE_EXPERIENCE"),
      HOSTED_WORLD("HOSTED_WORLD"),
      SINGLE_PLAYER("SINGLE_PLAYER"),
      SERVER("SERVER");

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

      public static GameRewindRecordingLocation.Type fromValue(String var0) {
         for (GameRewindRecordingLocation.Type var4 : values()) {
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

      public static class Data extends TypeAdapter<GameRewindRecordingLocation.Type> {
         public void method1(JsonWriter var1, GameRewindRecordingLocation.Type var2) {
            var1.value(var2.getValue());
         }

         public GameRewindRecordingLocation.Type method2(JsonReader var1) {
            String var2 = var1.nextString();
            return GameRewindRecordingLocation.Type.fromValue(var2);
         }
      }
   }
}
