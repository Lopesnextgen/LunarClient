package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ItemSpadeBridge;
import net.minecraft.item.ItemSpade;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemSpade.class)
public abstract class ItemSpadeMixin implements ItemSpadeBridge {
   public ItemSpadeMixin() {
   }
}
