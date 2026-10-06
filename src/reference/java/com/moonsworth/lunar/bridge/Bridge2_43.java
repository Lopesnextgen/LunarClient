package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import java.util.Map;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Bridge2_43 {
   double bridge$renderPosX();

   double bridge$renderPosY();

   double bridge$renderPosZ();

   double bridge$playerViewX();

   double bridge$playerViewY();

   double bridge$viewerPosX();

   double bridge$viewerPosY();

   double bridge$viewerPosZ();

   MixinHelper_6 bridge$defaultPlayerRenderer();

   Map<String, MixinHelper_6> bridge$getSkinMap();

   void bridge$setTextureManager(Bridge8Handler2 var1);

   void bridge$setLivingEntity(BridgeExtension2_5 var1);

   void bridge$setOptions(GameOptionsBridge var1);

   @com.moonsworth.lunar.ichor.Annotation2(max = 32)
   void bridge$setRenderShadow(boolean var1);

   void bridge$setPlayerViewY(float var1);

   void bridge$renderEntityWithPosYaw(
      @Nullable AbstractRenderContext var1, BridgeExtension var2, double var3, double var5, double var7, float var9, float var10, int var11
   );

   void bridge$renderShadow(@NotNull AbstractRenderContext var1, Itemcounter6 var2, double var3, double var5, double var7, float var9, double var10, double var12);

   void bridge$setDebugBoundingBox(boolean var1);

   boolean bridge$showDebugBoundingBox();

   void bridge$prepare(Itemcounter6 var1, BridgeExtension var2);

   default void bridge$onPlayerRenderersReloaded(Runnable var1) {
      var1.run();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default Optional<Bridge2_19> bridge$getCamera() {
      throw new AbstractMethodErrorImpl();
   }

   Vec3Bridge bridge$getCameraPos();

   @com.moonsworth.lunar.ichor.Annotation2(max = 0)
   default void bridge$setRenderOutlines(boolean var1) {
   }

   int bridge$getPackedLightCoords(BridgeExtension var1, float var2);

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default Bridge_62 bridge$getRenderer(BridgeExtension var1) {
      throw new AbstractMethodErrorImpl();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default void bridge$render(BridgeExtension var1, double var2, double value, double value2, float value3, float var9, Bridge5_16 var10, BatchingBufferSourceBridge var11, int var12) {
   }
}
