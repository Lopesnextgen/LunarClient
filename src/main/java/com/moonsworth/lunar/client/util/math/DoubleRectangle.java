package com.moonsworth.lunar.client.util.math;

import lombok.Generated;

public class DoubleRectangle {
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

   public boolean method5(DoubleRectangle threadmoduledump951) {
      return this.getX() < threadmoduledump951.method3() && this.method3() > threadmoduledump951.left() && this.method2() < threadmoduledump951.method4() && this.method4() > threadmoduledump951.method2();
   }

   public boolean method6(DoubleRectangle threadmoduledump951) {
      return threadmoduledump951.left() >= this.left() && threadmoduledump951.method2() >= this.method2() && threadmoduledump951.method3() <= this.method3() && threadmoduledump951.method4() <= this.method4();
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
   public void setX(double value1) {
      this.x = value1;
   }

   @Generated
   public void setY(double value1) {
      this.y = value1;
   }

   @Generated
   public void setWidth(double value1) {
      this.width = value1;
   }

   @Generated
   public void setHeight(double value1) {
      this.height = value1;
   }

   @Generated
   public DoubleRectangle(double value1, double value, double value2, double value3) {
      this.x = value1;
      this.y = value;
      this.width = value2;
      this.height = value3;
   }

   @Generated
   public DoubleRectangle() {
   }
}
