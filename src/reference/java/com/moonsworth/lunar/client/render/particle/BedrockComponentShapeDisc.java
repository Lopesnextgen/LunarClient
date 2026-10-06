package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.render.particle.BedrockComponentBase;
import com.moonsworth.lunar.client.render.particle.BedrockParticle;
import com.moonsworth.lunar.client.render.particle.BedrockEmitter;
import com.moonsworth.lunar.client.render.particle.MolangExpression;
import com.moonsworth.lunar.client.render.particle.MolangParser;
import java.util.concurrent.ThreadLocalRandom;
import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

public class BedrockComponentShapeDisc extends BedrockComponentShapeSphere {
   public MolangExpression[] field5 = new MolangExpression[]{MolangParser.field3, MolangParser.field4, MolangParser.field3};

   public BedrockComponentShapeDisc() {
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser glintcolorizer3iterator2) {
      if (!element1.isJsonObject()) {
         return super.method1(element1, glintcolorizer3iterator2);
      }

      JsonObject json3 = element1.getAsJsonObject();
      if (json3.has("plane_normal")) {
         JsonArray array4 = json3.getAsJsonArray("plane_normal");
         if (array4.size() >= 3) {
            this.field5[0] = glintcolorizer3iterator2.method5(array4.get(0));
            this.field5[1] = glintcolorizer3iterator2.method5(array4.get(1));
            this.field5[2] = glintcolorizer3iterator2.method5(array4.get(2));
         }
      }

      return super.method1(json3, glintcolorizer3iterator2);
   }

   @Override
   public JsonElement method2() {
      JsonObject json1 = (JsonObject)super.method2();
      JsonArray array2 = new JsonArray();

      for (MolangExpression glintcolorizer_56 : this.field5) {
         array2.add(glintcolorizer_56.method5());
      }

      json1.add("plane_normal", array2);
      return json1;
   }

   @Override
   public void method2(BedrockEmitter glintcolorizer5_21, BedrockParticle glintcolorizer4_22) {
      float value3 = (float)this.CHHRCIHIRROOIIRIHOHHICRHOROICC[0].get();
      float value4 = (float)this.CHHRCIHIRROOIIRIHOHHICRHOROICC[1].get();
      float value5 = (float)this.CHHRCIHIRROOIIRIHOHHICRHOROICC[2].get();
      Vector3f vector3f6 = new Vector3f((float)this.field5[0].get(), (float)this.field5[1].get(), (float)this.field5[2].get());
      vector3f6.normalize();
      Quat4f quat4f7 = new Quat4f(vector3f6.x, vector3f6.y, vector3f6.z, 1.0F);
      Matrix4f matrix4f8 = new Matrix4f();
      matrix4f8.set(quat4f7);
      ThreadLocalRandom threadlocalrandom9 = ThreadLocalRandom.current();
      Vector4f vector4f10 = new Vector4f(threadlocalrandom9.nextFloat() - 0.5F, 0.0F, threadlocalrandom9.nextFloat() - 0.5F, 0.0F);
      vector4f10.normalize();
      matrix4f8.transform(vector4f10);
      vector4f10.scale((float)(this.CROCCIIHICRCRIOIRIHIORRRIHCHCI.get() * (this.HCOHIIIIROOCHIHOHRHHHIORHHHOHI ? 1.0 : threadlocalrandom9.nextDouble())));
      vector4f10.add(new Vector4f(value3, value4, value5, 0.0F));
      glintcolorizer4_22.field25.x = glintcolorizer4_22.field25.x + vector4f10.x;
      glintcolorizer4_22.field25.y = glintcolorizer4_22.field25.y + vector4f10.y;
      glintcolorizer4_22.field25.z = glintcolorizer4_22.field25.z + vector4f10.z;
      this.HCHIRIROHRRROIHCOOCICHCIHOHHIH.method1(glintcolorizer4_22, value3, value4, value5);
   }
}
