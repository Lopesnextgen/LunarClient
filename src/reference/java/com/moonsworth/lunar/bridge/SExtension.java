package com.moonsworth.lunar.bridge;

import java.util.ArrayList;
import java.util.List;

public interface SExtension<E extends BridgeExtension2_5, S extends BridgeExtension2_2> {
   float field1 = 100.0F;

   default void bridge$addLayer(MExtension<E, S> var1) {
      this.bridge$addLayer(var1, false);
   }

   default void bridge$addLayer(MExtension<E, S> var1, boolean var2) {
      throw new UnsupportedOperationException("Can't call addLayer on " + Bridge.getMinecraftVersion().method45());
   }

   default Bridge2_10 bridge$getLayerCape() {
      throw new UnsupportedOperationException("Can't call addLayer on " + Bridge.getMinecraftVersion().method45());
   }

   @com.moonsworth.lunar.ichor.Annotation2(max = 0)
   default List<MExtension<E, S>> getLayers() {
      return new ArrayList<>();
   }

   @com.moonsworth.lunar.ichor.Annotation2(max = 1)
   default void bridge$setRenderOutlines(boolean var1) {
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 8)
   default boolean bridge$containsVboLayer() {
      throw new AbstractMethodErrorImpl();
   }

   default void bridge$renderName(E var1, double var2, double value, double value2, float value3, Bridge5_16 bridge5_16, BatchingBufferSourceBridge batchingBufferSourceBridge, float value4) {
      throw new AbstractMethodErrorImpl();
   }
}
