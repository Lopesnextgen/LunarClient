package com.moonsworth.lunar.client.util.io;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.function.BiFunction;
import java.util.function.Function;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.jspecify.annotations.NonNull;

public final class ThreadModuleDump9 {
   public static boolean getBoolean(JsonObject var0, String var1, boolean var2) {
      JsonElement var3 = var0.get(var1);
      return var3 != null && !var3.isJsonNull() ? var3.getAsBoolean() : var2;
   }

   public static byte getByte(JsonObject var0, String var1, byte var2) {
      JsonElement var3 = var0.get(var1);
      return var3 != null && !var3.isJsonNull() ? var3.getAsByte() : var2;
   }

   public static short getShort(JsonObject var0, String var1, short var2) {
      JsonElement var3 = var0.get(var1);
      return var3 != null && !var3.isJsonNull() ? var3.getAsShort() : var2;
   }

   public static int getInt(JsonObject var0, String var1, int var2) {
      JsonElement var3 = var0.get(var1);
      return var3 != null && !var3.isJsonNull() ? var3.getAsInt() : var2;
   }

   public static long getLong(JsonObject var0, String var1, long var2) {
      JsonElement var4 = var0.get(var1);
      return var4 != null && !var4.isJsonNull() ? var4.getAsLong() : var2;
   }

   public static float getFloat(JsonObject var0, String var1, float var2) {
      JsonElement var3 = var0.get(var1);
      return var3 != null && !var3.isJsonNull() ? var3.getAsFloat() : var2;
   }

   public static double getDouble(JsonObject var0, String var1, double var2) {
      JsonElement var4 = var0.get(var1);
      return var4 != null && !var4.isJsonNull() ? var4.getAsDouble() : var2;
   }

   public static Number getNumber(JsonObject var0, String var1, Number var2) {
      JsonElement var3 = var0.get(var1);
      return var3 != null && !var3.isJsonNull() ? var3.getAsNumber() : var2;
   }

   public static String getString(JsonObject var0, String var1, String var2) {
      JsonElement var3 = var0.get(var1);
      return var3 != null && !var3.isJsonNull() ? var3.getAsString() : var2;
   }

   public static <E extends Enum<E>> E getEnum(JsonObject var0, String var1, E var2) {
      String var3 = getString(var0, var1, "");

      for (Enum var7 : (Enum[])var2.getDeclaringClass().getEnumConstants()) {
         if (var7.name().equalsIgnoreCase(var3)) {
            return (E)var7;
         }
      }

      return (E)var2;
   }

   public static JsonObject getJsonObject(JsonObject var0, String var1, JsonObject var2) {
      JsonElement var3 = var0.get(var1);
      return var3 != null && !var3.isJsonNull() ? var3.getAsJsonObject() : var2;
   }

   public static JsonArray getJsonArray(JsonObject var0, String var1, JsonArray var2) {
      JsonElement var3 = var0.get(var1);
      return var3 != null && !var3.isJsonNull() ? var3.getAsJsonArray() : var2;
   }

   public static boolean getBoolean(JsonObject var0, boolean var1, String... var2) {
      return getByPath(var0, var1x -> var1x == null ? var1 : var1x.getAsBoolean(), var2);
   }

   public static byte getByte(JsonObject var0, byte var1, String... var2) {
      return getByPath(var0, var1x -> var1x == null ? var1 : var1x.getAsByte(), var2);
   }

   public static short getShort(JsonObject var0, short var1, String... var2) {
      return getByPath(var0, var1x -> var1x == null ? var1 : var1x.getAsShort(), var2);
   }

   public static int getInt(JsonObject var0, int var1, String... var2) {
      return getByPath(var0, var1x -> var1x == null ? var1 : var1x.getAsInt(), var2);
   }

   public static long getLong(JsonObject var0, long var1, String... var3) {
      return getByPath(var0, var2 -> var2 == null ? var1 : var2.getAsLong(), var3);
   }

   public static float getFloat(JsonObject var0, float var1, String... var2) {
      return getByPath(var0, var1x -> var1x == null ? var1 : var1x.getAsFloat(), var2);
   }

   public static double getDouble(JsonObject var0, double var1, String... var3) {
      return getByPath(var0, var2 -> var2 == null ? var1 : var2.getAsDouble(), var3);
   }

   public static Number getNumber(JsonObject var0, Number var1, String... var2) {
      return getByPath(var0, var1x -> var1x == null ? var1 : var1x.getAsNumber(), var2);
   }

   public static String getString(JsonObject var0, String var1, String... var2) {
      return getByPath(var0, var1x -> var1x == null ? var1 : var1x.getAsString(), var2);
   }

   public static JsonObject getJsonObject(JsonObject var0, JsonObject var1, String... var2) {
      for (String var6 : var2) {
         JsonElement var7 = var0.get(var6);
         if (var7 == null || var7.isJsonNull()) {
            return var1;
         }

         var0 = var7.getAsJsonObject();
      }

      return var0;
   }

