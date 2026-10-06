package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;
import org.jetbrains.annotations.Nullable;

public interface EntityFireworkRocketBridge extends MovementInputMarker {
   @VersionGate(min = 5)
   @Nullable
   EntityLivingBridge bridge$getAttachedToEntity();
}
