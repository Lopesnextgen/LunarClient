package com.moonsworth.lunar.client.cosmetics.molang;

import com.moonsworth.lunar.ichor.util.KeepName;

public class MolangMathMinAngle implements MolangBuiltin {
   public MolangMathMinAngle() {
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
      value = (value % 360.0 + 360.0) % 360.0;
      if (value > 180.0) {
         value -= 360.0;
      }

      return value;
   }
}
