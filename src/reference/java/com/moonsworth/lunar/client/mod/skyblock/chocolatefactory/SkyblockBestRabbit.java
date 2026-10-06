package com.moonsworth.lunar.client.mod.skyblock.chocolatefactory;

import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.ChocolateRabbit;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotAfterItems;
import com.moonsworth.lunar.client.config.option.ToggleOption;

public class SkyblockBestRabbit extends AbstractFeature {
   private final HighlightTypeListener field8 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final ScreenTitleListener field9 = (ScreenTitleListener)this.method63(ScreenTitleListener.class);
   private static final ResourceLocationBridge field10 = ResourceLocationBridge.create("lunar", "icons/assets/crown-16x16.png");

   public SkyblockBestRabbit(SkyblockChocolateFactory skyblockchocolatefactory1, ToggleOption lightingextension4432) {
      super(true);
      this.method45(ModTraits.field16, ChildModBinding.method4(false, skyblockchocolatefactory1));
      this.method45(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.handle(EventRenderContainerSlotAfterItems.class, this::method2);
   }

   public String getId() {
      return "SKYBLOCK_BEST_RABBIT";
   }

   protected void method1(boolean flag1) {
   }

   private void method2(EventRenderContainerSlotAfterItems data21) {
      SkyblockChocolateFactory skyblockchocolatefactory2 = (SkyblockChocolateFactory)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      if (this.field8.method7() == SkyblockMenuType.CHOCOLATE_FACTORY && !skyblockchocolatefactory2.method56().isEmpty()) {
         GuiContainerBridge bridge5extension_33 = this.field9.method6();
         ChocolateRabbit fishing74 = skyblockchocolatefactory2.method56().get(0);
         if (fishing74.method5() > 0.0 && fishing74.method1() > 0.0) {
            SlotBridge bridge3_185 = fishing74.method2();
            int number6 = bridge5extension_33.bridge$getGuiLeft() + bridge3_185.bridge$getXDisplayPosition() + 8;
            int number7 = bridge5extension_33.bridge$getGuiTop() + bridge3_185.bridge$getYDisplayPosition();
            data21.method5().method24(field10, number6, number7, 8, 8, -1);
         }
      }
   }
}
