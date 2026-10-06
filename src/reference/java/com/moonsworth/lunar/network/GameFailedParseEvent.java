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
import javax.annotation.Nullable;

public class GameFailedParseEvent implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_TYPE = "type";
   @SerializedName("type")
   private String type;
   public static final String SERIALIZED_NAME_DATA = "data";
   @SerializedName("data")
   private GameFailedParseEventData data;
   public static final String SERIALIZED_NAME__FAILED_PARSE = "__failed_parse";
   @SerializedName("__failed_parse")
   private Boolean FailedParse = true;
   public static HashSet<String> field7 = new HashSet<>();
   public static HashSet<String> field8 = new HashSet<>();

   public GameFailedParseEvent() {
   }

   public GameFailedParseEvent type(String text1) {
      this.type = text1;
      return this;
   }

   @Nonnull
   public String getType() {
      return this.type;
   }

   public void setType(String text1) {
      this.type = text1;
   }

   public GameFailedParseEvent data(GameFailedParseEventData mixinhelper151) {
      this.data = mixinhelper151;
      return this;
   }

   @Nonnull
   public GameFailedParseEventData getData() {
      return this.data;
   }

   public void setData(GameFailedParseEventData mixinhelper151) {
      this.data = mixinhelper151;
   }

   public GameFailedParseEvent FailedParse(Boolean flag1) {
      this.FailedParse = flag1;
      return this;
   }

   @Nullable
   public Boolean getFailedParse() {
      return this.FailedParse;
   }

   public void setFailedParse(Boolean flag1) {
      this.FailedParse = flag1;
   }

   @Override
   public boolean equals(Object obj1) {
      if (this == obj1) {
         return true;
      } else if (obj1 != null && this.getClass() == obj1.getClass()) {
         GameFailedParseEvent mixinhelper42 = (GameFailedParseEvent)obj1;
         return Objects.equals(this.type, mixinhelper42.type) && Objects.equals(this.data, mixinhelper42.data) && Objects.equals(this.FailedParse, mixinhelper42.FailedParse);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.type, this.data, this.FailedParse);
   }

   @Override
   public String toString() {
      StringBuilder builder1 = new StringBuilder();
      builder1.append("class GameFailedParseEvent {\n");
      builder1.append("    type: ").append(this.toIndentedString(this.type)).append("\n");
      builder1.append("    data: ").append(this.toIndentedString(this.data)).append("\n");
      builder1.append("    failedParse: ").append(this.toIndentedString(this.FailedParse)).append("\n");
      builder1.append("}");
      return builder1.toString();
   }

   private String toIndentedString(Object obj1) {
      return obj1 == null ? "null" : obj1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement element0) {
      if (element0 == null && !field8.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameFailedParseEvent is not found in the empty JSON string", field8.toString())
         );
      }

      for (Entry entry3 : element0.getAsJsonObject().entrySet()) {
         if (!field7.contains(entry3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `GameFailedParseEvent` properties. JSON: %s", entry3.getKey(), element0.toString()
               )
            );
         }
      }

      for (String text6 : field8) {
         if (element0.getAsJsonObject().get(text6) == null) {
            throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", text6, element0.toString()));
         }
      }

      JsonObject json5 = element0.getAsJsonObject();
      if (!json5.get("type").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `type` to be a primitive type in the JSON string but got `%s`", json5.get("type").toString())
         );
      }
   }

   public static GameFailedParseEvent method9(String text) {
      return (GameFailedParseEvent)MixinHelper7.getGson().fromJson(text, GameFailedParseEvent.class);
   }

   public String toJson() {
      return MixinHelper7.getGson().toJson(this);
   }

   static {
      field7.add("type");
      field7.add("data");
      field7.add("__failed_parse");
      field8.add("type");
      field8.add("data");
   }
}
