package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.ColorAnimation;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.ui.widget.TextLabelWidget;
import com.moonsworth.lunar.client.ui.widget.ProgressBarWidget;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuButton;
import com.moonsworth.lunar.client.ui.mainmenu.AnimatedLogoWidget;
import com.moonsworth.lunar.client.ui.hud.HudEditorStateMachine;
import com.moonsworth.lunar.client.ui.hud.HudElementRegistryImpl;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.InternalSettings;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.mixin.AccountBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.nameplate.LockerContextLegacy;
import com.moonsworth.lunar.client.driver.core.nameplate.LockerSectionLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump20;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class HudEditorScreen extends com.moonsworth.lunar.client.ui.LcuiScreen implements HudEditorWidget {
   private static final float field19 = 20.0F;
   private static final List<EditorShortcut> field20 = List.of(
      EditorShortcut.method1("Mouse1", "hold", "addModsToRegion"),
      EditorShortcut.method1("Mouse1", "hold", "selectDragMods"),
      EditorShortcut.method1("Mouse2", "click", "resetToClosest"),
      EditorShortcut.method2("CTRL", "Mouse1", null, "toggleModSelection"),
      EditorShortcut.method2("CTRL", "Z", null, "undoMovements"),
      EditorShortcut.method2("CTRL", "Y", null, "redoMovements")
   );
   private final HudEditorOverlay field21;
   private final AnimatedLogoWidget field22 = new AnimatedLogoWidget(null, true, false);
   private final ProgressBarWidget field23;
   private final TextLabelWidget field24;
   private final TextLabelWidget field25;
   private final TextLabelWidget field26;

   public HudEditorScreen() {
      HudElementRegistryImpl var1 = new HudElementRegistryImpl();
      this.field21 = new HudEditorOverlay(this, var1, new HudEditorStateMachine(var1));
      this.field23 = new ProgressBarWidget(null, "reportABug", ResourceLocationBridge.create("lunar", "icons/eye-24.png"));
      this.field24 = new HudEditorScreen.Data();
      this.field25 = new HudEditorScreen.Data();
      this.field26 = new HudEditorScreen.Data();
      this.field24
         .method4(
            (var1x, var2) -> {
               if (this.field21.method43() == null && var2 == 0) {
                  this.field21.method10();
                  if (ThreadModuleDump63.method3().bridge$getWorld() == null) {
                     ThreadModuleDump63.method3()
                        .bridge$displayScreen(
                           Bridge.method8()
                              .method18(
                                 new MainMenuButton(
                                    new com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen(ThreadModuleDump63.method3().bridge$getCurrentScreen())
                                 )
                              )
                        );
                  } else {
                     ThreadModuleDump63.method3()
                        .bridge$displayScreen(
                           Bridge.method8()
                              .method18(new com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen(ThreadModuleDump63.method3().bridge$getCurrentScreen()))
                        );
                  }

                  return true;
               } else {
                  return false;
               }
            }
         );
      this.field25.method4(() -> !ThreadModuleDump63.method4().method40().method64().method13());
      this.field26.method4(() -> !ThreadModuleDump63.method4().method40().method64().method13());
      this.field23
         .method4(
            (var1x, var2) -> {
               this.field21.method10();

               try {
                  ThreadModuleDump63.method3()
                     .bridge$displayScreen(
                        Bridge.method8().method33(null, "https://support.lunarclient.com", new URL("https://support.lunarclient.com").toURI(), true)
                     );
               } catch (MalformedURLException | URISyntaxException var4) {
                  Inventorymod2.method5(var4, "MovementUI");
               }

               return true;
            }
         );
      this.field25.method4((var1x, var2) -> {
         if (this.field21.method43() == null && var2 == 0) {
            this.field21.method10();
            if (ThreadModuleDump63.method3().bridge$getWorld() == null && !ThreadModuleDump63.method4().method43().method23()) {
               AccountBridgeLegacy.method2();
               return true;
            } else {
               DriverViewportLegacy.method50().method17(DriverRouteRegistryLegacy.field13, new LockerContextLegacy(LockerSectionLegacy.COSMETICS, null, false));
               return true;
            }
         } else {
            return false;
         }
      });
      this.field26.method4((var1x, var2) -> {
         if (this.field21.method43() != null || var2 != 0) {
            return false;
         } else if (ThreadModuleDump63.method3().bridge$getWorld() == null && !ThreadModuleDump63.method4().method43().method23()) {
            AccountBridgeLegacy.method2();
            return true;
         } else {
            DriverViewportLegacy.method50().method17(DriverRouteRegistryLegacy.field13, new LockerContextLegacy(LockerSectionLegacy.EMOTES, null, false));
            return true;
         }
      });
      this.field24.setTextColor(-1);
      this.field24.method12(new AnimatedValue(805306368, 1342177280));
      this.field24.method11(new AnimatedValue(1076176165, -1711276033));
      this.field24.method18(2.0F);
      this.field24.method1("mods", true);
      this.field25.setTextColor(-1);
      this.field25.method12(new AnimatedValue(805306368, 1342177280));
      this.field25.method11(new AnimatedValue(1076176165, -1711276033));
      this.field25.method18(2.0F);
      this.field25.method7(ResourceLocationBridge.create("lunar", "icons/assets/cosmetic-28x28.png"));
      this.field25.method17("btnCosmetics", new Object[0]);
      this.field26.setTextColor(-1);
      this.field26.method12(new AnimatedValue(805306368, 1342177280));
      this.field26.method11(new AnimatedValue(1076176165, -1711276033));
      this.field26.method18(2.0F);
      this.field26.method7(ResourceLocationBridge.create("lunar", "icons/assets/emote-28x28.png"));
      this.field26.method17("btnEmotes", new Object[0]);
      this.field23.setActive(false);
      this.field23.method18(new AnimatedValue(805306368, 1342177280));
      this.field23.method17(new AnimatedValue(1426128895, 1426128895));
      this.field23.method6(1.5F);
      this.field20.addAll(this.field21.method1());
      this.field20.addAll(List.of(this.field24, this.field25, this.field26, this.field23));
   }

   @Override
   public boolean method3(MarkerModel.Data2 var1) {
      return this.field24.method3(var1) || this.field26.method3(var1) || this.field25.method3(var1);
   }

   @Override
   public List<EditorShortcut> method5() {
      return field20;
   }

   @Override
   public String getLanguagePath() {
      return super.getLanguagePath() + ".movement";
   }

   @Override
   protected List<GuiWidget> method25() {
      return List.of();
   }

   @Override
   public void init() {
      this.field23.method2(32.0F, this.method23() - 28.0F, 24.0F, 24.0F);
      this.field24
         .method2(
            this.method22() / 2.0F - 50.0F, this.method23() / 2.0F - 14.0F, 100.0F, 28.0F
         );
      this.field25
         .method2(
            this.method22() / 2.0F + 50.0F + 4.0F, this.method23() / 2.0F - 14.0F, 28.0F, 28.0F
         );
      this.field26
         .method2(
            this.method22() / 2.0F - 50.0F - 4.0F - 28.0F, this.method23() / 2.0F - 14.0F, 28.0F, 28.0F
         );
      this.field22
         .method2(
            this.method22() / 2.0F - 32.0F, this.method23() / 2.0F - 80.0F, 64.0F, 58.5F
         );
      this.field21.init();
      ThreadModuleDump20.onOpen.run();
   }

   @Override
   public void update() {
      this.field21.update();
   }

   @Override
   public void method10(MixinHelper_4 var1, MarkerModel.Data2 var2) {
      this.field21.method2(var1, var2);
      InternalSettings var3 = ThreadModuleDump63.method4().method41().method9();
      if (this.field26.method13() && var3.method23()) {
         com.moonsworth.lunar.client.ui.LcuiScreen.method70(var1, this.field26);
      }

      if (this.field25.method13() && var3.method16()) {
         com.moonsworth.lunar.client.ui.LcuiScreen.method70(var1, this.field25);
      }

      this.method9(var1);
   }

   @Override
   public void method11(MarkerModel.Data2 var1, int var2) {
      if (!this.field23.method3(var1)) {
         this.field21.method3(var1, var2);
      }
   }

   @Override
   public void method12(MarkerModel.Data2 var1, int var2) {
      this.field21.method4(var1, var2);
   }

   @Override
   public void method14(char var1, KeyCode var2) {
      this.field21.method5(var1, var2);
   }

   @Override
   public void close() {
      ThreadModuleDump20.onClose.run();
   }

   public void method9() {
      this.field21.method10();
   }

   private void method9(MixinHelper_4 var1) {
      var1.push();
      ColorOption var2 = ThreadModuleDump63.method4().method41().method6().method66();
      ColorAnimation var3 = this.field21.method41();
      if (var3.method1() > 0.2F) {
         int var4 = ThreadModuleDump23.method26(var2.method1(0.0F), var3.method1());
         String var5 = "LUNAR";
         float var6 = FontRegistry.field17.method4(var5);
         FontRegistry.field17
            .method13(
               var1,
               var5,
               this.method22() / 2.0F - var6 - 2.0F + 1.0F,
               this.method23() / 2.0F - 40.0F + 1.0F,
               new Color(0.0F, 0.0F, 0.0F, 0.4F * var3.method1()).getRGB()
            );
         FontRegistry.field17
            .method13(var1, var5, this.method22() / 2.0F - var6 - 2.0F, this.method23() / 2.0F - 40.0F, var4);
         FontRegistry.field18
            .method13(
               var1,
               "CLIENT",
               this.method22() / 2.0F + 2.0F + 1.0F,
               this.method23() / 2.0F - 40.0F + 1.0F,
               new Color(0.0F, 0.0F, 0.0F, 0.4F * var3.method1()).getRGB()
            );
         FontRegistry.field18
            .method13(var1, "CLIENT", this.method22() / 2.0F + 2.0F, this.method23() / 2.0F - 40.0F, var4);
      }

      var1.method38(0.0F, -20.0F * var3.method1(), 0.0F);
      this.field22.method3(var1, new MarkerModel.Data2(0.0, 0.0), true);
      var1.pop();
   }

   private final class Data extends TextLabelWidget {
      private Data() {
         super(null, "", FontRegistry.field16);
      }

      @Override
      public boolean method1(MarkerModel.Data2 var1) {
         return HudEditorScreen.this.field21.method43() == null && super.method3(var1);
      }
   }
}
