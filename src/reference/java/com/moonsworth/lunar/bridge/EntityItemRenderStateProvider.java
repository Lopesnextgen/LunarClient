package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 16)
public interface EntityItemRenderStateProvider extends EntityRenderStateProvider {
   ItemStackBridge bridge$getItemStack();
}
