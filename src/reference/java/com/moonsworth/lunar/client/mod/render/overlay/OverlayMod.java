package com.moonsworth.lunar.client.mod.render.overlay;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge14_3;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_2;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.calculator.Calculator;
import com.moonsworth.lunar.client.gui.ConfirmScreen;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen;
import com.moonsworth.lunar.client.ui.menu.FeatureSettingsWidget;
import com.moonsworth.lunar.client.render.texture.TextureProcessor;
import com.moonsworth.lunar.client.render.texture.TexturePathResolver;
import com.moonsworth.lunar.client.render.turbo.TurboEngineManager;
import com.moonsworth.lunar.client.config.PerformanceSettings;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework6;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.gui.Gui;
import com.moonsworth.lunar.client.framework.feature.gui.Gui2;
import com.moonsworth.lunar.client.framework.feature.gui.GuiUpdater;
import com.moonsworth.lunar.client.framework.feature.gui.GuiUpdater2;
import com.moonsworth.lunar.client.framework.feature.gui.GuiUpdater3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension2;
import com.moonsworth.lunar.client.event.render.ContainerSlotRenderEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.highlight.EntityRenderBaseEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.AlertUpdateEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.ItemGlintRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.GlintTransformEvent;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.OptionSupplier;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Generated;
import org.jspecify.annotations.Nullable;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class OverlayMod extends AbstractFeature {
   public static final int BARRIER_TEXTURE_SIZE = 64;
   public static final ResourceLocationBridge BARRIER_OUTLINE_TEXTURE = ResourceLocationBridge.create("lunar", "barrier_outline");
   private static final int POWDER_SNOW_OUTLINE_COLOR = -3608326;
   public static volatile Object barrierModel = null;
   private final GuiUpdater2 clearGlass = this.registerUpdater(
      new GuiUpdater2(this, com.moonsworth.lunar.client.config.option.OptionFactory.method7("clearGlass").method31())
   );
   protected final ToggleOption clearColoredGlass = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("clearColoredGlass")
      .method31();
   protected final FloatOption clearGlassTransparency = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "clearGlassTransparency"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.0F))
         .method8(0.0F, 1.0F))
      .method31();
   protected final ToggleOption clearGlassOutline = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("clearGlassOutline")
      .method31();
   protected final IntegerOption clearGlassOutlineThickness = (IntegerOption)((IntegerOption.Data)((IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "clearGlassOutlineThickness"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1))
         .OCRRICRIORICCCRHIOHORCICIHHICO(1, 3))
      .method31();
   protected final FloatOption clearGlassOutlineTransparency = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "clearGlassOutlineTransparency"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 1.0F))
      .method31();
   private final EnumOption<OverlayMod.Type> glintMode = (EnumOption<OverlayMod.Type>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "glint", OverlayMod.Type.ALL
      )
      .method31();
   private final ToggleOption hideFoliage = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideFoliage")
      .method31();
   private final ColorOption stringColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "stringColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-65536))
      .method15()
      .method31();
   private final ToggleOption boldString = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("boldString")
      .method31();
   private final GuiUpdater coloredString = this.createUpdater(
      com.moonsworth.lunar.client.config.option.OptionFactory.method7("coloredString").method31(),
      ThreadModuleDump63.MC_VERSION > 5 ? "block/tripwire" : "blocks/trip_wire",
      var1 -> {
         int var2 = this.stringColor.method13();
         float var3 = this.stringColor.getAlpha() / 255.0F;
         boolean var4 = this.boldString.get();
         int var5 = var1.method2();

         for (int var6 = 0; var6 < var5 * var1.method3(); var6++) {
            int var7 = var6 % var5;
            int var8 = var6 / var5;
            int var9 = var1.method4(var7, var8);
            if (!var4) {
               float var10 = (var9 >> 24 & 0xFF) / 255.0F;
               if (var3 * var10 <= 0.0F) {
                  continue;
               }
            }

            var1.method5(var7, var8, ThreadModuleDump23.method36(var9, var2));
         }
      }
   );
   private final FloatOption fireHeight = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "fireHeight"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 2.0F))
      .method31();
   private final FloatOption fireBlockHeight = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "fireBlockHeight"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 2.0F))
      .method31();
   private final FloatOption shieldHeight = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "shieldHeight"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 2.0F))
      .method31();
   private final ToggleOption minimalViewBobbing = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("minimalViewBobbing")
      .method31();
   private final ToggleOption groundArrows = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "groundArrows"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption stuckArrows = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "stuckArrows"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption hideSkulls = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideSkulls")
      .method31();
   private final ToggleOption hideHelmet = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideHelmet")
      .method31();
   private final ToggleOption hideChest = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideChest")
      .method31();
   private final ToggleOption hideLeggings = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideLeggings")
      .method31();
   private final ToggleOption hideBoots = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideBoots")
      .method31();
   private final ToggleOption selfOnly = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "selfOnly"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<Gui2Extension2> hideEndportals = (EnumOption<Gui2Extension2>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "hideEndportals", Gui2Extension2.NEVER
      )
      .method31();
   private final ToggleOption entityShadow = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "entityShadow"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final FloatOption pumpkinOverlay = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "pumpkinOverlay"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 1.0F))
      .method31();
   private final FloatOption spyglassOverlay = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "spyglassOverlay"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 1.0F))
      .method31();
   private final FloatOption frostOverlay = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "frostOverlay"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 1.0F))
      .method31();
   private final FloatOption fishingBobberOverlay = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "fishingBobberOverlay"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 1.0F))
      .method31();
   @Annotation2(min = 8)
   private final GuiUpdater powderedSnowOutline = ThreadModuleDump63.MC_VERSION < 8
      ? null
      : this.createUpdater(
         com.moonsworth.lunar.client.config.option.OptionFactory.method7("powderedSnowOutline").method31(),
         "block/powder_snow",
         var0 -> GuiUpdater.method3(var0, 1, -3608326, false)
      );
   protected final ToggleOption oreOutlines = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("oreOutlines")
      .method31();
   protected final ToggleOption smartOutlineColors = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "smartOutlineColors"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption shinyOreOutlines = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "shinyOreOutlines"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final IntegerOption oreOutlineThickness = (IntegerOption)((IntegerOption.Data)((IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "oreOutlineThickness"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1))
         .OCRRICRIORICCCRHIOHORCICIHHICO(1, 3))
      .method31();
   private final GuiUpdater3 diamondOreOutline = this.createOreUpdater(
      com.moonsworth.lunar.client.config.option.OptionFactory.method7("diamondOreOutline").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true), "diamond_ore", -9898524, true
   );
   private final GuiUpdater3 goldOreOutline = this.createOreUpdater(
      com.moonsworth.lunar.client.config.option.OptionFactory.method7("goldOreOutline").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true), "gold_ore", -596911, true
   );
   private final GuiUpdater3 ironOreOutline = this.createOreUpdater(
      com.moonsworth.lunar.client.config.option.OptionFactory.method7("ironOreOutline").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true), "iron_ore", -2511472, true
   );
   private final GuiUpdater3 lapisOreOutline = this.createOreUpdater(
      com.moonsworth.lunar.client.config.option.OptionFactory.method7("lapisOreOutline").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true), "lapis_ore", -10911263, true
   );
   private final GuiUpdater3 redstoneOreOutline = this.createOreUpdater(
      com.moonsworth.lunar.client.config.option.OptionFactory.method7("redstoneOreOutline").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true), "redstone_ore", -261888, true
   );
   private final GuiUpdater3 emeraldOreOutline = this.createOreUpdater(
      com.moonsworth.lunar.client.config.option.OptionFactory.method7("emeraldOreOutline").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true), "emerald_ore", -14353558, true
   );
   private final GuiUpdater3 coalOreOutline = this.createOreUpdater(
      com.moonsworth.lunar.client.config.option.OptionFactory.method7("coalOreOutline").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true), "coal_ore", -13224394, true
   );
   @Annotation2(min = 8)
   private final GuiUpdater3 copperOreOutline = ThreadModuleDump63.MC_VERSION < 8
      ? null
      : this.createOreUpdater(
         com.moonsworth.lunar.client.config.option.OptionFactory.method7("copperOreOutline").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true), "copper_ore", -3905962, true
      );
   private final GuiUpdater3 netherQuartzOreOutline = this.createOreUpdater(
      com.moonsworth.lunar.client.config.option.OptionFactory.method7("netherQuartzOreOutline").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true),
      ThreadModuleDump63.MC_VERSION <= 5 ? "quartz_ore" : "nether_quartz_ore",
      -3160651,
      false
   );
   @Annotation2(min = 6)
   private final GuiUpdater3 netherGoldOreOutline = ThreadModuleDump63.MC_VERSION < 6
      ? null
      : this.createOreUpdater(
         com.moonsworth.lunar.client.config.option.OptionFactory.method7("netherGoldOreOutline").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true), "nether_gold_ore", -596911, false
      );
   @Annotation2(min = 6)
   private final GuiUpdater3 ancientDebrisOutline = ThreadModuleDump63.MC_VERSION < 6
      ? null
      : this.createOreUpdater(
         com.moonsworth.lunar.client.config.option.OptionFactory.method7("ancientDebrisOutline").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true),
         "ancient_debris_side",
         -5599611,
         false
      );
   private final ToggleOption overrideXpOrbColor = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("overrideXpOrbColor")
      .method31();
   private final ColorOption xpOrbColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "xpOrbColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-2139029728))
      .method31();
   private final ColorOption xpHotbarTextColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "xpHotbarTextColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-8323296))
      .method18(false)
      .method31();
   private final ToggleOption headlessHorses = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("headlessHorses")
      .method31();
   private final ToggleOption headlessCamels = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("headlessCamels")
      .method31();
   private final ToggleOption headlessLlamas = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("headlessLLamas")
      .method31();
   private final ToggleOption customFishingLine = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("customFishingLine")
      .method31();
   private final FloatOption fishingLineThickness = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "fishingLineThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.5F, 3.0F))
      .method31();
   private final ColorOption fishingLineColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "fishingLineColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16777216))
      .method31();
   private final ToggleOption disableDeathAnimation = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("disableDeathAnimation")
      .method31();
   private final ToggleOption disableDamageOverlay = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("disableDamageOverlay")
      .method31();
   private final ToggleOption disableFireOverlay = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("disableFireOverlay")
      .method31();
   private final ColorOption barrierOutlineColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "barrierOutlineColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1073807360))
      .method15()
      .method31();
   private final IntegerOption barrierOutlineThickness = (IntegerOption)((IntegerOption.Data)((IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "barrierOutlineThickness"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1))
         .OCRRICRIORICCCRHIOHORCICIHHICO(1, 3))
      .method31();
   private final GuiUpdater barrierOutlines = this.createUpdater(
      com.moonsworth.lunar.client.config.option.OptionFactory.method7("barrierOutlines"),
      field9.toString(),
      var1 -> GuiUpdater.method5(var1, this.barrierOutlineThickness.get(), this.barrierOutlineColor.method13(), 240, false)
   );
   private final ToggleOption customGuiColors = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("customGuiColors")
      .method31();
   private final ColorOption containerTint = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "containerTint"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11842741))
      .method31();
   private final ColorOption hotbarTint = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "hotbarTint"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11842741))
      .method31();
   private final ColorOption menuTint = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "menuTint"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11842741))
      .method31();
   private final ColorOption guiTextColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "guiTextColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-2631721))
      .method16()
      .method31();
   private final ColorOption menuTextColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "menuTextColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-2631721))
      .method16()
      .method31();
   private final OverlayTotemAnimation totemAnimationOverlay = new OverlayTotemAnimation(this);
   private final TexturePathResolver guiHandler = (TexturePathResolver)this.method19(TexturePathResolver.class);
   private List<GuiUpdater> updaters;
   private List<GuiUpdater3> oreUpdaters;
   @Annotation2(min = 6)
   private boolean field82;
   private int updateTicks;
   private boolean needsChunkReload;
   private final Set<GuiUpdater> pendingUpdaters = new HashSet<>();

   public OverlayMod() {
      super(false);
      this.method97(EventClientTick.class, this::method16);
      this.method40(ItemGlintRenderEvent.class, this::method15, 125);
      this.handle(EntityRenderBaseEvent.EntityRenderEvent.class, this::method16);
      this.handle(ContainerSlotRenderEvent.ContainerSlotPreEvent.class, this::onGuiTextColor);
      this.method97(ContainerSlotRenderEvent.ContainerSlotPostEvent.class, Gui2::method10);
      this.method40(AlertUpdateEvent.class, var1 -> var1.getFuture().thenRun(() -> this.mc.bridge$submit(() -> {
         Gui.reset();
         this.queueDelayedUpdate(this.updaters, true);
      })), Integer.MIN_VALUE);
      this.handle(GlintTransformEvent.class, var1 -> {
         if (var1.method8().firstPerson() && var1.method7().bridge$getItem() == Bridge.method28().method32()) {
            var1.method1(0.0F, this.shieldHeight.get() - 1.0F, 0.0F);
         }
      });
      this.method40(Framework.field11, Framework6.method3().method2(false));
   }

   @Override
   public String getId() {
      return "OVERLAY_MOD";
   }

   @Override
   protected boolean method23(String var1) {
      return var1.endsWith("Outline");
   }

   @Override
   protected boolean method24(String var1) {
      return var1.equals("TOTEM_ANIMATION_OVERLAY_CHILD");
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7()
         .method2("PackTweaks", "ClearGlass", "Glint", "Foliage", "RedString", "FireHeight", "ShieldHeight", "OreOutline")
         .method1(Calculator2Handler.field3)
         .method11(this);
   }

   @Override
   protected List<Framework7Extension> method9() {
      return List.of(this.totemAnimationOverlay);
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.method1("firstPersonOptions", var1x -> {
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.glintMode, this.minimalViewBobbing, this.fireHeight, this.fireBlockHeight});
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.shieldHeight}).RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(5);
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.pumpkinOverlay});
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.spyglassOverlay, this.frostOverlay}).RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(8);
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.fishingBobberOverlay}).RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(5);
      });
      var1.HOHIHOCHHRCRRIIORHHOROHIROCCCC();
      var1.method1(
         "worldOptions",
         var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
            new ClientOption[]{this.hideFoliage, this.entityShadow, this.groundArrows, this.stuckArrows, this.hideSkulls, this.hideEndportals}
         )
      );
      var1.HOHIHOCHHRCRRIIORHHOROHIROCCCC();
      var1.method1(
         "textureOptions",
         var1x -> {
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.clearGlass.option,
               var1xx -> {
                  var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.clearGlassTransparency, this.clearColoredGlass});
                  var1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                     this.clearGlassOutline, var1xxx -> var1xxx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.clearGlassOutlineThickness, this.clearGlassOutlineTransparency})
                  );
               }
            );
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.oreOutlines,
               var1xx -> {
                  var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
                     new ClientOption[]{
                        this.smartOutlineColors,
                        this.shinyOreOutlines,
                        this.oreOutlineThickness,
                        this.diamondOreOutline.getOption(),
                        this.goldOreOutline.getOption(),
                        this.ironOreOutline.getOption(),
                        this.lapisOreOutline.getOption(),
                        this.redstoneOreOutline.getOption(),
                        this.emeraldOreOutline.getOption(),
                        this.coalOreOutline.getOption(),
                        this.netherQuartzOreOutline.getOption()
                     }
                  );
                  if (ThreadModuleDump63.MC_VERSION >= 6) {
                     var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.netherGoldOreOutline.getOption(), this.ancientDebrisOutline.option})
                        .RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(6);
                  }

                  if (ThreadModuleDump63.MC_VERSION >= 8) {
                     var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.copperOreOutline.getOption()})
                        .RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(8);
                  }
               }
            );
            if (ThreadModuleDump63.MC_VERSION >= 8) {
               var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.powderedSnowOutline.getOption()}).RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(8);
            }

            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.barrierOutlines.getOption(), var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.barrierOutlineThickness, this.barrierOutlineColor})
               )
               .RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(1);
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.coloredString.getOption(), var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.boldString, this.stringColor})
            );
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.overrideXpOrbColor, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.xpOrbColor, this.xpHotbarTextColor})
            );
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.customFishingLine, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.fishingLineThickness, this.fishingLineColor})
            );
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.customGuiColors,
               var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.containerTint, this.hotbarTint, this.menuTint, this.guiTextColor, this.menuTextColor})
            );
         }
      );
      var1.HOHIHOCHHRCRRIIORHHOROHIROCCCC();
      var1.method1("entityOptions", var1x -> {
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.headlessHorses});
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.headlessLlamas}).RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(5);
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.headlessCamels}).RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(17);
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.disableDeathAnimation, this.disableDamageOverlay, this.disableFireOverlay});
      });
      var1.HOHIHOCHHRCRRIIORHHOROHIROCCCC();
      var1.method1(
         "armorOptions",
         var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.hideHelmet, this.hideChest, this.hideLeggings, this.hideBoots, this.selfOnly})
      );
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         var1.HOHIHOCHHRCRRIIORHHOROHIROCCCC();
         var1.method5(SettingsPage.OTHER);
      }

      this.bindOptionListeners();
   }

   @Override
   public boolean method8() {
      return !ThreadModuleDump63.field1;
   }

   private void bindOptionListeners() {
      this.clearColoredGlass.HOCCRCCRCHROIICOOHOHRIICRHCOHR(() -> this.refreshUpdaters(List.of(this.clearGlass)));
      this.clearGlassTransparency.HOCCRCCRCHROIICOOHOHRIICRHCOHR(() -> this.queueDelayedUpdate(List.of(this.clearGlass), false));
      this.clearGlassOutline.HOCCRCCRCHROIICOOHOHRIICRHCOHR(() -> this.queueUpdate(List.of(this.clearGlass), false));
      this.clearGlassOutlineThickness.HOCCRCCRCHROIICOOHOHRIICRHCOHR(() -> this.queueDelayedUpdate(List.of(this.clearGlass), false));
      this.clearGlassOutlineTransparency.HOCCRCCRCHROIICOOHOHRIICRHCOHR(() -> this.queueDelayedUpdate(List.of(this.clearGlass), false));
      this.oreOutlines.HOCCRCCRCHROIICOOHOHRIICRHCOHR(() -> this.refreshUpdaters(this.oreUpdaters));
      this.oreOutlineThickness.HOCCRCCRCHROIICOOHOHRIICRHCOHR(() -> this.queueDelayedUpdate(this.oreUpdaters, false));
      this.smartOutlineColors.HOCCRCCRCHROIICOOHOHRIICRHCOHR(() -> this.queueUpdate(this.oreUpdaters, false));
      this.shinyOreOutlines.HOCCRCCRCHROIICOOHOHRIICRHCOHR(() -> this.queueUpdate(this.oreUpdaters, false));
      this.stringColor.HOCCRCCRCHROIICOOHOHRIICRHCOHR(() -> this.queueDelayedUpdate(List.of(this.coloredString), false));
      this.boldString.HOCCRCCRCHROIICOOHOHRIICRHCOHR(() -> this.queueUpdate(List.of(this.coloredString), false));
      this.hideFoliage.HOCCRCCRCHROIICOOHOHRIICRHCOHR(this::method15);
      this.barrierOutlines.getOption().method9(this::method15);
      this.barrierOutlineThickness.HOCCRCCRCHROIICOOHOHRIICRHCOHR(() -> this.queueDelayedUpdate(List.of(this.barrierOutlines), true));
      this.barrierOutlineColor.HOCCRCCRCHROIICOOHOHRIICRHCOHR(() -> this.queueDelayedUpdate(List.of(this.barrierOutlines), true));
      this.fireBlockHeight.HOCCRCCRCHROIICOOHOHRIICRHCOHR(() -> {
         Gui.method1();
         this.method15();
      });
      if (ThreadModuleDump63.MC_VERSION >= 8) {
         this.groundArrows.HOCCRCCRCHROIICOOHOHRIICRHCOHR(() -> {
            PerformanceSettings var1 = this.field4.method41().method7();
            if (!var1.method37() && var1.method17().get()) {
               this.field4.method89().clear();
            }
         });
         this.hideSkulls.HOCCRCCRCHROIICOOHOHRIICRHCOHR(this::clearChunkCache);
         this.hideEndportals.HOCCRCCRCHROIICOOHOHRIICRHCOHR(this::clearChunkCache);
      }
   }

   @Annotation2(min = 8)
   private void clearChunkCache() {
      TurboEngineManager var1 = this.field4.method89();
      if (var1.method6()) {
         var1.method27().method3();
      }
   }

   @Override
   public void method3(boolean var1) {
      this.refreshUpdaters(this.updaters);
      Gui.method1();
      this.method15();
   }

   void queueUpdate(Collection<? extends GuiUpdater> var1, boolean var2) {
      if (this.isEnabled()) {
         this.pendingUpdaters.addAll(var1);
         this.needsChunkReload |= var2;
         if (this.updateTicks < 1) {
            this.updateTicks = 1;
         }
      }
   }

   private void queueDelayedUpdate(Collection<? extends GuiUpdater> var1, boolean var2) {
      if (this.isEnabled()) {
         this.pendingUpdaters.addAll(var1);
         this.needsChunkReload |= var2;
         this.updateTicks = 10;
      }
   }

   private void refreshUpdaters(Collection<? extends GuiUpdater> var1) {
      this.method71().method13();

      for (GuiUpdater var3 : var1) {
         var3.updateState();
      }

      this.method71().method15();
   }

   private void method15() {
      Bridge14_3 var1 = this.mc.bridge$getLevelRenderer();
      if (var1 != null) {
         var1.bridge$reloadChunks();
      }
   }

   private void method16() {
      if (ThreadModuleDump63.method8() != null) {
         if (this.updateTicks > 0) {
            this.updateTicks--;
            if (this.updateTicks == 0) {
               this.guiHandler.method2(this.pendingUpdaters);
               this.pendingUpdaters.clear();
               if (this.needsChunkReload) {
                  this.needsChunkReload = false;
                  this.method15();
               }
            }
         }
      }
   }

   private void method15(ItemGlintRenderEvent var1) {
      if (this.glintMode.get() == OverlayMod.Type.NONE || this.glintMode.get() == OverlayMod.Type.INVENTORY_ONLY && var1.method2() != ItemGlintRenderEvent.Type.GUI) {
         var1.setCancelled(true);
      }
   }

   private void method16(EntityRenderBaseEvent.EntityRenderEvent var1) {
      if (this.disableDeathAnimation.get() && var1.method1() instanceof BridgeExtension2_5 var2 && var2.bridge$getDeathTime() > 0.0F) {
         var1.setCancelled(true);
      }
   }

   private void onGuiTextColor(ContainerSlotRenderEvent.ContainerSlotPreEvent var1) {
      if (this.customGuiColors.get() && this.isGuiScreen(var1.method3())) {
         Gui2.method9(this.menuTextColor.method14(0.0F));
      }
   }

   private boolean isGuiScreen(@Nullable Bridge5Extension6 var1) {
      return var1 != null && !(var1 instanceof Bridge5Extension_3) && !(var1 instanceof Bridge5Extension62) && !(var1 instanceof Bridge5Extension612);
   }

   public void method19(FeatureSettingsScreen var1) {
      this.mc
         .bridge$displayScreen(
            Bridge.method8()
               .method18(new ConfirmScreen(() -> NotificationManager.method15("overlayModMigration").split("\n"), Calculator.field6, Calculator.field7, var2 -> {
                  if (var2) {
                     var1.method10().method2(new FeatureSettingsWidget(var1.method10(), this));
                  }

                  this.mc.bridge$displayScreen(null);
                  this.mc.bridge$displayScreen(Bridge.method8().method18(var1));
               }))
         );
   }

   public OverlayMod.Type getGlintMode() {
      return this.isEnabled() ? this.glintMode.get() : OverlayMod.Type.ALL;
   }

   public boolean isHideFoliageEnabled() {
      return this.isEnabled() && this.hideFoliage.get();
   }

   public float method23() {
      return this.isEnabled() ? this.fireHeight.get() : 1.0F;
   }

   public boolean method24() {
      return this.isEnabled() && !this.fireBlockHeight.isDefault();
   }

   public boolean method25() {
      return this.isEnabled() && this.overrideXpOrbColor.get();
   }

   public int method25(int var1) {
      return this.method25() ? this.xpHotbarTextColor.method14(0.0F) : var1;
   }

   public boolean isHeadlessHorsesEnabled() {
      return this.isEnabled() && this.headlessHorses.get() && this.mc.bridge$getGameSettings().bridge$getThirdPersonView() == 0;
   }

   public boolean isHeadlessLlamasEnabled() {
      return this.isEnabled() && this.headlessLlamas.get() && this.mc.bridge$getGameSettings().bridge$getThirdPersonView() == 0;
   }

   public boolean isHeadlessCamelsEnabled() {
      return this.isEnabled() && this.headlessCamels.get() && this.mc.bridge$getGameSettings().bridge$getThirdPersonView() == 0;
   }

   public boolean isCustomFishingLineEnabled() {
      return this.isEnabled() && this.customFishingLine.get();
   }

   public float getFishingBobberOverlayOpacity() {
      return this.isEnabled() && this.mc.bridge$getGameSettings().bridge$getThirdPersonView() == 0 ? this.fishingBobberOverlay.get() : 1.0F;
   }

   public float getFishingLineThickness() {
      return this.fishingLineThickness.get() * this.mc.bridge$displayWidth() / 1920.0F * 2.5F;
   }

   public int getFishingLineColor(float var1) {
      return this.fishingLineColor.method14(var1 * 250.0F);
   }

   public boolean isDamageOverlayDisabled() {
      return this.isEnabled() && this.disableDamageOverlay.get();
   }

   public boolean isFireOverlayDisabled() {
      return this.isEnabled() && this.disableFireOverlay.get();
   }

   public boolean shouldRenderEntityShadow() {
      return !this.isEnabled() || this.entityShadow.get();
   }

   public boolean isClearGlassEnabled() {
      return this.isEnabled() && this.clearGlass.option.get();
   }

   public boolean isClearColoredGlassEnabled() {
      return this.isEnabled() && this.clearGlass.option.get() && this.clearColoredGlass.get();
   }

   public boolean method40() {
      return this.isEnabled() && this.hideSkulls.get();
   }

   public boolean method41() {
      return this.isEnabled()
         && (this.hideEndportals.get() == Gui2Extension2.ALWAYS || this.hideEndportals.get() == Gui2Extension2.SKYBLOCK_ONLY && ThreadModuleDump3.method4("Skyblock"));
   }

   private boolean method40(BridgeExtension2_2 var1, ToggleOption var2) {
      return this.isEnabled() && var2.get() && (var1 == null || !this.selfOnly.get() || var1 instanceof EntityPlayerBridge var3 && var3.bridge$isSelf());
   }

   public boolean method41(BridgeExtension2_2 var1) {
      return this.method40(var1, this.hideHelmet);
   }

   public boolean isHideChestEnabled(BridgeExtension2_2 var1) {
      return this.method40(var1, this.hideChest);
   }

   public boolean isHideLeggingsEnabled(BridgeExtension2_2 var1) {
      return this.method40(var1, this.hideLeggings);
   }

   public boolean isHideBootsEnabled(BridgeExtension2_2 var1) {
      return this.method40(var1, this.hideBoots);
   }

   public boolean method45() {
      return !this.isEnabled() || this.stuckArrows.get();
   }

   public boolean shouldRenderGroundArrows() {
      return !this.isEnabled() || this.groundArrows.get();
   }

   public boolean isMinimalViewBobbingEnabled() {
      return this.isEnabled() && this.minimalViewBobbing.get();
   }

   public boolean method48() {
      return this.isEnabled() && this.barrierOutlines.getOption().get();
   }

   public boolean isContainerTintEnabled() {
      return this.isEnabled() && this.customGuiColors.get() && this.containerTint.method13() != -1;
   }

   public boolean isHotbarTintEnabled() {
      return this.isEnabled() && this.customGuiColors.get() && this.hotbarTint.method13() != -1;
   }

   public boolean isMenuTintEnabled() {
      return this.isEnabled() && this.customGuiColors.get() && this.menuTint.method13() != -1 && this.isGuiScreen(this.mc.bridge$getCurrentScreen());
   }

   public boolean isCustomGuiColorsEnabled() {
      return this.isEnabled() && this.customGuiColors.get();
   }

   public int getContainerTint() {
      return this.containerTint.method14(0.0F);
   }

   public int getHotbarTint() {
      return this.hotbarTint.method14(0.0F);
   }

   public int getMenuTint() {
      return this.menuTint.method14(0.0F);
   }

   public int getGuiTextColor() {
      return this.guiTextColor.method14(0.0F);
   }

   public ClientOption<Boolean> getColoredStringOption() {
      return this.coloredString.getOption();
   }

   public ClientOption<Boolean> getClearGlassOption() {
      return this.clearGlass.option;
   }

   private GuiUpdater3 createOreUpdater(OptionSupplier<? extends ClientOption<Boolean>, Boolean> var1, String var2, int var3, boolean var4) {
      GuiUpdater3 var5 = new GuiUpdater3(this, var1, var2, var3, var4);
      if (this.oreUpdaters == null) {
         this.oreUpdaters = new ArrayList<>();
      }

      this.oreUpdaters.add(var5);
      return this.registerUpdater(var5);
   }

   private GuiUpdater createUpdater(OptionSupplier<? extends ClientOption<Boolean>, Boolean> var1, String var2, final TextureProcessor var3) {
      return this.registerUpdater(new GuiUpdater(this, var1, var2) {
         @Override
         public void process(TextureProcessor.Extension var1) {
            var3.process(var1);
         }
      });
   }

   private <H extends GuiUpdater> H registerUpdater(H var1) {
      if (this.updaters == null) {
         this.updaters = new ArrayList<>();
      }

      this.updaters.add(var1);
      return (H)var1;
   }

   @Generated
   public GuiUpdater getColoredStringUpdater() {
      return this.coloredString;
   }

   @Generated
   public FloatOption getFireBlockHeight() {
      return this.fireBlockHeight;
   }

   @Generated
   public FloatOption getPumpkinOverlayOpacity() {
      return this.pumpkinOverlay;
   }

   @Generated
   public FloatOption getSpyglassOverlayOpacity() {
      return this.spyglassOverlay;
   }

   @Generated
   public FloatOption getFrostOverlayOpacity() {
      return this.frostOverlay;
   }

   @Generated
   public ColorOption getXpOrbColor() {
      return this.xpOrbColor;
   }

   @Generated
   public ColorOption getXpHotbarTextColor() {
      return this.xpHotbarTextColor;
   }

   @Generated
   public ColorOption getFishingLineColorOption() {
      return this.fishingLineColor;
   }

   @Generated
   public OverlayTotemAnimation getTotemAnimationOverlay() {
      return this.totemAnimationOverlay;
   }

   @Generated
   public TexturePathResolver method71() {
      return this.guiHandler;
   }

   @Generated
   public boolean method72() {
      return this.field82;
   }

   @Generated
   public void method73(boolean var1) {
      this.field82 = var1;
   }

   public enum Type implements OptionEnumValue {
      ALL("all"),
      INVENTORY_ONLY("inventoryOnly"),
      NONE("none");

      private final String id;

      @Override
      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
      }

      @Generated
      Type(String var3) {
         this.id = var3;
      }
   }
}
