package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

public interface BatchMultiBufferSourceBridge extends MultiBufferSourceBridge {
   void bridge$endBatch();

   void bridge$endBatch(RenderTypeBridge bridge201);

   @VersionGate(min = 6, max = 38)
   default void bridge$endLastBatch() {
      throw new AbstractMethodErrorImpl();
   }

   default boolean isOutlineBufferSource() {
      return false;
   }
}
