package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ItemPickaxeBridge;
import net.minecraft.item.ItemPickaxe;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemPickaxe.class)
public abstract class ItemPickaxeMixin implements ItemPickaxeBridge {
   public ItemPickaxeMixin() {
   }
}
