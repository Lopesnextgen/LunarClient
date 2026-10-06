package com.moonsworth.lunar.client.framework.feature.overlay;

import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TextureAnimationCache {
   private static final float field1 = 0.6F;
   private static volatile int generation;
   private static final Map<int[], TextureAnimationCache.AnimationCacheEntry> field2 = new ConcurrentHashMap<>();

   private TextureAnimationCache() {
   }

   public static void method1() {
      generation++;
   }

   public static void reset() {
      field2.clear();
   }

   public static boolean method2(float value, float value1) {
      return value1 - value >= 0.6F;
   }

   public static float method3(OverlayMod overlaymod0, float value1, float value) {
      return value + (value1 - value) * (Float)overlaymod0.getFireBlockHeight().get();
   }

   public static void method4(OverlayMod overlaymod0, int[] items1, int value) {
      int number3 = generation;
      TextureAnimationCache.AnimationCacheEntry data44 = field2.get(items1);
      if (data44 == null || data44.generation != number3) {
         boolean flag5 = overlaymod0.method24();
         if (data44 == null) {
            if (!flag5) {
               return;
            }

            data44 = field2.computeIfAbsent(
               items1,
               arg1x -> new TextureAnimationCache.AnimationCacheEntry(
                  new float[]{
                     Float.intBitsToFloat(arg1x[0 * value + 1]),
                     Float.intBitsToFloat(arg1x[1 * value + 1]),
                     Float.intBitsToFloat(arg1x[2 * value + 1]),
                     Float.intBitsToFloat(arg1x[3 * value + 1])
                  }
               )
            );
         }

         float[] items6 = data44.field1;
         float value7 = Math.min(Math.min(items6[0], items6[1]), Math.min(items6[2], items6[3]));
         float value8 = Math.max(Math.max(items6[0], items6[1]), Math.max(items6[2], items6[3]));
         boolean flag9 = flag5 && method2(value7, value8);

         for (int index10 = 0; index10 < 4; index10++) {
            items1[index10 * value + 1] = Float.floatToRawIntBits(flag9 ? method3(overlaymod0, items6[index10], value7) : items6[index10]);
         }

         data44.generation = number3;
      }
   }

   private static final class AnimationCacheEntry {
      private final float[] field1;
      private volatile int generation = -1;

      private AnimationCacheEntry(float[] items1) {
         this.field1 = items1;
      }
   }
}