   public static JsonArray getJsonArray(JsonObject var0, JsonArray var1, String... var2) {
      return getByPath(var0, var1x -> var1x == null ? var1 : var1x.getAsJsonArray(), var2);
   }

   public static <T> @NonNull T getByPath(JsonObject var0, Function<@Nullable JsonElement, @NonNull T> var1, String... var2) {
      int var3 = var2.length - 1;

      for (int var4 = 0; var4 < var3; var4++) {
         String var5 = var2[var4];
         JsonElement var6 = var0.get(var5);
         if (var6 == null || var6.isJsonNull()) {
            return (T)var1.apply(null);
         }

         var0 = var6.getAsJsonObject();
      }

      return (T)var1.apply(var0.get(var2[var3]));
   }

   public static boolean getBooleanStrict(JsonObject var0, String var1, boolean var2) {
      return var0.get(var1) instanceof JsonPrimitive var3 && var3.isBoolean() ? var3.getAsBoolean() : var2;
   }

   public static byte getByteStrict(JsonObject var0, String var1, byte var2) {
      return var0.get(var1) instanceof JsonPrimitive var3 && var3.isNumber() ? var3.getAsByte() : var2;
   }

   public static short getShortStrict(JsonObject var0, String var1, short var2) {
      return var0.get(var1) instanceof JsonPrimitive var3 && var3.isNumber() ? var3.getAsShort() : var2;
   }

   public static int getIntStrict(JsonObject var0, String var1, int var2) {
      return var0.get(var1) instanceof JsonPrimitive var3 && var3.isNumber() ? var3.getAsInt() : var2;
   }

   public static long getLongStrict(JsonObject var0, String var1, long var2) {
      return var0.get(var1) instanceof JsonPrimitive var4 && var4.isNumber() ? var4.getAsLong() : var2;
   }

   public static float getFloatStrict(JsonObject var0, String var1, float var2) {
      return var0.get(var1) instanceof JsonPrimitive var3 && var3.isNumber() ? var3.getAsFloat() : var2;
   }

   public static double getDoubleStrict(JsonObject var0, String var1, double var2) {
      return var0.get(var1) instanceof JsonPrimitive var4 && var4.isNumber() ? var4.getAsDouble() : var2;
   }

   public static Number getNumberStrict(JsonObject var0, String var1, Number var2) {
      return var0.get(var1) instanceof JsonPrimitive var3 && var3.isNumber() ? var3.getAsNumber() : var2;
   }

   public static String getStringStrict(JsonObject var0, String var1, String var2) {
      return var0.get(var1) instanceof JsonPrimitive var3 && var3.isString() ? var3.getAsString() : var2;
   }

   public static JsonObject getJsonObjectStrict(JsonObject var0, String var1, JsonObject var2) {
      return var0.get(var1) instanceof JsonObject var3 ? var3 : var2;
   }

   public static JsonArray getJsonArrayStrict(JsonObject var0, String var1, JsonArray var2) {
      return var0.get(var1) instanceof JsonArray var3 ? var3 : var2;
   }

   public static boolean getBooleanStrict(JsonObject var0, boolean var1, String... var2) {
      return getByPathStrict(var0, var1x -> var1x instanceof JsonPrimitive var2x && var2x.isBoolean() ? var2x.getAsBoolean() : var1, var2);
   }

   public static byte getByteStrict(JsonObject var0, byte var1, String... var2) {
      return getByPathStrict(var0, var1x -> var1x instanceof JsonPrimitive var2x && var2x.isNumber() ? var2x.getAsByte() : var1, var2);
   }

   public static short getShortStrict(JsonObject var0, short var1, String... var2) {
      return getByPathStrict(var0, var1x -> var1x instanceof JsonPrimitive var2x && var2x.isNumber() ? var2x.getAsShort() : var1, var2);
   }

   public static int getIntStrict(JsonObject var0, int var1, String... var2) {
      return getByPathStrict(var0, var1x -> var1x instanceof JsonPrimitive var2x && var2x.isNumber() ? var2x.getAsInt() : var1, var2);
   }

   public static long getLongStrict(JsonObject var0, long var1, String... var3) {
      return getByPathStrict(var0, var2 -> var2 instanceof JsonPrimitive var3x && var3x.isNumber() ? var3x.getAsLong() : var1, var3);
   }

   public static float getFloatStrict(JsonObject var0, float var1, String... var2) {
      return getByPathStrict(var0, var1x -> var1x instanceof JsonPrimitive var2x && var2x.isNumber() ? var2x.getAsFloat() : var1, var2);
   }

   public static double getDoubleStrict(JsonObject var0, double var1, String... var3) {
      return getByPathStrict(var0, var2 -> var2 instanceof JsonPrimitive var3x && var3x.isNumber() ? var3x.getAsDouble() : var1, var3);
   }

