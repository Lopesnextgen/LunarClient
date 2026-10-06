package com.moonsworth.lunar.client.render.particle;

import com.moonsworth.lunar.client.render.particle.BedrockParticle;
import com.moonsworth.lunar.client.render.particle.BedrockEmitter;

public class BedrockComponentShapePoint extends BedrockComponentShapeBase {
   public BedrockComponentShapePoint() {
   }

   @Override
   public void method2(BedrockEmitter glintcolorizer5_21, BedrockParticle glintcolorizer4_22) {
      glintcolorizer4_22.field25.x = (float)this.CHHRCIHIRROOIIRIHOHHICRHOROICC[0].get();
      glintcolorizer4_22.field25.y = (float)this.CHHRCIHIRROOIIRIHOHHICRHOROICC[1].get();
      glintcolorizer4_22.field25.z = (float)this.CHHRCIHIRROOIIRIHOHHICRHOROICC[2].get();
      if (this.HCHIRIROHRRROIHCOOCICHCIHOHHIH instanceof ParticleDirection.FixedVelocity) {
         this.HCHIRIROHRRROIHCOOCICHCIHOHHIH.method1(glintcolorizer4_22, glintcolorizer4_22.field25.x, glintcolorizer4_22.field25.y, glintcolorizer4_22.field25.z);
      }
   }
}
