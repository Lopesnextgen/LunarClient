package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

public interface EntityItemStateBridge {
   @VersionGate(min = 5)
   int bridge$renderSeed();

   int bridge$renderCount();

   ItemStackRenderStateBridge bridge$getItemState();
}
