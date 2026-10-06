package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(max = 28)
public interface AbstractTextureMultiTexBridge {
   default void bridge$setMultiTextureBase(int value) {
   }
}
