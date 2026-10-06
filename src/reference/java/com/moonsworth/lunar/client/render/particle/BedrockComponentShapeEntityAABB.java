package com.moonsworth.lunar.client.render.particle;

import com.moonsworth.lunar.client.render.particle.BedrockParticle;
import com.moonsworth.lunar.client.render.particle.BedrockEmitter;
import java.util.concurrent.ThreadLocalRandom;

public class BedrockComponentShapeEntityAABB extends BedrockComponentShapeBase {
   public BedrockComponentShapeEntityAABB() {
   }

   @Override
   public void method2(BedrockEmitter glintcolorizer5_21, BedrockParticle glintcolorizer4_22) {
      float value3 = (float)this.CHHRCIHIRROOIIRIHOHHICRHOROICC[0].get();
      float value4 = (float)this.CHHRCIHIRROOIIRIHOHHICRHOROICC[1].get();
      float value5 = (float)this.CHHRCIHIRROOIIRIHOHHICRHOROICC[2].get();
      float value6 = 0.0F;
      float value7 = 0.0F;
      if (glintcolorizer5_21.field1 != null) {
         value6 = glintcolorizer5_21.field1.bridge$getWidth();
         value7 = glintcolorizer5_21.field1.bridge$getEyeHeight();
      }

      ThreadLocalRandom threadlocalrandom8 = ThreadLocalRandom.current();
      glintcolorizer4_22.field25.x = value3 + (threadlocalrandom8.nextFloat() - 0.5F) * value6;
      glintcolorizer4_22.field25.y = value4 + (threadlocalrandom8.nextFloat() - 0.5F) * value7;
      glintcolorizer4_22.field25.z = value5 + (threadlocalrandom8.nextFloat() - 0.5F) * value6;
      if (this.HCOHIIIIROOCHIHOHRHHHIORHHHOHI) {
         int number9 = (int)(threadlocalrandom8.nextDouble() * 6.0 * 100.0) % 6;
         if (number9 == 0) {
            glintcolorizer4_22.field25.x = value3 + value6 / 2.0F;
         } else if (number9 == 1) {
            glintcolorizer4_22.field25.x = value3 - value6 / 2.0F;
         } else if (number9 == 2) {
            glintcolorizer4_22.field25.y = value4 + value7 / 2.0F;
         } else if (number9 == 3) {
            glintcolorizer4_22.field25.y = value4 - value7 / 2.0F;
         } else if (number9 == 4) {
            glintcolorizer4_22.field25.z = value5 + value6 / 2.0F;
         } else if (number9 == 5) {
            glintcolorizer4_22.field25.z = value5 - value6 / 2.0F;
         }
      }

      this.HCHIRIROHRRROIHCOOCICHCIHOHHIH.method1(glintcolorizer4_22, value3, value4, value5);
   }
}
