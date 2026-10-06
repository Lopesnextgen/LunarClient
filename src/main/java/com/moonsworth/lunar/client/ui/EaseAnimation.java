package com.moonsworth.lunar.client.ui;

public class EaseAnimation extends Animation {
   public EaseAnimation(long var1) {
      super(var1);
   }

   @Override
   protected float method1() {
      float var1 = super.method1();
      return (float)(var1 >= 1.0F ? 1.0 : 1.0 - Math.pow(2.0, -10.0F * var1));
   }
}
