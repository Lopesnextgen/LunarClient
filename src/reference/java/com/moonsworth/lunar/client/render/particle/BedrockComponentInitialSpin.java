package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.render.particle.BedrockComponentBase;
import com.moonsworth.lunar.client.render.particle.BedrockParticle;
import com.moonsworth.lunar.client.render.particle.BedrockEmitter;
import com.moonsworth.lunar.client.render.particle.IComponentParticleInitialize;
import com.moonsworth.lunar.client.render.particle.MolangExpression;
import com.moonsworth.lunar.client.render.particle.MolangParser;

public class BedrockComponentInitialSpin extends BedrockComponentBase implements IComponentParticleInitialize {
   public MolangExpression field1 = MolangParser.field3;
   public MolangExpression field2 = MolangParser.field3;

   public BedrockComponentInitialSpin() {
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser molangParser) {
      if (!element1.isJsonObject()) {
         return super.method1(element1, molangParser);
      }

      JsonObject json3 = element1.getAsJsonObject();
      if (json3.has("rotation")) {
         this.field1 = molangParser.method5(json3.get("rotation"));
      }

      if (json3.has("rotation_rate")) {
         this.field2 = molangParser.method5(json3.get("rotation_rate"));
      }

      return super.method1(json3, molangParser);
   }

   @Override
   public JsonElement method2() {
      JsonObject json1 = new JsonObject();
      if (!MolangExpression.method1(this.field1)) {
         json1.add("rotation", this.field1.method5());
      }

      if (!MolangExpression.method1(this.field2)) {
         json1.add("rotation_rate", this.field2.method5());
      }

      return json1;
   }

   @Override
   public void method2(BedrockEmitter glintcolorizer5_21, BedrockParticle glintcolorizer4_22) {
      glintcolorizer4_22.field20 = (float)this.field1.get();
      glintcolorizer4_22.field22 = (float)this.field2.get() / 20.0F;
   }
}
