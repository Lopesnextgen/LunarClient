package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 39)
public interface SulfurCubeTntBridge extends MovementInputMarker {
   int bridge$getFuse();

   int bridge$getMaximumFuse();
}
