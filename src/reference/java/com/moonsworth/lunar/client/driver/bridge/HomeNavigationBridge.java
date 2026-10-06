package com.moonsworth.lunar.client.driver.bridge;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GuiMainMenuBridge;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuButton;
import com.moonsworth.lunar.client.ui.hud.HudEditorScreen;
import com.moonsworth.lunar.client.ui.external.ExternalLinkRegistry;
import com.moonsworth.lunar.client.ui.external.ModMenuCompat;
import com.moonsworth.lunar.client.ui.external.RecordingExternalLink;
import com.moonsworth.lunar.client.config.InternalSettings;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.driver.DriverOverlayRegistry;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.driver.PhosphorIcon;
import com.moonsworth.lunar.client.driver.DriverScreen;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.bridge.ButtonProvider;
import com.moonsworth.lunar.client.driver.LockerContext;
import com.moonsworth.lunar.client.driver.LockerSection;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.config.Config;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import java.util.Optional;
import org.jetbrains.annotations.Nullable;

public class HomeNavigationBridge extends com.moonsworth.lunar.client.driver.bridge.ButtonProviderGui {
   private int field2;
   private int field3;
   private int field4;
   private int field5;
   private int field6;
   private int field7;
   private boolean field8 = false;
   private boolean field9;

   public HomeNavigationBridge() {
      this.method7();
      this.method1(
         ButtonProvider.method8()
            .method1("gui.components.btnMenu")
            .method3("icons/mainmenu/logo.svg")
            .method9(() -> DriverViewportLegacy.method50().method56().method2(new MainMenuButton(new HudEditorScreen())))
            .method12()
      );
      this.method1(ButtonProvider.method8().method1("gui.components.btnLocker").method3("icons/mainmenu/cosmetics.svg").method9(() -> {
         if (!Ref.method4().method43().method22()) {
            AccountBridge.method2();
         } else {
            com.moonsworth.lunar.client.ui.LcuiScreen.method15();
            DriverViewportLegacy.method50().method17(DriverRouteRegistry.field13, new LockerContext(LockerSection.COSMETICS, null, false));
         }
      }).method12());
      this.method1(ButtonProvider.method8().method1("gui.components.btnSatellite").method2(PhosphorIcon.PI_CHAT_TYPING_SOLID).method9(() -> {
         com.moonsworth.lunar.client.ui.LcuiScreen.method15();
         DriverViewportLegacy.method50().method19(DriverOverlayRegistry.field2);
      }).method12());
      if (Bridge.getMinecraftVersion().method4(Config.field40)) {
         this.method1(ButtonProvider.method8().method1("gui.components.btnXboxFriends").method2(PhosphorIcon.PI_USER_TWO_SOLID).method9(() -> {
            if (!Ref.method4().method43().method23()) {
               AccountBridge.method2();
            } else {
               com.moonsworth.lunar.client.ui.LcuiScreen.method15();
               DriverViewportLegacy highlight3iterator0 = DriverViewportLegacy.method50();
               highlight3iterator0.method16(DriverRouteRegistry.field3);
               highlight3iterator0.method75().add(() -> Bridge.method8().method37());
            }
         }).method11(ButtonProvider.ModLoader.VANILLA).method12());
      }

      this.method1(
         ButtonProvider.method8()
            .method1("gui.components.btnOptions")
            .method3("icons/mainmenu/cog.svg")
            .method9(() -> DriverViewportLegacy.method50().method56().method3(DriverScreen.SETTINGS))
            .method11(ButtonProvider.ModLoader.VANILLA)
            .method12()
      );
      this.method1(ButtonProvider.method8().method1("gui.components.btnLanguage").method3("icons/mainmenu/globe.svg").method9(() -> {
         com.moonsworth.lunar.client.ui.LcuiScreen.method15();
         DriverViewportLegacy.method50().method16(DriverRouteRegistry.field9);
      }).method12());
      if (Bridge.getMinecraftVersion().method19()) {
         this.method1(
            ButtonProvider.method8()
               .method1("gui.components.btnRealms")
               .method3("icons/mainmenu/gem.svg")
               .method9(() -> DriverViewportLegacy.method50().method56().method3(DriverScreen.REALMS))
               .method11(ButtonProvider.ModLoader.VANILLA)
               .method12()
         );
      }

      if (FeatureFlag.REWIND.isEnabled()) {
         this.method1(
            ButtonProvider.method8()
               .method1("gui.components.btnRewindEditor")
               .method3("icons/mainmenu/rewind.svg")
               .method10(() -> Ref.method4().method40().method85().isEnabled())
               .method9(() -> {
                  com.moonsworth.lunar.client.ui.LcuiScreen.method15();
                  DriverViewportLegacy.method50().method16(DriverRouteRegistry.field12);
               })
               .method12()
         );
      }

      Optional optional1 = ExternalLinkRegistry.method2(RecordingExternalLink.class);
      if (optional1.isPresent()) {
         this.method1(
            ButtonProvider.method8().method1("gui.components.btnReplayViewer").method3("icons/mainmenu/replaymod.png").method9(() -> {
               if (!Ref.method4().method43().method22()) {
                  AccountBridge.method2();
               } else {
                  com.moonsworth.lunar.client.ui.LcuiScreen.method15();
                  DriverViewportLegacy.method50().method16(DriverRouteRegistry.field3);
                  DriverViewportLegacy.method50().method75().add(() -> optional1.ifPresent(RecordingExternalLink::handleMainMenuButton));
               }
            }).method11(ButtonProvider.ModLoader.EXTERNAL).method12()
         );
      }

      Optional optional2 = ExternalLinkRegistry.method2(com.moonsworth.lunar.client.ui.external.MainMenuExternalLink.class);
      if (optional2.isPresent()) {
         this.method1(
            ButtonProvider.method8()
               .method1("gui.components.btnFlashback")
               .method3("icons/mainmenu/flashback.png")
               .method9(
                  () -> {
                     com.moonsworth.lunar.client.ui.LcuiScreen.method15();
                     DriverViewportLegacy.method50().method16(DriverRouteRegistry.field3);
                     DriverViewportLegacy.method50()
                        .method75()
                        .add(() -> optional2.ifPresent(com.moonsworth.lunar.client.ui.external.MainMenuExternalLink::handleMainMenuButton));
                  }
               )
               .method11(ButtonProvider.ModLoader.EXTERNAL)
               .method12()
         );
      }

      if (ModMenuCompat.method2()) {
         this.method1(
            ButtonProvider.method8().method1("gui.components.btnFabricModMenu").method3("icons/mainmenu/modmenu.svg").method9(() -> {
               com.moonsworth.lunar.client.ui.LcuiScreen.method15();
               DriverViewportLegacy.method50().method16(DriverRouteRegistry.field3);
               DriverViewportLegacy.method50().method75().add(ModMenuCompat::method1);
            }).method11(ButtonProvider.ModLoader.EXTERNAL).method12()
         );
      }
   }

