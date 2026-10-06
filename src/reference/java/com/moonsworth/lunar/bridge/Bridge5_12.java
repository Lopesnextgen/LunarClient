package com.moonsworth.lunar.bridge;

import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.PropertyMap;
import com.moonsworth.lunar.bridge.glintcolorizer.Glintcolorizer;
import com.moonsworth.lunar.bridge.horsestats.Horsestats;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.MovingObjectPositionHitResult;
import com.moonsworth.lunar.bridge.horsestats.TimerBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter4;
import java.io.File;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nullable;

public interface Bridge5_12 {
   Bridge5Extension_5 bridge$getPlayer();

   void bridge$setPlayer(Bridge5Extension_5 var1);

   Bridge2_25 bridge$getPlayerController();

   Itemcounter6Extension bridge$getWorld();

   Optional<BridgeExtension> bridge$getPointedEntity();

   ClientPacketListenerBridge bridge$getClientPacketListener();

   Bridge5Extension6 bridge$getCurrentScreen();

   Bridge5Extension6 bridge$getCurrentScreenOrRewind();

   Bridge10_2 bridge$getFontRenderer();

   Bridge11_2 bridge$getResourceManager();

   IResourcePackBridge bridge$getMcDefaultResourcePack();

   Bridge8Handler2 bridge$getTextureManager();

   Horsestats bridge$getSession();

   PropertyMap bridge$getProfileProperties();

   MinecraftSessionService bridge$getSessionService();

   Bridge3_17 bridge$getGameRenderer();

   @Nullable
   Bridge3_19 bridge$getCurrentServerData();

   Bridge5_19 bridge$getRenderItem();

   Bridge3_11 bridge$getSoundHandler();

   @com.moonsworth.lunar.ichor.Annotation2(min = 26)
   default Bridge9_3 bridge$getShaderManager() {
      throw new AbstractMethodErrorImpl();
   }

   void bridge$setSession(Horsestats var1);

   File bridge$getMcDataDir();

   void bridge$displayScreen(Bridge5Extension6 var1);

   void bridge$setCurrentScreenSilent(Bridge5Extension6 var1);

   void bridge$openChat(String var1);

   int bridge$displayWidth();

   void bridge$setDisplayWidth(int var1);

   int bridge$displayHeight();

   void bridge$setDisplayHeight(int var1);

   int bridge$logicalWidth();

   int bridge$logicalHeight();

   boolean bridge$unicode();

   int bridge$getGuiScale();

   void bridge$setGuiScale(int var1);

   void bridge$setRawGuiScale(int var1);

   void bridge$updateFramebufferSize();

   void bridge$loadWorld(Itemcounter6Extension var1);

   void bridge$setWorldDirect(Itemcounter6Extension var1);

   void bridge$refreshResources();

   void bridge$shutdownMinecraftApplet();

   GameOptionsBridge bridge$getGameSettings();

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default long bridge$getWindowId() {
      throw new RuntimeException("bridge$getWindowId() can only be called in Modern!");
   }

   Bridge3_33 bridge$getWindow();

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default void bridge$focusWindow() {
   }

   Bridge2_43 bridge$getEntityRenderDispatcher();

   @com.moonsworth.lunar.ichor.Annotation2(min = 8)
   default Bridge2_31 bridge$getBlockEntityRenderDispatcher() {
      throw new AbstractMethodErrorImpl();
   }

   long bridge$getSystemTime();

   long bridge$getRealSystemTime();

   TimerBridge bridge$getTimer();

   Bridge5Extension9 bridge$getGuiIngame();

   int bridge$getDebugFPS();

   float bridge$getFrametime();

   default float bridge$getGpuUtilization() {
      return -1.0F;
   }

   default void bridge$enableGpuProfiling() {
   }

   default void bridge$disableGpuProfiling() {
   }

   Bridge3_24 bridge$getMainRenderTarget();

   void bridge$setMainRenderTarget(Bridge3_24 var1);

