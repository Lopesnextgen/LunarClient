package com.moonsworth.lunar.client.render.shader;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager.Data;
import com.moonsworth.lunar.client.render.shader.GlslBuiltin;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import com.moonsworth.lunar.client.cosmetics.ShaderCloakRenderer;

public class ShaderUniformUpdater {
   private static final float field1 = 0.05F;
   public static final Map<GlslBuiltin, BiConsumer<ShaderCloakRenderer, Bridge6_10>> field2 = new EnumMap<>(GlslBuiltin.class);
   public static final Map<GlslBuiltin, Consumer<ShaderCloakRenderer>> field3 = new EnumMap<>(GlslBuiltin.class);

   public ShaderUniformUpdater() {
   }

   static {
      field3.put(
         GlslBuiltin.TIME_SECONDS,
         arg0 -> arg0.method4(GlslBuiltin.TIME_SECONDS, (float)(System.currentTimeMillis() - arg0.getStartTime()) / 1000.0F)
      );
      field3.put(
         GlslBuiltin.CAMERA_ROTATION,
         arg0 -> {
            double value1 = Ref.method13().bridge$playerViewX();
            double value3 = Ref.method13().bridge$playerViewY();
            float value5 = (float)Math.toRadians(value1);
            float value6 = (float)Math.toRadians(value3);
            arg0.method4(
               GlslBuiltin.CAMERA_ROTATION,
               (float)(-(Math.cos(value5) * Math.sin(value6))),
               (float)(-Math.sin(value5)),
               (float)(Math.cos(value5) * Math.cos(value6))
            );
         }
      );
      field2.put(GlslBuiltin.GAME_TIME, (arg0, arg1) -> {
         float value2 = Ref.method8() == null ? 0.0F : (float)Ref.method8().bridge$getDayTime();
         arg0.method4(GlslBuiltin.GAME_TIME, value2);
      });
      field2.put(
         GlslBuiltin.VELOCITY,
         (arg0, arg1) -> {
            if (arg1 != null) {
               arg0.method4(
                  GlslBuiltin.VELOCITY,
                  (float)(arg1.bridge$getPosX() - arg1.bridge$lastTickX()) / 0.05F,
                  (float)(arg1.bridge$getPosY() - arg1.bridge$lastTickY()) / 0.05F,
                  (float)(arg1.bridge$getPosZ() - arg1.bridge$lastTickZ()) / 0.05F
               );
            }
         }
      );
      field2.put(GlslBuiltin.VELOCITY_SMOOTH, (arg0, arg1) -> {
         if (arg1 != null) {
            PositionHistory fov7_22 = arg0.method43();
            if (fov7_22 == null) {
               fov7_22 = new PositionHistory();
               arg0.method44(fov7_22);
            }

            double value3 = (arg1.bridge$getPosX() - arg1.bridge$lastTickX()) / 0.05F;
            double value5 = (arg1.bridge$getPosY() - arg1.bridge$lastTickY()) / 0.05F;
            double value7 = (arg1.bridge$getPosZ() - arg1.bridge$lastTickZ()) / 0.05F;
            fov7_22.method1(value3, value5, value7);
            double[] items9 = fov7_22.method3();
            arg0.method4(GlslBuiltin.VELOCITY_SMOOTH, (float)items9[0], (float)items9[1], (float)items9[2]);
         }
      });
      field2.put(
         GlslBuiltin.LUNAR_PLUS_COLOR,
         (arg0, arg1) -> {
            if (arg1 == null) {
               arg0.method4(GlslBuiltin.LUNAR_PLUS_COLOR, 0.0F, 0.8980392F, 0.09803922F);
            } else {
               Data data2 = (Data)Ref.method4().method53().method63().get(arg1.bridge$getUniqueID());
               int number3 = data2 == null ? -16777216 : data2.method9();
               arg0.method4(
                  GlslBuiltin.LUNAR_PLUS_COLOR, ColorUtils.method5(number3), ColorUtils.method6(number3), ColorUtils.method7(number3)
               );
            }
         }
      );
      field2.put(
         GlslBuiltin.BIOME_COLOR,
         (arg0, arg1) -> {
            if (arg1 == null) {
               arg0.method4(GlslBuiltin.BIOME_COLOR, 0.46666667F, 0.67058825F, 0.18431373F);
            } else {
               Itemcounter6 itemcounter62 = arg1.bridge$getWorld();
               if (itemcounter62 == null) {
                  arg0.method4(GlslBuiltin.BIOME_COLOR, 0.46666667F, 0.67058825F, 0.18431373F);
               } else {
                  int number3 = itemcounter62.bridge$getFoliageColor(arg1.bridge$getBlockPos());
                  arg0.method4(
                     GlslBuiltin.BIOME_COLOR, ColorUtils.method5(number3), ColorUtils.method6(number3), ColorUtils.method7(number3)
                  );
               }
            }
         }
      );
   }
}
