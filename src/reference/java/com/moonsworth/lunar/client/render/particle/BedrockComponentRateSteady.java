package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.render.particle.Constant;
import com.moonsworth.lunar.client.render.particle.BedrockComponentBase;
import com.moonsworth.lunar.client.render.particle.BedrockEmitter;
import com.moonsworth.lunar.client.render.particle.IComponentParticleRender;
import com.moonsworth.lunar.client.render.particle.MolangValue;
import com.moonsworth.lunar.client.render.particle.MolangExpression;
import com.moonsworth.lunar.client.render.particle.MolangParser;

public class BedrockComponentRateSteady extends BedrockComponentRate implements IComponentParticleRender {
   public static final MolangExpression field2 = new MolangValue(null, new Constant(50.0));
   public MolangExpression field3 = MolangParser.field4;

   public BedrockComponentRateSteady() {
      this.HIOIOCCOORICHRIHCROOIOIORRCIIO = field2;
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser glintcolorizer3iterator2) {
      if (!element1.isJsonObject()) {
         return super.method1(element1, glintcolorizer3iterator2);
      }

      JsonObject json3 = element1.getAsJsonObject();
      if (json3.has("spawn_rate")) {
         this.field3 = glintcolorizer3iterator2.method5(json3.get("spawn_rate"));
      }

      if (json3.has("max_particles")) {
         this.HIOIOCCOORICHRIHCROOIOIORRCIIO = glintcolorizer3iterator2.method5(json3.get("max_particles"));
      }

      return super.method1(json3, glintcolorizer3iterator2);
   }

   @Override
   public JsonElement method2() {
      JsonObject json1 = new JsonObject();
      if (!MolangExpression.method2(this.field3)) {
         json1.add("spawn_rate", this.field3.method5());
      }

      if (!MolangExpression.method3(this.HIOIOCCOORICHRIHCROOIOIORRCIIO, 50.0)) {
         json1.add("max_particles", this.HIOIOCCOORICHRIHCROOIOIORRCIIO.method5());
      }

      return json1;
   }

   @Override
   public void method1(BedrockEmitter glintcolorizer5_21, float value2) {
   }

   @Override
   public void method2(BedrockEmitter glintcolorizer5_21, float value2) {
      if (glintcolorizer5_21.playing) {
         double value3 = glintcolorizer5_21.method2(value2) * this.field3.get();
         double value5 = value3 - glintcolorizer5_21.field14;
         double value7 = Math.ceil(value5);
         if (value7 > 0.0) {
            glintcolorizer5_21.method7(value2);

            for (int index9 = 0; index9 < value7; index9++) {
               if (glintcolorizer5_21.field4.size() < this.HIOIOCCOORICHRIHCROOIOIORRCIIO.get()) {
                  glintcolorizer5_21.method13();
               }
            }

            glintcolorizer5_21.field14 += value7;
         }
      }
   }

   @Override
   public int method1() {
      return 10;
   }
}
