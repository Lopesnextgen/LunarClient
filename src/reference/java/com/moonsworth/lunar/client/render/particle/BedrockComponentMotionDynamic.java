package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.render.particle.BedrockComponentBase;
import com.moonsworth.lunar.client.render.particle.BedrockParticle;
import com.moonsworth.lunar.client.render.particle.BedrockEmitter;
import com.moonsworth.lunar.client.render.particle.IComponentParticleUpdate;
import com.moonsworth.lunar.client.render.particle.MolangExpression;
import com.moonsworth.lunar.client.render.particle.MolangParser;

public class BedrockComponentMotionDynamic extends BedrockComponentMotion implements IComponentParticleUpdate {
   public MolangExpression[] field1;
   public MolangExpression field2;
   public MolangExpression field3;
   public MolangExpression field4;

   public BedrockComponentMotionDynamic() {
      this.field1 = new MolangExpression[]{MolangParser.field3, MolangParser.field3, MolangParser.field3};
      this.field2 = MolangParser.field3;
      this.field3 = MolangParser.field3;
      this.field4 = MolangParser.field3;
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser molangParser) {
      if (!element1.isJsonObject()) {
         return super.method1(element1, molangParser);
      }

      JsonObject json3 = element1.getAsJsonObject();
      if (json3.has("linear_acceleration")) {
         JsonArray array4 = json3.getAsJsonArray("linear_acceleration");
         if (array4.size() >= 3) {
            this.field1[0] = molangParser.method5(array4.get(0));
            this.field1[1] = molangParser.method5(array4.get(1));
            this.field1[2] = molangParser.method5(array4.get(2));
         }
      }

      if (json3.has("linear_drag_coefficient")) {
         this.field2 = molangParser.method5(json3.get("linear_drag_coefficient"));
      }

      if (json3.has("rotation_acceleration")) {
         this.field3 = molangParser.method5(json3.get("rotation_acceleration"));
      }

      if (json3.has("rotation_drag_coefficient")) {
         this.field4 = molangParser.method5(json3.get("rotation_drag_coefficient"));
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

      json1.add("linear_acceleration", array2);
      if (!MolangExpression.method1(this.field2)) {
         json1.add("linear_drag_coefficient", this.field2.method5());
      }

      if (!MolangExpression.method1(this.field3)) {
         json1.add("rotation_acceleration", this.field3.method5());
      }

      if (!MolangExpression.method1(this.field4)) {
         json1.add("rotation_drag_coefficient", this.field4.method5());
      }

      return json1;
   }

   @Override
   public void method1(BedrockEmitter glintcolorizer5_21, BedrockParticle glintcolorizer4_22) {
      glintcolorizer4_22.field31.x = glintcolorizer4_22.field31.x + (float)this.field1[0].get();
      glintcolorizer4_22.field31.y = glintcolorizer4_22.field31.y + (float)this.field1[1].get();
      glintcolorizer4_22.field31.z = glintcolorizer4_22.field31.z + (float)this.field1[2].get();
      glintcolorizer4_22.field33 = (float)this.field2.get();
      glintcolorizer4_22.field23 = glintcolorizer4_22.field23 + (float)this.field3.get() / 20.0F;
      glintcolorizer4_22.field24 = (float)this.field4.get();
   }
}
