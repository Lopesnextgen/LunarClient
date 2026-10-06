package com.moonsworth.lunar.client.framework.feature.hypixelbedwars.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Bridge4_8;
import com.moonsworth.lunar.bridge.MixinHelper2_10;
import com.moonsworth.lunar.bridge.BakedModelExtension;
import com.moonsworth.lunar.bridge.ModelBuilderExtension;
import com.moonsworth.lunar.bridge.QuadFactoryExtension;
import com.moonsworth.lunar.bridge.horsestats.BlockModelRotationBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.FacingIndexBridge;
import com.moonsworth.lunar.bridge.horsestats.ModelFaceBakeryBridge;
import com.moonsworth.lunar.client.mod.misc.hypixelbedwars.HypixelBedwars;
import com.moonsworth.lunar.client.mod.misc.hypixelbedwars.HypixelBedwars.Type;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import lombok.Generated;
import org.joml.Vector3f;

public class Hypixelbedwars {
   private static final String[] field1 = new String[]{"yellow", "cyan", "white", "pink", "gray", "red", "blue", "green"};
   private static final double field2 = Math.toRadians(45.0);
   private static final HashMap<Bridge2_17, BakedModelExtension[]> field3 = new HashMap<>();
   private static final List<String> field4 = new ArrayList<>();

   public static void method1() {
      for (BakedModelExtension[] var1 : field3.values()) {
         Arrays.fill(var1, null);
      }
   }

   @Annotation2(1)
   public static BakedModelExtension method2(Horsestats20Extension2 var0, Bridge2_17 var1, BakedModelExtension var2) {
      if (var0 == null) {
         return var2;
      }

      if (var1.bridge$getBlock() == Bridge.method34().method6()) {
         HypixelBedwars var3 = ThreadModuleDump63.method4().method40().method67();
         var3.method37().method7(true);
         if (!var3.method37().method5()) {
            return var2;
         }

         int var4 = -1;
         if (var3.method17()) {
            String var5 = ((Type)var3.method25().get()).id();

            for (int var6 = 0; var6 < field1.length; var6++) {
               String var7 = field1[var6];
               if (Objects.equals(var7, var5)) {
                  var4 = var6;
                  break;
               }
            }
         }

         if (var4 == -1) {
            double var22 = Math.atan2(var0.bridge$getZ(), var0.bridge$getX());
            var22 += Math.PI * 4;
            var4 = (int)(var22 / field2) % 8;
            var4 = var3.method37().method6()[var4];
         }

         BakedModelExtension[] var24 = field3.get(var1);
         if (var24 == null) {
            return var2;
         }

         BakedModelExtension var25 = var24[var4];
         if (var25 != null) {
            return var25;
         }

         if (var2 == null) {
            return null;
         }

         ModelFaceBakeryBridge var26 = Bridge.method26();
         ModelBuilderExtension var8 = var26.method2(true, false, var2.bridge$getItemCameraTransforms());
         FacingIndexBridge var9 = var1.bridge$getFacingValue();
         MixinHelper2_10 var10 = var1.bridge$getBedPartValue();
         String var11 = "minecraft:blocks/planks_oak";
         String var12 = "lunar:bedwars_coloured_beds/" + field1[var4];
         Bridge4_8 var13 = Bridge.method9().bridge$getTextureMap().bridge$getAtlasSprite(var11);
         Bridge4_8 var14 = Bridge.method9().bridge$getTextureMap().bridge$getAtlasSprite(var12);
         var8.bridge$setParticleTexture(var14);
         var8.impl$addFaceBreakingFours(var2, var14);
         QuadFactoryExtension var15 = Bridge.method26().method1();
         BlockModelRotationBridge var16 = var26.method3(0, var9.bridge$horizontalIndex() * 90 - 180);
         int var17 = var10.bridge$isFoot() ? 16 : 0;
         float var18 = 0.25F;
         float var19 = 0.25F;
         var8.bridge$addGeneralQuad(
            var15.bridge$makeBakedQuad(
               new Vector3f(0.0F, 3.0F, 0.0F),
               new Vector3f(16.0F, 3.0F, 16.0F),
               var26.method4(var26.method6(), -1, var11, new float[]{0.0F, 0.0F, 16.0F, 16.0F}, 0),
               var13,
               var26.method6(),
               var16
            )
         );
         if (!var10.bridge$isFoot()) {
            var8.bridge$addGeneralQuad(
               var15.bridge$makeBakedQuad(
                  new Vector3f(0.0F, 0.0F, 0.0F),
                  new Vector3f(16.0F, 9.0F, 0.0F),
                  var26.method4(var26.method7(), -1, var12, new float[]{25.0F * var18, 9.0F * var19, 9.0F * var18, 0.0F * var19}, 0),
                  var14,
                  var26.method7(),
                  var16
               )
            );
         } else {
            var8.bridge$addGeneralQuad(
               var15.bridge$makeBakedQuad(
                  new Vector3f(0.0F, 0.0F, 16.0F),
                  new Vector3f(16.0F, 9.0F, 16.0F),
                  var26.method4(var26.method8(), -1, var12, new float[]{25.0F * var18, 50.0F * var19, 9.0F * var18, 41.0F * var19}, 180),
                  var14,
                  var26.method8(),
                  var16
               )
            );
         }

         var8.bridge$addGeneralQuad(
            var15.bridge$makeBakedQuad(
               new Vector3f(0.0F, 9.0F, 0.0F),
               new Vector3f(16.0F, 9.0F, 16.0F),
               var26.method4(
                  var26.method5(), -1, var12, new float[]{9.0F * var18, 9.0F * var19 + var17 * var19, 25.0F * var18, 25.0F * var19 + var17 * var19}, 0
               ),
               var14,
               var26.method5(),
               var16
            )
         );
         var8.bridge$addGeneralQuad(
            var15.bridge$makeBakedQuad(
               new Vector3f(0.0F, 0.0F, 0.0F),
               new Vector3f(0.0F, 9.0F, 16.0F),
               var26.method4(
                  var26.method9(), -1, var12, new float[]{0.0F * var18, 9.0F * var19 + var17 * var19, 9.0F * var18, 25.0F * var19 + var17 * var19}, 270
               ),
               var14,
               var26.method9(),
               var16
            )
         );
         var8.bridge$addGeneralQuad(
            var15.bridge$makeBakedQuad(
               new Vector3f(16.0F, 0.0F, 0.0F),
               new Vector3f(16.0F, 9.0F, 16.0F),
               var26.method4(
                  var26.method10(), -1, var12, new float[]{25.0F * var18, 9.0F * var19 + var17 * var19, 34.0F * var18, 25.0F * var19 + var17 * var19}, 90
               ),
               var14,
               var26.method10(),
               var16
            )
         );
         BakedModelExtension var20 = var8.bridge$makeBakedModel();
         var24[var4] = var20;
         return var20;
      } else {
         return var2;
      }
   }

   @Generated
   public static List<String> method3() {
      return field4;
   }

   static {
      for (String var3 : field1) {
         field4.add("lunar:bedwars_coloured_beds/" + var3);
      }

      if (ThreadModuleDump63.MC_VERSION >= 1) {
         for (Bridge2_17 var5 : Bridge.method34().method6().bridge$getValidStates()) {
            field3.put(var5, new BakedModelExtension[8]);
         }
      }
   }
}
