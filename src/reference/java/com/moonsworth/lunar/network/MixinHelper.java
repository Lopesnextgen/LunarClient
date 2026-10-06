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
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Map.Entry;
import javax.annotation.Nullable;

public class MixinHelper implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_WIDTH = "width";
   @SerializedName("width")
   private BigDecimal width;
   public static final String SERIALIZED_NAME_HEIGHT = "height";
   @SerializedName("height")
   private BigDecimal height;
   public static HashSet<String> field6 = new HashSet<>();
   public static HashSet<String> field7 = new HashSet<>();

   public MixinHelper method1(BigDecimal var1) {
      this.width = var1;
      return this;
   }

   @Nullable
   public BigDecimal method2() {
      return this.width;
   }

   public void setWidth(BigDecimal var1) {
      this.width = var1;
   }

   public MixinHelper height(BigDecimal var1) {
      this.height = var1;
      return this;
   }

   @Nullable
   public BigDecimal getHeight() {
      return this.height;
   }

   public void setHeight(BigDecimal var1) {
      this.height = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         MixinHelper var2 = (MixinHelper)var1;
         return Objects.equals(this.width, var2.width) && Objects.equals(this.height, var2.height);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.width, this.height);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("class GameRewindExportResolution {\n");
      var1.append("    width: ").append(this.toIndentedString(this.width)).append("\n");
      var1.append("    height: ").append(this.toIndentedString(this.height)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String toIndentedString(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement var0) {
      if (var0 == null && !field7.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameRewindExportResolution is not found in the empty JSON string", field7.toString())
         );
      }

      for (Entry var3 : var0.getAsJsonObject().entrySet()) {
         if (!field6.contains(var3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `GameRewindExportResolution` properties. JSON: %s", var3.getKey(), var0.toString()
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
   }

   public static MixinHelper method8(String var0) {
      return (MixinHelper)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, MixinHelper.class);
   }

   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field6.add("width");
      field6.add("height");
      field7.add("width");
      field7.add("height");
   }

   public static class Data implements TypeAdapterFactory {
      public <T> TypeAdapter<T> create(Gson var1, TypeToken<T> var2) {
         if (!MixinHelper.class.isAssignableFrom(var2.getRawType())) {
            return null;
         }

         final TypeAdapter var3 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var4 = var1.getDelegateAdapter(this, TypeToken.get(MixinHelper.class));
         return (new TypeAdapter<MixinHelper>() {
            public void method1(JsonWriter var1, MixinHelper var2x) {
               JsonObject var3x = var4.toJsonTree(var2x).getAsJsonObject();
               var3.write(var1, var3x);
            }

            public MixinHelper method2(JsonReader var1) {
               JsonElement var2x = (JsonElement)var3.read(var1);
               return (MixinHelper)var4.fromJsonTree(var2x);
            }
         }).nullSafe();
      }
   }
}
