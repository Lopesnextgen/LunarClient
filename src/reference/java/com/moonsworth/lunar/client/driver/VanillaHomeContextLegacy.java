package com.moonsworth.lunar.client.driver;

import com.google.gson.JsonObject;

public class VanillaHomeContextLegacy extends DriverContextLegacy {
   private final boolean field1;

   public VanillaHomeContextLegacy(boolean var1) {
      this.field1 = var1;
   }

   @Override
   protected void method2(JsonObject var1) {
      super.method2(var1);
      var1.addProperty("vanillaHome", this.field1);
   }
}
