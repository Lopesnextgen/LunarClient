package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventExplosion extends LunarEvent {
   private final Itemcounter6 field1;
   private final double field2;
   private final double field3;
   private final double field4;
   private final float field5;

   @Generated
   public Itemcounter6 method1() {
      return this.field1;
   }

   @Generated
   public double getX() {
      return this.field2;
   }

   @Generated
   public double getY() {
      return this.field3;
   }

   @Generated
   public double getZ() {
      return this.field4;
   }

   @Generated
   public float method2() {
      return this.field5;
   }

   @Generated
   public EventExplosion(Itemcounter6 itemcounter61, double value, double value2, double value3, float value4) {
      this.field1 = itemcounter61;
      this.field2 = value;
      this.field3 = value2;
      this.field4 = value3;
      this.field5 = value4;
   }
}
