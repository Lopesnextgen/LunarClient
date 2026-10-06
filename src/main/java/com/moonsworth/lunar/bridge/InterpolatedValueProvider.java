package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 17)
public interface InterpolatedValueProvider<T> {
   T bridge$get(float value1);
}