   public static Number getNumberStrict(JsonObject var0, Number var1, String... var2) {
      return getByPathStrict(var0, var1x -> var1x instanceof JsonPrimitive var2x && var2x.isNumber() ? var2x.getAsNumber() : var1, var2);
   }

   public static String getStringStrict(JsonObject var0, String var1, String... var2) {
      return getByPathStrict(var0, var1x -> var1x instanceof JsonPrimitive var2x && var2x.isString() ? var2x.getAsString() : var1, var2);
   }

   public static JsonObject getJsonObjectStrict(JsonObject var0, JsonObject var1, String... var2) {
      for (String var6 : var2) {
         if (!(var0.get(var6) instanceof JsonObject var7)) {
            return var1;
         }

         var0 = var7;
      }

      return var0;
   }

   public static JsonArray getJsonArrayStrict(JsonObject var0, JsonArray var1, String... var2) {
      return getByPathStrict(var0, var1x -> var1x instanceof JsonArray var2x ? var2x : var1, var2);
   }

   public static <T> @NonNull T getByPathStrict(JsonObject var0, Function<@Nullable JsonElement, @NonNull T> var1, String... var2) {
      int var3 = var2.length - 1;

      for (int var4 = 0; var4 < var3; var4++) {
         String var5 = var2[var4];
         if (!(var0.get(var5) instanceof JsonObject var6)) {
            return (T)var1.apply(null);
         }

         var0 = var6;
      }

      return (T)var1.apply(var0.get(var2[var3]));
   }

   public static Optional<Boolean> findBoolean(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isBoolean() ? Optional.of(var2.getAsBoolean()) : Optional.empty();
   }

   public static Optional<Byte> findByte(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isNumber() ? Optional.of(var2.getAsByte()) : Optional.empty();
   }

   public static Optional<Short> findShort(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isNumber() ? Optional.of(var2.getAsShort()) : Optional.empty();
   }

   public static OptionalInt findInt(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isNumber() ? OptionalInt.of(var2.getAsInt()) : OptionalInt.empty();
   }

   public static OptionalLong findLong(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isNumber() ? OptionalLong.of(var2.getAsLong()) : OptionalLong.empty();
   }

   public static Optional<Float> findFloat(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isNumber() ? Optional.of(var2.getAsFloat()) : Optional.empty();
   }

   public static OptionalDouble findDouble(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isNumber() ? OptionalDouble.of(var2.getAsDouble()) : OptionalDouble.empty();
   }

   public static Optional<Number> findNumber(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isNumber() ? Optional.of(var2.getAsNumber()) : Optional.empty();
   }

   public static Optional<String> findString(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isString() ? Optional.of(var2.getAsString()) : Optional.empty();
   }

   public static Optional<JsonObject> findJsonObject(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonObject var2 ? Optional.of(var2) : Optional.empty();
   }

   public static Optional<JsonArray> findJsonArray(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonArray var2 ? Optional.of(var2) : Optional.empty();
   }

   public static Optional<Boolean> findBoolean(JsonObject var0, String... var1) {
      return findByPath(var0, var0x -> var0x instanceof JsonPrimitive var1x && var1x.isBoolean() ? Optional.of(var1x.getAsBoolean()) : Optional.empty(), var1);
   }

   public static Optional<Byte> findByte(JsonObject var0, String... var1) {
      return findByPath(var0, var0x -> var0x instanceof JsonPrimitive var1x && var1x.isNumber() ? Optional.of(var1x.getAsByte()) : Optional.empty(), var1);
   }

   public static Optional<Short> findShort(JsonObject var0, String... var1) {
      return findByPath(var0, var0x -> var0x instanceof JsonPrimitive var1x && var1x.isNumber() ? Optional.of(var1x.getAsShort()) : Optional.empty(), var1);
   }

   public static OptionalInt findInt(JsonObject var0, String... var1) {
      return findByPathOr(
         var0,
         OptionalInt.empty(),
         var0x -> var0x instanceof JsonPrimitive var1x && var1x.isNumber() ? OptionalInt.of(var1x.getAsInt()) : OptionalInt.empty(),
         var1
      );
   }

   public static OptionalLong findLong(JsonObject var0, String... var1) {
      return findByPathOr(
         var0,
         OptionalLong.empty(),
         var0x -> var0x instanceof JsonPrimitive var1x && var1x.isNumber() ? OptionalLong.of(var1x.getAsLong()) : OptionalLong.empty(),
         var1
      );
   }

   public static Optional<Float> findFloat(JsonObject var0, String... var1) {
      return findByPath(var0, var0x -> var0x instanceof JsonPrimitive var1x && var1x.isNumber() ? Optional.of(var1x.getAsFloat()) : Optional.empty(), var1);
   }

   public static OptionalDouble findDouble(JsonObject var0, String... var1) {
      return findByPathOr(
         var0,
         OptionalDouble.empty(),
         var0x -> var0x instanceof JsonPrimitive var1x && var1x.isNumber() ? OptionalDouble.of(var1x.getAsDouble()) : OptionalDouble.empty(),
         var1
      );
   }

