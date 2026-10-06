package com.moonsworth.lunar.client.framework.feature.waila;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge8_2;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;

public class WailaHandler4 implements Waila {
   private final BridgeExtension2_5 field1;

   public WailaHandler4(BridgeExtension2_5 var1) {
      this.field1 = var1;
   }

   @Override
   public int getWidth() {
      return (int)(this.field1.bridge$getBoundingBox().bridge$getMaxX() - this.field1.bridge$getBoundingBox().bridge$getMinX());
   }

   @Override
   public int getHeight() {
      return (int)(this.field1.bridge$getBoundingBox().bridge$getMaxY() - this.field1.bridge$getBoundingBox().bridge$getMinY());
   }

   @Override
   public void method1(MixinHelper_4 var1, com.moonsworth.lunar.client.mod.hud.waila.Waila var2, int var3, int var4) {
      method3(
         var1,
         var3,
         var4,
         (float)(this.field1.bridge$getBoundingBox().bridge$getMaxX() - this.field1.bridge$getBoundingBox().bridge$getMinX()),
         0.0F,
         -90.0F,
         -45.0F,
         this.field1
      );
      method3(var1, var3, var4, this.getHeight(), 0.0F, -90.0F, -45.0F, this.field1);
   }

   @Annotation2(min = 30)
   private static void method2(MixinHelper_4 var0, float var1, float var2, float var3, float var4, float var5, BridgeExtension2_5 var6) {
      Bridge8_2 var7 = var0.method49().orElseThrow();
      int var8 = (int)var3;
      int var9 = (int)var1 - var8;
      int var10 = (int)var2 - var8 * 2;
      int var11 = (int)var1 + var8;
      int var12 = (int)var2;
      float var13 = (var9 + var11) / 2.0F - var4;
      float var14 = (var10 + var12) / 2.0F - var5;
      var7.bridge$submitEntity$v1_21_6(var6, var9, var10, var11, var12, var8, 0.0F, var13, var14);
   }

   private static void method3(MixinHelper_4 var0, float var1, float var2, float var3, float var4, float var5, float var6, BridgeExtension2_5 var7) {
      if (var0 instanceof LegacyGuiGraphicsBridge var8) {
         AbstractRenderContext var9 = var8.method29();
         var9.method22();
         var9.method18();
         if (Bridge.getMinecraftVersion().method21()) {
            var9.method26();
         }

         var9.method25(1.0F, 1.0F, 1.0F, 1.0F);
         var9.push();
         var9.translate(var1, var2, 100.0);
         var9.scale(-var3, var3, var3);
         var9.method4(180.0F, 0.0F, 0.0F, 1.0F);
         float var10 = var7.bridge$getBodyRot();
         float var11 = (float)var7.bridge$getRotationYaw();
         float var12 = (float)var7.bridge$getRotationPitch();
         float var13 = var7.bridge$getPrevRotationYawHead();
         float var14 = var7.bridge$getRotationYawHead();
         var9.method4(135.0F, 0.0F, 1.0F, 0.0F);
         var9.method6(var0x -> Bridge.method14().method1());
         var9.method4(-135.0F, 0.0F, 1.0F, 0.0F);
         var9.method4(-((float)Math.atan(var6 / 40.0F)) * 20.0F, 1.0F, 0.0F, 0.0F);
         var7.bridge$setBodyYRot((float)Math.atan(var5 / 40.0F) * 20.0F - var4);
         var7.bridge$setRotationYaw((float)Math.atan(var5 / 40.0F) * 40.0F - var4);
         var7.bridge$setRotationPitch(var7.bridge$getRotationPitch() - (float)Math.atan(var6 / 40.0F) * 20.0F);
         var7.bridge$setRotationYawHead((float)var7.bridge$getRotationYaw());
         var7.bridge$setPrevRotationYawHead((float)var7.bridge$getRotationYaw());
         var9.translate(0.0, 0.0, 0.0);
         Bridge2_43 var15 = ThreadModuleDump63.method3().bridge$getEntityRenderDispatcher();
         var15.bridge$setPlayerViewY(180.0F);
         if (ThreadModuleDump63.MC_VERSION <= 32) {
            var15.bridge$setRenderShadow(false);
         }

         var15.bridge$renderEntityWithPosYaw(var9, var7, 0.0, 0.0, 0.0, 0.0F, 1.0F, Bridge.method8().method92());
         if (ThreadModuleDump63.MC_VERSION <= 32) {
            var15.bridge$setRenderShadow(true);
         }

         var7.bridge$setBodyYRot(var10);
         var7.bridge$setRotationYaw(var11);
         var7.bridge$setRotationPitch(var12);
         var7.bridge$setPrevRotationYawHead(var13);
         var7.bridge$setRotationYawHead(var14);
         var9.pop();
         var9.method23();
         Bridge.method14().method3();
         var9.method6(var1x -> {
            var9.method28(Bridge.method22().method5());
            var9.method13();
            var9.method28(Bridge.method22().method6());
         });
      } else {
         if (ThreadModuleDump63.MC_VERSION < 30) {
            throw new IllegalStateException();
         }

         method2(var0, var1, var2, var3, var5, var6, var7);
      }
   }

   public BridgeExtension2_5 method4() {
      return this.field1;
   }
}
