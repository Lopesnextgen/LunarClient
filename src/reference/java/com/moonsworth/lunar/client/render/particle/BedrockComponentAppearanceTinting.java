package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.render.particle.MolangParser;

public abstract class BedrockComponentAppearanceTinting extends BedrockComponentBase implements IComponentParticleRender {
   public Tint field1 = new TintSolid(
      MolangParser.field4, MolangParser.field4, MolangParser.field4, MolangParser.field4
   );

   public BedrockComponentAppearanceTinting() {
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser molangParser) {
      if (!element1.isJsonObject()) {
         return super.method1(element1, molangParser);
      }

      JsonObject json3 = element1.getAsJsonObject();
      if (json3.has("color")) {
         JsonElement element4 = json3.get("color");
         if (element4.isJsonArray() || element4.isJsonPrimitive()) {
            this.field1 = Tint.method1(element4, molangParser);
         } else if (element4.isJsonObject()) {
            this.field1 = Tint.method2(element4.getAsJsonObject(), molangParser);
         }
      }

      return super.method1(json3, molangParser);
   }

   @Override
   public JsonElement method2() {
      JsonObject json1 = new JsonObject();
      JsonElement element2 = this.field1.method4();
      if (!BedrockParticleDeserializer.method1(element2)) {
         json1.add("color", element2);
      }

      return json1;
   }

   @Override
   public void method1(BedrockEmitter glintcolorizer5_21, float value2) {
   }

   @Override
   public void method2(BedrockEmitter glintcolorizer5_21, float value2) {
   }

   @Override
   public int method1() {
      return -10;
   }
}
