package com.moonsworth.lunar.network;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Map.Entry;
import javax.annotation.Nonnull;

public class NetworkIterator implements Network, Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_TYPE = "type";
   @SerializedName("type")
   private NetworkIterator.Type type = NetworkIterator.Type.BLOGPOST_INTERACTION;
   public static final String SERIALIZED_NAME_DATA = "data";
   @SerializedName("data")
   private GameBlogPostInteractionEventData data;
   public static HashSet<String> field6 = new HashSet<>();
   public static HashSet<String> field7 = new HashSet<>();

   public NetworkIterator method1(NetworkIterator.Type var1) {
      this.type = var1;
      return this;
   }

   @Nonnull
   public NetworkIterator.Type method2() {
      return this.type;
   }

   public void setType(NetworkIterator.Type var1) {
      this.type = var1;
   }

   public NetworkIterator data(GameBlogPostInteractionEventData var1) {
      this.data = var1;
      return this;
   }

   @Nonnull
   public GameBlogPostInteractionEventData getData() {
      return this.data;
   }

   public void setData(GameBlogPostInteractionEventData var1) {
      this.data = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         NetworkIterator var2 = (NetworkIterator)var1;
         return Objects.equals(this.type, var2.type) && Objects.equals(this.data, var2.data);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.type, this.data);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("class GameBlogPostInteractionEvent {\n");
      var1.append("    type: ").append(this.toIndentedString(this.type)).append("\n");
      var1.append("    data: ").append(this.toIndentedString(this.data)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String toIndentedString(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement var0) {
      if (var0 == null && !field7.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameBlogPostInteractionEvent is not found in the empty JSON string", field7.toString())
         );
      }

      for (Entry var3 : var0.getAsJsonObject().entrySet()) {
         if (!field6.contains(var3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `GameBlogPostInteractionEvent` properties. JSON: %s", var3.getKey(), var0.toString()
               )
            );
         }
      }

      for (String var6 : field7) {
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

      NetworkIterator.Type.validateJsonElement(var5.get("type"));
      GameBlogPostInteractionEventData.validateJsonElement(var5.get("data"));
   }

   public static NetworkIterator method8(String var0) {
      return (NetworkIterator)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, NetworkIterator.class);
   }

   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   @Override
   public MixinHelper2 method1() {
      return this.getData();
   }

   static {
      field6.add("type");
      field6.add("data");
      field7.add("type");
      field7.add("data");
   }

   public static class Data implements TypeAdapterFactory {
      public <T> TypeAdapter<T> create(Gson var1, TypeToken<T> var2) {
         if (!NetworkIterator.class.isAssignableFrom(var2.getRawType())) {
            return null;
         }

         final TypeAdapter var3 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var4 = var1.getDelegateAdapter(this, TypeToken.get(NetworkIterator.class));
         return (new TypeAdapter<NetworkIterator>() {
            public void method1(JsonWriter var1, NetworkIterator var2x) {
               JsonObject var3x = var4.toJsonTree(var2x).getAsJsonObject();
               var3.write(var1, var3x);
            }

            public NetworkIterator method2(JsonReader var1) {
               JsonElement var2x = (JsonElement)var3.read(var1);
               return (NetworkIterator)var4.fromJsonTree(var2x);
            }
         }).nullSafe();
      }
   }

   @JsonAdapter(NetworkIterator.Type.Data.class)
   public enum Type {
      BLOGPOST_INTERACTION("blogpost.interaction");

      private String value;

      Type(String var3) {
         this.value = var3;
      }

      public String getValue() {
         return this.value;
      }

      @Override
      public String toString() {
         return String.valueOf(this.value);
      }

      public static NetworkIterator.Type fromValue(String var0) {
         for (NetworkIterator.Type var4 : values()) {
            if (var4.value.equals(var0)) {
               return var4;
            }
         }

         throw new IllegalArgumentException("Unexpected value '" + var0 + "'");
      }

      public static void validateJsonElement(JsonElement var0) {
         String var1 = var0.getAsString();
         fromValue(var1);
      }

      public static class Data extends TypeAdapter<NetworkIterator.Type> {
         public void method1(JsonWriter var1, NetworkIterator.Type var2) {
            var1.value(var2.getValue());
         }

         public NetworkIterator.Type method2(JsonReader var1) {
            String var2 = var1.nextString();
            return NetworkIterator.Type.fromValue(var2);
         }
      }
   }
}
