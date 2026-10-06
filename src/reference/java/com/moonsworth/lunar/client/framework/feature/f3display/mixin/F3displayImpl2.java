package com.moonsworth.lunar.client.framework.feature.f3display.mixin;

public class F3displayImpl2 extends F3display {
   private static final long field8 = 50L;
   private int frames = 0;
   private long field9 = 0L;

   public F3displayImpl2() {
      super(240, "FPS");
      this.frames = 60;
   }

   @Override
   protected int[] method8() {
      int var1 = this.method9();
      if (var1 > 1100) {
         return new int[]{1000};
      } else if (var1 > 600) {
         return new int[]{500};
      } else if (var1 > 300) {
         return new int[]{240};
      } else if (var1 > 240) {
         return new int[]{120};
      } else if (var1 > 120) {
         return new int[]{60};
      } else if (var1 > 90) {
         return new int[]{30, 60};
      } else if (var1 > 45) {
         return new int[]{30};
      } else if (var1 > 30) {
         return new int[]{15};
      } else {
         return var1 > 15 ? new int[]{10} : null;
      }
   }

   @Override
   public String method11(int var1) {
      return var1 + " FPS";
   }

   public void method3() {
      this.field9 = 0L;
      this.frames = 0;
   }

   public void method4() {
      long var1 = System.currentTimeMillis();
      if (this.field9 == 0L) {
         this.field9 = var1;
      }

      this.frames++;
      long var3 = var1 - this.field9;
      if (var3 >= 50L) {
         float var5 = 1000.0F / (float)var3;
         int var6 = (int)(this.frames * var5);
         this.frames = 0;
         this.field9 = var1;
         this.method2(var6);
      }
   }
}
