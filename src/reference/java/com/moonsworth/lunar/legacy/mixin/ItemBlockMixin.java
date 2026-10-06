package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ItemBlockBridge;
import net.minecraft.item.ItemBlock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemBlock.class)
public abstract class ItemBlockMixin implements ItemBlockBridge {
   public ItemBlockMixin() {
   }
}
