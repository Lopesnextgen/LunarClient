package com.moonsworth.lunar.network;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.network.mixin.MixinHelper7;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Map.Entry;
import javax.annotation.Nullable;

public class BaseGameEventInstalledModsInner implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_ID = "id";
   @SerializedName("id")
   private String id;
   public static final String SERIALIZED_NAME_NAME = "name";
   @SerializedName("name")
   private String name;
   public static final String SERIALIZED_NAME_VERSION = "version";
   @SerializedName("version")
   private String version;
   public static final String SERIALIZED_NAME_TYPE = "type";
   @SerializedName("type")
   private String type;
   public static HashSet<String> field6 = new HashSet<>();
   public static HashSet<String> field7 = new HashSet<>();

   public BaseGameEventInstalledModsInner() {
   }

   public BaseGameEventInstalledModsInner id(String text1) {
      this.id = text1;
      return this;
   }

   @Nullable
   public String getId() {
      return this.id;
   }

   public void setId(String text1) {
      this.id = text1;
   }

   public BaseGameEventInstalledModsInner name(String text1) {
      this.name = text1;
      return this;
   }

   @Nullable
   public String getName() {
      return this.name;
   }

   public void setName(String text1) {
      this.name = text1;
   }

   public BaseGameEventInstalledModsInner version(String text1) {
      this.version = text1;
      return this;
   }

   @Nullable
   public String getVersion() {
      return this.version;
   }

   public void setVersion(String text1) {
      this.version = text1;
   }

   public BaseGameEventInstalledModsInner type(String text1) {
      this.type = text1;
      return this;
   }

   @Nullable
   public String getType() {
      return this.type;
   }

   public void setType(String text1) {
      this.type = text1;
   }

   @Override
   public boolean equals(Object obj1) {
      if (this == obj1) {
         return true;
      } else if (obj1 != null && this.getClass() == obj1.getClass()) {
         BaseGameEventInstalledModsInner mixinhelper142 = (BaseGameEventInstalledModsInner)obj1;
         return Objects.equals(this.id, mixinhelper142.id)
            && Objects.equals(this.name, mixinhelper142.name)
            && Objects.equals(this.version, mixinhelper142.version)
            && Objects.equals(this.type, mixinhelper142.type);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.id, this.name, this.version, this.type);
   }

   @Override
   public String toString() {
      StringBuilder builder1 = new StringBuilder();
      builder1.append("class BaseGameEventInstalledModsInner {\n");
      builder1.append("    id: ").append(this.toIndentedString(this.id)).append("\n");
      builder1.append("    name: ").append(this.toIndentedString(this.name)).append("\n");
      builder1.append("    version: ").append(this.toIndentedString(this.version)).append("\n");
      builder1.append("    type: ").append(this.toIndentedString(this.type)).append("\n");
      builder1.append("}");
      return builder1.toString();
   }

   private String toIndentedString(Object obj1) {
      return obj1 == null ? "null" : obj1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement element0) {
      if (element0 == null && !field7.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in BaseGameEventInstalledModsInner is not found in the empty JSON string", field7.toString())
         );
      }

      for (Entry entry3 : element0.getAsJsonObject().entrySet()) {
         if (!field6.contains(entry3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `BaseGameEventInstalledModsInner` properties. JSON: %s",
                  entry3.getKey(),
                  element0.toString()
               )
            );
         }
      }

      JsonObject json4 = element0.getAsJsonObject();
      if (json4.get("id") != null && !json4.get("id").isJsonNull() && !json4.get("id").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `id` to be a primitive type in the JSON string but got `%s`", json4.get("id").toString())
         );
      }

      if (json4.get("name") != null && !json4.get("name").isJsonNull() && !json4.get("name").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `name` to be a primitive type in the JSON string but got `%s`", json4.get("name").toString())
         );
      }

      if (json4.get("version") != null && !json4.get("version").isJsonNull() && !json4.get("version").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `version` to be a primitive type in the JSON string but got `%s`", json4.get("version").toString())
         );
      }

      if (json4.get("type") != null && !json4.get("type").isJsonNull() && !json4.get("type").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `type` to be a primitive type in the JSON string but got `%s`", json4.get("type").toString())
         );
      }
   }

   public static BaseGameEventInstalledModsInner method6(String text) {
      return (BaseGameEventInstalledModsInner)MixinHelper7.getGson().fromJson(text, BaseGameEventInstalledModsInner.class);
   }

   public String toJson() {
      return MixinHelper7.getGson().toJson(this);
   }

   static {
      field6.add("id");
      field6.add("name");
      field6.add("version");
      field6.add("type");
   }
}
