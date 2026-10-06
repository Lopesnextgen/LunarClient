package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.player.EventInventoryUpdate;
import net.minecraft.client.gui.inventory.CreativeCrafting;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreativeCrafting.class)
public class CreativeCraftingMixin {
   public CreativeCraftingMixin() {
   }

   @Inject(method = "sendSlotContents", at = @At("HEAD"))
   private void lunar$creativeInventoryUpdate(Container container1, int value, ItemStack stack3, CallbackInfo callback4) {
      LunarEventBus.method29().method12(EventInventoryUpdate.class, EventInventoryUpdate::new);
   }
}
