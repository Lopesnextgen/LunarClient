package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ItemHoeBridge;
import net.minecraft.item.ItemHoe;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemHoe.class)
public abstract class ItemHoeMixin implements ItemHoeBridge {
   public ItemHoeMixin() {
   }
}
