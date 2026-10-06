package com.moonsworth.lunar.network;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Map.Entry;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GameRewindEditorSessionEventData extends MixinHelper2 implements Serializable {
   private static final long field56 = 1L;
   public static final String SERIALIZED_NAME_SESSION_TYPE = "session_type";
   @SerializedName("session_type")
   private GameRewindEditorSessionEventData.Type sessionType;
   public static final String SERIALIZED_NAME_DURATION_MS = "duration_ms";
   @SerializedName("duration_ms")
   private BigDecimal durationMs;
   public static final String SERIALIZED_NAME_TIMELINES = "timelines";
   @SerializedName("timelines")
   private List<MixinHelper8> timelines = new ArrayList<>();
   public static final String SERIALIZED_NAME_FAST_REWIND = "fast_rewind";
   @SerializedName("fast_rewind")
   private Boolean fastRewind;
   public static HashSet<String> field65 = new HashSet<>();
   public static HashSet<String> field66 = new HashSet<>();

   public GameRewindEditorSessionEventData method1(GameRewindEditorSessionEventData.Type var1) {
      this.sessionType = var1;
      return this;
   }

   @Nonnull
   public GameRewindEditorSessionEventData.Type method3() {
      return this.sessionType;
   }

   public void method3(GameRewindEditorSessionEventData.Type var1) {
      this.sessionType = var1;
   }

   public GameRewindEditorSessionEventData durationMs(BigDecimal var1) {
      this.durationMs = var1;
      return this;
   }

   @Nonnull
   public BigDecimal getDurationMs() {
      return this.durationMs;
   }

   public void setDurationMs(BigDecimal var1) {
      this.durationMs = var1;
   }

   public GameRewindEditorSessionEventData timelines(List<MixinHelper8> var1) {
      this.timelines = var1;
      return this;
   }

   public GameRewindEditorSessionEventData addTimelinesItem(MixinHelper8 var1) {
      if (this.timelines == null) {
         this.timelines = new ArrayList<>();
      }

      this.timelines.add(var1);
      return this;
   }

   @Nullable
   public List<MixinHelper8> method10() {
      return this.timelines;
   }

   public void method10(List<MixinHelper8> var1) {
      this.timelines = var1;
   }

   public GameRewindEditorSessionEventData fastRewind(Boolean var1) {
      this.fastRewind = var1;
      return this;
   }

   @Nullable
   public Boolean method13() {
      return this.fastRewind;
   }

   public void method13(Boolean var1) {
      this.fastRewind = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         GameRewindEditorSessionEventData var2 = (GameRewindEditorSessionEventData)var1;
         return Objects.equals(this.sessionType, var2.sessionType)
            && Objects.equals(this.durationMs, var2.durationMs)
            && Objects.equals(this.timelines, var2.timelines)
            && Objects.equals(this.fastRewind, var2.fastRewind)
            && super.equals(var1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.sessionType, this.durationMs, this.timelines, this.fastRewind, super.hashCode());
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("class GameRewindEditorSessionEventData {\n");
      var1.append("    ").append(this.toIndentedString(super.toString())).append("\n");
      var1.append("    sessionType: ").append(this.toIndentedString(this.sessionType)).append("\n");
      var1.append("    durationMs: ").append(this.toIndentedString(this.durationMs)).append("\n");
      var1.append("    timelines: ").append(this.toIndentedString(this.timelines)).append("\n");
      var1.append("    fastRewind: ").append(this.toIndentedString(this.fastRewind)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String toIndentedString(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement var0) {
      if (var0 == null && !field66.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameRewindEditorSessionEventData is not found in the empty JSON string", field66.toString())
         );
      }

      for (Entry var3 : var0.getAsJsonObject().entrySet()) {
         if (!field65.contains(var3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `GameRewindEditorSessionEventData` properties. JSON: %s",
                  var3.getKey(),
                  var0.toString()
               )
            );
         }
      }

      for (String var7 : field66) {
         if (var0.getAsJsonObject().get(var7) == null) {
            throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", var7, var0.toString()));
         }
      }

      JsonObject var6 = var0.getAsJsonObject();
      if (!var6.get("session_type").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `session_type` to be a primitive type in the JSON string but got `%s`", var6.get("session_type").toString())
         );
      }

      GameRewindEditorSessionEventData.Type.validateJsonElement(var6.get("session_type"));
      if (var6.get("timelines") != null && !var6.get("timelines").isJsonNull()) {
         JsonArray var8 = var6.getAsJsonArray("timelines");
         if (var8 != null) {
            if (!var6.get("timelines").isJsonArray()) {
               throw new IllegalArgumentException(
                  String.format("Expected the field `timelines` to be an array in the JSON string but got `%s`", var6.get("timelines").toString())
               );
            }

            for (int var4 = 0; var4 < var8.size(); var4++) {
               MixinHelper8.validateJsonElement(var8.get(var4));
            }
         }
      }
   }

   public static GameRewindEditorSessionEventData method15(String var0) {
      return (GameRewindEditorSessionEventData)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, GameRewindEditorSessionEventData.class);
   }

   @Override
   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field65.add("player_uuid");
      field65.add("installation_id");
      field65.add("overwolf_muid");
      field65.add("timestamp");
      field65.add("event_id");
      field65.add("assetserver_session_id");
      field65.add("launch_id");
      field65.add("inbound_location");
      field65.add("location");
      field65.add("minecraft_version");
      field65.add("lunar_client_git_commit");
      field65.add("lunar_client_git_branch");
      field65.add("lunar_client_semver");
      field65.add("lunar_client_ui_git_commit");
      field65.add("lunar_client_ui_git_branch");
      field65.add("operating_system");
      field65.add("operating_system_release");
      field65.add("cpu_architecture");
      field65.add("launcher_version");
      field65.add("canary_token");
      field65.add("ichor_modules");
      field65.add("gl_extensions");
      field65.add("installed_mods");
      field65.add("wearer_uuid");
      field65.add("wearer_cosmetic_ids");
      field65.add("wearer_outfit_id");
      field65.add("trigger");
      field65.add("geo_location");
      field65.add("session_type");
      field65.add("duration_ms");
      field65.add("timelines");
      field65.add("fast_rewind");
      field66.add("session_type");
      field66.add("duration_ms");
      field66.add("installation_id");
      field66.add("installed_mods");
   }

   public static class Data implements TypeAdapterFactory {
      public <T> TypeAdapter<T> create(Gson var1, TypeToken<T> var2) {
         if (!GameRewindEditorSessionEventData.class.isAssignableFrom(var2.getRawType())) {
            return null;
         }

         final TypeAdapter var3 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var4 = var1.getDelegateAdapter(this, TypeToken.get(GameRewindEditorSessionEventData.class));
         return (new TypeAdapter<GameRewindEditorSessionEventData>() {
            public void method1(JsonWriter var1, GameRewindEditorSessionEventData var2x) {
               JsonObject var3x = var4.toJsonTree(var2x).getAsJsonObject();
               var3.write(var1, var3x);
            }

            public GameRewindEditorSessionEventData method2(JsonReader var1) {
               JsonElement var2x = (JsonElement)var3.read(var1);
               return (GameRewindEditorSessionEventData)var4.fromJsonTree(var2x);
            }
         }).nullSafe();
      }
   }

   @JsonAdapter(GameRewindEditorSessionEventData.Type.Data.class)
   public enum Type {
      PROJECT("project"),
      QUICK_VIEW("quick_view");

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

      public static GameRewindEditorSessionEventData.Type fromValue(String var0) {
         for (GameRewindEditorSessionEventData.Type var4 : values()) {
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

      public static class Data extends TypeAdapter<GameRewindEditorSessionEventData.Type> {
         public void method1(JsonWriter var1, GameRewindEditorSessionEventData.Type var2) {
            var1.value(var2.getValue());
         }

         public GameRewindEditorSessionEventData.Type method2(JsonReader var1) {
            String var2 = var1.nextString();
            return GameRewindEditorSessionEventData.Type.fromValue(var2);
         }
      }
   }
}
