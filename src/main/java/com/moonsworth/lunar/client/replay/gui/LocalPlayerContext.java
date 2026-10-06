package com.moonsworth.lunar.client.replay.gui;

import lombok.Generated;

public class LocalPlayerContext {
   private double prevX;
   private double x;
   private double field1;
   private double y;
   private double prevZ;
   private double z;
   private float field2;
   private float yaw;
   private float field3;
   private float pitch;
   private int field4;

   public void setPos(double value1, double value, double value2) {
      this.prevX = this.x;
      this.field1 = this.y;
      this.prevZ = this.z;
      this.x = value1;
      this.y = value;
      this.z = value2;
   }

   public void method1(float value1, float value) {
      this.field2 = this.yaw;
      this.field3 = this.pitch;
      this.yaw = value1;
      this.pitch = value;
   }

   public float method2(float value1) {
      return this.field2 + (this.yaw - this.field2) * value1;
   }

   public float method3(float value1) {
      return this.field3 + (this.pitch - this.field3) * value1;
   }

   @Generated
   public LocalPlayerContext() {
   }

   @Generated
   public double getPrevX() {
      return this.prevX;
   }

   @Generated
   public double getX() {
      return this.x;
   }

   @Generated
   public double method5() {
      return this.field1;
   }

   @Generated
   public double getY() {
      return this.y;
   }

   @Generated
   public double getPrevZ() {
      return this.prevZ;
   }

   @Generated
   public double getZ() {
      return this.z;
   }

   @Generated
   public float method7() {
      return this.field2;
   }

   @Generated
   public float getYaw() {
      return this.yaw;
   }

   @Generated
   public float method8() {
      return this.field3;
   }

   @Generated
   public float getPitch() {
      return this.pitch;
   }

   @Generated
   public int method9() {
      return this.field4;
   }

   @Generated
   public void setPrevX(double value1) {
      this.prevX = value1;
   }

   @Generated
   public void setX(double value1) {
      this.x = value1;
   }

   @Generated
   public void method11(double value1) {
      this.field1 = value1;
   }

   @Generated
   public void setY(double value1) {
      this.y = value1;
   }

   @Generated
   public void setPrevZ(double value1) {
      this.prevZ = value1;
   }

   @Generated
   public void setZ(double value1) {
      this.z = value1;
   }

   @Generated
   public void method14(float value1) {
      this.field2 = value1;
   }

   @Generated
   public void setYaw(float value1) {
      this.yaw = value1;
   }

   @Generated
   public void method16(float value1) {
      this.field3 = value1;
   }

   @Generated
   public void setPitch(float value1) {
      this.pitch = value1;
   }

   @Generated
   public void method18(int number1) {
      this.field4 = number1;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof LocalPlayerContext nameplate32)) {
         return false;
      } else if (!nameplate32.canEqual(this)) {
         return false;
      } else if (Double.compare(this.getPrevX(), nameplate32.getPrevX()) != 0) {
         return false;
      } else if (Double.compare(this.getX(), nameplate32.getX()) != 0) {
         return false;
      } else if (Double.compare(this.method5(), nameplate32.method5()) != 0) {
         return false;
      } else if (Double.compare(this.getY(), nameplate32.getY()) != 0) {
         return false;
      } else if (Double.compare(this.getPrevZ(), nameplate32.getPrevZ()) != 0) {
         return false;
      } else if (Double.compare(this.getZ(), nameplate32.getZ()) != 0) {
         return false;
      } else if (Float.compare(this.method7(), nameplate32.method7()) != 0) {
         return false;
      } else if (Float.compare(this.getYaw(), nameplate32.getYaw()) != 0) {
         return false;
      } else if (Float.compare(this.method8(), nameplate32.method8()) != 0) {
         return false;
      } else {
         return Float.compare(this.getPitch(), nameplate32.getPitch()) != 0 ? false : this.method9() == nameplate32.method9();
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof LocalPlayerContext;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      long number3 = Double.doubleToLongBits(this.getPrevX());
      number2 = number2 * 59 + (int)(number3 >>> 32 ^ number3);
      long number5 = Double.doubleToLongBits(this.getX());
      number2 = number2 * 59 + (int)(number5 >>> 32 ^ number5);
      long number7 = Double.doubleToLongBits(this.method5());
      number2 = number2 * 59 + (int)(number7 >>> 32 ^ number7);
      long number9 = Double.doubleToLongBits(this.getY());
      number2 = number2 * 59 + (int)(number9 >>> 32 ^ number9);
      long number11 = Double.doubleToLongBits(this.getPrevZ());
      number2 = number2 * 59 + (int)(number11 >>> 32 ^ number11);
      long number13 = Double.doubleToLongBits(this.getZ());
      number2 = number2 * 59 + (int)(number13 >>> 32 ^ number13);
      number2 = number2 * 59 + Float.floatToIntBits(this.method7());
      number2 = number2 * 59 + Float.floatToIntBits(this.getYaw());
      number2 = number2 * 59 + Float.floatToIntBits(this.method8());
      number2 = number2 * 59 + Float.floatToIntBits(this.getPitch());
      return number2 * 59 + this.method9();
   }

   @Generated
   @Override
   public String toString() {
      return "LocalPlayerContext(prevX="
         + this.getPrevX()
         + ", x="
         + this.getX()
         + ", prevY="
         + this.method5()
         + ", y="
         + this.getY()
         + ", prevZ="
         + this.getPrevZ()
         + ", z="
         + this.getZ()
         + ", prevYaw="
         + this.method7()
         + ", yaw="
         + this.getYaw()
         + ", prevPitch="
         + this.method8()
         + ", pitch="
         + this.getPitch()
         + ", view="
         + this.method9()
         + ")";
   }
}
