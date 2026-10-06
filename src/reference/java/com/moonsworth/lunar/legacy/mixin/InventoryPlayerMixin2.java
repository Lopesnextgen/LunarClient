package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.player.InventoryUpdateEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryPlayer.class)
public class InventoryPlayerMixin2 {
   @Shadow
   public EntityPlayer player;

   @Inject(method = "markDirty", at = @At("TAIL"))
   private void lunar$onInventoryChange(CallbackInfo var1) {
      if (this.player.world.isRemote) {
         ClientEventBus.method29().method12(InventoryUpdateEvent.class, InventoryUpdateEvent::new);
      }
   }
}