   private JsonElement method2() {
      JsonArray array1 = new JsonArray();
      boolean flag2 = Ref.method4().method41().method9().method16();
      boolean flag3 = Ref.method4().method41().method9().method23();
      boolean flag4 = Ref.method4().method41().method9().method23();
      ((ButtonProvider)this.RIHCRCRCOOHRCOOOIHORHHHHOHCCCC.get("gui.components.btnLocker")).method9(flag2 || flag3 || flag4);
      ((ButtonProvider)this.RIHCRCRCOOHRCOOOIHORHHHHOHCCCC.get("gui.components.btnSatellite")).method10(Ref.method4().method52().method5());

      for (ButtonProvider gui2task6 : this.RIHCRCRCOOHRCOOOIHORHHHHOHCCCC.values()) {
         if (gui2task6.method12().getAsBoolean()) {
            array1.add(gui2task6.provide());
         }
      }

      this.field8 = Ref.method4().method40().method85().isEnabled();
      return array1;
   }

   @Override
   public void method1(String text1) {
      super.method1(text1);
      if (text1.equals("game-exit")) {
         Class clazz2 = Ref.method11();
         if (clazz2 != null && !GuiMainMenuBridge.class.isAssignableFrom(clazz2) && DriverViewportLegacy.method50().method63() != DriverRouteRegistry.field4) {
            Ref.method3().bridge$displayScreen(null);
         } else {
            DriverViewportLegacy.setShutdown(true);
         }
      }
   }

   @Override
   public JsonElement provide() {
      return this.method2();
   }

   @Nullable
   @Override
   public JsonElement method128() {
      if ((!this.method8() || !this.method4()) && !this.method5() && this.field8 == Ref.method4().method40().method85().isEnabled()) {
         return null;
      }

      this.method7();
      return this.provide();
   }

   private boolean method4() {
      InternalSettings fogloader231 = Ref.method4().method41().method9();
      return this.field4 != Ref.method4().method45().method24().size()
         || this.field2 != Ref.method4().method53().IIORHHIRHIORHRCCCOICCRCHRRCCRH().size()
         || this.field6 != Ref.method4().method46().IIORHHIRHIORHRCCCOICCRCHRRCCRH().size()
         || this.field3 != ((LongSet)fogloader231.method21().get()).size()
         || this.field7 != ((IntSet)fogloader231.method20().get()).size()
         || this.field5 != ((IntSet)fogloader231.method18().get()).size();
   }

   private boolean method5() {
      return this.field9 != Ref.method4().method52().method5();
   }

   private void method7() {
      InternalSettings fogloader231 = Ref.method4().method41().method9();
      this.field2 = Ref.method4().method53().IIORHHIRHIORHRCCCOICCRCHRRCCRH().size();
      this.field3 = ((LongSet)fogloader231.method21().get()).size();
      this.field4 = Ref.method4().method45().method24().size();
      this.field5 = ((IntSet)fogloader231.method18().get()).size();
      this.field6 = Ref.method4().method46().method41().size();
      this.field7 = ((IntSet)fogloader231.method20().get()).size();
      this.field9 = Ref.method4().method52().method5();
   }

   private boolean method8() {
      return (Boolean)Ref.method4().method41().method6().method58().get();
   }
}
