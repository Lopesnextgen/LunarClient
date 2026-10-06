package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.client.render.particle.MolangParser;

public abstract class MolangExpression {
   public MolangParser field1;

   public static boolean method1(MolangExpression glintcolorizer_50) {
      return method3(glintcolorizer_50, 0.0);
   }

   public static boolean method2(MolangExpression glintcolorizer_50) {
      return method3(glintcolorizer_50, 1.0);
   }

   public static boolean method3(MolangExpression glintcolorizer_50, double value) {
      return !(glintcolorizer_50 instanceof MolangValue glintcolorizerimpl23)
         ? false
         : glintcolorizerimpl23.field2 instanceof Constant && Operation.equals(glintcolorizerimpl23.field2.method1().doubleValue(), value);
   }

   public static boolean method4(MolangExpression glintcolorizer_50) {
      return glintcolorizer_50 instanceof MolangValue glintcolorizerimpl21 ? glintcolorizerimpl21.field2 instanceof Constant : false;
   }

   public MolangExpression(MolangParser molangParser) {
      this.field1 = molangParser;
   }

   public abstract double get();

   public JsonElement method5() {
      return new JsonPrimitive(this.toString());
   }
}
