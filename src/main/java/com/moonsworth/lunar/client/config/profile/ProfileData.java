package com.moonsworth.lunar.client.config.profile;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public final class ProfileData {
   private final JsonObject json;

   ProfileData(JsonObject json1) {
      this.json = json1;
   }

   public boolean has(String text1) {
      return this.json.has(text1) && !this.json.get(text1).isJsonNull();
   }

   public boolean isEnabled() {
      return this.getBoolean("enabled", false);
   }

   public boolean getBoolean(String text1, boolean flag) {
      JsonElement element3 = this.getValue(text1);
      return element3 != null && element3.isJsonPrimitive() && element3.getAsJsonPrimitive().isBoolean() ? element3.getAsBoolean() : flag;
   }

   public float getFloat(String text1, float value) {
      JsonElement element3 = this.getValue(text1);
      return element3 != null && element3.isJsonPrimitive() && element3.getAsJsonPrimitive().isNumber() ? element3.getAsFloat() : value;
   }

   public String getString(String text1, String text) {
      JsonElement element3 = this.getValue(text1);
      return element3 != null && element3.isJsonPrimitive() && element3.getAsJsonPrimitive().isString() ? element3.getAsString() : text;
   }

   public ProfileColor getColor(String text1) {
      return ProfileColor.parse(this.json.get(text1));
   }

   public JsonElement getElement(String text1) {
      return this.json.get(text1);
   }

   public ProfileData getSection(String text1) {
      JsonElement element2 = this.json.get(text1);
      return element2 != null && element2.isJsonObject() ? new ProfileData(element2.getAsJsonObject()) : null;
   }

   private JsonElement getValue(String text1) {
      JsonElement element2 = this.json.get(text1);
      return element2 != null && element2.isJsonObject() && element2.getAsJsonObject().has("value") ? element2.getAsJsonObject().get("value") : element2;
   }
}
