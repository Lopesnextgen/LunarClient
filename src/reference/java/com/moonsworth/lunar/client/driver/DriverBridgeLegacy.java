package com.moonsworth.lunar.client.driver;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuButton;
import com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod.Shaderdebugmod5;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers2_2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers2_3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers2_4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers_6;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.chest.Chest2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.click.Click2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.colorsaturation.Colorsaturation2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.fishing.GuiIterator;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.highlight.Highlight2_2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.holograms.Holograms2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate2_2;
import com.moonsworth.lunar.client.driver.gui.Gui;
import com.moonsworth.lunar.client.driver.core.gui.mixin.CosmeticsBridge;
import com.moonsworth.lunar.client.driver.core.gui.mixin.PromotionBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.SpraysBridge;
import com.moonsworth.lunar.client.driver.core.gui.mixin.EmotesBridge;
import com.moonsworth.lunar.client.driver.core.gui.mixin.HostedWorldBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.LanguagePageBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.CosmeticPreviewBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.SavedSkinBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.AlertBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.AccountBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.StyngrBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.BlogPostsBridge;
import com.moonsworth.lunar.client.driver.core.gui.mixin.SocialMediaBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.UiStateBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.SkyblockBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.OutfitBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.BadgeBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.ColorBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.LunarPlusBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.SettingsBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.HomeRadioBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.HomeThemeBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.HomeNavigationBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.ProfileBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.ServerDiscoveryBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.MetadataBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.NotificationBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.PlayerBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.ServerPingBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.HomeButtonBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.nameplate.FriendsGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.nameplate.FriendApiLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers.GuiExtension2;
import com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump61;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump68;
import com.moonsworth.lunar.files.Files6_2;
import com.moonsworth.webosr.javascript.CallbackJS;
import com.moonsworth.webosr.wrappers.Browser;
import com.moonsworth.webosr.wrappers.PromiseJS;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import lombok.Generated;

public class DriverBridgeLegacy {
   private final com.moonsworth.lunar.client.driver.core.DriverViewportLegacy field1;
   private final Map<String, Files6_2<Class<?>, Function<String, DriverGuiExtensionLegacy>>> field2;

   public DriverBridgeLegacy(com.moonsworth.lunar.client.driver.core.DriverViewportLegacy var1) {
      this.field1 = var1;
      this.field2 = this.method1();
   }

