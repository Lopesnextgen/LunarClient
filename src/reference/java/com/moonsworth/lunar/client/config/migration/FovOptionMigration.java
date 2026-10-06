package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.config.Config;

public class FovOptionMigration implements OptionMigration {
   public FovOptionMigration() {
   }

   @Override
   public boolean method1(Config config1) {
      return true;
   }

   @Override
   public JsonPrimitive method2(String text, JsonPrimitive json2) {
      if (!text.equals("fov")) {
         return json2;
      }

      double value3 = json2.getAsDouble();
      if (Bridge.getMinecraftVersion().method5(Config.field13)) {
         if (value3 < -1.0 || value3 > 1.0) {
            double value5 = (value3 - 70.0) / 40.0;
            value3 = value5 >= -1.0 && value5 <= 1.0 ? value5 : 0.0;
         }
      } else {
         value3 = method3(value3);
      }

      return new JsonPrimitive(value3);
   }

   public static double method3(double value) {
      if (!(value < 30.0) && !(value > 110.0)) {
         return value;
      }

      double value2 = value * 40.0 + 70.0;
      return value2 >= 30.0 && value2 <= 110.0 ? value2 : 70.0;
   }
}
