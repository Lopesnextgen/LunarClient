package com.moonsworth.lunar.network;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.network.mixin.MixinHelper7;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Map.Entry;
import javax.annotation.Nonnull;

public class GameRewindLayerAddEventData extends BaseGameEvent implements Serializable {
   private static final long field56 = 1L;
   public static final String SERIALIZED_NAME_LAYER = "layer";
   @SerializedName("layer")
   private GameRewindLayer layer;
   public static HashSet<String> field59 = new HashSet<>();
   public static HashSet<String> field60 = new HashSet<>();

   public GameRewindLayerAddEventData() {
   }

   public GameRewindLayerAddEventData layer(GameRewindLayer mixinhelper631) {
      this.layer = mixinhelper631;
      return this;
   }

   @Nonnull
   public GameRewindLayer method3() {
      return this.layer;
   }

   public void method3(GameRewindLayer mixinhelper631) {
      this.layer = mixinhelper631;
   }

   @Override
   public boolean equals(Object obj1) {
      if (this == obj1) {
         return true;
      } else if (obj1 != null && this.getClass() == obj1.getClass()) {
         GameRewindLayerAddEventData mixinhelper232 = (GameRewindLayerAddEventData)obj1;
         return Objects.equals(this.layer, mixinhelper232.layer) && super.equals(obj1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.layer, super.hashCode());
   }

   @Override
   public String toString() {
      StringBuilder builder1 = new StringBuilder();
      builder1.append("class GameRewindLayerAddEventData {\n");
      builder1.append("    ").append(this.toIndentedString(super.toString())).append("\n");
      builder1.append("    layer: ").append(this.toIndentedString(this.layer)).append("\n");
      builder1.append("}");
      return builder1.toString();
   }

   private String toIndentedString(Object obj1) {
      return obj1 == null ? "null" : obj1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement element0) {
      if (element0 == null && !field60.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameRewindLayerAddEventData is not found in the empty JSON string", field60.toString())
         );
      }

      for (Entry entry3 : element0.getAsJsonObject().entrySet()) {
         if (!field59.contains(entry3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `GameRewindLayerAddEventData` properties. JSON: %s", entry3.getKey(), element0.toString()
               )
            );
         }
      }

      for (String text6 : field60) {
         if (element0.getAsJsonObject().get(text6) == null) {
            throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", text6, element0.toString()));
         }
      }

      JsonObject json5 = element0.getAsJsonObject();
      GameRewindLayer.validateJsonElement(json5.get("layer"));
   }

   public static GameRewindLayerAddEventData method8(String text) {
      return (GameRewindLayerAddEventData)MixinHelper7.getGson().fromJson(text, GameRewindLayerAddEventData.class);
   }

   public String toJson() {
      return MixinHelper7.getGson().toJson(this);
   }

   static {
      field59.add("player_uuid");
      field59.add("installation_id");
      field59.add("overwolf_muid");
      field59.add("timestamp");
      field59.add("event_id");
      field59.add("assetserver_session_id");
      field59.add("launch_id");
      field59.add("inbound_location");
      field59.add("location");
      field59.add("minecraft_version");
      field59.add("lunar_client_git_commit");
      field59.add("lunar_client_git_branch");
      field59.add("lunar_client_semver");
      field59.add("lunar_client_ui_git_commit");
      field59.add("lunar_client_ui_git_branch");
      field59.add("operating_system");
      field59.add("operating_system_release");
      field59.add("cpu_architecture");
      field59.add("launcher_version");
      field59.add("canary_token");
      field59.add("ichor_modules");
      field59.add("gl_extensions");
      field59.add("installed_mods");
      field59.add("wearer_uuid");
      field59.add("wearer_cosmetic_ids");
      field59.add("wearer_outfit_id");
      field59.add("trigger");
      field59.add("geo_location");
      field59.add("layer");
      field60.add("layer");
      field60.add("installation_id");
      field60.add("installed_mods");
   }
}
