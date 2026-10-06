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
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MixinHelper10 implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_TYPE = "type";
   @SerializedName("type")
   private String type;
   public static final String SERIALIZED_NAME_PUBLIC_SERVER = "public_server";
   @SerializedName("public_server")
   private MixinHelper13 publicServer;
   public static final String SERIALIZED_NAME_HOSTED_WORLD = "hosted_world";
   @SerializedName("hosted_world")
   private MixinHelper12 hostedWorld;
   public static HashSet<String> field7 = new HashSet<>();
   public static HashSet<String> field8 = new HashSet<>();

   public MixinHelper10 method1(String var1) {
      this.type = var1;
      return this;
   }

   @Nonnull
   public String getType() {
      return this.type;
   }

   public void setType(String var1) {
      this.type = var1;
   }

   public MixinHelper10 method2(MixinHelper13 var1) {
      this.publicServer = var1;
      return this;
   }

   @Nullable
   public MixinHelper13 getPublicServer() {
      return this.publicServer;
   }

   public void setPublicServer(MixinHelper13 var1) {
      this.publicServer = var1;
   }

   public MixinHelper10 hostedWorld(MixinHelper12 var1) {
      this.hostedWorld = var1;
      return this;
   }

   @Nullable
   public MixinHelper12 getHostedWorld() {
      return this.hostedWorld;
   }

   public void setHostedWorld(MixinHelper12 var1) {
      this.hostedWorld = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         MixinHelper10 var2 = (MixinHelper10)var1;
         return Objects.equals(this.type, var2.type) && Objects.equals(this.publicServer, var2.publicServer) && Objects.equals(this.hostedWorld, var2.hostedWorld);
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
      StringBuilder var1 = new StringBuilder();
      var1.append("class BaseGameEventLocation {\n");
      var1.append("    type: ").append(this.toIndentedString(this.type)).append("\n");
      var1.append("    publicServer: ").append(this.toIndentedString(this.publicServer)).append("\n");
      var1.append("    hostedWorld: ").append(this.toIndentedString(this.hostedWorld)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String toIndentedString(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement var0) {
      if (var0 == null && !field8.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in BaseGameEventLocation is not found in the empty JSON string", field8.toString())
         );
      }

      for (Entry var3 : var0.getAsJsonObject().entrySet()) {
         if (!field7.contains(var3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `BaseGameEventLocation` properties. JSON: %s", var3.getKey(), var0.toString()
               )
            );
         }
      }

      for (String var6 : field8) {
         if (var0.getAsJsonObject().get(var6) == null) {
            throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", var6, var0.toString()));
         }
      }

      JsonObject var5 = var0.getAsJsonObject();
      if (!var5.get("type").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `type` to be a primitive type in the JSON string but got `%s`", var5.get("type").toString())
         );
      }

      if (var5.get("public_server") != null && !var5.get("public_server").isJsonNull()) {
         MixinHelper13.validateJsonElement(var5.get("public_server"));
      }

      if (var5.get("hosted_world") != null && !var5.get("hosted_world").isJsonNull()) {
         MixinHelper12.validateJsonElement(var5.get("hosted_world"));
      }
   }

   public static MixinHelper10 method9(String var0) {
      return (MixinHelper10)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, MixinHelper10.class);
   }

   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field7.add("type");
      field7.add("public_server");
      field7.add("hosted_world");
      field8.add("type");
   }

   public static class Data implements TypeAdapterFactory {
      public <T> TypeAdapter<T> create(Gson var1, TypeToken<T> var2) {
         if (!MixinHelper10.class.isAssignableFrom(var2.getRawType())) {
            return null;
         }

         final TypeAdapter var3 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var4 = var1.getDelegateAdapter(this, TypeToken.get(MixinHelper10.class));
         return (new TypeAdapter<MixinHelper10>() {
            public void method1(JsonWriter var1, MixinHelper10 var2x) {
               JsonObject var3x = var4.toJsonTree(var2x).getAsJsonObject();
               var3.write(var1, var3x);
            }

            public MixinHelper10 method2(JsonReader var1) {
               JsonElement var2x = (JsonElement)var3.read(var1);
               MixinHelper10.validateJsonElement(var2x);
               return (MixinHelper10)var4.fromJsonTree(var2x);
            }
         }).nullSafe();
      }
   }
}
