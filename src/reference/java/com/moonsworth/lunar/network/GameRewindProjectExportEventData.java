package com.moonsworth.lunar.network;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.network.mixin.MixinHelper7;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Map.Entry;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GameRewindProjectExportEventData extends BaseGameEvent implements Serializable {
   private static final long field56 = 1L;
   public static final String SERIALIZED_NAME_LENGTH_MS = "length_ms";
   @SerializedName("length_ms")
   private BigDecimal lengthMs;
   public static final String SERIALIZED_NAME_PATH = "path";
   @SerializedName("path")
   private String path;
   public static final String SERIALIZED_NAME_FORMAT = "format";
   @SerializedName("format")
   private String format;
   public static final String SERIALIZED_NAME_CODEC = "codec";
   @SerializedName("codec")
   private String codec;
   public static final String SERIALIZED_NAME_ENCODER = "encoder";
   @SerializedName("encoder")
   private String encoder;
   public static final String SERIALIZED_NAME_RESOLUTION = "resolution";
   @SerializedName("resolution")
   private GameRewindExportResolution resolution;
   public static final String SERIALIZED_NAME_FRAMERATE = "framerate";
   @SerializedName("framerate")
   private BigDecimal framerate;
   public static final String SERIALIZED_NAME_BITRATE = "bitrate";
   @SerializedName("bitrate")
   private String bitrate;
   public static final String SERIALIZED_NAME_AUDIO = "audio";
   @SerializedName("audio")
   private GameRewindExportAudio audio;
   public static HashSet<String> field73 = new HashSet<>();
   public static HashSet<String> field74 = new HashSet<>();

   public GameRewindProjectExportEventData() {
   }

   public GameRewindProjectExportEventData lengthMs(BigDecimal value1) {
      this.lengthMs = value1;
      return this;
   }

   @Nonnull
   public BigDecimal method3() {
      return this.lengthMs;
   }

   public void method3(BigDecimal value1) {
      this.lengthMs = value1;
   }

   public GameRewindProjectExportEventData path(String text1) {
      this.path = text1;
      return this;
   }

   @Nonnull
   public String getPath() {
      return this.path;
   }

   public void setPath(String text1) {
      this.path = text1;
   }

   public GameRewindProjectExportEventData format(String text1) {
      this.format = text1;
      return this;
   }

   @Nonnull
   public String getFormat() {
      return this.format;
   }

   public void setFormat(String text1) {
      this.format = text1;
   }

   public GameRewindProjectExportEventData method10(String text1) {
      this.codec = text1;
      return this;
   }

   @Nonnull
   public String method10() {
      return this.codec;
   }

   public void setCodec(String text1) {
      this.codec = text1;
   }

   public GameRewindProjectExportEventData method14(String text1) {
      this.encoder = text1;
      return this;
   }

   @Nonnull
   public String getEncoder() {
      return this.encoder;
   }

   public void setEncoder(String text1) {
      this.encoder = text1;
   }

   public GameRewindProjectExportEventData method14(GameRewindExportResolution mixinhelper1) {
      this.resolution = mixinhelper1;
      return this;
   }

   @Nonnull
   public GameRewindExportResolution method16() {
      return this.resolution;
   }

   public void method16(GameRewindExportResolution mixinhelper1) {
      this.resolution = mixinhelper1;
   }

   public GameRewindProjectExportEventData framerate(BigDecimal value1) {
      this.framerate = value1;
      return this;
   }

   @Nullable
   public BigDecimal getFramerate() {
      return this.framerate;
   }

   public void setFramerate(BigDecimal value1) {
      this.framerate = value1;
   }

   public GameRewindProjectExportEventData bitrate(String text1) {
      this.bitrate = text1;
      return this;
   }

   @Nonnull
   public String getBitrate() {
      return this.bitrate;
   }

   public void setBitrate(String text1) {
      this.bitrate = text1;
   }

   public GameRewindProjectExportEventData audio(GameRewindExportAudio mixinhelper31) {
      this.audio = mixinhelper31;
      return this;
   }

   @Nullable
   public GameRewindExportAudio getAudio() {
      return this.audio;
   }

   public void setAudio(GameRewindExportAudio mixinhelper31) {
      this.audio = mixinhelper31;
   }

   @Override
   public boolean equals(Object obj1) {
      if (this == obj1) {
         return true;
      } else if (obj1 != null && this.getClass() == obj1.getClass()) {
         GameRewindProjectExportEventData mixinhelper222 = (GameRewindProjectExportEventData)obj1;
         return Objects.equals(this.lengthMs, mixinhelper222.lengthMs)
            && Objects.equals(this.path, mixinhelper222.path)
            && Objects.equals(this.format, mixinhelper222.format)
            && Objects.equals(this.codec, mixinhelper222.codec)
            && Objects.equals(this.encoder, mixinhelper222.encoder)
            && Objects.equals(this.resolution, mixinhelper222.resolution)
            && Objects.equals(this.framerate, mixinhelper222.framerate)
            && Objects.equals(this.bitrate, mixinhelper222.bitrate)
            && Objects.equals(this.audio, mixinhelper222.audio)
            && super.equals(obj1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(
         this.lengthMs, this.path, this.format, this.codec, this.encoder, this.resolution, this.framerate, this.bitrate, this.audio, super.hashCode()
      );
   }

   @Override
   public String toString() {
      StringBuilder builder1 = new StringBuilder();
      builder1.append("class GameRewindProjectExportEventData {\n");
      builder1.append("    ").append(this.toIndentedString(super.toString())).append("\n");
      builder1.append("    lengthMs: ").append(this.toIndentedString(this.lengthMs)).append("\n");
      builder1.append("    path: ").append(this.toIndentedString(this.path)).append("\n");
      builder1.append("    format: ").append(this.toIndentedString(this.format)).append("\n");
      builder1.append("    codec: ").append(this.toIndentedString(this.codec)).append("\n");
      builder1.append("    encoder: ").append(this.toIndentedString(this.encoder)).append("\n");
      builder1.append("    resolution: ").append(this.toIndentedString(this.resolution)).append("\n");
      builder1.append("    framerate: ").append(this.toIndentedString(this.framerate)).append("\n");
      builder1.append("    bitrate: ").append(this.toIndentedString(this.bitrate)).append("\n");
      builder1.append("    audio: ").append(this.toIndentedString(this.audio)).append("\n");
      builder1.append("}");
      return builder1.toString();
   }

   private String toIndentedString(Object obj1) {
      return obj1 == null ? "null" : obj1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement element0) {
      if (element0 == null && !field74.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameRewindProjectExportEventData is not found in the empty JSON string", field74.toString())
         );
      }

      for (Entry entry3 : element0.getAsJsonObject().entrySet()) {
         if (!field73.contains(entry3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `GameRewindProjectExportEventData` properties. JSON: %s",
                  entry3.getKey(),
                  element0.toString()
               )
            );
         }
      }

      for (String text6 : field74) {
         if (element0.getAsJsonObject().get(text6) == null) {
            throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", text6, element0.toString()));
         }
      }

      JsonObject json5 = element0.getAsJsonObject();
      if (!json5.get("path").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `path` to be a primitive type in the JSON string but got `%s`", json5.get("path").toString())
         );
      }

      if (!json5.get("format").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `format` to be a primitive type in the JSON string but got `%s`", json5.get("format").toString())
         );
      }

      if (!json5.get("codec").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `codec` to be a primitive type in the JSON string but got `%s`", json5.get("codec").toString())
         );
      }

      if (!json5.get("encoder").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `encoder` to be a primitive type in the JSON string but got `%s`", json5.get("encoder").toString())
         );
      }

      GameRewindExportResolution.validateJsonElement(json5.get("resolution"));
      if (!json5.get("bitrate").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `bitrate` to be a primitive type in the JSON string but got `%s`", json5.get("bitrate").toString())
         );
      }

      if (json5.get("audio") != null && !json5.get("audio").isJsonNull()) {
         GameRewindExportAudio.validateJsonElement(json5.get("audio"));
      }
   }

   public static GameRewindProjectExportEventData method29(String text) {
      return (GameRewindProjectExportEventData)MixinHelper7.getGson().fromJson(text, GameRewindProjectExportEventData.class);
   }

   public String toJson() {
      return MixinHelper7.getGson().toJson(this);
   }

   static {
      field73.add("player_uuid");
      field73.add("installation_id");
      field73.add("overwolf_muid");
      field73.add("timestamp");
      field73.add("event_id");
      field73.add("assetserver_session_id");
      field73.add("launch_id");
      field73.add("inbound_location");
      field73.add("location");
      field73.add("minecraft_version");
      field73.add("lunar_client_git_commit");
      field73.add("lunar_client_git_branch");
      field73.add("lunar_client_semver");
      field73.add("lunar_client_ui_git_commit");
      field73.add("lunar_client_ui_git_branch");
      field73.add("operating_system");
      field73.add("operating_system_release");
      field73.add("cpu_architecture");
      field73.add("launcher_version");
      field73.add("canary_token");
      field73.add("ichor_modules");
      field73.add("gl_extensions");
      field73.add("installed_mods");
      field73.add("wearer_uuid");
      field73.add("wearer_cosmetic_ids");
      field73.add("wearer_outfit_id");
      field73.add("trigger");
      field73.add("geo_location");
      field73.add("length_ms");
      field73.add("path");
      field73.add("format");
      field73.add("codec");
      field73.add("encoder");
      field73.add("resolution");
      field73.add("framerate");
      field73.add("bitrate");
      field73.add("audio");
      field74.add("length_ms");
      field74.add("path");
      field74.add("format");
      field74.add("codec");
      field74.add("encoder");
      field74.add("resolution");
      field74.add("framerate");
      field74.add("bitrate");
      field74.add("installation_id");
      field74.add("installed_mods");
   }
}
