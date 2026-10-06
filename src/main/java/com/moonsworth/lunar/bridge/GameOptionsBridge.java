package com.moonsworth.lunar.bridge;

import java.util.Optional;
import java.util.Set;

public interface GameOptionsBridge {
   void bridge$loadOptions();

   void bridge$saveOptions();

   void bridge$setThirdPersonView(int var1);

   int bridge$getThirdPersonView();

   KeyCode bridge$getScreenshotKey();

   MixinHelper_15 bridge$getDropKey();

   MixinHelper_15 bridge$keyBindForward();

   MixinHelper_15 bridge$keyBindLeft();

   MixinHelper_15 bridge$keyBindBack();

   MixinHelper_15 bridge$keyBindRight();

   MixinHelper_15 bridge$keyBindJump();

   MixinHelper_15 bridge$keyBindAttack();

   MixinHelper_15 bridge$keyBindUseItem();

   MixinHelper_15 bridge$keyBindPickBlock();

   MixinHelper_15 bridge$keyBindSprint();

   MixinHelper_15 bridge$keyBindSneak();

   MixinHelper_15 bridge$keyBindPlayerList();

   MixinHelper_15 bridge$keyBindTogglePerspective();

   MixinHelper_15[] bridge$getKeyBindings();

   void bridge$setKeyBinds(MixinHelper_15[] var1);

   int bridge$getGuiScale();

   boolean bridge$isFancyGraphics();

   boolean bridge$isFabulousGraphics();

   int bridge$getRenderDistance();

   default int bridge$getEffectiveRenderDistance() {
      return this.bridge$getRenderDistance();
   }

   void bridge$setGamma(float var1);

   void bridge$setGammaOverride(float var1);

   void bridge$removeGammaOverride();

   void bridge$setForceUnicode(boolean var1);

   boolean bridge$isForceUnicode();

   default boolean bridge$isSettingGamma() {
      return false;
   }

   default void bridge$postSetGammaByUser() {
   }

   boolean bridge$showDebugInfo();

   Set<Bridge5_6> bridge$getModelParts();

   boolean bridge$isHideGui();

   void bridge$setHideGui(boolean var1);

   float bridge$getChatScale();

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   boolean bridge$showHat();

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   boolean bridge$showJacket();

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   boolean bridge$showRightSleeve();

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   boolean bridge$showLeftSleeve();

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   boolean bridge$showRightPant();

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   boolean bridge$showLeftPant();

   void bridge$setOptionFloatValue(int var1, float var2);

   void bridge$setFancyGraphics(boolean var1);

   void bridge$setKeyBindState(KeyCode var1, boolean var2);

   void bridge$unpressAllKeys();

   void bridge$setSmoothCamera(boolean var1);

   boolean bridge$getSmoothCamera();

   default void bridge$updateVSync() {
   }

   default void bridge$setToggleSprint(boolean var1) {
   }

   default void bridge$setToggleSneak(boolean var1) {
   }

   default boolean bridge$getToggleSprint() {
      return false;
   }

   default boolean bridge$getToggleSneak() {
      return false;
   }

   default Optional<MixinHelper_15> bridge$getZoomKey() {
      return Optional.empty();
   }

   int bridge$getFrameRateLimit();

   void bridge$setFrameRateLimit(int var1);

   boolean bridge$getVSync();

   boolean bridge$getAmbientOcclusion();

   boolean bridge$getEntityShadows();

   double bridge$getEntityScaling();

   MixinHelper2$Type3 bridge$getPrioritizeChunkUpdates();

   MixinHelper2$Type2 bridge$getCloudStatus();

   MixinHelper2$Type bridge$getParticleStatus();

   default int bridge$getSimulationDistance() {
      return this.bridge$getRenderDistance();
   }

   int bridge$getBiomeBlend();

   int bridge$getFov();

   void bridge$setVBO(boolean var1);

   boolean bridge$isChatLinks();

   boolean bridge$isChatPromptLinks();

   double bridge$getNotificationDisplayTime();

   boolean bridge$isShowSubtitles();

   void bridge$setShowSubtitles(boolean var1);

   default int bridge$getTextBackgroundColor(float var1) {
      return 0;
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 5)
   boolean bridge$isAttackIndicatorEnabled();

   boolean bridge$isAdvancedItemTooltips();

   float bridge$getMasterVolume();

   default boolean bridge$isStreamKey(KeyCode var1) {
      return false;
   }

   default double getPanoramaSpeed() {
      return 1.0;
   }

   default boolean bridge$getRawMouseInput() {
      return false;
   }

   default void bridge$setRawMouseInput(boolean var1) {
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   default boolean bridge$isNativeTransport() {
      return false;
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 35)
   void bridge$setChunkSectionFadeInTime$v1_21_11(double var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 35)
   double bridge$getChunkSectionFadeInTime$v1_21_11();

   MixinHelper2$Type4 bridge$getTextureFiltering();
}
