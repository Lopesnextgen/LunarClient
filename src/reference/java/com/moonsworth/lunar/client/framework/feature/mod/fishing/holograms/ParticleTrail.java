package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Random;

public class ParticleTrail {
   private static final double field1 = 1.0;
   private static final double field2 = 0.02;
   private static final Random field3 = new Random();

   public ParticleTrail() {
   }

   public static void method1(int[] items0, int[] items1, double[] items2, ParticleType horsestatstype23) {
      double value4 = Math.sqrt(Math.pow(items0[0] - items1[0], 2.0) + Math.pow(items0[1] - items1[1], 2.0) + Math.pow(items0[2] - items1[2], 2.0));
      int number6 = (int)Math.ceil(value4 * 1.0);
      double value7 = (items1[0] - items0[0]) / value4 * 0.02;
      double value9 = (items1[1] - items0[1]) / value4 * 0.02;
      double value11 = (items1[2] - items0[2]) / value4 * 0.02;

      for (int index13 = 0; index13 < number6; index13++) {
         double value14 = index13 + field3.nextDouble();
         double value16 = value14 / number6;
         double value18 = items0[0] * (1.0 - value16) + items1[0] * value16 + items2[0];
         double value20 = items0[1] * (1.0 - value16) + items1[1] * value16 + items2[1];
         double value22 = items0[2] * (1.0 - value16) + items1[2] * value16 + items2[2];
         method2(value18, value20, value22, value7, value9, value11, horsestatstype23);
      }
   }

   private static void method2(double value0, double value2, double value4, double value6, double value8, double value10, ParticleType horsestatstype212) {
      Ref.method8().bridge$spawnParticle(horsestatstype212, true, value0, value2, value4, value6, value8, value10, new int[0]);
   }
}
