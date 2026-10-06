package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge;
import javax.annotation.Nullable;

public interface Bridge3_31 {
   default void bridge$renderItem(
      ItemStackRenderStateBridge var1, MixinHelper2$Type5 var2, boolean var3, Bridge5_16 var4, Object object, int value, int value2, @Nullable BakedModelExtension var8
   ) {
      throw new UnsupportedOperationException("Incorrect renderItem call for a legacy version!");
   }

   void bridge$renderItem(BridgeExtension2_5 var1, ItemStackBridge var2, MixinHelper2$Type5 var3);

   @com.moonsworth.lunar.ichor.Annotation2(min = 28)
   void bridge$setWrappedLayerState(MixinHelper$Extension4 var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 28)
   MixinHelper$Extension4 bridge$getWrappedLayerState();

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   Bridge4_6 bridge$getFoilBufferDirect(BatchingBufferSourceBridge var1, RenderLayerBridge var2, boolean var3, boolean var4);

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   ItemTransformsBridge.Type bridge$getCurrentTransform();

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   void bridge$setCurrentTransform(ItemTransformsBridge.Type var1);
}
