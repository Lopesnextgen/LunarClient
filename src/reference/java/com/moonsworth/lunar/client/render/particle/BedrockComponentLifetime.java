package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.render.particle.MolangParser;

public abstract class BedrockComponentLifetime extends BedrockComponentBase implements IComponentEmitterUpdate {
   public static final MolangExpression field1 = new MolangValue(null, new Constant(10.0));
   public MolangExpression field2 = field1;

   public BedrockComponentLifetime() {
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser molangParser) {
      if (!element1.isJsonObject()) {
         return super.method1(element1, molangParser);
      }

      JsonObject json3 = element1.getAsJsonObject();
      if (json3.has(this.method4())) {
         this.field2 = molangParser.method5(json3.get(this.method4()));
      }

      return super.method1(json3, molangParser);
   }

   @Override
   public JsonElement method2() {
      JsonObject json1 = new JsonObject();
      if (!MolangExpression.method3(this.field2, 10.0)) {
         json1.add(this.method4(), this.field2.method5());
      }

      return json1;
   }

   protected String method4() {
      return "active_time";
   }

   @Override
   public int method1() {
      return -10;
   }
}
