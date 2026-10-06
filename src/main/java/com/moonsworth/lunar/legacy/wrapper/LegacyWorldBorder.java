package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.ichor.VersionGate;
import lombok.Generated;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;

@VersionGate(max = 0)
public class LegacyWorldBorder {
   private double field1 = 0.0;
   private double field2 = 0.0;
   private double field3 = 6.0E7;
   private double field4 = this.field3;
   private long field5;
   private long startTime;
   private int field6 = 29999984;
   private double field7 = 0.2;
   private double field8 = 5.0;
   private int field9 = 15;
   private int field10 = 5;

   public LegacyWorldBorder() {
   }

   public boolean contains(double value1, double value3) {
      value1 = Math.floor(value1);
      value3 = Math.floor(value3);
      return value1 + 1.0 > this.minX() && value1 < this.maxX() && value3 + 1.0 > this.minZ() && value3 < this.maxZ();
   }

   public boolean method1(AxisAlignedBB box1) {
      return box1.maxX > this.minX() && box1.minX < this.maxX() && box1.maxZ > this.minZ() && box1.minZ < this.maxZ();
   }

   public double method2(Entity entity1) {
      return this.method3(entity1.posX, entity1.posZ);
   }

   public double method3(double value1, double value3) {
      double value5 = value3 - this.minZ();
      double value7 = this.maxZ() - value3;
      double value9 = value1 - this.minX();
      double value11 = this.maxX() - value1;
      double value13 = Math.min(value9, value11);
      value13 = Math.min(value13, value5);
      return Math.min(value13, value7);
   }

   public BorderTransition method4() {
      return this.field4 < this.field3 ? BorderTransition.SHRINKING : (this.field4 > this.field3 ? BorderTransition.GROWING : BorderTransition.STATIONARY);
   }

   public double minX() {
      double value1 = this.getCenterX() - this.getDiameter() / 2.0;
      if (value1 < -this.field6) {
         value1 = -this.field6;
      }

      return value1;
   }

   public double minZ() {
      double value1 = this.method10() - this.getDiameter() / 2.0;
      if (value1 < -this.field6) {
         value1 = -this.field6;
      }

      return value1;
   }

   public double maxX() {
      double value1 = this.getCenterX() + this.getDiameter() / 2.0;
      if (value1 > this.field6) {
         value1 = this.field6;
      }

      return value1;
   }

   public double maxZ() {
      double value1 = this.method10() + this.getDiameter() / 2.0;
      if (value1 > this.field6) {
         value1 = this.field6;
      }

      return value1;
   }

   public void method5(double value1, double value3) {
      this.field1 = value1;
      this.field2 = value3;
   }

   public double getDiameter() {
      if (this.method4() != BorderTransition.STATIONARY) {
         double value1 = (float)(System.currentTimeMillis() - this.startTime) / (float)(this.field5 - this.startTime);
         if (value1 < 1.0) {
            return this.field3 + (this.field4 - this.field3) * value1;
         }

         this.method8(this.field4);
      }

      return this.field3;
   }

   public long method6() {
      return this.method4() != BorderTransition.STATIONARY ? this.field5 - System.currentTimeMillis() : 0L;
   }

   public double method7() {
      return this.field4;
   }

   public void method8(double value1) {
      this.field3 = value1;
      this.field4 = value1;
      this.field5 = System.currentTimeMillis();
      this.startTime = this.field5;
   }

   public void setTransition(double value1, double value3, long value) {
      if (value == 0L) {
         this.method8(value3);
      } else {
         this.field3 = value1;
         this.field4 = value3;
         this.startTime = System.currentTimeMillis();
         this.field5 = this.startTime + value;
      }
   }

   public int getSize() {
      return this.field6;
   }

   public void setSize(int number1) {
      this.field6 = number1;
   }

   public double method9() {
      return this.field5 == this.startTime ? 0.0 : Math.abs(this.field3 - this.field4) / (this.field5 - this.startTime);
   }

   @Generated
   public double getCenterX() {
      return this.field1;
   }

   @Generated
   public double method10() {
      return this.field2;
   }

   @Generated
   public double method11() {
      return this.field3;
   }

   @Generated
   public double method12() {
      return this.field4;
   }

   @Generated
   public long method13() {
      return this.field5;
   }

   @Generated
   public long getStartTime() {
      return this.startTime;
   }

   @Generated
   public int method14() {
      return this.field6;
   }

   @Generated
   public double method15() {
      return this.field7;
   }

   @Generated
   public double method16() {
      return this.field8;
   }

   @Generated
   public int method17() {
      return this.field9;
   }

   @Generated
   public int method18() {
      return this.field10;
   }

   @Generated
   public void method19(double value1) {
      this.field1 = value1;
   }

   @Generated
   public void method20(double value1) {
      this.field2 = value1;
   }

   @Generated
   public void method21(double value1) {
      this.field3 = value1;
   }

   @Generated
   public void method22(double value1) {
      this.field4 = value1;
   }

   @Generated
   public void method23(long number1) {
      this.field5 = number1;
   }

   @Generated
   public void setStartTime(long number1) {
      this.startTime = number1;
   }

   @Generated
   public void method25(int number1) {
      this.field6 = number1;
   }

   @Generated
   public void method26(double value1) {
      this.field7 = value1;
   }

   @Generated
   public void method27(double value1) {
      this.field8 = value1;
   }

   @Generated
   public void method28(int number1) {
      this.field9 = number1;
   }

   @Generated
   public void method29(int number1) {
      this.field10 = number1;
   }
}
