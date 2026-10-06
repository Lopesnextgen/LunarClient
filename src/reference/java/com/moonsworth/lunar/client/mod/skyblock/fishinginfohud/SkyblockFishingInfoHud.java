package com.moonsworth.lunar.client.mod.skyblock.fishinginfohud;

import com.google.common.collect.Sets;
import com.lunarclient.generated.skyblockprofileresponse.profile.Member;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemsBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.EntityFishHookBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.CoordinatesType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.ItemRarity;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.ScoreboardSection;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockProfileCache;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockScoreboardParser;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkillXpListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.FishingHookTracker;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SeaCreatureCatchEvent;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkillXpUpdateEvent;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkyblockProfileEvents.SkyblockProfileLoadEvent;
import com.moonsworth.lunar.client.render.particle.ClampUtils;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.text.TimeFormatting;
import com.moonsworth.lunar.client.util.game.NpcUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.Nullable;

public class SkyblockFishingInfoHud extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile("^ §.\\[Lvl (?<level>\\d{1,3})] §(?<rarity>.)Dolphin.*$");
   private static final NumberFormat field9 = NumberFormat.getIntegerInstance(Locale.ROOT);
   private static final int field10 = 120000;
   private static final Set<String> field11 = Sets.newHashSet(new String[]{"GRAPPLING_HOOK", "SOUL_WHIP", "FLAMING_FLAY"});
   private static final ResourceLocationBridge field12 = ResourceLocationBridge.create("lunar", "skyblock/hud/dolphin.png");
   private static final ResourceLocationBridge field13 = ResourceLocationBridge.create("lunar", "mobs/squid.png");
   private final SkillXpListener field14 = (SkillXpListener)this.method18(SkillXpListener.class);
   private final SkyblockScoreboardParser field15 = (SkyblockScoreboardParser)this.method18(SkyblockScoreboardParser.class);
   private final FishingHookTracker field16 = (FishingHookTracker)this.method18(FishingHookTracker.class);
   private final SkyblockProfileCache field17 = (SkyblockProfileCache)this.method18(SkyblockProfileCache.class);
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showIfRodInHotbar").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showTitle").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showFishingTime").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field21 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showSeaCreatures").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field22 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showMilestone").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field23 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showSessionXp").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field24 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showDolphinBoost").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field25 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showFishingProgress").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field26 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "titleColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-43521))
      .method31();
   private final ColorOption field27 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "fishingTimeColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141121))
      .method31();
   private final ColorOption field28 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "seaCreaturesColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final ColorOption field29 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "milestoneColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-43521))
      .method31();
   private final ColorOption field30 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "sessionXpColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141291))
      .method31();
   private final ColorOption field31 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "dolphinBoostColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16777046))
      .method31();
   private final ColorOption field32 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "fishingProgressColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16733526))
      .method31();
   private long field33;
   private boolean isActive;
   private long field34;
   private String field35;
   private int field36;
   private long field37;
   private double field38;
   private String field39;
   private String field40;
   private ItemRarity field41;
   private int field42;

   public SkyblockFishingInfoHud(Skyblock skyblock1) {
      super(false);
      this.method1(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method1(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockFishingInfoHud.Data()));
      this.method1(ModTraits.field17, ModCategories.method2(SettingsPage.FISHING));
      this.method1(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(SeaCreatureCatchEvent.class, this::method1);
      this.handle(SkillXpUpdateEvent.class, this::method2);
      this.handle(com.moonsworth.lunar.client.event.mixin.gui.EventTabListUpdate.class, this::method3);
      this.handle(EventTick.class, arg1x -> {
         this.method4(arg1x);
         this.method5(arg1x);
      });
      this.handle(com.moonsworth.lunar.client.event.mixin.fishing.EventSecond.class, this::method6);
      this.handle(EventWorldChange.class, this::method7);
      this.handle(SkyblockProfileLoadEvent.class, arg1x -> this.method13());
   }

   private void method1(SeaCreatureCatchEvent highlightimpl31) {
      this.field36 = this.field36 + highlightimpl31.getCount();
      this.field37 = this.field37 + highlightimpl31.getCount();
   }

   private void method2(SkillXpUpdateEvent highlightimpl41) {
      if (highlightimpl41.method1() == CoordinatesType.FISHING) {
         this.field38 = this.field38 + highlightimpl41.method4();
         int number2 = this.field14.method3(CoordinatesType.FISHING);
         String text3 = highlightimpl41.method7() > 0L ? String.format("%.2f", highlightimpl41.method6()) + "%" : "MAX";
         this.field40 = "Fishing " + number2 + ": " + text3;
      }
   }

   private void method3(com.moonsworth.lunar.client.event.mixin.gui.EventTabListUpdate highlightimpl31) {
      this.field41 = null;
      ScoreboardSection lotusfish32 = (ScoreboardSection)this.field15.method6().get("pet");
      if (lotusfish32 != null) {
         for (Component component4 : lotusfish32.method2()) {
            String text5 = TextBridge.asLegacyString(component4);
            Matcher matcher6 = field8.matcher(text5);
            if (matcher6.matches()) {
               this.field41 = ItemRarity.fromCode(matcher6.group("rarity").charAt(0));
               this.field42 = Integer.parseInt(matcher6.group("level"));
               return;
            }
         }
      }
   }

   private void method4(EventTick highlightimpl21) {
      WorldBridgeExtension itemcounter6extension2 = Ref.method8();
      Bridge5Extension_5 bridge5extension_53 = Ref.method7();
      if (itemcounter6extension2 != null && bridge5extension_53 != null) {
         if (this.field41 != null) {
            double value4 = this.field42 * switch (this.field41) {
               case COMMON -> 0.03;
               case UNCOMMON -> 0.04;
               case RARE -> 0.04;
               case EPIC -> 0.05;
               case LEGENDARY -> 0.05;
               default -> 0.03;
            };

            double value6 = switch (this.field41) {
               case COMMON -> 15.0;
               case UNCOMMON -> 20.0;
               case RARE -> 20.0;
               case EPIC -> 25.0;
               case LEGENDARY -> 25.0;
               default -> 15.0;
            };
            int index8 = 0;

            for (Bridge6_10 bridge6_1010 : itemcounter6extension2.bridge$getPlayerEntities()) {
               if (!bridge6_1010.bridge$isSelf() && !NpcUtils.method2(bridge6_1010, true) && bridge6_1010.HORHROIOIOICIRHIOCOICHHHIHCIIO(bridge5extension_53) <= 100.0) {
                  index8++;
               }
            }

            double value11 = ClampUtils.clamp(value4 * index8, 0.0, value6);
            this.field39 = String.format("%.2f", value11) + "%";
         }
      }
   }

   private void method5(EventTick highlightimpl21) {
      EntityFishHookBridge bridgeextension232 = this.field16.method15();
      if (bridgeextension232 != null && !bridgeextension232.bridge$isRemoved()) {
         if (this.field16.method12()) {
            this.field34 = Ref.method3().bridge$getSystemTime();
            if (!this.isActive) {
               this.isActive = true;
               this.field33 = Ref.method3().bridge$getSystemTime();
               this.field36 = 0;
               this.field38 = 0.0;
            }
         }
      }
   }

   private void method6(com.moonsworth.lunar.client.event.mixin.fishing.EventSecond highlightimpl41) {
      if (this.isActive) {
         this.field35 = TimeFormatting.method1(Ref.method3().bridge$getSystemTime() - this.field33);
         if (this.field34 + 120000L < Ref.method3().bridge$getSystemTime()) {
            this.isActive = false;
         }
      }
   }

   private void method7(EventWorldChange data31) {
      this.isActive = false;
   }

   private boolean method8(ItemStackBridge bridgeextension_41) {
      if (bridgeextension_41 != null && !bridgeextension_41.bridge$isEmpty()) {
         if (bridgeextension_41.bridge$getItem() != Bridge.method28().method2()) {
            return false;
         }

         String text2 = SkyblockItemUtil.method2(bridgeextension_41);
         return !field11.contains(text2);
      } else {
         return false;
      }
   }

   public void method3(boolean flag1) {
      if (flag1) {
         this.method13();
      }
   }

   private void method13() {
      Member member1 = this.field17.method9();
      if (member1 != null) {
         Double value2 = (Double)member1.playerStats().pets().milestone().seaCreaturesKilled().orElse(null);
         if (value2 != null) {
            this.field37 = value2.longValue();
         }
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.HUD, arg1x -> {
         arg1x.method9(new ClientOption[]{this.field18});
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field19, arg1xx -> arg1xx.method9(new ClientOption[]{this.field26}));
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field20, arg1xx -> arg1xx.method9(new ClientOption[]{this.field27}));
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field21, arg1xx -> arg1xx.method9(new ClientOption[]{this.field28}));
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field22, arg1xx -> arg1xx.method9(new ClientOption[]{this.field29}));
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field23, arg1xx -> arg1xx.method9(new ClientOption[]{this.field30}));
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field24, arg1xx -> arg1xx.method9(new ClientOption[]{this.field31}));
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field25, arg1xx -> arg1xx.method9(new ClientOption[]{this.field32}));
      });
   }

   public String getId() {
      return "SKYBLOCK_FISHING_INFO_HUD";
   }

   private class Data extends TypedHudRenderer<List<HudLine>> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_CENTER, true, true);
      }

      public HudSize method15() {
         return HudSize.method1(15, 120, 200, 60, 180, 300);
      }

      @Nullable
      public List<HudLine> method2(boolean flag1) {
         if (flag1) {
            return this.method3(true, "12m 34s", 251, 5327L, 18425.0, true, "5.00%", "Fishing 32: 46.18%");
         }

         if ((Boolean)SkyblockFishingInfoHud.this.field18.get()) {
            Bridge5Extension_5 bridge5extension_52 = Ref.method7();
            if (bridge5extension_52 == null) {
               return null;
            }

            boolean flag3 = bridge5extension_52.bridge$getInventory()
               .bridge$getMainInventory()
               .stream()
               .limit(9L)
               .anyMatch(arg1x -> SkyblockFishingInfoHud.this.method8(arg1x));
            if (!flag3) {
               return null;
            }
         }

         return this.method3(
            SkyblockFishingInfoHud.this.isActive,
            SkyblockFishingInfoHud.this.field35,
            SkyblockFishingInfoHud.this.field36,
            SkyblockFishingInfoHud.this.field37,
            SkyblockFishingInfoHud.this.field38,
            SkyblockFishingInfoHud.this.field41 != null,
            SkyblockFishingInfoHud.this.field39,
            SkyblockFishingInfoHud.this.field40
         );
      }

      private List<HudLine> method3(boolean flag1, String text2, int number3, long number4, double value6, boolean flag8, String text9, String text10) {
         ItemsBridge bridge2_2111 = Bridge.method28();
         ArrayList list12 = new ArrayList();
         if ((Boolean)SkyblockFishingInfoHud.this.field19.get()) {
            list12.add(
               new HudLine(
                  bridge2_2111.method2(),
                  Component.text(
                     SkyblockFishingInfoHud.this.method20("title", new Object[0])
                        + " ("
                        + SkyblockFishingInfoHud.this.method20(flag1 ? "active" : "inactive", new Object[0])
                        + ")",
                     TextColor.color(SkyblockFishingInfoHud.this.field26.method14(0.0F))
                  )
               )
            );
         }

         if ((Boolean)SkyblockFishingInfoHud.this.field20.get()) {
            String text13 = text2 != null ? text2 : SkyblockFishingInfoHud.this.method20("startFishing", new Object[0]);
            list12.add(
               new HudLine(
                  bridge2_2111.method20(),
                  TextComponentFactory.builder()
                     .method2(SkyblockFishingInfoHud.this.method20("fishingTime", new Object[0]))
                     .method4(text13)
                     .method10(SkyblockFishingInfoHud.this.field27.method14(0.0F))
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockFishingInfoHud.this.field21.get()) {
            list12.add(
               new HudLine(
                  SkyblockFishingInfoHud.field13,
                  TextComponentFactory.builder()
                     .method2(SkyblockFishingInfoHud.this.method20("seaCreatures", new Object[0]))
                     .method4(SkyblockFishingInfoHud.field9.format(number3))
                     .method10(SkyblockFishingInfoHud.this.field28.method14(0.0F))
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockFishingInfoHud.this.field22.get()) {
            list12.add(
               new HudLine(
                  bridge2_2111.method27(),
                  TextComponentFactory.builder()
                     .method2(SkyblockFishingInfoHud.this.method20("milestone", new Object[0]))
                     .method4(SkyblockFishingInfoHud.field9.format(number4))
                     .method10(SkyblockFishingInfoHud.this.field29.method14(0.0F))
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockFishingInfoHud.this.field23.get()) {
            list12.add(
               new HudLine(
                  bridge2_2111.method45(),
                  TextComponentFactory.builder()
                     .method2(SkyblockFishingInfoHud.this.method20("sessionXp", new Object[0]))
                     .method4(SkyblockFishingInfoHud.field9.format(value6))
                     .method10(SkyblockFishingInfoHud.this.field30.method14(0.0F))
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockFishingInfoHud.this.field24.get() && flag8) {
            list12.add(
               new HudLine(
                  SkyblockFishingInfoHud.field12,
                  TextComponentFactory.builder()
                     .method2(SkyblockFishingInfoHud.this.method20("dolphinBoost", new Object[0]))
                     .method4(text9)
                     .method10(SkyblockFishingInfoHud.this.field31.method14(0.0F))
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockFishingInfoHud.this.field25.get() && text10 != null) {
            list12.add(new HudLine(bridge2_2111.method2(), Component.text(text10, TextColor.color(SkyblockFishingInfoHud.this.field32.method14(0.0F)))));
         }

         return list12;
      }

      protected boolean method23() {
         return true;
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }
   }
}
