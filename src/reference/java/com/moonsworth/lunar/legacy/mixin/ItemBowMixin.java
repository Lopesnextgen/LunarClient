package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ItemBowBridge;
import net.minecraft.item.ItemBow;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemBow.class)
public abstract class ItemBowMixin implements ItemBowBridge {
   public ItemBowMixin() {
   }
}
