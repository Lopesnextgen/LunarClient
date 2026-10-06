package com.moonsworth.lunar.client.framework.feature.waila;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge8_2;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;

public class EntityWailaComponent implements WailaComponent {
   private final EntityLivingBridge field1;

   public EntityWailaComponent(EntityLivingBridge bridgeextension2_51) {
      this.field1 = bridgeextension2_51;
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
   public void method1(MixinHelper_4 mixinhelper_41, com.moonsworth.lunar.client.mod.hud.waila.WailaHud waila2, int number3, int number4) {
      method3(
         mixinhelper_41,
         number3,
         number4,
         (float)(this.field1.bridge$getBoundingBox().bridge$getMaxX() - this.field1.bridge$getBoundingBox().bridge$getMinX()),
         0.0F,
         -90.0F,
         -45.0F,
         this.field1
      );
      method3(mixinhelper_41, number3, number4, this.getHeight(), 0.0F, -90.0F, -45.0F, this.field1);
   }

   @VersionGate(min = 30)
   private static void method2(MixinHelper_4 mixinhelper_40, float value1, float value2, float value3, float value4, float value5, EntityLivingBridge bridgeextension2_56) {
      Bridge8_2 bridge8_27 = (Bridge8_2)mixinhelper_40.method49().orElseThrow();
      int number8 = (int)value3;
      int number9 = (int)value1 - number8;
      int number10 = (int)value2 - number8 * 2;
      int number11 = (int)value1 + number8;
      int number12 = (int)value2;
      float value13 = (number9 + number11) / 2.0F - value4;
      float value14 = (number10 + number12) / 2.0F - value5;
      bridge8_27.bridge$submitEntity$v1_21_6(bridgeextension2_56, number9, number10, number11, number12, number8, 0.0F, value13, value14);
   }

   private static void method3(MixinHelper_4 mixinhelper_40, float value1, float value2, float value3, float value4, float value5, float value6, EntityLivingBridge bridgeextension2_57) {
      if (mixinhelper_40 instanceof LegacyGuiGraphicsBridge mixinhelper58) {
         AbstractRenderContext bridgeextension_99 = mixinhelper58.method29();
         bridgeextension_99.method22();
         bridgeextension_99.method18();
         if (Bridge.getMinecraftVersion().method21()) {
            bridgeextension_99.method26();
         }

         bridgeextension_99.method25(1.0F, 1.0F, 1.0F, 1.0F);
         bridgeextension_99.push();
         bridgeextension_99.translate(value1, value2, 100.0);
         bridgeextension_99.scale(-value3, value3, value3);
         bridgeextension_99.method4(180.0F, 0.0F, 0.0F, 1.0F);
         float value10 = bridgeextension2_57.bridge$getBodyRot();
         float value11 = (float)bridgeextension2_57.bridge$getRotationYaw();
         float value12 = (float)bridgeextension2_57.bridge$getRotationPitch();
         float value13 = bridgeextension2_57.bridge$getPrevRotationYawHead();
         float value14 = bridgeextension2_57.bridge$getRotationYawHead();
         bridgeextension_99.method4(135.0F, 0.0F, 1.0F, 0.0F);
         bridgeextension_99.method6(arg0x -> Bridge.method14().method1());
         bridgeextension_99.method4(-135.0F, 0.0F, 1.0F, 0.0F);
         bridgeextension_99.method4(-((float)Math.atan(value6 / 40.0F)) * 20.0F, 1.0F, 0.0F, 0.0F);
         bridgeextension2_57.bridge$setBodyYRot((float)Math.atan(value5 / 40.0F) * 20.0F - value4);
         bridgeextension2_57.bridge$setRotationYaw((float)Math.atan(value5 / 40.0F) * 40.0F - value4);
         bridgeextension2_57.bridge$setRotationPitch(bridgeextension2_57.bridge$getRotationPitch() - (float)Math.atan(value6 / 40.0F) * 20.0F);
         bridgeextension2_57.bridge$setRotationYawHead((float)bridgeextension2_57.bridge$getRotationYaw());
         bridgeextension2_57.bridge$setPrevRotationYawHead((float)bridgeextension2_57.bridge$getRotationYaw());
         bridgeextension_99.translate(0.0, 0.0, 0.0);
         EntityRenderDispatcherBridge bridge2_4315 = Ref.method3().bridge$getEntityRenderDispatcher();
         bridge2_4315.bridge$setPlayerViewY(180.0F);
         if (Ref.MC_VERSION <= 32) {
            bridge2_4315.bridge$setRenderShadow(false);
         }

         bridge2_4315.bridge$renderEntityWithPosYaw(bridgeextension_99, bridgeextension2_57, 0.0, 0.0, 0.0, 0.0F, 1.0F, Bridge.method8().method92());
         if (Ref.MC_VERSION <= 32) {
            bridge2_4315.bridge$setRenderShadow(true);
         }

         bridgeextension2_57.bridge$setBodyYRot(value10);
         bridgeextension2_57.bridge$setRotationYaw(value11);
         bridgeextension2_57.bridge$setRotationPitch(value12);
         bridgeextension2_57.bridge$setPrevRotationYawHead(value13);
         bridgeextension2_57.bridge$setRotationYawHead(value14);
         bridgeextension_99.pop();
         bridgeextension_99.method23();
         Bridge.method14().method3();
         bridgeextension_99.method6(arg1x -> {
            bridgeextension_99.method28(Bridge.method22().method5());
            bridgeextension_99.method13();
            bridgeextension_99.method28(Bridge.method22().method6());
         });
      } else {
         if (Ref.MC_VERSION < 30) {
            throw new IllegalStateException();
         }

         method2(mixinhelper_40, value1, value2, value3, value5, value6, bridgeextension2_57);
      }
   }

   public EntityLivingBridge method4() {
      return this.field1;
   }
}
