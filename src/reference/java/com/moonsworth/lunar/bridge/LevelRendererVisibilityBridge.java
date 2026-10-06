package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 39)
public interface LevelRendererVisibilityBridge {
   boolean bridge$isVisible(AxisAlignedBBBridge horsestats121);

   boolean bridge$isBlockVisible(int number1, int number2, int number3);

   float bridge$getPartialTicks();
}
