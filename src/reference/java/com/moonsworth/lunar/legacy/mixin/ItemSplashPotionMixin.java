package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.combat.PotionThrowEvent;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSplashPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Annotation2(min = 5)
@Mixin(ItemSplashPotion.class)
public class ItemSplashPotionMixin {
   @Inject(method = "onItemRightClick", at = @At("HEAD"))
   private void lunar$throwPotionEvent(World var1, EntityPlayer var2, EnumHand var3, CallbackInfoReturnable<ActionResult<ItemStack>> var4) {
      if (var1.isRemote) {
         ClientEventBus.method29().method12(PotionThrowEvent.class, () -> new PotionThrowEvent((BridgeExtension2_5)var2, (ItemStackBridge)var2.getHeldItem(var3)));
      }
   }
}
