package com.moonsworth.lunar.client.mod.skyblock.priceinlore;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.ClickableText;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2.Data3;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.text.TimeFormatting;
import java.text.NumberFormat;
import java.time.Instant;
import java.util.List;
import java.util.Locale;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockPriceInLore extends AbstractFeature {
   private final GuiRewindhandlersHandler2 field8 = (GuiRewindhandlersHandler2)this.method63(GuiRewindhandlersHandler2.class);
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockShowAuctionPrices").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockShowBazaarPrices").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ModifierKeybindOption field11 = (ModifierKeybindOption)OptionFactory.method18("skyblockShowMorePriceInfo")
      .method5(KeyCode.KEY_LMENU)
      .method11()
      .method31();
   private final ModifierKeybindOption field12 = (ModifierKeybindOption)OptionFactory.method18("skyblockShowStackPrice")
      .method5(KeyCode.KEY_LSHIFT)
      .method11()
      .method31();
   private final ToggleOption field13 = (ToggleOption)OptionFactory.method7("skyblockExtraInfoAlways").method31();
   private final ColorOption field14 = (ColorOption)OptionFactory.method8("skyblockPriceSubjectColor")
      .method7(NamedTextColor.GOLD)
      .method31();
   private final ColorOption field15 = (ColorOption)OptionFactory.method8("skyblockPriceValueColor")
      .method7(NamedTextColor.YELLOW)
      .method31();

   public SkyblockPriceInLore(Skyblock skyblock1) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.ITEMS));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre.class, this::method2);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.method9(
            new ClientOption[]{this.field9, this.field10, this.field12, this.field11, this.field13, this.field14, this.field15}
         )
      );
   }

   private void method2(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre data21) {
      ItemStackBridge bridgeextension_42 = (ItemStackBridge)data21.method1().orElse(null);
      if (bridgeextension_42 != null) {
         String text3 = SkyblockItemUtil.method3(bridgeextension_42);
         int number4 = bridgeextension_42.bridge$getStackSize();
         if (!this.method3(data21, text3, number4)) {
            this.method4(data21, text3, number4);
         }
      }
   }

   private boolean method3(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre data21, String text2, int number3) {
      if (!(Boolean)this.field9.get()) {
         return false;
      }

      Data3 data34 = this.field8.method4(text2);
      if (data34 == null) {
         return false;
      }

      NumberFormat numberformat5 = NumberFormat.getNumberInstance(Locale.ROOT);
      numberformat5.setMaximumFractionDigits(0);
      List list6 = data21.method3();
      boolean flag7 = this.field12.isKeyDown();
      String text8 = flag7 ? "Current Lowest Bin (x" + number3 + ")" : "Current Lowest Bin";
      String text9 = flag7 ? numberformat5.format(data34.method1() * number3) : numberformat5.format(data34.method1());
      TextColor textcolor10 = TextColor.color(this.field14.method14(0.0F));
      TextColor textcolor11 = TextColor.color(this.field15.method14(0.0F));
      list6.add((ClickableText)Bridge.method8().method89(TextComponentFactory.builder().method2(text8).method4(text9).method5(textcolor10).method7(textcolor11).build()));
      text8 = flag7 ? "1d Average Bin (x" + number3 + ")" : "1d Average Bin";
      text9 = flag7 ? numberformat5.format(data34.method2() * number3) : numberformat5.format(data34.method2());
      list6.add((ClickableText)Bridge.method8().method89(TextComponentFactory.builder().method2(text8).method4(text9).method5(textcolor10).method7(textcolor11).build()));
      if (this.method13()) {
         String text12 = data34.method3();
         long number13 = Instant.parse(text12).toEpochMilli();
         String text15 = TimeFormatting.method3(number13);
         list6.add((ClickableText)Bridge.method8().method89(TextComponentFactory.builder().method2("Last Seen").method4(text15).method5(textcolor10).method7(textcolor11).build()));
      }

      data21.method2(list6);
      return true;
   }

   private boolean method4(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre data21, String text2, int number3) {
      if (!(Boolean)this.field10.get()) {
         return false;
      }

      com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2.Data2 data24 = this.field8.method5(text2);
      if (data24 == null) {
         return false;
      }

      NumberFormat numberformat5 = NumberFormat.getNumberInstance(Locale.ROOT);
      numberformat5.setMaximumFractionDigits(0);
      List list6 = data21.method3();
      boolean flag7 = this.field12.isKeyDown();
      String text8 = flag7 ? "Bazaar Insta-Sell (x" + number3 + ")" : "Bazaar Insta-Sell";
      String text9 = flag7 ? numberformat5.format(data24.method4().method2() * number3) : numberformat5.format(data24.method4().method2());
      TextColor textcolor10 = TextColor.color(this.field14.method14(0.0F));
      TextColor textcolor11 = TextColor.color(this.field15.method14(0.0F));
      list6.add((ClickableText)Bridge.method8().method89(TextComponentFactory.builder().method2(text8).method4(text9).method5(textcolor10).method7(textcolor11).build()));
      text8 = flag7 ? "Bazaar Insta-Buy (x" + number3 + ")" : "Bazaar Insta-Buy";
      text9 = flag7 ? numberformat5.format(data24.method4().method6() * number3) : numberformat5.format(data24.method4().method6());
      list6.add((ClickableText)Bridge.method8().method89(TextComponentFactory.builder().method2(text8).method4(text9).method5(textcolor10).method7(textcolor11).build()));
      if (this.method13()) {
         list6.add(
            (ClickableText)Bridge.method8()
               .method89(
                  TextComponentFactory.builder().method2("Insta-Sell Volume (last 7d)").method4(numberformat5.format(data24.method4().method4())).method5(textcolor10).method7(textcolor11).build()
               )
         );
         list6.add(
            (ClickableText)Bridge.method8()
               .method89(
                  TextComponentFactory.builder().method2("Insta-Buy Volume (last 7d)").method4(numberformat5.format(data24.method4().method8())).method5(textcolor10).method7(textcolor11).build()
               )
         );
         list6.add(
            (ClickableText)Bridge.method8()
               .method89(
                  TextComponentFactory.builder().method2("Current Buy Order Volume").method4(numberformat5.format(data24.method4().method7())).method5(textcolor10).method7(textcolor11).build()
               )
         );
         list6.add(
            (ClickableText)Bridge.method8()
               .method89(
                  TextComponentFactory.builder().method2("Current Sell Order Volume").method4(numberformat5.format(data24.method4().method3())).method5(textcolor10).method7(textcolor11).build()
               )
         );
      }

      data21.method2(list6);
      return true;
   }

   private boolean method13() {
      return (Boolean)this.field13.get() || this.field11.isKeyDown();
   }

   public String getId() {
      return "SKYBLOCK_PRICE_IN_LORE";
   }
}
