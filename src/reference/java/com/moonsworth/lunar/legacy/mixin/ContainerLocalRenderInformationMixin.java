package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge5_5;
import com.moonsworth.lunar.bridge.Bridge9_7;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.ichor.Annotation2;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.RenderGlobal.ContainerLocalRenderInformation;
import net.minecraft.client.renderer.chunk.RenderChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Annotation2(min = 1)
@Mixin(ContainerLocalRenderInformation.class)
public class ContainerLocalRenderInformationMixin implements Bridge9_7 {
   @Shadow
   public RenderChunk renderChunk;

   @Override
   public Horsestats20Extension2 bridge$origin() {
      return ((Bridge9_7)this.renderChunk).bridge$origin();
   }

   @Nullable
   @Override
   public Bridge5_5 bridge$lightOverlayTracker() {
      return ((Bridge9_7)this.renderChunk).bridge$lightOverlayTracker();
   }
}
