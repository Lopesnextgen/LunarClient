package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;

@FunctionalInterface
public interface RenderTypeResolver {
   RenderLayerBridge get(ResourceLocationBridge var1);
}
