package com.moonsworth.lunar.client.util.io;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import lombok.Generated;
import org.apache.commons.lang3.Range;
import com.moonsworth.lunar.client.util.Annotation7;

public class JsonAdapters {
   public static class Data implements JsonSerializer<Map<?, ?>> {
      public JsonElement method1(Map<?, ?> var1, Type var2, JsonSerializationContext var3) {
         if (var1 != null && !var1.isEmpty()) {
            JsonObject var4 = new JsonObject();

            for (Entry var6 : var1.entrySet()) {
               var4.add(var6.getKey().toString(), var3.serialize(var6.getValue()));
            }

            return var4;
         } else {
            return null;
         }
      }
   }

   public static class ListSerializer implements JsonSerializer<List<?>> {
      public JsonElement method1(List<?> var1, Type var2, JsonSerializationContext var3) {
         if (var1 != null && !var1.isEmpty()) {
            JsonArray var4 = new JsonArray();

            for (Object var6 : var1) {
               JsonElement var7 = var3.serialize(var6);
               var4.add(var7);
            }

            return var4;
         } else {
            return null;
         }
      }
   }

   public static class SetSerializer implements JsonSerializer<Set<?>> {
      public JsonElement method1(Set<?> var1, Type var2, JsonSerializationContext var3) {
         if (var1 != null && !var1.isEmpty()) {
            JsonArray var4 = new JsonArray();

            for (Object var6 : var1) {
               JsonElement var7 = var3.serialize(var6);
               var4.add(var7);
            }

            return var4;
         } else {
            return null;
         }
      }
   }

   public static class StringSerializer implements JsonSerializer<String> {
      public JsonElement method1(String var1, Type var2, JsonSerializationContext var3) {
         return var1 != null && !var1.isEmpty() ? new JsonPrimitive(var1) : null;
      }
   }

   public static class SerializedNameExclusionStrategy implements ExclusionStrategy {
      public boolean shouldSkipClass(Class<?> var1) {
         return false;
      }

      public boolean shouldSkipField(FieldAttributes var1) {
         for (java.lang.annotation.Annotation var5 : var1.getDeclaringClass().getAnnotations()) {
            if (var5 instanceof Annotation7) {
               return var1.getAnnotations().stream().noneMatch(var0 -> var0 instanceof SerializedName);
            }
         }

         return false;
      }
   }

   public static class IntegerRangeDeserializer implements JsonDeserializer<Range<Integer>> {
      public Range<Integer> method1(JsonElement var1, Type var2, JsonDeserializationContext var3) {
         String var4 = var1.getAsString();
         var4 = var4.substring(1, var4.length() - 1);
         String[] var5 = var4.split("\\.\\.");
         if (var5.length != 2) {
            throw new JsonParseException("Invalid range format: " + var4);
         }

         int var6 = Integer.parseInt(var5[0]);
         int var7 = Integer.parseInt(var5[1]);
         return Range.between(var6, var7);
      }
   }
}
