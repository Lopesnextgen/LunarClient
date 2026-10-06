package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import com.moonsworth.lunar.client.util.game.ItemTagUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Item.class)
public class ItemGlintMixin {
   public ItemGlintMixin() {
   }

   @ModifyReturnValue(method = "hasEffect", at = @At("RETURN"))
   private boolean apollo$hasGlintEffect(boolean flag1, ItemStack stack2) {
      CompoundTagBridge bridge_573 = ItemTagUtils.method1((ItemStackBridge)stack2);
      if (bridge_573 == null) {
         return flag1;
      } else {
         return bridge_573.bridge$getInteger("glint") == 0 && bridge_573.bridge$getString("glint").isEmpty() ? flag1 : true;
      }
   }
}
