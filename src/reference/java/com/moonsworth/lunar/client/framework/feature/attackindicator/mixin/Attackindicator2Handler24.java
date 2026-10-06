package com.moonsworth.lunar.client.framework.feature.attackindicator.mixin;

import com.moonsworth.lunar.bridge.Bridge3Extension3;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6Extension8;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class Attackindicator2Handler24 extends Attackindicator2Handler2 {
   @Override
   protected boolean method3(@NotNull ItemStackBridge var1) {
      return var1.bridge$getContainerItems() != null
         || var1.bridge$getItem() instanceof Bridge6Extension8 var2 && var2.bridge$getBlockFromItem().orElse(null) instanceof Bridge3Extension3
         || var1.bridge$getItem().bridge$isBundle();
   }

   @Override
   public int method5() {
      return 0;
   }

   @Override
   public float method2(@NotNull Bridge5Extension_5 var1) {
      List var2 = this.method6().bridge$getContainerItems();
      if (var2 == null) {
         return 0.0F;
      }

      boolean var3 = this.method6().bridge$getItem().bridge$isBundle();
      float var4 = var3 ? 64.0F : 27.0F;
      float var5 = 0.0F;

      for (int var6 = 0; var6 < var2.size(); var6++) {
         ItemStackBridge var7 = (ItemStackBridge)var2.get(var6);
         if (var7 != null && !var7.bridge$isEmpty()) {
            int var8 = var7.bridge$getMaxStackSize();
            if (var8 <= 0) {
               var8 = 64;
            }

            var5 += var3 ? var7.bridge$getStackSize() * (64.0F / var8) : (float)var7.bridge$getStackSize() / var8;
         }
      }

      return Math.min(1.0F, var5 / var4);
   }

   @Override
   public boolean method3(@NotNull Bridge5Extension_5 var1) {
      return this.method6() != null;
   }

   @Nullable
   @Override
   public ItemStackBridge method6(@NotNull Bridge5Extension_5 var1) {
      return this.method6();
   }
}
