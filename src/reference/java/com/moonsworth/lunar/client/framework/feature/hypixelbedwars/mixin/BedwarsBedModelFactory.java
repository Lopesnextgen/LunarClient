package com.moonsworth.lunar.client.framework.feature.hypixelbedwars.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.bridge.Bridge4_8;
import com.moonsworth.lunar.bridge.BedPartTypeBridge;
import com.moonsworth.lunar.bridge.BakedModelBridge;
import com.moonsworth.lunar.bridge.ModelBuilderBridge;
import com.moonsworth.lunar.bridge.BakedQuadFactoryBridge;
import com.moonsworth.lunar.bridge.minecraft.ModelRotationBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.minecraft.EnumFacingBridge;
import com.moonsworth.lunar.bridge.minecraft.FaceBakeryBridge;
import com.moonsworth.lunar.client.mod.misc.hypixelbedwars.HypixelBedwars;
import com.moonsworth.lunar.client.mod.misc.hypixelbedwars.HypixelBedwars.Type;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import lombok.Generated;
import org.joml.Vector3f;

public class BedwarsBedModelFactory {
   private static final String[] field1 = new String[]{"yellow", "cyan", "white", "pink", "gray", "red", "blue", "green"};
   private static final double field2 = Math.toRadians(45.0);
   private static final HashMap<BlockStateBridge, BakedModelBridge[]> field3 = new HashMap<>();
   private static final List<String> field4 = new ArrayList<>();

   public BedwarsBedModelFactory() {
   }

   public static void method1() {
      for (BakedModelBridge[] items1 : field3.values()) {
         Arrays.fill(items1, null);
      }
   }

