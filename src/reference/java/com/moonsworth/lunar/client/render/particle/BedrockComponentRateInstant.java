package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.render.particle.Constant;
import com.moonsworth.lunar.client.render.particle.BedrockComponentBase;
import com.moonsworth.lunar.client.render.particle.BedrockEmitter;
import com.moonsworth.lunar.client.render.particle.IComponentEmitterUpdate;
import com.moonsworth.lunar.client.render.particle.MolangValue;
import com.moonsworth.lunar.client.render.particle.Operation;
import com.moonsworth.lunar.client.render.particle.MolangExpression;
import com.moonsworth.lunar.client.render.particle.MolangParser;

public class BedrockComponentRateInstant extends BedrockComponentRate implements IComponentEmitterUpdate {
   public static final MolangExpression field2 = new MolangValue(null, new Constant(10.0));

   public BedrockComponentRateInstant() {
      this.HIOIOCCOORICHRIHCROOIOIORRCIIO = field2;
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser glintcolorizer3iterator2) {
      if (!element1.isJsonObject()) {
         return super.method1(element1, glintcolorizer3iterator2);
      }

      JsonObject json3 = element1.getAsJsonObject();
      if (json3.has("num_particles")) {
         this.HIOIOCCOORICHRIHCROOIOIORRCIIO = glintcolorizer3iterator2.method5(json3.get("num_particles"));
      }

      return super.method1(json3, glintcolorizer3iterator2);
   }

   @Override
   public JsonElement method2() {
      JsonObject json1 = new JsonObject();
      if (!MolangExpression.method3(this.HIOIOCCOORICHRIHCROOIOIORRCIIO, 10.0)) {
         json1.add("num_particles", this.HIOIOCCOORICHRIHCROOIOIORRCIIO.method5());
      }

      return json1;
   }

   @Override
   public void method2(BedrockEmitter glintcolorizer5_21) {
      double value2 = glintcolorizer5_21.method1();
      if (glintcolorizer5_21.playing && Operation.equals(value2, 0.0)) {
         glintcolorizer5_21.method7(0.0F);
         int index4 = 0;

         for (int index5 = (int)this.HIOIOCCOORICHRIHCROOIOIORRCIIO.get(); index4 < index5; index4++) {
            glintcolorizer5_21.method13();
         }
      }
   }
}
