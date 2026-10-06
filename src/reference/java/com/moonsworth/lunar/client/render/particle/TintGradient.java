package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;

public class TintGradient extends Tint {
   public List<TintGradient.ColorStop> field1;
   public MolangExpression field2;
   public boolean field3;

   public TintGradient(List<TintGradient.ColorStop> list, MolangExpression glintcolorizer_52, boolean flag) {
      this.field1 = list;
      this.field2 = glintcolorizer_52;
      this.field3 = flag;
   }

   @Override
   public void method3(BedrockParticle glintcolorizer4_21) {
      int number2 = this.field1.size();
      if (number2 == 0) {
         glintcolorizer4_21.field35 = glintcolorizer4_21.field36 = glintcolorizer4_21.field37 = glintcolorizer4_21.field38 = 1.0F;
      } else if (number2 == 1) {
         this.field1.get(0).field2.method3(glintcolorizer4_21);
      } else {
         double value3 = this.field2.get();
         value3 = ClampUtils.clamp(value3, 0.0, 1.0);
         TintGradient.ColorStop data5 = this.field1.get(0);
         if (value3 < data5.field1) {
            data5.field2.method3(glintcolorizer4_21);
         } else {
            for (int index6 = 1; index6 < number2; index6++) {
               TintGradient.ColorStop data7 = this.field1.get(index6);
               if (data7.field1 > value3) {
                  data5.field2.method3(glintcolorizer4_21);
                  data7.field2.method4(glintcolorizer4_21, (float)(value3 - data5.field1) / (data7.field1 - data5.field1));
                  return;
               }

               data5 = data7;
            }

            data5.field2.method3(glintcolorizer4_21);
         }
      }
   }

   @Override
   public JsonElement method4() {
      JsonObject json1 = new JsonObject();
      Object obj2;
      if (this.field3) {
         JsonArray array3 = new JsonArray();

         for (TintGradient.ColorStop data5 : this.field1) {
            array3.add(data5.field2.method3());
         }

         obj2 = array3;
      } else {
         JsonObject json6 = new JsonObject();

         for (TintGradient.ColorStop data8 : this.field1) {
            json6.add(String.valueOf(data8.field1), data8.field2.method3());
         }

         obj2 = json6;
      }

      if (!BedrockParticleDeserializer.method1((JsonElement)obj2)) {
         json1.add("gradient", (JsonElement)obj2);
      }

      if (!MolangExpression.method1(this.field2)) {
         json1.add("interpolant", this.field2.method5());
      }

      return json1;
   }

   public static class ColorStop {
      public float field1;
      public TintSolid field2;

      public ColorStop(float value, TintSolid glintcolorizer$data2) {
         this.field1 = value;
         this.field2 = glintcolorizer$data2;
      }
   }
}
