package com.moonsworth.lunar.client.render.particle;

import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.render.particle.BedrockParticle;
import com.moonsworth.lunar.client.render.particle.HologramParticleEmitter;

public class NameplateTintingComponent extends com.moonsworth.lunar.client.render.particle.BedrockComponentAppearanceTinting implements NameplateGlintRenderer {
   public NameplateTintingComponent() {
   }

   @Override
   public void method1(HologramParticleEmitter glintcolorizer5iterator1, BedrockParticle glintcolorizer4_22, DrawBufferBridge bridge2_323, AbstractRenderContext bridgeextension_94, float value5, ResourceLocationBridge horsestats146) {
      this.method2(glintcolorizer4_22, 0, 0, 0.0F, bridgeextension_94, horsestats146);
   }

   @Override
   public void method2(BedrockParticle glintcolorizer4_21, int number2, int number3, float value4, AbstractRenderContext bridgeextension_95, ResourceLocationBridge horsestats146) {
      if (this.CICORHOCCOOHICHIIROROROIOOCORC != null) {
         this.CICORHOCCOOHICHIIROROROIOOCORC.method3(glintcolorizer4_21);
      } else {
         glintcolorizer4_21.field35 = glintcolorizer4_21.field36 = glintcolorizer4_21.field37 = glintcolorizer4_21.field38 = 1.0F;
      }
   }
}
