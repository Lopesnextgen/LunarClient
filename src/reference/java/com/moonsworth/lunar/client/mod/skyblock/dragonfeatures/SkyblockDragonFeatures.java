package com.moonsworth.lunar.client.mod.skyblock.dragonfeatures;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.EntityDragonBridge;
import com.moonsworth.lunar.bridge.BlocksBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockChat;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.ChatMessageQueue;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.KingRelicTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonClass;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.KingRelic;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.DungeonFloor;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonFloorListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonScoreListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.AlertDisplayListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonMapListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl.Type;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockElectionListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.EquippedItemListener;
import com.moonsworth.lunar.client.framework.listener.LocalPlayerNameListener;
import com.moonsworth.lunar.client.framework.listener.ScoreboardListener;
import com.moonsworth.lunar.client.event.entity.EventEntityRemove;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ListOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import com.moonsworth.lunar.client.framework.hud.HudTimer;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.game.EntityLookup;
import com.moonsworth.lunar.client.util.text.RomanNumeralParser;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockDragonFeatures extends AbstractFeature {
   private final DungeonFloorListener field8 = (DungeonFloorListener)this.method15(DungeonFloorListener.class);
   private final ScoreboardListener field9 = (ScoreboardListener)this.method15(ScoreboardListener.class);
   private final SkyblockElectionListener field10 = (SkyblockElectionListener)this.method15(SkyblockElectionListener.class);
   private final DungeonMapListener field11 = (DungeonMapListener)this.method15(DungeonMapListener.class);
   private final DungeonScoreListener field12 = (DungeonScoreListener)this.method15(DungeonScoreListener.class);
   private final AlertDisplayListener field13 = (AlertDisplayListener)this.method15(AlertDisplayListener.class);
   private final LocalPlayerNameListener field14 = (LocalPlayerNameListener)this.method15(LocalPlayerNameListener.class);
   private final EquippedItemListener field15 = (EquippedItemListener)this.method15(EquippedItemListener.class);
   private static final Pattern field16 = Pattern.compile("^﴾ Withered Dragon( ᛤ)? ([\\w.]+)/([\\w.]+)❤ ﴿$");
   private static final Pattern field17 = Pattern.compile("^- (\\w+) Dragon ([\\w.]+)❤$");
   private static final Pattern field18 = Pattern.compile("^Blessing of Power (?<level>[XVI]+)$");
   private static final Pattern field19 = Pattern.compile("^(?<player>\\w+) picked the Corrupted (?<relic>\\w+) Relic!$");
   private static final DecimalFormat field20 = new DecimalFormat("0.#");
   private static final DecimalFormat field21 = new DecimalFormat("0.00");
   private static final ResourceLocationBridge field22 = ResourceLocationBridge.create("lunar", "mobs/ender_dragon.png");
   private final ToggleOption field23 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("m7DragonTimers").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field24 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("m7DragonHealth").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field25 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("m7DragonAlerts").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field26 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("m7DragonKillBoxes").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field27 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showHud").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field28 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("m7RelicWaypoints").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field29 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("relicSpawnTimer").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field30 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("relicPlaceTime").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field31 = (ToggleOption)OptionFactory.method7("announceRelicPlace").method31();
   private final ToggleOption field32 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("m7DragonPriority").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final IntegerOption field33 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "m7DragonSplitPower"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(0))
         .OCRRICRIORICCCRHIOHORCICIHHICO(0, 40))
      .method31();
   private final ToggleOption field34 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("m7DragonTankSoloDebuff").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field35 = (ToggleOption)OptionFactory.method7("m7DragonAnnounceSplit").method31();
   private final ListOption<String> field36 = (ListOption<String>)((com.moonsworth.lunar.client.config.option.ListOption.Data)OptionFactory.method32(
            "m7CustomDragonPriority", Codec.STRING.listOf()
         )
         .method2(Arrays.stream(KingRelic.values()).map(KingRelic::getName).toList()))
      .method31();
   private final Map<KingRelic, KingRelicTracker> field37 = new HashMap<>();
   private final Set<EntityLivingBridge> field38 = new HashSet<>();
   private final ArrayList<KingRelic> field39 = new ArrayList<>();
   private final HashSet<KingRelic> field40 = new HashSet<>();
   private KingRelic field41;
   private HudTimer field42;
   private long field43;

   public SkyblockDragonFeatures(Skyblock skyblock1) {
      super(false);
      this.method26(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method26(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockDragonFeatures.Data()));
      this.method26(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method26(ModTraits.field19, DynamicCondition.method1(this, () -> this.field8.method6() == DungeonFloor.M7));
      this.method29(this::onDisable);
      this.handle(EventSpawnParticle.class, this::method7);
      this.handle(HudRenderLegacyEvent.class, arg1x -> {
         this.method8(arg1x);
         this.method9(arg1x);
         this.method10(arg1x);
      });
      this.handle(HudRenderLegacyEventAlt.class, this::method11);
      this.handle(EventWorldChange.class, this::method1);
      this.handle(com.moonsworth.lunar.client.event.mixin.gui.EventScoreboardUpdate.class, this::method6);
      this.handle(EventEntityRemove.class, this::method5);
      this.handle(EventEntitySpawn.class, arg1x -> {
         this.method3(arg1x);
         this.method2(arg1x);
         this.method4(arg1x);
      });
      this.handle(com.moonsworth.lunar.client.event.mixin.fishing.EventTick.class, arg1x -> {
         this.method13();
         this.method14();
      });
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, arg1x -> {
         this.method14(arg1x);
         this.method15(arg1x);
      });
      this.handle(com.moonsworth.lunar.client.event.mixin.fishing.EventBlockUpdate.BlockUpdate.class, this::method16);
   }

   private void onDisable() {
      this.field37.clear();
      this.field38.clear();
   }

   private void method1(EventWorldChange data31) {
      this.field37.clear();
      this.field38.clear();
      this.field40.clear();
      this.field41 = null;
      this.field42 = null;
      this.field43 = 0L;
   }

   private void method2(EventEntitySpawn highlightimpl6_21) {
      if (highlightimpl6_21.field1 instanceof EntityArmorStandBridge bridgeextension_22) {
         Component component11 = bridgeextension_22.bridge$getCustomName();
         if (component11 != null) {
            String text4 = TextBridge.getTextContent(component11);
            Matcher matcher5 = field16.matcher(text4);
            if (matcher5.matches()) {
               String text6 = matcher5.group(2);
               KingRelic hologramstype47 = null;

               for (Entry entry9 : this.field37.entrySet()) {
                  KingRelicTracker holograms9_210 = (KingRelicTracker)entry9.getValue();
                  if (bridgeextension_22.equals(holograms9_210.method5()) && !holograms9_210.method1()) {
                     hologramstype47 = (KingRelic)entry9.getKey();
                     break;
                  }
               }

               if (hologramstype47 == null) {
                  Horsestats20Extension2 horsestats20extension212 = Bridge.method8().method4(bridgeextension_22.bridge$getBlockX(), bridgeextension_22.bridge$getBlockY(), bridgeextension_22.bridge$getBlockZ());
                  BridgeExtension bridgeextension14 = EntityLookup.method1(horsestats20extension212, 2, arg2x -> {
                     KingRelic hologramstype43 = KingRelic.getByEntity(arg2x);
                     if (hologramstype43 == null) {
                        return false;
                     }

                     KingRelicTracker holograms9_24x = this.field37.get(hologramstype43);
                     return !holograms9_24x.method1() ? false : arg2x.method13(bridgeextension_22) < 144.0;
                  });
                  if (bridgeextension14 == null) {
                     return;
                  }

                  hologramstype47 = KingRelic.getByEntity(bridgeextension14);
                  KingRelicTracker holograms9_215 = this.field37.get(hologramstype47);
                  holograms9_215.method11((EntityArmorStandBridge)bridgeextension14);
               }

               KingRelicTracker holograms9_213 = this.field37.get(hologramstype47);
               holograms9_213.method10(bridgeextension_22);
               holograms9_213.method3(text6, hologramstype47);
            }
         }
      }
   }

   private void method3(EventEntitySpawn highlightimpl6_21) {
      BridgeExtension bridgeextension2 = highlightimpl6_21.field1;
      KingRelic hologramstype43 = KingRelic.getByEntity(bridgeextension2);
      if (hologramstype43 != null) {
         KingRelicTracker holograms9_24 = this.field37.get(hologramstype43);
         if (holograms9_24 != null && holograms9_24.method6() == null) {
            holograms9_24.method11((EntityArmorStandBridge)bridgeextension2);
         }
      }
   }

   private void method4(EventEntitySpawn highlightimpl6_21) {
      BridgeExtension bridgeextension2 = highlightimpl6_21.field1;
      if (bridgeextension2 instanceof EntityDragonBridge) {
         this.field38.add((EntityLivingBridge)bridgeextension2);
      }
   }

   private void method5(EventEntityRemove highlightimpl121) {
      BridgeExtension bridgeextension2 = highlightimpl121.method1();

      for (Entry entry4 : this.field37.entrySet()) {
         KingRelicTracker holograms9_25 = (KingRelicTracker)entry4.getValue();
         if (bridgeextension2.equals(holograms9_25.method6())) {
            holograms9_25.method11(null);
         } else if (bridgeextension2.equals(holograms9_25.method5())) {
            holograms9_25.method10(null);
         }
      }

      if (bridgeextension2 instanceof EntityLivingBridge) {
         this.field38.remove(bridgeextension2);
      }
   }

   private void method6(com.moonsworth.lunar.client.event.mixin.gui.EventScoreboardUpdate highlightimpl21) {
      for (String text4 : this.field9.method6()) {
         Matcher matcher5 = field17.matcher(text4);
         if (matcher5.matches()) {
            KingRelic hologramstype46 = KingRelic.getByAltName(matcher5.group(1));
            if (hologramstype46 == null) {
               CrashReporter.method5(new IllegalStateException("Unknown Dragon Type Parsed"), "SkyBlockDragonFeatures");
            } else {
               KingRelicTracker holograms9_27 = this.field37.get(hologramstype46);
               if (holograms9_27.method1()) {
                  String text8 = matcher5.group(2);
                  if (holograms9_27.method6() == null) {
                     WorldBridgeExtension itemcounter6extension9 = Ref.method8();

                     for (BridgeExtension bridgeextension12 : itemcounter6extension9.bridge$getEntities()) {
                        KingRelic hologramstype413 = KingRelic.getByEntity(bridgeextension12);
                        if (hologramstype413 == hologramstype46) {
                           holograms9_27.method11((EntityArmorStandBridge)bridgeextension12);
                           break;
                        }
                     }
                  }

                  holograms9_27.method3(text8, hologramstype46);
               }
            }
         }
      }
   }

   private void method7(EventSpawnParticle highlightimpl151) {
      WorldBridgeExtension itemcounter6extension2 = Ref.method8();
      if (itemcounter6extension2 != null) {
         if (highlightimpl151.method2() == ParticleType.ANGRY_VILLAGER) {
            for (KingRelic hologramstype46 : KingRelic.values()) {
               if (!itemcounter6extension2.method4(hologramstype46.getDeadIndicator()).bridge$isAir()
                  && !(hologramstype46.getDeadIndicator().method2(highlightimpl151.getPosX(), highlightimpl151.getPosY(), highlightimpl151.getPosZ()) > 25.0)) {
                  KingRelicTracker holograms9_27 = this.field37.get(hologramstype46);
                  if ((holograms9_27 == null || holograms9_27.method4().get() <= -5000L) && (Boolean)this.field25.get()) {
                     this.field13
                        .method2(
                           ComparableImpl.method2()
                              .method1("DRAGON_KILLABLE")
                              .method2(Component.text(hologramstype46.getName(), hologramstype46.getTextColor()).append(Component.text(this.method5("killable", new Object[0]))))
                              .method3(2000L)
                              .method4(Type.CRITICAL)
                              .method6()
                        );
                  }
               }
            }
         }

         if (highlightimpl151.method2() == ParticleType.FLAME) {
            KingRelic[] items8 = KingRelic.values();
            int number9 = items8.length;
            int index10 = 0;

            KingRelic hologramstype411;
            while (true) {
               if (index10 >= number9) {
                  return;
               }

               hologramstype411 = items8[index10];
               if (!itemcounter6extension2.method4(hologramstype411.getDeadIndicator()).bridge$isAir() && hologramstype411.getParticleBounds().method10(highlightimpl151.getPosX(), highlightimpl151.getPosY(), highlightimpl151.getPosZ())
                  )
                {
                  if (!this.field37.containsKey(hologramstype411)) {
                     break;
                  }

                  KingRelicTracker holograms9_212 = this.field37.get(hologramstype411);
                  if (holograms9_212.method4().get() <= -5000L) {
                     break;
                  }
               }

               index10++;
            }

            KingRelicTracker holograms9_213 = new KingRelicTracker();
            this.field37.put(hologramstype411, holograms9_213);
            this.field39.add(hologramstype411);
         }
      }
   }

   private void method8(HudRenderLegacyEvent highlightimpl21) {
      if ((Boolean)this.field26.get()) {
         EntityRenderDispatcherBridge bridge2_432 = Ref.method13();
         AbstractRenderContext bridgeextension_93 = highlightimpl21.method3();
         bridgeextension_93.push();
         bridgeextension_93.translate(-bridge2_432.bridge$renderPosX(), -bridge2_432.bridge$renderPosY(), -bridge2_432.bridge$renderPosZ());
         Skyblock skyblock4 = (Skyblock)((ChildModBinding)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1();
         BufferBuilderBridge bridge_285 = bridgeextension_93.method11((Float)skyblock4.method19().get());

         for (KingRelic hologramstype49 : KingRelic.values()) {
            Vec3iBridge horsestats2010 = hologramstype49.getDeadIndicator();
            int number11 = horsestats2010.bridge$getX();
            int number12 = horsestats2010.bridge$getY();
            int number13 = horsestats2010.bridge$getZ();
            WorldRenderUtils.drawBoxOutline(bridge_285, number11 - 15, number12 - 12, number13 - 15, number11 + 15, number12 + 8, number13 + 15, hologramstype49.getTextColor().value() | 0xFF000000);
         }

         bridge_285.end();
         bridgeextension_93.pop();
      }
   }

   private void method9(HudRenderLegacyEvent highlightimpl21) {
      if ((Boolean)this.field28.get()) {
         if (this.field12.method10()) {
            Bridge5Extension_5 bridge5extension_52 = Ref.method7();
            if (bridge5extension_52 != null) {
               if (!(bridge5extension_52.bridge$getPosY() > 50.0)) {
                  for (KingRelic hologramstype46 : KingRelic.values()) {
                     if (!this.field40.contains(hologramstype46)) {
                        Vec3iBridge horsestats207 = hologramstype46.getRelicHomeLocation();
                        WorldRenderUtils.drawBeaconBeam(highlightimpl21.method3(), horsestats207.bridge$getX() + 0.5, 256.0, horsestats207.bridge$getZ() + 0.5, hologramstype46.getTextColor().value() | -1090519040);
                     }
                  }

                  if (this.field41 != null) {
                     Vec3iBridge horsestats208 = this.field41.getRelicDestinationLocation();
                     WorldRenderUtils.drawBeaconBeam(highlightimpl21.method3(), horsestats208.bridge$getX(), 256.0, horsestats208.bridge$getZ(), this.field41.getTextColor().value() | -1090519040);
                  }
               }
            }
         }
      }
   }

   private void method10(HudRenderLegacyEvent highlightimpl21) {
      if ((Boolean)this.field29.get()) {
         if (this.field42 != null && this.field42.get() > 0L) {
            EntityRenderDispatcherBridge bridge2_432 = Ref.method13();
            AbstractRenderContext bridgeextension_93 = highlightimpl21.method3();
            bridgeextension_93.push();
            bridgeextension_93.translate(-bridge2_432.bridge$renderPosX(), -bridge2_432.bridge$renderPosY(), -bridge2_432.bridge$renderPosZ());
            String text4 = this.field42.method1();

            for (KingRelic hologramstype48 : KingRelic.values()) {
               Vec3iBridge horsestats209 = hologramstype48.getRelicHomeLocation();
               WorldRenderUtils.drawComponent(bridgeextension_93, Component.text(text4, hologramstype48.getTextColor()), horsestats209.bridge$getX() + 0.5, horsestats209.bridge$getY() + 1, horsestats209.bridge$getZ() + 0.5, true);
            }

            bridgeextension_93.pop();
         }
      }
   }

   private void method11(HudRenderLegacyEventAlt highlightimpl41) {
      EntityRenderDispatcherBridge bridge2_432 = Ref.method13();
      AbstractRenderContext bridgeextension_93 = highlightimpl41.method3();
      bridgeextension_93.push();
      bridgeextension_93.translate(-bridge2_432.bridge$renderPosX(), -bridge2_432.bridge$renderPosY(), -bridge2_432.bridge$renderPosZ());

      for (Entry entry5 : this.field37.entrySet()) {
         KingRelicTracker holograms9_26 = (KingRelicTracker)entry5.getValue();
         if (holograms9_26.method4().get() > 0L && (Boolean)this.field23.get()) {
            KingRelic hologramstype47 = (KingRelic)entry5.getKey();
            TextComponent text8 = holograms9_26.method2(hologramstype47, true);
            Vec3iBridge horsestats209 = hologramstype47.getDeadIndicator();
            WorldRenderUtils.drawComponent(bridgeextension_93, text8, horsestats209.bridge$getX(), horsestats209.bridge$getY() - 2, horsestats209.bridge$getZ(), true, 10.0F, true);
         }
      }

      if ((Boolean)this.field24.get()) {
         float value10 = highlightimpl41.method5();

         for (EntityLivingBridge bridgeextension2_512 : this.field38) {
            int number13 = Math.round(bridgeextension2_512.bridge$getUnboundedHealth());
            String text14 = NumberUtils.method10(number13);
            Object obj15 = Component.text(text14, NamedTextColor.GREEN);
            if (number13 <= 100000000) {
               obj15 = obj15.color(NamedTextColor.RED);
            } else if (number13 <= 300000000) {
               obj15 = obj15.color(NamedTextColor.YELLOW);
            }

            WorldRenderUtils.drawComponent(
               bridgeextension_93,
               (Component)obj15,
               bridgeextension2_512.RHIRRICCRHHHIIHHIHHOHRCHIOORCC(value10),
               bridgeextension2_512.IHRHHRIHICHOOICIRIOOHOICHIRHOI(value10),
               bridgeextension2_512.IIORCIOOIHRRRICOHIRCIHOOCCOHRO(value10),
               true,
               8.0F,
               true
            );
         }
      }

      bridgeextension_93.pop();
   }

   private void method13() {
      if (!this.field39.isEmpty()) {
         if (this.field39.size() > 1) {
            List list1 = this.method21();
            this.field39.sort(Comparator.comparing(list1::indexOf));
            KingRelic hologramstype42 = this.field39.get(0);
            KingRelic hologramstype43 = this.field39.get(this.field39.size() - 1);
            DungeonClass hologramstype2_26 = this.method19();
            DungeonClass hologramstype2_27 = this.field34.get() ? DungeonClass.TANK : DungeonClass.HEALER;
            float value8 = this.method17();
            String text9 = field20.format(value8);
            KingRelic hologramstype44;
            KingRelic hologramstype45;
            if (((Integer)this.field33.get()).intValue() > value8) {
               hologramstype45 = hologramstype43;
               hologramstype43 = hologramstype42;
               hologramstype44 = hologramstype42;
            } else if (hologramstype2_26 != DungeonClass.ARCHER && hologramstype2_26 != hologramstype2_27) {
               hologramstype44 = hologramstype43;
               hologramstype45 = hologramstype42;
            } else {
               hologramstype44 = hologramstype42;
               hologramstype45 = hologramstype43;
            }

            if ((Boolean)this.field25.get()) {
               this.field13
                  .method2(
                     ComparableImpl.method2()
                        .method1("M7_PRIO_DRAGON")
                        .method2(Component.text(hologramstype44.getName(), hologramstype44.getTextColor()).append(Component.text(this.method5("spawning", new Object[0]))))
                        .method3(2000L)
                        .method4(Type.HIGH)
                        .method6()
                  );
               this.field13
                  .method2(
                     ComparableImpl.method2()
                        .method1("M7_DRAGON")
                        .method2(Component.text(hologramstype45.getName(), hologramstype45.getTextColor()).append(Component.text(this.method5("spawning", new Object[0]))))
                        .method3(2000L)
                        .method4(Type.LOW)
                        .method6()
                  );
            }

            if ((Boolean)this.field35.get()) {
               if (hologramstype42 == hologramstype43) {
                  ChatMessageQueue.method1("Power: " + text9 + " || No split on " + hologramstype44.getName() + "!");
               } else {
                  ChatMessageQueue.method1("Power: " + text9 + " || Arch Team: " + hologramstype42.getName() + " || Bers Team: " + hologramstype43.getName());
               }
            }
         } else if ((Boolean)this.field25.get()) {
            KingRelic hologramstype410 = this.field39.get(0);
            this.field13
               .method2(
                  ComparableImpl.method2()
                     .method1("M7_DRAGON")
                     .method2(Component.text(hologramstype410.getName(), hologramstype410.getTextColor()).append(Component.text(this.method5("spawning", new Object[0]))))
                     .method3(2000L)
                     .method4(Type.LOW)
                     .method6()
               );
         }

         this.field39.clear();
      }
   }

   private void method14() {
      if ((Boolean)this.field28.get()) {
         Bridge5Extension_5 bridge5extension_51 = Ref.method7();
         if (bridge5extension_51 != null) {
            ItemStackBridge bridgeextension_42 = (ItemStackBridge)bridge5extension_51.bridge$getInventory().bridge$getMainInventory().get(8);
            this.field41 = KingRelic.getByItemStack(bridgeextension_42);
         }
      }
   }

   private void method14(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      Matcher matcher2 = field19.matcher(data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH());
      if (matcher2.matches()) {
         String text3 = matcher2.group("relic");
         KingRelic hologramstype44 = KingRelic.getByName(text3);
         if (hologramstype44 != null) {
            if ((Boolean)this.field28.get()) {
               this.field40.add(hologramstype44);
            }

            if ((Boolean)this.field30.get() && matcher2.group("player").equals(this.field14.method5())) {
               this.field43 = Ref.method3().bridge$getSystemTime();
            }
         }
      }
   }

   private void method15(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if ((Boolean)this.field29.get()) {
         if (data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH().equals("[BOSS] Necron: All this, for nothing...")) {
            this.field42 = com.moonsworth.lunar.client.framework.hud.HudTimer.Data.method1().method2().method5(2100L).method4().method7().method2();
         }
      }
   }

   private void method16(com.moonsworth.lunar.client.event.mixin.fishing.EventBlockUpdate.BlockUpdate data1) {
      if ((Boolean)this.field30.get()) {
         if (this.field43 != 0L) {
            BlocksBridge bridge_562 = Bridge.method34();
            Bridge3_23 bridge3_233 = data1.getBlock();
            if (bridge3_233 == bridge_562.method105() || bridge3_233 == bridge_562.method28()) {
               KingRelic hologramstype44 = KingRelic.getByItemStack(this.field15.method8());
               if (hologramstype44 != null) {
                  Vec3iBridge horsestats205 = data1.method1();
                  Vec3iBridge horsestats206 = hologramstype44.getRelicDestinationLocation();
                  if (horsestats205.bridge$getX() == horsestats206.bridge$getX() && horsestats205.bridge$getZ() == horsestats206.bridge$getZ()) {
                     long number7 = Ref.method3().bridge$getSystemTime() - this.field43;
                     String text9 = field21.format(number7 / 1000.0);
                     SkyBlockChat.method1(this.method5("relicPlaced", new Object[]{hologramstype44.getName(), text9}));
                     if ((Boolean)this.field31.get()) {
                        ChatMessageQueue.method1("/pc " + hologramstype44.getName() + " relic placed in " + text9 + "s!");
                     }
                  }
               }
            }
         }
      }
   }

   private float method17() {
      float value1 = 0.0F;
      Bridge2_42 bridge2_422 = Ref.method3().bridge$getGuiIngame().bridge$getTabList().bridge$getFooter();
      String text3 = TextBridge.getTextContent(TextBridge.asAdventure(bridge2_422));
      boolean flag4 = false;

      for (String text8 : text3.split("\n")) {
         Matcher matcher9 = field18.matcher(text8);
         if (matcher9.matches()) {
            value1 += RomanNumeralParser.romanToInt(matcher9.group("level"));
         } else if (text8.equals("Blessing of Time V")) {
            flag4 = true;
         }
      }

      if (this.field10.method2("Benediction")) {
         value1 *= 1.25F;
      }

      if (flag4) {
         value1 += 2.5F;
      }

      return value1;
   }

   private DungeonClass method19() {
      DungeonStateTracker holograms2_51 = (DungeonStateTracker)this.field11.method5().orElse(null);
      return holograms2_51 == null ? null : holograms2_51.method29().method37();
   }

   private List<KingRelic> method21() {
      return ((List)this.field36.get()).stream().<KingRelic>map(KingRelic::getByName).toList();
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_DRAGON_FEATURES";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.method9(new ClientOption[]{this.field23, this.field24, this.field25, this.field26, this.field28, this.field29});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field30, arg1xx -> arg1xx.method9(new ClientOption[]{this.field31}));
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field32, arg1xx -> arg1xx.method9(new ClientOption[]{this.field33, this.field34, this.field35, this.field36})
            );
         }
      );
      lightingextension231.method7(SettingsPage.HUD, arg1x -> arg1x.method9(new ClientOption[]{this.field27}));
   }

   protected ModDetails method20() {
      return ModDetails.method7()
         .method1(new ModCategory[]{ModCategory.field5})
         .method2(new String[]{"f7", "m7", "floor seven", "master seven"})
         .method11(this);
   }

   private class Data extends TypedHudRenderer<List<HudLine>> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT, true, true);
      }

      public HudSize method15() {
         return HudSize.method1(20, 30, 60, 20, 100, 200);
      }

      public List<HudLine> method2(boolean flag1) {
         if (flag1) {
            return this.method24();
         }

         ArrayList list2 = new ArrayList();
         if ((Boolean)SkyblockDragonFeatures.this.field23.get()) {
            for (Entry entry4 : SkyblockDragonFeatures.this.field37.entrySet()) {
               if (((KingRelicTracker)entry4.getValue()).method4().get() > 0L) {
                  list2.add(
                     new HudLine(
                        Bridge.method28().method20(),
                        ((KingRelicTracker)entry4.getValue())
                           .method2(
                              (KingRelic)entry4.getKey(), this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get()
                           )
                     )
                  );
               }
            }
         }

         if ((Boolean)SkyblockDragonFeatures.this.field24.get()) {
            for (KingRelicTracker holograms9_27 : SkyblockDragonFeatures.this.field37.values()) {
               if (holograms9_27.method6() != null) {
                  TextComponent text5 = holograms9_27.method8();
                  if (text5 != null) {
                     list2.add(new HudLine(SkyblockDragonFeatures.field22, text5));
                  }
               }
            }
         }

         return list2;
      }

      private List<HudLine> method24() {
         ArrayList list1 = new ArrayList();
         if ((Boolean)SkyblockDragonFeatures.this.field23.get()) {
            list1.add(this.method4(KingRelic.RED, "4.21s", NamedTextColor.RED));
            list1.add(this.method4(KingRelic.ORANGE, "2.34s", NamedTextColor.YELLOW));
         }

         if ((Boolean)SkyblockDragonFeatures.this.field24.get()) {
            list1.add(this.method5(KingRelic.PURPLE, "1.2B"));
            list1.add(this.method5(KingRelic.BLUE, "843M"));
         }

         return list1.isEmpty() ? List.of(new HudLine(Component.text(SkyblockDragonFeatures.this.method5("hudTitle", new Object[0])))) : list1;
      }

      private HudLine method4(KingRelic hologramstype41, String text2, NamedTextColor namedtextcolor3) {
         return new HudLine(
            Bridge.method28().method20(),
            TextComponentFactory.builder()
               .method2(hologramstype41.getName())
               .method4(text2)
               .method5(hologramstype41.getTextColor())
               .method7(namedtextcolor3)
               .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
               .build()
         );
      }

      private HudLine method5(KingRelic hologramstype41, String text2) {
         return new HudLine(
            SkyblockDragonFeatures.field22,
            TextComponentFactory.builder().method2(hologramstype41.getName()).method4(text2).method5(hologramstype41.getTextColor()).method7(NamedTextColor.GRAY).build()
         );
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }

      protected boolean method20() {
         return false;
      }

      protected boolean method22() {
         return false;
      }

      protected boolean method23() {
         return true;
      }

      public boolean method4(boolean flag1) {
         return (Boolean)SkyblockDragonFeatures.this.field27.get() && SkyblockDragonFeatures.this.field8.method6() == DungeonFloor.M7 && super.method4(flag1);
      }
   }
}
