package com.moonsworth.lunar.client.framework;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.lunarclient.common.v1.LauncherVersion;
import com.lunarclient.common.v1.LunarClientUiVersion;
import com.lunarclient.common.v1.LunarClientVersion;
import com.lunarclient.common.v1.MinecraftVersion;
import com.lunarclient.common.v1.PlayerModpack;
import com.lunarclient.common.v1.UuidAndUsername;
import com.lunarclient.websocket.analytics.v1.RecordEnabledModsRequest;
import com.lunarclient.websocket.handshake.v1.GameHandshake;
import com.lunarclient.websocket.handshake.v1.Handshake;
import com.lunarclient.websocket.handshake.v1.InstalledMod;
import com.lunarclient.websocket.handshake.v1.MinecraftIdentity;
import com.lunarclient.websocket.handshake.v1.InstalledMod.Type;
import com.lunarclient.websocket.heartbeat.v1.GameHeartbeatRequest;
import com.lunarclient.websocket.language.v1.Language;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BridgeImplementation;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension610;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.horsestats.Horsestats;
import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.alert.mixin.AlertType;
import com.moonsworth.lunar.client.ui.prompt.ActivePrompt;
import com.moonsworth.lunar.client.gui.BugReportScreen;
import com.moonsworth.lunar.client.framework.loading.LoadingStage;
import com.moonsworth.lunar.client.gui.LoadingScreen;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuHomeScreen;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuBackground;
import com.moonsworth.lunar.client.fishing.highlight.Fishing2Extension;
import com.moonsworth.lunar.client.framework.loading.LoadableHandler;
import com.moonsworth.lunar.client.util.performance.GcMonitor;
import com.moonsworth.lunar.client.account.AccountSession;
import com.moonsworth.lunar.client.audio.music.JamManager;
import com.moonsworth.lunar.client.account.SocialLinksManager;
import com.moonsworth.lunar.client.config.override.OptionOverrideLoader;
import com.moonsworth.lunar.client.network.websocket.AnalyticsCounters;
import com.moonsworth.lunar.client.network.server.PinnedServerManager;
import com.moonsworth.lunar.client.gui.UiStateStore;
import com.moonsworth.lunar.client.util.collection.LinkRegistry;
import com.moonsworth.lunar.client.account.TeamMemberManager;
import com.moonsworth.lunar.client.mod.render.ServerHologramStore;
import com.moonsworth.lunar.client.mod.render.ServerBorderRegistry;
import com.moonsworth.lunar.client.network.server.ServerDiscoveryManager;
import com.moonsworth.lunar.client.render.turbo.TurboEntityManager;
import com.moonsworth.lunar.client.network.server.ServerPinger;
import com.moonsworth.lunar.client.chat.ProfanityFilter;
import com.moonsworth.lunar.client.framework.LiveExperienceManager;
import com.moonsworth.lunar.client.util.performance.PerformanceReporter;
import com.moonsworth.lunar.client.cosmetics.VanillaCapeManager;
import com.moonsworth.lunar.client.mod.misc.EventChestRegistry;
import com.moonsworth.lunar.client.cosmetics.OutfitManager;
import com.moonsworth.lunar.client.chat.ConversationManager;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.cosmetics.holograms.MorphManager;
import com.moonsworth.lunar.client.framework.ItemSetRegistry;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.network.friend.FriendRequestManager;
import com.moonsworth.lunar.client.account.TabLogoManager;
import com.moonsworth.lunar.client.cosmetics.CosmeticCompatibility;
import com.moonsworth.lunar.client.render.turbo.TurboEngineManager;
import com.moonsworth.lunar.client.config.FavoriteColorsConfigRescued;
import com.moonsworth.lunar.client.config.GeneralSettings;
import com.moonsworth.lunar.client.config.GlobalOptionsSettings;
import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.config.ServerFeaturesSettings;
import com.moonsworth.lunar.client.account.AccountManager;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.account.BadgeManager;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteManager;
import com.moonsworth.lunar.client.gui.blog.BlogPostManager;
import com.moonsworth.lunar.client.cosmetics.SprayManager;
import com.moonsworth.lunar.client.account.skin.SavedSkinManager;
import com.moonsworth.lunar.client.framework.StreamerMode;
import com.moonsworth.lunar.client.network.server.ServerIconManager;
import com.moonsworth.lunar.client.cosmetics.CosmeticPreviewManager;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.config.SettingsManager;
import com.moonsworth.lunar.client.account.LunarPlusManager;
import com.moonsworth.lunar.client.alert.DismissedAlertStore;
import com.moonsworth.lunar.client.mod.misc.StaffModsSettings;
import com.moonsworth.lunar.client.framework.loading.LoadingStageImpl;
import com.moonsworth.lunar.client.render.turbo.TurboPipelineHook;
import com.moonsworth.lunar.client.cosmetics.emote.GeckoComputePipeline;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartVisibility;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.feature.nickhider.Nickhider2;
import com.moonsworth.lunar.client.freelook.Highlight3Handler;
import com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers;
import com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers2;
import com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers3;
import com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers4;
import com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers5;
import com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers6;
import com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers6Impl;
import com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers6Task;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.resourcepack.ResourcePackUpdateEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.event.mixin.holograms.OptionsReloadBaseEvent;
import com.moonsworth.lunar.client.event.mixin.holograms.mixin.IchorHandlersLoadedEvent;
import com.moonsworth.lunar.client.hitcolor.FogHandler;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.itemcounter.Itemcounter;
import com.moonsworth.lunar.client.config.migration.ConfigMigrator;
import com.moonsworth.lunar.client.config.migration.VanillaOptionsFile;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DualMarkerScreenLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.mixin.AccountBridgeLegacy;
import com.moonsworth.lunar.client.memory.Memory;
import com.moonsworth.lunar.client.mixin.EntityRenderer3;
import com.moonsworth.lunar.client.mixin.EntityRenderer4;
import com.moonsworth.lunar.client.mixin.EntityRendererType;
import com.moonsworth.lunar.client.mixin.EntityRendererType2;
import com.moonsworth.lunar.client.mixin.WebSocketClientHandler;
import com.moonsworth.lunar.client.mod.misc.replaymod.ReplayMod;
import com.moonsworth.lunar.client.util.Highlight3Task;
import com.moonsworth.lunar.client.util.net.ServiceEndpoints;
import com.moonsworth.lunar.client.util.ThreadModuleDump36;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump49;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import com.moonsworth.lunar.client.util.ThreadModuleDump80;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType2;
import com.moonsworth.lunar.client.waypoints.WebSocketClientIterator;
import com.moonsworth.lunar.ichor.Ichor5Handler_2;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import com.moonsworth.lunar.network.AnalyticsBatcher;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.sentry.Breadcrumb;
import io.sentry.Sentry;
import io.sentry.SentryLevel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import lombok.Generated;
import mchorse.emoticons.Emoticons;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.network.websocket.PromotionMessageHandler;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleManager;

