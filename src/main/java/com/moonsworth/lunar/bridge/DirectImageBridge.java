package com.moonsworth.lunar.bridge;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class DirectImageBridge implements AutoCloseableExtension {
   private final byte field1 = -1;
   private final ByteBuffer field2;
   private final int field3;
   private final int field4;

   public DirectImageBridge(ByteBuffer buffer1, int number2, int value) {
      this.field2 = buffer1;
      this.field3 = number2;
      this.field4 = value;
   }

   public static ByteBuffer method3(int value, int number1) {
      return ByteBuffer.allocateDirect(value * number1 * 4).order(ByteOrder.nativeOrder());
   }

   private byte method4(int number1, int number2) {
      if (number1 >= 0 && number2 >= 0 && number1 < this.field3 && number2 < this.field4) {
         int index3 = (number1 + number2 * this.field3) * 4 + 3;
         return this.field2.get(index3);
      } else {
         throw new IllegalArgumentException(
            "Image coordinates (" + number1 + ", " + number2 + ") out of bounds for image of dimensions " + this.field3 + "x" + this.field4
         );
      }
   }

   public int bridge$getWidth() {
      return this.field3;
   }

   public int bridge$getHeight() {
      return this.field4;
   }

   public boolean method1(int number1, int number2) {
      return this.method4(number1, number2) != 0;
   }

   public boolean method2(int number1, int number2) {
      return this.method4(number1, number2) == -1;
   }

   public void close() {
   }
}
