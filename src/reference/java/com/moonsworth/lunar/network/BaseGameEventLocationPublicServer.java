package com.moonsworth.lunar.network;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.network.mixin.MixinHelper7;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Map.Entry;
import javax.annotation.Nullable;

public class BaseGameEventLocationPublicServer implements Serializable {
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

   public BaseGameEventLocationPublicServer() {
   }

   public BaseGameEventLocationPublicServer serverMappingsId(String text1) {
      this.serverMappingsId = text1;
      return this;
   }

   @Nullable
   public String getServerMappingsId() {
      return this.serverMappingsId;
   }

   public void setServerMappingsId(String text1) {
      this.serverMappingsId = text1;
   }

   public BaseGameEventLocationPublicServer richStatusSource(String text1) {
      this.richStatusSource = text1;
      return this;
   }

   @Nullable
   public String getRichStatusSource() {
      return this.richStatusSource;
   }

   public void setRichStatusSource(String text1) {
      this.richStatusSource = text1;
   }

   public BaseGameEventLocationPublicServer richStatusGameName(String text1) {
      this.richStatusGameName = text1;
      return this;
   }

   @Nullable
   public String getRichStatusGameName() {
      return this.richStatusGameName;
   }

   public void setRichStatusGameName(String text1) {
      this.richStatusGameName = text1;
   }

   public BaseGameEventLocationPublicServer richStatusGameVariantName(String text1) {
      this.richStatusGameVariantName = text1;
      return this;
   }

   @Nullable
   public String getRichStatusGameVariantName() {
      return this.richStatusGameVariantName;
   }

   public void setRichStatusGameVariantName(String text1) {
      this.richStatusGameVariantName = text1;
   }

   public BaseGameEventLocationPublicServer richStatusGameState(String text1) {
      this.richStatusGameState = text1;
      return this;
   }

   @Nullable
   public String getRichStatusGameState() {
      return this.richStatusGameState;
   }

   public void setRichStatusGameState(String text1) {
      this.richStatusGameState = text1;
   }

   public BaseGameEventLocationPublicServer richStatusPlayerState(String text1) {
      this.richStatusPlayerState = text1;
      return this;
   }

   @Nullable
   public String getRichStatusPlayerState() {
      return this.richStatusPlayerState;
   }

   public void setRichStatusPlayerState(String text1) {
      this.richStatusPlayerState = text1;
   }

   public BaseGameEventLocationPublicServer richStatusMapName(String text1) {
      this.richStatusMapName = text1;
      return this;
   }

   @Nullable
   public String getRichStatusMapName() {
      return this.richStatusMapName;
   }

   public void setRichStatusMapName(String text1) {
      this.richStatusMapName = text1;
   }

   public BaseGameEventLocationPublicServer richStatusSubServer(String text1) {
      this.richStatusSubServer = text1;
      return this;
   }

   @Nullable
   public String getRichStatusSubServer() {
      return this.richStatusSubServer;
   }

   public void setRichStatusSubServer(String text1) {
      this.richStatusSubServer = text1;
   }

