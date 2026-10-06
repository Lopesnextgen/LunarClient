package com.moonsworth.lunar.client.cosmetics.molang;

import com.moonsworth.lunar.client.cosmetics.molang.MolangStmtCompiler;

public class ConstantExpression implements MolangStmtCompiler {
   private final double field1;

   public ConstantExpression(double value) {
      this.field1 = value;
   }

   @Override
   public double run() {
      return this.field1;
   }
}
