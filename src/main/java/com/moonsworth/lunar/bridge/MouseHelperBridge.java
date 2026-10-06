package com.moonsworth.lunar.bridge;

import java.awt.image.BufferedImage;
import java.util.function.Supplier;

public interface MouseHelperBridge {
   boolean method1(int number1);

   int getX();

   int getY();

   @com.moonsworth.lunar.ichor.VersionGate(max = 5)
   default void method2(int number1, int value, int number3) {
      throw new AbstractMethodErrorImpl();
   }

   Object method3(int number1, Supplier<BufferedImage> supplier2, int number3, int number4);

   void method4(Object obj1);

   void method5();
}