   public static Optional<Number> findNumber(JsonObject var0, String... var1) {
      return findByPath(var0, var0x -> var0x instanceof JsonPrimitive var1x && var1x.isNumber() ? Optional.of(var1x.getAsNumber()) : Optional.empty(), var1);
   }

   public static Optional<String> findString(JsonObject var0, String... var1) {
      return findByPath(var0, var0x -> var0x instanceof JsonPrimitive var1x && var1x.isString() ? Optional.of(var1x.getAsString()) : Optional.empty(), var1);
   }

   public static Optional<JsonObject> findJsonObject(JsonObject var0, String... var1) {
      return findByPath(var0, var0x -> var0x instanceof JsonObject var1x ? Optional.of(var1x) : Optional.empty(), var1);
   }

   public static Optional<JsonArray> findJsonArray(JsonObject var0, String... var1) {
      return findByPath(var0, var0x -> var0x instanceof JsonArray var1x ? Optional.of(var1x) : Optional.empty(), var1);
   }

   public static <T> Optional<T> findByPath(JsonObject var0, Function<JsonElement, Optional<T>> var1, String... var2) {
      int var3 = var2.length - 1;

      for (int var4 = 0; var4 < var3; var4++) {
         String var5 = var2[var4];
         if (!(var0.get(var5) instanceof JsonObject var6)) {
            return Optional.empty();
         }

         var0 = var6;
      }

      return (Optional<T>)var1.apply(var0.get(var2[var3]));
   }

   private static <T> T findByPathOr(JsonObject var0, T var1, Function<JsonElement, T> var2, String... var3) {
      int var4 = var3.length - 1;

      for (int var5 = 0; var5 < var4; var5++) {
         String var6 = var3[var5];
         if (!(var0.get(var6) instanceof JsonObject var7)) {
            return (T)var1;
         }

         var0 = var7;
      }

      return (T)var2.apply(var0.get(var3[var4]));
   }

   public static JsonElement getRequiredElement(JsonObject var0, String var1) {
      JsonElement var2 = var0.get(var1);
      if (var2 == null) {
         throw new JSONException("Failed to find key \"" + var1 + "\" in: \"" + var0 + "\"");
      } else {
         return var2;
      }
   }

   public static boolean getRequiredBoolean(JsonObject var0, String var1) {
      if (getRequiredElement(var0, var1) instanceof JsonPrimitive var3 && var3.isBoolean()) {
         return var3.getAsBoolean();
      } else {
         throw new JSONException("Key \"" + var1 + "\" was the wrong type in: \"" + var0 + "\"");
      }
   }

   public static byte getRequiredByte(JsonObject var0, String var1) {
      if (getRequiredElement(var0, var1) instanceof JsonPrimitive var3 && var3.isNumber()) {
         return var3.getAsByte();
      } else {
         throw new JSONException("Key \"" + var1 + "\" was the wrong type in: \"" + var0 + "\"");
      }
   }

   public static short getRequiredShort(JsonObject var0, String var1) {
      if (getRequiredElement(var0, var1) instanceof JsonPrimitive var3 && var3.isNumber()) {
         return var3.getAsShort();
      } else {
         throw new JSONException("Key \"" + var1 + "\" was the wrong type in: \"" + var0 + "\"");
      }
   }

   public static int getRequiredInt(JsonObject var0, String var1) {
      if (getRequiredElement(var0, var1) instanceof JsonPrimitive var3 && var3.isNumber()) {
         return var3.getAsInt();
      } else {
         throw new JSONException("Key \"" + var1 + "\" was the wrong type in: \"" + var0 + "\"");
      }
   }

   public static long getRequiredLong(JsonObject var0, String var1) {
      if (getRequiredElement(var0, var1) instanceof JsonPrimitive var3 && var3.isNumber()) {
         return var3.getAsLong();
      } else {
         throw new JSONException("Key \"" + var1 + "\" was the wrong type in: \"" + var0 + "\"");
      }
   }

   public static float getRequiredFloat(JsonObject var0, String var1) {
      if (getRequiredElement(var0, var1) instanceof JsonPrimitive var3 && var3.isNumber()) {
         return var3.getAsFloat();
      } else {
         throw new JSONException("Key \"" + var1 + "\" was the wrong type in: \"" + var0 + "\"");
      }
   }

   public static double getRequiredDouble(JsonObject var0, String var1) {
      if (getRequiredElement(var0, var1) instanceof JsonPrimitive var3 && var3.isNumber()) {
         return var3.getAsDouble();
      } else {
         throw new JSONException("Key \"" + var1 + "\" was the wrong type in: \"" + var0 + "\"");
      }
   }

   public static String getRequiredString(JsonObject var0, String var1) {
      if (getRequiredElement(var0, var1) instanceof JsonPrimitive var3 && var3.isString()) {
         return var3.getAsString();
      } else {
         throw new JSONException("Key \"" + var1 + "\" was the wrong type in: \"" + var0 + "\"");
      }
   }

