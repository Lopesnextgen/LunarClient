package com.moonsworth.lunar.client.cosmetics.molang;

import com.moonsworth.lunar.ichor.util.KeepName;

public class MolangMathSign implements MolangBuiltin {
   public MolangMathSign() {
   }

   @Override
   public boolean method1(int number1) {
      return number1 == 1;
   }

   @Override
   public boolean method3(int number1) {
      return true;
   }

   @KeepName
   public static double call(double value) {
      return value >= 0.0 ? 1.0 : -1.0;
   }
}
