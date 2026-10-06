package com.moonsworth.lunar.client.framework.feature.pkg;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.moonsworth.lunar.bridge.Bridge2_46;
import com.moonsworth.lunar.bridge.Bridge3Extension_3;
import com.moonsworth.lunar.bridge.Bridge3_29;
import com.moonsworth.lunar.bridge.Bridge4_6;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.BridgeExtension22_3;
import com.moonsworth.lunar.bridge.BridgeExtension2_2;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.bridge.hitcolor.HitcolorExtension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.client.mod.render.skins3d.Skins3d;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Optional;
import javax.annotation.Nullable;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;

public class Pkg6 {
   private static boolean field1 = false;

   public static void method1(HitcolorExtension var0, GameProfile var1) {
      Bridge5Extension_5 var2 = ThreadModuleDump63.method3().bridge$getPlayer();
      Skins3d var3 = Skins3d.method13();
      if (!var3.isEnabled() || !(Boolean)var3.method28().get()) {
         field1 = false;
      } else if (ThreadModuleDump63.MC_VERSION >= 8 && ThreadModuleDump63.method4().method89().method6()) {
         field1 = false;
      } else {
         Vector3iBridge var4 = var0.bridge$getBlockPos();
         double var5 = var4.bridge$getX() - var2.bridge$getPosX();
         double var7 = var4.bridge$getY() - var2.bridge$getPosY();
         double var9 = var4.bridge$getZ() - var2.bridge$getPosZ();
         double var11 = var5 * var5 + var7 * var7 + var9 * var9;
         field1 = var11 < var3.method14();
         if (field1) {
            var3.method34().method5(var0, var1);
         }
      }
   }

   public static void method2(ItemStackBridge var0, GameProfile var1) {
      Skins3d var2 = Skins3d.method13();
      if (!var2.isEnabled()) {
         field1 = false;
      } else {
         if (!(Boolean)var2.method29().get()) {
            field1 = false;
         } else {
            field1 = true;
            var2.method34().method6(var0, var1);
         }
      }
   }

   public static void method3(BridgeExtension2_2 var0, GameProfile var1) {
      ItemStackRenderStateBridge var2;
      if (ThreadModuleDump63.MC_VERSION >= 28) {
         if (!(var0 instanceof BridgeExtension22_3 var3)) {
            field1 = false;
            return;
         }

         var2 = var3.bridge$getHeadItem();
      } else {
         var2 = var0.bridge$getHeadItem();
      }

      Bridge5Extension_5 var13 = ThreadModuleDump63.method3().bridge$getPlayer();
      Skins3d var4 = Skins3d.method13();
      if (var4.isEnabled() && (Boolean)var4.method29().get()) {
         double var5 = var0.bridge$getPosX() - var13.bridge$getPosX();
         double var7 = var0.bridge$getPosY() - var13.bridge$getPosY();
         double var9 = var0.bridge$getPosZ() - var13.bridge$getPosZ();
         double var11 = var5 * var5 + var7 * var7 + var9 * var9;
         field1 = var11 < var4.method14();
         if (field1) {
            var4.method34().method6(var2, var1);
         }
      } else {
         field1 = false;
      }
   }

   public static void method4(@Nullable GameProfile var0, Bridge2_46 var1, Bridge3_29 var2) {
      Skins3d var3 = Skins3d.method13();
      if (var2 instanceof Bridge3Extension_3 var4) {
         var4.bridge$showHat(true);
         if (var3.isEnabled() && field1) {
            com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 var5 = var3.method34().method7(var0);
            if (var5 != null) {
               var4.bridge$showHat(false);
               var5.method1(var1);
            }
         }
      }
   }

   @Annotation2(min = 6)
   public static void method5(Bridge5_16 var0, Bridge4_6 var1, int var2, @Nullable GameProfile var3) {
      if (field1) {
         field1 = false;
         Skins3d var4 = Skins3d.method13();
         com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 var5 = var4.method34().method7(var3);
         if (var5 != null) {
            var0.bridge$pushPose();
            float var6 = (Float)Skins3d.method13().method30().get();
            var0.bridge$scale(var6, var6, var6);
            if (var5.xRot != 0.0F) {
               var0.bridge$mulPose(new Quaternionf(new AxisAngle4f(var5.xRot, HorsestatsType_2.EAST.getUnitVector())));
            }

            var0.bridge$translate(var5.x, var5.y - 0.01F, var5.z);
            var5.method4(var0, var1, var2, 655360);
            var0.bridge$popPose();
         }
      }
   }

   @Annotation2(max = 5)
   public static void method6(BridgeExtension3_5 var0, GameProfile var1, float var2, float var3, Bridge3_29 var4) {
      if (field1) {
         field1 = false;
         Skins3d var5 = Skins3d.method13();
         com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 var6 = var5.method34().method7(var1);
         if (var6 != null) {
            var0.push();
            Optional var7 = ThreadModuleDump63.method3().bridge$getSkinManager().bridge$getSkinLocation(var1, Type.SKIN);
            float var8 = (Float)Skins3d.method13().method30().get();
            if (var7.isPresent()) {
               var6.method6(var0, var8 / 16.0F, (ResourceLocationBridge)var7.get());
            }

            var0.pop();
         }
      }
   }
}