   private Map<String, Files6_2<Class<?>, Function<String, DriverGuiExtensionLegacy>>> method1() {
      return ImmutableMap.builder()
         .put("notification", Files6_2.method1(NotificationBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new NotificationBridgeLegacy()))
         .put("language", Files6_2.method1(LanguagePageBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new LanguagePageBridgeLegacy()))
         .put("promotion", Files6_2.method1(PromotionBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new PromotionBridgeLegacy()))
         .put(
            "analytics",
            Files6_2.method1(
               com.moonsworth.lunar.client.driver.gui.AnalyticsDataProviderLegacy.class,
               (Function<String, DriverGuiExtensionLegacy>)var0 -> new com.moonsworth.lunar.client.driver.gui.AnalyticsDataProviderLegacy()
            )
         )
         .put("metadata", Files6_2.method1(MetadataBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new MetadataBridgeLegacy()))
         .put("accounts", Files6_2.method1(AccountBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new AccountBridgeLegacy()))
         .put("alerts", Files6_2.method1(AlertBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new AlertBridgeLegacy()))
         .put("blogPosts", Files6_2.method1(BlogPostsBridge.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new BlogPostsBridge()))
         .put("homeButtons", Files6_2.method1(HomeButtonBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new HomeButtonBridgeLegacy()))
         .put("homeNavigation", Files6_2.method1(HomeNavigationBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new HomeNavigationBridgeLegacy()))
         .put("savedSkins", Files6_2.method1(SavedSkinBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new SavedSkinBridgeLegacy()))
         .put("socialMedia", Files6_2.method1(SocialMediaBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new SocialMediaBridgeLegacy()))
         .put("homeTheme", Files6_2.method1(HomeThemeBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new HomeThemeBridgeLegacy()))
         .put("homeRadio", Files6_2.method1(HomeRadioBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new HomeRadioBridgeLegacy()))
         .put("serverDiscovery", Files6_2.method1(ServerDiscoveryBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new ServerDiscoveryBridgeLegacy()))
         .put("serverPing", Files6_2.method1(ServerPingBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new ServerPingBridgeLegacy()))
         .put("profiles", Files6_2.method1(ProfileBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new ProfileBridgeLegacy()))
         .put("profileImport", Files6_2.method1(ProfileImportProviderLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new ProfileImportProviderLegacy()))
         .put("settings", Files6_2.method1(SettingsBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new SettingsBridgeLegacy()))
         .put("cosmetics", Files6_2.method1(CosmeticsBridge.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new CosmeticsBridge()))
         .put("outfits", Files6_2.method1(OutfitBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new OutfitBridgeLegacy()))
         .put("lunarPlus", Files6_2.method1(LunarPlusBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new LunarPlusBridgeLegacy()))
         .put("emotes", Files6_2.method1(EmotesBridge.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new EmotesBridge()))
         .put("badges", Files6_2.method1(BadgeBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new BadgeBridgeLegacy()))
         .put("styngr", Files6_2.method1(StyngrBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new StyngrBridgeLegacy()))
         .put("colors", Files6_2.method1(ColorBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new ColorBridgeLegacy()))
         .put("sprays", Files6_2.method1(SpraysBridge.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new SpraysBridge()))
         .put("waypoints", Files6_2.method1(Rewindhandlers.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new Rewindhandlers()))
         .put(
            "waypointMap",
            Files6_2.method1(
               com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers.MinimapJsApiLegacy.class,
               (Function<String, DriverGuiExtensionLegacy>)var0 -> new com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers.MinimapJsApiLegacy()
            )
         )
         .put("waypointImportExport", Files6_2.method1(GuiExtension2.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new GuiExtension2()))
         .put(
            "waypointGroup",
            Files6_2.method1(
               com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers.GuiExtension.class,
               (Function<String, DriverGuiExtensionLegacy>)var0 -> new com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers.GuiExtension()
            )
         )
         .put(
            "waypointSort",
            Files6_2.method1(
               com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers.GuiExtension4.class,
               (Function<String, DriverGuiExtensionLegacy>)var0 -> new com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers.GuiExtension4()
            )
         )
         .put(
            "waypointUtility",
            Files6_2.method1(
               com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers.WaypointJsApiLegacy.class,
               (Function<String, DriverGuiExtensionLegacy>)var0 -> new com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers.WaypointJsApiLegacy()
            )
         )
         .put(
            "waypointModImport",
            Files6_2.method1(
               com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers.WaypointImportJsApiLegacy.class,
               (Function<String, DriverGuiExtensionLegacy>)var0 -> new com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers.WaypointImportJsApiLegacy()
            )
         )
         .put("mods", Files6_2.method1(Gui.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new Gui()))
         .put(
            "hudModProperty",
            Files6_2.method1(com.moonsworth.lunar.client.driver.gui.FeatureDataProviderLegacy.class, com.moonsworth.lunar.client.driver.gui.FeatureDataProviderLegacy::new)
         )
         .put(
            "hudModMetadata",
            Files6_2.method1(com.moonsworth.lunar.client.driver.gui.MarkersDataProviderLegacy.class, com.moonsworth.lunar.client.driver.gui.MarkersDataProviderLegacy::new)
         )
         .put(
            "spiritLeap",
            Files6_2.method1(
               com.moonsworth.lunar.client.driver.core.gui.mixin.GuiExtension.class,
               (Function<String, DriverGuiExtensionLegacy>)var0 -> new com.moonsworth.lunar.client.driver.core.gui.mixin.GuiExtension()
            )
         )
         .put("skyblock", Files6_2.method1(SkyblockBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new SkyblockBridgeLegacy()))
         .put(
            "markers",
            Files6_2.method1(
               com.moonsworth.lunar.client.driver.core.gui.mixin.Gui.class,
               (Function<String, DriverGuiExtensionLegacy>)var0 -> new com.moonsworth.lunar.client.driver.core.gui.mixin.Gui()
            )
         )
         .put(
            "screenshotUpload",
            Files6_2.method1(
               com.moonsworth.lunar.client.driver.gui.GuiExtension.class,
               (Function<String, DriverGuiExtensionLegacy>)var0 -> new com.moonsworth.lunar.client.driver.gui.GuiExtension()
            )
         )
         .put("shaderEditor", Files6_2.method1(Shaderdebugmod5.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new Shaderdebugmod5()))
         .put("assetsClient", Files6_2.method1(FriendsGuiExtensionLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new FriendsGuiExtensionLegacy()))
         .put("friends", Files6_2.method1(FriendApiLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new FriendApiLegacy()))
         .put(
            "conversations",
            Files6_2.method1(
               com.moonsworth.lunar.client.driver.core.gui.mixin.nameplate.GuiExtension.class,
               (Function<String, DriverGuiExtensionLegacy>)var0 -> new com.moonsworth.lunar.client.driver.core.gui.mixin.nameplate.GuiExtension()
            )
         )
         .put("hostedWorlds", Files6_2.method1(HostedWorldBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new HostedWorldBridgeLegacy()))
         .put("rewind", Files6_2.method1(Holograms2.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new Holograms2()))
         .put("rewindTimeline", Files6_2.method1(Chest2.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new Chest2()))
         .put("rewindProperties", Files6_2.method1(Colorsaturation2.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new Colorsaturation2()))
         .put("rewindMedia", Files6_2.method1(Highlight2_2.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new Highlight2_2()))
         .put("rewindTimelinesList", Files6_2.method1(Click2.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new Click2()))
         .put("rewindExport", Files6_2.method1(Rewindhandlers2_4.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new Rewindhandlers2_4()))
         .put("rewindEffects", Files6_2.method1(Rewindhandlers2_3.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new Rewindhandlers2_3()))
         .put("rewindsList", Files6_2.method1(Rewindhandlers_6.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new Rewindhandlers_6()))
         .put("rewindCreateProject", Files6_2.method1(GuiIterator.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new GuiIterator()))
         .put(
            "rewindPacks",
            Files6_2.method1(
               com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Gui.class,
               (Function<String, DriverGuiExtensionLegacy>)var0 -> new com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Gui()
            )
         )
         .put("rewindInteraction", Files6_2.method1(Nameplate2_2.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new Nameplate2_2()))
         .put("rewindDev", Files6_2.method1(Rewindhandlers2_2.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new Rewindhandlers2_2()))
         .put("player", Files6_2.method1(PlayerBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new PlayerBridgeLegacy()))
         .put("uiState", Files6_2.method1(UiStateBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new UiStateBridgeLegacy()))
         .put("cosmeticPreview", Files6_2.method1(CosmeticPreviewBridgeLegacy.class, (Function<String, DriverGuiExtensionLegacy>)var0 -> new CosmeticPreviewBridgeLegacy()))
         .build();
   }

