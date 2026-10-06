package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate;

import lombok.Generated;

public class Nameplate3 {
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

   public void setPos(double var1, double var3, double var5) {
      this.prevX = this.x;
      this.field1 = this.y;
      this.prevZ = this.z;
      this.x = var1;
      this.y = var3;
      this.z = var5;
   }

   public void method1(float var1, float var2) {
      this.field2 = this.yaw;
      this.field3 = this.pitch;
      this.yaw = var1;
      this.pitch = var2;
   }

   public float method2(float var1) {
      return this.field2 + (this.yaw - this.field2) * var1;
   }

   public float method3(float var1) {
      return this.field3 + (this.pitch - this.field3) * var1;
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
   public void setPrevX(double var1) {
      this.prevX = var1;
   }

   @Generated
   public void setX(double var1) {
      this.x = var1;
   }

   @Generated
   public void method11(double var1) {
      this.field1 = var1;
   }

   @Generated
   public void setY(double var1) {
      this.y = var1;
   }

   @Generated
   public void setPrevZ(double var1) {
      this.prevZ = var1;
   }

   @Generated
   public void setZ(double var1) {
      this.z = var1;
   }

   @Generated
   public void method14(float var1) {
      this.field2 = var1;
   }

   @Generated
   public void setYaw(float var1) {
      this.yaw = var1;
   }

   @Generated
   public void method16(float var1) {
      this.field3 = var1;
   }

   @Generated
   public void setPitch(float var1) {
      this.pitch = var1;
   }

   @Generated
   public void method18(int var1) {
      this.field4 = var1;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Nameplate3 var2)) {
         return false;
      } else if (!var2.canEqual(this)) {
         return false;
      } else if (Double.compare(this.getPrevX(), var2.getPrevX()) != 0) {
         return false;
      } else if (Double.compare(this.getX(), var2.getX()) != 0) {
         return false;
      } else if (Double.compare(this.method5(), var2.method5()) != 0) {
         return false;
      } else if (Double.compare(this.getY(), var2.getY()) != 0) {
         return false;
      } else if (Double.compare(this.getPrevZ(), var2.getPrevZ()) != 0) {
         return false;
      } else if (Double.compare(this.getZ(), var2.getZ()) != 0) {
         return false;
      } else if (Float.compare(this.method7(), var2.method7()) != 0) {
         return false;
      } else if (Float.compare(this.getYaw(), var2.getYaw()) != 0) {
         return false;
      } else if (Float.compare(this.method8(), var2.method8()) != 0) {
         return false;
      } else {
         return Float.compare(this.getPitch(), var2.getPitch()) != 0 ? false : this.method9() == var2.method9();
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof Nameplate3;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      long var3 = Double.doubleToLongBits(this.getPrevX());
      var2 = var2 * 59 + (int)(var3 >>> 32 ^ var3);
      long var5 = Double.doubleToLongBits(this.getX());
      var2 = var2 * 59 + (int)(var5 >>> 32 ^ var5);
      long var7 = Double.doubleToLongBits(this.method5());
      var2 = var2 * 59 + (int)(var7 >>> 32 ^ var7);
      long var9 = Double.doubleToLongBits(this.getY());
      var2 = var2 * 59 + (int)(var9 >>> 32 ^ var9);
      long var11 = Double.doubleToLongBits(this.getPrevZ());
      var2 = var2 * 59 + (int)(var11 >>> 32 ^ var11);
      long var13 = Double.doubleToLongBits(this.getZ());
      var2 = var2 * 59 + (int)(var13 >>> 32 ^ var13);
      var2 = var2 * 59 + Float.floatToIntBits(this.method7());
      var2 = var2 * 59 + Float.floatToIntBits(this.getYaw());
      var2 = var2 * 59 + Float.floatToIntBits(this.method8());
      var2 = var2 * 59 + Float.floatToIntBits(this.getPitch());
      return var2 * 59 + this.method9();
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