public class Client {
   private static Client field1;
   public static final ExecutorService field2 = Executors.newCachedThreadPool(new DefaultThreadFactory("lunar-executor-thread"));
   private Memory field3;
   private final Highlight3Handler field4;
   private final com.moonsworth.lunar.client.keystrokes.Highlight3Iterator field5;
   private final StreamerMode field6;
   private EntityRenderer4 field7;
   private WebSocketClientIterator field8;
   private boolean field9 = false;
   private String world = "";
   private com.moonsworth.lunar.client.framework.metadata.ModMetadataFetcher field10;
   private final Set<LoadableHandler> field11;
   private final ModsSettings field12;
   private final SettingsManager field13;
   private final GlobalOptionsSettings field14;
   private final AccountManager field15;
   private final StaffModsSettings field16;
   private final EmoteManager field17;
   private final SprayManager field18;
   private final FogHandler field19;
   private final WaypointStore field20;
   private final FavoriteColorsConfigRescued field21;
   private final FriendRequestManager field22;
   private final ItemSetRegistry field23;
   private final ConversationManager field24;
   private final CosmeticManager field25;
   private final LunarPlusManager field26;
   private final OutfitManager field27;
   private final CosmeticPreviewManager field28;
   private final ServerHologramStore field29;
   private final com.moonsworth.lunar.client.mod.render.NametagOverrideStore field30;
   private final PinnedServerManager field31;
   private final TeamMemberManager field32;
   private final com.moonsworth.lunar.client.config.profile.ModProfileManager field33;
   private final ServerFeaturesSettings field34;
   private final ServerBorderRegistry field35;
   private final LinkRegistry field36;
   private final BlogPostManager field37;
   private final DismissedAlertStore field38;
   private final TranslationManager field39;
   private final com.moonsworth.lunar.client.ui.ScreenStack field40;
   private final com.moonsworth.lunar.client.gui.notification.NotificationManager field41;
   private final MorphManager field42;
   private final ProfanityFilter field43;
   private final com.moonsworth.lunar.client.framework.metadata.RemoteMetadataManager field44;
   private final JamManager field45;
   private final GuiRewindhandlers5 field46;
   private final SavedSkinManager field47;
   private final com.moonsworth.lunar.client.cosmetics.gecko.MolangFunctionRegistry field48;
   private final CosmeticCompatibility field49;
   private final ServerIconManager field50;
   private final ServerDiscoveryManager field51;
   private final com.moonsworth.lunar.client.network.server.PingEntryConfig field52;
   private final com.moonsworth.lunar.client.coordinates.FogIterator field53;
   private final PerformanceReporter field54;
   private final LiveExperienceManager field55;
   private final ApolloModuleManager field56;
   private final com.moonsworth.lunar.client.render.texture.VanillaTextureIndex field57;
   private final AnalyticsCounters field58;
   private final EventChestRegistry field59;
   private final TurboEntityManager field60;
   private final TurboEngineManager field61;
   private final LoadingStageImpl field62;
   private final TabLogoManager field63;
   private final ActivePrompt field64;
   private final Itemcounter field65;
   private final VanillaCapeManager field66;
   private final BadgeManager field67;
   private final com.moonsworth.lunar.client.render.jit.JitAssetIndex field68;
   private final PromotionMessageHandler field69;
   private final TurboPipelineHook field70;
   private final com.moonsworth.lunar.client.render.shader.ShaderInjectRegistry field71;
   private final UiStateStore field72;
   private final SocialLinksManager field73;
   private final com.moonsworth.lunar.client.render.shader.DevShaderEditor field74;
   private final com.moonsworth.lunar.client.tps.FogIterator field75;
   private final ServerPinger field76;
   private final AnalyticsBatcher field77;
   private Optional<Nickhider2> field78 = Optional.empty();
   private final GuiIterator field79 = new GuiIterator();
   @Nullable
   private Map<String, Boolean> field80 = null;

