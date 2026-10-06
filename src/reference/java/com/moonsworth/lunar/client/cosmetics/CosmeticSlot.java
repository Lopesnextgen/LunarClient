package com.moonsworth.lunar.client.cosmetics;

import com.moonsworth.lunar.bridge.ModelPlayerBridge;
import com.moonsworth.lunar.bridge.ModelBipedBridge;
import com.moonsworth.lunar.bridge.minecraft.EntityEquipmentSlotBridge;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91;

public enum CosmeticSlot {
   HEAD(EntityEquipmentSlotBridge.HEAD) {
      @Override
      public ThreadModuleDump91 translate(ModelBipedBridge bridgeextension_101) {
         return (arg1x, arg2, arg3) -> bridgeextension_101.bridge$bipedHead().bridge$postRender(0.0625F);
      }

      @Override
      public ThreadModuleDump91 translateModern(ModelPlayerBridge bridgeextension2_71) {
         return ThreadModuleDump91.createModern(
            (arg1x, arg2) -> bridgeextension2_71.bridge$bipedHead().bridge$translateAndRotate(arg2), (arg1x, arg2) -> bridgeextension2_71.bridge$bipedHead().method2(arg2)
         );
      }
   },
   BODY(EntityEquipmentSlotBridge.CHEST) {
      @Override
      public ThreadModuleDump91 translate(ModelBipedBridge bridgeextension_101) {
         return (arg1x, arg2, arg3) -> bridgeextension_101.bridge$bipedBody().bridge$postRender(0.0625F);
      }

      @Override
      public ThreadModuleDump91 translateModern(ModelPlayerBridge bridgeextension2_71) {
         return ThreadModuleDump91.createModern(
            (arg1x, arg2) -> bridgeextension2_71.bridge$bipedBody().bridge$translateAndRotate(arg2), (arg1x, arg2) -> bridgeextension2_71.bridge$bipedBody().method2(arg2)
         );
      }
   },
   RIGHT_ARM(null) {
      @Override
      public ThreadModuleDump91 translate(ModelBipedBridge bridgeextension_101) {
         return (arg1x, arg2, arg3) -> bridgeextension_101.bridge$bipedRightArm().bridge$postRender(0.0625F);
      }

      @Override
      public ThreadModuleDump91 translateModern(ModelPlayerBridge bridgeextension2_71) {
         return ThreadModuleDump91.createModern(
            (arg1x, arg2) -> bridgeextension2_71.bridge$bipedRightArm().bridge$translateAndRotate(arg2), (arg1x, arg2) -> bridgeextension2_71.bridge$bipedRightArm().method2(arg2)
         );
      }
   },
   LEFT_ARM(null) {
      @Override
      public ThreadModuleDump91 translate(ModelBipedBridge bridgeextension_101) {
         return (arg1x, arg2, arg3) -> bridgeextension_101.bridge$bipedLeftArm().bridge$postRender(0.0625F);
      }

      @Override
      public ThreadModuleDump91 translateModern(ModelPlayerBridge bridgeextension2_71) {
         return ThreadModuleDump91.createModern(
            (arg1x, arg2) -> bridgeextension2_71.bridge$bipedLeftArm().bridge$translateAndRotate(arg2), (arg1x, arg2) -> bridgeextension2_71.bridge$bipedLeftArm().method2(arg2)
         );
      }
   },
   RIGHT_LEG(EntityEquipmentSlotBridge.LEGS) {
      @Override
      public ThreadModuleDump91 translate(ModelBipedBridge bridgeextension_101) {
         return (arg1x, arg2, arg3) -> bridgeextension_101.bridge$bipedRightLeg().bridge$postRender(0.0625F);
      }

      @Override
      public ThreadModuleDump91 translateModern(ModelPlayerBridge bridgeextension2_71) {
         return ThreadModuleDump91.createModern(
            (arg1x, arg2) -> bridgeextension2_71.bridge$bipedRightLeg().bridge$translateAndRotate(arg2), (arg1x, arg2) -> bridgeextension2_71.bridge$bipedRightLeg().method2(arg2)
         );
      }
   },
   LEFT_LEG(EntityEquipmentSlotBridge.LEGS) {
      @Override
      public ThreadModuleDump91 translate(ModelBipedBridge bridgeextension_101) {
         return (arg1x, arg2, arg3) -> bridgeextension_101.bridge$bipedLeftLeg().bridge$postRender(0.0625F);
      }

      @Override
      public ThreadModuleDump91 translateModern(ModelPlayerBridge bridgeextension2_71) {
         return ThreadModuleDump91.createModern(
            (arg1x, arg2) -> bridgeextension2_71.bridge$bipedLeftLeg().bridge$translateAndRotate(arg2), (arg1x, arg2) -> bridgeextension2_71.bridge$bipedLeftLeg().method2(arg2)
         );
      }
   };

   private EntityEquipmentSlotBridge armorSlot;

   CosmeticSlot(EntityEquipmentSlotBridge entity) {
      this.armorSlot = entity;
   }

   public boolean hasArmorSlot() {
      return this.armorSlot != null;
   }

   public EntityEquipmentSlotBridge getArmorSlot() {
      return this.armorSlot;
   }

   public abstract ThreadModuleDump91 translate(ModelBipedBridge bridgeextension_101);

   public abstract ThreadModuleDump91 translateModern(ModelPlayerBridge bridgeextension2_71);
}
