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

public class BaseGameEventInboundLocation implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_TYPE = "type";
   @SerializedName("type")
   private String type;
   public static final String SERIALIZED_NAME_SERVER_IP = "server_ip";
   @SerializedName("server_ip")
   private String serverIp;
   public static HashSet<String> field4 = new HashSet<>();
   public static HashSet<String> field5 = new HashSet<>();

   public BaseGameEventInboundLocation() {
   }

   public BaseGameEventInboundLocation type(String text1) {
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

   public BaseGameEventInboundLocation serverIp(String text1) {
      this.serverIp = text1;
      return this;
   }

   @Nullable
   public String getServerIp() {
      return this.serverIp;
   }

   public void setServerIp(String text1) {
      this.serverIp = text1;
   }

   @Override
   public boolean equals(Object obj1) {
      if (this == obj1) {
         return true;
      } else if (obj1 != null && this.getClass() == obj1.getClass()) {
         BaseGameEventInboundLocation mixinhelper92 = (BaseGameEventInboundLocation)obj1;
         return Objects.equals(this.type, mixinhelper92.type) && Objects.equals(this.serverIp, mixinhelper92.serverIp);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.type, this.serverIp);
   }

   @Override
   public String toString() {
      StringBuilder builder1 = new StringBuilder();
      builder1.append("class BaseGameEventInboundLocation {\n");
      builder1.append("    type: ").append(this.toIndentedString(this.type)).append("\n");
      builder1.append("    serverIp: ").append(this.toIndentedString(this.serverIp)).append("\n");
      builder1.append("}");
      return builder1.toString();
   }

   private String toIndentedString(Object obj1) {
      return obj1 == null ? "null" : obj1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement element0) {
      if (element0 == null && !field5.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in BaseGameEventInboundLocation is not found in the empty JSON string", field5.toString())
         );
      }

      for (Entry entry3 : element0.getAsJsonObject().entrySet()) {
         if (!field4.contains(entry3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `BaseGameEventInboundLocation` properties. JSON: %s", entry3.getKey(), element0.toString()
               )
            );
         }
      }

      for (String text6 : field5) {
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

      if (json5.get("server_ip") != null && !json5.get("server_ip").isJsonNull() && !json5.get("server_ip").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `server_ip` to be a primitive type in the JSON string but got `%s`", json5.get("server_ip").toString())
         );
      }
   }

   public static BaseGameEventInboundLocation method5(String text) {
      return (BaseGameEventInboundLocation)MixinHelper7.getGson().fromJson(text, BaseGameEventInboundLocation.class);
   }

   public String toJson() {
      return MixinHelper7.getGson().toJson(this);
   }

   static {
      field4.add("type");
      field4.add("server_ip");
      field5.add("type");
   }
}
