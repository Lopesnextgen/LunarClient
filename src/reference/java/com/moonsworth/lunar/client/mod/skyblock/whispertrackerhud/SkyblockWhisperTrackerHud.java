package com.moonsworth.lunar.client.mod.skyblock.whispertrackerhud;

import com.google.common.collect.UnmodifiableIterator;
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
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.feature.mod.impl.beeheemoth.mixin.BeeheemothType;
import com.moonsworth.lunar.client.framework.listener.TabListListener;
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
import com.moonsworth.lunar.client.config.option.ConstantName;
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

public class SkyblockWhisperTrackerHud extends AbstractFeature {
   private final HighlightTypeListener highlightTypeListener = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final TabListListener tabListListener = (TabListListener)this.method63(TabListListener.class);
   private final ToggleOption whisperTrackerResetTimeout = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("whisperTrackerResetTimeout")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final IntegerOption whisperTrackerTimeoutLength = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "whisperTrackerTimeoutLength"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(60))
         .method7(10, 600))
      .method31();
   private final Map<BeeheemothType, Integer> sessionWhispers = new EnumMap<>(BeeheemothType.class);
   private final Map<BeeheemothType, String> whispersPerHour = new EnumMap<>(BeeheemothType.class);
   private final Map<BeeheemothType, Integer> lastWidgetCounts = new EnumMap<>(BeeheemothType.class);
   private boolean foundInTabList;
   private boolean sessionActive;
   private long sessionStartTime;
   private long lastWhisperTime;
   private long lastResetHeartTime;

   public SkyblockWhisperTrackerHud(Skyblock skyblock1) {
      super(false);
      this.method5(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method5(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockWhisperTrackerHud.Data()));
      this.method5(ModTraits.field17, ModCategories.method2(SettingsPage.FORAGING));
      this.method5(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventTabListUpdate.class, this::method3);
      this.handle(EventRenderSlot.class, this::method2);
      this.handle(EventSecond.class, this::method1);
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_WHISPER_TRACKER_HUD";
   }

   private void method1(EventSecond highlightimpl41) {
      if (this.sessionActive) {
         for (BeeheemothType beeheemothtype5 : BeeheemothType.values()) {
            float value6 = (float)(Ref.method3().bridge$getSystemTime() - this.sessionStartTime);
            float value7 = value6 / 1000.0F / 60.0F / 60.0F;
            int number8 = (int)(this.sessionWhispers.get(beeheemothtype5).intValue() / value7);
            this.whispersPerHour.put(beeheemothtype5, NumberUtils.method12(number8));
         }
      }

      long number9 = Ref.method3().bridge$getSystemTime() - this.lastWhisperTime;
      if (number9 / 1000L > ((Integer)this.whisperTrackerTimeoutLength.get()).intValue() && (Boolean)this.whisperTrackerResetTimeout.get()) {
         this.method4(false);
      }
   }

   private void method2(EventRenderSlot highlightimpl51) {
      if (this.highlightTypeListener.method7() == SkyblockMenuType.RESET_HOTF) {
         List list2 = SkyblockItemUtil.method15(highlightimpl51.method5().bridge$getItemStack());
         if (!list2.isEmpty()) {
            String text3 = (String)list2.get(0);
            if (text3.startsWith("Reset Heart of the Forest")) {
               this.lastResetHeartTime = Ref.method3().bridge$getSystemTime();
            }
         }
      }
   }

   private void method3(EventTabListUpdate highlightimpl31) {
      this.foundInTabList = false;
      EnumMap map2 = new EnumMap(BeeheemothType.class);
      UnmodifiableIterator unmodifiableiterator3 = this.tabListListener.method6().iterator();

      while (unmodifiableiterator3.hasNext()) {
         String text4 = (String)unmodifiableiterator3.next();

         for (BeeheemothType beeheemothtype8 : BeeheemothType.values()) {
            Matcher matcher9 = beeheemothtype8.getPattern().matcher(text4);
            if (matcher9.find()) {
               try {
                  map2.put(beeheemothtype8, NumberUtils.method9(matcher9.group(1)));
               } catch (NumberFormatException numberformatexception11) {
                  continue;
               }

               this.foundInTabList = true;
            }
         }
      }

      if (this.foundInTabList) {
         if (Ref.method3().bridge$getSystemTime() - this.lastResetHeartTime > 5000L) {
            for (Entry entry13 : map2.entrySet()) {
               BeeheemothType beeheemothtype14 = (BeeheemothType)entry13.getKey();
               int number15 = this.lastWidgetCounts.getOrDefault(beeheemothtype14, -1);
               int number16 = (Integer)entry13.getValue();
               if (number15 != -1 && number15 < number16) {
                  this.method5(beeheemothtype14, number16 - number15);
               }
            }
         }

         this.lastWidgetCounts.putAll(map2);
      }
   }

   private void method4(boolean flag1) {
      this.sessionActive = false;
      if (flag1) {
         this.sessionStartTime = 0L;
      }
   }

   private void startSession() {
      this.sessionActive = true;
      this.sessionStartTime = Ref.method3().bridge$getSystemTime();
      this.lastWhisperTime = Ref.method3().bridge$getSystemTime();
      this.sessionWhispers.clear();
      this.whispersPerHour.clear();

      for (BeeheemothType beeheemothtype4 : BeeheemothType.values()) {
         this.sessionWhispers.put(beeheemothtype4, 0);
         this.whispersPerHour.put(beeheemothtype4, "");
      }
   }

   private void method5(BeeheemothType beeheemothtype1, int number2) {
      if (!this.sessionActive) {
         this.startSession();
      }

      this.sessionWhispers.put(beeheemothtype1, this.sessionWhispers.getOrDefault(beeheemothtype1, 0) + number2);
      this.lastWhisperTime = Ref.method3().bridge$getSystemTime();
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> {
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new OptionProvider[]{OptionFactory.method14("reset").method4(() -> this.method4(true))});
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.whisperTrackerResetTimeout, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.whisperTrackerTimeoutLength}));
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
         if (!flag1) {
            return this.method3(
               SkyblockWhisperTrackerHud.this.foundInTabList,
               SkyblockWhisperTrackerHud.this.sessionStartTime != 0L,
               SkyblockWhisperTrackerHud.this.sessionActive,
               SkyblockWhisperTrackerHud.this.sessionWhispers,
               SkyblockWhisperTrackerHud.this.whispersPerHour
            );
         }

         EnumMap map2 = new EnumMap(BeeheemothType.class);
         EnumMap map3 = new EnumMap(BeeheemothType.class);

         for (BeeheemothType beeheemothtype7 : BeeheemothType.values()) {
            map2.put(beeheemothtype7, 48250);
            map3.put(beeheemothtype7, NumberUtils.method12(184600.0));
         }

         return this.method3(true, true, true, map2, map3);
      }

      private List<HudLine> method3(boolean flag1, boolean flag2, boolean flag3, Map<BeeheemothType, Integer> map4, Map<BeeheemothType, String> map5) {
         TextComponent text6 = Component.text(
            SkyblockWhisperTrackerHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("whisperTracker", new Object[0]),
            NamedTextColor.GOLD,
            new TextDecoration[]{TextDecoration.BOLD}
         );
         TextComponent text7 = Component.text(
            SkyblockWhisperTrackerHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("whisperTrackerInactiveSuffix", new Object[0]), NamedTextColor.RED
         );
         if (!flag1) {
            return List.of(
               new HudLine(text6.append(text7)),
               new HudLine(
                  Bridge.method28().method42(),
                  Component.text(SkyblockWhisperTrackerHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("missingWidgetLine1", new Object[0]), NamedTextColor.GRAY)
               ),
               new HudLine(
                  Bridge.method28().method42(),
                  Component.text(SkyblockWhisperTrackerHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("missingWidgetLine2", new Object[0]), NamedTextColor.GRAY)
               )
            );
         }

         if (!flag2) {
            return List.of(new HudLine(text6.append(text7)));
         }

         ArrayList list8 = new ArrayList();
         list8.add(new HudLine((Component)(flag3 ? text6 : text6.append(text7))));

         for (BeeheemothType beeheemothtype12 : BeeheemothType.values()) {
            list8.add(
               new HudLine(
                  beeheemothtype12.getIcon(),
                  TextComponentFactory.builder()
                     .method2(beeheemothtype12.getId() + SkyblockWhisperTrackerHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("session", new Object[0]))
                     .method4(NumberUtils.method12(((Integer)map4.get(beeheemothtype12)).intValue()))
                     .method5(beeheemothtype12.getColor())
                     .method7(NamedTextColor.GRAY)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
            list8.add(
               new HudLine(
                  Bridge.method28().method20(),
                  TextComponentFactory.builder()
                     .method2(beeheemothtype12.getId() + SkyblockWhisperTrackerHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("perHour", new Object[0]))
                     .method4((String)map5.get(beeheemothtype12))
                     .method5(beeheemothtype12.getColor())
                     .method7(NamedTextColor.GRAY)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         return list8;
      }

      public boolean method4(boolean flag1) {
         return !super.method4(flag1) ? false : IslandUtils.getIsland().containsWhisperSources();
      }

      public boolean method30() {
         return !super.HHRRRCCCHIOCOCRHHHRIHHCCRHORRI() ? false : IslandUtils.getIsland().containsWhisperSources();
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
