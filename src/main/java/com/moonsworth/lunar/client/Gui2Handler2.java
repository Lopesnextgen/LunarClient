package com.moonsworth.lunar.client;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import java.time.Instant;

public class Gui2Handler2 implements JsonProviderLegacy {
   private final int field1;
   private final String field2;
   private final String field3;
   private final ResourceLocationBridge field4;
   private final ResourceLocationBridge field5;
   private final Instant field6;
   private final boolean field7;

   public Gui2Handler2(int var1, String text, String text2, ResourceLocationBridge resourceLocationBridge, ResourceLocationBridge resourceLocationBridge2, Instant instant, boolean flag) {
      this.field1 = var1;
      this.field2 = text;
      this.field3 = text2;
      this.field4 = resourceLocationBridge;
      this.field5 = resourceLocationBridge2;
      this.field6 = instant;
      this.field7 = flag;
   }

   @Override
   public JsonElement provide() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("id", this.field1);
      var1.addProperty("name", this.field2);
      var1.addProperty("resource", this.field4.bridge$getPath());
      var1.addProperty("resourceUi", this.field5.bridge$getPath());
      var1.addProperty("animated", this.field7);
      if (this.field3 != null && !this.field3.isEmpty()) {
         var1.addProperty("description", this.field3);
      }

      return var1;
   }

   public JsonElement method2() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("name", this.field2);
      var1.addProperty("resourceUi", this.field5.bridge$getPath());
      return var1;
   }

   public int id() {
      return this.field1;
   }

   public String name() {
      return this.field2;
   }

   public String description() {
      return this.field3;
   }

   public ResourceLocationBridge method3() {
      return this.field4;
   }

   public ResourceLocationBridge method4() {
      return this.field5;
   }

   public Instant method5() {
      return this.field6;
   }

   public boolean method6() {
      return this.field7;
   }
}
