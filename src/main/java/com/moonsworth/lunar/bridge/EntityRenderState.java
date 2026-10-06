package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 17)
public interface EntityRenderState {
   InterpolatedValueProvider<Transformation> bridge$getTransformation();
}