   public Client() {
      ClientEventBus.method29().method30().lock();
      long var1 = System.currentTimeMillis();
      ThreadModuleDump48.bootstrap();
      Inventorymod2.init();
      method30(this);
      EntityRenderer4.bootstrap();
      Bridge.method8().method3();
      Bridge.method9().bridge$setDisplayTitle(method17());
      Slayer.method3("Starting Lunar client...");
      this.field77 = new AnalyticsBatcher(ServiceEndpoints.method9());
      this.field77.start();
      this.method4();
      this.field4 = new Highlight3Handler();
      this.field5 = new com.moonsworth.lunar.client.keystrokes.Highlight3Iterator();
      this.field6 = new StreamerMode();
      MainMenuHomeScreen.method3().method1();
      MainMenuBackground.method9().init();
      LoadingScreen.method1(ThreadModuleDump63.MC_VERSION >= 6 ? "Starting Lunar Client" : "Starting Minecraft");
      ClientEventBus.method29().method2(OptionsReloadBaseEvent.Data12.class, var0 -> new OptionOverrideLoader());

      try {
         this.field10 = new com.moonsworth.lunar.client.framework.metadata.ModMetadataFetcher();
         this.field64 = new ActivePrompt();
         this.field65 = new Itemcounter();
         this.field63 = new TabLogoManager();
         this.field40 = new com.moonsworth.lunar.client.ui.ScreenStack();
         this.field41 = new com.moonsworth.lunar.client.gui.notification.NotificationManager();
         this.field39 = new TranslationManager();
         this.field33 = new com.moonsworth.lunar.client.config.profile.ModProfileManager();
         this.field52 = new com.moonsworth.lunar.client.network.server.PingEntryConfig();
         this.field12 = new ModsSettings();
         this.field16 = new StaffModsSettings();
         this.field14 = (GlobalOptionsSettings)new GlobalOptionsSettings().IHRRHCCHCIRHOICCHRRRCRCCCCHOCI();
         this.field13 = new SettingsManager();
         Builder var3 = ImmutableSet.builder();
         var3.add(
            new LoadableHandler[]{
               new FontRegistry(),
               this.field63,
               this.field40,
               this.field41,
               this.field39,
               this.field33,
               this.field52,
               this.field16,
               this.field14,
               this.field13,
               this.field12,
               this.field15 = new AccountManager(),
               this.field20 = new WaypointStore(),
               this.field21 = new FavoriteColorsConfigRescued(),
               this.field22 = new FriendRequestManager(),
               this.field24 = new ConversationManager(),
               this.field50 = new ServerIconManager(),
               this.field51 = new ServerDiscoveryManager(),
               this.field23 = new ItemSetRegistry(),
               this.field42 = new MorphManager(),
               this.field25 = new CosmeticManager(),
               this.field26 = new LunarPlusManager(),
               this.field27 = new OutfitManager(),
               this.field28 = new CosmeticPreviewManager(),
               this.field67 = new BadgeManager(),
               this.field17 = new EmoteManager(),
               this.field18 = new SprayManager(),
               this.field19 = new FogHandler(),
               this.field29 = new ServerHologramStore(),
               this.field35 = new ServerBorderRegistry(),
               this.field30 = new com.moonsworth.lunar.client.mod.render.NametagOverrideStore(),
               this.field31 = new PinnedServerManager(),
               this.field32 = new TeamMemberManager(),
               this.field34 = new ServerFeaturesSettings(),
               this.field36 = new LinkRegistry(),
               this.field37 = new BlogPostManager(),
               this.field38 = new DismissedAlertStore(),
               this.field43 = new ProfanityFilter(),
               this.field47 = new SavedSkinManager(),
               this.field44 = new com.moonsworth.lunar.client.framework.metadata.RemoteMetadataManager(),
               this.field45 = new JamManager(),
               this.field48 = new com.moonsworth.lunar.client.cosmetics.gecko.MolangFunctionRegistry(),
               this.field49 = new CosmeticCompatibility(),
               this.field54 = new PerformanceReporter(),
               this.field53 = new com.moonsworth.lunar.client.coordinates.FogIterator(),
               this.field55 = new LiveExperienceManager(),
               this.field56 = new ApolloModuleManager(),
               this.field57 = new com.moonsworth.lunar.client.render.texture.VanillaTextureIndex(),
               this.field58 = new AnalyticsCounters(),
               this.field59 = new EventChestRegistry(),
               this.field60 = new TurboEntityManager(),
               this.field68 = new com.moonsworth.lunar.client.render.jit.JitAssetIndex(),
               this.field62 = new LoadingStageImpl(),
               this.field66 = new VanillaCapeManager(),
               this.field69 = new PromotionMessageHandler(),
               this.field70 = new TurboPipelineHook(),
               this.field74 = new com.moonsworth.lunar.client.render.shader.DevShaderEditor(),
               this.field71 = new com.moonsworth.lunar.client.render.shader.ShaderInjectRegistry(),
               this.field72 = new UiStateStore(),
               this.field73 = new SocialLinksManager(),
               this.field76 = new ServerPinger(),
               this.field75 = new com.moonsworth.lunar.client.tps.FogIterator()
            }
         );
         if (ThreadModuleDump63.MC_VERSION >= 8) {
            var3.add(this.field61 = new TurboEngineManager());
         } else {
            this.field61 = null;
         }

         this.field11 = var3.build();
         this.field10.close();
         this.field10 = null;
      } catch (Throwable var6) {
         Slayer.method7("Failed to construct managers!");
         var6.printStackTrace();
         throw var6;
      }

      Slayer.method3("Finished making managers.");
      this.field11.stream().filter(var0 -> var0 instanceof LoadingStage).forEach(var0 -> LoadingScreen.method5((LoadingStage)var0));
      Slayer.method3("Setting up listeners...");
      new GuiRewindhandlers3();
      new GuiRewindhandlers6();
      new GuiRewindhandlers2();
      this.field46 = new GuiRewindhandlers5();
      new GuiRewindhandlers6Impl();
      new GuiRewindhandlers6Task();
      new PlayerModelPartVisibility();
      new GuiRewindhandlers4();
      new GuiRewindhandlers();
      WebSocketClientIterator.bootstrap();
      this.method8();
      Slayer.method3("Init GameUI and GameUIRenderer...");
      com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method41();
      DriverViewLegacy.init();
      Slayer.method3("Setting up fixes...");
      if (ThreadModuleDumpType2.isLinux() && Bridge.getMinecraftVersion().method19()) {
         new com.moonsworth.lunar.client.framework.bootstrap.Highlight3Handler();
      }

      new Highlight3Task();
      Emoticons.register();
      this.field67.register();
      if (!com.moonsworth.lunar.client.framework.build.LunarBuildData.field4) {
         this.method3();
      }

      IchorPipeline var7 = IchorAPI.getPipeline(this.getClass().getClassLoader()).orElseThrow(() -> new IllegalStateException("Pipeline not found!"));
      ClientEventBus.method29()
         .method12(
            IchorHandlersLoadedEvent.class, () -> new IchorHandlersLoadedEvent(var7.method18().collect(Collectors.toMap(Ichor5Handler_2::getKey, var0x -> (Ichor5Handler_2)var0x)))
         );
      if (com.moonsworth.lunar.client.fishing.Fishing.method2(Fishing2Extension.class).isEmpty()) {
         Slayer.method4("Ichor", "Disabling ReplayMod because there is no valid link.");
         ((Alert2)this.field12.method64().method7(Framework.field4)).method1(this.field12.method64(), AlertType.SERVER, false);
      }

      this.start();
      ClientEventBus.method29().method12(ResourcePackUpdateEvent.class, () -> new ResourcePackUpdateEvent(ThreadModuleDump63.method3().bridge$getSelectedResourcePack()));
      if (!field2.isShutdown()) {
         field2.shutdown();

         try {
            if (!field2.awaitTermination(1L, TimeUnit.DAYS)) {
               field2.shutdownNow();
               Thread.currentThread().interrupt();
            }
         } catch (InterruptedException var5) {
            throw new RuntimeException(var5);
         }
      }

      Slayer.method3("Finished Lunar initialization.");
      IchorAPI.getPipeline(Client.class).ifPresent(IchorPipeline::method5);
      Slayer.method3("Lunar took " + (System.currentTimeMillis() - var1) + "ms to initialize.");
      ClientEventBus.method29().method30().unlock();
      GcMonitor.method2().init();
      com.moonsworth.lunar.client.util.performance.PerfSnapshotRecorder.method4().init();
      com.moonsworth.lunar.client.framework.ReducedDebugInfoNotifier.bootstrap();
      if (!com.moonsworth.lunar.client.framework.build.LunarBuildData.field4) {
         this.method1();
      }
   }

