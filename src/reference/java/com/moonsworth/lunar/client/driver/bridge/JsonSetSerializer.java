package com.moonsworth.lunar.client.driver.bridge;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.Set;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;

class JsonSetSerializer {
   private final Set<Object> field1;

   @Nullable
   JsonElement method1() {
      JsonArray array1 = new JsonArray();

      for (Object obj3 : this.field1) {
         array1.add(GuiIterator.method8(obj3));
      }

      return array1;
   }

   static JsonProvider method2(final Set<Object> set0) {
      return new JsonProvider() {
         private final JsonSetSerializer field1 = new JsonSetSerializer(set0);

         @Override
         public JsonElement provide() {
            return this.field1.method1();
         }
      };
   }

   static DriverDataProvider method3(final Set<Object> set0) {
      return new DriverDataProvider() {
         private final JsonSetSerializer field1 = new JsonSetSerializer(set0);

         @Override
         public JsonElement method128() {
            return this.field1.method1();
         }
      };
   }

   @Generated
   public JsonSetSerializer(Set<Object> set) {
      this.field1 = set;
   }
}
