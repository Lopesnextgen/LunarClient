package com.moonsworth.lunar.client.driver.waypoint;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.config.override.SettingIntercept;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.driver.DriverContext;
import com.moonsworth.lunar.client.mod.render.waypoints.Waypoints;

public class WaypointContext extends DriverContext {
   private final boolean field1;
   private final Boolean field2;

   public WaypointContext() {
      Waypoints waypoints1 = Client.method109().method40().method20();
      SettingIntercept alert22 = (SettingIntercept)waypoints1.method18(ModTraits.field4);
      this.field2 = alert22 == null ? null : (Boolean)alert22.method3().orElse(null);
      this.field1 = waypoints1.isEnabled();
   }

   @Override
   protected void method2(JsonObject json1) {
      super.method2(json1);
      json1.addProperty("enabled", this.field1);
      if (this.field2 != null) {
         json1.addProperty("locked", this.field2);
      }
   }
}
