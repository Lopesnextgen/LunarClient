package com.moonsworth.lunar.client.util.click;

public class Click2 {
   public static final Click2 field1 = new Click2();
   public float[] field2 = new float[16];
   public int size = 0;

   public void method1(int value) {
      int var2 = this.field2.length - this.size;
      if (var2 < value) {
         float[] var3 = new float[Math.max(this.field2.length * 2, this.size + value)];
         System.arraycopy(this.field2, 0, var3, 0, this.field2.length);
         this.field2 = var3;
      }
   }
}
