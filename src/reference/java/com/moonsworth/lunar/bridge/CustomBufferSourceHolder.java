package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 33)
public interface CustomBufferSourceHolder {
   @VersionGate(min = 39)
   MultiBufferSourceBridge bridge$getCustomBufferSource();

   @VersionGate(min = 39)
   void bridge$setCustomBufferSource(MultiBufferSourceBridge bridge171);
}
