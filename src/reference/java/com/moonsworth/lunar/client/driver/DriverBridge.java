package com.moonsworth.lunar.client.driver;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuButton;
import com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod.ShaderDebugJsBridge;
import com.moonsworth.lunar.client.replay.gui.InputTimelineJsApi;
import com.moonsworth.lunar.client.replay.gui.EffectsJsApi;
import com.moonsworth.lunar.client.replay.export.ExportSettingsJsApi;
import com.moonsworth.lunar.client.replay.gui.RewindFileCallbacks;
import com.moonsworth.lunar.client.replay.gui.RewindTimelineBridge;
import com.moonsworth.lunar.client.replay.gui.RewindTimelinesListBridge;
import com.moonsworth.lunar.client.replay.gui.RewindPropertiesBridge;
import com.moonsworth.lunar.client.replay.gui.CreateProjectBridge;
import com.moonsworth.lunar.client.replay.export.MediaExporterJsApi;
import com.moonsworth.lunar.client.replay.gui.RewindEditorBridge;
import com.moonsworth.lunar.client.replay.gui.RewindInteractionJsApi;
import com.moonsworth.lunar.client.driver.bridge.ModsBridge;
import com.moonsworth.lunar.client.driver.core.gui.mixin.CosmeticsBridge;
import com.moonsworth.lunar.client.driver.bridge.PromotionBridge;
import com.moonsworth.lunar.client.driver.core.gui.mixin.SpraysBridge;
import com.moonsworth.lunar.client.driver.core.gui.mixin.EmotesBridge;
import com.moonsworth.lunar.client.driver.bridge.HostedWorldBridge;
import com.moonsworth.lunar.client.driver.bridge.LanguagePageBridge;
import com.moonsworth.lunar.client.driver.bridge.CosmeticPreviewBridge;
import com.moonsworth.lunar.client.driver.bridge.SavedSkinBridge;
import com.moonsworth.lunar.client.driver.bridge.AlertBridge;
import com.moonsworth.lunar.client.driver.bridge.AccountBridge;
import com.moonsworth.lunar.client.driver.bridge.StyngrBridge;
import com.moonsworth.lunar.client.driver.core.gui.mixin.BlogPostsBridge;
import com.moonsworth.lunar.client.driver.bridge.SocialMediaBridge;
import com.moonsworth.lunar.client.driver.bridge.UiStateBridge;
import com.moonsworth.lunar.client.driver.bridge.SkyblockBridge;
import com.moonsworth.lunar.client.driver.bridge.OutfitBridge;
import com.moonsworth.lunar.client.driver.bridge.BadgeBridge;
import com.moonsworth.lunar.client.driver.bridge.ColorBridge;
import com.moonsworth.lunar.client.driver.bridge.LunarPlusBridge;
import com.moonsworth.lunar.client.driver.bridge.SettingsBridge;
import com.moonsworth.lunar.client.driver.bridge.HomeRadioBridge;
import com.moonsworth.lunar.client.driver.bridge.HomeThemeBridge;
import com.moonsworth.lunar.client.driver.bridge.HomeNavigationBridge;
import com.moonsworth.lunar.client.driver.bridge.ProfileBridge;
import com.moonsworth.lunar.client.driver.bridge.ServerDiscoveryBridge;
import com.moonsworth.lunar.client.driver.bridge.MetadataBridge;
import com.moonsworth.lunar.client.driver.bridge.NotificationBridge;
import com.moonsworth.lunar.client.driver.bridge.PlayerBridge;
import com.moonsworth.lunar.client.driver.bridge.ServerPingBridge;
import com.moonsworth.lunar.client.driver.bridge.HomeButtonBridge;
import com.moonsworth.lunar.client.driver.bridge.FriendsGuiExtension;
import com.moonsworth.lunar.client.driver.bridge.FriendApi;
import com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers.GuiExtension2;
import com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.util.net.BrowserUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ClipboardUtils;
import com.moonsworth.lunar.files.ValuePair;
import com.moonsworth.webosr.javascript.CallbackJS;
import com.moonsworth.webosr.wrappers.Browser;
import com.moonsworth.webosr.wrappers.PromiseJS;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import lombok.Generated;

