package com.moonsworth.lunar.client.mod.skyblock.completedcommissions;

import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
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
import com.moonsworth.lunar.client.event.render.EventRenderSlot;
import com.moonsworth.lunar.client.event.screen.EventScreenOpen;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.ItemRender;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.config.option.ConstantName;
import java.util.HashSet;
import java.util.Set;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockCompletedCommissions extends AbstractFeature {
   private final HighlightTypeListener field8 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final ScreenTitleListener field9 = (ScreenTitleListener)this.method63(ScreenTitleListener.class);
   private final Set<SlotBridge> field10 = new HashSet<>();

   public SkyblockCompletedCommissions(Skyblock skyblock1) {
      super(false);
      this.method5(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method5(ModTraits.field17, ModCategories.method2(SettingsPage.MINING));
      this.method5(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(ItemRender.class, this::method4);
      this.handle(EventSlotUpdate.class, this::method3);
      this.handle(EventScreenOpen.class, this::method2);
      this.handle(EventRenderSlot.class, this::method1);
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_COMPLETED_COMMISSIONS";
   }

   private void method1(EventRenderSlot highlightimpl51) {
      this.field10.removeIf(arg2 -> this.method5(arg2, highlightimpl51.method5()));
   }

   private void method2(EventScreenOpen highlightimpl91) {
      this.field10.clear();
   }

   private void method3(EventSlotUpdate highlightimpl1) {
      if (this.field8.method7() == SkyblockMenuType.COMMISSIONS) {
         ItemStackBridge bridgeextension_42 = highlightimpl1.method3();
         if (bridgeextension_42 != null) {
            boolean flag3 = false;
            if (bridgeextension_42.bridge$getRawDisplayName().startsWith("Commission #")) {
               for (String text6 : SkyblockItemUtil.method15(bridgeextension_42)) {
                  if (text6.equals("COMPLETED")) {
                     flag3 = true;
                     break;
                  }
               }

               if (flag3) {
                  GuiContainerBridge bridge5extension_37 = this.field9.method6();
                  this.field10.add((SlotBridge)bridge5extension_37.bridge$inventorySlots().get(highlightimpl1.getSlot()));
               }
            }
         }
      }
   }

   private void method4(ItemRender data1) {
      for (SlotBridge bridge3_183 : this.field10) {
         LcuiScreen.method127(data1.method5(), this.field9.method6(), bridge3_183, -1442775296);
      }
   }

   private boolean method5(SlotBridge bridge3_181, SlotBridge bridge3_182) {
      return bridge3_181 != null && bridge3_182 != null ? bridge3_181.bridge$getIndex() == bridge3_182.bridge$getIndex() : false;
   }
}
