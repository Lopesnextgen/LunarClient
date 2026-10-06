package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.hitcolor.HitcolorExtension;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;

@com.moonsworth.lunar.ichor.Annotation2(min = 6)
public interface TileEntityRendererBridge {
   default boolean bridge$shouldRenderOffScreen(HitcolorExtension var1) {
      throw new AbstractMethodErrorImpl();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 8)
   default int bridge$getViewDistance() {
      throw new AbstractMethodErrorImpl();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 8)
   boolean bridge$shouldRender(HitcolorExtension var1, Vec3Bridge var2);
}
