package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.render.particle.BedrockComponentBase;
import com.moonsworth.lunar.client.render.particle.BedrockParticle;
import com.moonsworth.lunar.client.render.particle.BedrockEmitter;
import com.moonsworth.lunar.client.render.particle.MolangExpression;
import com.moonsworth.lunar.client.render.particle.MolangParser;
import java.util.concurrent.ThreadLocalRandom;

public class BedrockComponentShapeBox extends BedrockComponentShapeBase {
   public MolangExpression[] field4 = new MolangExpression[]{MolangParser.field3, MolangParser.field3, MolangParser.field3};

   public BedrockComponentShapeBox() {
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser glintcolorizer3iterator2) {
      if (!element1.isJsonObject()) {
         return super.method1(element1, glintcolorizer3iterator2);
      }

      JsonObject json3 = element1.getAsJsonObject();
      if (json3.has("half_dimensions")) {
         JsonArray array4 = json3.getAsJsonArray("half_dimensions");
         if (array4.size() >= 3) {
            this.field4[0] = glintcolorizer3iterator2.method5(array4.get(0));
            this.field4[1] = glintcolorizer3iterator2.method5(array4.get(1));
            this.field4[2] = glintcolorizer3iterator2.method5(array4.get(2));
         }
      }

      return super.method1(json3, glintcolorizer3iterator2);
   }

   @Override
   public JsonElement method2() {
      JsonObject json1 = (JsonObject)super.method2();
      JsonArray array2 = new JsonArray();

      for (MolangExpression glintcolorizer_56 : this.field4) {
         array2.add(glintcolorizer_56.method5());
      }

      json1.add("half_dimensions", array2);
      return json1;
   }

   @Override
   public void method2(BedrockEmitter glintcolorizer5_21, BedrockParticle glintcolorizer4_22) {
      float value3 = (float)this.CHHRCIHIRROOIIRIHOHHICRHOROICC[0].get();
      float value4 = (float)this.CHHRCIHIRROOIIRIHOHHICRHOROICC[1].get();
      float value5 = (float)this.CHHRCIHIRROOIIRIHOHHICRHOROICC[2].get();
      float value6 = (float)this.field4[0].get();
      float value7 = (float)this.field4[1].get();
      float value8 = (float)this.field4[2].get();
      ThreadLocalRandom threadlocalrandom9 = ThreadLocalRandom.current();
      glintcolorizer4_22.field25.x = value3 + (threadlocalrandom9.nextFloat() * 2.0F - 1.0F) * value6;
      glintcolorizer4_22.field25.y = value4 + (threadlocalrandom9.nextFloat() * 2.0F - 1.0F) * value7;
      glintcolorizer4_22.field25.z = value5 + (threadlocalrandom9.nextFloat() * 2.0F - 1.0F) * value8;
      if (this.HCOHIIIIROOCHIHOHRHHHIORHHHOHI) {
         int number10 = (int)(Math.random() * 6.0 * 100.0) % 6;
         if (number10 == 0) {
            glintcolorizer4_22.field25.x = value3 + value6;
         } else if (number10 == 1) {
            glintcolorizer4_22.field25.x = value3 - value6;
         } else if (number10 == 2) {
            glintcolorizer4_22.field25.y = value4 + value7;
         } else if (number10 == 3) {
            glintcolorizer4_22.field25.y = value4 - value7;
         } else if (number10 == 4) {
            glintcolorizer4_22.field25.z = value5 + value8;
         } else if (number10 == 5) {
            glintcolorizer4_22.field25.z = value5 - value8;
         }
      }

      this.HCHIRIROHRRROIHCOOCICHCIHOHHIH.method1(glintcolorizer4_22, value3, value4, value5);
   }
}
