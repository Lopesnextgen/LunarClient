package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.render.particle.BedrockComponentBase;
import com.moonsworth.lunar.client.render.particle.IComponentParticleInitialize;
import com.moonsworth.lunar.client.render.particle.MolangExpression;
import com.moonsworth.lunar.client.render.particle.MolangParser;

public abstract class BedrockComponentShapeBase extends BedrockComponentBase implements IComponentParticleInitialize {
   public MolangExpression[] field1 = new MolangExpression[]{MolangParser.field3, MolangParser.field3, MolangParser.field3};
   public ParticleDirection field2;
   public boolean field3;

   public BedrockComponentShapeBase() {
      this.field2 = ParticleDirection.field2;
      this.field3 = false;
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser molangParser) {
      if (!element1.isJsonObject()) {
         return super.method1(element1, molangParser);
      }

      JsonObject json3 = element1.getAsJsonObject();
      if (json3.has("offset")) {
         JsonArray array4 = json3.getAsJsonArray("offset");
         if (array4.size() >= 3) {
            this.field1[0] = molangParser.method5(array4.get(0));
            this.field1[1] = molangParser.method5(array4.get(1));
            this.field1[2] = molangParser.method5(array4.get(2));
         }
      }

      if (json3.has("direction")) {
         JsonElement element6 = json3.get("direction");
         if (element6.isJsonPrimitive()) {
            String text5 = element6.getAsString();
            if (text5.equals("inwards")) {
               this.field2 = ParticleDirection.field1;
            } else {
               this.field2 = ParticleDirection.field2;
            }
         } else if (element6.isJsonArray()) {
            JsonArray array7 = element6.getAsJsonArray();
            if (array7.size() >= 3) {
               this.field2 = new ParticleDirection.FixedVelocity(molangParser.method5(array7.get(0)), molangParser.method5(array7.get(1)), molangParser.method5(array7.get(2)));
            }
         }
      }

      if (json3.has("surface_only")) {
         this.field3 = json3.get("surface_only").getAsBoolean();
      }

      return super.method1(json3, molangParser);
   }

   @Override
   public JsonElement method2() {
      JsonObject json1 = new JsonObject();
      JsonArray array2 = new JsonArray();

      for (MolangExpression glintcolorizer_56 : this.field1) {
         array2.add(glintcolorizer_56.method5());
      }

      json1.add("offset", array2);
      if (this.field2 != ParticleDirection.field2) {
         json1.add("direction", this.field2.method2());
      }

      if (this.field3) {
         json1.addProperty("surface_only", true);
      }

      return json1;
   }
}
