package com.moonsworth.lunar.client.driver;

import com.google.gson.JsonObject;

public class VanillaHomeContext extends DriverContext {
   private final boolean field1;

   public VanillaHomeContext(boolean flag) {
      this.field1 = flag;
   }

   @Override
   protected void method2(JsonObject json1) {
      super.method2(json1);
      json1.addProperty("vanillaHome", this.field1);
   }
}
