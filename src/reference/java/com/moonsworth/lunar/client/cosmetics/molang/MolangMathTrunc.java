package com.moonsworth.lunar.client.cosmetics.molang;

import com.moonsworth.lunar.ichor.util.KeepName;

public class MolangMathTrunc implements MolangBuiltin {
   public MolangMathTrunc() {
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
      return value < 0.0 ? Math.ceil(value) : Math.floor(value);
   }
}
