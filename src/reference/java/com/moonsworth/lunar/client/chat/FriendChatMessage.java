package com.moonsworth.lunar.client.chat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import com.moonsworth.lunar.client.util.memory.Memory;
import com.moonsworth.lunar.client.util.text.TextUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.Generated;

public class FriendChatMessage implements JsonProvider {
   private static final DateTimeFormatter field1 = DateTimeFormatter.ofPattern("HH:mm:ss");
   private final String field2;
   private final Memory field3;
   private final String field4;
   private final String field5;
   private final List<String> field6;
   private boolean field7;

   public FriendChatMessage(Memory memory1, String text) {
      this.field3 = memory1;
      this.field2 = LocalDateTime.now().format(field1);
      String text3 = this.field2 + " " + memory1.method11() + ": ";
      this.field5 = text;
      this.field4 = text3 + text;
      this.field6 = TextUtils.wrapText(ChatFormatting.getTextWithoutFormattingCodes(this.field4), 180.0F, true);
      this.field7 = false;
      String text4 = this.getLines().get(0).replaceFirst(text3, "");
      this.field6.remove(0);
      this.field6.add(0, text4);
   }

   public void method2() {
      this.field7 = true;
   }

   public JsonElement provide() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("user", this.field3.method10().toString());
      json1.addProperty("time", this.field2);
      json1.addProperty("message", this.field5);
      return json1;
   }

   @Generated
   public String method3() {
      return this.field2;
   }

   @Generated
   public Memory method4() {
      return this.field3;
   }

   @Generated
   public String method5() {
      return this.field4;
   }

   @Generated
   public List<String> getLines() {
      return this.field6;
   }

   @Generated
   public boolean method6() {
      return this.field7;
   }
}
