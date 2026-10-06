package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public abstract class EventTeleportBase extends LunarEvent {
   private final double field1;
   private final double field2;
   private final double field3;
   private final float field4;
   private final float field5;

   @Override
   public String toString() {
      return "{x: " + this.field1 + ", y: " + this.field2 + ", z: " + this.field3 + ", rotX: " + this.field4 + ", rotY: " + this.field5 + "}";
   }

   @Generated
   public double getX() {
      return this.field1;
   }

   @Generated
   public double getY() {
      return this.field2;
   }

   @Generated
   public double getZ() {
      return this.field3;
   }

   @Generated
   public float method1() {
      return this.field4;
   }

   @Generated
   public float method2() {
      return this.field5;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof EventTeleportBase highlightbase2)) {
         return false;
      } else if (!highlightbase2.canEqual(this)) {
         return false;
      } else if (Double.compare(this.getX(), highlightbase2.getX()) != 0) {
         return false;
      } else if (Double.compare(this.getY(), highlightbase2.getY()) != 0) {
         return false;
      } else if (Double.compare(this.getZ(), highlightbase2.getZ()) != 0) {
         return false;
      } else {
         return Float.compare(this.method1(), highlightbase2.method1()) != 0 ? false : Float.compare(this.method2(), highlightbase2.method2()) == 0;
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof EventTeleportBase;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      long number3 = Double.doubleToLongBits(this.getX());
      number2 = number2 * 59 + (int)(number3 >>> 32 ^ number3);
      long number5 = Double.doubleToLongBits(this.getY());
      number2 = number2 * 59 + (int)(number5 >>> 32 ^ number5);
      long number7 = Double.doubleToLongBits(this.getZ());
      number2 = number2 * 59 + (int)(number7 >>> 32 ^ number7);
      number2 = number2 * 59 + Float.floatToIntBits(this.method1());
      return number2 * 59 + Float.floatToIntBits(this.method2());
   }

   @Generated
   public EventTeleportBase(double value1, double value3, double value5, float value7, float value8) {
      this.field1 = value1;
      this.field2 = value3;
      this.field3 = value5;
      this.field4 = value7;
      this.field5 = value8;
   }

   public static class EventTeleportPre extends EventTeleportBase {
      public EventTeleportPre(double value1, double value3, double value5, float value7, float value8) {
         super(value1, value3, value5, value7, value8);
      }
   }

   public static class EventTeleportPost extends EventTeleportBase {
      public EventTeleportPost(double value1, double value3, double value5, float value7, float value8) {
         super(value1, value3, value5, value7, value8);
      }
   }
}
