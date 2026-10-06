package com.moonsworth.lunar.network;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Map.Entry;
import javax.annotation.Nullable;

public class MixinHelper12 implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_HOST_UUID = "host_uuid";
   @SerializedName("host_uuid")
   private String hostUuid;
   public static final String SERIALIZED_NAME_HOST_USERNAME = "host_username";
   @SerializedName("host_username")
   private String hostUsername;
   public static HashSet<String> field6 = new HashSet<>();
   public static HashSet<String> field7 = new HashSet<>();

   public MixinHelper12 method1(String var1) {
      this.hostUuid = var1;
      return this;
   }

   @Nullable
   public String method2() {
      return this.hostUuid;
   }

   public void setHostUuid(String var1) {
      this.hostUuid = var1;
   }

   public MixinHelper12 hostUsername(String var1) {
      this.hostUsername = var1;
      return this;
   }

   @Nullable
   public String getHostUsername() {
      return this.hostUsername;
   }

   public void setHostUsername(String var1) {
      this.hostUsername = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         MixinHelper12 var2 = (MixinHelper12)var1;
         return Objects.equals(this.hostUuid, var2.hostUuid) && Objects.equals(this.hostUsername, var2.hostUsername);
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
      StringBuilder var1 = new StringBuilder();
      var1.append("class BaseGameEventLocationHostedWorld {\n");
      var1.append("    hostUuid: ").append(this.toIndentedString(this.hostUuid)).append("\n");
      var1.append("    hostUsername: ").append(this.toIndentedString(this.hostUsername)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String toIndentedString(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement var0) {
      if (var0 == null && !field7.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in BaseGameEventLocationHostedWorld is not found in the empty JSON string", field7.toString())
         );
      }

      for (Entry var3 : var0.getAsJsonObject().entrySet()) {
         if (!field6.contains(var3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `BaseGameEventLocationHostedWorld` properties. JSON: %s",
                  var3.getKey(),
                  var0.toString()
               )
            );
         }
      }

      JsonObject var4 = var0.getAsJsonObject();
      if (var4.get("host_uuid") != null && !var4.get("host_uuid").isJsonNull() && !var4.get("host_uuid").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `host_uuid` to be a primitive type in the JSON string but got `%s`", var4.get("host_uuid").toString())
         );
      }

      if (var4.get("host_username") != null && !var4.get("host_username").isJsonNull() && !var4.get("host_username").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `host_username` to be a primitive type in the JSON string but got `%s`", var4.get("host_username").toString())
         );
      }
   }

   public static MixinHelper12 method8(String var0) {
      return (MixinHelper12)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, MixinHelper12.class);
   }

   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field6.add("host_uuid");
      field6.add("host_username");
   }

   public static class Data implements TypeAdapterFactory {
      public <T> TypeAdapter<T> create(Gson var1, TypeToken<T> var2) {
         if (!MixinHelper12.class.isAssignableFrom(var2.getRawType())) {
            return null;
         }

         final TypeAdapter var3 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var4 = var1.getDelegateAdapter(this, TypeToken.get(MixinHelper12.class));
         return (new TypeAdapter<MixinHelper12>() {
            public void method1(JsonWriter var1, MixinHelper12 var2x) {
               JsonObject var3x = var4.toJsonTree(var2x).getAsJsonObject();
               var3.write(var1, var3x);
            }

            public MixinHelper12 method2(JsonReader var1) {
               JsonElement var2x = (JsonElement)var3.read(var1);
               MixinHelper12.validateJsonElement(var2x);
               return (MixinHelper12)var4.fromJsonTree(var2x);
            }
         }).nullSafe();
      }
   }
}
