package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.player.EventInventoryUpdate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryPlayer.class)
public class InventoryPlayerEventMixin {
   @Shadow
   public EntityPlayer player;

   public InventoryPlayerEventMixin() {
   }

   @Inject(method = "markDirty", at = @At("TAIL"))
   private void lunar$onInventoryChange(CallbackInfo callback1) {
      if (this.player.world.isRemote) {
         LunarEventBus.method29().method12(EventInventoryUpdate.class, EventInventoryUpdate::new);
      }
   }
}