public class DriverBridge {
   private final com.moonsworth.lunar.client.driver.core.DriverViewportLegacy field1;
   private final Map<String, ValuePair<Class<?>, Function<String, DriverGuiExtension>>> field2;

   public DriverBridge(com.moonsworth.lunar.client.driver.core.DriverViewportLegacy highlight3iterator1) {
      this.field1 = highlight3iterator1;
      this.field2 = this.method1();
   }

   private Map<String, ValuePair<Class<?>, Function<String, DriverGuiExtension>>> method1() {
      return ImmutableMap.builder()
         .put("notification", ValuePair.method1(NotificationBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new NotificationBridge()))
         .put("language", ValuePair.method1(LanguagePageBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new LanguagePageBridge()))
         .put("promotion", ValuePair.method1(PromotionBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new PromotionBridge()))
         .put(
            "analytics",
            ValuePair.method1(
               com.moonsworth.lunar.client.driver.bridge.AnalyticsDataProvider.class,
               (Function<String, DriverGuiExtension>)arg0 -> new com.moonsworth.lunar.client.driver.bridge.AnalyticsDataProvider()
            )
         )
         .put("metadata", ValuePair.method1(MetadataBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new MetadataBridge()))
         .put("accounts", ValuePair.method1(AccountBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new AccountBridge()))
         .put("alerts", ValuePair.method1(AlertBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new AlertBridge()))
         .put("blogPosts", ValuePair.method1(BlogPostsBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new BlogPostsBridge()))
         .put("homeButtons", ValuePair.method1(HomeButtonBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new HomeButtonBridge()))
         .put("homeNavigation", ValuePair.method1(HomeNavigationBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new HomeNavigationBridge()))
         .put("savedSkins", ValuePair.method1(SavedSkinBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new SavedSkinBridge()))
         .put("socialMedia", ValuePair.method1(SocialMediaBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new SocialMediaBridge()))
         .put("homeTheme", ValuePair.method1(HomeThemeBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new HomeThemeBridge()))
         .put("homeRadio", ValuePair.method1(HomeRadioBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new HomeRadioBridge()))
         .put("serverDiscovery", ValuePair.method1(ServerDiscoveryBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new ServerDiscoveryBridge()))
         .put("serverPing", ValuePair.method1(ServerPingBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new ServerPingBridge()))
         .put("profiles", ValuePair.method1(ProfileBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new ProfileBridge()))
         .put("profileImport", ValuePair.method1(ProfileImportProvider.class, (Function<String, DriverGuiExtension>)arg0 -> new ProfileImportProvider()))
         .put("settings", ValuePair.method1(SettingsBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new SettingsBridge()))
         .put("cosmetics", ValuePair.method1(CosmeticsBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new CosmeticsBridge()))
         .put("outfits", ValuePair.method1(OutfitBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new OutfitBridge()))
         .put("lunarPlus", ValuePair.method1(LunarPlusBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new LunarPlusBridge()))
         .put("emotes", ValuePair.method1(EmotesBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new EmotesBridge()))
         .put("badges", ValuePair.method1(BadgeBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new BadgeBridge()))
         .put("styngr", ValuePair.method1(StyngrBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new StyngrBridge()))
         .put("colors", ValuePair.method1(ColorBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new ColorBridge()))
         .put("sprays", ValuePair.method1(SpraysBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new SpraysBridge()))
         .put("waypoints", ValuePair.method1(Rewindhandlers.class, (Function<String, DriverGuiExtension>)arg0 -> new Rewindhandlers()))
         .put(
            "waypointMap",
            ValuePair.method1(
               com.moonsworth.lunar.client.driver.waypoint.MinimapJsApi.class,
               (Function<String, DriverGuiExtension>)arg0 -> new com.moonsworth.lunar.client.driver.waypoint.MinimapJsApi()
            )
         )
         .put("waypointImportExport", ValuePair.method1(GuiExtension2.class, (Function<String, DriverGuiExtension>)arg0 -> new GuiExtension2()))
         .put(
            "waypointGroup",
            ValuePair.method1(
               com.moonsworth.lunar.client.driver.waypoint.WaypointGroupBridge.class,
               (Function<String, DriverGuiExtension>)arg0 -> new com.moonsworth.lunar.client.driver.waypoint.WaypointGroupBridge()
            )
         )
         .put(
            "waypointSort",
            ValuePair.method1(
               com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers.GuiExtension4.class,
               (Function<String, DriverGuiExtension>)arg0 -> new com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers.GuiExtension4()
            )
         )
         .put(
            "waypointUtility",
            ValuePair.method1(
               com.moonsworth.lunar.client.driver.waypoint.WaypointJsApi.class,
               (Function<String, DriverGuiExtension>)arg0 -> new com.moonsworth.lunar.client.driver.waypoint.WaypointJsApi()
            )
         )
         .put(
            "waypointModImport",
            ValuePair.method1(
               com.moonsworth.lunar.client.driver.waypoint.WaypointImportJsApi.class,
               (Function<String, DriverGuiExtension>)arg0 -> new com.moonsworth.lunar.client.driver.waypoint.WaypointImportJsApi()
            )
         )
         .put("mods", ValuePair.method1(ModsBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new ModsBridge()))
         .put(
            "hudModProperty",
            ValuePair.method1(com.moonsworth.lunar.client.driver.bridge.FeatureDataProvider.class, com.moonsworth.lunar.client.driver.bridge.FeatureDataProvider::new)
         )
         .put(
            "hudModMetadata",
            ValuePair.method1(com.moonsworth.lunar.client.driver.bridge.MarkersDataProvider.class, com.moonsworth.lunar.client.driver.bridge.MarkersDataProvider::new)
         )
         .put(
            "spiritLeap",
            ValuePair.method1(
               com.moonsworth.lunar.client.driver.bridge.SpiritLeapMapBridge.class,
               (Function<String, DriverGuiExtension>)arg0 -> new com.moonsworth.lunar.client.driver.bridge.SpiritLeapMapBridge()
            )
         )
         .put("skyblock", ValuePair.method1(SkyblockBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new SkyblockBridge()))
         .put(
            "markers",
            ValuePair.method1(
               com.moonsworth.lunar.client.driver.bridge.MarkersBridge.class,
               (Function<String, DriverGuiExtension>)arg0 -> new com.moonsworth.lunar.client.driver.bridge.MarkersBridge()
            )
         )
         .put(
            "screenshotUpload",
            ValuePair.method1(
               com.moonsworth.lunar.client.driver.bridge.ScreenshotUploadBridge.class,
               (Function<String, DriverGuiExtension>)arg0 -> new com.moonsworth.lunar.client.driver.bridge.ScreenshotUploadBridge()
            )
         )
         .put("shaderEditor", ValuePair.method1(ShaderDebugJsBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new ShaderDebugJsBridge()))
         .put("assetsClient", ValuePair.method1(FriendsGuiExtension.class, (Function<String, DriverGuiExtension>)arg0 -> new FriendsGuiExtension()))
         .put("friends", ValuePair.method1(FriendApi.class, (Function<String, DriverGuiExtension>)arg0 -> new FriendApi()))
         .put(
            "conversations",
            ValuePair.method1(
               com.moonsworth.lunar.client.driver.core.gui.mixin.nameplate.GuiExtension.class,
               (Function<String, DriverGuiExtension>)arg0 -> new com.moonsworth.lunar.client.driver.core.gui.mixin.nameplate.GuiExtension()
            )
         )
         .put("hostedWorlds", ValuePair.method1(HostedWorldBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new HostedWorldBridge()))
         .put("rewind", ValuePair.method1(RewindEditorBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new RewindEditorBridge()))
         .put("rewindTimeline", ValuePair.method1(RewindTimelineBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new RewindTimelineBridge()))
         .put("rewindProperties", ValuePair.method1(RewindPropertiesBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new RewindPropertiesBridge()))
         .put("rewindMedia", ValuePair.method1(MediaExporterJsApi.class, (Function<String, DriverGuiExtension>)arg0 -> new MediaExporterJsApi()))
         .put("rewindTimelinesList", ValuePair.method1(RewindTimelinesListBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new RewindTimelinesListBridge()))
         .put("rewindExport", ValuePair.method1(ExportSettingsJsApi.class, (Function<String, DriverGuiExtension>)arg0 -> new ExportSettingsJsApi()))
         .put("rewindEffects", ValuePair.method1(EffectsJsApi.class, (Function<String, DriverGuiExtension>)arg0 -> new EffectsJsApi()))
         .put("rewindsList", ValuePair.method1(RewindFileCallbacks.class, (Function<String, DriverGuiExtension>)arg0 -> new RewindFileCallbacks()))
         .put("rewindCreateProject", ValuePair.method1(CreateProjectBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new CreateProjectBridge()))
         .put(
            "rewindPacks",
            ValuePair.method1(
               com.moonsworth.lunar.client.replay.gui.RewindPackListProvider.class,
               (Function<String, DriverGuiExtension>)arg0 -> new com.moonsworth.lunar.client.replay.gui.RewindPackListProvider()
            )
         )
         .put("rewindInteraction", ValuePair.method1(RewindInteractionJsApi.class, (Function<String, DriverGuiExtension>)arg0 -> new RewindInteractionJsApi()))
         .put("rewindDev", ValuePair.method1(InputTimelineJsApi.class, (Function<String, DriverGuiExtension>)arg0 -> new InputTimelineJsApi()))
         .put("player", ValuePair.method1(PlayerBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new PlayerBridge()))
         .put("uiState", ValuePair.method1(UiStateBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new UiStateBridge()))
         .put("cosmeticPreview", ValuePair.method1(CosmeticPreviewBridge.class, (Function<String, DriverGuiExtension>)arg0 -> new CosmeticPreviewBridge()))
         .build();
   }

