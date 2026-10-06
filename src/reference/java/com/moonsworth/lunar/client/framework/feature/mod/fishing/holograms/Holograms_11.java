package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.horsestats.mixin.HorsestatsType2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Random;

public class Holograms_11 {
   private static final double field1 = 1.0;
   private static final double field2 = 0.02;
   private static final Random field3 = new Random();

   public static void method1(int[] var0, int[] items, double[] var2, HorsestatsType2 horsestatsType2) {
      double var4 = Math.sqrt(Math.pow(var0[0] - items[0], 2.0) + Math.pow(var0[1] - items[1], 2.0) + Math.pow(var0[2] - items[2], 2.0));
      int var6 = (int)Math.ceil(var4 * 1.0);
      double var7 = (items[0] - var0[0]) / var4 * 0.02;
      double var9 = (items[1] - var0[1]) / var4 * 0.02;
      double var11 = (items[2] - var0[2]) / var4 * 0.02;

      for (int var13 = 0; var13 < var6; var13++) {
         double var14 = var13 + field3.nextDouble();
         double var16 = var14 / var6;
         double var18 = var0[0] * (1.0 - var16) + items[0] * var16 + var2[0];
         double var20 = var0[1] * (1.0 - var16) + items[1] * var16 + var2[1];
         double var22 = var0[2] * (1.0 - var16) + items[2] * var16 + var2[2];
         method2(var18, var20, var22, var7, var9, var11, horsestatsType2);
      }
   }

   private static void method2(double var0, double var2, double var4, double var6, double value, double value2, HorsestatsType2 horsestatsType2) {
      ThreadModuleDump63.method8().bridge$spawnParticle(horsestatsType2, true, var0, var2, var4, var6, value, value2, new int[0]);
   }
}
