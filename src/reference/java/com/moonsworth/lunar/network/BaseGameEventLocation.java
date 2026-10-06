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

public class BaseGameEventLocation implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_TYPE = "type";
   @SerializedName("type")
   private String type;
   public static final String SERIALIZED_NAME_PUBLIC_SERVER = "public_server";
   @SerializedName("public_server")
   private BaseGameEventLocationPublicServer publicServer;
   public static final String SERIALIZED_NAME_HOSTED_WORLD = "hosted_world";
   @SerializedName("hosted_world")
   private BaseGameEventLocationHostedWorld hostedWorld;
   public static HashSet<String> field7 = new HashSet<>();
   public static HashSet<String> field8 = new HashSet<>();

   public BaseGameEventLocation() {
   }

   public BaseGameEventLocation type(String text1) {
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

   public BaseGameEventLocation publicServer(BaseGameEventLocationPublicServer mixinhelper131) {
      this.publicServer = mixinhelper131;
      return this;
   }

   @Nullable
   public BaseGameEventLocationPublicServer getPublicServer() {
      return this.publicServer;
   }

   public void setPublicServer(BaseGameEventLocationPublicServer mixinhelper131) {
      this.publicServer = mixinhelper131;
   }

   public BaseGameEventLocation hostedWorld(BaseGameEventLocationHostedWorld mixinhelper121) {
      this.hostedWorld = mixinhelper121;
      return this;
   }

   @Nullable
   public BaseGameEventLocationHostedWorld getHostedWorld() {
      return this.hostedWorld;
   }

   public void setHostedWorld(BaseGameEventLocationHostedWorld mixinhelper121) {
      this.hostedWorld = mixinhelper121;
   }

   @Override
   public boolean equals(Object obj1) {
      if (this == obj1) {
         return true;
      } else if (obj1 != null && this.getClass() == obj1.getClass()) {
         BaseGameEventLocation mixinhelper102 = (BaseGameEventLocation)obj1;
         return Objects.equals(this.type, mixinhelper102.type) && Objects.equals(this.publicServer, mixinhelper102.publicServer) && Objects.equals(this.hostedWorld, mixinhelper102.hostedWorld);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.type, this.publicServer, this.hostedWorld);
   }

   @Override
   public String toString() {
      StringBuilder builder1 = new StringBuilder();
      builder1.append("class BaseGameEventLocation {\n");
      builder1.append("    type: ").append(this.toIndentedString(this.type)).append("\n");
      builder1.append("    publicServer: ").append(this.toIndentedString(this.publicServer)).append("\n");
      builder1.append("    hostedWorld: ").append(this.toIndentedString(this.hostedWorld)).append("\n");
      builder1.append("}");
      return builder1.toString();
   }

   private String toIndentedString(Object obj1) {
      return obj1 == null ? "null" : obj1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement element0) {
      if (element0 == null && !field8.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in BaseGameEventLocation is not found in the empty JSON string", field8.toString())
         );
      }

      for (Entry entry3 : element0.getAsJsonObject().entrySet()) {
         if (!field7.contains(entry3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `BaseGameEventLocation` properties. JSON: %s", entry3.getKey(), element0.toString()
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

      if (json5.get("public_server") != null && !json5.get("public_server").isJsonNull()) {
         BaseGameEventLocationPublicServer.validateJsonElement(json5.get("public_server"));
      }

      if (json5.get("hosted_world") != null && !json5.get("hosted_world").isJsonNull()) {
         BaseGameEventLocationHostedWorld.validateJsonElement(json5.get("hosted_world"));
      }
   }

   public static BaseGameEventLocation method9(String text) {
      return (BaseGameEventLocation)MixinHelper7.getGson().fromJson(text, BaseGameEventLocation.class);
   }

   public String toJson() {
      return MixinHelper7.getGson().toJson(this);
   }

   static {
      field7.add("type");
      field7.add("public_server");
      field7.add("hosted_world");
      field8.add("type");
   }
}
