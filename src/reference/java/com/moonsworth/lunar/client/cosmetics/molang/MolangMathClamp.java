package com.moonsworth.lunar.client.cosmetics.molang;

import com.moonsworth.lunar.client.render.particle.ClampUtils;
import com.moonsworth.lunar.ichor.util.KeepName;

public class MolangMathClamp implements MolangBuiltin {
   public MolangMathClamp() {
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
      return ClampUtils.clamp(value, value2, value3);
   }
}
