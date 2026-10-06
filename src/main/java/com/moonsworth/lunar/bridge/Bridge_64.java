package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.function.BiFunction;
import java.util.function.Consumer;

@com.moonsworth.lunar.ichor.Annotation2(min = 26)
public interface Bridge_64 {
   void bridge$setTextureReplacementFunction(BiFunction<ResourceLocationBridge, Consumer<Bridge_58>, ResourceLocationBridge> var1);

   void bridge$setNextOverlayTexture(int var1);
}
