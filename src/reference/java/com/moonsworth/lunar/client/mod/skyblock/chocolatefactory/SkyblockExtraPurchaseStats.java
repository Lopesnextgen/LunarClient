package com.moonsworth.lunar.client.mod.skyblock.chocolatefactory;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ClickableText;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.ChocolateRabbit;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;

public class SkyblockExtraPurchaseStats extends AbstractFeature {
   private final HighlightTypeListener field8 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);

   public SkyblockExtraPurchaseStats(SkyblockChocolateFactory skyblockchocolatefactory1, ToggleOption lightingextension4432) {
      super(true);
      this.method45(ModTraits.field16, ChildModBinding.method4(false, skyblockchocolatefactory1));
      this.method45(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.handle(EventRenderTooltipPre.class, this::method2);
   }

   public String getId() {
      return "SKYBLOCK_EXTRA_PURCHASE_STATS";
   }

   protected void method1(boolean flag1) {
   }

   private void method2(EventRenderTooltipPre data21) {
      if (this.field8.method7() == SkyblockMenuType.CHOCOLATE_FACTORY) {
         ItemStackBridge bridgeextension_42 = (ItemStackBridge)data21.HHCOCHOIIIOOROCORRRRORORIOOHRC().orElse(null);
         if (bridgeextension_42 != null) {
            SkyblockChocolateFactory skyblockchocolatefactory3 = (SkyblockChocolateFactory)((ChildModBinding)this.method7(ModTraits.field16)).method1();

            for (ChocolateRabbit fishing75 : skyblockchocolatefactory3.method56()) {
               if (fishing75.method3().bridge$getDisplayName().equals(bridgeextension_42.bridge$getDisplayName())) {
                  Style style6 = TextComponentFactory.styleOf(skyblockchocolatefactory3.method52());
                  Style style7 = TextComponentFactory.styleOf(skyblockchocolatefactory3.method53());
                  NumberFormat numberformat8 = NumberFormat.getNumberInstance(Locale.ROOT);
                  numberformat8.setMaximumFractionDigits(2);
                  String text9 = numberformat8.format(fishing75.method5()) + " choc/sec";
                  String text10 = numberformat8.format(fishing75.method1());
                  List list11 = data21.method3();
                  list11.add((ClickableText)Bridge.method8().method89(Component.empty()));
                  if (fishing75.method4() > 0L && fishing75.method5() > 0.0) {
                     list11.add(
                        (ClickableText)Bridge.method8()
                           .method89(((TextComponent)Component.text("Increase: ").style(style6)).append(Component.text(text9).style(style7)))
                     );
                     list11.add(
                        (ClickableText)Bridge.method8()
                           .method89(((TextComponent)Component.text("Effective Cost: ").style(style6)).append(Component.text(text10).style(style7)))
                     );
                  }

                  list11.add(
                     (ClickableText)Bridge.method8()
                        .method89(((TextComponent)Component.text("Time Until Upgrade: ").style(style6)).append(skyblockchocolatefactory3.method21().method4(fishing75.method4())))
                  );
                  data21.method2(list11);
                  return;
               }
            }
         }
      }
   }
}
