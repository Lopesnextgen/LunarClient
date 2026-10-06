package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

public interface GameRendererBridge {
   void bridge$loadPostEffectShader(ResourceLocationBridge horsestats141, ResourceLocationBridge horsestats142, ResourceLocationBridge horsestats143);

   boolean bridge$isShaderActive();

   Bridge7_10 bridge$getShaderGroup();

   void bridge$stopUseShader();

   void bridge$enableLightmap();

   void bridge$disableLightmap();

   MapItemRendererBridge bridge$getMapItemRenderer();

   @com.moonsworth.lunar.ichor.VersionGate(min = 5)
   @Nullable
   ItemStackBridge bridge$getItemActivationItem();

   @com.moonsworth.lunar.ichor.VersionGate(min = 5)
   int bridge$getItemActivationTicks();

   @com.moonsworth.lunar.ichor.VersionGate(min = 5)
   float bridge$getItemActivationOffsetX();

   @com.moonsworth.lunar.ichor.VersionGate(min = 5)
   float bridge$getItemActivationOffsetY();

   default void bridge$resize(int number1, int value) {
   }

   default int bridge$getRenderTargetsWidth() {
      return 0;
   }

   default int bridge$getRenderTargetsHeight() {
      return 0;
   }

   @Internal
   default void bridge$setRenderTargetsWidth(int number1) {
   }

   @Internal
   default void bridge$setRenderTargetsHeight(int number1) {
   }

   @com.moonsworth.lunar.ichor.VersionGate(min = 7)
   CameraBridge bridge$getCamera();

   @com.moonsworth.lunar.ichor.VersionGate(min = 26)
   Bridge9_2 bridge$getMapTextureManager();

   @com.moonsworth.lunar.ichor.VersionGate(min = 39)
   default void bridge$overrideMainRenderTarget(Bridge3_24 bridge3_241) {
   }
}
