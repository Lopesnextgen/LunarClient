package com.moonsworth.lunar.client.ui;

import java.awt.Color;
import lombok.Generated;

public class AnimatedValue extends Animation {
   private int field10;
   private int field11;
   private boolean field12;

   public AnimatedValue(long var1, int var3, int var4) {
      super(var1);
      this.field10 = var3;
      this.field11 = var4;
   }

   public AnimatedValue(int var1, int var2) {
      this(125L, var1, var2);
   }

   public float method1(boolean var1) {
      this.method3(var1);
      if (this.method7()) {
         float var2 = super.method1();
         return this.field12 ? var2 : 1.0F - var2;
      } else {
         return this.field12 ? 1.0F : 0.0F;
      }
   }

   public int method2(boolean var1) {
      this.method3(var1);
      if (this.method7()) {
         float var2 = super.method1();
         Color var3 = new Color(this.field12 ? this.field10 : this.field11, true);
         Color var4 = new Color(this.field12 ? this.field11 : this.field10, true);
         int var5 = (int)Math.abs(var2 * var4.getRed() + (1.0F - var2) * var3.getRed());
         int var6 = (int)Math.abs(var2 * var4.getGreen() + (1.0F - var2) * var3.getGreen());
         int var7 = (int)Math.abs(var2 * var4.getBlue() + (1.0F - var2) * var3.getBlue());
         int var8 = (int)Math.abs(var2 * var4.getAlpha() + (1.0F - var2) * var3.getAlpha());
         return (var8 & 0xFF) << 24 | (var5 & 0xFF) << 16 | (var6 & 0xFF) << 8 | var7 & 0xFF;
      } else {
         return var1 ? this.field11 : this.field10;
      }
   }

   private void method3(boolean var1) {
      if (var1 && !this.field12) {
         this.field12 = true;
         this.start();
      } else if (this.field12 && !var1) {
         this.field12 = false;
         this.start();
      }
   }

   @Generated
   public void method4(int var1) {
      this.field10 = var1;
   }

   @Generated
   public void method5(int var1) {
      this.field11 = var1;
   }

   @Generated
   public int method19() {
      return this.field11;
   }
}