   BridgeExtension bridge$getRenderViewEntity();

   void bridge$setRenderViewEntity(BridgeExtension var1);

   Bridge14_3 bridge$getLevelRenderer();

   boolean bridge$hasInGameFocus();

   void bridge$setInGameFocus(boolean var1);

   Bridge8Extension32 bridge$getTextureMap();

   Bridge8Extension32 bridge$getTextureMapById(ResourceLocationBridge var1);

   boolean bridge$isFullScreen();

   void bridge$toggleFullscreen();

   Bridge_40 bridge$getEffectRenderer();

   MovingObjectPositionHitResult bridge$getObjectMouseOver();

   void bridge$submit(Runnable var1);

   void bridge$schedule(Runnable var1);

   Bridge3_31 bridge$getItemRenderer();

   boolean bridge$isGamePaused();

   boolean bridge$isDisplayCreated();

   boolean bridge$isDisplayActive();

   void bridge$setDisplayTitle(String var1);

   void bridge$setRepeatEventsEnabled(boolean var1);

   Bridge3_19 bridge$lastServerData();

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   @Nullable
   default Bridge15_2 bridge$getRenderBuffers() {
      throw new RuntimeException("bridge$getRenderBuffers() can only be used in modern!");
   }

   default Set<Bridge3_23> bridge$xrayBlocks() {
      return Collections.emptySet();
   }

   IResourcePackBridge bridge$getSelectedResourcePack();

   List<IResourcePackBridge> bridge$getAllSelectedPacks();

   List<File> bridge$getClientResourcePacksFiles();

   List<File> bridge$getServerResourcePacksFiles();

   List<File> bridge$getAllBuiltInPacksFiles();

   @Nullable
   String bridge$getBuiltInPackName(String var1);

   Optional<InetSocketAddress> bridge$resolveAddress(String var1);

   void bridge$connect(Bridge3_19 var1, Bridge5Extension6 var2);

   boolean bridge$isConnectedToRealms();

   void bridge$updateDisplay();

   default void bridge$prepareWindowSurface() {
   }

   boolean bridge$isWindowFocused();

   boolean bridge$isFpsLimited();

   Bridge12_4 bridge$getSkinManager();

   default boolean bridge$renderChunkBorder() {
      return false;
   }

   default void bridge$setRenderChunkBorder(boolean var1) {
   }

   void bridge$setCurrentServer(Bridge3_19 var1);

   Glintcolorizer bridge$getIntegratedServer();

   default void bridge$recreateLoadingScreen() {
   }

   boolean bridge$isRunning();

   boolean bridge$areResourcesLoaded();

   default boolean bridge$hasOverlay() {
      return false;
   }

   void bridge$processKeyboardAndMouse();

   int bridge$getCreativeTab();

   void bridge$setCreativeTab(int var1);

   void bridge$clearLevel();

   Bridge7_6 bridge$getLanguageManager();

   void bridge$processRewindTick();

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   void bridge$resizeDisplay();

   void bridge$pickBlock();

   void bridge$waitOnAllChunksRendering();

   default void method1(Bridge3_24 var1, boolean var2) {
      this.bridge$overrideMainRenderTarget(var1, var2, false);
   }

   void bridge$overrideMainRenderTarget(Bridge3_24 var1, boolean var2, boolean var3);

   @com.moonsworth.lunar.ichor.Annotation2(min = 8)
   void bridge$makePanorama(File var1, int var2, int var3);

   int bridge$getProtocolVersion();

   BridgeExtension bridge$getSpectatedEntity();

   void bridge$setSpectatedEntity(BridgeExtension var1);

   void bridge$joinWorld(String var1, String var2, Itemcounter4 var3, Runnable var4);

   boolean bridge$deleteWorld(String var1);

   List<ProfilerResultBridge> bridge$getPieChartResults();

   boolean bridge$isReducedDebugInfo();

   boolean bridge$isMouseButtonDown(int var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   boolean bridge$isRepositoryReloadManual();
}
