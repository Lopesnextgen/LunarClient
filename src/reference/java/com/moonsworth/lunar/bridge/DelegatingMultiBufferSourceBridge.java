package com.moonsworth.lunar.bridge;

import java.util.function.Function;

public interface DelegatingMultiBufferSourceBridge extends MultiBufferSourceBridge {
   default void bridge$setDelegate(MultiBufferSourceBridge bridge171, Function<RenderTypeBridge, VertexConsumerBridge> function2) {
   }
}
