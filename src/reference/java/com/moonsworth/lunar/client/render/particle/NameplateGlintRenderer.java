package com.moonsworth.lunar.client.render.particle;

import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.render.particle.BedrockParticle;
import com.moonsworth.lunar.client.render.particle.HologramParticleEmitter;

public interface NameplateGlintRenderer extends com.moonsworth.lunar.client.render.particle.IComponentParticleRender {
   void method1(HologramParticleEmitter glintcolorizer5iterator1, BedrockParticle glintcolorizer4_22, DrawBufferBridge bridge2_323, AbstractRenderContext bridgeextension_94, float value5, ResourceLocationBridge horsestats146);

   void method2(BedrockParticle glintcolorizer4_21, int number2, int number3, float value4, AbstractRenderContext bridgeextension_95, ResourceLocationBridge horsestats146);
}
