package com.moonsworth.lunar.bridge;

public interface Bridge17Extension_2 extends BatchingBufferSourceBridge {
   void bridge$endBatch();

   void bridge$endBatch(RenderLayerBridge var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 6, max = 38)
   default void bridge$endLastBatch() {
      throw new AbstractMethodErrorImpl();
   }

   default boolean isOutlineBufferSource() {
      return false;
   }
}