   public void method2(Bridge7_8 var1) {
      Bridge7_8 var2;
      if ((ThreadModuleDump63.method8() == null || this.field1.method63() == DriverRouteRegistryLegacy.field4) && !(var1 instanceof MainMenuButton)) {
         var2 = new MainMenuButton(Bridge.method8().method18(var1));
      } else {
         var2 = var1;
      }

      if (var1 instanceof MainMenuButton var3) {
         Bridge7_8 var4 = ThreadModuleDump63.method31(var3.method17());
         Bridge7_8 var5 = ThreadModuleDump63.method31(ThreadModuleDump63.method3().bridge$getCurrentScreen());
         if (var5 != null && var4.getClass() == var5.getClass()) {
            return;
         }

         this.field1.method17(DriverRouteRegistryLegacy.field5, new VanillaHomeContextLegacy(var3.method4()));
      } else {
         this.field1.method16(DriverRouteRegistryLegacy.field3);
      }

      LcuiScreen.method15();
      this.field1.method75().add(() -> ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(var2)));
   }

   @CallbackJS("setScreen")
   public void method3(DriverScreenLegacy var1) {
      if (var1.isRequiresAuthentication() && !ThreadModuleDump63.method4().method43().method23()) {
         AccountBridgeLegacy.method2();
      } else if (var1 == DriverScreenLegacy.NULL
         && com.moonsworth.lunar.client.driver.core.DualMarkerScreenLegacy.method13(ThreadModuleDump63.method11())
         && ThreadModuleDump63.method8() == null) {
         Client.method109().method23();
      } else {
         LcuiScreen.method15();
         this.field1.method16(DriverRouteRegistryLegacy.field3);
         this.field1.method75().add(() -> ThreadModuleDump63.method3().bridge$displayScreen(var1.getInitScreen().apply(null)));
      }
   }

   @CallbackJS("requestPayload")
   public String method4(String var1, String var2) {
      Files6_2 var3 = this.field2.getOrDefault(var1, null);
      if (var3 != null) {
         String var4 = var2.isEmpty() ? "" : "-" + var2;
         String var5 = var1 + var4;
         DriverGuiExtensionLegacy var6 = (DriverGuiExtensionLegacy)((Function)var3.field2).apply(var2);
         JsonElement var7 = var6.provide();
         this.field1.method60().put(var5, var7);
         com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method59().put(var5, var6);
         if (com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.isDebug()) {
            Slayer.method4("WebOSR", "Tracking provider: %s", new Object[]{var5});
         }

         return ThreadModuleDump48.field22.toJson(var7);
      } else {
         return null;
      }
   }

   @CallbackJS("unregisterPayload")
   public void method5(String var1, String var2) {
      String var3 = var2.isEmpty() ? "" : "-" + var2;
      String var4 = var1 + var3;
      this.field1.method60().remove(var4);
      com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method59().remove(var4);
      if (com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.isDebug()) {
         Slayer.method4("WebOSR", "Stop tracking provider: %s", new Object[]{var4});
      }
   }

   @CallbackJS("setActiveRoute")
   public void method6(String var1) {
      this.field1.method27(this.field1.method61());

      while (!this.field1.method75().isEmpty()) {
         this.field1.method75().poll().run();
      }

      Optional var2 = DriverRouteRegistryLegacy.method3().stream().filter(var1x -> var1x.getPath().equals(var1.split("(?=/)")[0])).findAny();
      if (var2.isPresent()) {
         this.field1.method27((DriverRouteRegistryLegacy)var2.get());
      } else {
         Slayer.method6("WebOSR", "No route found for %s.", new Object[]{var1});
      }

      this.field1.method12();
   }

   @CallbackJS("setActiveOverlay")
   public void method7(String var1) {
      Optional var2 = DriverOverlayRegistryLegacy.method3().stream().filter(var1x -> var1x.getId().equals(var1)).findAny();
      if (var2.isPresent()) {
         this.field1.method19((DriverOverlayRegistryLegacy)var2.get());
      } else {
         Slayer.method6("WebOSR", "No overlay found for %s.", new Object[]{var1});
      }
   }

   @CallbackJS("hideOverlay")
   public void method8() {
      this.field1.method21();
   }

   @CallbackJS("invoke")
   public void method9(PromiseJS<String> var1, Browser var2, String[] var3) {
      this.field1.method39().invoke(var1, var2, var3[0], Arrays.copyOfRange(var3, 1, var3.length));
   }

   @CallbackJS("onClick")
   public void method10(String var1) {
      for (DriverGuiExtensionLegacy var3 : com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method59().values()) {
         var3.method1(var1);
      }
   }

   @CallbackJS("openUri")
   public void method11(String var1, Initiator var2) {
      if (!ThreadModuleDump61.method7(var1, var2)) {
      }
   }

   @CallbackJS("copyToClipboard")
   public void method12(String var1) {
      ThreadModuleDump68.setClipboardString(var1);
      ThreadModuleDump63.method4().method69().method3("Copied to clipboard!");
   }

   @CallbackJS("getClipboardString")
   public String method13() {
      return ThreadModuleDump68.getClipboardString();
   }

   @CallbackJS("setAllowKeybinds")
   public void method14(Boolean var1) {
      this.field1.method68(var1);
   }

   @CallbackJS("setToastsVisible")
   public void method15(Boolean var1) {
      this.field1.method72(var1);
   }

   @CallbackJS("setPickingColor")
   public void method16(Boolean var1) {
      this.field1.method70(var1);
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
   public Map<String, Files6_2<Class<?>, Function<String, DriverGuiExtensionLegacy>>> method19() {
      return this.field2;
   }
}
