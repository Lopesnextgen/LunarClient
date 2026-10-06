package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

public interface ModelBipedBridge extends ModelBaseBridge {
   ModelRendererBridge bridge$bipedHead();

   ModelRendererBridge bridge$bipedHeadwear();

   ModelRendererBridge bridge$bipedBody();

   ModelRendererBridge bridge$bipedRightArm();

   ModelRendererBridge bridge$bipedLeftArm();

   ModelRendererBridge bridge$bipedRightLeg();

   ModelRendererBridge bridge$bipedLeftLeg();

   @VersionGate(max = 25)
   void bridge$setSneak(boolean flag1);
}
