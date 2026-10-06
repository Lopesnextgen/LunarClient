package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 16)
public interface EntityRenderStateProvider extends MovementInputMarker {
   @VersionGate(min = 17)
   EntityRenderState bridge$getRenderState();
}
