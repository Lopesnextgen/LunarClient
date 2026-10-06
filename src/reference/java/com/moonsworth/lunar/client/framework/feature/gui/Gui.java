package com.moonsworth.lunar.client.framework.feature.gui;

import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Gui {
   private static final float field1 = 0.6F;
   private static volatile int generation;
   private static final Map<int[], Gui.Data4> field2 = new ConcurrentHashMap<>();

   private Gui() {
   }

   public static void method1() {
      generation++;
   }

   public static void reset() {
      field2.clear();
   }

   public static boolean method2(float var0, float var1) {
      return var1 - var0 >= 0.6F;
   }

   public static float method3(OverlayMod var0, float var1, float var2) {
      return var2 + (var1 - var2) * (Float)var0.getFireBlockHeight().get();
   }

   public static void method4(OverlayMod var0, int[] var1, int var2) {
      int var3 = generation;
      Gui.Data4 var4 = field2.get(var1);
      if (var4 == null || var4.generation != var3) {
         boolean var5 = var0.method24();
         if (var4 == null) {
            if (!var5) {
               return;
            }

            var4 = field2.computeIfAbsent(
               var1,
               var1x -> new Gui.Data4(
                  new float[]{
                     Float.intBitsToFloat(var1x[0 * var2 + 1]),
                     Float.intBitsToFloat(var1x[1 * var2 + 1]),
                     Float.intBitsToFloat(var1x[2 * var2 + 1]),
                     Float.intBitsToFloat(var1x[3 * var2 + 1])
                  }
               )
            );
         }

         float[] var6 = var4.field1;
         float var7 = Math.min(Math.min(var6[0], var6[1]), Math.min(var6[2], var6[3]));
         float var8 = Math.max(Math.max(var6[0], var6[1]), Math.max(var6[2], var6[3]));
         boolean var9 = var5 && method2(var7, var8);

         for (int var10 = 0; var10 < 4; var10++) {
            var1[var10 * var2 + 1] = Float.floatToRawIntBits(var9 ? method3(var0, var6[var10], var7) : var6[var10]);
         }

         var4.generation = var3;
      }
   }

   private static final class Data4 {
      private final float[] field1;
      private volatile int generation = -1;

      private Data4(float[] var1) {
         this.field1 = var1;
      }
   }
}
