package com.moonsworth.lunar.client.account;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.option.JsonConfigurable;
import lombok.Generated;

public class MinecraftProfile implements JsonConfigurable {
   private String id;
   private String name;

   public void load(JsonObject json1) {
      this.id = json1.get("id").getAsString();
      this.name = json1.get("name").getAsString();
   }

   public void method1(JsonObject json1) {
      JsonObject json2 = new JsonObject();
      json1.add("minecraftProfile", json2);
      json2.addProperty("id", this.id);
      json2.addProperty("name", this.name);
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public void setId(String text1) {
      this.id = text1;
   }

   @Generated
   public void setName(String text1) {
      this.name = text1;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof MinecraftProfile lighting3loader32)) {
         return false;
      } else if (!lighting3loader32.canEqual(this)) {
         return false;
      } else {
         String text3 = this.getId();
         String text4 = lighting3loader32.getId();
         if (text3 == null ? text4 == null : text3.equals(text4)) {
            String text5 = this.getName();
            String text6 = lighting3loader32.getName();
            return text5 == null ? text6 == null : text5.equals(text6);
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof MinecraftProfile;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      String text3 = this.getId();
      number2 = number2 * 59 + (text3 == null ? 43 : text3.hashCode());
      String text4 = this.getName();
      return number2 * 59 + (text4 == null ? 43 : text4.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "MinecraftProfile(id=" + this.getId() + ", name=" + this.getName() + ")";
   }

   @Generated
   public MinecraftProfile(String text1, String text) {
      this.id = text1;
      this.name = text;
   }

   @Generated
   public MinecraftProfile() {
   }
}
