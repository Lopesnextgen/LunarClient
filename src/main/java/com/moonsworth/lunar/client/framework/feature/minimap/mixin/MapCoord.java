package com.moonsworth.lunar.client.framework.feature.minimap.mixin;

import org.joml.Vector3ic;

public class MapCoord {
   public MapCoord() {
   }

   public static int method1(int number0) {
      return number0 >> 5;
   }

   public static long method2(int number0, int number1) {
      return number0 & 4294967295L | (number1 & 4294967295L) << 32;
   }

   public static long method3(Vector3ic vector3ic0) {
      return method2(vector3ic0.x() >> 4, vector3ic0.z() >> 4);
   }

   public static long method4(int number0, int number1) {
      return method2(method1(number0), method1(number1));
   }

   public static int method5(long number0) {
      return (int)(number0 & 4294967295L);
   }

   public static int method6(long number0) {
      return (int)(number0 >>> 32 & 4294967295L);
   }
}
