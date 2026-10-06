package com.moonsworth.lunar.client.ui;

public class Animation extends AnimationTimer {
   public Animation(long var1) {
      super(var1, 1.0F);
   }

   public Animation(long var1, float value) {
      super(var1, value);
   }

   @Override
   protected float method1() {
      float var1 = (float)(this.durationMs - this.method13()) / (float)this.durationMs;
      return this.method21() ? 1.0F - var1 : var1;
   }

   @Override
   protected long method2(float var1) {
      return (long)((float)this.durationMs * var1);
   }
}
