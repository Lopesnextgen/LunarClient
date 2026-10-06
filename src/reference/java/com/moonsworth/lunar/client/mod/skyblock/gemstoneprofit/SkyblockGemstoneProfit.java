package com.moonsworth.lunar.client.mod.skyblock.gemstoneprofit;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudComponent;
import com.moonsworth.lunar.client.ui.hud.TextHudComponent;
import com.moonsworth.lunar.client.ui.hud.HudComponentGroup;
import com.moonsworth.lunar.client.ui.hud.TitledHudElement;
import com.moonsworth.lunar.client.ui.hud.HudComponentGroup.Type;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WidgetFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockGemstoneProfit extends AbstractFeature {
   private final GuiRewindhandlersHandler2 field8 = (GuiRewindhandlersHandler2)this.method63(GuiRewindhandlersHandler2.class);
   private static final Pattern field9 = Pattern.compile("^PRISTINE! You found .{0,5} Flawed (?<type>\\w+) Gemstone x(?<count>\\d+).+$");
   private static final String[] field10 = new String[]{
      "JADE", "AMBER", "TOPAZ", "SAPPHIRE", "AMETHYST", "JASPER", "RUBY", "OPAL", "ONYX", "CITRINE", "PERIDOT"
   };
   private static final String[] field11 = new String[]{"ROUGH", "FLAWED", "FINE", "FLAWLESS", "PERFECT"};
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("useBazzarPrice").method4(true))
      .method31();
   private Map<String, Integer> field13 = new HashMap<>();
   private double field14 = 0.0;
   private long field15 = 0L;
   private long field16 = 0L;
   private long field17 = 0L;
   private double field18;

   public SkyblockGemstoneProfit(Skyblock skyblock1) {
      super(false);
      this.method7(ModTraits.field16, ChildModBinding.method4(true, skyblock1));
      this.method7(ModTraits.field17, ModCategories.method2(SettingsPage.MINING));
      this.method7(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockGemstoneProfit.Data()));
      this.method7(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() != null && IslandUtils.getIsland().containsGemstones()));
      this.method5(this::reset);
      this.method50(this::onEnable);
      this.handle(EventTick.class, this::method2);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method4);
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_GEMSTONE_PROFIT";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field12});
   }

   private void reset() {
      this.field16 = 0L;
      this.field15 = 0L;
      this.field14 = 0.0;
      this.field18 = 0.0;
   }

   private void onEnable() {
      this.method13();
      this.reset();
   }

   private void method2(EventTick highlightimpl21) {
      this.method13();
      if (this.field16 != 0L && Ref.method3().bridge$getSystemTime() - this.field16 > 300000L) {
         this.reset();
      }
   }

   private void method13() {
      if (Ref.method7() != null) {
         HashMap map1 = new HashMap();

         for (ItemStackBridge bridgeextension_43 : Ref.method7().bridge$getInventory().bridge$getMainInventory()) {
            String text4 = SkyblockItemUtil.method2(bridgeextension_43);
            if (text4.endsWith("_GEM")) {
               for (String text8 : field10) {
                  if (text4.endsWith("_" + text8 + "_GEM")) {
                     byte number9 = 1;

                     for (String text13 : field11) {
                        if (text4.equals(text13 + "_" + text8 + "_GEM")) {
                           int number14 = number9 * bridgeextension_43.bridge$getStackSize();
                           map1.compute(text8, (arg1x, arg2) -> arg2 == null ? number14 : arg2 + number14);
                        }

                        number9 *= 80;
                     }
                  }
               }
            }
         }

         if (Ref.method3().bridge$hasInGameFocus()) {
            for (Entry entry16 : map1.entrySet()) {
               int number17 = this.field13.getOrDefault(entry16.getKey(), 0);
               if ((Integer)entry16.getValue() > number17) {
                  this.field17 = Ref.method3().bridge$getSystemTime();
                  this.method5((Integer)entry16.getValue() - number17, (String)entry16.getKey());
               }
            }
         }

         this.field13 = map1;
      }
   }

   private void method4(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if (Ref.method3().bridge$getSystemTime() - this.field17 >= 5000L) {
         String text2 = data1.OROIIOCCOORRCRCIIHHOCCCRHICRCC();
         Matcher matcher3 = field9.matcher(text2);
         if (matcher3.matches()) {
            int number4 = Integer.parseInt(matcher3.group("count"));
            String text5 = matcher3.group("type").toUpperCase();
            this.method5(number4 * 80, text5);
         }
      }
   }

   private void method5(int number1, String text2) {
      if ((Boolean)this.field12.get()) {
         this.method6(3 * number1);
      } else {
         com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2.Data2 data23 = (com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2.Data2)this.field8
            .method9()
            .get("FINE_" + text2 + "_GEM");
         if (data23 != null) {
            this.method6(number1 * data23.method4().method2() / 80.0F / 80.0F);
         }
      }
   }

   private void method6(double value1) {
      long number3 = Ref.method3().bridge$getSystemTime();
      this.field16 = number3;
      if (this.field14 == 0.0) {
         this.field15 = number3;
         this.field14 += value1;
      } else {
         this.field14 += value1;
         this.field18 = Math.floor(this.field14 / ((number3 - this.field15) / 3600000.0));
      }
   }

   private class Data extends TitledHudElement {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT);
         this.method1(arg1x -> arg1x || SkyblockGemstoneProfit.this.field15 != 0L);
      }

      protected HudComponent method5() {
         return new HudComponentGroup(Type.CENTER)
            .method5(WidgetFactory.createItem(Bridge.method28().method40()).method6(1.4))
            .method5(new TextHudComponent(() -> SkyblockGemstoneProfit.this.method2("gemstoneProfit", new Object[0]), this.IOOICCHORRCHIIIOROOIRCICIIRRCR()));
      }

      protected void method3(HudComponentGroup mixincore5iterator1) {
         mixincore5iterator1.method5(
            new HudComponentGroup()
               .method5(new TextHudComponent("$/h: ", this.IRIHIHRICICCRIICHOHROCCHCOIIHH()))
               .method5(new TextHudComponent("$", this.HCOICHOHHHHOHHICHHCOIOROOHCOHI()))
               .method5(
                  new TextHudComponent()
                     .method2(() -> this.OHCROHOHCIHICOCIHROIRCOIIICRCI ? "12,345,678" : String.format("%,.0f", SkyblockGemstoneProfit.this.field18))
                     .method7(this.HCOICHOHHHHOHHICHHCOIOROOHCOHI())
               )
         );
         mixincore5iterator1.method5(
            new HudComponentGroup()
               .method5(new TextHudComponent(() -> SkyblockGemstoneProfit.this.method2("profit", new Object[0]), this.IRIHIHRICICCRIICHOHROCCHCOIIHH()))
               .method5(new TextHudComponent("$", this.HCOICHOHHHHOHHICHHCOIOROOHCOHI()))
               .method5(
                  new TextHudComponent()
                     .method2(() -> this.OHCROHOHCIHICOCIHROIRCOIIICRCI ? "345,678,123" : String.format("%,.0f", SkyblockGemstoneProfit.this.field14))
                     .method7(this.HCOICHOHHHHOHHICHHCOIOROOHCOHI())
               )
         );
         mixincore5iterator1.method5(
            new HudComponentGroup()
               .method5(new TextHudComponent(() -> SkyblockGemstoneProfit.this.method2("time", new Object[0]), this.IRIHIHRICICCRIICHOHROCCHCOIIHH()))
               .method5(
                  new TextHudComponent()
                     .method2(
                        () -> com.moonsworth.lunar.client.util.text.TimeFormatting.Type.EASY_DYNAMIC_1
                           .format(
                              this.OHCROHOHCIHICOCIHROIRCOIIICRCI
                                 ? 1820000L
                                 : Ref.method3().bridge$getSystemTime() - SkyblockGemstoneProfit.this.field15
                           )
                     )
                     .method7(this.HCOICHOHHHHOHHICHHCOIOROOHCOHI())
               )
         );
      }
   }
}