   public static JsonObject getRequiredJsonObject(JsonObject var0, String var1) {
      if (getRequiredElement(var0, var1) instanceof JsonObject var3) {
         return var3;
      } else {
         throw new JSONException("Key \"" + var1 + "\" was the wrong type in: \"" + var0 + "\"");
      }
   }

   public static JsonArray getRequiredJsonArray(JsonObject var0, String var1) {
      if (getRequiredElement(var0, var1) instanceof JsonArray var3) {
         return var3;
      } else {
         throw new JSONException("Key \"" + var1 + "\" was the wrong type in: \"" + var0 + "\"");
      }
   }

   public static JsonElement getRequiredElement(JsonObject var0, String... var1) {
      return getRequiredByPath(var0, ThreadModuleDump9::getRequiredElement, var1);
   }

   public static boolean getRequiredBoolean(JsonObject var0, String... var1) {
      return getRequiredByPath(var0, ThreadModuleDump9::getRequiredBoolean, var1);
   }

   public static byte getRequiredByte(JsonObject var0, String... var1) {
      return getRequiredByPath(var0, ThreadModuleDump9::getRequiredByte, var1);
   }

   public static short getRequiredShort(JsonObject var0, String... var1) {
      return getRequiredByPath(var0, ThreadModuleDump9::getRequiredShort, var1);
   }

   public static int getRequiredInt(JsonObject var0, String... var1) {
      return getRequiredByPath(var0, ThreadModuleDump9::getRequiredInt, var1);
   }

   public static long getRequiredLong(JsonObject var0, String... var1) {
      return getRequiredByPath(var0, ThreadModuleDump9::getRequiredLong, var1);
   }

   public static float getRequiredFloat(JsonObject var0, String... var1) {
      return getRequiredByPath(var0, ThreadModuleDump9::getRequiredFloat, var1);
   }

   public static double getRequiredDouble(JsonObject var0, String... var1) {
      return getRequiredByPath(var0, ThreadModuleDump9::getRequiredDouble, var1);
   }

   public static String getRequiredString(JsonObject var0, String... var1) {
      return getRequiredByPath(var0, ThreadModuleDump9::getRequiredString, var1);
   }

   public static JsonObject getRequiredJsonObject(JsonObject var0, String... var1) {
      return getRequiredByPath(var0, ThreadModuleDump9::getRequiredJsonObject, var1);
   }

   public static JsonArray getRequiredJsonArray(JsonObject var0, String... var1) {
      return getRequiredByPath(var0, ThreadModuleDump9::getRequiredJsonArray, var1);
   }

   public static <T> @NonNull T getRequiredByPath(JsonObject var0, BiFunction<JsonObject, String, @NonNull T> var1, String... var2) {
      int var3 = var2.length - 1;

      for (int var4 = 0; var4 < var3; var4++) {
         String var5 = var2[var4];
         var0 = getRequiredJsonObject(var0, var5);
      }

      return (T)var1.apply(var0, var2[var3]);
   }

   public static <T> T[] mapArray(JsonArray var0, Function<JsonElement, T> var1) {
      List var2 = var0.asList();
      Object[] var3 = new Object[var2.size()];

      for (int var4 = 0; var4 < var2.size(); var4++) {
         var3[var4] = var1.apply((JsonElement)var2.get(var4));
      }

      return (T[])var3;
   }

   public static <T> T[] mapArray(JsonObject var0, String var1, Function<JsonElement, T> var2) {
      return (T[])(var0.get(var1) instanceof JsonArray var3 ? mapArray(var3, var2) : new Object[0]);
   }

   public static <T> T[] mapRequiredArray(JsonObject var0, String var1, Function<JsonElement, T> var2) {
      if (getRequiredElement(var0, var1) instanceof JsonArray var3) {
         return (T[])mapArray(var3, var2);
      } else {
         throw new JSONException("Key \"" + var1 + "\" isn't a array in: \"" + var0 + "\"");
      }
   }

   public static <T> List<T> mapList(JsonObject var0, String var1, Function<JsonElement, @Nullable T> var2) {
      if (var0.get(var1) instanceof JsonArray var3) {
         List var9 = var3.asList();
         ArrayList var5 = new ArrayList(var9.size());

         for (JsonElement var7 : var9) {
            Object var8 = var2.apply(var7);
            if (var8 != null) {
               var5.add(var8);
            }
         }

         return var5;
      } else {
         return List.of();
      }
   }

   public static <T> List<T> mapRequiredList(JsonObject var0, String var1, Function<JsonElement, @Nullable T> var2) {
      if (getRequiredElement(var0, var1) instanceof JsonArray var3) {
         List var9 = var3.asList();
         ArrayList var5 = new ArrayList(var9.size());

         for (JsonElement var7 : var9) {
            Object var8 = var2.apply(var7);
            if (var8 != null) {
               var5.add(var8);
            }
         }

         return var5;
      } else {
         throw new JSONException("Key \"" + var1 + "\" isn't a list in: \"" + var0 + "\"");
      }
   }

