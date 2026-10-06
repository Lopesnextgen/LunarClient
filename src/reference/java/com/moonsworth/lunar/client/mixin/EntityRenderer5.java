package com.moonsworth.lunar.client.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import com.moonsworth.lunar.client.memory.Memory;
import com.moonsworth.lunar.client.util.ThreadModuleDump46;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.Generated;

public class EntityRenderer5 implements JsonProviderLegacy {
   private static final DateTimeFormatter field1 = DateTimeFormatter.ofPattern("HH:mm:ss");
   private final String field2;
   private final Memory field3;
   private final String field4;
   private final String field5;
   private final List<String> field6;
   private boolean field7;

   public EntityRenderer5(Memory var1, String text) {
      this.field3 = var1;
      this.field2 = LocalDateTime.now().format(field1);
      String var3 = this.field2 + " " + var1.method11() + ": ";
      this.field5 = text;
      this.field4 = var3 + text;
      this.field6 = ThreadModuleDump46.method1(AdventureChatFormatting.getTextWithoutFormattingCodes(this.field4), 180.0F, true);
      this.field7 = false;
      String var4 = this.getLines().get(0).replaceFirst(var3, "");
      this.field6.remove(0);
      this.field6.add(0, var4);
   }

   public void method2() {
      this.field7 = true;
   }

   @Override
   public JsonElement provide() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("user", this.field3.method10().toString());
      var1.addProperty("time", this.field2);
      var1.addProperty("message", this.field5);
      return var1;
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
