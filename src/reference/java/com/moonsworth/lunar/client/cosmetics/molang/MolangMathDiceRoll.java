package com.moonsworth.lunar.client.cosmetics.molang;

import com.moonsworth.lunar.ichor.util.KeepName;
import java.util.concurrent.ThreadLocalRandom;

public class MolangMathDiceRoll implements MolangBuiltin {
   public MolangMathDiceRoll() {
   }

   @Override
   public boolean method1(int number1) {
      return number1 == 3;
   }

   @Override
   public boolean method3(int number1) {
      return true;
   }

   @KeepName
   public static double call(double value, double value2, double value3) {
      double value6 = 0.0;
      if (value > 0.0) {
         for (int index8 = 0; index8 < value; index8++) {
            value6 += ThreadLocalRandom.current().nextDouble() * (value3 - value2) + value2;
         }
      }

      return value6;
   }
}