   public static Optional<JsonElement> findElement(JsonObject var0, String var1) {
      JsonElement var2 = var0.get(var1);
      return var2 != null && !var2.isJsonNull() ? Optional.of(var2) : Optional.empty();
   }

   public static <T> Optional<T> findMapped(JsonObject var0, String var1, Function<JsonElement, T> var2) {
      JsonElement var3 = var0.get(var1);
      return var3 != null && !var3.isJsonNull() ? Optional.of((T)var2.apply(var3)) : Optional.empty();
   }

   public static JsonElement getRequiredElement(JsonObject var0, String var1) {
      JsonElement var2 = var0.get(var1);
      if (var2 == null) {
         throw new JSONException("Failed to find key \"" + var1 + "\" in: \"" + var0 + "\"");
      } else if (var2.isJsonNull()) {
         throw new JSONException("Key \"" + var1 + "\" is null in: \"" + var0 + "\"");
      } else {
         return var2;
      }
   }

   public static <T> T getRequiredMapped(JsonObject var0, String var1, Function<JsonElement, T> var2) {
      return (T)var2.apply(getRequiredElement(var0, var1));
   }

   @Nullable
   public static JsonElement getNullableElement(JsonObject var0, String var1) {
      JsonElement var2 = var0.get(var1);
      return var2 != null && !var2.isJsonNull() ? var2 : null;
   }

   @Nullable
   public static <T> T getNullableMapped(JsonObject var0, String var1, Function<@NotNull JsonElement, T> var2) {
      JsonElement var3 = var0.get(var1);
      return (T)(var3 != null && !var3.isJsonNull() ? var2.apply(var3) : null);
   }

   public static boolean hasField(JsonObject var0, String var1) {
      JsonElement var2 = var0.get(var1);
      return var2 != null && !var2.isJsonNull();
   }

   public static boolean removeIfNull(JsonObject var0, String var1) {
      JsonElement var2 = var0.get(var1);
      if (var2 != null && var2.isJsonNull()) {
         var0.remove(var1);
         return true;
      } else {
         return false;
      }
   }

   @Nullable
   public static JsonElement removeElement(JsonObject var0, String var1) {
      JsonElement var2 = var0.get(var1);
      return var2 != null && !var2.isJsonNull() ? var0.remove(var1) : null;
   }

   @Nullable
   public static <T> T removeMapped(JsonObject var0, String var1, Function<@NotNull JsonElement, T> var2) {
      JsonElement var3 = var0.get(var1);
      return (T)(var3 != null && !var3.isJsonNull() ? var2.apply(var0.remove(var1)) : null);
   }

   public static Optional<Boolean> takeBoolean(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isBoolean() ? Optional.of(var0.remove(var1).getAsBoolean()) : Optional.empty();
   }

   public static Optional<Byte> takeByte(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isNumber() ? Optional.of(var0.remove(var1).getAsByte()) : Optional.empty();
   }

   public static Optional<Short> takeShort(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isNumber() ? Optional.of(var0.remove(var1).getAsShort()) : Optional.empty();
   }

   public static OptionalInt takeInt(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isNumber() ? OptionalInt.of(var0.remove(var1).getAsInt()) : OptionalInt.empty();
   }

   public static OptionalLong takeLong(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isNumber() ? OptionalLong.of(var0.remove(var1).getAsLong()) : OptionalLong.empty();
   }

   public static Optional<Float> takeFloat(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isNumber() ? Optional.of(var0.remove(var1).getAsFloat()) : Optional.empty();
   }

   public static OptionalDouble takeDouble(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isNumber() ? OptionalDouble.of(var0.remove(var1).getAsDouble()) : OptionalDouble.empty();
   }

   public static Optional<Number> takeNumber(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isNumber() ? Optional.of(var0.remove(var1).getAsNumber()) : Optional.empty();
   }

   public static Optional<String> takeString(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isString() ? Optional.of(var0.remove(var1).getAsString()) : Optional.empty();
   }

   public static Optional<JsonObject> takeJsonObject(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonObject ? Optional.of(var0.remove(var1).getAsJsonObject()) : Optional.empty();
   }

   public static Optional<JsonArray> takeJsonArray(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonArray ? Optional.of(var0.remove(var1).getAsJsonArray()) : Optional.empty();
   }

   @Nullable
   public static Boolean takeBooleanOrNull(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isBoolean() ? var0.remove(var1).getAsBoolean() : null;
   }

   @Nullable
   public static Byte takeByteOrNull(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isNumber() ? var0.remove(var1).getAsByte() : null;
   }

   @Nullable
   public static Short takeShortOrNull(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isNumber() ? var0.remove(var1).getAsShort() : null;
   }

   @Nullable
   public static Integer takeIntOrNull(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isNumber() ? var0.remove(var1).getAsInt() : null;
   }

