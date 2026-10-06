package com.moonsworth.lunar.client.render.particle;

public class ClampUtils {
   public ClampUtils() {
   }

   public static int clamp(int number0, int number1, int number2) {
      return number0 < number1 ? number1 : (number0 > number2 ? number2 : number0);
   }

   public static float clamp(float value0, float value1, float value2) {
      return value0 < value1 ? value1 : (value0 > value2 ? value2 : value0);
   }

   public static double clamp(double value0, double value2, double value4) {
      return value0 < value2 ? value2 : (value0 > value4 ? value4 : value0);
   }

   public static int cycler(int number0, int number1, int number2) {
      return number0 < number1 ? number2 : (number0 > number2 ? number1 : number0);
   }

   public static float cycler(float value0, float value1, float value2) {
      return value0 < value1 ? value2 : (value0 > value2 ? value1 : value0);
   }

   public static double cycler(double value0, double value2, double value4) {
      return value0 < value2 ? value4 : (value0 > value4 ? value2 : value0);
   }
}
