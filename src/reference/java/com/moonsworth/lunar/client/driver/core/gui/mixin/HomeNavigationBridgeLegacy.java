package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension610;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuButton;
import com.moonsworth.lunar.client.ui.hud.HudEditorScreen;
import com.moonsworth.lunar.client.fishing.Fishing;
import com.moonsworth.lunar.client.fishing.Nameplate;
import com.moonsworth.lunar.client.fishing.highlight.Fishing2Extension;
import com.moonsworth.lunar.client.config.InternalSettings;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.driver.DriverOverlayRegistryLegacy;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.PhosphorIconLegacy;
import com.moonsworth.lunar.client.driver.DriverScreenLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.Gui2Task;
import com.moonsworth.lunar.client.driver.core.nameplate.LockerContextLegacy;
import com.moonsworth.lunar.client.driver.core.nameplate.LockerSectionLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.config.Config;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import java.util.Optional;
import org.jetbrains.annotations.Nullable;

public class HomeNavigationBridgeLegacy extends com.moonsworth.lunar.client.driver.core.gui.GuiExtension {
   private int field2;
   private int field3;
   private int field4;
   private int field5;
   private int field6;
   private int field7;
   private boolean field8 = false;
   private boolean field9;

   public HomeNavigationBridgeLegacy() {
      this.method7();
      this.method1(
         Gui2Task.method8()
            .method1("gui.components.btnMenu")
            .method3("icons/mainmenu/logo.svg")
            .method9(() -> DriverViewportLegacy.method50().method56().method2(new MainMenuButton(new HudEditorScreen())))
            .method12()
      );
      this.method1(Gui2Task.method8().method1("gui.components.btnLocker").method3("icons/mainmenu/cosmetics.svg").method9(() -> {
         if (!ThreadModuleDump63.method4().method43().method22()) {
            AccountBridgeLegacy.method2();
         } else {
            com.moonsworth.lunar.client.ui.LcuiScreen.method15();
            DriverViewportLegacy.method50().method17(DriverRouteRegistryLegacy.field13, new LockerContextLegacy(LockerSectionLegacy.COSMETICS, null, false));
         }
      }).method12());
      this.method1(Gui2Task.method8().method1("gui.components.btnSatellite").method2(PhosphorIconLegacy.PI_CHAT_TYPING_SOLID).method9(() -> {
         com.moonsworth.lunar.client.ui.LcuiScreen.method15();
         DriverViewportLegacy.method50().method19(DriverOverlayRegistryLegacy.field2);
      }).method12());
      if (Bridge.getMinecraftVersion().method4(Config.field40)) {
         this.method1(Gui2Task.method8().method1("gui.components.btnXboxFriends").method2(PhosphorIconLegacy.PI_USER_TWO_SOLID).method9(() -> {
            if (!ThreadModuleDump63.method4().method43().method23()) {
               AccountBridgeLegacy.method2();
            } else {
               com.moonsworth.lunar.client.ui.LcuiScreen.method15();
               DriverViewportLegacy var0 = DriverViewportLegacy.method50();
               var0.method16(DriverRouteRegistryLegacy.field3);
               var0.method75().add(() -> Bridge.method8().method37());
            }
         }).method11(Gui2Task.Type2.VANILLA).method12());
      }

      this.method1(
         Gui2Task.method8()
            .method1("gui.components.btnOptions")
            .method3("icons/mainmenu/cog.svg")
            .method9(() -> DriverViewportLegacy.method50().method56().method3(DriverScreenLegacy.SETTINGS))
            .method11(Gui2Task.Type2.VANILLA)
            .method12()
      );
      this.method1(Gui2Task.method8().method1("gui.components.btnLanguage").method3("icons/mainmenu/globe.svg").method9(() -> {
         com.moonsworth.lunar.client.ui.LcuiScreen.method15();
         DriverViewportLegacy.method50().method16(DriverRouteRegistryLegacy.field9);
      }).method12());
      if (Bridge.getMinecraftVersion().method19()) {
         this.method1(
            Gui2Task.method8()
               .method1("gui.components.btnRealms")
               .method3("icons/mainmenu/gem.svg")
               .method9(() -> DriverViewportLegacy.method50().method56().method3(DriverScreenLegacy.REALMS))
               .method11(Gui2Task.Type2.VANILLA)
               .method12()
         );
      }

      if (FeatureFlag.REWIND.isEnabled()) {
         this.method1(
            Gui2Task.method8()
               .method1("gui.components.btnRewindEditor")
               .method3("icons/mainmenu/rewind.svg")
               .method10(() -> ThreadModuleDump63.method4().method40().method85().isEnabled())
               .method9(() -> {
                  com.moonsworth.lunar.client.ui.LcuiScreen.method15();
                  DriverViewportLegacy.method50().method16(DriverRouteRegistryLegacy.field12);
               })
               .method12()
         );
      }

      Optional var1 = Fishing.method2(Fishing2Extension.class);
      if (var1.isPresent()) {
         this.method1(
            Gui2Task.method8().method1("gui.components.btnReplayViewer").method3("icons/mainmenu/replaymod.png").method9(() -> {
               if (!ThreadModuleDump63.method4().method43().method22()) {
                  AccountBridgeLegacy.method2();
               } else {
                  com.moonsworth.lunar.client.ui.LcuiScreen.method15();
                  DriverViewportLegacy.method50().method16(DriverRouteRegistryLegacy.field3);
                  DriverViewportLegacy.method50().method75().add(() -> var1.ifPresent(Fishing2Extension::handleMainMenuButton));
               }
            }).method11(Gui2Task.Type2.EXTERNAL).method12()
         );
      }

      Optional var2 = Fishing.method2(com.moonsworth.lunar.client.fishing.mixin.Fishing2Extension.class);
      if (var2.isPresent()) {
         this.method1(
            Gui2Task.method8()
               .method1("gui.components.btnFlashback")
               .method3("icons/mainmenu/flashback.png")
               .method9(
                  () -> {
                     com.moonsworth.lunar.client.ui.LcuiScreen.method15();
                     DriverViewportLegacy.method50().method16(DriverRouteRegistryLegacy.field3);
                     DriverViewportLegacy.method50()
                        .method75()
                        .add(() -> var2.ifPresent(com.moonsworth.lunar.client.fishing.mixin.Fishing2Extension::handleMainMenuButton));
                  }
               )
               .method11(Gui2Task.Type2.EXTERNAL)
               .method12()
         );
      }

      if (Nameplate.method2()) {
         this.method1(
            Gui2Task.method8().method1("gui.components.btnFabricModMenu").method3("icons/mainmenu/modmenu.svg").method9(() -> {
               com.moonsworth.lunar.client.ui.LcuiScreen.method15();
               DriverViewportLegacy.method50().method16(DriverRouteRegistryLegacy.field3);
               DriverViewportLegacy.method50().method75().add(Nameplate::method1);
            }).method11(Gui2Task.Type2.EXTERNAL).method12()
         );
      }
   }

