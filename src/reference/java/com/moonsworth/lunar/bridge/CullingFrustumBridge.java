package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import org.joml.Vector3d;

@com.moonsworth.lunar.ichor.Annotation2(max = 5)
public interface CullingFrustumBridge {
   Vector3d bridge$getPosition();

   boolean bridge$isBoundingBoxInFrustum(AxisAlignedBBBridge var1);
}
