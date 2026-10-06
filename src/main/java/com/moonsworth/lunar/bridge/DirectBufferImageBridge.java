package com.moonsworth.lunar.bridge;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class DirectBufferImageBridge implements AutoCloseableExtension {
   private final byte field1 = -1;
   private final ByteBuffer field2;
   private final int field3;
   private final int field4;

   public DirectBufferImageBridge(ByteBuffer var1, int var2, int var3) {
      this.field2 = var1;
      this.field3 = var2;
      this.field4 = var3;
   }

   public static ByteBuffer method3(int value, int var1) {
      return ByteBuffer.allocateDirect(value * var1 * 4).order(ByteOrder.nativeOrder());
   }

   private byte method4(int var1, int var2) {
      if (var1 >= 0 && var2 >= 0 && var1 < this.field3 && var2 < this.field4) {
         int var3 = (var1 + var2 * this.field3) * 4 + 3;
         return this.field2.get(var3);
      } else {
         throw new IllegalArgumentException(
            "Image coordinates (" + var1 + ", " + var2 + ") out of bounds for image of dimensions " + this.field3 + "x" + this.field4
         );
      }
   }

   @Override
   public int bridge$getWidth() {
      return this.field3;
   }

   @Override
   public int bridge$getHeight() {
      return this.field4;
   }

   @Override
   public boolean method1(int var1, int var2) {
      return this.method4(var1, var2) != 0;
   }

   @Override
   public boolean method2(int var1, int var2) {
      return this.method4(var1, var2) == -1;
   }

   @Override
   public void close() {
   }
}
