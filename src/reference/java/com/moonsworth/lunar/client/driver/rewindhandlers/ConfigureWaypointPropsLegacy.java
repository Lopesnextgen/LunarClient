package com.moonsworth.lunar.client.driver.rewindhandlers;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.driver.DriverContextLegacy;
import lombok.Generated;
import com.moonsworth.lunar.client.driver.waypoint.ConfigureWaypointProps;

public class ConfigureWaypointPropsLegacy extends DriverContextLegacy {
   private String server;
   private String world;
   private String name;
   private Double x;
   private Double y;
   private Double field1;

   @Override
   protected void method2(JsonObject var1) {
      if (this.server != null && this.world != null && this.name != null) {
         JsonObject var2 = new JsonObject();
         var2.addProperty("server", this.server);
         var2.addProperty("world", this.world);
         var2.addProperty("name", this.name);
         var1.add("waypoint", var2);
      }

      if (this.x != null && this.y != null && this.field1 != null) {
         JsonObject var3 = new JsonObject();
         var3.addProperty("x", this.x);
         var3.addProperty("y", this.y);
         var3.addProperty("z", this.field1);
         var1.add("location", var3);
      }

      super.method2(var1);
   }

   @Generated
   ConfigureWaypointPropsLegacy(String var1, String var2, String var3, Double doubleValue, Double doubleValue2, Double doubleValue3) {
      this.server = var1;
      this.world = var2;
      this.name = var3;
      this.x = doubleValue;
      this.y = doubleValue2;
      this.field1 = doubleValue3;
   }

   @Generated
   public static ConfigureWaypointPropsLegacy.Data method2() {
      return new ConfigureWaypointPropsLegacy.Data();
   }

   @Generated
   public static class Data {
      @Generated
      private String server;
      @Generated
      private String world;
      @Generated
      private String name;
      @Generated
      private Double x;
      @Generated
      private Double y;
      @Generated
      private Double field1;

      @Generated
      Data() {
      }

      @Generated
      public ConfigureWaypointPropsLegacy.Data method1(String var1) {
         this.server = var1;
         return this;
      }

      @Generated
      public ConfigureWaypointPropsLegacy.Data method2(String var1) {
         this.world = var1;
         return this;
      }

      @Generated
      public ConfigureWaypointPropsLegacy.Data method3(String var1) {
         this.name = var1;
         return this;
      }

      @Generated
      public ConfigureWaypointPropsLegacy.Data method4(Double var1) {
         this.x = var1;
         return this;
      }

      @Generated
      public ConfigureWaypointPropsLegacy.Data method5(Double var1) {
         this.y = var1;
         return this;
      }

      @Generated
      public ConfigureWaypointPropsLegacy.Data method6(Double var1) {
         this.field1 = var1;
         return this;
      }

      @Generated
      public ConfigureWaypointPropsLegacy method7() {
         return new ConfigureWaypointPropsLegacy(this.server, this.world, this.name, this.x, this.y, this.field1);
      }

      @Generated
      @Override
      public String toString() {
         return "ConfigureWaypointProps.ConfigureWaypointPropsBuilder(server="
            + this.server
            + ", world="
            + this.world
            + ", name="
            + this.name
            + ", x="
            + this.x
            + ", y="
            + this.y
            + ", z="
            + this.field1
            + ")";
      }
   }
}
