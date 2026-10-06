package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

public interface EntitySkeletonBridge {
   @VersionGate(max = 1)
   default boolean bridge$isWitherSkeleton() {
      return false;
   }
}
