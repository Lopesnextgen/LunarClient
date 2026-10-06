package com.moonsworth.lunar.client.cosmetics.molang;

import com.eliotlash.molang.ast.Evaluatable;
import com.eliotlash.molang.ast.Expr;
import com.eliotlash.molang.ast.Expr.Access;
import com.eliotlash.molang.functions.Function;
import com.eliotlash.molang.variables.ExecutionContext;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap;
import java.util.List;

public class FunctionImpl extends Function {
   private final Evaluatable field1;
   private final List<Access> args;

   public FunctionImpl(String text, Evaluatable evaluatable2, List<Access> list) {
      super(text);
      this.field1 = evaluatable2;
      this.args = list;
   }

   public int getRequiredArguments() {
      return this.args.size();
   }

   public double _evaluate(Expr[] items1, ExecutionContext executioncontext2) {
      Object2DoubleMap object2doublemap3 = executioncontext2.functionScopedArguments;
      Object2DoubleOpenHashMap object2doubleopenhashmap4 = new Object2DoubleOpenHashMap();

      for (int index5 = 0; index5 < this.args.size(); index5++) {
         Access access6 = this.args.get(index5);
         double value7 = this.evaluateArgument(items1, executioncontext2, index5);
         object2doubleopenhashmap4.put(access6, value7);
      }

      executioncontext2.functionScopedArguments = object2doubleopenhashmap4;
      double value9 = this.field1.evaluate(executioncontext2.getEvaluator());
      executioncontext2.functionScopedArguments = object2doublemap3;
      return value9;
   }

   public boolean isConstant() {
      return false;
   }
}
