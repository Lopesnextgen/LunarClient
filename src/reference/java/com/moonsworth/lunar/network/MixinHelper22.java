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
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Map.Entry;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MixinHelper22 extends MixinHelper2 implements Serializable {
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
   private MixinHelper resolution;
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

   public MixinHelper22 method1(BigDecimal var1) {
      this.lengthMs = var1;
      return this;
   }

   @Nonnull
   public BigDecimal method3() {
      return this.lengthMs;
   }

   public void method3(BigDecimal var1) {
      this.lengthMs = var1;
   }

   public MixinHelper22 path(String var1) {
      this.path = var1;
      return this;
   }

   @Nonnull
   public String getPath() {
      return this.path;
   }

   public void setPath(String var1) {
      this.path = var1;
   }

   public MixinHelper22 format(String var1) {
      this.format = var1;
      return this;
   }

   @Nonnull
   public String getFormat() {
      return this.format;
   }

   public void setFormat(String var1) {
      this.format = var1;
   }

   public MixinHelper22 method10(String var1) {
      this.codec = var1;
      return this;
   }

   @Nonnull
   public String method10() {
      return this.codec;
   }

   public void setCodec(String var1) {
      this.codec = var1;
   }

   public MixinHelper22 method14(String var1) {
      this.encoder = var1;
      return this;
   }

   @Nonnull
   public String getEncoder() {
      return this.encoder;
   }

   public void setEncoder(String var1) {
      this.encoder = var1;
   }

   public MixinHelper22 method14(MixinHelper var1) {
      this.resolution = var1;
      return this;
   }

   @Nonnull
   public MixinHelper method16() {
      return this.resolution;
   }

   public void method16(MixinHelper var1) {
      this.resolution = var1;
   }

   public MixinHelper22 framerate(BigDecimal var1) {
      this.framerate = var1;
      return this;
   }

   @Nullable
   public BigDecimal getFramerate() {
      return this.framerate;
   }

   public void setFramerate(BigDecimal var1) {
      this.framerate = var1;
   }

   public MixinHelper22 bitrate(String var1) {
      this.bitrate = var1;
      return this;
   }

   @Nonnull
   public String getBitrate() {
      return this.bitrate;
   }

   public void setBitrate(String var1) {
      this.bitrate = var1;
   }

   public MixinHelper22 audio(GameRewindExportAudio var1) {
      this.audio = var1;
      return this;
   }

   @Nullable
   public GameRewindExportAudio getAudio() {
      return this.audio;
   }

   public void setAudio(GameRewindExportAudio var1) {
      this.audio = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         MixinHelper22 var2 = (MixinHelper22)var1;
         return Objects.equals(this.lengthMs, var2.lengthMs)
            && Objects.equals(this.path, var2.path)
            && Objects.equals(this.format, var2.format)
            && Objects.equals(this.codec, var2.codec)
            && Objects.equals(this.encoder, var2.encoder)
            && Objects.equals(this.resolution, var2.resolution)
            && Objects.equals(this.framerate, var2.framerate)
            && Objects.equals(this.bitrate, var2.bitrate)
            && Objects.equals(this.audio, var2.audio)
            && super.equals(var1);
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
      StringBuilder var1 = new StringBuilder();
      var1.append("class GameRewindProjectExportEventData {\n");
      var1.append("    ").append(this.toIndentedString(super.toString())).append("\n");
      var1.append("    lengthMs: ").append(this.toIndentedString(this.lengthMs)).append("\n");
      var1.append("    path: ").append(this.toIndentedString(this.path)).append("\n");
      var1.append("    format: ").append(this.toIndentedString(this.format)).append("\n");
      var1.append("    codec: ").append(this.toIndentedString(this.codec)).append("\n");
      var1.append("    encoder: ").append(this.toIndentedString(this.encoder)).append("\n");
      var1.append("    resolution: ").append(this.toIndentedString(this.resolution)).append("\n");
      var1.append("    framerate: ").append(this.toIndentedString(this.framerate)).append("\n");
      var1.append("    bitrate: ").append(this.toIndentedString(this.bitrate)).append("\n");
      var1.append("    audio: ").append(this.toIndentedString(this.audio)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String toIndentedString(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement var0) {
      if (var0 == null && !field74.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameRewindProjectExportEventData is not found in the empty JSON string", field74.toString())
         );
      }

      for (Entry var3 : var0.getAsJsonObject().entrySet()) {
         if (!field73.contains(var3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `GameRewindProjectExportEventData` properties. JSON: %s",
                  var3.getKey(),
                  var0.toString()
               )
            );
         }
      }

      for (String var6 : field74) {
         if (var0.getAsJsonObject().get(var6) == null) {
            throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", var6, var0.toString()));
         }
      }

      JsonObject var5 = var0.getAsJsonObject();
      if (!var5.get("path").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `path` to be a primitive type in the JSON string but got `%s`", var5.get("path").toString())
         );
      }

      if (!var5.get("format").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `format` to be a primitive type in the JSON string but got `%s`", var5.get("format").toString())
         );
      }

      if (!var5.get("codec").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `codec` to be a primitive type in the JSON string but got `%s`", var5.get("codec").toString())
         );
      }

      if (!var5.get("encoder").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `encoder` to be a primitive type in the JSON string but got `%s`", var5.get("encoder").toString())
         );
      }

      MixinHelper.validateJsonElement(var5.get("resolution"));
      if (!var5.get("bitrate").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `bitrate` to be a primitive type in the JSON string but got `%s`", var5.get("bitrate").toString())
         );
      }

      if (var5.get("audio") != null && !var5.get("audio").isJsonNull()) {
         GameRewindExportAudio.validateJsonElement(var5.get("audio"));
      }
   }

   public static MixinHelper22 method29(String var0) {
      return (MixinHelper22)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, MixinHelper22.class);
   }

   @Override
   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
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

   public static class Data implements TypeAdapterFactory {
      public <T> TypeAdapter<T> create(Gson var1, TypeToken<T> var2) {
         if (!MixinHelper22.class.isAssignableFrom(var2.getRawType())) {
            return null;
         }

         final TypeAdapter var3 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var4 = var1.getDelegateAdapter(this, TypeToken.get(MixinHelper22.class));
         return (new TypeAdapter<MixinHelper22>() {
            public void method1(JsonWriter var1, MixinHelper22 var2x) {
               JsonObject var3x = var4.toJsonTree(var2x).getAsJsonObject();
               var3.write(var1, var3x);
            }

            public MixinHelper22 method2(JsonReader var1) {
               JsonElement var2x = (JsonElement)var3.read(var1);
               return (MixinHelper22)var4.fromJsonTree(var2x);
            }
         }).nullSafe();
      }
   }
}
