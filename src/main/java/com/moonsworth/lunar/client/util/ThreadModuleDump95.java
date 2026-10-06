package com.moonsworth.lunar.client.util;

import lombok.Generated;

public class ThreadModuleDump95 {
   private double x;
   private double y;
   private double width;
   private double height;

   public void method1() {
      this.x = 0.0;
      this.y = 0.0;
      this.width = 0.0;
      this.height = 0.0;
   }

   public double left() {
      return this.getX();
   }

   public double method2() {
      return this.getY();
   }

   public double method3() {
      return this.getX() + this.getWidth();
   }

   public double method4() {
      return this.getY() + this.getHeight();
   }

   public boolean method5(ThreadModuleDump95 var1) {
      return this.getX() < var1.method3() && this.method3() > var1.left() && this.method2() < var1.method4() && this.method4() > var1.method2();
   }

   public boolean method6(ThreadModuleDump95 var1) {
      return var1.left() >= this.left() && var1.method2() >= this.method2() && var1.method3() <= this.method3() && var1.method4() <= this.method4();
   }

   @Generated
   public double getX() {
      return this.x;
   }

   @Generated
   public double getY() {
      return this.y;
   }

   @Generated
   public double getWidth() {
      return this.width;
   }

   @Generated
   public double getHeight() {
      return this.height;
   }

   @Generated
   public void setX(double var1) {
      this.x = var1;
   }

   @Generated
   public void setY(double var1) {
      this.y = var1;
   }

   @Generated
   public void setWidth(double var1) {
      this.width = var1;
   }

   @Generated
   public void setHeight(double var1) {
      this.height = var1;
   }

   @Generated
   public ThreadModuleDump95(double var1, double value, double value2, double value3) {
      this.x = var1;
      this.y = value;
      this.width = value2;
      this.height = value3;
   }

   @Generated
   public ThreadModuleDump95() {
   }
}
