package com.moonsworth.lunar.client.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.ui.hud.HudEditorScreen;
import com.moonsworth.lunar.client.cosmetics.SprayPlacementImpl;
import com.moonsworth.lunar.client.cosmetics.SprayPlacementTracker;
import com.moonsworth.lunar.client.cosmetics.SprayEntry;
import com.moonsworth.lunar.client.cosmetics.emote.Emote;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.KeyCombo;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.SettingsTreeAssembler;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption.Data;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.PhosphorIconLegacy;
import com.moonsworth.lunar.client.driver.core.gui.Gui;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import com.moonsworth.lunar.client.mixin.EntityRenderer4;
import com.moonsworth.lunar.client.mixin.EntityRendererType2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.cosmetics.SprayManager;

public class ControlsSettings extends com.moonsworth.lunar.client.config.SettingsContainer implements Gui, JsonProviderLegacy {
   private final SimpleKeybindOption field2 = (SimpleKeybindOption)((Data)((Data)OptionFactory.method17("modMenu")
            .method2(KeyCode.KEY_RSHIFT))
         .method12()
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final SimpleKeybindOption field3 = (SimpleKeybindOption)((Data)((Data)((Data)OptionFactory.method17("waypointsKeybind")
               .method2(KeyCode.KEY_M))
            .method12()
            .method15(PhosphorIconLegacy.PI_PLUS_SQUARE_STROKE))
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final SimpleKeybindOption field4 = (SimpleKeybindOption)((Data)((Data)OptionFactory.method17("emoteWheelKeybind")
            .method2(KeyCode.KEY_B))
         .method12()
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final SimpleKeybindOption field5 = (SimpleKeybindOption)((Data)((Data)OptionFactory.method17("sprayWheelKeybind")
            .method2(KeyCode.KEY_Y))
         .method12()
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final SimpleKeybindOption field6 = (SimpleKeybindOption)((Data)OptionFactory.method17("spraySnapKeybind")
         .method12()
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ToggleOption field7 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("sprayAlwaysSnap").method4(true))
      .method31();
   private final ModifierKeybindOption field8 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "dropStack"
         )
         .method5(KeyCode.KEY_LCONTROL)
         .method11()
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field9 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "friendMenuKey"
         )
         .method2(KeyCombo.method3(KeyCode.KEY_TAB)))
      .method31();
   private final ModifierKeybindOption field10 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "emote1Keybind"
         )
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field11 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "emote2Keybind"
         )
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field12 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "emote3Keybind"
         )
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field13 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "emote4Keybind"
         )
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field14 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "emote5Keybind"
         )
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field15 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "emote6Keybind"
         )
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field16 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "emote7Keybind"
         )
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field17 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "emote8Keybind"
         )
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field18 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "spray1Keybind"
         )
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field19 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "spray2Keybind"
         )
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field20 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "spray3Keybind"
         )
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field21 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "spray4Keybind"
         )
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field22 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "spray5Keybind"
         )
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field23 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "spray6Keybind"
         )
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field24 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "spray7Keybind"
         )
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field25 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "spray8Keybind"
         )
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field26 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "removeSprayKeybind"
         )
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();

   @Override
   protected void method10(SettingsTreeAssembler var1) {
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
         new ClientOption[]{
            this.field2,
            this.field3,
            this.field8,
            this.field9,
            ThreadModuleDump63.method4().method40().method51().method15(),
            ThreadModuleDump63.method4().method40().method20().method24(),
            ThreadModuleDump63.method4().method40().method20().method25()
         }
      );
      var1.method1(
         "emoteKeys",
         var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
            new ClientOption[]{
               this.field4, this.field10, this.field11, this.field12, this.field13, this.field14, this.field15, this.field16, this.field17
            }
         )
      );
      var1.method1(
         "sprayKeys",
         var1x -> {
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
               new ClientOption[]{
                  this.field5,
                  this.field18,
                  this.field19,
                  this.field20,
                  this.field21,
                  this.field22,
                  this.field23,
                  this.field24,
                  this.field25,
                  this.field26,
                  this.field7
               }
            );
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field6}).method3(this.field7::get);
         }
      );
      this.field2.method3(() -> ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(new HudEditorScreen())));
      this.field10.method3(this.method4(0));
      this.field11.method3(this.method4(1));
      this.field12.method3(this.method4(2));
      this.field13.method3(this.method4(3));
      this.field14.method3(this.method4(4));
      this.field15.method3(this.method4(5));
      this.field16.method3(this.method4(6));
      this.field17.method3(this.method4(7));
      this.field18.method3(this.method5(0)).method2(this.method6(0));
      this.field19.method3(this.method5(1)).method2(this.method6(1));
      this.field20.method3(this.method5(2)).method2(this.method6(2));
      this.field21.method3(this.method5(3)).method2(this.method6(3));
      this.field22.method3(this.method5(4)).method2(this.method6(4));
      this.field23.method3(this.method5(5)).method2(this.method6(5));
      this.field24.method3(this.method5(6)).method2(this.method6(6));
      this.field25.method3(this.method5(7)).method2(this.method6(7));
      this.field4
         .method3(
            () -> {
               if (!ThreadModuleDump63.method4().method40().method64().method13()
                  && !ThreadModuleDump63.method5().isEmpty()
                  && ((EntityRenderer4)ThreadModuleDump63.method5().get()).method112() == EntityRendererType2.READY
                  && !Bridge.method18().method1(KeyCode.KEY_F3)) {
                  com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50().method16(DriverRouteRegistryLegacy.field14);
               }
            }
         );
      this.field5
         .method3(
            () -> {
               if (!ThreadModuleDump63.method4().method40().method64().method13()
                  && !ThreadModuleDump63.method5().isEmpty()
                  && ((EntityRenderer4)ThreadModuleDump63.method5().get()).method112() == EntityRendererType2.READY
                  && !Bridge.method18().method1(KeyCode.KEY_F3)) {
                  com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50().method16(DriverRouteRegistryLegacy.field15);
               }
            }
         );
      this.field26.method3(() -> ThreadModuleDump63.method4().method46().method5());
      this.field3.method3(() -> {
         if (ThreadModuleDump63.method8() != null) {
            com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50().method16(DriverRouteRegistryLegacy.field17);
         }
      });
   }

   public boolean method40() {
      return (Boolean)this.field7.get() || this.field6.isDown();
   }

   @Override
   public String method5() {
      return "controls.json";
   }

   private Runnable method4(int var1) {
      return () -> ThreadModuleDump63.method4()
         .method45()
         .method25()
         .stream()
         .filter(var1x -> var1x.getSlotId() == var1)
         .findFirst()
         .ifPresentOrElse(var0x -> {
            int var1x = var0x.getEmoteId();
            Emote var2 = ThreadModuleDump63.method4().method45().method13(var1x);
            ThreadModuleDump63.method4().method45().method3(var1x, var2.getMetadata(), var0x.getJamId());
         }, () -> ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, "No emote selected in slot " + (var1 + 1)));
   }

   private Runnable method5(int var1) {
      return () -> {
         if (!(Boolean)ThreadModuleDump63.method4().method41().method6().method35().get()) {
            ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, "Sprays are disabled, you must enable sprays first");
         } else if (ThreadModuleDump63.method4().method41().method6().method8(ThreadModuleDump63.method7())) {
            SprayManager var1x = ThreadModuleDump63.method4().method46();
            var1x.method42()
               .stream()
               .filter(var1xx -> var1xx.getSlotNumber() == var1)
               .findFirst()
               .ifPresentOrElse(
                  var1xx -> {
                     SprayEntry var2 = var1x.method5(var1xx.getSprayId());
                     if (var2 != null) {
                        var1x.method16(
                           false,
                           true,
                           (var2x, var3, var4, var5, var6, var7, var8) -> var1x.method6(var2, var6, var4, var3, var8),
                           ThreadModuleDump63.method3().bridge$getTimer().method1()
                        );
                     }
                  },
                  () -> ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, "No spray selected in slot " + (var1 + 1))
               );
         }
      };
   }

   private BooleanConsumer method6(int var1) {
      return var1x -> {
         if ((Boolean)ThreadModuleDump63.method4().method41().method6().method35().get()) {
            if (ThreadModuleDump63.method4().method41().method6().method8(ThreadModuleDump63.method7())) {
               SprayManager var2 = ThreadModuleDump63.method4().method46();
               SprayPlacementImpl var3 = var2.method7();
               if (var3 != null) {
                  var2.method42().stream().filter(var1xx -> var1xx.getSlotNumber() == var1).findFirst().ifPresent(var2x -> {
                     SprayEntry var3x = var2.method5(var2x.getSprayId());
                     if (var3x != null) {
                        var2.method16(false, false, (var3xx, var4, var5, var6, var7, var8, var9) -> {
                           float var10 = var3.method6();
                           float var11 = SprayManager.method23(var8 + var10);
                           SprayPlacementTracker var12 = var2.method14(var3x, var7, var5, var11);
                           if (var12 != null) {
                              var2.method2(var4.bridge$getUniqueID(), var12, var2.getMaxActiveSprays(), true);
                           }
                        }, ThreadModuleDump63.method3().bridge$getTimer().method1());
                     }
                  });
               }
            }
         }
      };
   }

   @Nullable
   public JsonElement method128() {
      return this.provide();
   }

   public JsonElement provide() {
      JsonArray var1 = new JsonArray(this.method10().size());

      for (ClientOption var3 : this.method10()) {
         OptionDataProvider var4 = (OptionDataProvider)var3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field10);
         if (var4 != null) {
            var1.add(var4.provide());
         }
      }

      return var1;
   }

   @Generated
   public SimpleKeybindOption method15() {
      return this.field2;
   }

   @Generated
   public SimpleKeybindOption method16() {
      return this.field3;
   }

   @Generated
   public SimpleKeybindOption method17() {
      return this.field4;
   }

   @Generated
   public SimpleKeybindOption method18() {
      return this.field5;
   }

   @Generated
   public SimpleKeybindOption method19() {
      return this.field6;
   }

   @Generated
   public ToggleOption method20() {
      return this.field7;
   }

   @Generated
   public ModifierKeybindOption method21() {
      return this.field8;
   }

   @Generated
   public ModifierKeybindOption method22() {
      return this.field9;
   }

   @Generated
   public ModifierKeybindOption method23() {
      return this.field10;
   }

   @Generated
   public ModifierKeybindOption method24() {
      return this.field11;
   }

   @Generated
   public ModifierKeybindOption method25() {
      return this.field12;
   }

   @Generated
   public ModifierKeybindOption method26() {
      return this.field13;
   }

   @Generated
   public ModifierKeybindOption method27() {
      return this.field14;
   }

   @Generated
   public ModifierKeybindOption method28() {
      return this.field15;
   }

   @Generated
   public ModifierKeybindOption method29() {
      return this.field16;
   }

   @Generated
   public ModifierKeybindOption method30() {
      return this.field17;
   }

   @Generated
   public ModifierKeybindOption method31() {
      return this.field18;
   }

   @Generated
   public ModifierKeybindOption method32() {
      return this.field19;
   }

   @Generated
   public ModifierKeybindOption method33() {
      return this.field20;
   }

   @Generated
   public ModifierKeybindOption method34() {
      return this.field21;
   }

   @Generated
   public ModifierKeybindOption method35() {
      return this.field22;
   }

   @Generated
   public ModifierKeybindOption method36() {
      return this.field23;
   }

   @Generated
   public ModifierKeybindOption method37() {
      return this.field24;
   }

   @Generated
   public ModifierKeybindOption method38() {
      return this.field25;
   }

   @Generated
   public ModifierKeybindOption method39() {
      return this.field26;
   }
}
