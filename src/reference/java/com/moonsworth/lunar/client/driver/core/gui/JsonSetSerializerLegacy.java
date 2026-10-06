package com.moonsworth.lunar.client.driver.core.gui;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.Set;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

class JsonSetSerializerLegacy {
   private final Set<Object> field1;

   @Nullable
   JsonElement method1() {
      JsonArray var1 = new JsonArray();

      for (Object var3 : this.field1) {
         var1.add(GuiIterator.method8(var3));
      }

      return var1;
   }

   static JsonProviderLegacy method2(final Set<Object> var0) {
      return new JsonProviderLegacy() {
         private final JsonSetSerializerLegacy field1 = new JsonSetSerializerLegacy(var0);

         @Override
         public JsonElement provide() {
            return this.field1.method1();
         }
      };
   }

   static Gui method3(final Set<Object> var0) {
      return new Gui() {
         private final JsonSetSerializerLegacy field1 = new JsonSetSerializerLegacy(var0);

         @Override
         public JsonElement method128() {
            return this.field1.method1();
         }
      };
   }

   @Generated
   public JsonSetSerializerLegacy(Set<Object> var1) {
      this.field1 = var1;
   }
}
