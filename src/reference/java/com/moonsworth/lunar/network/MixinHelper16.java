package com.moonsworth.lunar.network;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MixinHelper16 implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_DURATION_MS = "duration_ms";
   @SerializedName("duration_ms")
   private BigDecimal durationMs;
   public static final String SERIALIZED_NAME_PROPERTIES = "properties";
   @SerializedName("properties")
   private List<String> properties = new ArrayList<>();
   public static HashSet<String> field6 = new HashSet<>();
   public static HashSet<String> field7 = new HashSet<>();

   public MixinHelper16 durationMs(BigDecimal var1) {
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

   public MixinHelper16 properties(List<String> var1) {
      this.properties = var1;
      return this;
   }

   public MixinHelper16 addPropertiesItem(String var1) {
      if (this.properties == null) {
         this.properties = new ArrayList<>();
      }

      this.properties.add(var1);
      return this;
   }

   @Nullable
   public List<String> getProperties() {
      return this.properties;
   }

   public void setProperties(List<String> var1) {
      this.properties = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         MixinHelper16 var2 = (MixinHelper16)var1;
         return Objects.equals(this.durationMs, var2.durationMs) && Objects.equals(this.properties, var2.properties);
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
      StringBuilder var1 = new StringBuilder();
      var1.append("class GameRewindBaseLayer {\n");
      var1.append("    durationMs: ").append(this.toIndentedString(this.durationMs)).append("\n");
      var1.append("    properties: ").append(this.toIndentedString(this.properties)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String toIndentedString(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement var0) {
      if (var0 == null && !field7.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameRewindBaseLayer is not found in the empty JSON string", field7.toString())
         );
      }

      JsonObject var1 = var0.getAsJsonObject();
      if (var1.get("properties") != null && !var1.get("properties").isJsonNull() && !var1.get("properties").isJsonArray()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `properties` to be an array in the JSON string but got `%s`", var1.get("properties").toString())
         );
      }
   }

   public static MixinHelper16 method9(String var0) {
      return (MixinHelper16)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, MixinHelper16.class);
   }

   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field6.add("duration_ms");
      field6.add("properties");
      field7.add("duration_ms");
   }
}
