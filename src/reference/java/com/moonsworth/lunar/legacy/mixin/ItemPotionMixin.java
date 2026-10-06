package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge6Extension2;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.fog.Fog;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Collection;
import java.util.List;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemPotion.class)
public abstract class ItemPotionMixin implements Bridge6Extension2 {
   @Shadow
   public abstract List<PotionEffect> getEffects(ItemStack var1);

   @Shadow
   public abstract List<PotionEffect> getEffects(ItemStack var1);

   @Override
   public Collection<Fog> bridge$getEffects(ItemStackBridge var1) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         return PotionUtils.getEffectsFromStack((ItemStack)var1);
      } else {
         return ThreadModuleDump63.MC_VERSION >= 1 ? this.getEffects((ItemStack)var1) : this.getEffects((ItemStack)var1);
      }
   }
}
