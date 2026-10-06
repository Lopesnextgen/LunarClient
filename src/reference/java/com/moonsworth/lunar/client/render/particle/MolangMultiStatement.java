package com.moonsworth.lunar.client.render.particle;

import com.moonsworth.lunar.client.render.particle.MolangParser;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class MolangMultiStatement extends MolangExpression {
   public List<MolangExpression> expressions = new ArrayList<>();

   public MolangMultiStatement(MolangParser molangParser) {
      super(molangParser);
   }

   @Override
   public double get() {
      double value1 = 0.0;

      for (MolangExpression glintcolorizer_54 : this.expressions) {
         value1 = glintcolorizer_54.get();
      }

      return value1;
   }

   @Override
   public String toString() {
      StringJoiner stringjoiner1 = new StringJoiner("; ");

      for (MolangExpression glintcolorizer_53 : this.expressions) {
         stringjoiner1.add(glintcolorizer_53.toString());
      }

      return stringjoiner1.toString();
   }
}
