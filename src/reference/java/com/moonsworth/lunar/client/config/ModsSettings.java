package com.moonsworth.lunar.client.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.config.DynamicFeatureFlag;
import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.Framework7Loader;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.holograms.OptionsReloadBaseEvent.Data12;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.OptionCategory;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.mod.combat.attackindicator.AttackIndicator;
import com.moonsworth.lunar.client.mod.combat.combo.Combo;
import com.moonsworth.lunar.client.mod.combat.cooldowns.Cooldowns;
import com.moonsworth.lunar.client.mod.combat.damagetint.DamageTint;
import com.moonsworth.lunar.client.mod.combat.hitcolor.HitColor;
import com.moonsworth.lunar.client.mod.combat.hitbox.Hitbox;
import com.moonsworth.lunar.client.mod.combat.killsounds.KillSounds;
import com.moonsworth.lunar.client.mod.combat.knockbacktrainer.KnockbackTrainer;
import com.moonsworth.lunar.client.mod.combat.potioncounter.PotionCounter;
import com.moonsworth.lunar.client.mod.combat.pvpinfo.PvpInfo;
import com.moonsworth.lunar.client.mod.combat.reachdisplay.ReachDisplay;
import com.moonsworth.lunar.client.mod.combat.shields.Shields;
import com.moonsworth.lunar.client.mod.combat.tntcountdown.TntCountdown;
import com.moonsworth.lunar.client.mod.combat.totemcounter.TotemCounter;
import com.moonsworth.lunar.client.mod.hud.actionbar.ActionBar;
import com.moonsworth.lunar.client.mod.hud.audiosubtitles.AudioSubtitles;
import com.moonsworth.lunar.client.mod.hud.bossbar.Bossbar;
import com.moonsworth.lunar.client.mod.hud.clock.Clock;
import com.moonsworth.lunar.client.mod.hud.coordinates.Coordinates;
import com.moonsworth.lunar.client.mod.hud.cps.Cps;
import com.moonsworth.lunar.client.mod.hud.daycounter.DayCounter;
import com.moonsworth.lunar.client.mod.hud.directionhud.DirectionHud;
import com.moonsworth.lunar.client.mod.hud.f3display.F3Display;
import com.moonsworth.lunar.client.mod.hud.fps.Fps;
import com.moonsworth.lunar.client.mod.hud.horsestats.HorseStats;
import com.moonsworth.lunar.client.mod.misc.debug.HudDebug;
import com.moonsworth.lunar.client.mod.hud.itemcounter.ItemCounter;
import com.moonsworth.lunar.client.mod.hud.keystrokes.Keystrokes;
import com.moonsworth.lunar.client.mod.hud.memory.Memory;
import com.moonsworth.lunar.client.mod.hud.packdisplay.PackDisplay;
import com.moonsworth.lunar.client.mod.hud.ping.Ping;
import com.moonsworth.lunar.client.mod.hud.playtime.Playtime;
import com.moonsworth.lunar.client.mod.hud.scoreboard.Scoreboard;
import com.moonsworth.lunar.client.mod.hud.serveraddress.ServerAddress;
import com.moonsworth.lunar.client.mod.hud.stopwatch.Stopwatch;
import com.moonsworth.lunar.client.mod.hud.tab.Tab;
import com.moonsworth.lunar.client.mod.hud.waila.Waila;
import com.moonsworth.lunar.client.mod.misc.debug.ApolloDebugMod;
import com.moonsworth.lunar.client.mod.misc.autotextactions.AutoTextActions;
import com.moonsworth.lunar.client.mod.misc.autotextactions.AutoTextHotkey;
import com.moonsworth.lunar.client.mod.misc.debug.BasicDebugMod;
import com.moonsworth.lunar.client.mod.misc.chat.Chat;
import com.moonsworth.lunar.client.mod.misc.debug.CosmeticDebugMod;
import com.moonsworth.lunar.client.mod.misc.debug.DynamiclistenerDebugMod;
import com.moonsworth.lunar.client.mod.misc.debug.EventDebugMod;
import com.moonsworth.lunar.client.mod.misc.debug.FeatureFlagDebug;
import com.moonsworth.lunar.client.mod.misc.debug.FpsDebugMod;
import com.moonsworth.lunar.client.mod.misc.debug.GeckolibDebugMod;
import com.moonsworth.lunar.client.mod.misc.debug.GraphDebugMod;
import com.moonsworth.lunar.client.mod.misc.guiscale.GuiScale;
import com.moonsworth.lunar.client.mod.misc.hypixelbedwars.HypixelBedwars;
import com.moonsworth.lunar.client.mod.misc.hypixel.HypixelMods;
import com.moonsworth.lunar.client.mod.misc.itemtracker.ItemTracker;
import com.moonsworth.lunar.client.mod.misc.debug.McDebugRenderer;
import com.moonsworth.lunar.client.mod.misc.debug.ModsEnabledDebugMod;
import com.moonsworth.lunar.client.mod.misc.debug.OptimizationDebugMod;
import com.moonsworth.lunar.client.mod.misc.debug.OptionDumpMod;
import com.moonsworth.lunar.client.mod.misc.packorganizer.PackOrganizer;
import com.moonsworth.lunar.client.mod.misc.panoramamaker.PanoramaMaker;
import com.moonsworth.lunar.client.mod.misc.debug.PathfindingDebug;
import com.moonsworth.lunar.client.mod.misc.quickplay.Quickplay;
import com.moonsworth.lunar.client.mod.misc.radio.Radio;
import com.moonsworth.lunar.client.mod.misc.debug.RaycastDebug;
import com.moonsworth.lunar.client.mod.misc.replaymod.ReplayMod;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.mod.misc.screenshot.Screenshot;
import com.moonsworth.lunar.client.mod.misc.debug.SettingsOverrideDebug;
import com.moonsworth.lunar.client.mod.misc.debug.ShaderDebugMod;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.mod.skyblock.debug.SkyblockDebugMod;
import com.moonsworth.lunar.client.mod.misc.soundchanger.SoundChanger;
import com.moonsworth.lunar.client.mod.misc.debug.TickDebug;
import com.moonsworth.lunar.client.mod.misc.debug.TransformationDebug;
import com.moonsworth.lunar.client.mod.misc.debug.TurboRenderingDebugMod;
import com.moonsworth.lunar.client.mod.movement.freelook.Freelook;
import com.moonsworth.lunar.client.mod.movement.momentum.Momentum;
import com.moonsworth.lunar.client.mod.movement.snaplook.Snaplook;
import com.moonsworth.lunar.client.mod.movement.togglesneak.ToggleSneak;
import com.moonsworth.lunar.client.mod.movement.zoom.Zoom;
import com.moonsworth.lunar.client.mod.player.inventorymod.InventoryMods;
import com.moonsworth.lunar.client.mod.player.mumblelink.MumbleLink;
import com.moonsworth.lunar.client.mod.player.teamview.TeamView;
import com.moonsworth.lunar.client.mod.render.armorstatus.Armorstatus;
import com.moonsworth.lunar.client.mod.render.blockoutline.BlockOutline;
import com.moonsworth.lunar.client.mod.render.chunkborders.ChunkBorders;
import com.moonsworth.lunar.client.mod.render.colorsaturation.ColorSaturation;
import com.moonsworth.lunar.client.mod.render.crosshair.Crosshair;
import com.moonsworth.lunar.client.mod.render.fog.Fog;
import com.moonsworth.lunar.client.mod.render.fov.Fov;
import com.moonsworth.lunar.client.mod.render.glintcolorizer.GlintColorizer;
import com.moonsworth.lunar.client.mod.render.heightlimit.HeightLimit;
import com.moonsworth.lunar.client.mod.render.hurtcam.HurtCam;
import com.moonsworth.lunar.client.mod.render.itemcustomizer.ItemCustomizer;
import com.moonsworth.lunar.client.mod.render.itemphysics.ItemPhysics;
import com.moonsworth.lunar.client.mod.render.items2d.Items2d;
import com.moonsworth.lunar.client.mod.render.lightoverlay.LightOverlay;
import com.moonsworth.lunar.client.mod.render.lighting.Lighting;
import com.moonsworth.lunar.client.mod.render.markers.Markers;
import com.moonsworth.lunar.client.mod.render.menublur.MenuBlur;
import com.moonsworth.lunar.client.mod.render.minimap.Minimap;
import com.moonsworth.lunar.client.mod.render.mobsize.MobSize;
import com.moonsworth.lunar.client.mod.render.motionblur.MotionBlur;
import com.moonsworth.lunar.client.mod.render.nametag.Nametag;
import com.moonsworth.lunar.client.mod.render.nickhider.NickHider;
import com.moonsworth.lunar.client.mod.render.onesevenvisuals.OneSevenVisuals;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.mod.render.particlechanger.ParticleChanger;
import com.moonsworth.lunar.client.mod.render.potioneffects.PotionEffects;
import com.moonsworth.lunar.client.mod.render.saturation.Saturation;
import com.moonsworth.lunar.client.mod.render.scrollabletooltips.ScrollableTooltips;
import com.moonsworth.lunar.client.mod.render.shinypots.ShinyPots;
import com.moonsworth.lunar.client.mod.render.shulkerpreview.ShulkerPreview;
import com.moonsworth.lunar.client.mod.render.skins3d.Skins3d;
import com.moonsworth.lunar.client.mod.render.tiertagger.TierTagger;
import com.moonsworth.lunar.client.mod.render.timechanger.TimeChanger;
import com.moonsworth.lunar.client.mod.render.titles.Titles;
import com.moonsworth.lunar.client.mod.render.waypoints.Waypoints;
import com.moonsworth.lunar.client.mod.render.weatherchanger.WeatherChanger;
import com.moonsworth.lunar.client.mod.render.worldeditcui.WorldeditCui;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class ModsSettings extends com.moonsworth.lunar.client.config.FavoriteColorsConfig<Framework7Extension> {
   private final GuiIterator field2 = new GuiIterator();
   private static final Map<String, Framework7Extension> field3 = new HashMap<>();
   private static final Map<String, Framework7Extension> field4 = new HashMap<>();
   private OneSevenVisuals field5;
   private HitColor field6;
   private Scoreboard field7;
   private Fov field8;
   private Fog field9;
   private Keystrokes field10;
   private Coordinates field11;
   private AutoTextHotkey field12;
   private Waypoints field13;
   private BlockOutline field14;
   private ParticleChanger field15;
   private PotionEffects field16;
   private ShinyPots field17;
   private Skins3d field18;
   private GlintColorizer field19;
   private DirectionHud field20;
   private ToggleSneak field21;
   private Items2d field22;
   private ItemPhysics field23;
   private Freelook field24;
   private Screenshot field25;
   private Crosshair field26;
   private AttackIndicator field27;
   private ReplayMod field28;
   private Titles field29;
   private Cooldowns field30;
   private Stopwatch field31;
   private TntCountdown field32;
   private ReachDisplay field33;
   private TimeChanger field34;
   private NickHider field35;
   private Armorstatus field36;
   private MenuBlur field37;
   private MotionBlur field38;
   private ColorSaturation field39;
   private ScrollableTooltips field40;
   private Bossbar field41;
   private Chat field42;
   private Tab field43;
   private HypixelMods field44;
   private Zoom field45;
   private Nametag field46;
   private Quickplay field47;
   private Momentum field48;
   private Hitbox field49;
   private PanoramaMaker field50;
   private HypixelBedwars field51;
   private HeightLimit field52;
   private WeatherChanger field53;
   private Lighting field54;
   private PackOrganizer field55;
   private SoundChanger field56;
   private PvpInfo field57;
   private Saturation field58;
   private ChunkBorders field59;
   private Waila field60;
   private McDebugRenderer field61;
   private GeckolibDebugMod field62;
   private ShaderDebugMod field63;
   private ModsEnabledDebugMod field64;
   private OptimizationDebugMod field65;
   private SkyblockDebugMod field66;
   private FpsDebugMod field67;
   private BasicDebugMod field68;
   private ApolloDebugMod field69;
   private HurtCam field70;
   private Skyblock field71;
   private ItemCustomizer field72;
   private ShulkerPreview field73;
   private HorseStats field74;
   private OverlayMod field75;
   private Rewind field76;
   private TierTagger field77;
   private Markers field78;
   private TeamView field79;
   @Nullable
   private Radio field80;
   private AudioSubtitles field81;
   private ActionBar field82;
   private Shields field83;
   private LightOverlay field84;
   private InventoryMods field85;
   private Minimap field86;
   private F3Display field87;
   private GuiScale field88;
   private KnockbackTrainer field89;

   @Override
   protected Set<Framework7Extension> method1() {
      LinkedHashSet var1 = this.method99(
         new Framework7Extension[]{
            this.field28 = new ReplayMod(),
            this.field5 = new OneSevenVisuals(),
            new Fps(),
            new Cps(),
            this.field21 = new ToggleSneak(),
            this.field45 = new Zoom(),
            this.field44 = new HypixelMods(),
            this.field51 = new HypixelBedwars(),
            this.field52 = new HeightLimit(),
            this.field47 = new Quickplay(),
            this.field36 = new Armorstatus(),
            this.field10 = new Keystrokes(),
            this.field11 = new Coordinates(),
            new DayCounter(),
            this.field26 = new Crosshair(),
            this.field27 = new AttackIndicator(),
            this.field16 = new PotionEffects(),
            this.field20 = new DirectionHud(),
            this.field13 = new Waypoints(),
            this.field6 = new HitColor(),
            this.field7 = new Scoreboard(),
            this.field29 = new Titles(),
            new ItemCounter(),
            new PotionCounter(),
            new Ping(),
            this.field38 = new MotionBlur(),
            this.field55 = new PackOrganizer(),
            this.field42 = new Chat(),
            this.field43 = new Tab(),
            this.field46 = new Nametag(),
            this.field73 = new ShulkerPreview(),
            this.field40 = new ScrollableTooltips(),
            this.field15 = new ParticleChanger(),
            this.field35 = new NickHider(),
            this.field30 = new Cooldowns(),
            new WorldeditCui(),
            new Clock(),
            this.field31 = new Stopwatch(),
            new Playtime(),
            new Memory(),
            new Combo(),
            this.field33 = new ReachDisplay(),
            this.field34 = new TimeChanger(),
            new ServerAddress(),
            this.field58 = new Saturation(),
            this.field39 = new ColorSaturation(),
            this.field23 = new ItemPhysics(),
            this.field32 = new TntCountdown(),
            new ItemTracker(),
            this.field17 = new ShinyPots(),
            this.field18 = new Skins3d(),
            this.field19 = new GlintColorizer(),
            this.field48 = new Momentum(),
            this.field14 = new BlockOutline(),
            this.field25 = new Screenshot(),
            this.field8 = new Fov(),
            this.field9 = new Fog(),
            this.field12 = new AutoTextHotkey(),
            new AutoTextActions(),
            new MumbleLink(),
            new TotemCounter(),
            this.field22 = new Items2d(),
            this.field41 = new Bossbar(),
            this.field24 = new Freelook(),
            this.field57 = new PvpInfo(),
            this.field78 = new Markers(),
            new Snaplook(),
            this.field79 = new TeamView(),
            new PackDisplay(),
            this.field37 = new MenuBlur(),
            this.field86 = new Minimap(),
            this.field49 = new Hitbox(),
            this.field54 = new Lighting(),
            this.field53 = new WeatherChanger(),
            this.field59 = new ChunkBorders(),
            this.field56 = new SoundChanger(),
            this.field60 = new Waila(),
            this.field70 = new HurtCam(),
            this.field77 = new TierTagger(),
            new DamageTint(),
            new MobSize(),
            this.field71 = new Skyblock(),
            this.field72 = new ItemCustomizer(),
            this.field74 = new HorseStats(),
            this.field75 = new OverlayMod(),
            this.field76 = new Rewind(),
            this.field81 = new AudioSubtitles(),
            this.field82 = new ActionBar(),
            this.field83 = new Shields(),
            this.field84 = new LightOverlay(),
            new KillSounds(),
            this.field85 = new InventoryMods(),
            this.field87 = new F3Display(),
            this.field88 = new GuiScale(),
            this.field89 = new KnockbackTrainer()
         }
      );
      if (!LunarBuildData.field4) {
         var1.add(this.field50 = new PanoramaMaker(false));
         var1.add(new HudDebug());
         var1.add(this.field61 = new McDebugRenderer(false));
         var1.add(new RaycastDebug());
         var1.add(new TransformationDebug());
         var1.add(new FeatureFlagDebug());
         var1.add(new SettingsOverrideDebug());
         var1.add(new GraphDebugMod());
         if (ThreadModuleDump63.MC_VERSION >= 8) {
            var1.add(new TurboRenderingDebugMod());
         }

         var1.add(this.field68 = new BasicDebugMod());
         var1.add(new EventDebugMod());
         var1.add(this.field69 = new ApolloDebugMod());
         var1.add(new DynamiclistenerDebugMod());
         var1.add(new TickDebug());
         var1.add(new PathfindingDebug());
         var1.add(new CosmeticDebugMod());
         var1.add(new OptionDumpMod());
      }

      if (DynamicFeatureFlag.RADIO.isEnabled()) {
         var1.add(this.field80 = new Radio());
      }

      var1.add(this.field64 = new ModsEnabledDebugMod());
      var1.add(this.field65 = new OptimizationDebugMod());
      var1.add(this.field66 = new SkyblockDebugMod());
      var1.add(this.field67 = new FpsDebugMod());
      var1.add(this.field62 = new GeckolibDebugMod());
      var1.add(this.field63 = new ShaderDebugMod());
      return var1;
   }

   @Override
   protected void method4(Framework7Extension var1) {
      this.method5(var1);
      super.method4(var1);
   }

   @Override
   protected void method5(Set<Framework7Extension> var1) {
      if (com.moonsworth.lunar.client.framework.metadata.ModMetadataFetcher.field2 != null) {
         Client.method109().method38().method2(var1);
      }
   }

   private void method5(Framework7Extension var1) {
      Framework7Loader var2 = (Framework7Loader)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field3);
      if (var2 != null) {
         if (var2.method6() != null) {
            field4.put(var2.method6(), var1);
            Framework5 var3 = (Framework5)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
            if (var3 != null) {
               for (ClientOption var5 : var3.method2()) {
                  if (var5 instanceof SimpleKeybindOption var6) {
                     var6.method3(var2);
                  }
               }
            }
         }

         if (var2.method1()) {
            field3.put(var2.method5(), var1);
         }
      }
   }

   public Framework7Extension method5(String var1) {
      return field3.get(var1);
   }

   public List<String> method10() {
      return field4.entrySet().stream().filter(var0 -> !var0.getValue().isEnabled()).map(Entry::getKey).toList();
   }

   @Override
   public void init() {
      super.init();
      ClientEventBus.method29().method12(Data12.class, Data12::new);
      this.method11();
   }

   @Override
   public String method5() {
      return "mods.json";
   }

   @Override
   public File method6() {
      return new File(ThreadModuleDump48.field25 + File.separator + ThreadModuleDump63.method4().method61().method14().getName(), this.method5());
   }

   public void method11() {
      LinkedHashSet var1 = this.IIORHHIRHIORHRCCCOICCRCHRRCCRH()
         .stream()
         .filter(var0 -> var0.method2(Framework.field10))
         .collect(Collectors.toCollection(LinkedHashSet::new));
      this.field2.method2("details", var1);
      JsonObject var2 = new JsonObject();

      for (Framework7Extension var4 : this.IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
         this.method10(var4, var2);
      }

      Client.method109().method107().method3("features", var2);
   }

   private void method10(Framework7Extension var1, JsonObject var2) {
      Framework5 var3 = (Framework5)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
      if (var3 != null) {
         JsonArray var4 = new JsonArray();
         var3.method4(var1x -> {
            OptionDataProvider var2x = (OptionDataProvider)var1x.HIRHCCHIRHRORIICOIHIHCICOIRHHC(com.moonsworth.lunar.client.config.option.OptionTraits.field10);
            if (var2x != null && var2x.method2()) {
               var2x.method4(OptionCategory.FEATURE, var1.getId());
               var1x.method8(var2xx -> var2x.method3(OptionCategory.FEATURE, var1.getId()));
            }

            return false;
         });
         var3.method1().forEach(var1x -> {
            OptionDataProvider var2x = (OptionDataProvider)var1x.HIRHCCHIRHRORIICOIHIHCICOIRHHC(com.moonsworth.lunar.client.config.option.OptionTraits.field10);
            if (var2x != null) {
               var4.add(var2x.provide());
            }
         });
         var2.add(var1.getId(), var4);
      }

      AlertExtension var7 = (AlertExtension)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
      if (var7 != null) {
         for (Framework7Extension var6 : var7.getChildren()) {
            this.method10(var6, var2);
         }
      }
   }

   @Nullable
   public Framework7Extension method11(String var1) {
      for (Framework7Extension var3 : this.IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
         if (var3.getId().equals(var1)) {
            return var3;
         }

         AlertExtension var4 = (AlertExtension)var3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
         if (var4 != null) {
            Framework7Extension var5 = (Framework7Extension)var4.method4(var1x -> var1x.getId().equals(var1));
            if (var5 != null) {
               return var5;
            }
         }
      }

      return null;
   }

   @Generated
   public GuiIterator method12() {
      return this.field2;
   }

   @Generated
   public OneSevenVisuals method98() {
      return this.field5;
   }

   @Generated
   public HitColor method14() {
      return this.field6;
   }

   @Generated
   public Scoreboard method15() {
      return this.field7;
   }

   @Generated
   public Fov method16() {
      return this.field8;
   }

   @Generated
   public Fog method17() {
      return this.field9;
   }

   @Generated
   public Keystrokes method18() {
      return this.field10;
   }

   @Generated
   public AutoTextHotkey method19() {
      return this.field12;
   }

   @Generated
   public Waypoints method20() {
      return this.field13;
   }

   @Generated
   public BlockOutline method21() {
      return this.field14;
   }

   @Generated
   public ParticleChanger method22() {
      return this.field15;
   }

   @Generated
   public PotionEffects method23() {
      return this.field16;
   }

   @Generated
   public ShinyPots method24() {
      return this.field17;
   }

   @Generated
   public Skins3d method25() {
      return this.field18;
   }

   @Generated
   public GlintColorizer method26() {
      return this.field19;
   }

   @Generated
   public DirectionHud method27() {
      return this.field20;
   }

   @Generated
   public ToggleSneak method28() {
      return this.field21;
   }

   @Generated
   public Items2d method29() {
      return this.field22;
   }

   @Generated
   public ItemPhysics method30() {
      return this.field23;
   }

   @Generated
   public Freelook method31() {
      return this.field24;
   }

   @Generated
   public Screenshot method32() {
      return this.field25;
   }

   @Generated
   public Crosshair method33() {
      return this.field26;
   }

   @Generated
   public AttackIndicator method34() {
      return this.field27;
   }

   @Generated
   public Titles method35() {
      return this.field29;
   }

   @Generated
   public Cooldowns method36() {
      return this.field30;
   }

   @Generated
   public Stopwatch method37() {
      return this.field31;
   }

   @Generated
   public TntCountdown method38() {
      return this.field32;
   }

   @Generated
   public ReachDisplay method39() {
      return this.field33;
   }

   @Generated
   public TimeChanger method40() {
      return this.field34;
   }

   @Generated
   public NickHider method41() {
      return this.field35;
   }

   @Generated
   public Armorstatus method42() {
      return this.field36;
   }

   @Generated
   public MenuBlur method43() {
      return this.field37;
   }

   @Generated
   public MotionBlur method44() {
      return this.field38;
   }

   @Generated
   public ColorSaturation method45() {
      return this.field39;
   }

   @Generated
   public ScrollableTooltips method46() {
      return this.field40;
   }

   @Generated
   public Chat method47() {
      return this.field42;
   }

   @Generated
   public Tab method48() {
      return this.field43;
   }

   @Generated
   public HypixelMods method49() {
      return this.field44;
   }

   @Generated
   public Zoom method50() {
      return this.field45;
   }

   @Generated
   public Nametag method51() {
      return this.field46;
   }

   @Generated
   public Quickplay method52() {
      return this.field47;
   }

   @Generated
   public Momentum method53() {
      return this.field48;
   }

   @Generated
   public Hitbox method54() {
      return this.field49;
   }

   @Generated
   public WeatherChanger method55() {
      return this.field53;
   }

   @Generated
   public Lighting method56() {
      return this.field54;
   }

   @Generated
   public PackOrganizer method57() {
      return this.field55;
   }

   @Generated
   public SoundChanger method58() {
      return this.field56;
   }

   @Generated
   public PvpInfo method59() {
      return this.field57;
   }

   @Generated
   public ShulkerPreview method60() {
      return this.field73;
   }

   @Generated
   public HorseStats method61() {
      return this.field74;
   }

   @Nullable
   @Generated
   public Radio method62() {
      return this.field80;
   }

   @Generated
   public Coordinates method63() {
      return this.field11;
   }

   @Generated
   public ReplayMod method64() {
      return this.field28;
   }

   @Generated
   public Bossbar method65() {
      return this.field41;
   }

   @Generated
   public PanoramaMaker method66() {
      return this.field50;
   }

   @Generated
   public HypixelBedwars method67() {
      return this.field51;
   }

   @Generated
   public HeightLimit method68() {
      return this.field52;
   }

   @Generated
   public Saturation method69() {
      return this.field58;
   }

   @Generated
   public ChunkBorders method70() {
      return this.field59;
   }

   @Generated
   public Waila method71() {
      return this.field60;
   }

   @Generated
   public McDebugRenderer method72() {
      return this.field61;
   }

   @Generated
   public GeckolibDebugMod method73() {
      return this.field62;
   }

   @Generated
   public ShaderDebugMod method74() {
      return this.field63;
   }

   @Generated
   public ModsEnabledDebugMod method75() {
      return this.field64;
   }

   @Generated
   public OptimizationDebugMod method76() {
      return this.field65;
   }

   @Generated
   public SkyblockDebugMod method77() {
      return this.field66;
   }

   @Generated
   public FpsDebugMod method78() {
      return this.field67;
   }

   @Generated
   public BasicDebugMod method79() {
      return this.field68;
   }

   @Generated
   public ApolloDebugMod method80() {
      return this.field69;
   }

   @Generated
   public HurtCam method81() {
      return this.field70;
   }

   @Generated
   public Skyblock method82() {
      return this.field71;
   }

   @Generated
   public ItemCustomizer method83() {
      return this.field72;
   }

   @Generated
   public OverlayMod method84() {
      return this.field75;
   }

   @Generated
   public Rewind method85() {
      return this.field76;
   }

   @Generated
   public TierTagger method86() {
      return this.field77;
   }

   @Generated
   public Markers method87() {
      return this.field78;
   }

   @Generated
   public TeamView method88() {
      return this.field79;
   }

   @Generated
   public AudioSubtitles method89() {
      return this.field81;
   }

   @Generated
   public ActionBar method90() {
      return this.field82;
   }

   @Generated
   public Shields method91() {
      return this.field83;
   }

   @Generated
   public LightOverlay method92() {
      return this.field84;
   }

   @Generated
   public InventoryMods method93() {
      return this.field85;
   }

   @Generated
   public Minimap method94() {
      return this.field86;
   }

   @Generated
   public F3Display method95() {
      return this.field87;
   }

   @Generated
   public GuiScale method96() {
      return this.field88;
   }

   @Generated
   public KnockbackTrainer method97() {
      return this.field89;
   }
}
