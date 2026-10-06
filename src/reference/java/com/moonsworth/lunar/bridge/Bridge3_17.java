package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

public interface Bridge3_17 {
   void bridge$loadPostEffectShader(ResourceLocationBridge var1, ResourceLocationBridge var2, ResourceLocationBridge var3);

   boolean bridge$isShaderActive();

   Bridge7_10 bridge$getShaderGroup();

   void bridge$stopUseShader();

   void bridge$enableLightmap();

   void bridge$disableLightmap();

   Bridge6_7 bridge$getMapItemRenderer();

   @com.moonsworth.lunar.ichor.Annotation2(min = 5)
   @Nullable
   ItemStackBridge bridge$getItemActivationItem();

   @com.moonsworth.lunar.ichor.Annotation2(min = 5)
   int bridge$getItemActivationTicks();

   @com.moonsworth.lunar.ichor.Annotation2(min = 5)
   float bridge$getItemActivationOffsetX();

   @com.moonsworth.lunar.ichor.Annotation2(min = 5)
   float bridge$getItemActivationOffsetY();

   default void bridge$resize(int var1, int var2) {
   }

   default int bridge$getRenderTargetsWidth() {
      return 0;
   }

   default int bridge$getRenderTargetsHeight() {
      return 0;
   }

   @Internal
   default void bridge$setRenderTargetsWidth(int var1) {
   }

   @Internal
   default void bridge$setRenderTargetsHeight(int var1) {
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 7)
   Bridge2_19 bridge$getCamera();

   @com.moonsworth.lunar.ichor.Annotation2(min = 26)
   Bridge9_2 bridge$getMapTextureManager();

   @com.moonsworth.lunar.ichor.Annotation2(min = 39)
   default void bridge$overrideMainRenderTarget(Bridge3_24 var1) {
   }
}
