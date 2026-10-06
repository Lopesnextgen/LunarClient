package com.moonsworth.lunar.client.framework.feature.minimap.mixin;

import org.joml.Vector3ic;

public class Minimap2 {
   public static int method1(int var0) {
      return var0 >> 5;
   }

   public static long method2(int var0, int var1) {
      return var0 & 4294967295L | (var1 & 4294967295L) << 32;
   }

   public static long method3(Vector3ic var0) {
      return method2(var0.x() >> 4, var0.z() >> 4);
   }

   public static long method4(int var0, int var1) {
      return method2(method1(var0), method1(var1));
   }

   public static int method5(long var0) {
      return (int)(var0 & 4294967295L);
   }

   public static int method6(long var0) {
      return (int)(var0 >>> 32 & 4294967295L);
   }
}
