package com.moonsworth.lunar.client.cosmetics.molang;

import com.eliotlash.molang.utils.Interpolations;
import com.moonsworth.lunar.ichor.util.KeepName;

public class MolangMathLerpRotate implements MolangBuiltin {
   public MolangMathLerpRotate() {
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
      return Interpolations.lerpYaw(value, value2, value3);
   }
}
