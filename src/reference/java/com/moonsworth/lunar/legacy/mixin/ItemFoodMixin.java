package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ItemFoodBridge;
import com.moonsworth.lunar.bridge.potion.PotionBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemFood.class)
public abstract class ItemFoodMixin implements ItemFoodBridge {
   @Shadow
   public boolean alwaysEdible;
   @Shadow
   public int potionId;
   @Shadow
   public PotionEffect potionId$v1_12;

   public ItemFoodMixin() {
   }

   @Shadow
   public abstract int getHealAmount(ItemStack stack1);

   @Shadow
   public abstract float getSaturationModifier(ItemStack stack1);

   public int bridge$getHealing(ItemStackBridge bridgeextension_41) {
      return this.getHealAmount((ItemStack)bridgeextension_41);
   }

   public float bridge$getSaturation(ItemStackBridge bridgeextension_41) {
      return this.getHealAmount((ItemStack)bridgeextension_41) * this.getSaturationModifier((ItemStack)bridgeextension_41) * 2.0F;
   }

   public boolean bridge$canEatWhenFull() {
      return this.alwaysEdible;
   }

   public boolean bridge$givesBadEffect() {
      if (Ref.MC_VERSION >= 5) {
         return this.potionId$v1_12 != null && this.potionId$v1_12.potion$v1_12 != null && this.potionId$v1_12.potion$v1_12.isBadEffect;
      }

      PotionBridge fog21 = Bridge.method36().method8(this.potionId);
      return fog21 != null && fog21.bridge$isBadEffect();
   }
}
