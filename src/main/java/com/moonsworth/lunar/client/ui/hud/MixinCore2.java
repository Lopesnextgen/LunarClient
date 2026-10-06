package com.moonsworth.lunar.client.ui.hud;

import lombok.Generated;

public class MixinCore2 {
   private int field1;
   private int height;
   private int field2;
   private int minWidth;
   private int width;
   private int maxWidth;

   public static MixinCore2 method1(int value, int var1, int var2, int var3, int var4, int var5) {
      return new MixinCore2(value, var1, var2, var3, var4, var5);
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
   public void method5(int var1) {
      this.field1 = var1;
   }

   @Generated
   public void setHeight(int var1) {
      this.height = var1;
   }

   @Generated
   public void method6(int var1) {
      this.field2 = var1;
   }

   @Generated
   public void setMinWidth(int var1) {
      this.minWidth = var1;
   }

   @Generated
   public void setWidth(int var1) {
      this.width = var1;
   }

   @Generated
   public void setMaxWidth(int var1) {
      this.maxWidth = var1;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof MixinCore2 var2)) {
         return false;
      } else if (!var2.canEqual(this)) {
         return false;
      } else if (this.method2() != var2.method2()) {
         return false;
      } else if (this.getHeight() != var2.getHeight()) {
         return false;
      } else if (this.method3() != var2.method3()) {
         return false;
      } else if (this.getMinWidth() != var2.getMinWidth()) {
         return false;
      } else {
         return this.getWidth() != var2.getWidth() ? false : this.getMaxWidth() == var2.getMaxWidth();
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof MixinCore2;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      var2 = var2 * 59 + this.method2();
      var2 = var2 * 59 + this.getHeight();
      var2 = var2 * 59 + this.method3();
      var2 = var2 * 59 + this.getMinWidth();
      var2 = var2 * 59 + this.getWidth();
      return var2 * 59 + this.getMaxWidth();
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
   public MixinCore2(int var1, int var2, int var3, int var4, int var5, int value) {
      this.field1 = var1;
      this.height = var2;
      this.field2 = var3;
      this.minWidth = var4;
      this.width = var5;
      this.maxWidth = value;
   }
}
