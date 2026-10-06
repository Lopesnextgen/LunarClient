package com.moonsworth.lunar.bridge;

import java.nio.IntBuffer;

public interface AutoCloseableExtension extends AutoCloseable {
   int bridge$getWidth();

   int bridge$getHeight();

   boolean method1(int var1, int var2);

   boolean method2(int var1, int var2);

   @Override
   void close();

   static AutoCloseableExtension method3(final int value, final int var1, final IntBuffer var2) {
      return new AutoCloseableExtension() {
         @Override
         public int bridge$getWidth() {
            return value;
         }

         @Override
         public int bridge$getHeight() {
            return var1;
         }

         @Override
         public boolean method1(int var1x, int var2x) {
            int var3 = value * var2x + var1x;
            return (var2.get(var3) & 0xFF) != 0;
         }

         @Override
         public boolean method2(int var1x, int var2x) {
            int var3 = value * var2x + var1x;
            return (var2.get(var3) & 0xFF) == 255;
         }

         @Override
         public void close() {
         }
      };
   }
}
