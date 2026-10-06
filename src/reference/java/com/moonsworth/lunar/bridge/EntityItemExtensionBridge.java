package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

public interface EntityItemExtensionBridge extends EntityItemStateBridge, EntityRenderStateBridge {
   float bridge$getBobOffset();

   @VersionGate(min = 1)
   BakedModelBridge bridge$getBakedModel();

   @Override
   int bridge$getEntityId();

   double bridge$getRotationYaw();

   boolean bridge$isOnGround();
}
