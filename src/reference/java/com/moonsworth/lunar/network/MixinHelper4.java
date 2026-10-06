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

public class MixinHelper4 implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_TYPE = "type";
   @SerializedName("type")
   private String type;
   public static final String SERIALIZED_NAME_DATA = "data";
   @SerializedName("data")
   private MixinHelper15 data;
   public static final String SERIALIZED_NAME__FAILED_PARSE = "__failed_parse";
   @SerializedName("__failed_parse")
   private Boolean FailedParse = true;
   public static HashSet<String> field7 = new HashSet<>();
   public static HashSet<String> field8 = new HashSet<>();

   public MixinHelper4 method1(String var1) {
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

   public MixinHelper4 method2(MixinHelper15 var1) {
      this.data = var1;
      return this;
   }

   @Nonnull
   public MixinHelper15 getData() {
      return this.data;
   }

   public void setData(MixinHelper15 var1) {
      this.data = var1;
   }

   public MixinHelper4 FailedParse(Boolean var1) {
      this.FailedParse = var1;
      return this;
   }

   @Nullable
   public Boolean getFailedParse() {
      return this.FailedParse;
   }

   public void setFailedParse(Boolean var1) {
      this.FailedParse = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         MixinHelper4 var2 = (MixinHelper4)var1;
         return Objects.equals(this.type, var2.type) && Objects.equals(this.data, var2.data) && Objects.equals(this.FailedParse, var2.FailedParse);
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
      StringBuilder var1 = new StringBuilder();
      var1.append("class GameFailedParseEvent {\n");
      var1.append("    type: ").append(this.toIndentedString(this.type)).append("\n");
      var1.append("    data: ").append(this.toIndentedString(this.data)).append("\n");
      var1.append("    failedParse: ").append(this.toIndentedString(this.FailedParse)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String toIndentedString(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement var0) {
      if (var0 == null && !field8.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameFailedParseEvent is not found in the empty JSON string", field8.toString())
         );
      }

      for (Entry var3 : var0.getAsJsonObject().entrySet()) {
         if (!field7.contains(var3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `GameFailedParseEvent` properties. JSON: %s", var3.getKey(), var0.toString()
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
   }

   public static MixinHelper4 method9(String var0) {
      return (MixinHelper4)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, MixinHelper4.class);
   }

   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field7.add("type");
      field7.add("data");
      field7.add("__failed_parse");
      field8.add("type");
      field8.add("data");
   }

   public static class Data implements TypeAdapterFactory {
      public <T> TypeAdapter<T> create(Gson var1, TypeToken<T> var2) {
         if (!MixinHelper4.class.isAssignableFrom(var2.getRawType())) {
            return null;
         }

         final TypeAdapter var3 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var4 = var1.getDelegateAdapter(this, TypeToken.get(MixinHelper4.class));
         return (new TypeAdapter<MixinHelper4>() {
            public void method1(JsonWriter var1, MixinHelper4 var2x) {
               JsonObject var3x = var4.toJsonTree(var2x).getAsJsonObject();
               var3.write(var1, var3x);
            }

            public MixinHelper4 method2(JsonReader var1) {
               JsonElement var2x = (JsonElement)var3.read(var1);
               return (MixinHelper4)var4.fromJsonTree(var2x);
            }
         }).nullSafe();
      }
   }
}