   public void method2(Bridge7_8 bridge7_81) {
      Object obj2;
      if ((Ref.method8() == null || this.field1.method63() == DriverRouteRegistry.field4) && !(bridge7_81 instanceof MainMenuButton)) {
         obj2 = new MainMenuButton(Bridge.method8().method18(bridge7_81));
      } else {
         obj2 = bridge7_81;
      }

      if (bridge7_81 instanceof MainMenuButton bridge7task3) {
         Bridge7_8 bridge7_84 = Ref.method31(bridge7task3.method17());
         Bridge7_8 bridge7_85 = Ref.method31(Ref.method3().bridge$getCurrentScreen());
         if (bridge7_85 != null && bridge7_84.getClass() == bridge7_85.getClass()) {
            return;
         }

         this.field1.method17(DriverRouteRegistry.field5, new VanillaHomeContext(bridge7task3.method4()));
      } else {
         this.field1.method16(DriverRouteRegistry.field3);
      }

      LcuiScreen.method15();
      this.field1.method75().add(() -> Ref.method3().bridge$displayScreen(Bridge.method8().method18(obj2)));
   }

   @CallbackJS("setScreen")
   public void method3(DriverScreen markerstype_31) {
      if (markerstype_31.isRequiresAuthentication() && !Ref.method4().method43().method23()) {
         AccountBridge.method2();
      } else if (markerstype_31 == DriverScreen.NULL
         && com.moonsworth.lunar.client.driver.core.DualMarkerScreenLegacy.method13(Ref.method11())
         && Ref.method8() == null) {
         Client.method109().method23();
      } else {
         LcuiScreen.method15();
         this.field1.method16(DriverRouteRegistry.field3);
         this.field1.method75().add(() -> Ref.method3().bridge$displayScreen(markerstype_31.getInitScreen().apply(null)));
      }
   }

