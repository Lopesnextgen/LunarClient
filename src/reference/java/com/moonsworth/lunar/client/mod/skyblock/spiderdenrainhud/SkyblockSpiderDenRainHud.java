package com.moonsworth.lunar.client.mod.skyblock.spiderdenrainhud;

import com.moonsworth.lunar.bridge.Bridge;
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
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.SkyblockCalendar;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
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
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.Nullable;

public class SkyblockSpiderDenRainHud extends AbstractFeature {
   public static final ResourceLocationBridge TOXIC_RAIN_SLIME_TEXTURE = ResourceLocationBridge.create("lunar", "skyblock/entity/toxicrainslime.png");
   private static final ResourceLocationBridge CLOUD_TEXTURE = ResourceLocationBridge.create("lunar", "skyblock/hud/cloud.png");
   private static final ResourceLocationBridge RAIN_TEXTURE = ResourceLocationBridge.create("lunar", "skyblock/hud/rain.png");
   private static final ResourceLocationBridge THUNDER_TEXTURE = ResourceLocationBridge.create("lunar", "skyblock/hud/thunder.png");
   private final ToggleOption showRainHud = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showRainHud").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showNextRain = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showNextRain").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption highlightToxicRainSlime = (ToggleOption)OptionFactory.method7("highlightToxicRainSlime").method31();
   private final ColorOption textColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "textColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-22016))
      .method31();
   private final ColorOption timerColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "timerColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141291))
      .method31();
   private SkyblockSpiderDenRainHud.Type rainType = SkyblockSpiderDenRainHud.Type.NONE;
   private long currentRainTimeLeft;
   private long nextRainTime;
   private long nextThunderTime;

   public SkyblockSpiderDenRainHud(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockSpiderDenRainHud.Data()));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.SPIDERS_DEN));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventSecond.class, this::method1);
   }

   private void method1(EventSecond highlightimpl41) {
      if (IslandUtils.isOnIsland()) {
         long number2 = SkyblockCalendar.getElapsedDaySeconds();
         long number4 = SkyblockCalendar.getElapsedSeconds(3);
         long number6 = SkyblockCalendar.getElapsedSeconds(9);
         if (number6 >= 2400L && number6 <= 3600L) {
            this.rainType = SkyblockSpiderDenRainHud.Type.THUNDER;
            this.currentRainTimeLeft = 1200L - number2;
            this.nextRainTime = 6000L - number4;
            this.nextThunderTime = 13200L - number6;
         } else if (number4 >= 2400L) {
            this.rainType = SkyblockSpiderDenRainHud.Type.RAIN;
            this.currentRainTimeLeft = 1200L - number2;
            this.nextRainTime = 6000L - number4;
            this.nextThunderTime = 2400L - number6;
            if (this.nextThunderTime <= 0L) {
               this.nextThunderTime += 10800L;
            }
         } else {
            this.rainType = SkyblockSpiderDenRainHud.Type.NONE;
            this.currentRainTimeLeft = 0L;
            this.nextRainTime = 2400L - number4;
            this.nextThunderTime = 2400L - number6;
            if (this.nextThunderTime <= 0L) {
               this.nextThunderTime += 10800L;
            }
         }
      }
   }

   public boolean isThundering() {
      return this.rainType == SkyblockSpiderDenRainHud.Type.THUNDER;
   }

   private TextColor getTextColor() {
      return TextColor.color(this.textColor.method14(0.0F));
   }

   private TextColor method15() {
      return TextColor.color(this.timerColor.method14(0.0F));
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.method9(new ClientOption[]{this.showRainHud, this.showNextRain, this.textColor, this.timerColor, this.highlightToxicRainSlime})
      );
   }

   public String getId() {
      return "SKYBLOCK_SPIDER_DEN_RAIN_HUD";
   }

   @Generated
   public ToggleOption method16() {
      return this.highlightToxicRainSlime;
   }

   private class Data extends TypedHudRenderer<List<HudLine>> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT, true, true);
      }

      public HudSize method15() {
         return HudSize.method1(50, 80, 200, 100, 160, 300);
      }

      @Nullable
      public List<HudLine> method2(boolean flag1) {
         return flag1
            ? this.buildLines(SkyblockSpiderDenRainHud.Type.RAIN, 735L, 2535L, 8535L)
            : this.buildLines(
               SkyblockSpiderDenRainHud.this.rainType,
               SkyblockSpiderDenRainHud.this.currentRainTimeLeft,
               SkyblockSpiderDenRainHud.this.nextRainTime,
               SkyblockSpiderDenRainHud.this.nextThunderTime
            );
      }

      private List<HudLine> buildLines(SkyblockSpiderDenRainHud.Type type1, long number2, long number4, long number6) {
         ArrayList list8 = new ArrayList();

         NamedTextColor namedtextcolor9 = switch (type1) {
            case RAIN -> NamedTextColor.AQUA;
            case THUNDER -> NamedTextColor.GOLD;
            case NONE -> NamedTextColor.GRAY;
         };
         list8.add(
            new HudLine(
               SkyblockSpiderDenRainHud.CLOUD_TEXTURE,
               TextComponentFactory.builder()
                  .method2(SkyblockSpiderDenRainHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("status", new Object[0]))
                  .method4(SkyblockSpiderDenRainHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO(type1.getText(), new Object[0]))
                  .method5(SkyblockSpiderDenRainHud.this.getTextColor())
                  .method7(namedtextcolor9)
                  .build()
            )
         );
         if (type1 != SkyblockSpiderDenRainHud.Type.NONE) {
            list8.add(
               new HudLine(
                  Bridge.method28().method20(),
                  TextComponentFactory.builder()
                     .method2(SkyblockSpiderDenRainHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("timeLeft", new Object[0]))
                     .method4(TimeFormatting.method1(number2 * 1000L))
                     .method5(SkyblockSpiderDenRainHud.this.getTextColor())
                     .method7(SkyblockSpiderDenRainHud.this.method15())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockSpiderDenRainHud.this.showNextRain.get()) {
            list8.add(
               new HudLine(
                  SkyblockSpiderDenRainHud.RAIN_TEXTURE,
                  TextComponentFactory.builder()
                     .method2(SkyblockSpiderDenRainHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("nextRain", new Object[0]))
                     .method4(TimeFormatting.method1(number4 * 1000L))
                     .method5(SkyblockSpiderDenRainHud.this.getTextColor())
                     .method7(SkyblockSpiderDenRainHud.this.method15())
                     .build()
               )
            );
            list8.add(
               new HudLine(
                  SkyblockSpiderDenRainHud.THUNDER_TEXTURE,
                  TextComponentFactory.builder()
                     .method2(SkyblockSpiderDenRainHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("nextThunder", new Object[0]))
                     .method4(TimeFormatting.method1(number6 * 1000L))
                     .method5(SkyblockSpiderDenRainHud.this.getTextColor())
                     .method7(SkyblockSpiderDenRainHud.this.method15())
                     .build()
               )
            );
         }

         return list8;
      }

      public boolean method4(boolean flag1) {
         return !SkyblockSpiderDenRainHud.this.showRainHud.get() ? false : super.method4(flag1);
      }

      public boolean method30() {
         return !SkyblockSpiderDenRainHud.this.showRainHud.get() ? false : super.HHRRRCCCHIOCOCRHHHRIHHCCRHORRI();
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }
   }

   private enum Type {
      NONE("none"),
      RAIN("rain"),
      THUNDER("thunder");

      private final String text;

      @Generated
      Type(String text3) {
         this.text = text3;
      }

      @Generated
      public String getText() {
         return this.text;
      }
   }
}
