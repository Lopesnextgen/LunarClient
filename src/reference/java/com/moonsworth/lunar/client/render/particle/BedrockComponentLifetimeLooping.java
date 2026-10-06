package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.render.particle.MolangParser;

public class BedrockComponentLifetimeLooping extends BedrockComponentLifetime {
   public MolangExpression field3;

   public BedrockComponentLifetimeLooping() {
      this.field3 = MolangParser.field3;
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser glintcolorizer3iterator2) {
      if (!element1.isJsonObject()) {
         return super.method1(element1, glintcolorizer3iterator2);
      }

      JsonObject json3 = element1.getAsJsonObject();
      if (json3.has("sleep_time")) {
         this.field3 = glintcolorizer3iterator2.method5(json3.get("sleep_time"));
      }

      return super.method1(json3, glintcolorizer3iterator2);
   }

   @Override
   public JsonElement method2() {
      JsonObject json1 = (JsonObject)super.method2();
      if (!MolangExpression.method1(this.field3)) {
         json1.add("sleep_time", this.field3.method5());
      }

      return json1;
   }

   @Override
   public void method2(BedrockEmitter glintcolorizer5_21) {
      double value2 = this.CIHHIHOCOHOHIHRIIIRRIOIHRIROHC.get();
      double value4 = this.field3.get();
      double value6 = glintcolorizer5_21.method1();
      glintcolorizer5_21.lifetime = (int)(value2 * 20.0);
      if (value6 >= value2 && glintcolorizer5_21.playing) {
         glintcolorizer5_21.stop();
      }

      if (value6 >= value4 && !glintcolorizer5_21.playing) {
         glintcolorizer5_21.start();
      }
   }
}
