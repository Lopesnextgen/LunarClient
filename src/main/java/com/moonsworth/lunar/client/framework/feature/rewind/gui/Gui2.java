package com.moonsworth.lunar.client.framework.feature.rewind.gui;

public class Gui2 {
   public static double method1(double var0, double value) {
      double var4 = Math.abs(value - var0);
      if (var4 > 180.0) {
         var0 += var0 < value ? 360.0 : -360.0;
      }

      return var0;
   }

   public static double method2(double var0) {
      return (var0 % 360.0 + 360.0) % 360.0;
   }
}
