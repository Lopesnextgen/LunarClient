package com.moonsworth.lunar.client.cosmetics.molang;

import com.moonsworth.lunar.ichor.util.KeepName;

public class MolangMathTan implements MolangBuiltin {
   public MolangMathTan() {
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
      return Math.tan(value / 180.0 * Math.PI);
   }
}
