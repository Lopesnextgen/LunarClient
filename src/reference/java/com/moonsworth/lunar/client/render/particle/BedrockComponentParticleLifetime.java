package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.moonsworth.lunar.client.render.particle.MolangParser;

public class BedrockComponentParticleLifetime extends BedrockComponentBase implements IComponentParticleUpdate, IComponentParticleInitialize {
   public MolangExpression field1 = MolangParser.field3;
   public boolean field2;

   public BedrockComponentParticleLifetime() {
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser molangParser) {
      if (!element1.isJsonObject()) {
         return super.method1(element1, molangParser);
      }

      JsonObject json3 = element1.getAsJsonObject();
      JsonElement element4 = null;
      if (json3.has("expiration_expression")) {
         element4 = json3.get("expiration_expression");
         this.field2 = false;
      } else {
         if (!json3.has("max_lifetime")) {
            throw new JsonParseException("No expiration_expression or max_lifetime was found in minecraft:particle_lifetime_expression component");
         }

         element4 = json3.get("max_lifetime");
         this.field2 = true;
      }

      this.field1 = molangParser.method5(element4);
      return super.method1(json3, molangParser);
   }

   @Override
   public JsonElement method2() {
      JsonObject json1 = new JsonObject();
      json1.add(this.field2 ? "max_lifetime" : "expiration_expression", this.field1.method5());
      return json1;
   }

   @Override
   public void method1(BedrockEmitter glintcolorizer5_21, BedrockParticle glintcolorizer4_22) {
      if (!this.field2 && this.field1.get() != 0.0) {
         glintcolorizer4_22.field6 = true;
      }
   }

   @Override
   public void method2(BedrockEmitter glintcolorizer5_21, BedrockParticle glintcolorizer4_22) {
      if (this.field2) {
         glintcolorizer4_22.lifetime = (int)(this.field1.get() * 20.0);
      } else {
         glintcolorizer4_22.lifetime = -1;
      }
   }
}
