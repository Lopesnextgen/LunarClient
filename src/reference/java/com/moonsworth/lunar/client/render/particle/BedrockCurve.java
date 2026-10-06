package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.render.particle.MolangParser;

public class BedrockCurve {
   public BedrockCurveType field1 = BedrockCurveType.LINEAR;
   public MolangExpression[] field2;
   public MolangExpression field3;
   public MolangExpression field4;
   public Variable field5;

   public BedrockCurve() {
      this.field2 = new MolangExpression[]{MolangParser.field3, MolangParser.field4, MolangParser.field3};
   }

   public double method1() {
      return this.method2(this.field3.get() / this.field4.get());
   }

   private double method2(double value) {
      int index3 = this.field2.length;
      if (index3 == 0) {
         return 0.0;
      }

      if (index3 == 1) {
         return this.field2[0].get();
      }

      if (value < 0.0) {
         value = -(1.0 + value);
      }

      value = ClampUtils.clamp(value, 0.0, 1.0);
      if (this.field1 == BedrockCurveType.HERMITE) {
         if (index3 <= 3) {
            return this.field2[index3 - 2].get();
         }

         value *= index3 - 3;
         int number12 = (int)value + 1;
         MolangExpression glintcolorizer_513 = this.method3(number12 - 1);
         MolangExpression glintcolorizer_514 = this.method3(number12);
         MolangExpression glintcolorizer_57 = this.method3(number12 + 1);
         MolangExpression glintcolorizer_58 = this.method3(number12 + 2);
         return Interpolations.cubicHermite(glintcolorizer_513.get(), glintcolorizer_514.get(), glintcolorizer_57.get(), glintcolorizer_58.get(), value % 1.0);
      } else {
         value *= index3 - 1;
         int number4 = (int)value;
         MolangExpression glintcolorizer_55 = this.method3(number4);
         MolangExpression glintcolorizer_56 = this.method3(number4 + 1);
         return Interpolations.lerp(glintcolorizer_55.get(), glintcolorizer_56.get(), value % 1.0);
      }
   }

   private MolangExpression method3(int index1) {
      if (index1 < 0) {
         return this.field2[0];
      } else {
         return index1 >= this.field2.length ? this.field2[this.field2.length - 1] : this.field2[index1];
      }
   }

   public void method4(JsonObject json1, MolangParser molangParser) {
      if (json1.has("type")) {
         this.field1 = BedrockCurveType.fromString(json1.get("type").getAsString());
      }

      if (json1.has("input")) {
         this.field3 = molangParser.method5(json1.get("input"));
      }

      if (json1.has("horizontal_range")) {
         this.field4 = molangParser.method5(json1.get("horizontal_range"));
      }

      if (json1.has("nodes")) {
         JsonArray array3 = json1.getAsJsonArray("nodes");
         MolangExpression[] items4 = new MolangExpression[array3.size()];
         int index5 = 0;

         for (int index6 = items4.length; index5 < index6; index5++) {
            items4[index5] = molangParser.method5(array3.get(index5));
         }

         this.field2 = items4;
      }
   }

   public JsonElement method5() {
      JsonObject json1 = new JsonObject();
      JsonArray array2 = new JsonArray();
      json1.addProperty("type", this.field1.id);
      json1.add("nodes", array2);
      json1.add("input", this.field3.method5());
      json1.add("horizontal_range", this.field4.method5());

      for (MolangExpression glintcolorizer_56 : this.field2) {
         array2.add(glintcolorizer_56.method5());
      }

      return json1;
   }
}
