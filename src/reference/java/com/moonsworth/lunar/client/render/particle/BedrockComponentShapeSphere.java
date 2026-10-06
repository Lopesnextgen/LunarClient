package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.render.particle.BedrockComponentBase;
import com.moonsworth.lunar.client.render.particle.BedrockParticle;
import com.moonsworth.lunar.client.render.particle.BedrockEmitter;
import com.moonsworth.lunar.client.render.particle.MolangExpression;
import com.moonsworth.lunar.client.render.particle.MolangParser;
import java.util.concurrent.ThreadLocalRandom;
import javax.vecmath.Vector3f;

public class BedrockComponentShapeSphere extends BedrockComponentShapeBase {
   public MolangExpression field4 = MolangParser.field3;

   public BedrockComponentShapeSphere() {
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser glintcolorizer3iterator2) {
      if (!element1.isJsonObject()) {
         return super.method1(element1, glintcolorizer3iterator2);
      }

      JsonObject json3 = element1.getAsJsonObject();
      if (json3.has("radius")) {
         this.field4 = glintcolorizer3iterator2.method5(json3.get("radius"));
      }

      return super.method1(json3, glintcolorizer3iterator2);
   }

   @Override
   public JsonElement method2() {
      JsonObject json1 = (JsonObject)super.method2();
      if (!MolangExpression.method1(this.field4)) {
         json1.add("radius", this.field4.method5());
      }

      return json1;
   }

   @Override
   public void method2(BedrockEmitter glintcolorizer5_21, BedrockParticle glintcolorizer4_22) {
      float value3 = (float)this.CHHRCIHIRROOIIRIHOHHICRHOROICC[0].get();
      float value4 = (float)this.CHHRCIHIRROOIIRIHOHHICRHOROICC[1].get();
      float value5 = (float)this.CHHRCIHIRROOIIRIHOHHICRHOROICC[2].get();
      float value6 = (float)this.field4.get();
      ThreadLocalRandom threadlocalrandom7 = ThreadLocalRandom.current();
      Vector3f vector3f8 = new Vector3f((float)threadlocalrandom7.nextDouble() * 2.0F - 1.0F, (float)threadlocalrandom7.nextDouble() * 2.0F - 1.0F, (float)threadlocalrandom7.nextDouble() * 2.0F - 1.0F);
      vector3f8.normalize();
      if (!this.HCOHIIIIROOCHIHOHRHHHIORHHHOHI) {
         value6 = (float)(value6 * threadlocalrandom7.nextDouble());
      }

      vector3f8.scale(value6);
      glintcolorizer4_22.field25.x = value3 + vector3f8.x;
      glintcolorizer4_22.field25.y = value4 + vector3f8.y;
      glintcolorizer4_22.field25.z = value5 + vector3f8.z;
      this.HCHIRIROHRRROIHCOOCICHCIHOHHIH.method1(glintcolorizer4_22, value3, value4, value5);
   }
}
