package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.client.render.particle.MolangParser;
import javax.vecmath.Vector3d;

public class BedrockComponentKillPlane extends BedrockComponentBase implements IComponentParticleUpdate {
   public float field1;
   public float field2;
   public float field3;
   public float field4;

   public BedrockComponentKillPlane() {
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser molangParser) {
      if (!element1.isJsonArray()) {
         return super.method1(element1, molangParser);
      }

      JsonArray array3 = element1.getAsJsonArray();
      if (array3.size() >= 4) {
         this.field1 = array3.get(0).getAsFloat();
         this.field2 = array3.get(1).getAsFloat();
         this.field3 = array3.get(2).getAsFloat();
         this.field4 = array3.get(3).getAsFloat();
      }

      return super.method1(element1, molangParser);
   }

   @Override
   public JsonElement method2() {
      JsonArray array1 = new JsonArray();
      if (Operation.equals(this.field1, 0.0)
         && Operation.equals(this.field2, 0.0)
         && Operation.equals(this.field3, 0.0)
         && Operation.equals(this.field4, 0.0)) {
         return array1;
      }

      array1.add(new JsonPrimitive(this.field1));
      array1.add(new JsonPrimitive(this.field2));
      array1.add(new JsonPrimitive(this.field3));
      array1.add(new JsonPrimitive(this.field4));
      return array1;
   }

   @Override
   public void method1(BedrockEmitter glintcolorizer5_21, BedrockParticle glintcolorizer4_22) {
      if (!glintcolorizer4_22.field6) {
         Vector3d vector3d3 = new Vector3d(glintcolorizer4_22.field27);
         Vector3d vector3d4 = new Vector3d(glintcolorizer4_22.field25);
         if (!glintcolorizer4_22.field7) {
            vector3d4.sub(glintcolorizer5_21.field8);
            vector3d3.sub(glintcolorizer5_21.field8);
         }

         double value5 = this.field1 * vector3d3.x + this.field2 * vector3d3.y + this.field3 * vector3d3.z + this.field4;
         double value7 = this.field1 * vector3d4.x + this.field2 * vector3d4.y + this.field3 * vector3d4.z + this.field4;
         if (value5 > 0.0 && value7 < 0.0 || value5 < 0.0 && value7 > 0.0) {
            glintcolorizer4_22.field6 = true;
         }
      }
   }

   @Override
   public int method1() {
      return 100;
   }
}
