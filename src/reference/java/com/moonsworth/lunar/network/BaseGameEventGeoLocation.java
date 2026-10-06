package com.moonsworth.lunar.network;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Map.Entry;
import javax.annotation.Nonnull;

public class BaseGameEventGeoLocation implements Serializable {
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

   public BaseGameEventGeoLocation() {
   }

   public BaseGameEventGeoLocation city(String text1) {
      this.city = text1;
      return this;
   }

   @Nonnull
   public String getCity() {
      return this.city;
   }

   public void setCity(String text1) {
      this.city = text1;
   }

   public BaseGameEventGeoLocation continent(String text1) {
      this.continent = text1;
      return this;
   }

   @Nonnull
   public String getContinent() {
      return this.continent;
   }

   public void setContinent(String text1) {
      this.continent = text1;
   }

   public BaseGameEventGeoLocation country(String text1) {
      this.country = text1;
      return this;
   }

   @Nonnull
   public String getCountry() {
      return this.country;
   }

   public void setCountry(String text1) {
      this.country = text1;
   }

   public BaseGameEventGeoLocation region(String text1) {
      this.region = text1;
      return this;
   }

   @Nonnull
   public String getRegion() {
      return this.region;
   }

   public void setRegion(String text1) {
      this.region = text1;
   }

   public BaseGameEventGeoLocation regionCode(String text1) {
      this.regionCode = text1;
      return this;
   }

   @Nonnull
   public String getRegionCode() {
      return this.regionCode;
   }

   public void setRegionCode(String text1) {
      this.regionCode = text1;
   }

   public BaseGameEventGeoLocation postalCode(String text1) {
      this.postalCode = text1;
      return this;
   }

   @Nonnull
   public String getPostalCode() {
      return this.postalCode;
   }

   public void setPostalCode(String text1) {
      this.postalCode = text1;
   }

   public BaseGameEventGeoLocation metroCode(String text1) {
      this.metroCode = text1;
      return this;
   }

   @Nonnull
   public String getMetroCode() {
      return this.metroCode;
   }

   public void setMetroCode(String text1) {
      this.metroCode = text1;
   }

   public BaseGameEventGeoLocation timezone(String text1) {
      this.timezone = text1;
      return this;
   }

   @Nonnull
   public String getTimezone() {
      return this.timezone;
   }

   public void setTimezone(String text1) {
      this.timezone = text1;
   }

   @Override
   public boolean equals(Object obj1) {
      if (this == obj1) {
         return true;
      } else if (obj1 != null && this.getClass() == obj1.getClass()) {
         BaseGameEventGeoLocation mixinhelper72 = (BaseGameEventGeoLocation)obj1;
         return Objects.equals(this.city, mixinhelper72.city)
            && Objects.equals(this.continent, mixinhelper72.continent)
            && Objects.equals(this.country, mixinhelper72.country)
            && Objects.equals(this.region, mixinhelper72.region)
            && Objects.equals(this.regionCode, mixinhelper72.regionCode)
            && Objects.equals(this.postalCode, mixinhelper72.postalCode)
            && Objects.equals(this.metroCode, mixinhelper72.metroCode)
            && Objects.equals(this.timezone, mixinhelper72.timezone);
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
      StringBuilder builder1 = new StringBuilder();
      builder1.append("class BaseGameEventGeoLocation {\n");
      builder1.append("    city: ").append(this.toIndentedString(this.city)).append("\n");
      builder1.append("    continent: ").append(this.toIndentedString(this.continent)).append("\n");
      builder1.append("    country: ").append(this.toIndentedString(this.country)).append("\n");
      builder1.append("    region: ").append(this.toIndentedString(this.region)).append("\n");
      builder1.append("    regionCode: ").append(this.toIndentedString(this.regionCode)).append("\n");
      builder1.append("    postalCode: ").append(this.toIndentedString(this.postalCode)).append("\n");
      builder1.append("    metroCode: ").append(this.toIndentedString(this.metroCode)).append("\n");
      builder1.append("    timezone: ").append(this.toIndentedString(this.timezone)).append("\n");
      builder1.append("}");
      return builder1.toString();
   }

   private String toIndentedString(Object obj1) {
      return obj1 == null ? "null" : obj1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement element0) {
      if (element0 == null && !field16.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in BaseGameEventGeoLocation is not found in the empty JSON string", field16.toString())
         );
      }

      for (Entry entry3 : element0.getAsJsonObject().entrySet()) {
         if (!field15.contains(entry3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `BaseGameEventGeoLocation` properties. JSON: %s", entry3.getKey(), element0.toString()
               )
            );
         }
      }

      for (String text6 : field16) {
         if (element0.getAsJsonObject().get(text6) == null) {
            throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", text6, element0.toString()));
         }
      }

      JsonObject json5 = element0.getAsJsonObject();
      if (!json5.get("city").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `city` to be a primitive type in the JSON string but got `%s`", json5.get("city").toString())
         );
      }

      if (!json5.get("continent").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `continent` to be a primitive type in the JSON string but got `%s`", json5.get("continent").toString())
         );
      }

      if (!json5.get("country").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `country` to be a primitive type in the JSON string but got `%s`", json5.get("country").toString())
         );
      }

      if (!json5.get("region").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `region` to be a primitive type in the JSON string but got `%s`", json5.get("region").toString())
         );
      }

      if (!json5.get("region_code").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `region_code` to be a primitive type in the JSON string but got `%s`", json5.get("region_code").toString())
         );
      }

      if (!json5.get("postal_code").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `postal_code` to be a primitive type in the JSON string but got `%s`", json5.get("postal_code").toString())
         );
      }

      if (!json5.get("metro_code").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `metro_code` to be a primitive type in the JSON string but got `%s`", json5.get("metro_code").toString())
         );
      }

      if (!json5.get("timezone").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `timezone` to be a primitive type in the JSON string but got `%s`", json5.get("timezone").toString())
         );
      }
   }

   public static BaseGameEventGeoLocation method20(String text) {
      return (BaseGameEventGeoLocation)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(text, BaseGameEventGeoLocation.class);
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
}
