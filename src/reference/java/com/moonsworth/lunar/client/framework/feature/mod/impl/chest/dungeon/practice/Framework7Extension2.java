package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.practice;

import com.moonsworth.lunar.bridge.Bridge3_18;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler22;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.HighlightType;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler2_2;

public abstract class Framework7Extension2 extends com.moonsworth.lunar.client.framework.mod.AbstractFeature {
   private final GuiRewindhandlersHandler2_2 field8 = (GuiRewindhandlersHandler2_2)this.method19(GuiRewindhandlersHandler2_2.class);
   private final GuiRewindhandlersHandler22 field9 = (GuiRewindhandlersHandler22)this.method19(GuiRewindhandlersHandler22.class);
   private final HighlightType field10;

   public Framework7Extension2(HighlightType var1) {
      super(true);
      this.field10 = var1;
   }

   protected boolean isInGui() {
      return this.field10 == this.field9.method7();
   }

   protected Bridge5Extension_3 method13() {
      return this.field8.method6();
   }

   protected String getTitle() {
      return this.field8.method5();
   }

   protected boolean method2(Bridge3_18 var1, int value) {
      int var3 = var1.bridge$getIndex();
      if (var3 >= 0 && var3 < value) {
         Bridge5Extension_3 var4 = this.field8.method6();
         if (var4 != null && ((Bridge3_18)var4.bridge$inventorySlots().get(var3)).equals(var1)) {
            ItemStackBridge var5 = var1.bridge$getItemStack();
            return var5 != null
               && var5.bridge$getItem() != null
               && !AdventureChatFormatting.getTextWithoutFormattingCodes(var5.bridge$getDisplayName().trim()).isEmpty();
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   protected boolean method3(ItemStackBridge var1) {
      return var1.bridge$isItemEnchanted() || var1.bridge$hasFoil();
   }
}
