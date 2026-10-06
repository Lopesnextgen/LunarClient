package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ItemAxeBridge;
import net.minecraft.item.ItemAxe;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemAxe.class)
public abstract class ItemAxeMixin implements ItemAxeBridge {
   public ItemAxeMixin() {
   }
}
