package com.moonsworth.lunar.client.mod.skyblock.witherhealthhud;

import com.google.common.collect.Sets;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BossInfoBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
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
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.DungeonFloor;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonFloorListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonScoreListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.AlertDisplayListener;
import com.moonsworth.lunar.client.framework.feature.momentum.MomentumRounding;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderBossBar;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import java.util.Locale;
import java.util.Set;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.apache.commons.lang3.text.WordUtils;

public class SkyblockWitherHealthHud extends AbstractFeature {
   private static final Set<String> WITHER_STUCK_MESSAGES = Sets.newHashSet(
      new String[]{"[BOSS] Maxor: YOU TRICKED ME!", "[BOSS] Maxor: THAT BEAM! IT HURTS! IT HURTS!!", "[BOSS] Storm: Ouch, that hurt!", "[BOSS] Storm: Oof"}
   );
   private final DungeonFloorListener dungeonFloorListener = (DungeonFloorListener)this.method6(DungeonFloorListener.class);
   private final DungeonScoreListener dungeonScoreListener = (DungeonScoreListener)this.method6(DungeonScoreListener.class);
   private final AlertDisplayListener alertDisplayListener = (AlertDisplayListener)this.method6(AlertDisplayListener.class);
   private final ToggleOption witherHealth = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("witherHealth").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption witherHealthInBossBar = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("witherHealthInBossBar").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption witherHealthAsPercentage = (ToggleOption)OptionFactory.method7("witherHealthAsPercentage").method31();
   private final ColorOption witherHealthNameColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "witherHealthNameColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-43691))
      .method31();
   private final ColorOption witherHealthColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "witherHealthColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141291))
      .method31();
   private final ToggleOption witherStuckAlert = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("witherStuckAlert").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final TextOption alertText = (TextOption)((com.moonsworth.lunar.client.config.option.TextOption.Data)OptionFactory.method12(
            "alertText"
         )
         .method2("WITHER STUCK!"))
      .method31();
   private final ColorOption alertColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "alertColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method15()
      .method31();
   private final IntegerOption timeToShow = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "timeToShow"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1))
         .method7(1, 5))
      .method31();
   private final ToggleOption playChime = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("playChime").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private SkyblockWitherHealthHud.Type currentType;
   private float healthFraction;

   public SkyblockWitherHealthHud(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockWitherHealthHud.Data()));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> this.dungeonFloorListener.method6().getNumber() == 7 && this.dungeonScoreListener.method10()));
      this.handle(EventRenderBossBar.class, this::onRenderBossBar);
      this.handle(EventWorldChange.class, this::method2);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::onChatMessage);
   }

   private void onRenderBossBar(EventRenderBossBar highlightimpl101) {
      for (BossInfoBridge bridge2_113 : Bridge.method8().method42()) {
         Component component4 = (Component)bridge2_113.method4().orElse(null);
         if (component4 != null) {
            String text5 = TextBridge.getTextContent(component4);

            for (SkyblockWitherHealthHud.Type type9 : SkyblockWitherHealthHud.Type.values()) {
               if (type9.getName().equals(text5)) {
                  this.currentType = type9;
                  this.healthFraction = bridge2_113.method2();
                  if ((Boolean)this.witherHealthInBossBar.get()) {
                     String text10;
                     if ((Boolean)this.witherHealthAsPercentage.get()) {
                        text10 = MomentumRounding.DECIMAL_2.format(this.healthFraction * 100.0F) + "%";
                     } else {
                        long number11 = this.dungeonFloorListener.method6() == DungeonFloor.M7 ? type9.getMasterModeHealth() : type9.getHealth();
                        long number13 = (long)((float)number11 * this.healthFraction);
                        text10 = NumberUtils.method11(number13, 0, 1) + "/" + NumberUtils.method11(number11, 0, 1);
                     }

                     Component component15 = component4.append(Component.text(" - ")).append(Component.text(text10, TextColor.color(this.witherHealthColor.method14(0.0F))));
                     bridge2_113.method5(component15);
                  }
               }
            }
         }
      }
   }

   private void method2(EventWorldChange data31) {
      this.currentType = null;
   }

   private void onChatMessage(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if (field8.contains(data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH())) {
         TextComponent text2 = Component.text((String)this.alertText.get(), TextColor.color(this.alertColor.method14(0.0F)));
         this.alertDisplayListener.method2(ComparableImpl.method2().method1("F7_WITHER_STUCK").method2(text2).method3((Integer)this.timeToShow.get() * 1000).method6());
         if ((Boolean)this.playChime.get()) {
            IslandUtils.playSound();
         }
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.witherHealth, arg1xx -> arg1xx.method9(new ClientOption[]{this.witherHealthInBossBar, this.witherHealthAsPercentage, this.witherHealthNameColor, this.witherHealthColor})
            );
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.witherStuckAlert, arg1xx -> arg1xx.method9(new ClientOption[]{this.alertText, this.timeToShow, this.playChime, this.alertColor})
            );
         }
      );
   }

   public String getId() {
      return "SKYBLOCK_WITHER_HEALTH_HUD";
   }

   protected ModDetails method20() {
      return ModDetails.method7()
         .method1(new ModCategory[]{ModCategory.field5})
         .method2(new String[]{"f7", "m7", "floor seven", "master seven"})
         .method11(this);
   }

   private class Data extends TypedHudRenderer<HudLine> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_CENTER, false, true);
      }

      public HudSize method15() {
         return HudSize.method1(10, 20, 40, 20, 200, 500);
      }

      public HudLine method2(boolean flag1) {
         if (SkyblockWitherHealthHud.this.currentType != null && !(SkyblockWitherHealthHud.this.healthFraction < 0.01F)) {
            String text7;
            if ((Boolean)SkyblockWitherHealthHud.this.witherHealthAsPercentage.get()) {
               text7 = MomentumRounding.DECIMAL_2.format(SkyblockWitherHealthHud.this.healthFraction * 100.0F) + "%";
            } else {
               long number3 = SkyblockWitherHealthHud.this.dungeonFloorListener.method6() == DungeonFloor.M7
                  ? SkyblockWitherHealthHud.this.currentType.getMasterModeHealth()
                  : SkyblockWitherHealthHud.this.currentType.getHealth();
               long number5 = (long)((float)number3 * SkyblockWitherHealthHud.this.healthFraction);
               text7 = NumberUtils.method11(number5, 0, 1) + "/" + NumberUtils.method11(number3, 0, 1);
            }

            return new HudLine(
               SkyblockWitherHealthHud.this.currentType.getIcon(),
               TextComponentFactory.builder()
                  .method2(SkyblockWitherHealthHud.this.currentType.name())
                  .method6(SkyblockWitherHealthHud.this.witherHealthNameColor.method14(0.0F))
                  .method4(text7)
                  .method8(SkyblockWitherHealthHud.this.witherHealthColor.method14(0.0F))
                  .method12((Boolean)this.CROIRORCCHHHRHOOCRHCHHORCROOIR().get())
                  .build()
            );
         } else if (flag1) {
            String text2 = SkyblockWitherHealthHud.this.witherHealthAsPercentage.get() ? "12.34%" : "12M/100M";
            return new HudLine(
               SkyblockWitherHealthHud.Type.MAXOR.getIcon(),
               TextComponentFactory.builder()
                  .method2(SkyblockWitherHealthHud.Type.MAXOR.getName())
                  .method6(SkyblockWitherHealthHud.this.witherHealthNameColor.method14(0.0F))
                  .method4(text2)
                  .method8(SkyblockWitherHealthHud.this.witherHealthColor.method14(0.0F))
                  .method12((Boolean)this.CROIRORCCHHHRHOOCRHCHHORCROOIR().get())
                  .build()
            );
         } else {
            return null;
         }
      }

      public boolean method4(boolean flag1) {
         return super.method4(flag1) && (Boolean)SkyblockWitherHealthHud.this.witherHealth.get() && !(Boolean)SkyblockWitherHealthHud.this.witherHealthInBossBar.get();
      }

      public boolean method30() {
         return (Boolean)SkyblockWitherHealthHud.this.witherHealth.get() && !(Boolean)SkyblockWitherHealthHud.this.witherHealthInBossBar.get();
      }

      protected boolean method23() {
         return true;
      }
   }

   private enum Type {
      MAXOR(100000000L, 800000000L),
      STORM(400000000L, 1000000000L),
      GOLDOR(750000000L, 1200000000L),
      NECRON(1000000000L, 1400000000L);

      private final String name = WordUtils.capitalizeFully(this.name());
      private final long health;
      private final long masterModeHealth;
      private final ResourceLocationBridge icon;

      Type(long number3, long number5) {
         this.health = number3;
         this.masterModeHealth = number5;
         this.icon = ResourceLocationBridge.create("lunar", "skyblock/hud/" + this.name().toLowerCase(Locale.ROOT) + ".png");
      }

      @Generated
      public String getName() {
         return this.name;
      }

      @Generated
      public long getHealth() {
         return this.health;
      }

      @Generated
      public long getMasterModeHealth() {
         return this.masterModeHealth;
      }

      @Generated
      public ResourceLocationBridge getIcon() {
         return this.icon;
      }
   }
}
