package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.render.particle.BedrockComponentBase;
import com.moonsworth.lunar.client.render.particle.BedrockParticle;
import com.moonsworth.lunar.client.render.particle.BedrockEmitter;
import com.moonsworth.lunar.client.render.particle.IComponentParticleUpdate;
import com.moonsworth.lunar.client.render.particle.IComponentParticleInitialize;
import com.moonsworth.lunar.client.render.particle.MolangExpression;
import com.moonsworth.lunar.client.render.particle.MolangParser;
import javax.vecmath.Vector3f;

public class BedrockComponentMotionParametric extends BedrockComponentMotion implements IComponentParticleUpdate, IComponentParticleInitialize {
   public MolangExpression[] field1 = new MolangExpression[]{MolangParser.field3, MolangParser.field3, MolangParser.field3};
   public MolangExpression field2 = MolangParser.field3;

   public BedrockComponentMotionParametric() {
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser molangParser) {
      if (!element1.isJsonObject()) {
         return super.method1(element1, molangParser);
      }

      JsonObject json3 = element1.getAsJsonObject();
      if (json3.has("relative_position") && json3.get("relative_position").isJsonArray()) {
         JsonArray array4 = json3.get("relative_position").getAsJsonArray();
         this.field1[0] = molangParser.method5(array4.get(0));
         this.field1[1] = molangParser.method5(array4.get(1));
         this.field1[2] = molangParser.method5(array4.get(2));
      }

      if (json3.has("rotation")) {
         this.field2 = molangParser.method5(json3.get("rotation"));
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

      json1.add("relative_position", array2);
      if (!MolangExpression.method1(this.field2)) {
         json1.add("rotation", this.field2.method5());
      }

      return json1;
   }

   @Override
   public void method2(BedrockEmitter glintcolorizer5_21, BedrockParticle glintcolorizer4_22) {
      Vector3f vector3f3 = new Vector3f((float)this.field1[0].get(), (float)this.field1[1].get(), (float)this.field1[2].get());
      glintcolorizer4_22.field17 = true;
      glintcolorizer4_22.field26.set(glintcolorizer4_22.field25);
      glintcolorizer4_22.field28.transform(vector3f3);
      glintcolorizer4_22.field25.x = glintcolorizer4_22.field26.x + vector3f3.x;
      glintcolorizer4_22.field25.y = glintcolorizer4_22.field26.y + vector3f3.y;
      glintcolorizer4_22.field25.z = glintcolorizer4_22.field26.z + vector3f3.z;
      glintcolorizer4_22.rotation = (float)this.field2.get();
   }

   @Override
   public void method1(BedrockEmitter glintcolorizer5_21, BedrockParticle glintcolorizer4_22) {
      Vector3f vector3f3 = new Vector3f((float)this.field1[0].get(), (float)this.field1[1].get(), (float)this.field1[2].get());
      glintcolorizer4_22.field28.transform(vector3f3);
      glintcolorizer4_22.field25.x = glintcolorizer4_22.field26.x + vector3f3.x;
      glintcolorizer4_22.field25.y = glintcolorizer4_22.field26.y + vector3f3.y;
      glintcolorizer4_22.field25.z = glintcolorizer4_22.field26.z + vector3f3.z;
      glintcolorizer4_22.rotation = (float)this.field2.get();
   }

   @Override
   public int method1() {
      return 10;
   }
}
