package com.moonsworth.lunar.client.cosmetics.molang;

import com.eliotlash.molang.ast.Evaluator;
import com.eliotlash.molang.ast.Expr;
import com.eliotlash.molang.ast.Stmt;
import com.eliotlash.molang.ast.Expr.Constant;
import com.moonsworth.lunar.client.cosmetics.molang.MolangRuntime;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import java.util.List;
import lombok.Generated;

public class EvaluatorImpl extends Evaluator {
   private MolangRuntime field1;

   public EvaluatorImpl() {
   }

   public double evaluate(List<Stmt> list) {
      try {
         return this.field1.evaluate(list);
      } catch (Exception exception3) {
         CrashReporter.method5(exception3, "Molang JIT");
         return 0.0;
      }
   }

   public Double evaluate(Expr expr1) {
      try {
         return expr1 instanceof Constant constant2 ? constant2.value() : this.field1.method1(expr1);
      } catch (Exception exception3) {
         CrashReporter.method5(exception3, "Molang JIT");
         return 0.0;
      }
   }

   @Generated
   public void method1(MolangRuntime highlight1) {
      this.field1 = highlight1;
   }
}
