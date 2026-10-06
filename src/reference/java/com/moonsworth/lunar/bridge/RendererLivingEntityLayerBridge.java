package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;
import java.util.ArrayList;
import java.util.List;

public interface RendererLivingEntityLayerBridge<E extends EntityLivingBridge, S extends EntityLivingStateBridge> {
   float field1 = 100.0F;

   default void bridge$addLayer(LayerRendererBridge<E, S> mextension1) {
      this.bridge$addLayer(mextension1, false);
   }

   default void bridge$addLayer(LayerRendererBridge<E, S> mextension1, boolean flag) {
      throw new UnsupportedOperationException("Can't call addLayer on " + Bridge.getMinecraftVersion().method45());
   }

   default LayerCapeBridge bridge$getLayerCape() {
      throw new UnsupportedOperationException("Can't call addLayer on " + Bridge.getMinecraftVersion().method45());
   }

   @VersionGate(max = 0)
   default List<LayerRendererBridge<E, S>> getLayers() {
      return new ArrayList<>();
   }

   @VersionGate(max = 1)
   default void bridge$setRenderOutlines(boolean flag) {
   }

   @VersionGate(min = 8)
   default boolean bridge$containsVboLayer() {
      throw new AbstractMethodErrorImpl();
   }

   default void bridge$renderName(E e, double value, double value2, double value3, float value4, Bridge5_16 bridge5_169, MultiBufferSourceBridge bridge1710, float value5) {
      throw new AbstractMethodErrorImpl();
   }
}
