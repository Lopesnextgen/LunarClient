package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ItemSwordBridge;
import net.minecraft.item.ItemSword;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemSword.class)
public abstract class ItemSwordMixin implements ItemSwordBridge {
   public ItemSwordMixin() {
   }
}
