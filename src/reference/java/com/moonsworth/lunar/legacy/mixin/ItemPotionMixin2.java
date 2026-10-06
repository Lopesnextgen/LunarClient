package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.combat.PotionThrowEvent;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Annotation2(max = 1)
@Mixin(ItemPotion.class)
public class ItemPotionMixin2 {
   @Inject(
      method = "onItemRightClick$v1_7(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/item/ItemStack;",
      at = @At("HEAD")
   )
   private void lunar$throwPotionEvent(ItemStack var1, World var2, EntityPlayer var3, CallbackInfoReturnable<ItemStack> var4) {
      if (var2.isRemote) {
         ClientEventBus.method29().method12(PotionThrowEvent.class, () -> new PotionThrowEvent((BridgeExtension2_5)var3, (ItemStackBridge)var1));
      }
   }
}
