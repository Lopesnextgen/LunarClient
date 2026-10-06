package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.tileentity.BlockEntityBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 6)
public interface BlockEntityRendererBridge {
   default boolean bridge$shouldRenderOffScreen(BlockEntityBridge hitcolorextension1) {
      throw new AbstractMethodErrorImpl();
   }

   @VersionGate(min = 8)
   default int bridge$getViewDistance() {
      throw new AbstractMethodErrorImpl();
   }

   @VersionGate(min = 8)
   boolean bridge$shouldRender(BlockEntityBridge hitcolorextension1, Vec3Bridge horsestats152);
}
