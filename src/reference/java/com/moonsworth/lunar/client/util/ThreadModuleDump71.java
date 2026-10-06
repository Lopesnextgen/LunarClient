package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import lombok.Generated;

public class ThreadModuleDump71 {
   private final int width;
   private final int height;
   private final double scaledWidthD;
   private final double scaledHeightD;
   private int scaledWidth;
   private int scaledHeight;
   private int scaleFactor;

   public ThreadModuleDump71(Bridge5_12 var1) {
      this(var1, var1.bridge$displayWidth(), var1.bridge$displayHeight());
   }

   public ThreadModuleDump71(int var1, int var2, int var3, double var4, double value) {
      this.width = var2;
      this.height = var3;
      this.scaledWidth = ThreadModuleDump67.method8(var4);
      this.scaledHeight = ThreadModuleDump67.method8(value);
      this.scaleFactor = var1;
      this.scaledWidthD = var4;
      this.scaledHeightD = value;
   }

   public ThreadModuleDump71(Bridge5_12 var1, int var2, int var3) {
      this.width = var2;
      this.height = var3;
      if (ThreadModuleDump63.MC_VERSION >= 6) {
         this.scaleFactor = Math.max(1, var1.bridge$getGuiScale());
         this.scaledWidth = var2;
         this.scaledHeight = var3;
      } else {
         this.scaledWidth = (int)(var2 / LcuiScreen.method20());
         this.scaledHeight = (int)(var3 / LcuiScreen.method20());
         this.scaleFactor = 1;
         boolean var4 = var1.bridge$unicode();
         int var5 = var1.bridge$getGameSettings().bridge$getGuiScale();
         if (var5 == 0) {
            var5 = 1000;
         }

         while (this.scaleFactor < var5 && this.scaledWidth / (this.scaleFactor + 1) >= 320 && this.scaledHeight / (this.scaleFactor + 1) >= 240) {
            this.scaleFactor++;
         }

         if (var4 && this.scaleFactor % 2 != 0 && this.scaleFactor != 1) {
            this.scaleFactor--;
         }
      }

      this.scaledWidthD = (double)this.scaledWidth / this.scaleFactor;
      this.scaledHeightD = (double)this.scaledHeight / this.scaleFactor;
      this.scaledWidth = ThreadModuleDump67.method8(this.scaledWidthD);
      this.scaledHeight = ThreadModuleDump67.method8(this.scaledHeightD);
   }

   public double getScaledWidth_double() {
      return this.scaledWidthD;
   }

   public double getScaledHeight_double() {
      return this.scaledHeightD;
   }

   @Generated
   public int getWidth() {
      return this.width;
   }

   @Generated
   public int getHeight() {
      return this.height;
   }

   @Generated
   public int getScaledWidth() {
      return this.scaledWidth;
   }

   @Generated
   public int getScaledHeight() {
      return this.scaledHeight;
   }

   @Generated
   public int getScaleFactor() {
      return this.scaleFactor;
   }
}
