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
import javax.annotation.Nullable;

public class MixinHelper26 extends MixinHelper2 implements Serializable {
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

   public MixinHelper26 method1(BigDecimal var1) {
      this.durationMs = var1;
      return this;
   }

   @Nonnull
   public BigDecimal method3() {
      return this.durationMs;
   }

   public void method3(BigDecimal var1) {
      this.durationMs = var1;
   }

   public MixinHelper26 locations(List<GameRewindRecordingLocation> var1) {
      this.locations = var1;
      return this;
   }

   public MixinHelper26 addLocationsItem(GameRewindRecordingLocation var1) {
      if (this.locations == null) {
         this.locations = new ArrayList<>();
      }

      this.locations.add(var1);
      return this;
   }

   @Nonnull
   public List<GameRewindRecordingLocation> getLocations() {
      return this.locations;
   }

   public void setLocations(List<GameRewindRecordingLocation> var1) {
      this.locations = var1;
   }

   public MixinHelper26 shadowRewind(Boolean var1) {
      this.shadowRewind = var1;
      return this;
   }

   @Nullable
   public Boolean method10() {
      return this.shadowRewind;
   }

   public void method10(Boolean var1) {
      this.shadowRewind = var1;
   }

   public MixinHelper26 micRecording(Boolean var1) {
      this.micRecording = var1;
      return this;
   }

   @Nullable
   public Boolean method13() {
      return this.micRecording;
   }

   public void method13(Boolean var1) {
      this.micRecording = var1;
   }

   public MixinHelper26 systemAudioRecording(Boolean var1) {
      this.systemAudioRecording = var1;
      return this;
   }

   @Nullable
   public Boolean method16() {
      return this.systemAudioRecording;
   }

   public void method16(Boolean var1) {
      this.systemAudioRecording = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         MixinHelper26 var2 = (MixinHelper26)var1;
         return Objects.equals(this.durationMs, var2.durationMs)
            && Objects.equals(this.locations, var2.locations)
            && Objects.equals(this.shadowRewind, var2.shadowRewind)
            && Objects.equals(this.micRecording, var2.micRecording)
            && Objects.equals(this.systemAudioRecording, var2.systemAudioRecording)
            && super.equals(var1);
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
      StringBuilder var1 = new StringBuilder();
      var1.append("class GameRewindRecordingEventData {\n");
      var1.append("    ").append(this.toIndentedString(super.toString())).append("\n");
      var1.append("    durationMs: ").append(this.toIndentedString(this.durationMs)).append("\n");
      var1.append("    locations: ").append(this.toIndentedString(this.locations)).append("\n");
      var1.append("    shadowRewind: ").append(this.toIndentedString(this.shadowRewind)).append("\n");
      var1.append("    micRecording: ").append(this.toIndentedString(this.micRecording)).append("\n");
      var1.append("    systemAudioRecording: ").append(this.toIndentedString(this.systemAudioRecording)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String toIndentedString(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement var0) {
      if (var0 == null && !field68.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameRewindRecordingEventData is not found in the empty JSON string", field68.toString())
         );
      }

      for (Entry var3 : var0.getAsJsonObject().entrySet()) {
         if (!field67.contains(var3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `GameRewindRecordingEventData` properties. JSON: %s", var3.getKey(), var0.toString()
               )
            );
         }
      }

      for (String var7 : field68) {
         if (var0.getAsJsonObject().get(var7) == null) {
            throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", var7, var0.toString()));
         }
      }

      JsonObject var6 = var0.getAsJsonObject();
      if (!var6.get("locations").isJsonArray()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `locations` to be an array in the JSON string but got `%s`", var6.get("locations").toString())
         );
      }

      JsonArray var8 = var6.getAsJsonArray("locations");

      for (int var4 = 0; var4 < var8.size(); var4++) {
         GameRewindRecordingLocation.validateJsonElement(var8.get(var4));
      }
   }

   public static MixinHelper26 method19(String var0) {
      return (MixinHelper26)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, MixinHelper26.class);
   }

   @Override
   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
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

   public static class Data implements TypeAdapterFactory {
      public <T> TypeAdapter<T> create(Gson var1, TypeToken<T> var2) {
         if (!MixinHelper26.class.isAssignableFrom(var2.getRawType())) {
            return null;
         }

         final TypeAdapter var3 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var4 = var1.getDelegateAdapter(this, TypeToken.get(MixinHelper26.class));
         return (new TypeAdapter<MixinHelper26>() {
            public void method1(JsonWriter var1, MixinHelper26 var2x) {
               JsonObject var3x = var4.toJsonTree(var2x).getAsJsonObject();
               var3.write(var1, var3x);
            }

            public MixinHelper26 method2(JsonReader var1) {
               JsonElement var2x = (JsonElement)var3.read(var1);
               return (MixinHelper26)var4.fromJsonTree(var2x);
            }
         }).nullSafe();
      }
   }
}
