package com.moonsworth.lunar.client.ui;

public class ColorAnimation extends Animation {
   public ColorAnimation(long var1) {
      super(var1);
   }

   @Override
   protected float method1() {
      float var1 = super.method1();
      return var1 < 0.5 ? 2.0F * var1 * var1 : -1.0F + (4.0F - 2.0F * var1) * var1;
   }

   @Override
   protected long method2(float var1) {
      return (long)((float)this.durationMs * var1);
   }
}
