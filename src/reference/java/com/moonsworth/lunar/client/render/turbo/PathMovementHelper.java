package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import java.util.concurrent.ThreadLocalRandom;
import org.joml.Math;

public class PathMovementHelper {
   public static void method1(BridgeExtension var0, Vec3Bridge var1) {
      method2(var0, var1, true);
   }

   public static void method2(BridgeExtension var0, Vec3Bridge var1, boolean var2) {
      Vec3Bridge var3 = var0.bridge$getEyePosition();
      double var4 = var3.bridge$xCoord() - var1.bridge$xCoord();
      double var6 = var3.bridge$yCoord() - var1.bridge$yCoord();
      double var8 = var3.bridge$zCoord() - var1.bridge$zCoord();
      double var10 = wrapDegrees((float)(Math.atan2(var8, var4) * 180.0F / (float)java.lang.Math.PI) + 90.0F);
      if (var2) {
         double var12 = Math.sqrt(var4 * var4 + var8 * var8);
         double var14 = 360.0F - wrapDegrees((float)(-(Math.atan2(var6, var12) * 180.0F / (float)java.lang.Math.PI)));
         var0.bridge$setRotationPitch(var14);
         var0.bridge$setPreviousRotationPitch(var14);
      }

      if (var0 instanceof BridgeExtension2_5 var16) {
         double var13 = 0.0;
         if (var10 > 30.0) {
            var13 = 360.0 - (var10 - 30.0);
            var10 = 30.0;
         } else if (var10 < 30.0) {
            var13 = 360.0 - (var10 + 30.0);
            var10 = -30.0;
         }

         var16.bridge$setRotationYawHead((float)var10);
         var16.bridge$setPrevRotationYawHead((float)var10);
         if (var13 != 0.0) {
            var0.bridge$setRotationYaw(var13);
            var0.bridge$setPreviousRotationYaw(var13);
         }
      } else {
         double var17 = 360.0 - var10;
         var0.bridge$setRotationYaw(var17);
         var0.bridge$setPreviousRotationYaw(var17);
      }
   }

   public static boolean method3(Itemcounter6 var0, int var1, int var2, int var3, BridgeExtension var4) {
      Bridge2_17 var5 = var0.bridge$getBlockState(var1, var2, var3);
      if (!var5.bridge$getBlock().bridge$isAir()) {
         return false;
      }

      Horsestats20Extension2 var6 = Bridge.method8().method4(var1, var2 - 1, var3);
      Bridge3_23 var7 = var0.method2(var6).bridge$getBlock();
      return var7.bridge$isFoliage() ? false : !var7.bridge$isAir() && var7.bridge$entityCanStandOn(var0, var6, var4);
   }

   public static boolean method4(Itemcounter6Extension var0, Bridge5Extension_5 var1, BridgeExtension var2) {
      if (var1.bridge$isOnGround() && !var1.bridge$isFlying()) {
         int var3 = (int)Math.floor(var1.bridge$getPosX());
         int var4 = (int)Math.floor(var1.bridge$getPosY());
         int var5 = (int)Math.floor(var1.bridge$getPosZ());
         ThreadLocalRandom var6 = ThreadLocalRandom.current();

         for (int var7 = 0; var7 < 6; var7++) {
            boolean var8 = var7 >= 4;
            int var9 = method5(var6, var8 ? -3 : -2, var8 ? 3 : 2);
            int var10 = method5(var6, -1, 1);
            int var11 = method5(var6, var8 ? -3 : -2, var8 ? 3 : 2);
            if (method3(var0, var3 + var9, var4 + var10, var5 + var11, var2)) {
               double var12 = var3 + var9 + 0.5;
               double var14 = var4 + var10;
               double var16 = var5 + var11 + 0.5;
               var2.bridge$setPosX(var12);
               var2.bridge$setPosY(var14);
               var2.bridge$setPosZ(var16);
               var2.bridge$setPreviousPosX(var12);
               var2.bridge$setPreviousPosY(var14);
               var2.bridge$setPreviousPosZ(var16);
               return true;
            }
         }
      }

      return false;
   }

   public static float wrapDegrees(float var0) {
      var0 %= 360.0F;
      if (var0 >= 180.0F) {
         var0 -= 360.0F;
      }

      if (var0 < -180.0F) {
         var0 += 360.0F;
      }

      return var0;
   }

   public static int method5(ThreadLocalRandom var0, int var1, int var2) {
      return var0.nextInt(var2 - var1 + 1) + var1;
   }
}