   @Override
   public boolean equals(Object obj1) {
      if (this == obj1) {
         return true;
      } else if (obj1 != null && this.getClass() == obj1.getClass()) {
         BaseGameEventLocationPublicServer mixinhelper132 = (BaseGameEventLocationPublicServer)obj1;
         return Objects.equals(this.serverMappingsId, mixinhelper132.serverMappingsId)
            && Objects.equals(this.richStatusSource, mixinhelper132.richStatusSource)
            && Objects.equals(this.richStatusGameName, mixinhelper132.richStatusGameName)
            && Objects.equals(this.richStatusGameVariantName, mixinhelper132.richStatusGameVariantName)
            && Objects.equals(this.richStatusGameState, mixinhelper132.richStatusGameState)
            && Objects.equals(this.richStatusPlayerState, mixinhelper132.richStatusPlayerState)
            && Objects.equals(this.richStatusMapName, mixinhelper132.richStatusMapName)
            && Objects.equals(this.richStatusSubServer, mixinhelper132.richStatusSubServer);
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
      StringBuilder builder1 = new StringBuilder();
      builder1.append("class BaseGameEventLocationPublicServer {\n");
      builder1.append("    serverMappingsId: ").append(this.toIndentedString(this.serverMappingsId)).append("\n");
      builder1.append("    richStatusSource: ").append(this.toIndentedString(this.richStatusSource)).append("\n");
      builder1.append("    richStatusGameName: ").append(this.toIndentedString(this.richStatusGameName)).append("\n");
      builder1.append("    richStatusGameVariantName: ").append(this.toIndentedString(this.richStatusGameVariantName)).append("\n");
      builder1.append("    richStatusGameState: ").append(this.toIndentedString(this.richStatusGameState)).append("\n");
      builder1.append("    richStatusPlayerState: ").append(this.toIndentedString(this.richStatusPlayerState)).append("\n");
      builder1.append("    richStatusMapName: ").append(this.toIndentedString(this.richStatusMapName)).append("\n");
      builder1.append("    richStatusSubServer: ").append(this.toIndentedString(this.richStatusSubServer)).append("\n");
      builder1.append("}");
      return builder1.toString();
   }

   private String toIndentedString(Object obj1) {
      return obj1 == null ? "null" : obj1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement element0) {
      if (element0 == null && !field19.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in BaseGameEventLocationPublicServer is not found in the empty JSON string", field19.toString())
         );
      }

      for (Entry entry3 : element0.getAsJsonObject().entrySet()) {
         if (!field18.contains(entry3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `BaseGameEventLocationPublicServer` properties. JSON: %s",
                  entry3.getKey(),
                  element0.toString()
               )
            );
         }
      }

      JsonObject json4 = element0.getAsJsonObject();
      if (json4.get("server_mappings_id") != null && !json4.get("server_mappings_id").isJsonNull() && !json4.get("server_mappings_id").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `server_mappings_id` to be a primitive type in the JSON string but got `%s`", json4.get("server_mappings_id").toString()
            )
         );
      }

      if (json4.get("rich_status_source") != null && !json4.get("rich_status_source").isJsonNull() && !json4.get("rich_status_source").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `rich_status_source` to be a primitive type in the JSON string but got `%s`", json4.get("rich_status_source").toString()
            )
         );
      }

      if (json4.get("rich_status_game_name") != null && !json4.get("rich_status_game_name").isJsonNull() && !json4.get("rich_status_game_name").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `rich_status_game_name` to be a primitive type in the JSON string but got `%s`",
               json4.get("rich_status_game_name").toString()
            )
         );
      }

      if (json4.get("rich_status_game_variant_name") != null
         && !json4.get("rich_status_game_variant_name").isJsonNull()
         && !json4.get("rich_status_game_variant_name").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `rich_status_game_variant_name` to be a primitive type in the JSON string but got `%s`",
               json4.get("rich_status_game_variant_name").toString()
            )
         );
      }

      if (json4.get("rich_status_game_state") != null
         && !json4.get("rich_status_game_state").isJsonNull()
         && !json4.get("rich_status_game_state").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `rich_status_game_state` to be a primitive type in the JSON string but got `%s`",
               json4.get("rich_status_game_state").toString()
            )
         );
      }

      if (json4.get("rich_status_player_state") != null
         && !json4.get("rich_status_player_state").isJsonNull()
         && !json4.get("rich_status_player_state").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `rich_status_player_state` to be a primitive type in the JSON string but got `%s`",
               json4.get("rich_status_player_state").toString()
            )
         );
      }

      if (json4.get("rich_status_map_name") != null && !json4.get("rich_status_map_name").isJsonNull() && !json4.get("rich_status_map_name").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `rich_status_map_name` to be a primitive type in the JSON string but got `%s`", json4.get("rich_status_map_name").toString()
            )
         );
      }

      if (json4.get("rich_status_sub_server") != null
         && !json4.get("rich_status_sub_server").isJsonNull()
         && !json4.get("rich_status_sub_server").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `rich_status_sub_server` to be a primitive type in the JSON string but got `%s`",
               json4.get("rich_status_sub_server").toString()
            )
         );
      }
   }

   public static BaseGameEventLocationPublicServer method25(String text) {
      return (BaseGameEventLocationPublicServer)MixinHelper7.getGson().fromJson(text, BaseGameEventLocationPublicServer.class);
   }

   public String toJson() {
      return MixinHelper7.getGson().toJson(this);
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
}
