package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;

@com.moonsworth.lunar.ichor.Annotation2(min = 39)
public interface LevelRendererBridge {
   boolean bridge$isVisible(AxisAlignedBBBridge var1);

   boolean bridge$isBlockVisible(int var1, int var2, int var3);

   float bridge$getPartialTicks();
}
