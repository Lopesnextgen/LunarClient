package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.function.BiFunction;
import java.util.function.Consumer;

@VersionGate(min = 26)
public interface TextureOverrideBridge {
   void bridge$setTextureReplacementFunction(BiFunction<ResourceLocationBridge, Consumer<SpriteAnimationBridge>, ResourceLocationBridge> function1);

   void bridge$setNextOverlayTexture(int number1);
}
