package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.render.particle.BedrockComponentBase;
import com.moonsworth.lunar.client.render.particle.BedrockParticle;
import com.moonsworth.lunar.client.render.particle.BedrockEmitter;
import com.moonsworth.lunar.client.render.particle.IComponentParticleInitialize;
import com.moonsworth.lunar.client.render.particle.MolangExpression;
import com.moonsworth.lunar.client.render.particle.MolangParser;

public class BedrockComponentInitialSpeed extends BedrockComponentBase implements IComponentParticleInitialize {
   public MolangExpression field1 = MolangParser.field4;
   public MolangExpression[] field2;

   public BedrockComponentInitialSpeed() {
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser molangParser) {
      if (element1.isJsonArray()) {
         JsonArray array3 = element1.getAsJsonArray();
         if (array3.size() >= 3) {
            this.field2 = new MolangExpression[]{molangParser.method5(array3.get(0)), molangParser.method5(array3.get(1)), molangParser.method5(array3.get(2))};
         }
      } else if (element1.isJsonPrimitive()) {
         this.field1 = molangParser.method5(element1);
      }

      return super.method1(element1, molangParser);
   }

   @Override
   public JsonElement method2() {
      if (this.field2 == null) {
         return this.field1.method5();
      }

      JsonArray array1 = new JsonArray();

      for (MolangExpression glintcolorizer_55 : this.field2) {
         array1.add(glintcolorizer_55.method5());
      }

      return array1;
   }

   @Override
   public boolean method3() {
      return true;
   }

   @Override
   public void method2(BedrockEmitter glintcolorizer5_21, BedrockParticle glintcolorizer4_22) {
      if (this.field2 != null) {
         glintcolorizer4_22.field30.set((float)this.field2[0].get(), (float)this.field2[1].get(), (float)this.field2[1].get());
      } else {
         float value3 = (float)this.field1.get();
         glintcolorizer4_22.field30.scale(value3);
      }
   }

   @Override
   public int method1() {
      return 5;
   }
}
