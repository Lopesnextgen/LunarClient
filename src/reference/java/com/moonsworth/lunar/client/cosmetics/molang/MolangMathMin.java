package com.moonsworth.lunar.client.cosmetics.molang;

import com.moonsworth.lunar.ichor.util.KeepName;

public class MolangMathMin implements MolangBuiltin {
   public MolangMathMin() {
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
      return Math.min(value, value2);
   }
}
