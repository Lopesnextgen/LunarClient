package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.practice;

import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;

public abstract class TerminalSolverModule extends com.moonsworth.lunar.client.framework.mod.AbstractFeature {
   private final ScreenTitleListener field8 = (ScreenTitleListener)this.method63(ScreenTitleListener.class);
   private final HighlightTypeListener field9 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final SkyblockMenuType field10;

   public TerminalSolverModule(SkyblockMenuType highlighttype1) {
      super(true);
      this.field10 = highlighttype1;
   }

   protected boolean isInGui() {
      return this.field10 == this.field9.method7();
   }

   protected GuiContainerBridge method13() {
      return this.field8.method6();
   }

   protected String getTitle() {
      return this.field8.method5();
   }

   protected boolean method2(SlotBridge bridge3_181, int number2) {
      int index3 = bridge3_181.bridge$getIndex();
      if (index3 >= 0 && index3 < number2) {
         GuiContainerBridge bridge5extension_34 = this.field8.method6();
         if (bridge5extension_34 != null && ((SlotBridge)bridge5extension_34.bridge$inventorySlots().get(index3)).equals(bridge3_181)) {
            ItemStackBridge bridgeextension_45 = bridge3_181.bridge$getItemStack();
            return bridgeextension_45 != null
               && bridgeextension_45.bridge$getItem() != null
               && !ChatFormatting.getTextWithoutFormattingCodes(bridgeextension_45.bridge$getDisplayName().trim()).isEmpty();
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   protected boolean method3(ItemStackBridge bridgeextension_41) {
      return bridgeextension_41.bridge$isItemEnchanted() || bridgeextension_41.bridge$hasFoil();
   }
}
