package com.moonsworth.lunar.network;

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
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GameRewindBaseLayer implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_DURATION_MS = "duration_ms";
   @SerializedName("duration_ms")
   private BigDecimal durationMs;
   public static final String SERIALIZED_NAME_PROPERTIES = "properties";
   @SerializedName("properties")
   private List<String> properties = new ArrayList<>();
   public static HashSet<String> field6 = new HashSet<>();
   public static HashSet<String> field7 = new HashSet<>();

   public GameRewindBaseLayer() {
   }

   public GameRewindBaseLayer durationMs(BigDecimal value1) {
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

   public GameRewindBaseLayer properties(List<String> list1) {
      this.properties = list1;
      return this;
   }

   public GameRewindBaseLayer addPropertiesItem(String text) {
      if (this.properties == null) {
         this.properties = new ArrayList<>();
      }

      this.properties.add(text);
      return this;
   }

   @Nullable
   public List<String> getProperties() {
      return this.properties;
   }

   public void setProperties(List<String> list1) {
      this.properties = list1;
   }

   @Override
   public boolean equals(Object obj1) {
      if (this == obj1) {
         return true;
      } else if (obj1 != null && this.getClass() == obj1.getClass()) {
         GameRewindBaseLayer mixinhelper162 = (GameRewindBaseLayer)obj1;
         return Objects.equals(this.durationMs, mixinhelper162.durationMs) && Objects.equals(this.properties, mixinhelper162.properties);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.durationMs, this.properties);
   }

   @Override
   public String toString() {
      StringBuilder builder1 = new StringBuilder();
      builder1.append("class GameRewindBaseLayer {\n");
      builder1.append("    durationMs: ").append(this.toIndentedString(this.durationMs)).append("\n");
      builder1.append("    properties: ").append(this.toIndentedString(this.properties)).append("\n");
      builder1.append("}");
      return builder1.toString();
   }

   private String toIndentedString(Object obj1) {
      return obj1 == null ? "null" : obj1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement element0) {
      if (element0 == null && !field7.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameRewindBaseLayer is not found in the empty JSON string", field7.toString())
         );
      }

      JsonObject json1 = element0.getAsJsonObject();
      if (json1.get("properties") != null && !json1.get("properties").isJsonNull() && !json1.get("properties").isJsonArray()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `properties` to be an array in the JSON string but got `%s`", json1.get("properties").toString())
         );
      }
   }

   public static GameRewindBaseLayer method9(String text) {
      return (GameRewindBaseLayer)MixinHelper7.getGson().fromJson(text, GameRewindBaseLayer.class);
   }

   public String toJson() {
      return MixinHelper7.getGson().toJson(this);
   }

   static {
      field6.add("duration_ms");
      field6.add("properties");
      field7.add("duration_ms");
   }
}
