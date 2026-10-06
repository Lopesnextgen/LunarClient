package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.config.Config;

public class KeyCodeOptionMigration implements OptionMigration {
   public KeyCodeOptionMigration() {
   }

   @Override
   public boolean method1(Config config1) {
      return Bridge.getMinecraftVersion().method19();
   }

   @Override
   public JsonPrimitive method2(String text, JsonPrimitive json2) {
      if (text.startsWith("maxFps") && json2.getAsString().equals("0")) {
         return new JsonPrimitive(60);
      } else if (text.startsWith("chatOpacity")) {
         return new JsonPrimitive(Math.max(0.4, json2.getAsFloat()));
      } else {
         return text.startsWith("key_") && json2.isNumber() ? new JsonPrimitive(VanillaOptionsFile.method10(json2.getAsInt())) : json2;
      }
   }
}
