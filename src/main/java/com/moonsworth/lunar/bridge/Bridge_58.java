package com.moonsworth.lunar.bridge;

import lombok.Generated;

public final class Bridge_58 {
   private final Bridge3Extension field1;
   private final int field2;
   private final boolean field3;
   private final int field4;
   private long lastUpdated;
   private int field5 = 0;
   private int frameCount;

   public Bridge_58(Bridge3Extension var1) {
      this.field1 = var1;
      this.field2 = var1.bridge$getFrameTime();
      this.field3 = var1.bridge$getFrameCount() > 0;
      if (this.field3) {
         this.field4 = var1.bridge$getFrameCount();
      } else {
         this.field4 = 0;
      }
   }

   public int getFrame() {
      return this.field3 ? this.field1.bridge$getFrameIndex(this.field5) : this.field5;
   }

   public void updateAnimation() {
      int var1;
      int var2;
      if (this.field3 && this.field1.bridge$hasTime(var2 = this.getFrame())) {
         var1 = this.field1.bridge$getFrameTimeSingle(var2);
      } else {
         var1 = this.field2;
      }

      var1 *= 50;
      if (System.currentTimeMillis() - this.lastUpdated >= var1) {
         if (++this.field5 >= (this.field3 ? this.field4 : this.frameCount)) {
            this.field5 = 0;
         }

         this.lastUpdated = System.currentTimeMillis();
      }
   }

   public void setFrameCount(int var1) {
      if (this.field3) {
         this.frameCount = this.field1.bridge$getFrameCount();
      } else {
         this.frameCount = var1 / this.field1.bridge$getFrameHeight();
      }
   }

   @Generated
   public Bridge3Extension method1() {
      return this.field1;
   }

   @Generated
   public int method2() {
      return this.field5;
   }

   @Generated
   public int getFrameCount() {
      return this.frameCount;
   }
}
