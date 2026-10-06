package com.moonsworth.lunar.bridge;

import lombok.Generated;

public final class SpriteAnimationBridge {
   private final AnimationMetadataSectionBridge field1;
   private final int field2;
   private final boolean field3;
   private final int field4;
   private long lastUpdated;
   private int field5 = 0;
   private int frameCount;

   public SpriteAnimationBridge(AnimationMetadataSectionBridge animationMetadataSectionBridge) {
      this.field1 = animationMetadataSectionBridge;
      this.field2 = animationMetadataSectionBridge.bridge$getFrameTime();
      this.field3 = animationMetadataSectionBridge.bridge$getFrameCount() > 0;
      if (this.field3) {
         this.field4 = animationMetadataSectionBridge.bridge$getFrameCount();
      } else {
         this.field4 = 0;
      }
   }

   public int getFrame() {
      return this.field3 ? this.field1.bridge$getFrameIndex(this.field5) : this.field5;
   }

   public void updateAnimation() {
      int number1;
      int number2;
      if (this.field3 && this.field1.bridge$hasTime(number2 = this.getFrame())) {
         number1 = this.field1.bridge$getFrameTimeSingle(number2);
      } else {
         number1 = this.field2;
      }

      number1 *= 50;
      if (System.currentTimeMillis() - this.lastUpdated >= number1) {
         if (++this.field5 >= (this.field3 ? this.field4 : this.frameCount)) {
            this.field5 = 0;
         }

         this.lastUpdated = System.currentTimeMillis();
      }
   }

   public void setFrameCount(int number1) {
      if (this.field3) {
         this.frameCount = this.field1.bridge$getFrameCount();
      } else {
         this.frameCount = number1 / this.field1.bridge$getFrameHeight();
      }
   }

   @Generated
   public AnimationMetadataSectionBridge method1() {
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
