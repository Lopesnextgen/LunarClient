package com.moonsworth.lunar.client.ui;

public class HoverAnimation extends Animation {
   private final float exponent;

   public HoverAnimation(long var1) {
      this(var1, 2.0F);
   }

   public HoverAnimation(long var1, float value) {
      super(var1);
      this.exponent = value;
   }

   @Override
   public float method1() {
      float var1 = super.method1();
      return (float)Math.pow(var1, this.exponent);
   }
}
