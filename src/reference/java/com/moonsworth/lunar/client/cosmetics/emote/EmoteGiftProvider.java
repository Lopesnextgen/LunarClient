package com.moonsworth.lunar.client.cosmetics.emote;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteGift;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import lombok.Generated;

public class EmoteGiftProvider implements JsonProvider {
   private final int field1;
   private int field2;
   private int field3;
   private EmoteGift field4;

   public EmoteGiftProvider(int number1, int value, int value2) {
      this.field1 = number1;
      this.field2 = value;
      this.field3 = value2;
   }

   @Override
   public JsonElement provide() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("emoteId", this.field1);
      json1.addProperty("slotId", this.field2);
      json1.addProperty("jamId", this.field3 == 0 ? null : this.field3);
      if (this.field4 != null) {
         json1.addProperty("expireTime", this.field4.method5());
         if (this.field4.method1() != null) {
            json1.addProperty("grantedAt", this.field4.method3());
         }
      }

      return json1;
   }

   @Generated
   public void method1(int number1) {
      this.field2 = number1;
   }

   @Generated
   public void method2(int number1) {
      this.field3 = number1;
   }

   @Generated
   public void method3(EmoteGift fov2_41) {
      this.field4 = fov2_41;
   }

   @Generated
   public int getEmoteId() {
      return this.field1;
   }

   @Generated
   public int getSlotId() {
      return this.field2;
   }

   @Generated
   public int getJamId() {
      return this.field3;
   }

   @Generated
   public EmoteGift method4() {
      return this.field4;
   }
}
