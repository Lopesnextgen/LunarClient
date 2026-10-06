package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.bridge.ModelRendererBridge;
import com.moonsworth.lunar.bridge.ModelPlayerBridge;
import com.moonsworth.lunar.bridge.ModelBipedBridge;
import com.moonsworth.lunar.client.cosmetics.CosmeticSlot;
import java.util.function.Function;
import lombok.Generated;

public enum AttachedBone {
   NONE(null, null, null),
   HEAD("head", CosmeticSlot.HEAD, ModelBipedBridge::bridge$bipedHead),
   SHOULDER("low_body", CosmeticSlot.BODY, ModelBipedBridge::bridge$bipedBody),
   LEFT_ARM("left_arm", CosmeticSlot.LEFT_ARM, ModelBipedBridge::bridge$bipedLeftArm),
   RIGHT_ARM("right_arm", CosmeticSlot.RIGHT_ARM, ModelBipedBridge::bridge$bipedRightArm),
   LEFT_LEG("left_leg", CosmeticSlot.LEFT_LEG, ModelBipedBridge::bridge$bipedLeftLeg),
   RIGHT_LEG("right_leg", CosmeticSlot.RIGHT_LEG, ModelBipedBridge::bridge$bipedRightLeg);

   private final String boneName;
   private final CosmeticSlot bodyPart;
   private final Function<ModelPlayerBridge, ModelRendererBridge> bodyPartSupplier;

   AttachedBone(String text, CosmeticSlot cosmeticSlot, Function<ModelPlayerBridge, ModelRendererBridge> function5) {
      this.boneName = text;
      this.bodyPart = cosmeticSlot;
      this.bodyPartSupplier = function5;
   }

   @Generated
   public String getBoneName() {
      return this.boneName;
   }

   @Generated
   public CosmeticSlot getBodyPart() {
      return this.bodyPart;
   }

   @Generated
   public Function<ModelPlayerBridge, ModelRendererBridge> getBodyPartSupplier() {
      return this.bodyPartSupplier;
   }
}
