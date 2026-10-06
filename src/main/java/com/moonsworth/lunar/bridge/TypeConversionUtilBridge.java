package com.moonsworth.lunar.bridge;

public class TypeConversionUtilBridge {
   public TypeConversionUtilBridge() {
   }

   public static double method1(float value0) {
      return value0;
   }

   public static float method2(double value0) {
      return (float)value0;
   }

   public static long method3(int number0) {
      return number0;
   }

   public static float method4(int number0) {
      return number0;
   }

   public static float[] method5(int[] items0) {
      float[] items1 = new float[items0.length];

      for (int index2 = 0; index2 < items0.length; index2++) {
         items1[index2] = items0[index2];
      }

      return items1;
   }
}
