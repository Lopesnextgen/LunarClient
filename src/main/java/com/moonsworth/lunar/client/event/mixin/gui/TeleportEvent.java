package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public abstract class TeleportEvent extends Highlight {
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
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof TeleportEvent var2)) {
         return false;
      } else if (!var2.canEqual(this)) {
         return false;
      } else if (Double.compare(this.getX(), var2.getX()) != 0) {
         return false;
      } else if (Double.compare(this.getY(), var2.getY()) != 0) {
         return false;
      } else if (Double.compare(this.getZ(), var2.getZ()) != 0) {
         return false;
      } else {
         return Float.compare(this.method1(), var2.method1()) != 0 ? false : Float.compare(this.method2(), var2.method2()) == 0;
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof TeleportEvent;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      long var3 = Double.doubleToLongBits(this.getX());
      var2 = var2 * 59 + (int)(var3 >>> 32 ^ var3);
      long var5 = Double.doubleToLongBits(this.getY());
      var2 = var2 * 59 + (int)(var5 >>> 32 ^ var5);
      long var7 = Double.doubleToLongBits(this.getZ());
      var2 = var2 * 59 + (int)(var7 >>> 32 ^ var7);
      var2 = var2 * 59 + Float.floatToIntBits(this.method1());
      return var2 * 59 + Float.floatToIntBits(this.method2());
   }

   @Generated
   public TeleportEvent(double var1, double var3, double var5, float var7, float var8) {
      this.field1 = var1;
      this.field2 = var3;
      this.field3 = var5;
      this.field4 = var7;
      this.field5 = var8;
   }

   public static class TeleportPreEvent extends TeleportEvent {
      public TeleportPreEvent(double var1, double var3, double var5, float var7, float var8) {
         super(var1, var3, var5, var7, var8);
      }
   }

   public static class TeleportPostEvent extends TeleportEvent {
      public TeleportPostEvent(double var1, double var3, double var5, float var7, float var8) {
         super(var1, var3, var5, var7, var8);
      }
   }
}
