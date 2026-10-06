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
import javax.annotation.Nullable;

public class GameRewindExportResolution implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_WIDTH = "width";
   @SerializedName("width")
   private BigDecimal width;
   public static final String SERIALIZED_NAME_HEIGHT = "height";
   @SerializedName("height")
   private BigDecimal height;
   public static HashSet<String> field6 = new HashSet<>();
   public static HashSet<String> field7 = new HashSet<>();

   public GameRewindExportResolution() {
   }

   public GameRewindExportResolution width(BigDecimal value1) {
      this.width = value1;
      return this;
   }

   @Nullable
   public BigDecimal getWidth() {
      return this.width;
   }

   public void setWidth(BigDecimal value1) {
      this.width = value1;
   }

   public GameRewindExportResolution height(BigDecimal value1) {
      this.height = value1;
      return this;
   }

   @Nullable
   public BigDecimal getHeight() {
      return this.height;
   }

   public void setHeight(BigDecimal value1) {
      this.height = value1;
   }

   @Override
   public boolean equals(Object obj1) {
      if (this == obj1) {
         return true;
      } else if (obj1 != null && this.getClass() == obj1.getClass()) {
         GameRewindExportResolution mixinhelper2 = (GameRewindExportResolution)obj1;
         return Objects.equals(this.width, mixinhelper2.width) && Objects.equals(this.height, mixinhelper2.height);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.width, this.height);
   }

   @Override
   public String toString() {
      StringBuilder builder1 = new StringBuilder();
      builder1.append("class GameRewindExportResolution {\n");
      builder1.append("    width: ").append(this.toIndentedString(this.width)).append("\n");
      builder1.append("    height: ").append(this.toIndentedString(this.height)).append("\n");
      builder1.append("}");
      return builder1.toString();
   }

   private String toIndentedString(Object obj1) {
      return obj1 == null ? "null" : obj1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement element0) {
      if (element0 == null && !field7.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameRewindExportResolution is not found in the empty JSON string", field7.toString())
         );
      }

      for (Entry entry3 : element0.getAsJsonObject().entrySet()) {
         if (!field6.contains(entry3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `GameRewindExportResolution` properties. JSON: %s", entry3.getKey(), element0.toString()
               )
            );
         }
      }

      for (String text6 : field7) {
         if (element0.getAsJsonObject().get(text6) == null) {
            throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", text6, element0.toString()));
         }
      }

      JsonObject json5 = element0.getAsJsonObject();
   }

   public static GameRewindExportResolution method8(String text) {
      return (GameRewindExportResolution)MixinHelper7.getGson().fromJson(text, GameRewindExportResolution.class);
   }

   public String toJson() {
      return MixinHelper7.getGson().toJson(this);
   }

   static {
      field6.add("width");
      field6.add("height");
      field7.add("width");
      field7.add("height");
   }
}
