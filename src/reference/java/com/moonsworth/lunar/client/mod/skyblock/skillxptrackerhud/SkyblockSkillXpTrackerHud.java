package com.moonsworth.lunar.client.mod.skyblock.skillxptrackerhud;

import com.moonsworth.lunar.bridge.Bridge;
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
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkillXpListener;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkillXpUpdateEvent;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkyblockProfileEvents.SkyblockProfileChangeEvent;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.text.TimeFormatting;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class SkyblockSkillXpTrackerHud extends AbstractFeature {
   private final SkillXpListener skillXpListener = (SkillXpListener)this.method6(SkillXpListener.class);
   private final ToggleOption showSkillXpGained = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showSkillXpGained").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showSkillXpPerHour = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showSkillXpPerHour").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showTimeUntilNextLevel = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showTimeUntilNextLevel").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showTimeUntilMaxLevel = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showTimeUntilMaxLevel").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption skillXpTrackerResetTimeout = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skillXpTrackerResetTimeout")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final IntegerOption skillXpTrackerTimeoutLength = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                  "skillXpTrackerTimeoutLength"
               )
               .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(60))
            .method7(10, 600))
         .method17(() -> !(Boolean)this.skillXpTrackerResetTimeout.get()))
      .method31();
   private final ColorOption skillTextColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "skillTextColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ColorOption xpTextColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "xpTextColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-5592406))
      .method31();
   private final Map<CoordinatesType, Double> sessionXp = new HashMap<>();
   private final Map<CoordinatesType, Double> xpPerHour = new HashMap<>();
   private final Map<CoordinatesType, Double> xpToNextLevel = new HashMap<>();
   private boolean sessionActive;
   private long sessionStartTime;
   private long lastXpTime;

   public SkyblockSkillXpTrackerHud(Skyblock skyblock1) {
      super(false);
      this.method1(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method1(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockSkillXpTrackerHud.Data()));
      this.method1(ModTraits.field17, ModCategories.method2(SettingsPage.SKILLS));
      this.method1(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(SkillXpUpdateEvent.class, this::method1);
      this.handle(com.moonsworth.lunar.client.event.mixin.fishing.EventSecond.class, this::method2);
      this.handle(SkyblockProfileChangeEvent.class, this::method3);
   }

   private void method1(SkillXpUpdateEvent highlightimpl41) {
      CoordinatesType coordinatestype2 = highlightimpl41.method1();
      if (coordinatestype2.isTrackable()) {
         if (!this.sessionActive) {
            this.startSession();
         }

         this.sessionXp.put(coordinatestype2, this.sessionXp.getOrDefault(coordinatestype2, 0.0) + highlightimpl41.method4());
         this.lastXpTime = Ref.method3().bridge$getSystemTime();
         this.xpToNextLevel.put(coordinatestype2, highlightimpl41.method7() - highlightimpl41.method5());
      }
   }

   private void method2(com.moonsworth.lunar.client.event.mixin.fishing.EventSecond highlightimpl41) {
      if (this.sessionActive) {
         float value2 = (float)(Ref.method3().bridge$getSystemTime() - this.sessionStartTime);
         float value3 = value2 / 1000.0F / 60.0F / 60.0F;

         for (Entry entry5 : this.sessionXp.entrySet()) {
            double value6 = (Double)entry5.getValue() / value3;
            this.xpPerHour.put((CoordinatesType)entry5.getKey(), value6);
         }
      }

      long number8 = Ref.method3().bridge$getSystemTime() - this.lastXpTime;
      if (number8 / 1000L > ((Integer)this.skillXpTrackerTimeoutLength.get()).intValue() && (Boolean)this.skillXpTrackerResetTimeout.get()) {
         this.resetSession(false);
      }
   }

   private void method3(SkyblockProfileChangeEvent data151) {
      this.resetSession(false);
   }

   private void resetSession(boolean flag1) {
      this.sessionActive = false;
      if (flag1) {
         this.sessionStartTime = 0L;
      }
   }

   private void startSession() {
      this.sessionActive = true;
      this.sessionStartTime = Ref.method3().bridge$getSystemTime();
      this.lastXpTime = Ref.method3().bridge$getSystemTime();
      this.sessionXp.clear();
      this.xpPerHour.clear();
      this.xpToNextLevel.clear();
   }

   public String getId() {
      return "SKYBLOCK_SKILL_XP_TRACKER_HUD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
            new OptionProvider[]{
               this.showSkillXpGained, this.showSkillXpPerHour, this.showTimeUntilNextLevel, this.showTimeUntilMaxLevel, this.skillXpTrackerResetTimeout, this.skillXpTrackerTimeoutLength, OptionFactory.method14("reset").method4(() -> this.resetSession(true))
            }
         )
      );
      lightingextension231.method7(
         SettingsPage.COLOR, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.skillTextColor, this.xpTextColor})
      );
   }

   private class Data extends TypedHudRenderer<List<HudLine>> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT, true, true);
      }

      public HudSize method15() {
         return HudSize.method1(10, 100, 1000, 10, 100, 1000);
      }

      public List<HudLine> method2(boolean flag1) {
         if (flag1) {
            return this.method3(CoordinatesType.COMBAT, true, 1234567.0, 2500000.0, 1875000.0, 5.5E7);
         }

         TextComponent text2 = Component.text(
            SkyblockSkillXpTrackerHud.this.method3("skillXpTracker", new Object[0]), NamedTextColor.GOLD, new TextDecoration[]{TextDecoration.BOLD}
         );
         TextComponent text3 = Component.text(SkyblockSkillXpTrackerHud.this.method3("inactive", new Object[0]), NamedTextColor.RED);
         if (SkyblockSkillXpTrackerHud.this.sessionStartTime == 0L) {
            return List.of(new HudLine(text2.append(text3)));
         }

         CoordinatesType coordinatestype4 = SkyblockSkillXpTrackerHud.this.skillXpListener.method17();
         if (coordinatestype4 == null) {
            return List.of(new HudLine(text2.append(text3)));
         }

         double value5 = SkyblockSkillXpTrackerHud.this.xpPerHour.getOrDefault(coordinatestype4, 0.0);
         double value7 = SkyblockSkillXpTrackerHud.this.xpToNextLevel.getOrDefault(coordinatestype4, 0.0);
         double value9 = com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.SkillLevelCalculator.method6(coordinatestype4);
         double value11 = value9 - SkyblockSkillXpTrackerHud.this.skillXpListener.method4(coordinatestype4);
         return this.method3(coordinatestype4, SkyblockSkillXpTrackerHud.this.sessionActive, SkyblockSkillXpTrackerHud.this.sessionXp.getOrDefault(coordinatestype4, 0.0), value5, value7, value11);
      }

      private List<HudLine> method3(CoordinatesType coordinatestype1, boolean flag2, double value3, double value5, double value7, double value9) {
         TextComponent text11 = Component.text(
            SkyblockSkillXpTrackerHud.this.method3("skillXpTracker", new Object[0]), NamedTextColor.GOLD, new TextDecoration[]{TextDecoration.BOLD}
         );
         TextComponent text12 = Component.text(SkyblockSkillXpTrackerHud.this.method3("inactive", new Object[0]), NamedTextColor.RED);
         ArrayList list13 = new ArrayList();
         list13.add(new HudLine((Component)(flag2 ? text11 : text11.append(text12))));
         if ((Boolean)SkyblockSkillXpTrackerHud.this.showSkillXpGained.get()) {
            list13.add(
               new HudLine(
                  coordinatestype1.getIcon(),
                  TextComponentFactory.builder()
                     .method2(SkyblockSkillXpTrackerHud.this.method3("session", new Object[]{coordinatestype1.getText()}))
                     .method4(NumberUtils.method12(value3))
                     .method6(SkyblockSkillXpTrackerHud.this.skillTextColor.method14(0.0F))
                     .method8(SkyblockSkillXpTrackerHud.this.xpTextColor.method14(0.0F))
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockSkillXpTrackerHud.this.showSkillXpPerHour.get()) {
            list13.add(
               new HudLine(
                  Bridge.method28().method20(),
                  TextComponentFactory.builder()
                     .method2(SkyblockSkillXpTrackerHud.this.method3("perHour", new Object[]{coordinatestype1.getText()}))
                     .method4(NumberUtils.method12(value5))
                     .method6(SkyblockSkillXpTrackerHud.this.skillTextColor.method14(0.0F))
                     .method8(SkyblockSkillXpTrackerHud.this.xpTextColor.method14(0.0F))
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockSkillXpTrackerHud.this.showTimeUntilNextLevel.get() && value7 > 0.0) {
            String text14 = value5 > 0.0
               ? TimeFormatting.method1((long)(value7 / value5 * 3600000.0))
               : SkyblockSkillXpTrackerHud.this.method3("never", new Object[0]);
            list13.add(
               new HudLine(
                  Bridge.method28().method27(),
                  TextComponentFactory.builder()
                     .method2(SkyblockSkillXpTrackerHud.this.method3("untilNext", new Object[0]))
                     .method4(text14)
                     .method6(SkyblockSkillXpTrackerHud.this.skillTextColor.method14(0.0F))
                     .method8(SkyblockSkillXpTrackerHud.this.xpTextColor.method14(0.0F))
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockSkillXpTrackerHud.this.showTimeUntilMaxLevel.get() && value9 > 0.0) {
            String text15 = value5 > 0.0
               ? TimeFormatting.method1((long)(value9 / value5 * 3600000.0))
               : SkyblockSkillXpTrackerHud.this.method3("never", new Object[0]);
            list13.add(
               new HudLine(
                  Bridge.method28().method28(),
                  TextComponentFactory.builder()
                     .method2(SkyblockSkillXpTrackerHud.this.method3("untilMax", new Object[0]))
                     .method4(text15)
                     .method6(SkyblockSkillXpTrackerHud.this.skillTextColor.method14(0.0F))
                     .method8(SkyblockSkillXpTrackerHud.this.xpTextColor.method14(0.0F))
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         return list13;
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

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }
   }
}
