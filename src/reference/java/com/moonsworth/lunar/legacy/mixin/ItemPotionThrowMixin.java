package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.combat.EventPotionThrow;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@VersionGate(max = 1)
@Mixin(ItemPotion.class)
public class ItemPotionThrowMixin {
   public ItemPotionThrowMixin() {
   }

   @Inject(
      method = "onItemRightClick$v1_7(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/item/ItemStack;",
      at = @At("HEAD")
   )
   private void lunar$throwPotionEvent(ItemStack stack1, World world2, EntityPlayer player3, CallbackInfoReturnable<ItemStack> callbackinforeturnable4) {
      if (world2.isRemote) {
         LunarEventBus.method29().method12(EventPotionThrow.class, () -> new EventPotionThrow((EntityLivingBridge)player3, (ItemStackBridge)stack1));
      }
   }
}
