package com.moonsworth.lunar.client.cosmetics.molang;

import com.moonsworth.lunar.ichor.util.KeepName;
import java.util.Random;

public class MolangMathRandomInteger implements MolangBuiltin {
   public Random random = new Random();

   public MolangMathRandomInteger() {
   }

   @Override
   public boolean method1(int number1) {
      return number1 >= 1 && number1 <= 2;
   }

   @Override
   public boolean method3(int number1) {
      return number1 < 3;
   }

   @Override
   public boolean method2(int number1) {
      return true;
   }

   @KeepName
   public static double call(double value0) {
      double value2 = Math.random() * value0;
      return Math.floor(value2);
   }

   @KeepName
   public static double call(double value0, double value2) {
      double value4 = Math.random();
      double value6 = Math.min(value0, value2);
      double value8 = Math.max(value0, value2);
      value4 = value4 * (value8 - value6) + value6;
      return Math.floor(value4);
   }

   @KeepName
   public double call(double value, double value2, double value3) {
      this.random.setSeed((long)value3);
      double value7 = this.random.nextDouble();
      double value9 = Math.min(value, value2);
      double value11 = Math.max(value, value2);
      value7 = value7 * (value11 - value9) + value9;
      return Math.floor(value7);
   }
}
