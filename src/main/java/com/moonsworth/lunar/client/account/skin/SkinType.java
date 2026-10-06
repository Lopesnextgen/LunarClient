package com.moonsworth.lunar.client.account.skin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import lombok.Generated;

public enum SkinType implements JsonProvider {
   CLASSIC("classic", "Steve", "http://textures.minecraft.net/texture/1a4af718455d4aab528e7a61f86fa25e6a369d1768dcb13f7df319a713eb810b"),
   SLIM("slim", "Alex", "http://textures.minecraft.net/texture/3b60a1f6d562f52aaebbf1434f1de147933a3affe0e764fa49ea057536623cd3");

   private final String name;
   private final String userFriendlyName;
   private final String defaultSkinUrl;

   public JsonElement provide() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("name", this.name);
      json1.addProperty("userFriendlyName", this.userFriendlyName);
      json1.addProperty("defaultSkinUrl", this.defaultSkinUrl);
      return json1;
   }

   @Override
   public String toString() {
      return this.name;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public String getUserFriendlyName() {
      return this.userFriendlyName;
   }

   @Generated
   public String getDefaultSkinUrl() {
      return this.defaultSkinUrl;
   }

   @Generated
   SkinType(String text, String text2, String text3) {
      this.name = text;
      this.userFriendlyName = text2;
      this.defaultSkinUrl = text3;
   }
}
