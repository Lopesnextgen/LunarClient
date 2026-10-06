package com.moonsworth.lunar.client.mod.skyblock.highlightfilledbzorders;

import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.event.screen.EventScreenOpen;
import com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramItem;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import java.util.HashSet;

public class SkyblockHighlightFilledBzOrders extends AbstractFeature {
   private final HighlightTypeListener field8 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final ScreenTitleListener field9 = (ScreenTitleListener)this.method63(ScreenTitleListener.class);
   private final ColorOption field10 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("highlightColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method31();
   private final HashSet<SlotBridge> field11 = new HashSet<>();

   public SkyblockHighlightFilledBzOrders(Skyblock skyblock1) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.INVENTORY));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventRenderHologramItem.class, this::method2);
      this.handle(EventSlotUpdate.class, this::method1);
      this.handle(EventScreenOpen.class, this::method3);
   }

   private void method1(EventSlotUpdate highlightimpl1) {
      SkyblockMenuType highlighttype2 = this.field8.method7();
      if (highlighttype2 == SkyblockMenuType.BAZAAR_ORDERS) {
         int index3 = highlightimpl1.getSlot();
         if (highlightimpl1.getSlot() >= 0 && highlightimpl1.getSlot() <= 53) {
            GuiContainerBridge bridge5extension_34 = this.field9.method6();
            if (bridge5extension_34 != null) {
               SlotBridge bridge3_185 = (SlotBridge)bridge5extension_34.bridge$inventorySlots().get(index3);
               ItemStackBridge bridgeextension_46 = highlightimpl1.method3();

               for (String text8 : SkyblockItemUtil.method15(bridgeextension_46)) {
                  if (text8.endsWith(" 100%!")) {
                     this.field11.add(bridge3_185);
                     return;
                  }
               }
            }
         }
      }
   }

   private void method2(EventRenderHologramItem data51) {
      if (this.field11.contains(data51.method3())) {
         data51.method1(this.field10.method14(0.0F));
      }
   }

   private void method3(EventScreenOpen highlightimpl91) {
      this.field11.clear();
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> arg1x.method9(new ClientOption[]{this.field10}));
   }

   public String getId() {
      return "SKYBLOCK_HIGHLIGHT_FILLED_BZ_ORDERS";
   }
}
