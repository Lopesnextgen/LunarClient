package com.moonsworth.lunar.client.util.math;

public class Vector2f {
   public float x;
   public float y;

   public Vector2f() {
   }

   public Vector2f(float value1, float value2) {
      this.x = value1;
      this.y = value2;
   }

   public void set(float value1, float value2) {
      this.x = value1;
      this.y = value2;
   }

   public Vector2f normalize() {
      float value1 = this.x * this.x + this.y * this.y;
      if (value1 > 0.0F) {
         value1 = 1.0F / (float)Math.sqrt(value1);
         this.x *= value1;
         this.y *= value1;
      }

      return this;
   }
}
