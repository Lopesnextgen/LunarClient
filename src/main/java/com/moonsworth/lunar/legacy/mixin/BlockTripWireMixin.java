package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.BlockTripWireBridge;
import net.minecraft.block.BlockTripWire;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockTripWire.class)
public abstract class BlockTripWireMixin implements BlockTripWireBridge {
   public BlockTripWireMixin() {
   }
}
