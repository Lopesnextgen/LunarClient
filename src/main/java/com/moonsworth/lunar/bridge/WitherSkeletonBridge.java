package com.moonsworth.lunar.bridge;

public interface WitherSkeletonBridge {
   @com.moonsworth.lunar.ichor.Annotation2(max = 1)
   default boolean bridge$isWitherSkeleton() {
      return false;
   }
}
