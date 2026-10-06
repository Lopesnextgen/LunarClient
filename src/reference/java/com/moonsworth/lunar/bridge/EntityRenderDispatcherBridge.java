package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Map;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EntityRenderDispatcherBridge {
   double bridge$renderPosX();

   double bridge$renderPosY();

   double bridge$renderPosZ();

   double bridge$playerViewX();

   double bridge$playerViewY();

   double bridge$viewerPosX();

   double bridge$viewerPosY();

   double bridge$viewerPosZ();

   RenderPlayerBridge bridge$defaultPlayerRenderer();

   Map<String, RenderPlayerBridge> bridge$getSkinMap();

   void bridge$setTextureManager(TextureManagerBridge bridge8handler21);

   void bridge$setLivingEntity(EntityLivingBridge bridgeextension2_51);

   void bridge$setOptions(GameOptionsBridge mixinhelper2_81);

   @VersionGate(max = 32)
   void bridge$setRenderShadow(boolean flag1);

   void bridge$setPlayerViewY(float value1);

   void bridge$renderEntityWithPosYaw(
      @Nullable AbstractRenderContext bridgeextension_91, BridgeExtension bridgeextension2, double value3, double value5, double value7, float value9, float value10, int number11
   );

   void bridge$renderShadow(@NotNull AbstractRenderContext bridgeextension_91, Itemcounter6 itemcounter62, double value3, double value5, double value7, float value9, double value10, double value12);

   void bridge$setDebugBoundingBox(boolean flag1);

   boolean bridge$showDebugBoundingBox();

   void bridge$prepare(Itemcounter6 itemcounter61, BridgeExtension bridgeextension2);

   default void bridge$onPlayerRenderersReloaded(Runnable runnable1) {
      runnable1.run();
   }

   @VersionGate(min = 6)
   default Optional<CameraBridge> bridge$getCamera() {
      throw new AbstractMethodErrorImpl();
   }

   Vec3Bridge bridge$getCameraPos();

   @VersionGate(max = 0)
   default void bridge$setRenderOutlines(boolean flag1) {
   }

   int bridge$getPackedLightCoords(BridgeExtension bridgeextension1, float value2);

   @VersionGate(min = 6)
   default EntityRendererBridge bridge$getRenderer(BridgeExtension bridgeextension1) {
      throw new AbstractMethodErrorImpl();
   }

   @VersionGate(min = 6)
   default void bridge$render(BridgeExtension bridgeextension1, double value2, double value, double value3, float value4, float value9, Bridge5_16 bridge5_1610, MultiBufferSourceBridge bridge1711, int value5) {
   }
}
