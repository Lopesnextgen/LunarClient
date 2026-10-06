package com.moonsworth.lunar.bridge;

import java.awt.image.BufferedImage;
import java.util.function.Supplier;

public interface Bridge3_27 {
   boolean method1(int var1);

   int getX();

   int getY();

   @com.moonsworth.lunar.ichor.Annotation2(max = 5)
   default void method2(int var1, int var2, int var3) {
      throw new AbstractMethodErrorImpl();
   }

   Object method3(int var1, Supplier<BufferedImage> var2, int var3, int var4);

   void method4(Object var1);

   void method5();
}
