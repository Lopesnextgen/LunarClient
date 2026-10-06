package com.moonsworth.lunar.client.config.migration;

import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.config.Config;

public class TypeCoercionMigration implements OptionMigration {
   public TypeCoercionMigration() {
   }

   @Override
   public boolean method1(Config config1) {
      return true;
   }

   @Override
   public JsonPrimitive method2(String text, JsonPrimitive json2) {
      String text3 = json2.getAsString();
      if (!text3.equals("true") && !text3.equals("false")) {
         Integer number4 = Ints.tryParse(text3);
         if (number4 != null) {
            return new JsonPrimitive(number4);
         }

         Float value5 = Floats.tryParse(text3);
         if (value5 != null) {
            return new JsonPrimitive(value5);
         }

         Double value6 = Doubles.tryParse(text3);
         return value6 != null ? new JsonPrimitive(value6) : json2;
      } else {
         return new JsonPrimitive(Boolean.parseBoolean(text3));
      }
   }
}
