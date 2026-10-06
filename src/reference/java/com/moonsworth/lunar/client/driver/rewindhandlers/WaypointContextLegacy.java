package com.moonsworth.lunar.client.driver.rewindhandlers;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.driver.DriverContextLegacy;
import com.moonsworth.lunar.client.mod.render.waypoints.Waypoints;

public class WaypointContextLegacy extends DriverContextLegacy {
   private final boolean field1;
   private final Boolean field2;

   public WaypointContextLegacy() {
      Waypoints var1 = Client.method109().method40().method20();
      Alert2 var2 = (Alert2)var1.method18(Framework.field4);
      this.field2 = var2 == null ? null : (Boolean)var2.method3().orElse(null);
      this.field1 = var1.isEnabled();
   }

   @Override
   protected void method2(JsonObject var1) {
      super.method2(var1);
      var1.addProperty("enabled", this.field1);
      if (this.field2 != null) {
         var1.addProperty("locked", this.field2);
      }
   }
}
