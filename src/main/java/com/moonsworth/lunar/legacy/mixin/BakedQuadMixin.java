package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.BakedQuadBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.block.model.BakedQuad;
import org.spongepowered.asm.mixin.Mixin;

@VersionGate(min = 1)
@Mixin(BakedQuad.class)
public class BakedQuadMixin implements BakedQuadBridge {
   public BakedQuadMixin() {
   }
}
