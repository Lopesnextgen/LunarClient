package com.moonsworth.lunar.network;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nonnull;

public class GameFailedParseEventData implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_TIMESTAMP = "timestamp";
   @SerializedName("timestamp")
   private OffsetDateTime timestamp;
   private Map<String, Object> field4;
   public static HashSet<String> field5 = new HashSet<>();
   public static HashSet<String> field6 = new HashSet<>();

   public GameFailedParseEventData() {
   }

   public GameFailedParseEventData timestamp(OffsetDateTime offsetdatetime1) {
      this.timestamp = offsetdatetime1;
      return this;
   }

   @Nonnull
   public OffsetDateTime getTimestamp() {
      return this.timestamp;
   }

   public void setTimestamp(OffsetDateTime offsetdatetime1) {
      this.timestamp = offsetdatetime1;
   }

   public GameFailedParseEventData method4(String text1, Object object) {
      if (this.field4 == null) {
         this.field4 = new HashMap<>();
      }

      this.field4.put(text1, object);
      return this;
   }

   public Map<String, Object> method5() {
      return this.field4;
   }

   public Object method6(String text1) {
      return this.field4 == null ? null : this.field4.get(text1);
   }

   @Override
   public boolean equals(Object obj1) {
      if (this == obj1) {
         return true;
      } else if (obj1 != null && this.getClass() == obj1.getClass()) {
         GameFailedParseEventData mixinhelper152 = (GameFailedParseEventData)obj1;
         return Objects.equals(this.timestamp, mixinhelper152.timestamp) && Objects.equals(this.field4, mixinhelper152.field4);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.timestamp, this.field4);
   }

   @Override
   public String toString() {
      StringBuilder builder1 = new StringBuilder();
      builder1.append("class GameFailedParseEventData {\n");
      builder1.append("    timestamp: ").append(this.toIndentedString(this.timestamp)).append("\n");
      builder1.append("    additionalProperties: ").append(this.toIndentedString(this.field4)).append("\n");
      builder1.append("}");
      return builder1.toString();
   }

   private String toIndentedString(Object obj1) {
      return obj1 == null ? "null" : obj1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement element0) {
      if (element0 == null && !field6.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameFailedParseEventData is not found in the empty JSON string", field6.toString())
         );
      }

      for (String text2 : field6) {
         if (element0.getAsJsonObject().get(text2) == null) {
            throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", text2, element0.toString()));
         }
      }

      JsonObject json3 = element0.getAsJsonObject();
   }

   public static GameFailedParseEventData method8(String text) {
      return (GameFailedParseEventData)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(text, GameFailedParseEventData.class);
   }

   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field5.add("timestamp");
      field6.add("timestamp");
   }
}
