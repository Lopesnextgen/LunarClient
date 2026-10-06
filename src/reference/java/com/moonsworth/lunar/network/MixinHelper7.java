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

public class MixinHelper7 implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_CITY = "city";
   @SerializedName("city")
   private String city;
   public static final String SERIALIZED_NAME_CONTINENT = "continent";
   @SerializedName("continent")
   private String continent;
   public static final String SERIALIZED_NAME_COUNTRY = "country";
   @SerializedName("country")
   private String country;
   public static final String SERIALIZED_NAME_REGION = "region";
   @SerializedName("region")
   private String region;
   public static final String SERIALIZED_NAME_REGION_CODE = "region_code";
   @SerializedName("region_code")
   private String regionCode;
   public static final String SERIALIZED_NAME_POSTAL_CODE = "postal_code";
   @SerializedName("postal_code")
   private String postalCode;
   public static final String SERIALIZED_NAME_METRO_CODE = "metro_code";
   @SerializedName("metro_code")
   private String metroCode;
   public static final String SERIALIZED_NAME_TIMEZONE = "timezone";
   @SerializedName("timezone")
   private String timezone;
   public static HashSet<String> field15 = new HashSet<>();
   public static HashSet<String> field16 = new HashSet<>();

   public MixinHelper7 method1(String var1) {
      this.city = var1;
      return this;
   }

   @Nonnull
   public String getCity() {
      return this.city;
   }

   public void setCity(String var1) {
      this.city = var1;
   }

   public MixinHelper7 method2(String var1) {
      this.continent = var1;
      return this;
   }

   @Nonnull
   public String getContinent() {
      return this.continent;
   }

   public void setContinent(String var1) {
      this.continent = var1;
   }

   public MixinHelper7 country(String var1) {
      this.country = var1;
      return this;
   }

   @Nonnull
   public String getCountry() {
      return this.country;
   }

   public void setCountry(String var1) {
      this.country = var1;
   }

   public MixinHelper7 region(String var1) {
      this.region = var1;
      return this;
   }

   @Nonnull
   public String getRegion() {
      return this.region;
   }

   public void setRegion(String var1) {
      this.region = var1;
   }

   public MixinHelper7 regionCode(String var1) {
      this.regionCode = var1;
      return this;
   }

   @Nonnull
   public String getRegionCode() {
      return this.regionCode;
   }

   public void setRegionCode(String var1) {
      this.regionCode = var1;
   }

   public MixinHelper7 postalCode(String var1) {
      this.postalCode = var1;
      return this;
   }

   @Nonnull
   public String getPostalCode() {
      return this.postalCode;
   }

   public void setPostalCode(String var1) {
      this.postalCode = var1;
   }

   public MixinHelper7 metroCode(String var1) {
      this.metroCode = var1;
      return this;
   }

   @Nonnull
   public String getMetroCode() {
      return this.metroCode;
   }

   public void setMetroCode(String var1) {
      this.metroCode = var1;
   }

   public MixinHelper7 timezone(String var1) {
      this.timezone = var1;
      return this;
   }

   @Nonnull
   public String getTimezone() {
      return this.timezone;
   }

   public void setTimezone(String var1) {
      this.timezone = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         MixinHelper7 var2 = (MixinHelper7)var1;
         return Objects.equals(this.city, var2.city)
            && Objects.equals(this.continent, var2.continent)
            && Objects.equals(this.country, var2.country)
            && Objects.equals(this.region, var2.region)
            && Objects.equals(this.regionCode, var2.regionCode)
            && Objects.equals(this.postalCode, var2.postalCode)
            && Objects.equals(this.metroCode, var2.metroCode)
            && Objects.equals(this.timezone, var2.timezone);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.city, this.continent, this.country, this.region, this.regionCode, this.postalCode, this.metroCode, this.timezone);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("class BaseGameEventGeoLocation {\n");
      var1.append("    city: ").append(this.toIndentedString(this.city)).append("\n");
      var1.append("    continent: ").append(this.toIndentedString(this.continent)).append("\n");
      var1.append("    country: ").append(this.toIndentedString(this.country)).append("\n");
      var1.append("    region: ").append(this.toIndentedString(this.region)).append("\n");
      var1.append("    regionCode: ").append(this.toIndentedString(this.regionCode)).append("\n");
      var1.append("    postalCode: ").append(this.toIndentedString(this.postalCode)).append("\n");
      var1.append("    metroCode: ").append(this.toIndentedString(this.metroCode)).append("\n");
      var1.append("    timezone: ").append(this.toIndentedString(this.timezone)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String toIndentedString(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement var0) {
      if (var0 == null && !field16.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in BaseGameEventGeoLocation is not found in the empty JSON string", field16.toString())
         );
      }

      for (Entry var3 : var0.getAsJsonObject().entrySet()) {
         if (!field15.contains(var3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `BaseGameEventGeoLocation` properties. JSON: %s", var3.getKey(), var0.toString()
               )
            );
         }
      }

      for (String var6 : field16) {
         if (var0.getAsJsonObject().get(var6) == null) {
            throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", var6, var0.toString()));
         }
      }

      JsonObject var5 = var0.getAsJsonObject();
      if (!var5.get("city").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `city` to be a primitive type in the JSON string but got `%s`", var5.get("city").toString())
         );
      }

      if (!var5.get("continent").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `continent` to be a primitive type in the JSON string but got `%s`", var5.get("continent").toString())
         );
      }

      if (!var5.get("country").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `country` to be a primitive type in the JSON string but got `%s`", var5.get("country").toString())
         );
      }

      if (!var5.get("region").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `region` to be a primitive type in the JSON string but got `%s`", var5.get("region").toString())
         );
      }

      if (!var5.get("region_code").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `region_code` to be a primitive type in the JSON string but got `%s`", var5.get("region_code").toString())
         );
      }

      if (!var5.get("postal_code").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `postal_code` to be a primitive type in the JSON string but got `%s`", var5.get("postal_code").toString())
         );
      }

      if (!var5.get("metro_code").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `metro_code` to be a primitive type in the JSON string but got `%s`", var5.get("metro_code").toString())
         );
      }

      if (!var5.get("timezone").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `timezone` to be a primitive type in the JSON string but got `%s`", var5.get("timezone").toString())
         );
      }
   }

   public static MixinHelper7 method20(String var0) {
      return (MixinHelper7)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, MixinHelper7.class);
   }

   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field15.add("city");
      field15.add("continent");
      field15.add("country");
      field15.add("region");
      field15.add("region_code");
      field15.add("postal_code");
      field15.add("metro_code");
      field15.add("timezone");
      field16.add("city");
      field16.add("continent");
      field16.add("country");
      field16.add("region");
      field16.add("region_code");
      field16.add("postal_code");
      field16.add("metro_code");
      field16.add("timezone");
   }

   public static class Data implements TypeAdapterFactory {
      public <T> TypeAdapter<T> create(Gson var1, TypeToken<T> var2) {
         if (!MixinHelper7.class.isAssignableFrom(var2.getRawType())) {
            return null;
         }

         final TypeAdapter var3 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var4 = var1.getDelegateAdapter(this, TypeToken.get(MixinHelper7.class));
         return (new TypeAdapter<MixinHelper7>() {
            public void method1(JsonWriter var1, MixinHelper7 var2x) {
               JsonObject var3x = var4.toJsonTree(var2x).getAsJsonObject();
               var3.write(var1, var3x);
            }

            public MixinHelper7 method2(JsonReader var1) {
               JsonElement var2x = (JsonElement)var3.read(var1);
               MixinHelper7.validateJsonElement(var2x);
               return (MixinHelper7)var4.fromJsonTree(var2x);
            }
         }).nullSafe();
      }
   }
}
