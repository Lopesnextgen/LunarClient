package com.moonsworth.lunar.client.ui;

import com.moonsworth.lunar.client.util.ThreadModuleDump38;

public class HudElementAnimation extends Animation {
   public HudElementAnimation(long var1) {
      super(var1, 0.0F);
   }

   @Override
   protected float method1() {
      float var1 = super.method1();
      float var2 = var1 * 2.0F - 1.0F;
      return (float)(ThreadModuleDump38.method1(var2 * Math.PI) + 1.0) / 2.0F;
   }

   @Override
   protected long method2(float var1) {
      return (long)((float)this.durationMs * var1);
   }
}