   @Nullable
   public static Long takeLongOrNull(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isNumber() ? var0.remove(var1).getAsLong() : null;
   }

   @Nullable
   public static Float takeFloatOrNull(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isNumber() ? var0.remove(var1).getAsFloat() : null;
   }

   @Nullable
   public static Double takeDoubleOrNull(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isNumber() ? var0.remove(var1).getAsDouble() : null;
   }

   @Nullable
   public static Number takeNumberOrNull(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isNumber() ? var0.remove(var1).getAsNumber() : null;
   }

   @Nullable
   public static String takeStringOrNull(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonPrimitive var2 && var2.isString() ? var0.remove(var1).getAsString() : null;
   }

   @Nullable
   public static JsonObject takeJsonObjectOrNull(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonObject ? var0.remove(var1).getAsJsonObject() : null;
   }

   @Nullable
   public static JsonArray takeJsonArrayOrNull(JsonObject var0, String var1) {
      return var0.get(var1) instanceof JsonArray ? var0.remove(var1).getAsJsonArray() : null;
   }

   public static boolean takeBooleanOrDefault(JsonObject var0, String var1, boolean var2) {
      return var0.get(var1) instanceof JsonPrimitive var3 && var3.isBoolean() ? var0.remove(var1).getAsBoolean() : var2;
   }

   public static byte takeByteOrDefault(JsonObject var0, String var1, byte var2) {
      return var0.get(var1) instanceof JsonPrimitive var3 && var3.isNumber() ? var0.remove(var1).getAsByte() : var2;
   }

   public static short takeShortOrDefault(JsonObject var0, String var1, short var2) {
      return var0.get(var1) instanceof JsonPrimitive var3 && var3.isNumber() ? var0.remove(var1).getAsShort() : var2;
   }

   public static int takeIntOrDefault(JsonObject var0, String var1, int var2) {
      return var0.get(var1) instanceof JsonPrimitive var3 && var3.isNumber() ? var0.remove(var1).getAsInt() : var2;
   }

   public static long takeLongOrDefault(JsonObject var0, String var1, long var2) {
      return var0.get(var1) instanceof JsonPrimitive var4 && var4.isNumber() ? var0.remove(var1).getAsLong() : var2;
   }

   public static float takeFloatOrDefault(JsonObject var0, String var1, float var2) {
      return var0.get(var1) instanceof JsonPrimitive var3 && var3.isNumber() ? var0.remove(var1).getAsFloat() : var2;
   }

   public static double takeDoubleOrDefault(JsonObject var0, String var1, double var2) {
      return var0.get(var1) instanceof JsonPrimitive var4 && var4.isNumber() ? var0.remove(var1).getAsDouble() : var2;
   }

   public static Number takeNumberOrDefault(JsonObject var0, String var1, Number var2) {
      return var0.get(var1) instanceof JsonPrimitive var3 && var3.isNumber() ? var0.remove(var1).getAsNumber() : var2;
   }

   @NotNull
   public static String takeStringOrDefault(JsonObject var0, String var1, @NotNull String var2) {
      return var0.get(var1) instanceof JsonPrimitive var3 && var3.isString() ? var0.remove(var1).getAsString() : var2;
   }

   @NotNull
   public static JsonObject takeJsonObjectOrDefault(JsonObject var0, String var1, @NotNull JsonObject var2) {
      return var0.get(var1) instanceof JsonObject ? var0.remove(var1).getAsJsonObject() : var2;
   }

   @NotNull
   public static JsonArray takeJsonArrayOrDefault(JsonObject var0, String var1, @NotNull JsonArray var2) {
      return var0.get(var1) instanceof JsonArray ? var0.remove(var1).getAsJsonArray() : var2;
   }

   public static boolean renameKey(JsonObject var0, String var1, String var2) {
      if (!var1.equals(var2)) {
         JsonElement var3 = var0.get(var1);
         if (var3 != null && !var3.isJsonNull()) {
            var0.remove(var1);
            var0.add(var2, var3);
            return true;
         }
      }

      return false;
   }

   public static JsonElement getArrayElement(JsonArray var0, int var1, JsonElement var2) {
      JsonElement var3 = var0.get(var1);
      return var3 == null ? var2 : var3;
   }

   public static boolean getArrayBoolean(JsonArray var0, int var1, boolean var2) {
      return getArrayElement(var0, var1, (JsonElement)null) instanceof JsonPrimitive var4 && var4.isBoolean() ? var4.getAsBoolean() : var2;
   }

   public static byte getArrayByte(JsonArray var0, int var1, byte var2) {
      return getArrayElement(var0, var1, (JsonElement)null) instanceof JsonPrimitive var4 && var4.isNumber() ? var4.getAsByte() : var2;
   }

   public static short getArrayShort(JsonArray var0, int var1, short var2) {
      return getArrayElement(var0, var1, (JsonElement)null) instanceof JsonPrimitive var4 && var4.isNumber() ? var4.getAsShort() : var2;
   }

