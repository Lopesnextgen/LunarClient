package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;

@FunctionalInterface
public interface RenderTypeLookup {
   RenderTypeBridge get(ResourceLocationBridge horsestats141);
}
