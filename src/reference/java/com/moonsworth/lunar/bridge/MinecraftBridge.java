package com.moonsworth.lunar.bridge;

import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.PropertyMap;
import com.moonsworth.lunar.bridge.server.IntegratedServerBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.minecraft.MovingObjectPositionBridge;
import com.moonsworth.lunar.bridge.horsestats.TimerBridge;
import com.moonsworth.lunar.bridge.world.WorldSettingsBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.io.File;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nullable;

public interface MinecraftBridge {
   Bridge5Extension_5 bridge$getPlayer();

   void bridge$setPlayer(Bridge5Extension_5 bridge5extension_51);

   PlayerControllerBridge bridge$getPlayerController();

   WorldBridgeExtension bridge$getWorld();

   Optional<BridgeExtension> bridge$getPointedEntity();

   NetHandlerPlayClientBridge bridge$getClientPacketListener();

   GuiScreenBridge bridge$getCurrentScreen();

   GuiScreenBridge bridge$getCurrentScreenOrRewind();

   Bridge10_2 bridge$getFontRenderer();

   Bridge11_2 bridge$getResourceManager();

   ResourcePackBridge bridge$getMcDefaultResourcePack();

   TextureManagerBridge bridge$getTextureManager();

   Horsestats bridge$getSession();

   PropertyMap bridge$getProfileProperties();

   MinecraftSessionService bridge$getSessionService();

   GameRendererBridge bridge$getGameRenderer();

   @Nullable
   ServerDataBridge bridge$getCurrentServerData();

   RenderItemBridge bridge$getRenderItem();

   SoundHandlerBridge bridge$getSoundHandler();

   @VersionGate(min = 26)
   default Bridge9_3 bridge$getShaderManager() {
      throw new AbstractMethodErrorImpl();
   }

   void bridge$setSession(Horsestats horsestats1);

   File bridge$getMcDataDir();

   void bridge$displayScreen(GuiScreenBridge bridge5extension61);

   void bridge$setCurrentScreenSilent(GuiScreenBridge bridge5extension61);

   void bridge$openChat(String text1);

   int bridge$displayWidth();

   void bridge$setDisplayWidth(int number1);

   int bridge$displayHeight();

   void bridge$setDisplayHeight(int number1);

   int bridge$logicalWidth();

   int bridge$logicalHeight();

   boolean bridge$unicode();

   int bridge$getGuiScale();

   void bridge$setGuiScale(int number1);

   void bridge$setRawGuiScale(int number1);

   void bridge$updateFramebufferSize();

   void bridge$loadWorld(WorldBridgeExtension itemcounter6extension1);

   void bridge$setWorldDirect(WorldBridgeExtension itemcounter6extension1);

   void bridge$refreshResources();

   void bridge$shutdownMinecraftApplet();

   GameOptionsBridge bridge$getGameSettings();

   @VersionGate(min = 6)
   default long bridge$getWindowId() {
      throw new RuntimeException("bridge$getWindowId() can only be called in Modern!");
   }

   DisplayBridge bridge$getWindow();

   @VersionGate(min = 6)
   default void bridge$focusWindow() {
   }

   EntityRenderDispatcherBridge bridge$getEntityRenderDispatcher();

   @VersionGate(min = 8)
   default BlockEntityRenderDispatcherBridge bridge$getBlockEntityRenderDispatcher() {
      throw new AbstractMethodErrorImpl();
   }

   long bridge$getSystemTime();

   long bridge$getRealSystemTime();

   TimerBridge bridge$getTimer();

   GuiIngameBridge bridge$getGuiIngame();

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

   void bridge$setMainRenderTarget(Bridge3_24 bridge3_241);

   BridgeExtension bridge$getRenderViewEntity();

   void bridge$setRenderViewEntity(BridgeExtension bridgeextension1);

   Bridge14_3 bridge$getLevelRenderer();

   boolean bridge$hasInGameFocus();

   void bridge$setInGameFocus(boolean flag1);

   Bridge8Extension32 bridge$getTextureMap();

   Bridge8Extension32 bridge$getTextureMapById(ResourceLocationBridge horsestats141);

   boolean bridge$isFullScreen();

   void bridge$toggleFullscreen();

   ParticleEngineBridge bridge$getEffectRenderer();

   MovingObjectPositionBridge bridge$getObjectMouseOver();

   void bridge$submit(Runnable runnable1);

   void bridge$schedule(Runnable runnable1);

   Bridge3_31 bridge$getItemRenderer();

   boolean bridge$isGamePaused();

   boolean bridge$isDisplayCreated();

   boolean bridge$isDisplayActive();

   void bridge$setDisplayTitle(String text1);

   void bridge$setRepeatEventsEnabled(boolean flag1);

   ServerDataBridge bridge$lastServerData();

   @VersionGate(min = 6)
   @Nullable
   default Bridge15_2 bridge$getRenderBuffers() {
      throw new RuntimeException("bridge$getRenderBuffers() can only be used in modern!");
   }

   default Set<Bridge3_23> bridge$xrayBlocks() {
      return Collections.emptySet();
   }

   ResourcePackBridge bridge$getSelectedResourcePack();

   List<ResourcePackBridge> bridge$getAllSelectedPacks();

   List<File> bridge$getClientResourcePacksFiles();

   List<File> bridge$getServerResourcePacksFiles();

   List<File> bridge$getAllBuiltInPacksFiles();

   @Nullable
   String bridge$getBuiltInPackName(String text1);

   Optional<InetSocketAddress> bridge$resolveAddress(String text1);

   void bridge$connect(ServerDataBridge bridge3_191, GuiScreenBridge bridge5extension62);

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

   default void bridge$setRenderChunkBorder(boolean flag1) {
   }

   void bridge$setCurrentServer(ServerDataBridge bridge3_191);

   IntegratedServerBridge bridge$getIntegratedServer();

   default void bridge$recreateLoadingScreen() {
   }

   boolean bridge$isRunning();

   boolean bridge$areResourcesLoaded();

   default boolean bridge$hasOverlay() {
      return false;
   }

   void bridge$processKeyboardAndMouse();

   int bridge$getCreativeTab();

   void bridge$setCreativeTab(int number1);

   void bridge$clearLevel();

   Bridge7_6 bridge$getLanguageManager();

   void bridge$processRewindTick();

   @VersionGate(min = 6)
   void bridge$resizeDisplay();

   void bridge$pickBlock();

   void bridge$waitOnAllChunksRendering();

   default void method1(Bridge3_24 bridge3_241, boolean flag2) {
      this.bridge$overrideMainRenderTarget(bridge3_241, flag2, false);
   }

   void bridge$overrideMainRenderTarget(Bridge3_24 bridge3_241, boolean flag2, boolean flag3);

   @VersionGate(min = 8)
   void bridge$makePanorama(File file1, int number2, int number3);

   int bridge$getProtocolVersion();

   BridgeExtension bridge$getSpectatedEntity();

   void bridge$setSpectatedEntity(BridgeExtension bridgeextension1);

   void bridge$joinWorld(String text1, String text2, WorldSettingsBridge itemcounter43, Runnable runnable4);

   boolean bridge$deleteWorld(String text1);

   List<ProfilerResultBridge> bridge$getPieChartResults();

   boolean bridge$isReducedDebugInfo();

   boolean bridge$isMouseButtonDown(int number1);

   @VersionGate(min = 6)
   boolean bridge$isRepositoryReloadManual();
}
