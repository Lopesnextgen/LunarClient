package com.moonsworth.lunar.client.driver.waypoint;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.driver.DriverContext;
import lombok.Generated;

public class ConfigureWaypointProps extends DriverContext {
   private String server;
   private String world;
   private String name;
   private Double x;
   private Double y;
   private Double field1;

   @Override
   protected void method2(JsonObject json1) {
      if (this.server != null && this.world != null && this.name != null) {
         JsonObject json2 = new JsonObject();
         json2.addProperty("server", this.server);
         json2.addProperty("world", this.world);
         json2.addProperty("name", this.name);
         json1.add("waypoint", json2);
      }

      if (this.x != null && this.y != null && this.field1 != null) {
         JsonObject json3 = new JsonObject();
         json3.addProperty("x", this.x);
         json3.addProperty("y", this.y);
         json3.addProperty("z", this.field1);
         json1.add("location", json3);
      }

      super.method2(json1);
   }

   @Generated
   ConfigureWaypointProps(String text1, String text, String text2, Double doubleValue, Double doubleValue2, Double doubleValue3) {
      this.server = text1;
      this.world = text;
      this.name = text2;
      this.x = doubleValue;
      this.y = doubleValue2;
      this.field1 = doubleValue3;
   }

   @Generated
   public static ConfigureWaypointProps.Data method2() {
      return new ConfigureWaypointProps.Data();
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
      public ConfigureWaypointProps.Data method1(String text1) {
         this.server = text1;
         return this;
      }

      @Generated
      public ConfigureWaypointProps.Data method2(String text1) {
         this.world = text1;
         return this;
      }

      @Generated
      public ConfigureWaypointProps.Data method3(String text1) {
         this.name = text1;
         return this;
      }

      @Generated
      public ConfigureWaypointProps.Data method4(Double value1) {
         this.x = value1;
         return this;
      }

      @Generated
      public ConfigureWaypointProps.Data method5(Double value1) {
         this.y = value1;
         return this;
      }

      @Generated
      public ConfigureWaypointProps.Data method6(Double value1) {
         this.field1 = value1;
         return this;
      }

      @Generated
      public ConfigureWaypointProps method7() {
         return new ConfigureWaypointProps(this.server, this.world, this.name, this.x, this.y, this.field1);
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
