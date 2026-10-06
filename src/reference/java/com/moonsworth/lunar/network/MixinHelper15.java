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
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import javax.annotation.Nonnull;

public class MixinHelper15 implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_TIMESTAMP = "timestamp";
   @SerializedName("timestamp")
   private OffsetDateTime timestamp;
   private Map<String, Object> field4;
   public static HashSet<String> field5 = new HashSet<>();
   public static HashSet<String> field6 = new HashSet<>();

   public MixinHelper15 method1(OffsetDateTime var1) {
      this.timestamp = var1;
      return this;
   }

   @Nonnull
   public OffsetDateTime method2() {
      return this.timestamp;
   }

   public void setTimestamp(OffsetDateTime var1) {
      this.timestamp = var1;
   }

   public MixinHelper15 method4(String var1, Object var2) {
      if (this.field4 == null) {
         this.field4 = new HashMap<>();
      }

      this.field4.put(var1, var2);
      return this;
   }

   public Map<String, Object> method5() {
      return this.field4;
   }

   public Object method6(String var1) {
      return this.field4 == null ? null : this.field4.get(var1);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         MixinHelper15 var2 = (MixinHelper15)var1;
         return Objects.equals(this.timestamp, var2.timestamp) && Objects.equals(this.field4, var2.field4);
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
      StringBuilder var1 = new StringBuilder();
      var1.append("class GameFailedParseEventData {\n");
      var1.append("    timestamp: ").append(this.toIndentedString(this.timestamp)).append("\n");
      var1.append("    additionalProperties: ").append(this.toIndentedString(this.field4)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String toIndentedString(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement var0) {
      if (var0 == null && !field6.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameFailedParseEventData is not found in the empty JSON string", field6.toString())
         );
      }

      for (String var2 : field6) {
         if (var0.getAsJsonObject().get(var2) == null) {
            throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", var2, var0.toString()));
         }
      }

      JsonObject var3 = var0.getAsJsonObject();
   }

   public static MixinHelper15 method8(String var0) {
      return (MixinHelper15)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, MixinHelper15.class);
   }

   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field5.add("timestamp");
      field6.add("timestamp");
   }

   public static class Data implements TypeAdapterFactory {
      public <T> TypeAdapter<T> create(final Gson var1, TypeToken<T> var2) {
         if (!MixinHelper15.class.isAssignableFrom(var2.getRawType())) {
            return null;
         }

         final TypeAdapter var3 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var4 = var1.getDelegateAdapter(this, TypeToken.get(MixinHelper15.class));
         return (new TypeAdapter<MixinHelper15>() {
               public void method1(JsonWriter var1x, MixinHelper15 var2x) {
                  JsonObject var3x = var4.toJsonTree(var2x).getAsJsonObject();
                  var3x.remove("additionalProperties");
                  if (var2x.method5() != null) {
                     for (Entry var5 : var2x.method5().entrySet()) {
                        if (var5.getValue() instanceof String) {
                           var3x.addProperty((String)var5.getKey(), (String)var5.getValue());
                        } else if (var5.getValue() instanceof Number) {
                           var3x.addProperty((String)var5.getKey(), (Number)var5.getValue());
                        } else if (var5.getValue() instanceof Boolean) {
                           var3x.addProperty((String)var5.getKey(), (Boolean)var5.getValue());
                        } else if (var5.getValue() instanceof Character) {
                           var3x.addProperty((String)var5.getKey(), (Character)var5.getValue());
                        } else {
                           JsonElement var6 = var1.toJsonTree(var5.getValue());
                           if (var6.isJsonArray()) {
                              var3x.add((String)var5.getKey(), var6.getAsJsonArray());
                           } else {
                              var3x.add((String)var5.getKey(), var6.getAsJsonObject());
                           }
                        }
                     }
                  }

                  var3.write(var1x, var3x);
               }

               public MixinHelper15 method2(JsonReader var1x) {
                  JsonElement var2x = (JsonElement)var3.read(var1x);
                  JsonObject var3x = var2x.getAsJsonObject();
                  MixinHelper15 var4x = (MixinHelper15)var4.fromJsonTree(var3x);

                  for (Entry var6 : var3x.entrySet()) {
                     if (!MixinHelper15.field5.contains(var6.getKey())) {
                        if (((JsonElement)var6.getValue()).isJsonPrimitive()) {
                           if (((JsonElement)var6.getValue()).getAsJsonPrimitive().isString()) {
                              var4x.method4((String)var6.getKey(), ((JsonElement)var6.getValue()).getAsString());
                           } else if (((JsonElement)var6.getValue()).getAsJsonPrimitive().isNumber()) {
                              var4x.method4((String)var6.getKey(), ((JsonElement)var6.getValue()).getAsNumber());
                           } else {
                              if (!((JsonElement)var6.getValue()).getAsJsonPrimitive().isBoolean()) {
                                 throw new IllegalArgumentException(
                                    String.format(
                                       "The field `%s` has unknown primitive type. Value: %s", var6.getKey(), ((JsonElement)var6.getValue()).toString()
                                    )
                                 );
                              }

                              var4x.method4((String)var6.getKey(), ((JsonElement)var6.getValue()).getAsBoolean());
                           }
                        } else if (((JsonElement)var6.getValue()).isJsonArray()) {
                           var4x.method4((String)var6.getKey(), var1.fromJson((JsonElement)var6.getValue(), List.class));
                        } else {
                           var4x.method4((String)var6.getKey(), var1.fromJson((JsonElement)var6.getValue(), HashMap.class));
                        }
                     }
                  }

                  return var4x;
               }
            })
            .nullSafe();
      }
   }
}
