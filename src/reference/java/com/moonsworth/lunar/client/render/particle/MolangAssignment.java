package com.moonsworth.lunar.client.render.particle;

import com.moonsworth.lunar.client.render.particle.MolangParser;

public class MolangAssignment extends MolangExpression {
   public Variable field2;
   public IValue field3;

   public MolangAssignment(MolangParser molangParser, Variable variable, IValue glintcolorizer2_43) {
      super(molangParser);
      this.field2 = variable;
      this.field3 = glintcolorizer2_43;
   }

   @Override
   public double get() {
      double value1 = this.field3.method1().doubleValue();
      this.field2.set(value1);
      return value1;
   }

   @Override
   public String toString() {
      return this.field2.getName() + " = " + this.field3.toString();
   }
}
