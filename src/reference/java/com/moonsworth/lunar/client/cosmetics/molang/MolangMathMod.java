package com.moonsworth.lunar.client.cosmetics.molang;

import com.moonsworth.lunar.ichor.util.KeepName;

public class MolangMathMod implements MolangBuiltin {
   public MolangMathMod() {
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
      return value % value2;
   }
}
