package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.client.render.particle.MolangParser;

public class MolangValue extends MolangExpression {
   public IValue field2;
   public boolean field3;

   public MolangValue(MolangParser molangParser, IValue glintcolorizer2_42) {
      super(molangParser);
      this.field2 = glintcolorizer2_42;
   }

   public MolangExpression method1() {
      this.field3 = true;
      return this;
   }

   @Override
   public double get() {
      return this.field2.method1().doubleValue();
   }

   @Override
   public String toString() {
      return (this.field3 ? "return " : "") + this.field2.toString();
   }

   @Override
   public JsonElement method5() {
      return (JsonElement)(this.field2 instanceof Constant ? new JsonPrimitive(this.field2.method1().doubleValue()) : super.method5());
   }
}
