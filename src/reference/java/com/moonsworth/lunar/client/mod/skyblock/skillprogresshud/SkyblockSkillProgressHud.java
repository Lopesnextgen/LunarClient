package com.moonsworth.lunar.client.mod.skyblock.skillprogresshud;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.CoordinatesType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.SkillXpSource;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkillXpListener;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkillXpUpdateEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class SkyblockSkillProgressHud extends AbstractFeature {
   private final ToggleOption showSkillXpGained = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showSkillXpGained").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showSkillProgress = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showSkillProgress").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showSkillPercentage = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showSkillPercentage").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showActionsUntilNextLevel = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showActionsUntilNextLevel")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption textColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "textColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11184641))
      .method31();
   private final IntegerOption displayTime = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "displayTime"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(3))
         .method7(1, 10))
      .method31();
   private final DecimalFormat xpFormat = new DecimalFormat("###,###,###.#");
   private final DecimalFormat percentFormat = new DecimalFormat("###.##");
   private HudLine currentLine;
   private long lastUpdateTime;

   public SkyblockSkillProgressHud(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockSkillProgressHud.Data()));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.SKILLS));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(SkillXpUpdateEvent.class, this::onSkillXpUpdate);
      this.method2(com.moonsworth.lunar.client.event.mixin.EventChatMessage.EventActionBarMessage.class, this::method3, 0);
   }

   private void onSkillXpUpdate(SkillXpUpdateEvent highlightimpl41) {
      if (highlightimpl41.method2() == SkillXpSource.ACTION_BAR) {
         CoordinatesType coordinatestype2 = highlightimpl41.method1();
         this.lastUpdateTime = Ref.method3().bridge$getSystemTime();
         this.currentLine = this.method2(coordinatestype2.getIcon(), highlightimpl41.method3(), highlightimpl41.method5(), highlightimpl41.method6(), highlightimpl41.method7());
      }
   }

   private HudLine method2(ItemStackBridge bridgeextension_41, double value2, double value4, double value6, long number8) {
      ArrayList list10 = new ArrayList();
      if ((Boolean)this.showSkillXpGained.get()) {
         list10.add("+" + this.xpFormat.format(value2));
      }

      if ((Boolean)this.showSkillProgress.get()) {
         if ((Boolean)this.showSkillPercentage.get()) {
            list10.add("(" + this.percentFormat.format(value6) + "%)");
         } else {
            String text11 = "(" + this.xpFormat.format(value4);
            if (number8 > 0.0) {
               text11 = text11 + "/" + NumberUtils.method10(number8);
            }

            list10.add(text11 + ")");
         }
      }

      if ((Boolean)this.showActionsUntilNextLevel.get() && number8 > 0.0) {
         double value14 = number8 - value4;
         int number13 = (int)Math.ceil(value14 / value2);
         if (!list10.isEmpty()) {
            list10.add("-");
         }

         list10.add(this.method3("actionsLeft", new Object[]{this.xpFormat.format(number13)}));
      }

      return new HudLine(bridgeextension_41, Component.text(String.join(" ", list10), TextColor.color(this.textColor.method14(0.0F))));
   }

   private void method3(com.moonsworth.lunar.client.event.mixin.EventChatMessage.EventActionBarMessage data21) {
      String text2 = TextBridge.asLegacyString(data21.RIOCCRROHHHOCHOHHCIRHRCOOHRIHI());
      Matcher matcher3 = SkillXpListener.field11.matcher(text2);
      if (matcher3.find()) {
         text2 = text2.replace(matcher3.group(0), "");
      } else {
         matcher3 = SkillXpListener.field12.matcher(text2);
         if (matcher3.find()) {
            text2 = text2.replace(matcher3.group(0), "");
         }
      }

      data21.<init>(TextBridge.asAdventure(text2));
      data21.OOOHICCHHHRHCORIRCRHOCROROIOCR(true);
   }

   public String getId() {
      return "SKYBLOCK_SKILL_PROGRESS_HUD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> {
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showSkillXpGained});
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.showSkillProgress, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showSkillPercentage}));
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showActionsUntilNextLevel, this.displayTime});
      });
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.textColor}));
   }

   private class Data extends TypedHudRenderer<HudLine> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_CENTER, false, true);
      }

      public HudSize method15() {
         return HudSize.method1(10, 20, 40, 20, 200, 500);
      }

      public HudLine method2(boolean flag1) {
         if (flag1) {
            return SkyblockSkillProgressHud.this.method2(CoordinatesType.COMBAT.getIcon(), 25.0, 88250.5, 88.25, 100000L);
         } else {
            return SkyblockSkillProgressHud.this.lastUpdateTime + SkyblockSkillProgressHud.this.displayTime.get() * 1000
                  <= Ref.method3().bridge$getSystemTime()
               ? null
               : SkyblockSkillProgressHud.this.currentLine;
         }
      }

      protected boolean method17() {
         return false;
      }

      protected boolean method19() {
         return false;
      }
   }
}
