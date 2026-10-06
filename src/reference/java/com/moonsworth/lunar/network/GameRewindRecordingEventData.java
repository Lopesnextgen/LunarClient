package com.moonsworth.lunar.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.network.mixin.MixinHelper7;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Map.Entry;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GameRewindRecordingEventData extends BaseGameEvent implements Serializable {
   private static final long field56 = 1L;
   public static final String SERIALIZED_NAME_DURATION_MS = "duration_ms";
   @SerializedName("duration_ms")
   private BigDecimal durationMs;
   public static final String SERIALIZED_NAME_LOCATIONS = "locations";
   @SerializedName("locations")
   private List<GameRewindRecordingLocation> locations = new ArrayList<>();
   public static final String SERIALIZED_NAME_SHADOW_REWIND = "shadow_rewind";
   @SerializedName("shadow_rewind")
   private Boolean shadowRewind = false;
   public static final String SERIALIZED_NAME_MIC_RECORDING = "mic_recording";
   @SerializedName("mic_recording")
   private Boolean micRecording = false;
   public static final String SERIALIZED_NAME_SYSTEM_AUDIO_RECORDING = "system_audio_recording";
   @SerializedName("system_audio_recording")
   private Boolean systemAudioRecording = false;
   public static HashSet<String> field67 = new HashSet<>();
   public static HashSet<String> field68 = new HashSet<>();

   public GameRewindRecordingEventData() {
   }

   public GameRewindRecordingEventData durationMs(BigDecimal value1) {
      this.durationMs = value1;
      return this;
   }

   @Nonnull
   public BigDecimal method3() {
      return this.durationMs;
   }

   public void method3(BigDecimal value1) {
      this.durationMs = value1;
   }

   public GameRewindRecordingEventData locations(List<GameRewindRecordingLocation> list1) {
      this.locations = list1;
      return this;
   }

   public GameRewindRecordingEventData addLocationsItem(GameRewindRecordingLocation mixinhelper51) {
      if (this.locations == null) {
         this.locations = new ArrayList<>();
      }

      this.locations.add(mixinhelper51);
      return this;
   }

   @Nonnull
   public List<GameRewindRecordingLocation> getLocations() {
      return this.locations;
   }

   public void setLocations(List<GameRewindRecordingLocation> list1) {
      this.locations = list1;
   }

   public GameRewindRecordingEventData shadowRewind(Boolean flag1) {
      this.shadowRewind = flag1;
      return this;
   }

   @Nullable
   public Boolean method10() {
      return this.shadowRewind;
   }

   public void method10(Boolean flag1) {
      this.shadowRewind = flag1;
   }

   public GameRewindRecordingEventData micRecording(Boolean flag1) {
      this.micRecording = flag1;
      return this;
   }

   @Nullable
   public Boolean method13() {
      return this.micRecording;
   }

   public void method13(Boolean flag1) {
      this.micRecording = flag1;
   }

   public GameRewindRecordingEventData systemAudioRecording(Boolean flag1) {
      this.systemAudioRecording = flag1;
      return this;
   }

   @Nullable
   public Boolean method16() {
      return this.systemAudioRecording;
   }

   public void method16(Boolean flag1) {
      this.systemAudioRecording = flag1;
   }

   @Override
   public boolean equals(Object obj1) {
      if (this == obj1) {
         return true;
      } else if (obj1 != null && this.getClass() == obj1.getClass()) {
         GameRewindRecordingEventData mixinhelper262 = (GameRewindRecordingEventData)obj1;
         return Objects.equals(this.durationMs, mixinhelper262.durationMs)
            && Objects.equals(this.locations, mixinhelper262.locations)
            && Objects.equals(this.shadowRewind, mixinhelper262.shadowRewind)
            && Objects.equals(this.micRecording, mixinhelper262.micRecording)
            && Objects.equals(this.systemAudioRecording, mixinhelper262.systemAudioRecording)
            && super.equals(obj1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.durationMs, this.locations, this.shadowRewind, this.micRecording, this.systemAudioRecording, super.hashCode());
   }

   @Override
   public String toString() {
      StringBuilder builder1 = new StringBuilder();
      builder1.append("class GameRewindRecordingEventData {\n");
      builder1.append("    ").append(this.toIndentedString(super.toString())).append("\n");
      builder1.append("    durationMs: ").append(this.toIndentedString(this.durationMs)).append("\n");
      builder1.append("    locations: ").append(this.toIndentedString(this.locations)).append("\n");
      builder1.append("    shadowRewind: ").append(this.toIndentedString(this.shadowRewind)).append("\n");
      builder1.append("    micRecording: ").append(this.toIndentedString(this.micRecording)).append("\n");
      builder1.append("    systemAudioRecording: ").append(this.toIndentedString(this.systemAudioRecording)).append("\n");
      builder1.append("}");
      return builder1.toString();
   }

   private String toIndentedString(Object obj1) {
      return obj1 == null ? "null" : obj1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement element0) {
      if (element0 == null && !field68.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameRewindRecordingEventData is not found in the empty JSON string", field68.toString())
         );
      }

      for (Entry entry3 : element0.getAsJsonObject().entrySet()) {
         if (!field67.contains(entry3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `GameRewindRecordingEventData` properties. JSON: %s", entry3.getKey(), element0.toString()
               )
            );
         }
      }

      for (String text7 : field68) {
         if (element0.getAsJsonObject().get(text7) == null) {
            throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", text7, element0.toString()));
         }
      }

      JsonObject json6 = element0.getAsJsonObject();
      if (!json6.get("locations").isJsonArray()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `locations` to be an array in the JSON string but got `%s`", json6.get("locations").toString())
         );
      }

      JsonArray array8 = json6.getAsJsonArray("locations");

      for (int index4 = 0; index4 < array8.size(); index4++) {
         GameRewindRecordingLocation.validateJsonElement(array8.get(index4));
      }
   }

   public static GameRewindRecordingEventData method19(String text) {
      return (GameRewindRecordingEventData)MixinHelper7.getGson().fromJson(text, GameRewindRecordingEventData.class);
   }

   public String toJson() {
      return MixinHelper7.getGson().toJson(this);
   }

   static {
      field67.add("player_uuid");
      field67.add("installation_id");
      field67.add("overwolf_muid");
      field67.add("timestamp");
      field67.add("event_id");
      field67.add("assetserver_session_id");
      field67.add("launch_id");
      field67.add("inbound_location");
      field67.add("location");
      field67.add("minecraft_version");
      field67.add("lunar_client_git_commit");
      field67.add("lunar_client_git_branch");
      field67.add("lunar_client_semver");
      field67.add("lunar_client_ui_git_commit");
      field67.add("lunar_client_ui_git_branch");
      field67.add("operating_system");
      field67.add("operating_system_release");
      field67.add("cpu_architecture");
      field67.add("launcher_version");
      field67.add("canary_token");
      field67.add("ichor_modules");
      field67.add("gl_extensions");
      field67.add("installed_mods");
      field67.add("wearer_uuid");
      field67.add("wearer_cosmetic_ids");
      field67.add("wearer_outfit_id");
      field67.add("trigger");
      field67.add("geo_location");
      field67.add("duration_ms");
      field67.add("locations");
      field67.add("shadow_rewind");
      field67.add("mic_recording");
      field67.add("system_audio_recording");
      field68.add("duration_ms");
      field68.add("locations");
      field68.add("installation_id");
      field68.add("installed_mods");
   }
}