   @CallbackJS("requestPayload")
   public String method4(String text1, String text2) {
      ValuePair files6_23 = this.field2.getOrDefault(text1, null);
      if (files6_23 != null) {
         String text4 = text2.isEmpty() ? "" : "-" + text2;
         String text5 = text1 + text4;
         DriverGuiExtension guiextension_26 = (DriverGuiExtension)((Function)files6_23.field2).apply(text2);
         JsonElement element7 = guiextension_26.provide();
         this.field1.method60().put(text5, element7);
         com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method59().put(text5, guiextension_26);
         if (com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.isDebug()) {
            LunarLogger.method4("WebOSR", "Tracking provider: %s", new Object[]{text5});
         }

         return LunarConstants.field22.toJson(element7);
      } else {
         return null;
      }
   }

   @CallbackJS("unregisterPayload")
   public void method5(String text1, String text2) {
      String text3 = text2.isEmpty() ? "" : "-" + text2;
      String text4 = text1 + text3;
      this.field1.method60().remove(text4);
      com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method59().remove(text4);
      if (com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.isDebug()) {
         LunarLogger.method4("WebOSR", "Stop tracking provider: %s", new Object[]{text4});
      }
   }

   @CallbackJS("setActiveRoute")
   public void method6(String text1) {
      this.field1.method27(this.field1.method61());

      while (!this.field1.method75().isEmpty()) {
         this.field1.method75().poll().run();
      }

      Optional optional2 = DriverRouteRegistry.method3().stream().filter(arg1x -> arg1x.getPath().equals(text1.split("(?=/)")[0])).findAny();
      if (optional2.isPresent()) {
         this.field1.method27((DriverRouteRegistry)optional2.get());
      } else {
         LunarLogger.method6("WebOSR", "No route found for %s.", new Object[]{text1});
      }

      this.field1.method12();
   }

