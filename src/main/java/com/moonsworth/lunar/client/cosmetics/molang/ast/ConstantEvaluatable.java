package com.moonsworth.lunar.client.cosmetics.molang.ast;

import com.eliotlash.molang.ast.Evaluatable;
import com.eliotlash.molang.ast.Evaluator;

public class ConstantEvaluatable implements Evaluatable {
   public final double field1;

   public ConstantEvaluatable(double value) {
      this.field1 = value;
   }

   public double evaluate(Evaluator evaluator1) {
      return this.field1;
   }

   public boolean isConstant() {
      return true;
   }

   public double getConstant() {
      return this.field1;
   }
}
