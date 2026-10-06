package com.moonsworth.lunar.client.account;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import java.time.Instant;

public class Badge implements JsonProvider {
   private final int field1;
   private final String field2;
   private final String field3;
   private final ResourceLocationBridge field4;
   private final ResourceLocationBridge field5;
   private final Instant field6;
   private final boolean field7;

   public Badge(int value, String text, String text2, ResourceLocationBridge horsestats144, ResourceLocationBridge horsestats145, Instant instant6, boolean flag) {
      this.field1 = value;
      this.field2 = text;
      this.field3 = text2;
      this.field4 = horsestats144;
      this.field5 = horsestats145;
      this.field6 = instant6;
      this.field7 = flag;
   }

   public JsonElement provide() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("id", this.field1);
      json1.addProperty("name", this.field2);
      json1.addProperty("resource", this.field4.bridge$getPath());
      json1.addProperty("resourceUi", this.field5.bridge$getPath());
      json1.addProperty("animated", this.field7);
      if (this.field3 != null && !this.field3.isEmpty()) {
         json1.addProperty("description", this.field3);
      }

      return json1;
   }

   public JsonElement method2() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("name", this.field2);
      json1.addProperty("resourceUi", this.field5.bridge$getPath());
      return json1;
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