   private void method1() {
      try {
         Class var1 = ClassLoader.getSystemClassLoader().loadClass("com.moonsworth.lunar.genesis.Genesis");
         Field var2 = var1.getDeclaredField("START_TIME");
         long var3 = (Long)var2.get(null);
         long var5 = System.nanoTime() - var3;
         System.out.printf("BOOT TIMING: Took %.2f seconds to boot\n", (double)var5 / Duration.ofSeconds(1L).toNanos());
         this.getClass().getClassLoader().getClass().getClassLoader().loadClass("com.moonsworth.lunar.ichor.ProfileData").getDeclaredMethod("log").invoke(null);
      } catch (Throwable var7) {
         throw new RuntimeException(var7);
      }
   }

   public boolean method2() {
      return this.field11 != null && this.field10 == null;
   }

   private void method3() {
      try {
         Class var1 = Class.forName("com.moonsworth.lunar.client.dev.DevEnvironment");
         Method var2 = var1.getDeclaredMethod("init");
         var2.invoke(null);
      } catch (ReflectiveOperationException var3) {
         var3.printStackTrace();
      }
   }

   private void start() {
      Slayer.method3("Lunar.start");
      this.field39.setLanguage(ThreadModuleDump80.field12);
      this.field40
         .method7(ThreadModuleDump63.method3(), ThreadModuleDump63.method3().bridge$displayWidth(), ThreadModuleDump63.method3().bridge$displayHeight());
      this.field15.init();

      for (LoadableHandler var2 : this.field11) {
         if (var2 != this.field15) {
            try {
               var2.init();
            } catch (Exception var5) {
               Inventorymod2.method5(var5, "Manager Init");
            }
         }
      }

      LoadingScreen.method1(Bridge.getMinecraftVersion().method19() ? "Starting Minecraft" : "Finalizing");
      Slayer.method3("Launching");
      this.field9 = true;
      if (com.moonsworth.lunar.client.fishing.Fishing.method2(com.moonsworth.lunar.client.fishing.Fishing2Extension.class).isPresent()) {
         try {
            Class.forName("gg.essential.model.backend.minecraft.RenderLayerFactory", true, Client.class.getClassLoader());
            Slayer.method3("Essentials Mod: Preloaded RenderLayerFactory class to initialize its render types");
         } catch (ClassNotFoundException var4) {
            Slayer.method3("Essentials Mod: Not present");
         }
      }

      ClientEventBus.method29().method2(EventClientTick.class, var0 -> {
         ThreadModuleDump49.tick();
         ThreadModuleDump37.tick();
      });
      ClientEventBus.method29()
         .method2(
            EventEverySecond.class,
            var1 -> {
               if (ThreadModuleDump80.field13 != null) {
                  BridgeImplementation var2x = Bridge.method8();
                  if (ThreadModuleDump63.method4().method43().method23()) {
                     ThreadModuleDump63.method3()
                        .bridge$connect(
                           var2x.method50(ThreadModuleDump80.field13, ThreadModuleDump80.field13, false),
                           var2x.method18(new DualMarkerScreenLegacy(DriverRouteRegistryLegacy.field4, null))
                        );
                  } else {
                     AccountBridgeLegacy.method2();
                     ThreadModuleDump63.method4().method69().method3("Please login before attempt to join a server from the Launcher!");
                     Slayer.method3("Unable to quick join " + ThreadModuleDump80.field13 + " as the user can not play online!");
                  }

                  ThreadModuleDump80.field13 = null;
               }

               if (!ThreadModuleDump63.method5().isEmpty() && ThreadModuleDump63.method5().get().method112() == EntityRendererType2.READY) {
                  int var6 = EventClientTick.field1 / 20;
                  if (var6 % 60 == 0 && !ThreadModuleDump63.method4().method40().method85().method19()) {
                     ThreadModuleDump63.method5()
                        .ifPresent(var0 -> var0.method94().gameHeartbeat(null, GameHeartbeatRequest.getDefaultInstance(), var0x -> {}));
                  }

                  if (var6 % 30 == 0) {
                     HashMap var3 = new HashMap();

                     for (Framework7Extension var5x : ThreadModuleDump63.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
                        var3.put(var5x.getId(), var5x.isEnabled());
                     }

                     if (this.field80 == null || !this.field80.equals(var3)) {
                        this.field80 = var3;
                        ThreadModuleDump63.method5().ifPresent(var1x -> {
                           RecordEnabledModsRequest var2xx = RecordEnabledModsRequest.newBuilder().putAllMods(var3).build();
                           var1x.method100().recordEnabledMods(null, var2xx, var0x -> {});
                        });
                     }
                  }
               } else {
                  this.field80 = null;
               }
            }
         );
   }

