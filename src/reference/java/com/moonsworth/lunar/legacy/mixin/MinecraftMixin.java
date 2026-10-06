package com.moonsworth.lunar.legacy.mixin;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalLongRef;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.PropertyMap;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.Bridge12_4;
import com.moonsworth.lunar.bridge.IResourcePackBridge;
import com.moonsworth.lunar.bridge.Bridge14_3;
import com.moonsworth.lunar.bridge.Bridge2_25;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge3_11;
import com.moonsworth.lunar.bridge.Bridge3_17;
import com.moonsworth.lunar.bridge.Bridge3_19;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.Bridge3_31;
import com.moonsworth.lunar.bridge.Bridge3_33;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension69;
import com.moonsworth.lunar.bridge.Bridge5Extension9;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.Bridge5_19;
import com.moonsworth.lunar.bridge.Bridge7_6;
import com.moonsworth.lunar.bridge.Bridge8Extension32;
import com.moonsworth.lunar.bridge.Bridge8Handler2;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.bridge.Bridge_40;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.bridge.ProfilerResultBridge;
import com.moonsworth.lunar.bridge.glintcolorizer.Glintcolorizer;
import com.moonsworth.lunar.bridge.horsestats.Horsestats;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.MovingObjectPositionHitResult;
import com.moonsworth.lunar.bridge.horsestats.TimerBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter4;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.input.InputActionLegacy;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.event.mixin.highlight.AlertUpdateEvent;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseButtonLegacy;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseWheelLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewLegacy;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.pkg.Pkg;
import com.moonsworth.lunar.client.pkg.Pkg3;
import com.moonsworth.lunar.client.pkg.Pkg6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.Bridge3Handler;
import com.moonsworth.lunar.legacy.wrapper.RenderViewEntityProxy;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import net.minecraft.block.Block;
import net.minecraft.client.LoadingScreenRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.achievement.GuiAchievement;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.toasts.GuiToast;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.debug.DebugRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.settings.KeyBinding_v1_7;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.profiler.IPlayerUsage;
import net.minecraft.profiler.Profiler;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.MouseHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.ISaveFormat;
import org.apache.commons.lang3.SystemUtils;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin implements Bridge5_12, IPlayerUsage, IThreadListener {
   @Shadow
   public static int debugFPS;
   @Shadow
   public LoadingScreenRenderer loadingScreen;
   @Shadow
   public GuiScreen currentScreen;
   @Shadow
   public WorldClient world;
   @Shadow
   public FontRenderer fontRenderer;
   @Shadow
   public IReloadableResourceManager resourceManager;
   @Shadow
   public TextureManager renderEngine;
   @Mutable
   @Final
   @Shadow
   public Session session;
   @Final
   @Shadow
   public File gameDir;
   @Shadow
   public int displayWidth;
   @Shadow
   public int displayHeight;
   @Shadow
   public DebugRenderer debugRenderer$v1_12;
   @Shadow
   public GameSettings gameSettings;
   @Shadow
   public EntityRenderer entityRenderer;
   @Shadow
   public ServerData currentServerData;
   @Final
   @Shadow
   public DefaultResourcePack defaultResourcePack;
   @Shadow
   public Entity renderViewEntity$v1_8;
   @Shadow
   public EntityLivingBase renderViewEntity$v1_7;
   @Shadow
   public GuiIngame ingameGUI;
   @Shadow
   public Entity pointedEntity;
   @Shadow
   public RenderItem renderItem;
   @Final
   @Shadow
   public PropertyMap profileProperties;
   @Shadow
   public EntityPlayerSP player$v1_8;
   @Shadow
   public EntityClientPlayerMP thePlayer$v1_7;
   @Final
   @Shadow
   public Timer timer;
   @Shadow
   public MovingObjectPosition objectMouseOver;
   @Shadow
   public RenderGlobal renderGlobal;
   @Shadow
   public boolean inGameHasFocus;
   @Shadow
   public TextureMap textureMapBlocks;
   @Shadow
   public PlayerControllerMP playerController;
   @Shadow
   public EffectRenderer effectRenderer;
   @Shadow
   public SoundHandler soundHandler;
   @Final
   @Shadow
   public MinecraftSessionService sessionService;
   @Shadow
   public boolean isGamePaused;
   @Unique
   private Bridge3_19 bridge$lastServerData;
   @Unique
   private long lunar$frameTimeNs;
   @Shadow
   public IntegratedServer integratedServer;
   @Shadow
   public volatile boolean running;
   @Unique
   private boolean lunar$resourcesLoaded = false;
   @Shadow
   public long debugCrashKeyPressTime;
   @Shadow
   public int joinPlayerCounter;
   @Shadow
   public MouseHelper mouseHelper;
   @Shadow
   public static boolean IS_RUNNING_ON_MAC;
   @Shadow
   public Queue<FutureTask<?>> scheduledTasks;
   @Shadow
   public Queue scheduledTasks$v1_7;
   @Shadow
   public GuiAchievement guiAchievement;
   @Shadow
   public GuiToast toastGui$v1_12;
   @Shadow
   public Profiler profiler;
   @Shadow
   public String debugProfilerName;
   @Shadow
   public Framebuffer framebuffer;
   @Unique
   public long lunar$realSystemTime;
   @Unique
   public BridgeExtension lunar$spectatedEntity = null;

   @Shadow
   public abstract ResourcePackRepository getResourcePackRepository();

   @Shadow
   public abstract boolean isFullScreen();

   @Shadow
   public abstract void toggleFullscreen();

   @Shadow
   public abstract TextureManager getTextureManager();

   @Shadow
   public abstract void loadWorld(WorldClient var1);

   @Shadow
   public abstract Framebuffer getFramebuffer();

   @Shadow
   public abstract PropertyMap getProfileProperties();

   @Shadow
   public abstract void refreshResources();

   @Shadow
   public abstract void shutdownMinecraftApplet();

   @Shadow
   public abstract NetHandlerPlayClient getConnection$v1_12();

   @Shadow
   public abstract NetHandlerPlayClient getNetHandler();

   @Shadow
   public abstract RenderManager getRenderManager();

   @Shadow
   public abstract ItemRenderer getItemRenderer();

   @Shadow
   public abstract void updateDisplay();

   @Shadow
   public abstract void resetSize$v1_7();

   @Shadow
   public abstract void displayGuiScreen(GuiScreen var1);

   @Shadow
   public abstract boolean isUnicode();

   @Shadow
   public abstract void setRenderViewEntity(Entity var1);

   @Shadow
   public abstract void runTickKeyboard$v1_12();

   @Shadow
   public abstract void runTickMouse$v1_12();

   @Shadow
   public abstract void setIngameFocus();

   @Shadow
   public abstract void dispatchKeypresses();

   @Shadow
   public abstract void displayInGameMenu();

   @Shadow
   public abstract void updateDebugProfilerName(int var1);

   @Shadow
   public abstract LanguageManager getLanguageManager();

   @Shadow
   public abstract void updateFramebufferSize();

   @Shadow
   public abstract void setIngameNotInFocus();

   @Shadow
   public abstract ListenableFuture<Object> addScheduledTask(Runnable var1);

   @Shadow
   public abstract ListenableFuture addScheduledTask(Runnable var1);

   @Shadow
   public abstract void middleClickMouse();

   @Shadow
   public abstract ISaveFormat getSaveLoader();

   @Shadow
   public abstract void launchIntegratedServer(String var1, String var2, WorldSettings var3);

   @Override
   public Bridge5Extension6 bridge$getCurrentScreen() {
      return (Bridge5Extension6)this.currentScreen;
   }

   @Override
   public Bridge5Extension6 bridge$getCurrentScreenOrRewind() {
      RewindHandlers var1 = ThreadModuleDump63.method4().method40().method85().method35();
      return var1 != null ? var1.method42().get().method8().method5() : this.bridge$getCurrentScreen();
   }

   @Override
   public Bridge5Extension_5 bridge$getPlayer() {
      return (Bridge5Extension_5)(ThreadModuleDump63.MC_VERSION >= 1 ? this.player$v1_8 : this.thePlayer$v1_7);
   }

   @Override
   public void bridge$setPlayer(Bridge5Extension_5 var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.player$v1_8 = (EntityPlayerSP)var1;
      } else {
         this.thePlayer$v1_7 = (EntityClientPlayerMP)var1;
      }
   }

   @Override
   public Bridge2_25 bridge$getPlayerController() {
      return (Bridge2_25)this.playerController;
   }

   @Override
   public Itemcounter6Extension bridge$getWorld() {
      return (Itemcounter6Extension)this.world;
   }

   @Override
   public Bridge10_2 bridge$getFontRenderer() {
      return (Bridge10_2)this.fontRenderer;
   }

   @Override
   public Bridge11_2 bridge$getResourceManager() {
      return (Bridge11_2)this.resourceManager;
   }

   @Override
   public IResourcePackBridge bridge$getMcDefaultResourcePack() {
      return (IResourcePackBridge)this.defaultResourcePack;
   }

   @Override
   public Bridge8Handler2 bridge$getTextureManager() {
      return (Bridge8Handler2)this.renderEngine;
   }

   @Override
   public Horsestats bridge$getSession() {
      return (Horsestats)this.session;
   }

   @Override
   public PropertyMap bridge$getProfileProperties() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         return this.getProfileProperties();
      }

      PropertyMap var1 = new PropertyMap();
      GameProfile var2 = this.sessionService.fillProfileProperties(this.session.getProfile(), false);
      var1.putAll(var2.getProperties());
      return var1;
   }

   @Override
   public MinecraftSessionService bridge$getSessionService() {
      return this.sessionService;
   }

   @Override
   public Bridge3_17 bridge$getGameRenderer() {
      return (Bridge3_17)this.entityRenderer;
   }

   @Override
   public Bridge3_19 bridge$getCurrentServerData() {
      return (Bridge3_19)this.currentServerData;
   }

   @Override
   public Bridge5_19 bridge$getRenderItem() {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? (Bridge5_19)this.renderItem
         : (Bridge5_19)RenderManager.theMinecraft.entityRenderMap$v1_7.get(EntityItem.class);
   }

   @Override
   public Bridge3_11 bridge$getSoundHandler() {
      return (Bridge3_11)this.soundHandler;
   }

   @Override
   public void bridge$setSession(Horsestats var1) {
      Session var2 = this.session;
      this.session = (Session)var1;
      if (var1 != null) {
         Slayer.method3("Setting user: " + this.session.getUsername());
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            this.profileProperties.clear();
         }

         if (ThreadModuleDump63.method4() != null) {
            ThreadModuleDump63.method4().method14((Horsestats)var2);
         }
      }
   }

   @Override
   public File bridge$getMcDataDir() {
      return this.gameDir;
   }

   @Override
   public void bridge$displayScreen(Bridge5Extension6 var1) {
      if (Client.method109() != null) {
         Client.method109().method15(var1);
      }

      this.displayGuiScreen((GuiScreen)var1);
   }

   @Override
   public void bridge$setCurrentScreenSilent(Bridge5Extension6 var1) {
      this.currentScreen = (GuiScreen)var1;
   }

   @Override
   public void bridge$openChat(String var1) {
      this.displayGuiScreen(new GuiChat(var1));
   }

   @Override
   public int bridge$displayWidth() {
      return this.displayWidth;
   }

   @Override
   public void bridge$setDisplayWidth(int var1) {
      this.displayWidth = var1;
   }

   @Override
   public int bridge$displayHeight() {
      return this.displayHeight;
   }

   @Override
   public void bridge$setDisplayHeight(int var1) {
      this.displayHeight = var1;
   }

   @Override
   public int bridge$logicalWidth() {
      return Display.getWidth();
   }

   @Override
   public int bridge$logicalHeight() {
      return Display.getHeight();
   }

   @Override
   public boolean bridge$unicode() {
      return this.isUnicode();
   }

   @Override
   public int bridge$getGuiScale() {
      return this.gameSettings.guiScale;
   }

   @Override
   public void bridge$setGuiScale(int var1) {
      this.gameSettings.guiScale = var1;
   }

   @Override
   public void bridge$setRawGuiScale(int var1) {
      this.gameSettings.guiScale = var1;
   }

   @Override
   public void bridge$updateFramebufferSize() {
      this.updateFramebufferSize();
   }

   @Override
   public void bridge$loadWorld(Itemcounter6Extension var1) {
      this.loadWorld((WorldClient)var1);
   }

   @Override
   public void bridge$setWorldDirect(Itemcounter6Extension var1) {
      this.world = (WorldClient)var1;
   }

   @Override
   public void bridge$shutdownMinecraftApplet() {
      this.shutdownMinecraftApplet();
   }

   @Override
   public ClientPacketListenerBridge bridge$getClientPacketListener() {
      ClientPacketListenerBridge var1;
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         var1 = (ClientPacketListenerBridge)this.getConnection$v1_12();
      } else {
         var1 = (ClientPacketListenerBridge)this.getNetHandler();
      }

      if (var1 == null && this.currentScreen instanceof Bridge5Extension69 var2) {
         var1 = var2.bridge$getClientPacketListener();
      }

      return var1;
   }

   @Override
   public void bridge$refreshResources() {
      this.refreshResources();
   }

   @Override
   public Bridge2_43 bridge$getEntityRenderDispatcher() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? (Bridge2_43)this.getRenderManager() : (Bridge2_43)RenderManager.theMinecraft;
   }

   @Override
   public Bridge3_33 bridge$getWindow() {
      try {
         Field var1 = Display.class.getDeclaredField("display_impl");
         var1.setAccessible(true);
         return (Bridge3_33)var1.get(null);
      } catch (IllegalAccessException | NoSuchFieldException var2) {
         throw new RuntimeException(var2);
      }
   }

   @Override
   public Optional<BridgeExtension> bridge$getPointedEntity() {
      return Optional.ofNullable((BridgeExtension)this.pointedEntity);
   }

   @Override
   public long bridge$getSystemTime() {
      return Minecraft.getSystemTime();
   }

   @Override
   public long bridge$getRealSystemTime() {
      return Sys.getTime() * 1000L / Sys.getTimerResolution();
   }

   @Override
   public GameOptionsBridge bridge$getGameSettings() {
      return (GameOptionsBridge)this.gameSettings;
   }

   @Override
   public Bridge5Extension9 bridge$getGuiIngame() {
      return (Bridge5Extension9)this.ingameGUI;
   }

   @Override
   public int bridge$getDebugFPS() {
      return debugFPS;
   }

   @Override
   public float bridge$getFrametime() {
      return (float)this.lunar$frameTimeNs / 1.0E9F;
   }

   @Override
   public BridgeExtension bridge$getRenderViewEntity() {
      return (BridgeExtension)(ThreadModuleDump63.MC_VERSION >= 1 ? this.renderViewEntity$v1_8 : this.renderViewEntity$v1_7);
   }

   @Override
   public void bridge$setRenderViewEntity(BridgeExtension var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.setRenderViewEntity((Entity)var1);
      } else if (var1 == null) {
         this.renderViewEntity$v1_7 = null;
      } else if (var1 instanceof EntityLivingBase var2) {
         this.renderViewEntity$v1_7 = var2;
      } else {
         this.renderViewEntity$v1_7 = new RenderViewEntityProxy((Entity)var1);
      }
   }

   @Override
   public Bridge14_3 bridge$getLevelRenderer() {
      return (Bridge14_3)this.renderGlobal;
   }

   @Override
   public Bridge3_24 bridge$getMainRenderTarget() {
      return (Bridge3_24)this.getFramebuffer();
   }

   @Override
   public void bridge$setMainRenderTarget(Bridge3_24 var1) {
      this.framebuffer = (Framebuffer)var1;
   }

   @Override
   public boolean bridge$hasInGameFocus() {
      return this.inGameHasFocus;
   }

   @Override
   public void bridge$setInGameFocus(boolean var1) {
      this.inGameHasFocus = var1;
      if (var1) {
         if (!IS_RUNNING_ON_MAC && ThreadModuleDump63.MC_VERSION >= 5) {
            KeyBinding.updateKeyBindState$v1_12();
         }

         this.mouseHelper.grabMouseCursor();
      } else {
         this.mouseHelper.ungrabMouseCursor();
      }
   }

   @Override
   public Bridge8Extension32 bridge$getTextureMap() {
      return (Bridge8Extension32)this.textureMapBlocks;
   }

   @Override
   public Bridge8Extension32 bridge$getTextureMapById(ResourceLocationBridge var1) {
      return TextureMap.locationBlocksTexture.equals(var1) ? (Bridge8Extension32)this.textureMapBlocks : null;
   }

   @Override
   public boolean bridge$isFullScreen() {
      return this.isFullScreen();
   }

   @Override
   public void bridge$toggleFullscreen() {
      this.toggleFullscreen();
   }

   @Override
   public Bridge_40 bridge$getEffectRenderer() {
      return (Bridge_40)this.effectRenderer;
   }

   @Override
   public TimerBridge bridge$getTimer() {
      return (TimerBridge)this.timer;
   }

   @Override
   public MovingObjectPositionHitResult bridge$getObjectMouseOver() {
      return (MovingObjectPositionHitResult)this.objectMouseOver;
   }

   @Override
   public void bridge$submit(Runnable var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.addScheduledTask(var1);
      } else {
         this.addScheduledTask(var1);
      }
   }

   @Override
   public void bridge$schedule(Runnable var1) {
      ListenableFutureTask var2 = ListenableFutureTask.create(Executors.callable(var1));
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         synchronized (this.scheduledTasks) {
            this.scheduledTasks.add(var2);
         }
      } else {
         synchronized (this.scheduledTasks$v1_7) {
            this.scheduledTasks$v1_7.add(var2);
         }
      }
   }

   @Override
   public Bridge3_31 bridge$getItemRenderer() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? (Bridge3_31)this.getItemRenderer() : (Bridge3_31)this.entityRenderer.field_177083_e;
   }

   @Override
   public boolean bridge$isGamePaused() {
      return this.isGamePaused;
   }

   @Override
   public boolean bridge$isDisplayActive() {
      return Display.isActive();
   }

   @Override
   public boolean bridge$isDisplayCreated() {
      return Display.isCreated();
   }

   @Override
   public void bridge$setDisplayTitle(String var1) {
      Display.setTitle(var1);
   }

   @Override
   public Bridge3_19 bridge$lastServerData() {
      return this.bridge$lastServerData;
   }

   @Inject(method = "setServerData", at = @At("HEAD"))
   private void bridge$setServerData(ServerData var1, CallbackInfo var2) {
      this.bridge$lastServerData = (Bridge3_19)this.currentServerData;
   }

   @Override
   public void bridge$setCurrentServer(Bridge3_19 var1) {
      this.bridge$lastServerData = var1;
   }

   @Override
   public void bridge$setRepeatEventsEnabled(boolean var1) {
      Keyboard.enableRepeatEvents(var1);
   }

   @Override
   public IResourcePackBridge bridge$getSelectedResourcePack() {
      List var1 = ThreadModuleDump63.MC_VERSION >= 1
         ? this.getResourcePackRepository().getRepositoryEntries()
         : this.getResourcePackRepository().getRepositoryEntries();
      return (IResourcePackBridge)var1.stream().map(var0 -> var0.reResourcePack).findFirst().orElseGet(() -> (IResourcePack)this.bridge$getMcDefaultResourcePack());
   }

   @Override
   public List<File> bridge$getClientResourcePacksFiles() {
      List var1 = this.bridge$getAllSelectedPacks();
      IResourcePack var2 = this.getResourcePackRepository().resourcePackInstance;
      return var1.stream()
         .filter(var1x -> var1x instanceof AbstractResourcePack && var1x != var2)
         .map(var0 -> ((AbstractResourcePack)var0).resourcePackFile)
         .toList();
   }

   @Override
   public List<File> bridge$getServerResourcePacksFiles() {
      return this.getResourcePackRepository().resourcePackInstance instanceof AbstractResourcePack var2 ? List.of(var2.resourcePackFile) : List.of();
   }

   @Override
   public List<File> bridge$getAllBuiltInPacksFiles() {
      return List.of();
   }

   @Nullable
   @Override
   public String bridge$getBuiltInPackName(String var1) {
      return null;
   }

   @Override
   public List<IResourcePackBridge> bridge$getAllSelectedPacks() {
      List var1 = ThreadModuleDump63.MC_VERSION >= 1
         ? this.getResourcePackRepository().getRepositoryEntries()
         : this.getResourcePackRepository().getRepositoryEntries();
      return var1.stream().map(var0 -> (IResourcePackBridge)var0.reResourcePack).toList();
   }

   @Override
   public Optional<InetSocketAddress> bridge$resolveAddress(String var1) {
      return Pkg6.field1.method1(Pkg3.method1(var1)).map(Pkg::method3);
   }

   @Override
   public void bridge$connect(Bridge3_19 var1, Bridge5Extension6 var2) {
      this.displayGuiScreen(new GuiConnecting((GuiScreen)var2, (Minecraft)this, (ServerData)var1));
   }

   @Override
   public boolean bridge$isConnectedToRealms() {
      return false;
   }

   @Override
   public void bridge$updateDisplay() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.updateDisplay();
      } else {
         this.resetSize$v1_7();
      }
   }

   @Override
   public boolean bridge$isWindowFocused() {
      return Display.isActive();
   }

   @Override
   public boolean bridge$isFpsLimited() {
      return false;
   }

   @Override
   public Bridge12_4 bridge$getSkinManager() {
      return (Bridge12_4)((Minecraft)this).getSkinManager();
   }

   @Override
   public boolean bridge$renderChunkBorder() {
      return ThreadModuleDump63.MC_VERSION >= 5 && this.debugRenderer$v1_12.chunkBorderEnabled;
   }

   @Override
   public void bridge$setRenderChunkBorder(boolean var1) {
      if (ThreadModuleDump63.MC_VERSION >= 5 && this.debugRenderer$v1_12 != null) {
         this.debugRenderer$v1_12.chunkBorderEnabled = var1;
      }
   }

   @Inject(method = "run", at = @At("HEAD"))
   private void lunar$run(CallbackInfo var1) {
      Bridge.method2(this);
   }

   @Override
   public Set<Bridge3_23> bridge$xrayBlocks() {
      Builder var1 = ImmutableSet.builder();
      var1.add(
         new Block[]{
            Blocks.coal_ore,
            Blocks.iron_ore,
            Blocks.gold_ore,
            Blocks.redstone_ore,
            Blocks.lapis_ore,
            Blocks.diamond_ore,
            Blocks.emerald_ore,
            Blocks.quartz_ore,
            Blocks.glowstone,
            Blocks.crafting_table,
            Blocks.torch,
            Blocks.ladder,
            Blocks.tnt,
            Blocks.coal_block,
            Blocks.iron_block,
            Blocks.gold_block,
            Blocks.diamond_block,
            Blocks.chest,
            Blocks.trapped_chest,
            Blocks.emerald_block,
            Blocks.redstone_block,
            Blocks.lapis_block,
            Blocks.fire,
            Blocks.mossy_cobblestone,
            Blocks.mob_spawner,
            Blocks.end_portal_frame,
            Blocks.enchanting_table,
            Blocks.bookshelf,
            Blocks.command_block,
            Blocks.furnace,
            Blocks.lit_furnace
         }
      );
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         var1.add(new Block[]{Blocks.lava$v1_7, Blocks.flowing_lava$v1_7, Blocks.water$v1_7, Blocks.flowing_water$v1_7});
      } else {
         var1.add(new Block[]{Blocks.lava, Blocks.flowing_lava, Blocks.water, Blocks.flowing_water});
      }

      return (Set<Bridge3_23>)var1.build();
   }

   @Override
   public void bridge$recreateLoadingScreen() {
      if (OpenGlHelper.isFramebufferEnabled()) {
         LoadingScreenRenderer var1 = this.loadingScreen;
         if (var1 == null || var1.framebufferMc == null || var1.framebufferMc.framebufferObject == -1) {
            this.loadingScreen = new LoadingScreenRenderer((Minecraft)this);
         }
      }
   }

   @Override
   public Glintcolorizer bridge$getIntegratedServer() {
      return (Glintcolorizer)this.integratedServer;
   }

   @Override
   public boolean bridge$isRunning() {
      return this.running;
   }

   @Override
   public Bridge7_6 bridge$getLanguageManager() {
      return (Bridge7_6)this.getLanguageManager();
   }

   @Annotation2(min = 1)
   @Inject(method = "runGameLoop", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;clear(I)V", ordinal = 0))
   private void lunar$startFrametime$v1_8(CallbackInfo var1, @Share("frametime") LocalLongRef var2) {
      var2.set(System.nanoTime());
   }

   @Annotation2(min = 1)
   @Inject(
      method = "runGameLoop",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/EntityRenderer;renderStreamIndicator$v1_8(F)V", shift = Shift.AFTER, ordinal = 0)
   )
   private void lunar$endFrametime$v1_8(CallbackInfo var1, @Share("frametime") LocalLongRef var2) {
      this.lunar$frameTimeNs = System.nanoTime() - var2.get();
   }

   @Annotation2(max = 0)
   @Inject(method = "runGameLoop", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glClear(I)V", ordinal = 0))
   private void lunar$startFrametime$v1_7(CallbackInfo var1, @Share("frametime") LocalLongRef var2) {
      var2.set(System.nanoTime());
   }

   @Annotation2(max = 0)
   @Inject(
      method = "runGameLoop",
      slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/client/shader/Framebuffer;framebufferRender(II)V")),
      at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;startSection(Ljava/lang/String;)V", ordinal = 0)
   )
   private void lunar$endFrametime$v1_7(CallbackInfo var1, @Share("frametime") LocalLongRef var2) {
      this.lunar$frameTimeNs = System.nanoTime() - var2.get();
   }

   @Inject(method = "refreshResources", at = @At("HEAD"))
   private void lunar$refreshResources$head(CallbackInfo var1) {
      this.lunar$resourcesLoaded = false;
   }

   @Inject(method = "refreshResources", at = @At("RETURN"))
   private void lunar$refreshResources$return(CallbackInfo var1) {
      this.lunar$resourcesLoaded = true;
      ClientEventBus.method29().method12(AlertUpdateEvent.class, () -> new AlertUpdateEvent(CompletableFuture.completedFuture(null)));
   }

   @Override
   public boolean bridge$areResourcesLoaded() {
      return this.lunar$resourcesLoaded;
   }

   @Override
   public void bridge$processKeyboardAndMouse() {
      if (ThreadModuleDump63.MC_VERSION == 5) {
         this.runTickKeyboard$v1_12();
         this.runTickMouse$v1_12();
      } else {
         while (Keyboard.next()) {
            if (ThreadModuleDump63.MC_VERSION <= 0) {
               KeyBinding_v1_7.setKeyBindState(Keyboard.getEventKey(), Keyboard.getEventKeyState());
               if (Keyboard.getEventKeyState()) {
                  KeyBinding_v1_7.onTick(Keyboard.getEventKey());
               }
            } else {
               KeyBinding.setKeyBindState(Keyboard.getEventKey(), Keyboard.getEventKeyState());
               if (Keyboard.getEventKeyState()) {
                  KeyBinding.onTick(Keyboard.getEventKey());
               }
            }

            if (this.debugCrashKeyPressTime > 0L) {
               if (this.bridge$getRealSystemTime() - this.debugCrashKeyPressTime >= 6000L) {
                  throw new ReportedException(new CrashReport("Manually triggered debug crash", new Throwable()));
               }

               if (!Keyboard.isKeyDown(46) || !Keyboard.isKeyDown(61)) {
                  this.debugCrashKeyPressTime = -1L;
               }
            } else if (Keyboard.isKeyDown(46) && Keyboard.isKeyDown(61)) {
               this.debugCrashKeyPressTime = this.bridge$getRealSystemTime();
            }

            this.dispatchKeypresses();
            if (Keyboard.getEventKeyState()) {
               if (ThreadModuleDump63.MC_VERSION == 0 && Keyboard.getEventKey() == 62 && this.entityRenderer != null) {
                  this.entityRenderer.deactivateShader$v1_7();
               }

               if (this.currentScreen == null) {
                  if (Keyboard.getEventKey() == 1) {
                     this.displayInGameMenu();
                  }

                  if (Keyboard.getEventKey() == 31 && Keyboard.isKeyDown(61)) {
                     this.refreshResources();
                  }

                  if (Keyboard.getEventKey() == 20 && Keyboard.isKeyDown(61)) {
                     this.refreshResources();
                  }

                  if (Keyboard.getEventKey() == 33 && Keyboard.isKeyDown(61)) {
                     boolean var1 = Keyboard.isKeyDown(42) | Keyboard.isKeyDown(54);
                     this.gameSettings.setOptionValue(Options.RENDER_DISTANCE, var1 ? -1 : 1);
                  }

                  if (Keyboard.getEventKey() == 30 && Keyboard.isKeyDown(61)) {
                     this.renderGlobal.loadRenderers();
                  }

                  if (Keyboard.getEventKey() == 35 && Keyboard.isKeyDown(61)) {
                     this.gameSettings.advancedItemTooltips = !this.gameSettings.advancedItemTooltips;
                     this.gameSettings.saveOptions();
                  }

                  if (ThreadModuleDump63.MC_VERSION == 0 && Keyboard.getEventKey() == 48 && Keyboard.isKeyDown(61)) {
                     RenderManager.debugBoundingBox$v1_7 = !RenderManager.debugBoundingBox$v1_7;
                  }

                  if (Keyboard.getEventKey() == 25 && Keyboard.isKeyDown(61)) {
                     this.gameSettings.pauseOnLostFocus = !this.gameSettings.pauseOnLostFocus;
                     this.gameSettings.saveOptions();
                  }

                  if (Keyboard.getEventKey() == 59) {
                     this.gameSettings.hideGUI = !this.gameSettings.hideGUI;
                  }

                  if (Keyboard.getEventKey() == 61) {
                     this.gameSettings.showDebugInfo = !this.gameSettings.showDebugInfo;
                     this.gameSettings.showDebugProfilerChart = GuiScreen.isShiftKeyDown();
                  }

                  if (ThreadModuleDump63.MC_VERSION <= 0) {
                     if (this.gameSettings.keyBindTogglePerspective$v1_7.isPressed()) {
                        this.gameSettings.thirdPersonView++;
                        if (this.gameSettings.thirdPersonView > 2) {
                           this.gameSettings.thirdPersonView = 0;
                        }
                     }

                     if (this.gameSettings.keyBindSmoothCamera$v1_7.isPressed()) {
                        this.gameSettings.smoothCamera = !this.gameSettings.smoothCamera;
                     }
                  } else {
                     if (this.gameSettings.keyBindTogglePerspective.isPressed()) {
                        this.gameSettings.thirdPersonView++;
                        if (this.gameSettings.thirdPersonView > 2) {
                           this.gameSettings.thirdPersonView = 0;
                        }
                     }

                     if (this.gameSettings.keyBindSmoothCamera.isPressed()) {
                        this.gameSettings.smoothCamera = !this.gameSettings.smoothCamera;
                     }
                  }
               }

               if (this.gameSettings.showDebugInfo && this.gameSettings.showDebugProfilerChart) {
                  if (Keyboard.getEventKey() == 11) {
                     this.updateDebugProfilerName(0);
                  }

                  for (int var9 = 0; var9 < 9; var9++) {
                     if (Keyboard.getEventKey() == 2 + var9) {
                        this.updateDebugProfilerName(var9 + 1);
                     }
                  }
               }
            }
         }

         while (Mouse.next()) {
            int var10 = Mouse.getEventButton();
            if (ThreadModuleDump63.MC_VERSION <= 0) {
               KeyBinding_v1_7.setKeyBindState(var10 - 100, Mouse.getEventButtonState());
               if (Mouse.getEventButtonState()) {
                  KeyBinding_v1_7.onTick(var10 - 100);
               }
            } else {
               KeyBinding.setKeyBindState(var10 - 100, Mouse.getEventButtonState());
               if (Mouse.getEventButtonState()) {
                  KeyBinding.onTick(var10 - 100);
               }
            }

            long var2 = this.bridge$getRealSystemTime() - this.lunar$realSystemTime;
            if (var2 <= 200L) {
               int var4 = Mouse.getEventDWheel();
               if (var4 != 0) {
                  int var5 = var4;
                  EventMouseWheelLegacy var6 = ClientEventBus.method29().method12(EventMouseWheelLegacy.class, () -> new EventMouseWheelLegacy(var5));
                  if (var6 != null && var6.isCancelled() || ThreadModuleDump63.method4().method41().method6().method64().get()) {
                     var4 = 0;
                  }
               }

               if (var4 != 0) {
                  if (ThreadModuleDump63.MC_VERSION == 1) {
                     this.player$v1_8.inventory.changeCurrentItem(var4);
                  } else {
                     this.thePlayer$v1_7.inventory.changeCurrentItem(var4);
                     if (this.gameSettings.noclip$v1_7) {
                        if (var4 > 0) {
                           var4 = 1;
                        }

                        if (var4 < 0) {
                           var4 = -1;
                        }

                        GameSettings var11 = this.gameSettings;
                        var11.noclipRate$v1_7 += var4 * 0.25F;
                     }
                  }
               }

               if (this.currentScreen == null) {
                  if (!this.inGameHasFocus && Mouse.getEventButtonState()) {
                     this.setIngameFocus();
                  }
               } else if (this.currentScreen != null) {
                  this.currentScreen.handleMouseInput();
                  if (!ThreadModuleDump63.method4().method40().method85().method17(RewindHandlers::method62)) {
                     int var12 = Mouse.getEventDWheel();
                     if (var12 != 0) {
                        DriverViewLegacy.method21().method9(0.0, var12 > 0 ? 1.0 : -1.0);
                     }

                     int var13 = Mouse.getEventButton();
                     if (var13 != -1) {
                        EventMouseButtonLegacy var7 = ClientEventBus.method29()
                           .method12(
                              EventMouseButtonLegacy.class,
                              () -> new EventMouseButtonLegacy(
                                 Mouse.getEventButton(), Bridge3Handler.method2(), Mouse.getEventButtonState() ? InputActionLegacy.DOWN : InputActionLegacy.UP
                              )
                           );
                        if (var7 == null || !var7.isCancelled()) {
                           boolean var8 = Mouse.getEventButtonState();
                           DriverViewLegacy.method21().method8(var13, var8 ? 1 : 0, Bridge3Handler.method2());
                        }
                     }
                  }
               }
            }
         }

         this.lunar$realSystemTime = this.bridge$getRealSystemTime();
      }
   }

   @Override
   public int bridge$getCreativeTab() {
      return GuiContainerCreative.selectedTabIndex;
   }

   @Override
   public void bridge$setCreativeTab(int var1) {
      GuiContainerCreative.selectedTabIndex = var1;
   }

   @Override
   public void bridge$clearLevel() {
      this.loadWorld(null);
   }

   @Override
   public void bridge$processRewindTick() {
      ClientEventBus.method29().method12(EventClientTick.class, EventClientTick::new);
      if (EventClientTick.field1 != 0 && EventClientTick.field1 % 20 == 0) {
         ClientEventBus.method29().method12(EventEverySecond.class, EventEverySecond::new);
      }

      EventClientTick.field1++;
      if (this.world != null) {
         this.renderEngine.tick();
         Object var1 = ThreadModuleDump63.MC_VERSION >= 1 ? this.player$v1_8 : this.thePlayer$v1_7;
         if (var1 != null) {
            this.joinPlayerCounter++;
            if (this.joinPlayerCounter == 30) {
               this.joinPlayerCounter = 0;
               this.world.joinEntityInSurroundings((Entity)var1);
            }
         }

         if (!this.isGamePaused) {
            this.entityRenderer.updateRenderer();
            this.renderGlobal.updateClouds();
            if (this.world.lastLightningBolt > 0) {
               this.world.lastLightningBolt--;
            }

            this.world.updateEntities();
            this.world.tick();
         }
      }

      if (!this.isGamePaused) {
         this.effectRenderer.updateEffects();
      }
   }

   @Override
   public void bridge$pickBlock() {
      this.middleClickMouse();
   }

   @Override
   public void bridge$waitOnAllChunksRendering() {
      Bridge.method5().ifPresent(var1 -> var1.waitOnAllChunksRendering(this));
   }

   @Override
   public void bridge$overrideMainRenderTarget(Bridge3_24 var1, boolean var2, boolean var3) {
      Framebuffer var4;
      if (var1 instanceof Framebuffer var5) {
         var4 = var5;
      } else {
         var4 = this.framebuffer;
      }

      if (Bridge.method5().map(var0 -> var0.getConfig().hasFastRender()).orElse(false) && var3) {
         int var8 = var4 == this.framebuffer ? 0 : var4.framebufferObject;
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            OpenGlHelper.glBindFramebuffer(36009, var8);
         } else {
            OpenGlHelper.func_153171_g$v1_7(36009, var8);
         }

         int var6 = var4.framebufferWidth;
         int var7 = var4.framebufferHeight;
         if (var2) {
            if (ThreadModuleDump63.MC_VERSION >= 1) {
               GlStateManager.viewport(0, 0, var6, var7);
            } else {
               GL11.glViewport(0, 0, var6, var7);
            }
         }
      } else {
         var4.bindFramebuffer(var2);
      }
   }

   @Override
   public int bridge$getProtocolVersion() {
      return switch (ThreadModuleDump63.MC_VERSION) {
         case 0 -> 5;
         case 1 -> 47;
         case 5 -> 340;
         default -> throw new IllegalStateException("Unexpected version: " + ThreadModuleDump63.MC_VERSION);
      };
   }

   @Override
   public BridgeExtension bridge$getSpectatedEntity() {
      return this.lunar$spectatedEntity;
   }

   @Override
   public void bridge$setSpectatedEntity(BridgeExtension var1) {
      this.lunar$spectatedEntity = var1;
   }

   @Override
   public void bridge$joinWorld(String var1, String var2, Itemcounter4 var3, Runnable var4) {
      try {
         this.launchIntegratedServer(var2, var1, (WorldSettings)var3);
      } catch (ReportedException var6) {
         throw var6;
      } catch (Exception var7) {
         Slayer.method9(var7, "Failed to join world %s", var2);
         var4.run();
      }
   }

   @Override
   public boolean bridge$deleteWorld(String var1) {
      ISaveFormat var2 = this.getSaveLoader();
      var2.flushCache();

      try {
         if (!var2.deleteWorldDirectory(var1)) {
            Slayer.method8("World", "Failed to delete world save %s", var1);
            return false;
         } else {
            return true;
         }
      } catch (Exception var4) {
         Slayer.method9(var4, "Failed to delete world save %s", var1);
         return false;
      }
   }

   @Override
   public List<ProfilerResultBridge> bridge$getPieChartResults() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         List var2 = this.profiler.getProfilingData(this.debugProfilerName);
         return var2 == null ? null : var2.stream().map(var0 -> (ProfilerResultBridge)var0).toList();
      } else {
         List var1 = this.profiler.getProfilingData(this.debugProfilerName);
         return var1 == null ? null : var1.stream().map(var0 -> (ProfilerResultBridge)var0).toList();
      }
   }

   @Override
   public boolean bridge$isReducedDebugInfo() {
      return ThreadModuleDump63.MC_VERSION < 1 ? false : this.player$v1_8 != null && this.player$v1_8.hasReducedDebug() || this.gameSettings.reducedDebugInfo;
   }

   @Override
   public boolean bridge$isMouseButtonDown(int var1) {
      return Mouse.isButtonDown(var1);
   }

   @WrapOperation(
      method = "updateDisplayMode",
      at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setDisplayMode(Lorg/lwjgl/opengl/DisplayMode;)V")
   )
   private void lunar$fullscreenRefreshRateFix(DisplayMode var1, Operation<Void> var2) {
      if (SystemUtils.IS_OS_WINDOWS) {
         try {
            Constructor var3 = DisplayMode.class.getDeclaredConstructor(int.class, int.class, int.class, int.class, boolean.class);
            var3.setAccessible(true);
            var1 = (DisplayMode)var3.newInstance(var1.getWidth(), var1.getHeight(), var1.getBitsPerPixel(), 0, var1.isFullscreenCapable());
         } catch (Exception var4) {
            Slayer.warn("Failed to inject into display mode", var4);
         }
      }

      var2.call(new Object[]{var1});
   }
}
