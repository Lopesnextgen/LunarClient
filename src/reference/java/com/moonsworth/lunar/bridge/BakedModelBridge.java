package com.moonsworth.lunar.bridge;

import java.util.Optional;

public interface BakedModelBridge {
   boolean bridge$isGui3D();

   default Optional<Bridge4_8> bridge$getParticleTexture() {
      return Optional.empty();
   }

   ItemCameraTransformsBridge bridge$getItemCameraTransforms();
}
