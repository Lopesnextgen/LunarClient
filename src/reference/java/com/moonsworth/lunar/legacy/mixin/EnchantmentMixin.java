package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_26;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin implements Bridge2_26 {
   public EnchantmentMixin() {
   }

   @Shadow
   public abstract boolean canApply(ItemStack stack1);

   public boolean bridge$canApply(ItemStackBridge bridgeextension_41) {
      return this.canApply((ItemStack)bridgeextension_41);
   }

   public boolean bridge$isProtection() {
      return this == Bridge.method32().method1();
   }

   public boolean bridge$isEnchantment(Bridge2_26 bridge2_261) {
      return this == bridge2_261;
   }
}
