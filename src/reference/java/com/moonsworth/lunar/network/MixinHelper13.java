package com.moonsworth.lunar.network;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Map.Entry;
import javax.annotation.Nullable;

public class MixinHelper13 implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_SERVER_MAPPINGS_ID = "server_mappings_id";
   @SerializedName("server_mappings_id")
   private String serverMappingsId;
   public static final String SERIALIZED_NAME_RICH_STATUS_SOURCE = "rich_status_source";
   @SerializedName("rich_status_source")
   private String richStatusSource;
   public static final String SERIALIZED_NAME_RICH_STATUS_GAME_NAME = "rich_status_game_name";
   @SerializedName("rich_status_game_name")
   private String richStatusGameName;
   public static final String SERIALIZED_NAME_RICH_STATUS_GAME_VARIANT_NAME = "rich_status_game_variant_name";
   @SerializedName("rich_status_game_variant_name")
   private String richStatusGameVariantName;
   public static final String SERIALIZED_NAME_RICH_STATUS_GAME_STATE = "rich_status_game_state";
   @SerializedName("rich_status_game_state")
   private String richStatusGameState;
   public static final String SERIALIZED_NAME_RICH_STATUS_PLAYER_STATE = "rich_status_player_state";
   @SerializedName("rich_status_player_state")
   private String richStatusPlayerState;
   public static final String SERIALIZED_NAME_RICH_STATUS_MAP_NAME = "rich_status_map_name";
   @SerializedName("rich_status_map_name")
   private String richStatusMapName;
   public static final String SERIALIZED_NAME_RICH_STATUS_SUB_SERVER = "rich_status_sub_server";
   @SerializedName("rich_status_sub_server")
   private String richStatusSubServer;
   public static HashSet<String> field18 = new HashSet<>();
   public static HashSet<String> field19 = new HashSet<>();

   public MixinHelper13 method1(String var1) {
      this.serverMappingsId = var1;
      return this;
   }

   @Nullable
   public String getServerMappingsId() {
      return this.serverMappingsId;
   }

   public void method2(String var1) {
      this.serverMappingsId = var1;
   }

   public MixinHelper13 richStatusSource(String var1) {
      this.richStatusSource = var1;
      return this;
   }

   @Nullable
   public String getRichStatusSource() {
      return this.richStatusSource;
   }

   public void setRichStatusSource(String var1) {
      this.richStatusSource = var1;
   }

   public MixinHelper13 richStatusGameName(String var1) {
      this.richStatusGameName = var1;
      return this;
   }

   @Nullable
   public String getRichStatusGameName() {
      return this.richStatusGameName;
   }

   public void setRichStatusGameName(String var1) {
      this.richStatusGameName = var1;
   }

   public MixinHelper13 richStatusGameVariantName(String var1) {
      this.richStatusGameVariantName = var1;
      return this;
   }

   @Nullable
   public String getRichStatusGameVariantName() {
      return this.richStatusGameVariantName;
   }

   public void setRichStatusGameVariantName(String var1) {
      this.richStatusGameVariantName = var1;
   }

   public MixinHelper13 richStatusGameState(String var1) {
      this.richStatusGameState = var1;
      return this;
   }

   @Nullable
   public String getRichStatusGameState() {
      return this.richStatusGameState;
   }

   public void setRichStatusGameState(String var1) {
      this.richStatusGameState = var1;
   }

   public MixinHelper13 richStatusPlayerState(String var1) {
      this.richStatusPlayerState = var1;
      return this;
   }

   @Nullable
   public String getRichStatusPlayerState() {
      return this.richStatusPlayerState;
   }

   public void setRichStatusPlayerState(String var1) {
      this.richStatusPlayerState = var1;
   }

   public MixinHelper13 richStatusMapName(String var1) {
      this.richStatusMapName = var1;
      return this;
   }

   @Nullable
   public String getRichStatusMapName() {
      return this.richStatusMapName;
   }

   public void setRichStatusMapName(String var1) {
      this.richStatusMapName = var1;
   }

   public MixinHelper13 richStatusSubServer(String var1) {
      this.richStatusSubServer = var1;
      return this;
   }

   @Nullable
   public String getRichStatusSubServer() {
      return this.richStatusSubServer;
   }

   public void setRichStatusSubServer(String var1) {
      this.richStatusSubServer = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         MixinHelper13 var2 = (MixinHelper13)var1;
         return Objects.equals(this.serverMappingsId, var2.serverMappingsId)
            && Objects.equals(this.richStatusSource, var2.richStatusSource)
            && Objects.equals(this.richStatusGameName, var2.richStatusGameName)
            && Objects.equals(this.richStatusGameVariantName, var2.richStatusGameVariantName)
            && Objects.equals(this.richStatusGameState, var2.richStatusGameState)
            && Objects.equals(this.richStatusPlayerState, var2.richStatusPlayerState)
            && Objects.equals(this.richStatusMapName, var2.richStatusMapName)
            && Objects.equals(this.richStatusSubServer, var2.richStatusSubServer);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.serverMappingsId, this.richStatusSource, this.richStatusGameName, this.richStatusGameVariantName, this.richStatusGameState, this.richStatusPlayerState, this.richStatusMapName, this.richStatusSubServer);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("class BaseGameEventLocationPublicServer {\n");
      var1.append("    serverMappingsId: ").append(this.toIndentedString(this.serverMappingsId)).append("\n");
      var1.append("    richStatusSource: ").append(this.toIndentedString(this.richStatusSource)).append("\n");
      var1.append("    richStatusGameName: ").append(this.toIndentedString(this.richStatusGameName)).append("\n");
      var1.append("    richStatusGameVariantName: ").append(this.toIndentedString(this.richStatusGameVariantName)).append("\n");
      var1.append("    richStatusGameState: ").append(this.toIndentedString(this.richStatusGameState)).append("\n");
      var1.append("    richStatusPlayerState: ").append(this.toIndentedString(this.richStatusPlayerState)).append("\n");
      var1.append("    richStatusMapName: ").append(this.toIndentedString(this.richStatusMapName)).append("\n");
      var1.append("    richStatusSubServer: ").append(this.toIndentedString(this.richStatusSubServer)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String toIndentedString(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement var0) {
      if (var0 == null && !field19.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in BaseGameEventLocationPublicServer is not found in the empty JSON string", field19.toString())
         );
      }

      for (Entry var3 : var0.getAsJsonObject().entrySet()) {
         if (!field18.contains(var3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `BaseGameEventLocationPublicServer` properties. JSON: %s",
                  var3.getKey(),
                  var0.toString()
               )
            );
         }
      }

      JsonObject var4 = var0.getAsJsonObject();
      if (var4.get("server_mappings_id") != null && !var4.get("server_mappings_id").isJsonNull() && !var4.get("server_mappings_id").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `server_mappings_id` to be a primitive type in the JSON string but got `%s`", var4.get("server_mappings_id").toString()
            )
         );
      }

      if (var4.get("rich_status_source") != null && !var4.get("rich_status_source").isJsonNull() && !var4.get("rich_status_source").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `rich_status_source` to be a primitive type in the JSON string but got `%s`", var4.get("rich_status_source").toString()
            )
         );
      }

      if (var4.get("rich_status_game_name") != null && !var4.get("rich_status_game_name").isJsonNull() && !var4.get("rich_status_game_name").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `rich_status_game_name` to be a primitive type in the JSON string but got `%s`",
               var4.get("rich_status_game_name").toString()
            )
         );
      }

      if (var4.get("rich_status_game_variant_name") != null
         && !var4.get("rich_status_game_variant_name").isJsonNull()
         && !var4.get("rich_status_game_variant_name").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `rich_status_game_variant_name` to be a primitive type in the JSON string but got `%s`",
               var4.get("rich_status_game_variant_name").toString()
            )
         );
      }

      if (var4.get("rich_status_game_state") != null
         && !var4.get("rich_status_game_state").isJsonNull()
         && !var4.get("rich_status_game_state").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `rich_status_game_state` to be a primitive type in the JSON string but got `%s`",
               var4.get("rich_status_game_state").toString()
            )
         );
      }

      if (var4.get("rich_status_player_state") != null
         && !var4.get("rich_status_player_state").isJsonNull()
         && !var4.get("rich_status_player_state").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `rich_status_player_state` to be a primitive type in the JSON string but got `%s`",
               var4.get("rich_status_player_state").toString()
            )
         );
      }

      if (var4.get("rich_status_map_name") != null && !var4.get("rich_status_map_name").isJsonNull() && !var4.get("rich_status_map_name").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `rich_status_map_name` to be a primitive type in the JSON string but got `%s`", var4.get("rich_status_map_name").toString()
            )
         );
      }

      if (var4.get("rich_status_sub_server") != null
         && !var4.get("rich_status_sub_server").isJsonNull()
         && !var4.get("rich_status_sub_server").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `rich_status_sub_server` to be a primitive type in the JSON string but got `%s`",
               var4.get("rich_status_sub_server").toString()
            )
         );
      }
   }

   public static MixinHelper13 method25(String var0) {
      return (MixinHelper13)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, MixinHelper13.class);
   }

   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field18.add("server_mappings_id");
      field18.add("rich_status_source");
      field18.add("rich_status_game_name");
      field18.add("rich_status_game_variant_name");
      field18.add("rich_status_game_state");
      field18.add("rich_status_player_state");
      field18.add("rich_status_map_name");
      field18.add("rich_status_sub_server");
   }

   public static class Data implements TypeAdapterFactory {
      public <T> TypeAdapter<T> create(Gson var1, TypeToken<T> var2) {
         if (!MixinHelper13.class.isAssignableFrom(var2.getRawType())) {
            return null;
         }

         final TypeAdapter var3 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var4 = var1.getDelegateAdapter(this, TypeToken.get(MixinHelper13.class));
         return (new TypeAdapter<MixinHelper13>() {
            public void method1(JsonWriter var1, MixinHelper13 var2x) {
               JsonObject var3x = var4.toJsonTree(var2x).getAsJsonObject();
               var3.write(var1, var3x);
            }

            public MixinHelper13 method2(JsonReader var1) {
               JsonElement var2x = (JsonElement)var3.read(var1);
               MixinHelper13.validateJsonElement(var2x);
               return (MixinHelper13)var4.fromJsonTree(var2x);
            }
         }).nullSafe();
      }
   }
}