   @CallbackJS("setActiveOverlay")
   public void method7(String text1) {
      Optional optional2 = DriverOverlayRegistry.method3().stream().filter(arg1x -> arg1x.getId().equals(text1)).findAny();
      if (optional2.isPresent()) {
         this.field1.method19((DriverOverlayRegistry)optional2.get());
      } else {
         LunarLogger.method6("WebOSR", "No overlay found for %s.", new Object[]{text1});
      }
   }

   @CallbackJS("hideOverlay")
   public void method8() {
      this.field1.method21();
   }

   @CallbackJS("invoke")
   public void method9(PromiseJS<String> promisejs1, Browser browser2, String[] items3) {
      this.field1.method39().invoke(promisejs1, browser2, items3[0], Arrays.copyOfRange(items3, 1, items3.length));
   }

   @CallbackJS("onClick")
   public void method10(String text1) {
      for (DriverGuiExtension guiextension_23 : com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method59().values()) {
         guiextension_23.method1(text1);
      }
   }

   @CallbackJS("openUri")
   public void method11(String text1, Initiator initiator2) {
      if (!BrowserUtils.method7(text1, initiator2)) {
      }
   }

   @CallbackJS("copyToClipboard")
   public void method12(String text1) {
      ClipboardUtils.method2(text1);
      Ref.method4().method69().method3("Copied to clipboard!");
   }

   @CallbackJS("getClipboardString")
   public String method13() {
      return ClipboardUtils.method1();
   }

   @CallbackJS("setAllowKeybinds")
   public void method14(Boolean flag1) {
      this.field1.method68(flag1);
   }

   @CallbackJS("setToastsVisible")
   public void method15(Boolean flag1) {
      this.field1.method72(flag1);
   }

   @CallbackJS("setPickingColor")
   public void method16(Boolean flag1) {
      this.field1.method70(flag1);
   }

   @CallbackJS("playClickSound")
   public void method17() {
      LcuiScreen.method15();
   }

   @Generated
   public com.moonsworth.lunar.client.driver.core.DriverViewportLegacy method18() {
      return this.field1;
   }

   @Generated
   public Map<String, ValuePair<Class<?>, Function<String, DriverGuiExtension>>> method19() {
      return this.field2;
   }
}
