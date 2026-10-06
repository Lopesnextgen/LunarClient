package com.moonsworth.lunar.client.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.optifine.OptifineBridge;
import com.moonsworth.lunar.client.framework.feature.mobsize.mixin.TessellatorRenderer;
import java.util.Optional;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.Tessellator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EffectRenderer.class)
public class EffectRendererMixin {
   public EffectRendererMixin() {
   }

   @ModifyVariable(method = "renderParticles", at = @At("STORE"))
   private Tessellator lunar$renderParticles(Tessellator tessellator1) {
      Optional optional2 = Bridge.method5();
      return (Tessellator)(optional2.isPresent() && ((OptifineBridge)optional2.get()).getConfig().hasShaders() ? tessellator1 : new TessellatorRenderer());
   }
}
