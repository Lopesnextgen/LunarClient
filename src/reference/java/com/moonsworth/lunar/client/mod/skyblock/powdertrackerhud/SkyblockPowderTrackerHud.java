package com.moonsworth.lunar.client.mod.skyblock.powdertrackerhud;

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
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.ScoreboardSection;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockScoreboardParser;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.feature.mod.impl.alert.mixin.AlertType;
import com.moonsworth.lunar.client.event.render.EventRenderSlot;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.gui.EventTabListUpdate;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class SkyblockPowderTrackerHud extends AbstractFeature {
   private final HighlightTypeListener field8 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final SkyblockScoreboardParser field9 = (SkyblockScoreboardParser)this.method63(SkyblockScoreboardParser.class);
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("powderTrackerResetTimeout")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final IntegerOption field11 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "powderTrackerTimeoutLength"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(60))
         .method7(10, 600))
      .method31();
   private final Map<AlertType, Integer> field12 = new EnumMap<>(AlertType.class);
   private final Map<AlertType, String> field13 = new EnumMap<>(AlertType.class);
   private final Map<AlertType, Integer> field14 = new EnumMap<>(AlertType.class);
   private boolean field15;
   private boolean field16;
   private long field17;
   private long field18;
   private long field19;

   public SkyblockPowderTrackerHud(Skyblock skyblock1) {
      super(false);
      this.method5(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method5(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockPowderTrackerHud.Data()));
      this.method5(ModTraits.field17, ModCategories.method2(SettingsPage.MINING));
      this.method5(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventTabListUpdate.class, this::method3);
      this.handle(EventRenderSlot.class, this::method2);
      this.handle(EventSecond.class, this::method1);
   }

   public String getId() {
      return "SKYBLOCK_POWDER_TRACKER_HUD";
   }

   private void method1(EventSecond highlightimpl41) {
      if (this.field16) {
         for (AlertType alerttype5 : AlertType.values()) {
            float value6 = (float)(Ref.method3().bridge$getSystemTime() - this.field17);
            float value7 = value6 / 1000.0F / 60.0F / 60.0F;
            int number8 = (int)(this.field12.get(alerttype5).intValue() / value7);
            this.field13.put(alerttype5, NumberUtils.method12(number8));
         }
      }

      long number9 = Ref.method3().bridge$getSystemTime() - this.field18;
      if (number9 / 1000L > ((Integer)this.field11.get()).intValue() && (Boolean)this.field10.get()) {
         this.method4(false);
      }
   }

   private void method2(EventRenderSlot highlightimpl51) {
      if (this.field8.method7() == SkyblockMenuType.RESET_HOTM) {
         List list2 = SkyblockItemUtil.method15(highlightimpl51.method5().bridge$getItemStack());
         if (!list2.isEmpty()) {
            String text3 = (String)list2.get(0);
            if (text3.startsWith("Reset Heart of the Mountain")) {
               this.field19 = Ref.method3().bridge$getSystemTime();
            }
         }
      }
   }

   private void method3(EventTabListUpdate highlightimpl31) {
      ScoreboardSection lotusfish32 = (ScoreboardSection)this.field9.method6().get("powder");
      if (lotusfish32 == null) {
         this.field15 = false;
      } else {
         this.field15 = true;
         EnumMap map3 = new EnumMap(AlertType.class);

         for (String text5 : lotusfish32.method3()) {
            for (AlertType alerttype9 : AlertType.values()) {
               Matcher matcher10 = alerttype9.getPattern().matcher(text5);
               if (matcher10.find()) {
                  map3.put(alerttype9, Integer.parseInt(matcher10.group(1).replaceAll(",", "")));
               }
            }
         }

         if (Ref.method3().bridge$getSystemTime() - this.field19 > 5000L) {
            for (Entry entry12 : map3.entrySet()) {
               AlertType alerttype13 = (AlertType)entry12.getKey();
               int number14 = this.field14.getOrDefault(alerttype13, -1);
               int number15 = (Integer)entry12.getValue();
               if (number14 != -1 && number14 < number15) {
                  this.method5(alerttype13, number15 - number14);
               }
            }
         }

         this.field14.putAll(map3);
      }
   }

   private void method4(boolean flag1) {
      this.field16 = false;
      if (flag1) {
         this.field17 = 0L;
      }
   }

   private void startSession() {
      this.field16 = true;
      this.field17 = Ref.method3().bridge$getSystemTime();
      this.field18 = Ref.method3().bridge$getSystemTime();
      this.field12.clear();
      this.field13.clear();

      for (AlertType alerttype4 : AlertType.values()) {
         this.field12.put(alerttype4, 0);
         this.field13.put(alerttype4, "");
      }
   }

   private void method5(AlertType alerttype1, int number2) {
      if (!this.field16) {
         this.startSession();
      }

      this.field12.put(alerttype1, this.field12.getOrDefault(alerttype1, 0) + number2);
      this.field18 = Ref.method3().bridge$getSystemTime();
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> {
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new OptionProvider[]{OptionFactory.method14("reset").method4(() -> this.method4(true))});
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field10, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field11}));
      });
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
            EnumMap map2 = new EnumMap(AlertType.class);
            map2.put(AlertType.MITHRIL, 1234567);
            map2.put(AlertType.GEMSTONE, 456789);
            map2.put(AlertType.GLACITE, 678901);
            EnumMap map3 = new EnumMap(AlertType.class);
            map3.put(AlertType.MITHRIL, NumberUtils.method12(1543209.0));
            map3.put(AlertType.GEMSTONE, NumberUtils.method12(570986.0));
            map3.put(AlertType.GLACITE, NumberUtils.method12(848626.0));
            return this.method3(true, true, true, map2, map3);
         } else {
            return this.method3(
               SkyblockPowderTrackerHud.this.field15,
               SkyblockPowderTrackerHud.this.field17 != 0L,
               SkyblockPowderTrackerHud.this.field16,
               SkyblockPowderTrackerHud.this.field12,
               SkyblockPowderTrackerHud.this.field13
            );
         }
      }

      private List<HudLine> method3(boolean flag1, boolean flag2, boolean flag3, Map<AlertType, Integer> map4, Map<AlertType, String> map5) {
         TextComponent text6 = Component.text(
            SkyblockPowderTrackerHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("powderTracker", new Object[0]),
            NamedTextColor.GOLD,
            new TextDecoration[]{TextDecoration.BOLD}
         );
         TextComponent text7 = Component.text(
            SkyblockPowderTrackerHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("powderTrackerInactiveSuffix", new Object[0]), NamedTextColor.RED
         );
         if (!flag1) {
            return List.of(
               new HudLine(text6.append(text7)),
               new HudLine(
                  Bridge.method28().method42(),
                  Component.text(SkyblockPowderTrackerHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("missingWidgetLine1", new Object[0]), NamedTextColor.GRAY)
               ),
               new HudLine(
                  Bridge.method28().method42(),
                  Component.text(SkyblockPowderTrackerHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("missingWidgetLine2", new Object[0]), NamedTextColor.GRAY)
               )
            );
         }

         if (!flag2) {
            return List.of(new HudLine(text6.append(text7)));
         }

         ArrayList list8 = new ArrayList();
         list8.add(new HudLine((Component)(flag3 ? text6 : text6.append(text7))));

         for (AlertType alerttype12 : AlertType.values()) {
            list8.add(
               new HudLine(
                  alerttype12.getIcon(),
                  TextComponentFactory.builder()
                     .method2(alerttype12.getId() + SkyblockPowderTrackerHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("session", new Object[0]))
                     .method4(NumberUtils.method12(((Integer)map4.get(alerttype12)).intValue()))
                     .method5(alerttype12.getColor())
                     .method7(NamedTextColor.GRAY)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
            list8.add(
               new HudLine(
                  Bridge.method28().method20(),
                  TextComponentFactory.builder()
                     .method2(alerttype12.getId() + SkyblockPowderTrackerHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("perHour", new Object[0]))
                     .method4((String)map5.get(alerttype12))
                     .method5(alerttype12.getColor())
                     .method7(NamedTextColor.GRAY)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         return list8;
      }

      public boolean method4(boolean flag1) {
         return !super.method4(flag1) ? false : IslandUtils.getIsland().containsPowderSources();
      }

      public boolean method30() {
         return !super.HHRRRCCCHIOCOCRHHHRIHHCCRHORRI() ? false : IslandUtils.getIsland().containsPowderSources();
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
