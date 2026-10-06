package com.moonsworth.lunar.client.render.particle;

public class BedrockComponentLifetimeOnce extends BedrockComponentLifetime {
   public BedrockComponentLifetimeOnce() {
   }

   @Override
   public void method2(BedrockEmitter glintcolorizer5_21) {
      glintcolorizer5_21.lifetime = (int)(this.CIHHIHOCOHOHIHRIIIRRIOIHRIROHC.get() * 20.0);
      if (glintcolorizer5_21.method1() >= glintcolorizer5_21.lifetime) {
         glintcolorizer5_21.stop();
      }
   }
}
