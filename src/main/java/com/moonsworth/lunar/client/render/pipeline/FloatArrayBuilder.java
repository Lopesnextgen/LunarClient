package com.moonsworth.lunar.client.render.pipeline;

public class FloatArrayBuilder {
   public static final FloatArrayBuilder INSTANCE = new FloatArrayBuilder();
   public float[] array = new float[16];
   public int size = 0;

   public FloatArrayBuilder() {
   }

   public void ensureCapacity(int value) {
      int number2 = this.array.length - this.size;
      if (number2 < value) {
         float[] items3 = new float[Math.max(this.array.length * 2, this.size + value)];
         System.arraycopy(this.array, 0, items3, 0, this.array.length);
         this.array = items3;
      }
   }
}
