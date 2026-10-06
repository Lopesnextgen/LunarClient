package com.moonsworth.lunar.client.ui;

public class FadeAnimation extends Animation {
   private boolean field10;

   public FadeAnimation(long var1) {
      super(var1, 1.0F);
   }

   public FadeAnimation(long var1, float value) {
      super(var1, value);
   }

   public float method1(boolean var1) {
      this.method2(var1);
      if (this.method7()) {
         return super.method1();
      } else {
         return var1 ? 1.0F : 0.0F;
      }
   }

   private void method2(boolean var1) {
      if (var1 && !this.field10) {
         this.field10 = true;
         this.start();
      } else if (this.field10 && !var1) {
         this.field10 = false;
         this.start();
      }
   }
}
