package com.moonsworth.lunar.client.cosmetics.skin;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.moonsworth.lunar.bridge.ModelRendererBridge;
import com.moonsworth.lunar.bridge.ModelHumanoidHeadBridge;
import com.moonsworth.lunar.bridge.ModelSkeletonHeadBridge;
import com.moonsworth.lunar.bridge.VertexConsumerBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.BridgeExtension22_3;
import com.moonsworth.lunar.bridge.EntityLivingStateBridge;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.bridge.tileentity.BlockEntityBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.client.mod.render.skins3d.Skins3d;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Optional;
import javax.annotation.Nullable;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;

public class SkullSkinLayerRenderer {
   private static boolean field1 = false;

   public SkullSkinLayerRenderer() {
   }

   public static void method1(BlockEntityBridge hitcolorextension0, GameProfile gameprofile1) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method3().bridge$getPlayer();
      Skins3d skins3d3 = Skins3d.method13();
      if (!skins3d3.isEnabled() || !(Boolean)skins3d3.method28().get()) {
         field1 = false;
      } else if (Ref.MC_VERSION >= 8 && Ref.method4().method89().method6()) {
         field1 = false;
      } else {
         Vec3iBridge horsestats204 = hitcolorextension0.bridge$getBlockPos();
         double value5 = horsestats204.bridge$getX() - bridge5extension_52.bridge$getPosX();
         double value7 = horsestats204.bridge$getY() - bridge5extension_52.bridge$getPosY();
         double value9 = horsestats204.bridge$getZ() - bridge5extension_52.bridge$getPosZ();
         double value11 = value5 * value5 + value7 * value7 + value9 * value9;
         field1 = value11 < skins3d3.method14();
         if (field1) {
            skins3d3.method34().method5(hitcolorextension0, gameprofile1);
         }
      }
   }

   public static void method2(ItemStackBridge bridgeextension_40, GameProfile gameprofile1) {
      Skins3d skins3d2 = Skins3d.method13();
      if (!skins3d2.isEnabled()) {
         field1 = false;
      } else {
         if (!(Boolean)skins3d2.method29().get()) {
            field1 = false;
         } else {
            field1 = true;
            skins3d2.method34().method6(bridgeextension_40, gameprofile1);
         }
      }
   }

   public static void method3(EntityLivingStateBridge bridgeextension2_20, GameProfile gameprofile1) {
      ItemStackRenderStateBridge mixinhelper_142;
      if (Ref.MC_VERSION >= 28) {
         if (!(bridgeextension2_20 instanceof BridgeExtension22_3 bridgeextension22_33)) {
            field1 = false;
            return;
         }

         mixinhelper_142 = bridgeextension22_33.bridge$getHeadItem();
      } else {
         mixinhelper_142 = bridgeextension2_20.bridge$getHeadItem();
      }

      Bridge5Extension_5 bridge5extension_513 = Ref.method3().bridge$getPlayer();
      Skins3d skins3d4 = Skins3d.method13();
      if (skins3d4.isEnabled() && (Boolean)skins3d4.method29().get()) {
         double value5 = bridgeextension2_20.bridge$getPosX() - bridge5extension_513.bridge$getPosX();
         double value7 = bridgeextension2_20.bridge$getPosY() - bridge5extension_513.bridge$getPosY();
         double value9 = bridgeextension2_20.bridge$getPosZ() - bridge5extension_513.bridge$getPosZ();
         double value11 = value5 * value5 + value7 * value7 + value9 * value9;
         field1 = value11 < skins3d4.method14();
         if (field1) {
            skins3d4.method34().method6(mixinhelper_142, gameprofile1);
         }
      } else {
         field1 = false;
      }
   }

   public static void method4(@Nullable GameProfile gameprofile0, ModelRendererBridge bridge2_461, ModelSkeletonHeadBridge bridge3_292) {
      Skins3d skins3d3 = Skins3d.method13();
      if (bridge3_292 instanceof ModelHumanoidHeadBridge bridge3extension_34) {
         bridge3extension_34.bridge$showHat(true);
         if (skins3d3.isEnabled() && field1) {
            com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 pkg35 = skins3d3.method34().method7(gameprofile0);
            if (pkg35 != null) {
               bridge3extension_34.bridge$showHat(false);
               pkg35.method1(bridge2_461);
            }
         }
      }
   }

   @VersionGate(min = 6)
   public static void method5(Bridge5_16 bridge5_160, VertexConsumerBridge bridge4_61, int number2, @Nullable GameProfile gameprofile3) {
      if (field1) {
         field1 = false;
         Skins3d skins3d4 = Skins3d.method13();
         com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 pkg35 = skins3d4.method34().method7(gameprofile3);
         if (pkg35 != null) {
            bridge5_160.bridge$pushPose();
            float value6 = (Float)Skins3d.method13().method30().get();
            bridge5_160.bridge$scale(value6, value6, value6);
            if (pkg35.xRot != 0.0F) {
               bridge5_160.bridge$mulPose(new Quaternionf(new AxisAngle4f(pkg35.xRot, HorsestatsType_2.EAST.getUnitVector())));
            }

            bridge5_160.bridge$translate(pkg35.x, pkg35.y - 0.01F, pkg35.z);
            pkg35.method4(bridge5_160, bridge4_61, number2, 655360);
            bridge5_160.bridge$popPose();
         }
      }
   }

   @VersionGate(max = 5)
   public static void method6(BridgeExtension3_5 bridgeextension3_50, GameProfile gameprofile1, float value2, float value3, ModelSkeletonHeadBridge bridge3_294) {
      if (field1) {
         field1 = false;
         Skins3d skins3d5 = Skins3d.method13();
         com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 pkg36 = skins3d5.method34().method7(gameprofile1);
         if (pkg36 != null) {
            bridgeextension3_50.push();
            Optional optional7 = Ref.method3().bridge$getSkinManager().bridge$getSkinLocation(gameprofile1, Type.SKIN);
            float value8 = (Float)Skins3d.method13().method30().get();
            if (optional7.isPresent()) {
               pkg36.method6(bridgeextension3_50, value8 / 16.0F, (ResourceLocationBridge)optional7.get());
            }

            bridgeextension3_50.pop();
         }
      }
   }
}