   public void close() {
      try {
         if (this.field77 != null) {
            this.field77.shutdown();
         }

         com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method42();
         com.moonsworth.lunar.client.fishing.Fishing.method2(Fishing2Extension.class).ifPresent(var1 -> {
            ReplayMod var2x = this.field12.method64();
            if (!var2x.isEnabled() || !var1.isRecording()) {
               var1.method13();
            }
         });
         ClientEventBus.method29()
            .method12(
               com.moonsworth.lunar.client.event.mixin.holograms.ClientShutdownEvent.class,
               com.moonsworth.lunar.client.event.mixin.holograms.ClientShutdownEvent::new
            );
         if (this.field11 != null) {
            for (LoadableHandler var2 : this.field11) {
               var2.close();
            }
         }

         if (GeckoComputePipeline.method13()) {
            GeckoComputePipeline.method12().close();
         }

         if (this.field7 != null && this.field7.isOpen()) {
            this.field7.close();
         }

         if (this.field8 != null && this.field8.isOpen()) {
            this.field8.close();
         }

         VanillaOptionsFile.method13(ThreadModuleDump63.method3().bridge$getMcDataDir());
         ThreadModuleDump37.shutdown();
      } catch (Throwable var3) {
         var3.printStackTrace();
      }
   }

   private void method4() {
      File var1 = ThreadModuleDump48.field25.toPath().resolve("version").toFile();
      if (!ThreadModuleDump48.field25.exists()) {
         ThreadModuleDump48.field25.mkdirs();
         boolean var2 = false;
         if (ThreadModuleDump48.field26.exists()) {
            var2 = true;

            try {
               FileUtils.copyDirectory(ThreadModuleDump48.field26, ThreadModuleDump48.field25);
            } catch (IOException var20) {
               var2 = false;
            }
         }

         if (!var2) {
            String var3 = ThreadModuleDump63.method3().bridge$getMcDataDir() + File.separator + "config" + File.separator;
            File var4 = new File(var3 + "assets" + File.separator + "lunar");
            File var5 = new File(var3 + "lunar");
            File var6 = null;
            if (var4.exists() && var5.exists()) {
               if (var4.lastModified() > var5.lastModified()) {
                  var6 = var4;
               } else {
                  var6 = var5;
               }
            } else if (var4.exists()) {
               var6 = var4;
            } else if (var5.exists()) {
               var6 = var5;
            }

            if (var6 != null) {
               try {
                  FileUtils.copyDirectory(var6, ThreadModuleDump48.field25);
               } catch (Exception var19) {
                  var19.printStackTrace();
               }
            }

            try {
               if (var1.createNewFile()) {
                  try (OutputStreamWriter var7 = new OutputStreamWriter(new FileOutputStream(var1), StandardCharsets.UTF_8)) {
                     var7.write(String.valueOf(ConfigMigrator.field2));
                  } catch (IOException var17) {
                     var17.printStackTrace();
                  }
               }

               ThreadModuleDump48.field26.mkdirs();
               FileUtils.copyDirectory(ThreadModuleDump48.field25, ThreadModuleDump48.field26);
            } catch (Exception var18) {
               var18.printStackTrace();
            }
         }
      } else {
         try {
            int var21 = 0;
            if (var1.exists()) {
               String var22 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);

               try {
                  var21 = Integer.parseInt(var22);
                  Slayer.method1("Loading settings with version: " + var21);
               } catch (NumberFormatException var13) {
                  Slayer.method7("Could not read version string: " + var22);
               }
            }

            if (var21 < ConfigMigrator.field2) {
               FileUtils.deleteDirectory(ThreadModuleDump48.field26);
               FileUtils.copyDirectory(ThreadModuleDump48.field25, ThreadModuleDump48.field26);
            } else if (var21 > ConfigMigrator.field2) {
               if (ThreadModuleDump48.field26.exists()) {
                  FileUtils.deleteDirectory(ThreadModuleDump48.field25);
                  FileUtils.copyDirectory(ThreadModuleDump48.field26, ThreadModuleDump48.field25);
               } else {
                  FileUtils.deleteDirectory(ThreadModuleDump48.field26);
                  FileUtils.copyDirectory(ThreadModuleDump48.field25, ThreadModuleDump48.field26);
               }
            }

            var1.delete();
            if (var1.createNewFile()) {
               try (OutputStreamWriter var23 = new OutputStreamWriter(new FileOutputStream(var1), StandardCharsets.UTF_8)) {
                  var23.write(String.valueOf(ConfigMigrator.field2));
               } catch (IOException var12) {
                  var12.printStackTrace();
               }
            }
         } catch (Exception var14) {
            var14.printStackTrace();
         }
      }
   }

   private com.lunarclient.websocket.handshake.v1.Handshake.Builder method5() {
      Set var1 = IchorAPI.getPipeline(Client.class.getClassLoader()).map(var0 -> var0.method31().keySet()).orElse(Set.of());
      return Handshake.newBuilder()
         .setLauncherVersion(LauncherVersion.newBuilder().setSemver(ThreadModuleDump80.field7).build())
         .setInstallationId(ThreadModuleDump80.field1)
         .setOverwolfMuid(ThreadModuleDump80.field2)
         .setCanaryToken(ThreadModuleDump80.field5)
         .setOperatingSystem(ThreadModuleDump48.field1)
         .setOperatingSystemRelease(ThreadModuleDump48.field2)
         .setCpuArchitecture(ThreadModuleDump48.field3)
         .setLanguage(Language.newBuilder().setMinecraftCode(ThreadModuleDump80.field12).build())
         .setGameHandshake(
            GameHandshake.newBuilder()
               .setMinecraftVersion(MinecraftVersion.newBuilder().setEnum(Bridge.getMinecraftVersion().getId()).build())
               .setLunarClientVersion(
                  LunarClientVersion.newBuilder()
                     .setGitBranch(com.moonsworth.lunar.client.framework.build.LunarBuildData.field1)
                     .setGitCommit(com.moonsworth.lunar.client.framework.build.LunarBuildData.field3)
                     .setSemver(method19())
                     .build()
               )
               .setLocation(EntityRenderer4.method80())
               .addAllIchorModules(var1)
               .addAllInstalledMods(IchorAPI.getPipeline(Client.class).orElseThrow().method18().map(Client::method12).collect(Collectors.toList()))
               .setModpack(
                  PlayerModpack.newBuilder()
                     .setModrinthId(ThreadModuleDump80.field8)
                     .setModrinthVersionId(ThreadModuleDump80.field9)
                     .setCurseforgeId(ThreadModuleDump80.field10)
                     .setCurseforgeFileId(ThreadModuleDump80.field11)
                     .build()
               )
               .addAllGlExtensions(Bridge.method22().method26())
               .setLaunchId(ThreadModuleDump80.field4)
               .setLunarClientUiVersion(
                  LunarClientUiVersion.newBuilder()
                     .setGitBranch(com.moonsworth.lunar.client.framework.build.LunarBuildData.field7)
                     .setGitCommit(
                        com.moonsworth.lunar.client.framework.build.LunarBuildData.field8 == null ? "latest" : com.moonsworth.lunar.client.framework.build.LunarBuildData.field8
                     )
                     .build()
               )
               .build()
         );
   }

   public void method6(String var1, Consumer<String> var2) {
      Horsestats var3 = ThreadModuleDump63.method3().bridge$getSession();
      AccountSession var4 = ThreadModuleDump63.method4().method43().method10();
      if (var4 == null) {
         Slayer.method4("Auth", "Failed to establish connection with the auth server, account null.");
         var2.accept(null);
      } else if (!var4.method3()) {
         Slayer.method4("Auth", "Failed to establish connection with the auth server, access token invalid.");
         var2.accept(null);
      } else {
         UuidAndUsername var5 = ThreadModuleDump66.method7(UUID.fromString(ThreadModuleDump36.method1(var3.bridge$getPlayerID())), var3.bridge$getUsername());
         new WebSocketClientHandler(var5, var1, var2).connect();
      }
   }

   public void method7(@Nullable EntityRenderer3 var1) {
      com.lunarclient.websocket.handshake.v1.Handshake.Builder var2 = this.method5();
      new Thread(() -> {
         Slayer.method4("Assets", "Establishing connection");
         if (this.field7 != null && this.field7.isOpen()) {
            try {
               this.field7.closeBlocking();
            } catch (InterruptedException var4) {
            }
         }

         if (var1 != null) {
            Optional var3 = var1.method2();
            if (var3.isPresent()) {
               Slayer.method4("Assets", "Skipping Authenticator call, using reconnect JWT instead.");
               this.method11((String)var3.get(), var2);
            } else {
               Slayer.method4("Assets", "Connecting to Authenticator to obtain JWT, no reconnect JWT offered.");
               this.method10(var2);
            }
         } else {
            Slayer.method4("Assets", "Connecting to Authenticator to obtain JWT, no previous connection.");
            this.method10(var2);
         }
      }).start();
   }

   public void method8() {
      new Thread(
            () -> {
               try {
                  if (this.field8 != null && this.field8.isOpen()) {
                     this.field8.closeBlocking();
                  }

                  com.lunarclient.gameipc.handshake.v1.Handshake var1 = this.method9();
                  if (var1 == null) {
                     Slayer.method4("IPC", "Unable to construct IPC handshake.");
                     return;
                  }

                  this.field8 = new WebSocketClientIterator(var1);
                  this.field8.connect();
                  ThreadModuleDump63.method3()
                     .bridge$submit(
                        () -> ClientEventBus.method29()
                           .method12(
                              com.moonsworth.lunar.client.event.mixin.holograms.EventWebSocketReadyLegacy.class,
                              () -> new com.moonsworth.lunar.client.event.mixin.holograms.EventWebSocketReadyLegacy(this.field8)
                           )
                     );
               } catch (InterruptedException var2) {
                  var2.printStackTrace();
               }
            }
         )
         .start();
   }

   @Nullable
   private Handshake method9() {
      String var1 = ThreadModuleDump80.field4;
      String var2 = ThreadModuleDump80.field1;
      label26:
      if (!var1.equalsIgnoreCase("not supplied")) {
         com.lunarclient.gameipc.handshake.v1.Handshake.Builder var3 = com.lunarclient.gameipc.handshake.v1.Handshake.newBuilder()
            .setLaunchId(var1)
            .setInstallationId(var2);

         try {
            long var4 = ProcessHandle.current().pid();
            var3.setProcessId(Long.toString(var4));
         } catch (Exception var6) {
         }

         return var3.build();
      } else {
         if (com.moonsworth.lunar.client.framework.build.LunarBuildData.field4) {
            return null;
         }

         var1 = "00000000-0000-0000-0000-000000000000";

         try {
            var2 = Files.readString(ThreadModuleDump48.field8.resolve("installation-id"));
            break label26;
         } catch (IOException var7) {
            var7.printStackTrace();
            return null;
         }
      }
   }

   private void method10(com.lunarclient.websocket.handshake.v1.Handshake.Builder var1) {
      this.method6("GAME_WEBSOCKET", var2 -> {
         if (var2 != null) {
            this.method11(var2, var1);
         }
      });
   }

   private void method11(String var1, com.lunarclient.websocket.handshake.v1.Handshake.Builder var2) {
      Horsestats var3 = ThreadModuleDump63.method3().bridge$getSession();
      AccountSession var4 = ThreadModuleDump63.method4().method43().method10();
      if (!com.moonsworth.lunar.client.framework.build.LunarBuildData.field4) {
         ThreadModuleDump80.field7 = "gradle";
      }

      this.field7 = new EntityRenderer4(
         var2.setIdentity(
               MinecraftIdentity.newBuilder()
                  .setPlayer(ThreadModuleDump66.method7(UUID.fromString(ThreadModuleDump36.method1(var3.bridge$getPlayerID())), var3.bridge$getUsername()))
                  .setType(var4.method12().getProtobufType())
                  .setAuthenticatorJwt(var1)
                  .build()
            )
            .build()
      );
      this.field7.connect();
      Inventorymod2.method1(Breadcrumb.debug("Connected to the AssetServer"));
   }

   @NotNull
   private static InstalledMod method12(Ichor5Handler_2 var0) {
      com.lunarclient.websocket.handshake.v1.InstalledMod.Builder var1 = InstalledMod.newBuilder()
         .setId(var0.getId())
         .setName(var0.getId())
         .setType(Bridge.getMinecraftVersion().method19() ? (var0.method5() ? Type.TYPE_FABRIC_INTERNAL : Type.TYPE_FABRIC_EXTERNAL) : Type.TYPE_FORGE_INTERNAL);
      String var2 = var0.getVersion();
      if (var2 == null) {
         var1.clearVersion();
      } else {
         var1.setVersion(var2);
      }

      return var1.build();
   }

   public boolean method13() {
      return this.field3 == null;
   }

   public void method14(Horsestats var1) {
      try {
         Horsestats var2 = ThreadModuleDump63.method3().bridge$getSession();
         if (this.field3 != null
            && Objects.equals(this.field3.method10(), var2.bridge$getProfile().getId())
            && Objects.equals(this.field3.getName(), var2.bridge$getUsername())) {
            return;
         }

         boolean var3 = var1 != null && var1.bridge$getProfile() != null && var1.bridge$getProfile().getId() != null;
         boolean var4 = var3 && !var1.bridge$getProfile().getId().equals(var2.bridge$getProfile().getId());
         if (var3 && var4) {
            this.field25.method21();
            this.field27.method5();
         }

         this.field3 = new Memory(var2.bridge$getProfile().getId(), var2.bridge$getUsername());
         this.field3.method4(EntityRendererType.ONLINE);
         ThreadModuleDump63.method3().bridge$submit(() -> this.method7(null));
      } catch (Exception var5) {
         var5.printStackTrace();
      }
   }

   public void method15(Bridge5Extension6 var1) {
      if (var1 == null) {
         Sentry.clearBreadcrumbs();
      } else {
         String var2 = var1.getClass().getName();
         if (var1 instanceof Bridge5Extension62) {
            if (((Bridge5Extension62)var1).method2() instanceof BugReportScreen) {
               return;
            }

            var2 = ((Bridge5Extension62)var1).method2().getClass().getName();
         }

         String var3 = var1.CHRHORHCCHCCCROOICRIOOICCOHIRO().orElse(var2);
         if (!"/".equals(var3)) {
            Breadcrumb var4 = new Breadcrumb();
            var4.setLevel(SentryLevel.DEBUG);
            var4.setType("debug");
            var4.setCategory("screen.open");
            var4.setMessage("User opened " + var3);
            Sentry.addBreadcrumb(var4);
         }
      }
   }

   public boolean method16(String var1) {
      return this.world.isEmpty() || var1.isEmpty() || var1.equals(this.world);
   }

   public static String method17() {
      return "Lunar Client " + method18();
   }

   public static String method18() {
      String var0 = Bridge.getMinecraftVersion().getDisplayName();
      return var0 + " (" + method19() + ")";
   }

   public static String method19() {
      return method20(true);
   }

   public static String method20(boolean var0) {
      return ThreadModuleDump48.field28
         ? (var0 ? "v" : "") + com.moonsworth.lunar.client.framework.build.LunarBuildData.field6
         : com.moonsworth.lunar.client.framework.build.LunarBuildData.field2 + "/" + com.moonsworth.lunar.client.framework.build.LunarBuildData.field1;
   }

   public static String getClientBrand() {
      return "lunarclient:" + method19();
   }

   public static int method21() {
      return 64;
   }

   public boolean method22() {
      return ThreadModuleDump63.method8() == null;
   }

   public void method23() {
      this.method24(false);
   }

   public void method24(boolean var1) {
      if (this.method25() && !(ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension610)) {
         ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method19());
      } else {
         com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50()
            .method18(DriverRouteRegistryLegacy.field4, var1 || !ThreadModuleDump63.method2() || ThreadModuleDump63.method3().bridge$getCurrentScreen() == null);
      }
   }

   public boolean method25() {
      return this.field13 != null && this.field13.method6().method65().get() == GeneralSettings.Type3.VANILLA;
   }

   public static Optional<File> method26() {
      return Optional.ofNullable(ThreadModuleDump80.field15);
   }

   public static Optional<File> method27() {
      return Optional.ofNullable(ThreadModuleDump80.field17);
   }

   public static Optional<File> method28() {
      return Optional.ofNullable(ThreadModuleDump80.field18);
   }

   public static Optional<File> method29() {
      return Optional.ofNullable(ThreadModuleDump80.field16);
   }

   public static void method30(Client var0) {
      if (field1 != null) {
         throw new IllegalStateException("Lunar is already initialized");
      }

      field1 = var0;
   }

   @Generated
   public Memory method31() {
      return this.field3;
   }

   @Generated
   public Highlight3Handler method32() {
      return this.field4;
   }

   @Generated
   public com.moonsworth.lunar.client.keystrokes.Highlight3Iterator method33() {
      return this.field5;
   }

   @Generated
   public StreamerMode method34() {
      return this.field6;
   }

   @Generated
   public EntityRenderer4 method35() {
      return this.field7;
   }

   @Generated
   public WebSocketClientIterator method36() {
      return this.field8;
   }

   @Generated
   public boolean method37() {
      return this.field9;
   }

   @Generated
   public String getWorld() {
      return this.world;
   }

   @Generated
   public com.moonsworth.lunar.client.framework.metadata.ModMetadataFetcher method38() {
      return this.field10;
   }

   @Generated
   public Set<LoadableHandler> method39() {
      return this.field11;
   }

   @Generated
   public ModsSettings method40() {
      return this.field12;
   }

   @Generated
   public SettingsManager method41() {
      return this.field13;
   }

   @Generated
   public GlobalOptionsSettings method42() {
      return this.field14;
   }

   @Generated
   public AccountManager method43() {
      return this.field15;
   }

   @Generated
   public StaffModsSettings method44() {
      return this.field16;
   }

   @Generated
   public EmoteManager method45() {
      return this.field17;
   }

   @Generated
   public SprayManager method46() {
      return this.field18;
   }

   @Generated
   public FogHandler method47() {
      return this.field19;
   }

   @Generated
   public WaypointStore method48() {
      return this.field20;
   }

   @Generated
   public FavoriteColorsConfigRescued method49() {
      return this.field21;
   }

   @Generated
   public FriendRequestManager method50() {
      return this.field22;
   }

   @Generated
   public ItemSetRegistry method51() {
      return this.field23;
   }

   @Generated
   public ConversationManager method52() {
      return this.field24;
   }

   @Generated
   public CosmeticManager method53() {
      return this.field25;
   }

   @Generated
   public LunarPlusManager method54() {
      return this.field26;
   }

   @Generated
   public OutfitManager method55() {
      return this.field27;
   }

   @Generated
   public CosmeticPreviewManager method56() {
      return this.field28;
   }

   @Generated
   public ServerHologramStore method57() {
      return this.field29;
   }

   @Generated
   public com.moonsworth.lunar.client.mod.render.NametagOverrideStore method58() {
      return this.field30;
   }

   @Generated
   public PinnedServerManager method59() {
      return this.field31;
   }

   @Generated
   public TeamMemberManager method60() {
      return this.field32;
   }

   @Generated
   public com.moonsworth.lunar.client.config.profile.ModProfileManager method61() {
      return this.field33;
   }

   @Generated
   public ServerFeaturesSettings method62() {
      return this.field34;
   }

   @Generated
   public ServerBorderRegistry method63() {
      return this.field35;
   }

   @Generated
   public LinkRegistry method64() {
      return this.field36;
   }

   @Generated
   public BlogPostManager method65() {
      return this.field37;
   }

   @Generated
   public DismissedAlertStore method66() {
      return this.field38;
   }

   @Generated
   public TranslationManager method67() {
      return this.field39;
   }

   @Generated
   public com.moonsworth.lunar.client.ui.ScreenStack method68() {
      return this.field40;
   }

   @Generated
   public com.moonsworth.lunar.client.gui.notification.NotificationManager method69() {
      return this.field41;
   }

   @Generated
   public MorphManager method70() {
      return this.field42;
   }

   @Generated
   public ProfanityFilter method71() {
      return this.field43;
   }

   @Generated
   public com.moonsworth.lunar.client.framework.metadata.RemoteMetadataManager method72() {
      return this.field44;
   }

   @Generated
   public JamManager method73() {
      return this.field45;
   }

   @Generated
   public GuiRewindhandlers5 method74() {
      return this.field46;
   }

   @Generated
   public SavedSkinManager method75() {
      return this.field47;
   }

   @Generated
   public com.moonsworth.lunar.client.cosmetics.gecko.MolangFunctionRegistry method76() {
      return this.field48;
   }

   @Generated
   public CosmeticCompatibility method77() {
      return this.field49;
   }

   @Generated
   public ServerIconManager method78() {
      return this.field50;
   }

   @Generated
   public ServerDiscoveryManager method79() {
      return this.field51;
   }

   @Generated
   public com.moonsworth.lunar.client.network.server.PingEntryConfig method80() {
      return this.field52;
   }

   @Generated
   public com.moonsworth.lunar.client.coordinates.FogIterator method81() {
      return this.field53;
   }

   @Generated
   public PerformanceReporter method82() {
      return this.field54;
   }

   @Generated
   public LiveExperienceManager method83() {
      return this.field55;
   }

   @Generated
   public ApolloModuleManager method84() {
      return this.field56;
   }

   @Generated
   public com.moonsworth.lunar.client.render.texture.VanillaTextureIndex method85() {
      return this.field57;
   }

   @Generated
   public AnalyticsCounters method86() {
      return this.field58;
   }

   @Generated
   public EventChestRegistry method87() {
      return this.field59;
   }

   @Generated
   public TurboEntityManager method88() {
      return this.field60;
   }

   @Generated
   public TurboEngineManager method89() {
      return this.field61;
   }

   @Generated
   public LoadingStageImpl method90() {
      return this.field62;
   }

   @Generated
   public TabLogoManager method91() {
      return this.field63;
   }

   @Generated
   public ActivePrompt method92() {
      return this.field64;
   }

   @Generated
   public Itemcounter method93() {
      return this.field65;
   }

   @Generated
   public VanillaCapeManager method94() {
      return this.field66;
   }

   @Generated
   public BadgeManager method95() {
      return this.field67;
   }

   @Generated
   public com.moonsworth.lunar.client.render.jit.JitAssetIndex method96() {
      return this.field68;
   }

   @Generated
   public PromotionMessageHandler method97() {
      return this.field69;
   }

   @Generated
   public TurboPipelineHook method98() {
      return this.field70;
   }

   @Generated
   public com.moonsworth.lunar.client.render.shader.ShaderInjectRegistry method99() {
      return this.field71;
   }

   @Generated
   public UiStateStore method100() {
      return this.field72;
   }

   @Generated
   public SocialLinksManager method101() {
      return this.field73;
   }

   @Generated
   public com.moonsworth.lunar.client.render.shader.DevShaderEditor method102() {
      return this.field74;
   }

   @Generated
   public com.moonsworth.lunar.client.tps.FogIterator method103() {
      return this.field75;
   }

   @Generated
   public ServerPinger method104() {
      return this.field76;
   }

   @Generated
   public AnalyticsBatcher method105() {
      return this.field77;
   }

   @Generated
   public Optional<Nickhider2> method106() {
      return this.field78;
   }

   @Generated
   public GuiIterator method107() {
      return this.field79;
   }

   @Nullable
   @Generated
   public Map<String, Boolean> method108() {
      return this.field80;
   }

   @Generated
   public static Client method109() {
      return field1;
   }

   @Generated
   public void setWorld(String var1) {
      this.world = var1;
   }

   @Generated
   public void method111(Optional<Nickhider2> var1) {
      this.field78 = var1;
   }
}
