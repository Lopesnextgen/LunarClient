package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.render.particle.MolangParser;

public class BedrockComponentLifetimeExpression extends BedrockComponentLifetime {
   public MolangExpression field3;

   public BedrockComponentLifetimeExpression() {
      this.field3 = MolangParser.field3;
   }

   @Override
   protected String method4() {
      return "activation_expression";
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser glintcolorizer3iterator2) {
      if (!element1.isJsonObject()) {
         return super.method1(element1, glintcolorizer3iterator2);
      }

      JsonObject json3 = element1.getAsJsonObject();
      if (json3.has("expiration_expression")) {
         this.field3 = glintcolorizer3iterator2.method5(json3.get("expiration_expression"));
      }

      return super.method1(json3, glintcolorizer3iterator2);
   }

   @Override
   public JsonElement method2() {
      JsonObject json1 = (JsonObject)super.method2();
      if (!MolangExpression.method1(this.field3)) {
         json1.add("expiration_expression", this.field3.method5());
      }

      return json1;
   }

   @Override
   public void method2(BedrockEmitter glintcolorizer5_21) {
      if (!Operation.equals(this.CIHHIHOCOHOHIHRIIIRRIOIHRIROHC.get(), 0.0)) {
         glintcolorizer5_21.start();
      }

      if (!Operation.equals(this.field3.get(), 0.0)) {
         glintcolorizer5_21.stop();
      }
   }
}
