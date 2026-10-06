package com.moonsworth.lunar.client.mod.skyblock.chocolatefactory;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.ChocolateRabbit;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.ItemRender;
import com.moonsworth.lunar.client.config.option.ToggleOption;

public class SkyblockAffordableRabbit extends AbstractFeature {
   private final HighlightTypeListener field8 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final ScreenTitleListener field9 = (ScreenTitleListener)this.method63(ScreenTitleListener.class);

   public SkyblockAffordableRabbit(SkyblockChocolateFactory skyblockchocolatefactory1, ToggleOption lightingextension4432) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method4(false, skyblockchocolatefactory1));
      this.method2(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.handle(ItemRender.class, this::method2);
   }

   public String getId() {
      return "SKYBLOCK_AFFORDABLE_RABBIT";
   }

   protected void method1(boolean flag1) {
   }

   private void method2(ItemRender data1) {
      if (this.field8.method7() == SkyblockMenuType.CHOCOLATE_FACTORY) {
         SkyblockChocolateFactory skyblockchocolatefactory2 = (SkyblockChocolateFactory)((ChildModBinding)this.method7(ModTraits.field16)).method1();
         long number3 = skyblockchocolatefactory2.method57() + skyblockchocolatefactory2.method74();

         for (ChocolateRabbit fishing76 : skyblockchocolatefactory2.method56()) {
            if (number3 >= fishing76.method4()) {
               LcuiScreen.method127(data1.method5(), this.field9.method6(), fishing76.method2(), skyblockchocolatefactory2.method48().method14(0.0F));
            }
         }
      }
   }
}
