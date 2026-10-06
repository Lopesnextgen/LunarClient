package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.player.EventItemRightClick;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackUseItemMixin {
   @Annotation2(max = 1)
   @Inject(
      method = "useItemRightClick$v1_7(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/item/ItemStack;",
      at = @At("HEAD")
   )
   private void lunar$useItemEvent(World var1, EntityPlayer var2, CallbackInfoReturnable<ItemStack> var3) {
      if (var1.isRemote) {
         ClientEventBus.method29().method12(EventItemRightClick.class, () -> new EventItemRightClick((Bridge6_10)var2, (ItemStackBridge)this));
      }
   }

   @Annotation2(min = 5)
   @Inject(
      method = "useItemRightClick$v1_12(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/util/EnumHand;)Lnet/minecraft/util/ActionResult;",
      at = @At("HEAD")
   )
   private void lunar$useItemEvent(World var1, EntityPlayer var2, EnumHand var3, CallbackInfoReturnable<ActionResult<ItemStack>> var4) {
      if (var1.isRemote) {
         ClientEventBus.method29().method12(EventItemRightClick.class, () -> new EventItemRightClick((Bridge6_10)var2, (ItemStackBridge)this));
      }
   }
}
