package com.moonsworth.lunar.bridge.world;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 16)
public interface EquippableComponentBridge {
   ResourceLocationBridge bridge$layerAssetId(boolean flag1, ItemStackBridge bridgeextension_42);

   boolean bridge$isDecal();
}
