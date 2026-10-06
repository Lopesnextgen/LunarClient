package com.moonsworth.lunar.bridge;

import javax.annotation.Nullable;

@FunctionalInterface
public interface DataComponentType<T> {
   @Nullable
   T bridge$get(ItemStackBridge bridgeextension_41);
}
