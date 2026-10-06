package com.moonsworth.lunar.client.cosmetics.molang;

import com.moonsworth.lunar.ichor.util.KeepName;

public class MolangMathAtan2 implements MolangBuiltin {
   public MolangMathAtan2() {
   }

   @Override
   public boolean method1(int number1) {
      return number1 == 2;
   }

   @Override
   public boolean method3(int number1) {
      return true;
   }

   @KeepName
   public static double call(double value, double value2) {
      return Math.atan2(value, value2);
   }
}
