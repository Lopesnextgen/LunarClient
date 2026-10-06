package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.apache.commons.lang3.StringUtils;

public class TintSolid extends Tint {
   public MolangExpression field1;
   public MolangExpression field2;
   public MolangExpression field3;
   public MolangExpression field4;

   public TintSolid(MolangExpression glintcolorizer_51, MolangExpression glintcolorizer_52, MolangExpression glintcolorizer_53, MolangExpression glintcolorizer_54) {
      this.field1 = glintcolorizer_51;
      this.field2 = glintcolorizer_52;
      this.field3 = glintcolorizer_53;
      this.field4 = glintcolorizer_54;
   }

   public boolean isConstant() {
      return MolangExpression.method4(this.field1)
         && MolangExpression.method4(this.field2)
         && MolangExpression.method4(this.field3)
         && MolangExpression.method4(this.field4);
   }

   @Override
   public void method3(BedrockParticle glintcolorizer4_21) {
      glintcolorizer4_21.field35 = (float)this.field1.get();
      glintcolorizer4_21.field36 = (float)this.field2.get();
      glintcolorizer4_21.field37 = (float)this.field3.get();
      glintcolorizer4_21.field38 = (float)this.field4.get();
   }

   @Override
   public JsonElement method4() {
      JsonArray array1 = new JsonArray();
      if (MolangExpression.method2(this.field1)
         && MolangExpression.method2(this.field2)
         && MolangExpression.method2(this.field3)
         && MolangExpression.method2(this.field4)) {
         return array1;
      }

      array1.add(this.field1.method5());
      array1.add(this.field2.method5());
      array1.add(this.field3.method5());
      array1.add(this.field4.method5());
      return array1;
   }

   public JsonElement method3() {
      int number1 = (int)(this.field1.get() * 255.0) & 0xFF;
      int number2 = (int)(this.field2.get() * 255.0) & 0xFF;
      int number3 = (int)(this.field3.get() * 255.0) & 0xFF;
      int number4 = (int)(this.field4.get() * 255.0) & 0xFF;
      String text5 = "#";
      if (number4 < 255) {
         text5 = text5 + StringUtils.leftPad(Integer.toHexString(number4), 2, "0").toUpperCase();
      }

      text5 = text5 + StringUtils.leftPad(Integer.toHexString(number1), 2, "0").toUpperCase();
      text5 = text5 + StringUtils.leftPad(Integer.toHexString(number2), 2, "0").toUpperCase();
      text5 = text5 + StringUtils.leftPad(Integer.toHexString(number3), 2, "0").toUpperCase();
      return new JsonPrimitive(text5);
   }

   public void method4(BedrockParticle glintcolorizer4_21, float value) {
      glintcolorizer4_21.field35 = Interpolations.lerp(glintcolorizer4_21.field35, (float)this.field1.get(), value);
      glintcolorizer4_21.field36 = Interpolations.lerp(glintcolorizer4_21.field36, (float)this.field2.get(), value);
      glintcolorizer4_21.field37 = Interpolations.lerp(glintcolorizer4_21.field37, (float)this.field3.get(), value);
      glintcolorizer4_21.field38 = Interpolations.lerp(glintcolorizer4_21.field38, (float)this.field4.get(), value);
   }
}
