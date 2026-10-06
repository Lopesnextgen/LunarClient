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

public class GameRewindEditorTimeline implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_DURATION_MS = "duration_ms";
   @SerializedName("duration_ms")
   private BigDecimal durationMs;
   public static final String SERIALIZED_NAME_LAYERS = "layers";
   @SerializedName("layers")
   private List<GameRewindLayer> layers = new ArrayList<>();
   public static HashSet<String> field5 = new HashSet<>();
   public static HashSet<String> field6 = new HashSet<>();

   public GameRewindEditorTimeline() {
   }

   public GameRewindEditorTimeline durationMs(BigDecimal value1) {
      this.durationMs = value1;
      return this;
   }

   @Nonnull
   public BigDecimal getDurationMs() {
      return this.durationMs;
   }

   public void setDurationMs(BigDecimal value1) {
      this.durationMs = value1;
   }

   public GameRewindEditorTimeline layers(List<GameRewindLayer> list1) {
      this.layers = list1;
      return this;
   }

   public GameRewindEditorTimeline addLayersItem(GameRewindLayer mixinhelper631) {
      if (this.layers == null) {
         this.layers = new ArrayList<>();
      }

      this.layers.add(mixinhelper631);
      return this;
   }

   @Nonnull
   public List<GameRewindLayer> getLayers() {
      return this.layers;
   }

   public void setLayers(List<GameRewindLayer> list1) {
      this.layers = list1;
   }

   @Override
   public boolean equals(Object obj1) {
      if (this == obj1) {
         return true;
      } else if (obj1 != null && this.getClass() == obj1.getClass()) {
         GameRewindEditorTimeline mixinhelper82 = (GameRewindEditorTimeline)obj1;
         return Objects.equals(this.durationMs, mixinhelper82.durationMs) && Objects.equals(this.layers, mixinhelper82.layers);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.durationMs, this.layers);
   }

   @Override
   public String toString() {
      StringBuilder builder1 = new StringBuilder();
      builder1.append("class GameRewindEditorTimeline {\n");
      builder1.append("    durationMs: ").append(this.toIndentedString(this.durationMs)).append("\n");
      builder1.append("    layers: ").append(this.toIndentedString(this.layers)).append("\n");
      builder1.append("}");
      return builder1.toString();
   }

   private String toIndentedString(Object obj1) {
      return obj1 == null ? "null" : obj1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement element0) {
      if (element0 == null && !field6.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameRewindEditorTimeline is not found in the empty JSON string", field6.toString())
         );
      }

      for (Entry entry3 : element0.getAsJsonObject().entrySet()) {
         if (!field5.contains(entry3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `GameRewindEditorTimeline` properties. JSON: %s", entry3.getKey(), element0.toString()
               )
            );
         }
      }

      for (String text7 : field6) {
         if (element0.getAsJsonObject().get(text7) == null) {
            throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", text7, element0.toString()));
         }
      }

      JsonObject json6 = element0.getAsJsonObject();
      if (!json6.get("layers").isJsonArray()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `layers` to be an array in the JSON string but got `%s`", json6.get("layers").toString())
         );
      }

      JsonArray array8 = json6.getAsJsonArray("layers");

      for (int index4 = 0; index4 < array8.size(); index4++) {
         GameRewindLayer.validateJsonElement(array8.get(index4));
      }
   }

   public static GameRewindEditorTimeline method8(String text) {
      return (GameRewindEditorTimeline)MixinHelper7.getGson().fromJson(text, GameRewindEditorTimeline.class);
   }

   public String toJson() {
      return MixinHelper7.getGson().toJson(this);
   }

   static {
      field5.add("duration_ms");
      field5.add("layers");
      field6.add("duration_ms");
      field6.add("layers");
   }
}
