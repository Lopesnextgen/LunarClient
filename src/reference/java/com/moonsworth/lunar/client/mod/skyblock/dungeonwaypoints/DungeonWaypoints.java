package com.moonsworth.lunar.client.mod.skyblock.dungeonwaypoints;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework11;
import com.moonsworth.lunar.client.framework.mod.Framework2;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.command.MixinNameplate2;
import com.moonsworth.lunar.client.command.MixinNameplateImpl;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing2_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3_4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType_3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Holograms4Updater;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.gui.mixin.Bridge7Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.gui.mixin.Gui;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.HighlightType;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler22_2;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler23_2;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler2_2;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.Dungeonwaypoints;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.Dungeonwaypoints2;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.Dungeonwaypoints3;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.Dungeonwaypoints4;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints5;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Gui2Extension;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Gui2Extension2;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase$Data3;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase$Data4;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightImpl2;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle;
import com.moonsworth.lunar.client.event.mixin.highlight.EntitiesRenderEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.config.option.KeyCombo;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.DynamicDropdownOption;
import com.moonsworth.lunar.client.config.option.OptionSupplier;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class DungeonWaypoints extends AbstractFeature {
   private static final Calculator2 field8 = () -> "settings";
   private static final String field9 = "dungeonWaypointsWriter";
   private final GuiRewindhandlersHandler2_2 field10 = (GuiRewindhandlersHandler2_2)this.method15(GuiRewindhandlersHandler2_2.class);
   private final GuiRewindhandlersHandler23_2 field11 = (GuiRewindhandlersHandler23_2)this.method15(GuiRewindhandlersHandler23_2.class);
   private final GuiRewindhandlersHandler22_2 field12 = (GuiRewindhandlersHandler22_2)this.method15(GuiRewindhandlersHandler22_2.class);
   private final ModifierKeybindOption field13 = (ModifierKeybindOption)((ModifierKeybindOption.Data)((ModifierKeybindOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method18(
               "dungeonWaypointEditModeKeybind"
            )
            .method2(new KeyCombo(true, false, false, KeyCode.KEY_W)))
         .method18(this))
      .method31();
   private final ModifierKeybindOption field14 = (ModifierKeybindOption)((ModifierKeybindOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method18(
            "dungeonWaypointKeybind"
         )
         .method5(KeyCode.KEY_C)
         .method18(this))
      .method11()
      .method31();
   private final ModifierKeybindOption field15 = (ModifierKeybindOption)((ModifierKeybindOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method18(
            "dungeonWaypointClearKeybind"
         )
         .method5(KeyCode.KEY_NONE)
         .method18(this))
      .method11()
      .method31();
   private final ModifierKeybindOption field16 = (ModifierKeybindOption)((ModifierKeybindOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method18(
            "dungeonWaypointScrollAdjustKeybind"
         )
         .method5(KeyCode.KEY_MOUSE3)
         .method18(this))
      .method11()
      .method31();
   private final DynamicDropdownOption field17 = (DynamicDropdownOption)((DynamicDropdownOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method19(
            "dungeonWaypointPreset"
         )
         .method2("default"))
      .method4(this::method14)
      .method5(this::method22)
      .method31();
   private final EnumOption<Gui2Extension2> field18 = (EnumOption<Gui2Extension2>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "dungeonWaypointRenderMode", Gui2Extension2.BOTH
      )
      .method31();
   private final EnumOption<Gui2Extension3> field19 = (EnumOption<Gui2Extension3>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "dungeonWaypointWhen", Gui2Extension3.BOTH
      )
      .method31();
   private final ToggleOption field20 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "dungeonWaypointIgnoreWhen"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   private final EnumOption<Gui2Extension> field21 = (EnumOption<Gui2Extension>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "dungeonWaypointBoxMode", Gui2Extension.FULL
      )
      .method31();
   private final ColorOption field22 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "dungeonWaypointFillColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1140915968))
      .method31();
   private final ColorOption field23 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "dungeonWaypointWireframeColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(ThreadModuleDump23.method22(1140915968, 255)))
      .method31();
   private final ToggleOption field24 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "dungeonWaypointThroughWalls"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   private final FloatOption field25 = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "dungeonWaypointOffsetX"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.0F))
         .method8(0.0F, 1.0F))
      .method31();
   private final FloatOption field26 = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "dungeonWaypointOffsetY"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.0F))
         .method8(0.0F, 1.0F))
      .method31();
   private final FloatOption field27 = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "dungeonWaypointOffsetZ"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.0F))
         .method8(0.0F, 1.0F))
      .method31();
   private final FloatOption field28 = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "dungeonWaypointSizeX"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 1.0F))
      .method31();
   private final FloatOption field29 = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "dungeonWaypointSizeY"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 1.0F))
      .method31();
   private final FloatOption field30 = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "dungeonWaypointSizeZ"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 1.0F))
      .method31();
   private final ToggleOption field31 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "dungeonWaypointShowBoss"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field32 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
               "dungeonWaypointHideSecrets"
            )
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
         .method17(
            () -> !((Framework4)this.method7(Framework.field16)).<Skyblock>method1().method112().isEnabled()
         ))
      .method31();
   private final Dungeonwaypoints3 field33 = new Dungeonwaypoints3();
   private final Dungeonwaypoints field34 = new Dungeonwaypoints(this.field33);
   private final Dungeonwaypoints4 field35 = new Dungeonwaypoints4();
   private final Dungeonwaypoints2 field36 = new Dungeonwaypoints2(this);
   private Holograms4Iterator field37;
   private boolean field38 = true;
   @Nullable
   private JsonObject field39;
   private boolean field40;

   public DungeonWaypoints(Skyblock var1) {
      super(false);
      this.method51(Framework.field16, Framework4.method3(var1));
      this.method51(Framework.field17, Framework2.method2(SettingsPage.DUNGEONS));
      this.method51(
         Framework.field19,
         Framework11.method1(this, () -> Click3.getIsland() == com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3.DUNGEON)
      );
      this.method27(new DungeonWaypoints.Data());
      this.field33.load();
      this.method30();
      Gui var2 = new Gui(this);
      this.method51(Framework.field1, Nameplate.method4(var2));
      this.handle(HighlightImpl2.class, var1x -> this.field34.method2());
      this.handle(HighlightBase$Data4.class, this::method4);
      this.handle(HighlightBase$Data3.class, this::method5);
      this.handle(EntitiesRenderEvent.class, this.field36::method1);
      this.handle(HudRenderLegacyEventAlt.class, this.field36::method2);
      this.handle(EventWorldLifecycle.EventWorldChanged.class, this::method6);
      this.handle(com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseScrollLegacy.class, var2x -> {
         if (!var2.method17()) {
            this.field35.method5(var2x.method1());
         }
      });
      ClientEventBus.method29().method2(com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick.class, var1x -> this.field33.method25());
      this.field13.method3(this::method22);
      this.field14.method3(this::method24);
      this.field15.method3(() -> {
         if (this.field35.method7()) {
            this.method25();
         }
      });
      this.field16.method3(this::method23);
      this.field17.HORHIRROCIOIICIOHCOCCOOHIRCCRI(this::method20);
      this.method1(this.field25, this.field28);
      this.method1(this.field26, this.field29);
      this.method1(this.field27, this.field30);

      for (ClientOption var4 : this.method13()) {
         var4.method9(this::method28);
      }

      this.RCIOICOHRIOIIRRRROCRHCIICRROHO(this::onEnable);
      this.OHROCHICOIOICHOCRROORRCIIICIHO(this::onDisable);
   }

   private void method1(FloatOption var1, FloatOption var2) {
      var1.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var2x -> {
         if (!this.field38 && var2x + var2.get() > 1.0F) {
            var2.method1(1.0F - var2x);
         }
      });
      var2.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var2x -> {
         if (!this.field38 && var1.get() + var2x > 1.0F) {
            var1.method1(1.0F - var2x);
         }
      });
   }

   private List<ClientOption<?>> method13() {
      return List.of(
         this.field18,
         this.field19,
         this.field21,
         this.field22,
         this.field23,
         this.field24,
         this.field25,
         this.field26,
         this.field27,
         this.field28,
         this.field29,
         this.field30
      );
   }

   private List<String> method14() {
      return this.field33.method20().stream().map(Dungeonwaypoints5::name).toList();
   }

   private void onEnable() {
      this.field34.method2();
      this.field37 = this.field10.method5().map(Holograms2_5::method29).map(Holograms4Updater::method7).orElse(null);
   }

   private void onDisable() {
      this.field37 = null;
      this.field35.reset();
   }

   private void method4(HighlightBase$Data4 var1) {
      this.field37 = this.field10.method5().map(Holograms2_5::method29).map(Holograms4Updater::method7).orElse(null);
   }

   private void method5(HighlightBase$Data3 var1) {
      this.field37 = null;
   }

   private void method6(EventWorldLifecycle.EventWorldChanged var1) {
      this.field37 = null;
      this.field35.reset();
   }

   public Optional<Holograms3> method15() {
      return this.field37 == null ? Optional.empty() : this.field37.method23().filter(var0 -> var0.method26() != null);
   }

   @Nullable
   Holograms4Iterator method16() {
      return this.field37;
   }

   public boolean method17() {
      return this.field11.method10() && this.method19() >= 1;
   }

   public int method19() {
      HighlightType var1 = this.field12.method6();
      return var1.isBossFloor() ? var1.getNumber() : -1;
   }

   public boolean method21() {
      if (this.isEnabled() && this.field32.get()) {
         Holograms3 var1 = this.method15().orElse(null);
         return var1 == null ? false : !this.field33.method2(var1.method26().getBlcID(), var1.method26().communityName()).isEmpty();
      } else {
         return false;
      }
   }

   private void method22() {
      if (this.field35.method7() || Click3.getIsland() == com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3.DUNGEON) {
         this.field35.method1();
      }
   }

   private void method23() {
      if (this.field35.method7()) {
         this.field35.method2();
      }
   }

   private void method24() {
      if (this.field35.method7()) {
         Vector3iBridge var1 = this.field35.method6();
         if (var1 != null) {
            if (this.method17()) {
               int var7 = this.method19();
               com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints var8 = this.method16(
                  this.field33.method15(var7), var1
               );
               if (var8 != null) {
                  this.field33.method17(var7, var8);
               } else {
                  this.field33
                     .method16(
                        var7,
                        new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints(
                           var1, this.method15(var1)
                        )
                     );
               }

               this.field35.method3(var1);
            } else {
               Holograms3 var2 = this.method15().orElse(null);
               if (var2 != null && this.field37.contains(var1.bridge$getX(), var1.bridge$getZ())) {
                  Vector3iBridge var3 = var2.method13(var1);
                  String var4 = var2.method26().getBlcID();
                  String var5 = var2.method26().communityName();
                  com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints var6 = this.method16(
                     this.field33.method2(var4, var5), var3
                  );
                  if (var6 != null) {
                     this.field33.method4(var4, var5, var6);
                  } else {
                     this.field33
                        .method3(
                           var4,
                           var5,
                           new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints(
                              var3, this.method15(var1)
                           )
                        );
                  }

                  this.field35.method3(var1);
               } else {
                  Fishing2_2.method1(this.method27("outsideRoom", new Object[0]));
               }
            }
         }
      }
   }

   public com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints4 method15(Vector3iBridge var1) {
      Dungeonwaypoints5 var2 = this.field33.method22();
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints4 var3 = var2.method3();
      if (var2.method4() != Gui2Extension.HITBOX) {
         return var3.method3();
      }

      Itemcounter6Extension var4 = ThreadModuleDump63.method8();
      Bridge3_23 var5 = var4 == null ? null : var4.bridge$getBlockAt(var1.bridge$getX(), var1.bridge$getY(), var1.bridge$getZ());
      AxisAlignedBBBridge var6 = var5 != null && !var5.bridge$isAir() ? var5.bridge$getAABB(var4, var1) : null;
      if (var6 == null) {
         return var3.method2(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }

      HologramsType_3 var7 = this.method17() ? null : this.method15().map(Holograms3::method28).orElse(null);
      float[] var8 = Holograms3_4.method3(var6.bridge$getMinX(), var6.bridge$getMaxX(), var6.bridge$getMinZ(), var6.bridge$getMaxZ(), var7);
      return var3.method2(var8[0], (float)var6.bridge$getMinY(), var8[2], var8[1], (float)(var6.bridge$getMaxY() - var6.bridge$getMinY()), var8[3]);
   }

   @Nullable
   private Dungeonwaypoints method16(
      List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints> var1, Vector3iBridge var2
   ) {
      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints var4 : var1) {
         if (var4.method7().bridge$getX() == var2.bridge$getX()
            && var4.method7().bridge$getY() == var2.bridge$getY()
            && var4.method7().bridge$getZ() == var2.bridge$getZ()) {
            return var4;
         }
      }

      return null;
   }

   private boolean method25() {
      if (Click3.getIsland() != com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3.DUNGEON) {
         return false;
      }

      if (this.method17()) {
         if (!this.field33.method7(this.method19())) {
            return false;
         }

         ThreadModuleDump63.method4().method69().method3(NotificationManager.method15("clearedDungeonWaypoints"));
         return true;
      } else {
         Holograms3 var1 = this.method15().orElse(null);
         if (var1 == null) {
            return false;
         }

         if (!this.field33.method6(var1.method26().getBlcID(), var1.method26().communityName())) {
            return false;
         }

         ThreadModuleDump63.method4().method69().method3(NotificationManager.method15("clearedDungeonWaypoints"));
         return true;
      }
   }

   private void method26() {
      Fishing2_2.method1(this.method27("usage", new Object[0]));
   }

   private void method27() {
      int var1 = this.field33.method14();
      if (var1 == 0) {
         Fishing2_2.method1(this.method27("nothingToDelete", new Object[0]));
      } else {
         Fishing2_2.method1(this.method27("deletedAll", new Object[]{var1}));
      }
   }

   private void method20(String var1) {
      if (!this.field38) {
         this.field33.method23(var1);
         this.method30();
         this.method27(this.field33.method22());
      }
   }

   public void method21(String var1) {
      this.field17.method10(var1);
   }

   public String method22(String var1) {
      return switch (var1) {
         case "default" -> this.method25("dungeonWaypointPresetDefault");
         case "mining" -> this.method25("dungeonWaypointPresetMining");
         case "superboom" -> this.method25("dungeonWaypointPresetSuperboom");
         case "etherwarp" -> this.method25("dungeonWaypointPresetEtherwarp");
         case "pearl" -> this.method25("dungeonWaypointPresetPearl");
         default -> var1;
      };
   }

   private String method25(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
      return field8.method1(var1);
   }

   private void method28() {
      if (!this.field38) {
         this.field33.method24(this.field33.method22().method1(this.method29()).method2(this.field21.get()));
      }
   }

   public com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints4 method29() {
      return new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints4(
         this.field18.get(),
         this.field19.get(),
         this.field22.getColor(),
         this.field23.method13(),
         this.field24.get(),
         this.field25.get(),
         this.field26.get(),
         this.field27.get(),
         this.field28.get(),
         this.field29.get(),
         this.field30.get()
      );
   }

   private void method30() {
      Dungeonwaypoints5 var1 = Dungeonwaypoints3.method1(this.field33.method40()).orElseGet(() -> Dungeonwaypoints3.method1("default").orElseThrow());
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints4 var2 = var1.method3();
      this.field18.HIRIHCROOIRIORCCOIRRCRHOHCCRRO(var2.method9());
      this.field19.HIRIHCROOIRIORCCOIRRCRHOHCCRRO(var2.method10());
      this.field21.HIRIHCROOIRIORCCOIRRCRHOHCCRRO(var1.method4());
      this.field22.method6(var2.method11());
      this.field23.method6(var2.method12());
      this.field24.method6(var2.method13());
      this.field25.method6(var2.method14());
      this.field26.method6(var2.method15());
      this.field27.method6(var2.method16());
      this.field28.method6(var2.method17());
      this.field29.method6(var2.method18());
      this.field30.method6(var2.method19());
   }

   private void method27(Dungeonwaypoints5 var1) {
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints4 var2 = var1.method3();
      this.field38 = true;

      try {
         this.field18.OIRHOOIICOCIOOHICRRRICORIHHIHC(var2.method9());
         this.field19.OIRHOOIICOCIOOHICRRRICORIHHIHC(var2.method10());
         this.field21.OIRHOOIICOCIOOHICRRRICORIHHIHC(var1.method4());
         this.field22.method1(Integer.valueOf(var2.method11()));
         this.field23.method1(Integer.valueOf(var2.method12()));
         this.field24.method10(var2.method13());
         this.field25.method1(var2.method14());
         this.field26.method1(var2.method15());
         this.field27.method1(var2.method16());
         this.field28.method1(var2.method17());
         this.field29.method1(var2.method18());
         this.field30.method1(var2.method19());
      } finally {
         this.field38 = false;
      }
   }

   @Override
   public void method1(JsonObject var1) {
      this.field34.method2();
      JsonObject var2;
      if (this.field34.method22()) {
         var2 = this.method34();
         var1.addProperty("dungeonWaypointsWriter", 1);
      } else {
         var2 = this.field39 == null ? new JsonObject() : this.field39;
         if (this.field40) {
            var1.addProperty("dungeonWaypointsWriter", 1);
         }
      }

      var1.add("dungeonWaypoints", var2);
      super.method1(var1);
   }

   private JsonObject method34() {
      JsonObject var1 = new JsonObject();

      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var3 : this.field33.method13()) {
         String var4 = var3.communityName();
         if (var4 != null && !var3.method4().isEmpty()) {
            JsonArray var5 = var1.has(var4) ? var1.getAsJsonArray(var4) : new JsonArray();

            for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints var7 : var3.method4()) {
               var5.add(var7.method6());
            }

            var1.add(var4, var5);
         }
      }

      return var1;
   }

   @Override
   public void load(JsonObject var1) {
      this.field38 = true;
      super.load(var1);
      this.field39 = null;
      this.field40 = false;
      if (var1.isJsonObject() && var1.getAsJsonObject().has("dungeonWaypoints") && var1.getAsJsonObject().get("dungeonWaypoints").isJsonObject()) {
         this.field39 = var1.getAsJsonObject().getAsJsonObject("dungeonWaypoints");
         this.field40 = var1.getAsJsonObject().has("dungeonWaypointsWriter");
      }

      this.field34.method1(this.field39, this.field40);
      this.field17.method10(this.field33.method40());
      this.method30();
      this.method27(this.field33.method22());
   }

   @Override
   protected boolean method23(String var1) {
      return var1.equals("dungeonWaypointColor") || var1.equals("dungeonWaypointShowThroughWalls");
   }

   @Override
   public String getId() {
      return "DUNGEON_WAYPOINTS";
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      ((SettingsSectionImpl)var1.CHHOORIOCHRICORCCCOHCOHIHIHHRH("dungeonWaypointDeleteAllHelp")).method4(true);
      var1.method11(
         new ClientOption[]{
            this.field13,
            this.field14,
            this.field15,
            this.field16,
            this.field17,
            this.field18,
            this.field19,
            this.field21,
            this.field22,
            this.field23,
            this.field24,
            this.field25,
            this.field26,
            this.field27,
            this.field28,
            this.field29,
            this.field30,
            this.field31,
            this.field20,
            this.field32
         }
      );
      var1.method11(
         new OptionSupplier[]{
            com.moonsworth.lunar.client.config.option.OptionFactory.method14("dungeonWaypointClearRoom").method4(this::method25),
            com.moonsworth.lunar.client.config.option.OptionFactory.method14("dungeonWaypointShare").method4(() -> Bridge7Iterator.method1(this))
         }
      );
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7().method1(Calculator2Handler.field5).method11(this);
   }

   @Generated
   public EnumOption<Gui2Extension2> method35() {
      return this.field18;
   }

   @Generated
   public EnumOption<Gui2Extension3> method36() {
      return this.field19;
   }

   @Generated
   public ToggleOption method37() {
      return this.field20;
   }

   @Generated
   public EnumOption<Gui2Extension> method38() {
      return this.field21;
   }

   @Generated
   public ColorOption method39() {
      return this.field22;
   }

   @Generated
   public ColorOption method40() {
      return this.field23;
   }

   @Generated
   public ToggleOption method41() {
      return this.field24;
   }

   @Generated
   public FloatOption method42() {
      return this.field25;
   }

   @Generated
   public FloatOption method43() {
      return this.field26;
   }

   @Generated
   public FloatOption method44() {
      return this.field27;
   }

   @Generated
   public FloatOption method45() {
      return this.field28;
   }

   @Generated
   public FloatOption method46() {
      return this.field29;
   }

   @Generated
   public FloatOption method47() {
      return this.field30;
   }

   @Generated
   public ToggleOption method48() {
      return this.field31;
   }

   @Generated
   public Dungeonwaypoints3 method49() {
      return this.field33;
   }

   @Generated
   public Dungeonwaypoints4 method50() {
      return this.field35;
   }

   private final class Data extends MixinNameplate2 {
      private Data() {
         super(
            MixinNameplateImpl.method1("lcdwp")
               .method3(var1x -> DungeonWaypoints.this.method26())
               .method2(MixinNameplateImpl.method1("deleteall").method3(var1x -> DungeonWaypoints.this.method27()))
         );
      }

      @Override
      public boolean isEnabled() {
         return DungeonWaypoints.this.isEnabled() && Click3.hasIsland();
      }
   }
}
