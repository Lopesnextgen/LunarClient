package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.hitcolor.HitcolorExtension;
import org.jetbrains.annotations.Nullable;

public interface Bridge2_31 {
   @com.moonsworth.lunar.ichor.Annotation2(min = 8)
   @Nullable
   default TileEntityRendererBridge bridge$getBlockEntityRenderer(HitcolorExtension var1) {
      throw new AbstractMethodErrorImpl();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 8)
   default void bridge$render(TileEntityRendererBridge var1, HitcolorExtension hitcolor, float value, Bridge5_16 bridge5_16, BatchingBufferSourceBridge batchingBufferSourceBridge) {
      throw new AbstractMethodErrorImpl();
   }
}
