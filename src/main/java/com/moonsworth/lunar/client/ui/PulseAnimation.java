package com.moonsworth.lunar.client.ui;

public class PulseAnimation extends Animation {
   public PulseAnimation(long var1) {
      super(var1);
   }

   @Override
   public float method1() {
      float var1 = super.method1();
      return var1 * (2.0F - var1);
   }
}
