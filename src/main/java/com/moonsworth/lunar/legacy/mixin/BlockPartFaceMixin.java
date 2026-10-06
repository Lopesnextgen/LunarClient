package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.BlockPartFaceBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import org.spongepowered.asm.mixin.Mixin;

@VersionGate(min = 1)
@Mixin(BlockPartFace.class)
public class BlockPartFaceMixin implements BlockPartFaceBridge {
   public BlockPartFaceMixin() {
   }
}
