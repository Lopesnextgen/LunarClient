package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.ichor.Annotation2;
import lombok.Generated;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;

@Annotation2(max = 0)
public class LegacyWorldBorderBase {
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

   public boolean contains(double var1, double var3) {
      var1 = Math.floor(var1);
      var3 = Math.floor(var3);
      return var1 + 1.0 > this.minX() && var1 < this.maxX() && var3 + 1.0 > this.minZ() && var3 < this.maxZ();
   }

   public boolean method1(AxisAlignedBB var1) {
      return var1.maxX > this.minX() && var1.minX < this.maxX() && var1.maxZ > this.minZ() && var1.minZ < this.maxZ();
   }

   public double method2(Entity var1) {
      return this.method3(var1.posX, var1.posZ);
   }

   public double method3(double var1, double var3) {
      double var5 = var3 - this.minZ();
      double var7 = this.maxZ() - var3;
      double var9 = var1 - this.minX();
      double var11 = this.maxX() - var1;
      double var13 = Math.min(var9, var11);
      var13 = Math.min(var13, var5);
      return Math.min(var13, var7);
   }

   public Wrapper$Type method4() {
      return this.field4 < this.field3 ? Wrapper$Type.SHRINKING : (this.field4 > this.field3 ? Wrapper$Type.GROWING : Wrapper$Type.STATIONARY);
   }

   public double minX() {
      double var1 = this.getCenterX() - this.getDiameter() / 2.0;
      if (var1 < -this.field6) {
         var1 = -this.field6;
      }

      return var1;
   }

   public double minZ() {
      double var1 = this.method10() - this.getDiameter() / 2.0;
      if (var1 < -this.field6) {
         var1 = -this.field6;
      }

      return var1;
   }

   public double maxX() {
      double var1 = this.getCenterX() + this.getDiameter() / 2.0;
      if (var1 > this.field6) {
         var1 = this.field6;
      }

      return var1;
   }

   public double maxZ() {
      double var1 = this.method10() + this.getDiameter() / 2.0;
      if (var1 > this.field6) {
         var1 = this.field6;
      }

      return var1;
   }

   public void method5(double var1, double var3) {
      this.field1 = var1;
      this.field2 = var3;
   }

   public double getDiameter() {
      if (this.method4() != Wrapper$Type.STATIONARY) {
         double var1 = (float)(System.currentTimeMillis() - this.startTime) / (float)(this.field5 - this.startTime);
         if (var1 < 1.0) {
            return this.field3 + (this.field4 - this.field3) * var1;
         }

         this.method8(this.field4);
      }

      return this.field3;
   }

   public long method6() {
      return this.method4() != Wrapper$Type.STATIONARY ? this.field5 - System.currentTimeMillis() : 0L;
   }

   public double method7() {
      return this.field4;
   }

   public void method8(double var1) {
      this.field3 = var1;
      this.field4 = var1;
      this.field5 = System.currentTimeMillis();
      this.startTime = this.field5;
   }

   public void setTransition(double var1, double var3, long var5) {
      if (var5 == 0L) {
         this.method8(var3);
      } else {
         this.field3 = var1;
         this.field4 = var3;
         this.startTime = System.currentTimeMillis();
         this.field5 = this.startTime + var5;
      }
   }

   public int getSize() {
      return this.field6;
   }

   public void setSize(int var1) {
      this.field6 = var1;
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
   public void method19(double var1) {
      this.field1 = var1;
   }

   @Generated
   public void method20(double var1) {
      this.field2 = var1;
   }

   @Generated
   public void method21(double var1) {
      this.field3 = var1;
   }

   @Generated
   public void method22(double var1) {
      this.field4 = var1;
   }

   @Generated
   public void method23(long var1) {
      this.field5 = var1;
   }

   @Generated
   public void setStartTime(long var1) {
      this.startTime = var1;
   }

   @Generated
   public void method25(int var1) {
      this.field6 = var1;
   }

   @Generated
   public void method26(double var1) {
      this.field7 = var1;
   }

   @Generated
   public void method27(double var1) {
      this.field8 = var1;
   }

   @Generated
   public void method28(int var1) {
      this.field9 = var1;
   }

   @Generated
   public void method29(int var1) {
      this.field10 = var1;
   }
}
