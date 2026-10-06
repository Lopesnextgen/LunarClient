package com.moonsworth.lunar.client.ui.hud;

import lombok.Generated;

public class HudSize {
   private int field1;
   private int height;
   private int field2;
   private int minWidth;
   private int width;
   private int maxWidth;

   public static HudSize method1(int value, int number1, int number2, int number3, int number4, int number5) {
      return new HudSize(value, number1, number2, number3, number4, number5);
   }

   @Generated
   public int method2() {
      return this.field1;
   }

   @Generated
   public int getHeight() {
      return this.height;
   }

   @Generated
   public int method3() {
      return this.field2;
   }

   @Generated
   public int getMinWidth() {
      return this.minWidth;
   }

   @Generated
   public int getWidth() {
      return this.width;
   }

   @Generated
   public int getMaxWidth() {
      return this.maxWidth;
   }

   @Generated
   public void method5(int number1) {
      this.field1 = number1;
   }

   @Generated
   public void setHeight(int number1) {
      this.height = number1;
   }

   @Generated
   public void method6(int number1) {
      this.field2 = number1;
   }

   @Generated
   public void setMinWidth(int number1) {
      this.minWidth = number1;
   }

   @Generated
   public void setWidth(int number1) {
      this.width = number1;
   }

   @Generated
   public void setMaxWidth(int number1) {
      this.maxWidth = number1;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof HudSize mixincore22)) {
         return false;
      } else if (!mixincore22.canEqual(this)) {
         return false;
      } else if (this.method2() != mixincore22.method2()) {
         return false;
      } else if (this.getHeight() != mixincore22.getHeight()) {
         return false;
      } else if (this.method3() != mixincore22.method3()) {
         return false;
      } else if (this.getMinWidth() != mixincore22.getMinWidth()) {
         return false;
      } else {
         return this.getWidth() != mixincore22.getWidth() ? false : this.getMaxWidth() == mixincore22.getMaxWidth();
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof HudSize;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      number2 = number2 * 59 + this.method2();
      number2 = number2 * 59 + this.getHeight();
      number2 = number2 * 59 + this.method3();
      number2 = number2 * 59 + this.getMinWidth();
      number2 = number2 * 59 + this.getWidth();
      return number2 * 59 + this.getMaxWidth();
   }

   @Generated
   @Override
   public String toString() {
      return "HudSize(minHeight="
         + this.method2()
         + ", height="
         + this.getHeight()
         + ", maxHeight="
         + this.method3()
         + ", minWidth="
         + this.getMinWidth()
         + ", width="
         + this.getWidth()
         + ", maxWidth="
         + this.getMaxWidth()
         + ")";
   }

   @Generated
   public HudSize(int number1, int number2, int number3, int number4, int number5, int value) {
      this.field1 = number1;
      this.height = number2;
      this.field2 = number3;
      this.minWidth = number4;
      this.width = number5;
      this.maxWidth = value;
   }
}
