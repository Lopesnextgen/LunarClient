package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.config.Config;

public class KeyNameOptionMigration implements OptionMigration {
   public KeyNameOptionMigration() {
   }

   @Override
   public boolean method1(Config config1) {
      return Bridge.getMinecraftVersion().method21();
   }

   @Override
   public JsonPrimitive method2(String text, JsonPrimitive json2) {
      if (text.startsWith("chatOpacity")) {
         return new JsonPrimitive(Math.max(0.4, json2.getAsFloat()));
      } else {
         return text.startsWith("key_") && json2.isString() ? new JsonPrimitive(VanillaOptionsFile.method12(json2.getAsString())) : json2;
      }
   }
}
