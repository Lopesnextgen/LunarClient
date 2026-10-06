package com.moonsworth.lunar.client.render.particle;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.CameraBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.render.particle.HologramSorting;
import com.moonsworth.lunar.client.cosmetics.emote.MorphRenderer;
import com.moonsworth.lunar.client.cosmetics.emote.MorphRenderType;
import com.moonsworth.lunar.client.driver.holograms.HologramsIterator2;
import java.nio.FloatBuffer;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import javax.vecmath.Matrix3f;
import org.lwjgl.BufferUtils;

public class HologramBatchRenderer {
   public static final FloatBuffer field1 = BufferUtils.createFloatBuffer(16);
   public static final float[] field2 = new float[16];

   public HologramBatchRenderer() {
   }

   public static void method1(AbstractRenderContext bridgeextension_90, CameraBridge bridge2_191, List<MorphRenderer> list2) {
      if (!list2.isEmpty()) {
         if (HologramSorting.method1()) {
            list2.sort((arg1x, arg2x) -> {
               double value3x = arg1x.method13(bridge2_191);
               double value5x = arg2x.method13(bridge2_191);
               if (value3x < value5x) {
                  return 1;
               } else {
                  return value3x > value5x ? -1 : 0;
               }
            });
         }

         EnumMap map3 = new EnumMap(MorphRenderType.class);

         for (MorphRenderer holograms85 : list2) {
            map3.computeIfAbsent(holograms85.method11(), arg0x -> new LinkedList<>()).add(holograms85);
         }

         if (HologramsIterator2.field7 && bridgeextension_90.method39()) {
            Bridge.method9().bridge$getGameRenderer().bridge$disableLightmap();
         }

         bridgeextension_90.push();
         List list8 = (List)map3.get(MorphRenderType.PARTICLE);
         if (list8 != null && !list8.isEmpty()) {
            for (MorphRenderer holograms86 : list8) {
               holograms86.method7(bridgeextension_90, bridge2_191);
            }
         }

         List list10 = (List)map3.get(MorphRenderType.BLOCK);
         if (list10 != null && !list10.isEmpty()) {
            for (MorphRenderer holograms87 : list10) {
               holograms87.method7(bridgeextension_90, bridge2_191);
            }
         }

         bridgeextension_90.pop();
      }
   }

   public static void method2(AbstractRenderContext bridgeextension_90, Matrix3f matrix3f1) {
      float value2 = method3(matrix3f1.m00, matrix3f1.m10, matrix3f1.m20);
      float value3 = method3(matrix3f1.m01, matrix3f1.m11, matrix3f1.m21);
      float value4 = method3(matrix3f1.m02, matrix3f1.m12, matrix3f1.m22);
      bridgeextension_90.scale(value2, value3, value4);
      Matrix3f matrix3f5 = new Matrix3f();
      if (value2 != 0.0F) {
         matrix3f5.m00 = matrix3f1.m00 / value2;
         matrix3f5.m10 = matrix3f1.m10 / value2;
         matrix3f5.m20 = matrix3f1.m20 / value2;
      }

      if (value3 != 0.0F) {
         matrix3f5.m01 = matrix3f1.m01 / value3;
         matrix3f5.m11 = matrix3f1.m11 / value3;
         matrix3f5.m21 = matrix3f1.m21 / value3;
      }

      if (value4 != 0.0F) {
         matrix3f5.m02 = matrix3f1.m02 / value4;
         matrix3f5.m12 = matrix3f1.m12 / value4;
         matrix3f5.m22 = matrix3f1.m22 / value4;
      }

      float value6 = (float)Math.atan2(matrix3f5.m20, matrix3f5.m00);
      float value7 = (float)Math.atan2(-matrix3f5.m10, Math.sqrt(matrix3f5.m00 * matrix3f5.m00 + matrix3f5.m20 * matrix3f5.m20));
      float value8 = (float)Math.atan2(matrix3f5.m12, matrix3f5.m11);
      bridgeextension_90.method2((float)Math.toDegrees(value6), 0.0F, 1.0F, 0.0F);
      bridgeextension_90.method2((float)Math.toDegrees(value7), 1.0F, 0.0F, 0.0F);
      bridgeextension_90.method2((float)Math.toDegrees(value8), 0.0F, 0.0F, 1.0F);
   }

   private static float method3(float value0, float value1, float value2) {
      return (float)Math.sqrt(value0 * value0 + value1 * value1 + value2 * value2);
   }
}
