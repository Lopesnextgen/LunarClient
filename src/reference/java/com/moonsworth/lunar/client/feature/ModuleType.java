package com.moonsworth.lunar.client.feature;

import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.BipedModelBridge;
import com.moonsworth.lunar.bridge.horsestats.EquipmentSlotBridge;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91;

public enum ModuleType {
   HEAD(EquipmentSlotBridge.HEAD) {
      @Override
      public ThreadModuleDump91 translate(BipedModelBridge var1) {
         return (var1x, var2, var3) -> var1.bridge$bipedHead().bridge$postRender(0.0625F);
      }

      @Override
      public ThreadModuleDump91 translateModern(BridgeExtension2_7 var1) {
         return ThreadModuleDump91.createModern(
            (var1x, var2) -> var1.bridge$bipedHead().bridge$translateAndRotate(var2), (var1x, var2) -> var1.bridge$bipedHead().method2(var2)
         );
      }
   },
   BODY(EquipmentSlotBridge.CHEST) {
      @Override
      public ThreadModuleDump91 translate(BipedModelBridge var1) {
         return (var1x, var2, var3) -> var1.bridge$bipedBody().bridge$postRender(0.0625F);
      }

      @Override
      public ThreadModuleDump91 translateModern(BridgeExtension2_7 var1) {
         return ThreadModuleDump91.createModern(
            (var1x, var2) -> var1.bridge$bipedBody().bridge$translateAndRotate(var2), (var1x, var2) -> var1.bridge$bipedBody().method2(var2)
         );
      }
   },
   RIGHT_ARM(null) {
      @Override
      public ThreadModuleDump91 translate(BipedModelBridge var1) {
         return (var1x, var2, var3) -> var1.bridge$bipedRightArm().bridge$postRender(0.0625F);
      }

      @Override
      public ThreadModuleDump91 translateModern(BridgeExtension2_7 var1) {
         return ThreadModuleDump91.createModern(
            (var1x, var2) -> var1.bridge$bipedRightArm().bridge$translateAndRotate(var2), (var1x, var2) -> var1.bridge$bipedRightArm().method2(var2)
         );
      }
   },
   LEFT_ARM(null) {
      @Override
      public ThreadModuleDump91 translate(BipedModelBridge var1) {
         return (var1x, var2, var3) -> var1.bridge$bipedLeftArm().bridge$postRender(0.0625F);
      }

      @Override
      public ThreadModuleDump91 translateModern(BridgeExtension2_7 var1) {
         return ThreadModuleDump91.createModern(
            (var1x, var2) -> var1.bridge$bipedLeftArm().bridge$translateAndRotate(var2), (var1x, var2) -> var1.bridge$bipedLeftArm().method2(var2)
         );
      }
   },
   RIGHT_LEG(EquipmentSlotBridge.LEGS) {
      @Override
      public ThreadModuleDump91 translate(BipedModelBridge var1) {
         return (var1x, var2, var3) -> var1.bridge$bipedRightLeg().bridge$postRender(0.0625F);
      }

      @Override
      public ThreadModuleDump91 translateModern(BridgeExtension2_7 var1) {
         return ThreadModuleDump91.createModern(
            (var1x, var2) -> var1.bridge$bipedRightLeg().bridge$translateAndRotate(var2), (var1x, var2) -> var1.bridge$bipedRightLeg().method2(var2)
         );
      }
   },
   LEFT_LEG(EquipmentSlotBridge.LEGS) {
      @Override
      public ThreadModuleDump91 translate(BipedModelBridge var1) {
         return (var1x, var2, var3) -> var1.bridge$bipedLeftLeg().bridge$postRender(0.0625F);
      }

      @Override
      public ThreadModuleDump91 translateModern(BridgeExtension2_7 var1) {
         return ThreadModuleDump91.createModern(
            (var1x, var2) -> var1.bridge$bipedLeftLeg().bridge$translateAndRotate(var2), (var1x, var2) -> var1.bridge$bipedLeftLeg().method2(var2)
         );
      }
   };

   private EquipmentSlotBridge armorSlot;

   ModuleType(EquipmentSlotBridge var3) {
      this.armorSlot = var3;
   }

   public boolean hasArmorSlot() {
      return this.armorSlot != null;
   }

   public EquipmentSlotBridge getArmorSlot() {
      return this.armorSlot;
   }

   public abstract ThreadModuleDump91 translate(BipedModelBridge var1);

   public abstract ThreadModuleDump91 translateModern(BridgeExtension2_7 var1);
}
