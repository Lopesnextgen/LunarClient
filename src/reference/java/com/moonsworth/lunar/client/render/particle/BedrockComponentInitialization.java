package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.render.particle.BedrockComponentBase;
import com.moonsworth.lunar.client.render.particle.BedrockEmitter;
import com.moonsworth.lunar.client.render.particle.IComponentEmitterInitialize;
import com.moonsworth.lunar.client.render.particle.IComponentEmitterUpdate;
import com.moonsworth.lunar.client.render.particle.MolangExpression;
import com.moonsworth.lunar.client.render.particle.MolangParser;

public class BedrockComponentInitialization extends BedrockComponentBase implements IComponentEmitterInitialize, IComponentEmitterUpdate {
   public MolangExpression field1 = MolangParser.field3;
   public MolangExpression field2 = MolangParser.field3;

   public BedrockComponentInitialization() {
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser molangParser) {
      if (!element1.isJsonObject()) {
         return super.method1(element1, molangParser);
      }

      JsonObject json3 = element1.getAsJsonObject();
      if (json3.has("creation_expression")) {
         this.field1 = molangParser.method5(json3.get("creation_expression"));
      }

      if (json3.has("per_update_expression")) {
         this.field2 = molangParser.method5(json3.get("per_update_expression"));
      }

      return super.method1(json3, molangParser);
   }

   @Override
   public JsonElement method2() {
      JsonObject json1 = new JsonObject();
      if (!MolangExpression.method1(this.field1)) {
         json1.add("creation_expression", this.field1.method5());
      }

      if (!MolangExpression.method1(this.field2)) {
         json1.add("per_update_expression", this.field2.method5());
      }

      return json1;
   }

   @Override
   public void method1(BedrockEmitter glintcolorizer5_21) {
      this.field1.get();
      glintcolorizer5_21.method10();
   }

   @Override
   public void method2(BedrockEmitter glintcolorizer5_21) {
      this.field2.get();
      glintcolorizer5_21.method10();
   }
}
