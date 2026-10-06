package com.moonsworth.lunar.bridge;

import javax.annotation.Nullable;

public interface SpecialModelRendererBridge<T> {
   void bridge$render(@Nullable T value1, ItemTransformTypeBridge bridgetype_52, Bridge5_16 bridge5_163, MultiBufferSourceBridge bridge174, int number5, int number6, boolean flag7);

   @Nullable
   T bridge$extractArgument(ItemStackBridge bridgeextension_41);
}
