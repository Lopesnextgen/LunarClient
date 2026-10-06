package com.moonsworth.lunar.bridge;

import javax.annotation.Nullable;

public interface Bridge4_14<T> {
   void bridge$render(@Nullable T var1, ItemTransformType var2, Bridge5_16 var3, BatchingBufferSourceBridge var4, int var5, int var6, boolean var7);

   @Nullable
   T bridge$extractArgument(ItemStackBridge var1);
}