   @VersionGate(1)
   public static BakedModelBridge method2(Horsestats20Extension2 horsestats20extension20, BlockStateBridge bridge2_171, BakedModelBridge mixinhelper4_52) {
      if (horsestats20extension20 == null) {
         return mixinhelper4_52;
      }

      if (bridge2_171.bridge$getBlock() == Bridge.method34().method6()) {
         HypixelBedwars hypixelbedwars3 = Ref.method4().method40().method67();
         hypixelbedwars3.method37().method7(true);
         if (!hypixelbedwars3.method37().method5()) {
            return mixinhelper4_52;
         }

         int index4 = -1;
         if (hypixelbedwars3.method17()) {
            String text5 = ((Type)hypixelbedwars3.method25().get()).id();

            for (int index6 = 0; index6 < field1.length; index6++) {
               String text7 = field1[index6];
               if (Objects.equals(text7, text5)) {
                  index4 = index6;
                  break;
               }
            }
         }

         if (index4 == -1) {
            double value22 = Math.atan2(horsestats20extension20.bridge$getZ(), horsestats20extension20.bridge$getX());
            value22 += Math.PI * 4;
            index4 = (int)(value22 / field2) % 8;
            index4 = hypixelbedwars3.method37().method6()[index4];
         }

         BakedModelBridge[] items24 = field3.get(bridge2_171);
         if (items24 == null) {
            return mixinhelper4_52;
         }

         BakedModelBridge mixinhelper4_525 = items24[index4];
         if (mixinhelper4_525 != null) {
            return mixinhelper4_525;
         }

         if (mixinhelper4_52 == null) {
            return null;
         }

         FaceBakeryBridge horsestats726 = Bridge.method26();
         ModelBuilderBridge mixinhelper6_68 = horsestats726.method2(true, false, mixinhelper4_52.bridge$getItemCameraTransforms());
         EnumFacingBridge horsestats259 = bridge2_171.bridge$getFacingValue();
         BedPartTypeBridge mixinhelper2_1010 = bridge2_171.bridge$getBedPartValue();
         String text11 = "minecraft:blocks/planks_oak";
         String text12 = "lunar:bedwars_coloured_beds/" + field1[index4];
         Bridge4_8 bridge4_813 = Bridge.method9().bridge$getTextureMap().bridge$getAtlasSprite(text11);
         Bridge4_8 bridge4_814 = Bridge.method9().bridge$getTextureMap().bridge$getAtlasSprite(text12);
         mixinhelper6_68.bridge$setParticleTexture(bridge4_814);
         mixinhelper6_68.impl$addFaceBreakingFours(mixinhelper4_52, bridge4_814);
         BakedQuadFactoryBridge mixinhelper7_215 = Bridge.method26().method1();
         ModelRotationBridge horsestats216 = horsestats726.method3(0, horsestats259.bridge$horizontalIndex() * 90 - 180);
         int number17 = mixinhelper2_1010.bridge$isFoot() ? 16 : 0;
         float value18 = 0.25F;
         float value19 = 0.25F;
         mixinhelper6_68.bridge$addGeneralQuad(
            mixinhelper7_215.bridge$makeBakedQuad(
               new Vector3f(0.0F, 3.0F, 0.0F),
               new Vector3f(16.0F, 3.0F, 16.0F),
               horsestats726.method4(horsestats726.method6(), -1, text11, new float[]{0.0F, 0.0F, 16.0F, 16.0F}, 0),
               bridge4_813,
               horsestats726.method6(),
               horsestats216
            )
         );
         if (!mixinhelper2_1010.bridge$isFoot()) {
            mixinhelper6_68.bridge$addGeneralQuad(
               mixinhelper7_215.bridge$makeBakedQuad(
                  new Vector3f(0.0F, 0.0F, 0.0F),
                  new Vector3f(16.0F, 9.0F, 0.0F),
                  horsestats726.method4(horsestats726.method7(), -1, text12, new float[]{25.0F * value18, 9.0F * value19, 9.0F * value18, 0.0F * value19}, 0),
                  bridge4_814,
                  horsestats726.method7(),
                  horsestats216
               )
            );
         } else {
            mixinhelper6_68.bridge$addGeneralQuad(
               mixinhelper7_215.bridge$makeBakedQuad(
                  new Vector3f(0.0F, 0.0F, 16.0F),
                  new Vector3f(16.0F, 9.0F, 16.0F),
                  horsestats726.method4(horsestats726.method8(), -1, text12, new float[]{25.0F * value18, 50.0F * value19, 9.0F * value18, 41.0F * value19}, 180),
                  bridge4_814,
                  horsestats726.method8(),
                  horsestats216
               )
            );
         }

         mixinhelper6_68.bridge$addGeneralQuad(
            mixinhelper7_215.bridge$makeBakedQuad(
               new Vector3f(0.0F, 9.0F, 0.0F),
               new Vector3f(16.0F, 9.0F, 16.0F),
               horsestats726.method4(
                  horsestats726.method5(), -1, text12, new float[]{9.0F * value18, 9.0F * value19 + number17 * value19, 25.0F * value18, 25.0F * value19 + number17 * value19}, 0
               ),
               bridge4_814,
               horsestats726.method5(),
               horsestats216
            )
         );
         mixinhelper6_68.bridge$addGeneralQuad(
            mixinhelper7_215.bridge$makeBakedQuad(
               new Vector3f(0.0F, 0.0F, 0.0F),
               new Vector3f(0.0F, 9.0F, 16.0F),
               horsestats726.method4(
                  horsestats726.method9(), -1, text12, new float[]{0.0F * value18, 9.0F * value19 + number17 * value19, 9.0F * value18, 25.0F * value19 + number17 * value19}, 270
               ),
               bridge4_814,
               horsestats726.method9(),
               horsestats216
            )
         );
         mixinhelper6_68.bridge$addGeneralQuad(
            mixinhelper7_215.bridge$makeBakedQuad(
               new Vector3f(16.0F, 0.0F, 0.0F),
               new Vector3f(16.0F, 9.0F, 16.0F),
               horsestats726.method4(
                  horsestats726.method10(), -1, text12, new float[]{25.0F * value18, 9.0F * value19 + number17 * value19, 34.0F * value18, 25.0F * value19 + number17 * value19}, 90
               ),
               bridge4_814,
               horsestats726.method10(),
               horsestats216
            )
         );
         BakedModelBridge mixinhelper4_520 = mixinhelper6_68.bridge$makeBakedModel();
         items24[index4] = mixinhelper4_520;
         return mixinhelper4_520;
      } else {
         return mixinhelper4_52;
      }
   }

   @Generated
   public static List<String> method3() {
      return field4;
   }

   static {
      for (String text3 : field1) {
         field4.add("lunar:bedwars_coloured_beds/" + text3);
      }

      if (Ref.MC_VERSION >= 1) {
         for (BlockStateBridge bridge2_175 : Bridge.method34().method6().bridge$getValidStates()) {
            field3.put(bridge2_175, new BakedModelBridge[8]);
         }
      }
   }
}
