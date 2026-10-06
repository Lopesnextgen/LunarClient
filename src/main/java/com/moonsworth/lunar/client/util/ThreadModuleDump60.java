package com.moonsworth.lunar.client.util;

public class ThreadModuleDump60 {
   public float x;
   public float y;

   public ThreadModuleDump60() {
   }

   public ThreadModuleDump60(float var1, float var2) {
      this.x = var1;
      this.y = var2;
   }

   public void set(float var1, float var2) {
      this.x = var1;
      this.y = var2;
   }

   public ThreadModuleDump60 method1() {
      float var1 = this.x * this.x + this.y * this.y;
      if (var1 > 0.0F) {
         var1 = 1.0F / (float)Math.sqrt(var1);
         this.x *= var1;
         this.y *= var1;
      }

      return this;
   }
}