   private JsonElement method2() {
      JsonArray var1 = new JsonArray();
      boolean var2 = ThreadModuleDump63.method4().method41().method9().method16();
      boolean var3 = ThreadModuleDump63.method4().method41().method9().method23();
      boolean var4 = ThreadModuleDump63.method4().method41().method9().method23();
      ((Gui2Task)this.field1.get("gui.components.btnLocker")).method9(var2 || var3 || var4);
      ((Gui2Task)this.field1.get("gui.components.btnSatellite")).method10(ThreadModuleDump63.method4().method52().method5());

      for (Gui2Task var6 : this.field1.values()) {
         if (var6.method12().getAsBoolean()) {
            var1.add(var6.provide());
         }
      }

      this.field8 = ThreadModuleDump63.method4().method40().method85().isEnabled();
      return var1;
   }

   @Override
   public void method1(String var1) {
      super.method1(var1);
      if (var1.equals("game-exit")) {
         Class var2 = ThreadModuleDump63.method11();
         if (var2 != null && !Bridge5Extension610.class.isAssignableFrom(var2) && DriverViewportLegacy.method50().method63() != DriverRouteRegistryLegacy.field4) {
            ThreadModuleDump63.method3().bridge$displayScreen(null);
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
      if ((!this.method8() || !this.method4()) && !this.method5() && this.field8 == ThreadModuleDump63.method4().method40().method85().isEnabled()) {
         return null;
      }

      this.method7();
      return this.provide();
   }

   private boolean method4() {
      InternalSettings var1 = ThreadModuleDump63.method4().method41().method9();
      return this.field4 != ThreadModuleDump63.method4().method45().method24().size()
         || this.field2 != ThreadModuleDump63.method4().method53().IIORHHIRHIORHRCCCOICCRCHRRCCRH().size()
         || this.field6 != ThreadModuleDump63.method4().method46().IIORHHIRHIORHRCCCOICCRCHRRCCRH().size()
         || this.field3 != ((LongSet)var1.method21().get()).size()
         || this.field7 != ((IntSet)var1.method20().get()).size()
         || this.field5 != ((IntSet)var1.method18().get()).size();
   }

   private boolean method5() {
      return this.field9 != ThreadModuleDump63.method4().method52().method5();
   }

   private void method7() {
      InternalSettings var1 = ThreadModuleDump63.method4().method41().method9();
      this.field2 = ThreadModuleDump63.method4().method53().IIORHHIRHIORHRCCCOICCRCHRRCCRH().size();
      this.field3 = ((LongSet)var1.method21().get()).size();
      this.field4 = ThreadModuleDump63.method4().method45().method24().size();
      this.field5 = ((IntSet)var1.method18().get()).size();
      this.field6 = ThreadModuleDump63.method4().method46().method41().size();
      this.field7 = ((IntSet)var1.method20().get()).size();
      this.field9 = ThreadModuleDump63.method4().method52().method5();
   }

   private boolean method8() {
      return (Boolean)ThreadModuleDump63.method4().method41().method6().method58().get();
   }
}
