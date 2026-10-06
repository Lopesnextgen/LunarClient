package com.moonsworth.lunar.client.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Generated;

public enum DynamicFeatureFlag {
   RADIO("radio", true);

   private final String identifier;
   private boolean value;

   DynamicFeatureFlag(String var3, boolean var4) {
      this.identifier = var3.toLowerCase();
      this.value = var4;
   }

   public boolean isEnabled() {
      return this.value;
   }

   public static void registerFromJson(JsonObject var0) {
      DynamicFeatureFlag[] var1 = values();

      for (JsonElement var3 : var0.get("enabled").getAsJsonArray().asList()) {
         String var4 = var3.getAsString().toLowerCase();

         for (DynamicFeatureFlag var8 : var1) {
            if (var4.equals(var8.getIdentifier())) {
               var8.setValue(true);
               break;
            }
         }
      }

      for (JsonElement var10 : var0.get("disabled").getAsJsonArray().asList()) {
         String var11 = var10.getAsString().toLowerCase();

         for (DynamicFeatureFlag var15 : var1) {
            if (var11.equals(var15.getIdentifier())) {
               var15.setValue(false);
               break;
            }
         }
      }
   }

   @Generated
   public String getIdentifier() {
      return this.identifier;
   }

   @Generated
   public void setValue(boolean var1) {
      this.value = var1;
   }
}