   public static int getArrayInt(JsonArray var0, int var1, int var2) {
      return getArrayElement(var0, var1, (JsonElement)null) instanceof JsonPrimitive var4 && var4.isNumber() ? var4.getAsInt() : var2;
   }

   public static float getArrayFloat(JsonArray var0, int var1, float var2) {
      return getArrayElement(var0, var1, (JsonElement)null) instanceof JsonPrimitive var4 && var4.isNumber() ? var4.getAsFloat() : var2;
   }

   public static double getArrayDouble(JsonArray var0, int var1, double var2) {
      return getArrayElement(var0, var1, (JsonElement)null) instanceof JsonPrimitive var5 && var5.isNumber() ? var5.getAsDouble() : var2;
   }

   public static String getArrayString(JsonArray var0, int var1, String var2) {
      return getArrayElement(var0, var1, (JsonElement)null) instanceof JsonPrimitive var4 && var4.isString() ? var4.getAsString() : var2;
   }

   public static JsonObject getArrayJsonObject(JsonArray var0, int var1, JsonObject var2) {
      return getArrayElement(var0, var1, (JsonElement)null) instanceof JsonObject var4 ? var4 : var2;
   }

   public static JsonArray getArrayJsonArray(JsonArray var0, int var1, JsonArray var2) {
      return getArrayElement(var0, var1, (JsonElement)null) instanceof JsonArray var4 ? var4 : var2;
   }

   public static JsonElement getRequiredArrayElement(JsonArray var0, int var1) {
      JsonElement var2 = var0.get(var1);
      if (var2 == null) {
         throw new JSONException("Failed to find index \"" + var1 + "\" in: \"" + var0 + "\"");
      } else {
         return var2;
      }
   }

   public static boolean getRequiredArrayBoolean(JsonArray var0, int var1) {
      if (getRequiredArrayElement(var0, var1) instanceof JsonPrimitive var3 && var3.isBoolean()) {
         return var3.getAsBoolean();
      } else {
         throw new JSONException("Index \"" + var1 + "\" was the wrong type in: \"" + var0 + "\"");
      }
   }

   public static byte getRequiredArrayByte(JsonArray var0, int var1) {
      if (getRequiredArrayElement(var0, var1) instanceof JsonPrimitive var3 && var3.isNumber()) {
         return var3.getAsByte();
      } else {
         throw new JSONException("Index \"" + var1 + "\" was the wrong type in: \"" + var0 + "\"");
      }
   }

   public static short getRequiredArrayShort(JsonArray var0, int var1) {
      if (getRequiredArrayElement(var0, var1) instanceof JsonPrimitive var3 && var3.isNumber()) {
         return var3.getAsShort();
      } else {
         throw new JSONException("Index \"" + var1 + "\" was the wrong type in: \"" + var0 + "\"");
      }
   }

   public static int getRequiredArrayInt(JsonArray var0, int var1) {
      if (getRequiredArrayElement(var0, var1) instanceof JsonPrimitive var3 && var3.isNumber()) {
         return var3.getAsInt();
      } else {
         throw new JSONException("Index \"" + var1 + "\" was the wrong type in: \"" + var0 + "\"");
      }
   }

   public static float getRequiredArrayFloat(JsonArray var0, int var1) {
      if (getRequiredArrayElement(var0, var1) instanceof JsonPrimitive var3 && var3.isNumber()) {
         return var3.getAsFloat();
      } else {
         throw new JSONException("Index \"" + var1 + "\" was the wrong type in: \"" + var0 + "\"");
      }
   }

   public static double getRequiredArrayDouble(JsonArray var0, int var1) {
      if (getRequiredArrayElement(var0, var1) instanceof JsonPrimitive var3 && var3.isNumber()) {
         return var3.getAsDouble();
      } else {
         throw new JSONException("Index \"" + var1 + "\" was the wrong type in: \"" + var0 + "\"");
      }
   }

   public static String getRequiredArrayString(JsonArray var0, int var1) {
      if (getRequiredArrayElement(var0, var1) instanceof JsonPrimitive var3 && var3.isString()) {
         return var3.getAsString();
      } else {
         throw new JSONException("Index \"" + var1 + "\" was the wrong type in: \"" + var0 + "\"");
      }
   }

   public static JsonObject getRequiredArrayJsonObject(JsonArray var0, int var1) {
      if (getRequiredArrayElement(var0, var1) instanceof JsonObject var3) {
         return var3;
      } else {
         throw new JSONException("Index \"" + var1 + "\" was the wrong type in: \"" + var0 + "\"");
      }
   }

   public static JsonArray getRequiredArrayJsonArray(JsonArray var0, int var1) {
      if (getRequiredArrayElement(var0, var1) instanceof JsonArray var3) {
         return var3;
      } else {
         throw new JSONException("Index \"" + var1 + "\" was the wrong type in: \"" + var0 + "\"");
      }
   }

   @Generated
   private ThreadModuleDump9() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
