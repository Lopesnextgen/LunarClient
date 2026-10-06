package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.VertexFormatBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.vertex.VertexFormat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(min = 1)
@Mixin(VertexFormat.class)
public abstract class VertexFormatMixin implements VertexFormatBridge {
   @Shadow
   public int vertexSize;

   public VertexFormatMixin() {
   }

   public int bridge$getVertexSize() {
      return this.vertexSize;
   }
}
