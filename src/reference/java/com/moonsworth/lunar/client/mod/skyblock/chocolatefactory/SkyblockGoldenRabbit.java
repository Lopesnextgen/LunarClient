package com.moonsworth.lunar.client.mod.skyblock.chocolatefactory;

import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.RabbitCollectionStore;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramItem;
import com.moonsworth.lunar.client.event.render.EventRenderScreenOverlay.EventScreenInitPre;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.config.option.ToggleOption;

public class SkyblockGoldenRabbit extends AbstractFeature {
   private final HighlightTypeListener field8 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final ScreenTitleListener field9 = (ScreenTitleListener)this.method63(ScreenTitleListener.class);
   private SlotBridge field10;

   public SkyblockGoldenRabbit(SkyblockChocolateFactory skyblockchocolatefactory1, ToggleOption lightingextension4432) {
      super(true);
      this.method4(ModTraits.field16, ChildModBinding.method4(false, skyblockchocolatefactory1));
      this.method4(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.handle(EventRenderHologramItem.class, this::method2);
      this.handle(EventSlotUpdate.class, this::method3);
      this.handle(EventScreenInitPre.class, arg1x -> this.field10 = null);
   }

   public String getId() {
      return "SKYBLOCK_GOLDEN_RABBIT";
   }

   protected void method1(boolean flag1) {
   }

   private void method2(EventRenderHologramItem data51) {
      if (this.field8.method7() == SkyblockMenuType.CHOCOLATE_FACTORY) {
         if (this.field10 != null) {
            SkyblockChocolateFactory skyblockchocolatefactory2 = (SkyblockChocolateFactory)((ChildModBinding)this.method7(ModTraits.field16)).method1();
            if (data51.method3() == this.field10) {
               data51.method1(skyblockchocolatefactory2.method50().method14(0.0F));
               skyblockchocolatefactory2.method21().method5(RabbitCollectionStore.field2, 1000);
            }
         }
      }
   }

   private void method3(EventSlotUpdate highlightimpl1) {
      if (this.field8.method7() == SkyblockMenuType.CHOCOLATE_FACTORY) {
         int index2 = highlightimpl1.getSlot();
         if (index2 >= 0 && index2 < 54) {
            SlotBridge bridge3_183 = (SlotBridge)this.field9.method6().bridge$inventorySlots().get(index2);
            ItemStackBridge bridgeextension_44 = highlightimpl1.method3();
            if (bridgeextension_44 != null && !bridgeextension_44.bridge$isEmpty()) {
               String text5 = bridgeextension_44.bridge$getDisplayName();
               if (text5.contains("Golden Rabbit") && !text5.endsWith("CAUGHT!")) {
                  this.field10 = bridge3_183;
               } else if (this.field10 != null && this.field10 == bridge3_183) {
                  this.field10 = null;
               }
            }
         }
      }
   }
}
