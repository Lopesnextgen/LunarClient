package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.Bridge_57;
import com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Item.class)
public class ItemMixin2 {
   @ModifyReturnValue(method = "hasEffect", at = @At("RETURN"))
   private boolean apollo$hasGlintEffect(boolean var1, ItemStack var2) {
      Bridge_57 var3 = Rewindhandlers.method1((ItemStackBridge)var2);
      if (var3 == null) {
         return var1;
      } else {
         return var3.bridge$getInteger("glint") == 0 && var3.bridge$getString("glint").isEmpty() ? var1 : true;
      }
   }
}
