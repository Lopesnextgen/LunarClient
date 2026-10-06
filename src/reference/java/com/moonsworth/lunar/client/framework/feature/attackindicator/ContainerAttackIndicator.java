package com.moonsworth.lunar.client.framework.feature.attackindicator;

import com.moonsworth.lunar.bridge.Bridge3Extension3;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemBlockBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class ContainerAttackIndicator extends AbstractItemAttackIndicator {
   ContainerAttackIndicator() {
   }

   @Override
   protected boolean method3(@NotNull ItemStackBridge bridgeextension_41) {
      return bridgeextension_41.bridge$getContainerItems() != null
         || bridgeextension_41.bridge$getItem() instanceof ItemBlockBridge bridge6extension82 && bridge6extension82.bridge$getBlockFromItem().orElse(null) instanceof Bridge3Extension3
         || bridgeextension_41.bridge$getItem().bridge$isBundle();
   }

   @Override
   public int method5() {
      return 0;
   }

   @Override
   public float method2(@NotNull Bridge5Extension_5 bridge5extension_51) {
      List list2 = this.method6().bridge$getContainerItems();
      if (list2 == null) {
         return 0.0F;
      }

      boolean flag3 = this.method6().bridge$getItem().bridge$isBundle();
      float value4 = flag3 ? 64.0F : 27.0F;
      float value5 = 0.0F;

      for (int index6 = 0; index6 < list2.size(); index6++) {
         ItemStackBridge bridgeextension_47 = (ItemStackBridge)list2.get(index6);
         if (bridgeextension_47 != null && !bridgeextension_47.bridge$isEmpty()) {
            int number8 = bridgeextension_47.bridge$getMaxStackSize();
            if (number8 <= 0) {
               number8 = 64;
            }

            value5 += flag3 ? bridgeextension_47.bridge$getStackSize() * (64.0F / number8) : (float)bridgeextension_47.bridge$getStackSize() / number8;
         }
      }

      return Math.min(1.0F, value5 / value4);
   }

   @Override
   public boolean method3(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return this.method6() != null;
   }

   @Nullable
   @Override
   public ItemStackBridge method6(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return this.method6();
   }
}
