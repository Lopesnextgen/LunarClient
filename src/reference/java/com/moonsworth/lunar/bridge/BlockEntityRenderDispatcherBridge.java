package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.tileentity.BlockEntityBridge;
import org.jetbrains.annotations.Nullable;

public interface BlockEntityRenderDispatcherBridge {
   @com.moonsworth.lunar.ichor.VersionGate(min = 8)
   @Nullable
   default BlockEntityRendererBridge bridge$getBlockEntityRenderer(BlockEntityBridge entity) {
      throw new AbstractMethodErrorImpl();
   }

   @com.moonsworth.lunar.ichor.VersionGate(min = 8)
   default void bridge$render(BlockEntityRendererBridge bridge_171, BlockEntityBridge entity, float value, Bridge5_16 bridge5_164, MultiBufferSourceBridge bridge175) {
      throw new AbstractMethodErrorImpl();
   }
}
