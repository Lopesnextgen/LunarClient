package com.moonsworth.lunar.network;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Map.Entry;
import javax.annotation.Nullable;

public class BaseGameEventLocationHostedWorld implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_HOST_UUID = "host_uuid";
   @SerializedName("host_uuid")
   private String hostUuid;
   public static final String SERIALIZED_NAME_HOST_USERNAME = "host_username";
   @SerializedName("host_username")
   private String hostUsername;
   public static HashSet<String> field6 = new HashSet<>();
   public static HashSet<String> field7 = new HashSet<>();

   public BaseGameEventLocationHostedWorld() {
   }

   public BaseGameEventLocationHostedWorld hostUuid(String text1) {
      this.hostUuid = text1;
      return this;
   }

   @Nullable
   public String getHostUuid() {
      return this.hostUuid;
   }

   public void setHostUuid(String text1) {
      this.hostUuid = text1;
   }

   public BaseGameEventLocationHostedWorld hostUsername(String text1) {
      this.hostUsername = text1;
      return this;
   }

   @Nullable
   public String getHostUsername() {
      return this.hostUsername;
   }

   public void setHostUsername(String text1) {
      this.hostUsername = text1;
   }

   @Override
   public boolean equals(Object obj1) {
      if (this == obj1) {
         return true;
      } else if (obj1 != null && this.getClass() == obj1.getClass()) {
         BaseGameEventLocationHostedWorld mixinhelper122 = (BaseGameEventLocationHostedWorld)obj1;
         return Objects.equals(this.hostUuid, mixinhelper122.hostUuid) && Objects.equals(this.hostUsername, mixinhelper122.hostUsername);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.hostUuid, this.hostUsername);
   }

   @Override
   public String toString() {
      StringBuilder builder1 = new StringBuilder();
      builder1.append("class BaseGameEventLocationHostedWorld {\n");
      builder1.append("    hostUuid: ").append(this.toIndentedString(this.hostUuid)).append("\n");
      builder1.append("    hostUsername: ").append(this.toIndentedString(this.hostUsername)).append("\n");
      builder1.append("}");
      return builder1.toString();
   }

   private String toIndentedString(Object obj1) {
      return obj1 == null ? "null" : obj1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement element0) {
      if (element0 == null && !field7.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in BaseGameEventLocationHostedWorld is not found in the empty JSON string", field7.toString())
         );
      }

      for (Entry entry3 : element0.getAsJsonObject().entrySet()) {
         if (!field6.contains(entry3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `BaseGameEventLocationHostedWorld` properties. JSON: %s",
                  entry3.getKey(),
                  element0.toString()
               )
            );
         }
      }

      JsonObject json4 = element0.getAsJsonObject();
      if (json4.get("host_uuid") != null && !json4.get("host_uuid").isJsonNull() && !json4.get("host_uuid").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `host_uuid` to be a primitive type in the JSON string but got `%s`", json4.get("host_uuid").toString())
         );
      }

      if (json4.get("host_username") != null && !json4.get("host_username").isJsonNull() && !json4.get("host_username").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `host_username` to be a primitive type in the JSON string but got `%s`", json4.get("host_username").toString())
         );
      }
   }

   public static BaseGameEventLocationHostedWorld method8(String text) {
      return (BaseGameEventLocationHostedWorld)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(text, BaseGameEventLocationHostedWorld.class);
   }

   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field6.add("host_uuid");
      field6.add("host_username");
   }
}
