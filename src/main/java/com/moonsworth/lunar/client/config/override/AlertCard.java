package com.moonsworth.lunar.client.config.override;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;

public class AlertCard implements JsonProvider {
   private final int field1;
   private final String field2;
   private final String field3;
   private final String field4;
   private final String field5;
   private final String link;
   private final boolean field6;
   private final ResourceLocationBridge field7;
   private static final Pattern field8 = Pattern.compile("\\{COUNTDOWN:(.*?)\\}");

   public AlertCard(int value, String text2, String text, String text4, String text3, String text5, boolean flag) {
      this.field1 = value;
      this.field2 = text2;
      this.field3 = text;
      this.field4 = text4;
      this.field5 = text3;
      this.link = text5;
      this.field6 = flag;
      this.field7 = ResourceLocationBridge.create("lunar", "icons/alerts/" + this.field5.toLowerCase() + ".webp");
   }

   public String method2() {
      Matcher matcher1 = field8.matcher(this.field3);
      String text2 = "";
      if (matcher1.find()) {
         long number3 = Integer.parseInt(matcher1.group(1).trim());
         long number5 = System.currentTimeMillis() / 1000L;
         text2 = method2(number3, number5);
      }

      return this.field3.replaceAll(field8.pattern(), text2);
   }

   private static String method2(long value, long value2) {
      String text4 = "now";
      if (value > value2) {
         long number5 = value - value2;
         int number7 = Math.round((float)number5 / 86400.0F);
         int number8 = Math.round((float)number5 % 86400.0F / 3600.0F);
         int number9 = Math.round((float)number5 % 3600.0F / 60.0F);
         int number10 = Math.round((float)(number5 % 60L));
         text4 = number7 + "d " + number8 + "h " + number9 + "m " + number10 + "s";
      }

      return text4;
   }

   public JsonElement provide() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("id", this.field1);
      json1.addProperty("name", this.field2);
      json1.addProperty("text", this.method2());
      json1.addProperty("color", this.field4);
      json1.addProperty("icon", this.field5.toLowerCase());
      json1.addProperty("link", this.link);
      json1.addProperty("dismissible", this.field6);
      return json1;
   }

   @Generated
   public int getId() {
      return this.field1;
   }

   @Generated
   public String getName() {
      return this.field2;
   }

   @Generated
   public String getText() {
      return this.field3;
   }

   @Generated
   public String getColor() {
      return this.field4;
   }

   @Generated
   public String getIcon() {
      return this.field5;
   }

   @Generated
   public String getLink() {
      return this.link;
   }

   @Generated
   public boolean method4() {
      return this.field6;
   }

   @Generated
   public ResourceLocationBridge method5() {
      return this.field7;
   }
}
