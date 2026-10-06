package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import org.joml.Vector3d;

@VersionGate(max = 5)
public interface FrustumBridge {
   Vector3d bridge$getPosition();

   boolean bridge$isBoundingBoxInFrustum(AxisAlignedBBBridge horsestats121);
}
