package com.moonsworth.lunar.bridge;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface CompoundTagComponent {
   @Nullable
   CompoundTagBridge bridge$getData();
}
