package com.moonsworth.lunar.client.cosmetics.molang;

import com.eliotlash.molang.ast.Accessible;
import com.eliotlash.molang.ast.Evaluator;
import com.eliotlash.molang.ast.Expr;
import com.eliotlash.molang.ast.StmtContext;
import com.eliotlash.molang.ast.Expr.Access;
import com.eliotlash.molang.ast.Expr.Assignment;
import com.eliotlash.molang.ast.Expr.BinOp;
import com.eliotlash.molang.ast.Expr.Block;
import com.eliotlash.molang.ast.Expr.Call;
import com.eliotlash.molang.ast.Expr.Coalesce;
import com.eliotlash.molang.ast.Expr.Conditional;
import com.eliotlash.molang.ast.Expr.Constant;
import com.eliotlash.molang.ast.Expr.Group;
import com.eliotlash.molang.ast.Expr.Negate;
import com.eliotlash.molang.ast.Expr.Not;
import com.eliotlash.molang.ast.Expr.Str;
import com.eliotlash.molang.ast.Expr.Struct;
import com.eliotlash.molang.ast.Expr.SwitchContext;
import com.eliotlash.molang.ast.Expr.Ternary;
import com.eliotlash.molang.ast.Expr.Variable;
import com.eliotlash.molang.ast.Stmt.Break;
import com.eliotlash.molang.ast.Stmt.Continue;
import com.eliotlash.molang.ast.Stmt.Expression;
import com.eliotlash.molang.ast.Stmt.If;
import com.eliotlash.molang.ast.Stmt.Loop;
import com.eliotlash.molang.ast.Stmt.Return;
import com.eliotlash.molang.functions.Function;
import com.eliotlash.molang.functions.FunctionDefinition;
import com.eliotlash.molang.utils.MolangUtils;
import com.eliotlash.molang.variables.ExecutionContext;
import com.eliotlash.molang.variables.RuntimeVariable;

public class EvaluatorIterator extends Evaluator {
   private final ExecutionContext field1;
   private int depth = 0;

   public EvaluatorIterator(ExecutionContext executioncontext1) {
      this.field1 = executioncontext1;
   }

   public Void visitExpression(Expression expression1, StmtContext stmtcontext2) {
      System.out.println("  ".repeat(this.depth) + "Expression");
      return super.visitExpression(expression1, stmtcontext2);
   }

   public Void visitReturn(Return value1, StmtContext stmtcontext2) {
      System.out.println("  ".repeat(this.depth) + "Return");
      return super.visitReturn(value1, stmtcontext2);
   }

   public Void visitBreak(Break value1, StmtContext stmtcontext2) {
      System.out.println("  ".repeat(this.depth) + "Break");
      return super.visitBreak(value1, stmtcontext2);
   }

   public Void visitContinue(Continue value1, StmtContext stmtcontext2) {
      System.out.println("  ".repeat(this.depth) + "Continue");
      return super.visitContinue(value1, stmtcontext2);
   }

   public Void visitLoop(Loop loop1, StmtContext stmtcontext2) {
      double value3 = this.evaluate(loop1.count());
      System.out.println("  ".repeat(this.depth) + "Loop(" + value3 + ")");
      this.depth++;

      for (int index5 = 0; index5 < value3; index5++) {
         this.evaluate(loop1.expr());
      }

      this.depth--;
      return null;
   }

   public Double visitAssignment(Assignment assignment1) {
      this.depth++;
      double value2 = this.evaluate(assignment1.expression());
      this.depth--;
      if (assignment1.variable() instanceof Access access5) {
         Accessible accessible6 = access5.target();
         if (accessible6 instanceof Variable variable7) {
            System.out.println("  ".repeat(this.depth) + "Assignment(" + this.method1(variable7) + ", Variable)");
            System.out.println("  ".repeat(this.depth + 1) + "= " + value2);
            this.field1.assignableMap.put(access5, value2);
            this.field1.parseRuntimeVariable(variable7.flavor(), variable7.name(), access5);
         } else {
            if (!(accessible6 instanceof Struct struct8)) {
               throw new RuntimeException("Unexpected assignment to non variable/struct.");
            }

            System.out.println("  ".repeat(this.depth) + "Assignment(" + this.method1(struct8) + ", Struct)");
            System.out.println("  ".repeat(this.depth + 1) + "= " + value2);
            this.field1.getStructMap().put(struct8, value2);
         }

         return value2;
      } else {
         System.out.println("  ".repeat(this.depth) + "Assignment(" + this.method1(assignment1) + ")");
         System.out.println("  ".repeat(this.depth + 1) + "= 0");
         return 0.0;
      }
   }

   public Void visitIf(If value1, StmtContext stmtcontext2) {
      boolean flag3 = false;
      this.depth++;
      Double value4 = this.evaluate(value1.condition());
      this.depth--;
      if (MolangUtils.doubleToBoolean(value4)) {
         System.out.println("  ".repeat(this.depth) + "If(" + this.method1(value1.condition()) + ")");
         this.depth++;
         this.evaluate(value1.body().statements(), stmtcontext2);
         this.depth--;
         flag3 = true;
      }

      for (If value6 : value1.elifs()) {
         if (!flag3) {
            this.depth++;
            Double value7 = this.evaluate(value6.condition());
            this.depth--;
            if (MolangUtils.doubleToBoolean(value7)) {
               System.out.println("  ".repeat(this.depth) + "Elif(" + this.method1(value6.condition()) + ")");
               this.depth++;
               this.evaluate(value6.body().statements(), stmtcontext2);
               this.depth--;
               flag3 = true;
            }
         }
      }

      if (!flag3 && value1.elseBlock() != null) {
         System.out.println("  ".repeat(this.depth) + "Else");
         this.depth++;
         this.evaluate(value1.elseBlock().statements(), stmtcontext2);
         this.depth--;
      }

      return null;
   }

   public Double visitAccess(Access access1) {
      if (this.field1.assignableMap.containsKey(access1)) {
         System.out.println("  ".repeat(this.depth) + "Access(" + this.method1(access1) + ", Assignable)");
         double value8 = this.field1.assignableMap.getDouble(access1);
         System.out.println("  ".repeat(this.depth + 1) + "= " + value8);
         return value8;
      }

      if (this.field1.functionScopedArguments.containsKey(access1)) {
         System.out.println("  ".repeat(this.depth) + "Access(" + this.method1(access1) + ", FunctionScopedArgument)");
         double value7 = this.field1.functionScopedArguments.getDouble(access1);
         System.out.println("  ".repeat(this.depth + 1) + "= " + value7);
         return value7;
      }

      Accessible accessible2 = access1.target();
      if (accessible2 instanceof Variable variable3) {
         RuntimeVariable runtimevariable4 = this.field1.getCachedVariable(variable3.flavor(), access1.member());
         if (this.field1.getVariableMap().containsKey(runtimevariable4)) {
            System.out.println("  ".repeat(this.depth) + "Access(" + this.method1(variable3) + ", " + this.method1(access1) + ", Variable)");
            double value5 = this.field1.getVariableMap().getDouble(runtimevariable4);
            System.out.println("  ".repeat(this.depth + 1) + "= " + value5);
            return value5;
         }
      }

      if (accessible2 instanceof Struct struct9 && this.field1.getStructMap().containsKey(struct9)) {
         System.out.println("  ".repeat(this.depth) + "Access(" + access1.member() + ", Struct)");
         double value10 = this.field1.getStructMap().getDouble(struct9);
         System.out.println("  ".repeat(this.depth + 1) + "= " + value10);
         return value10;
      } else {
         return null;
      }
   }

   public Double visitBinOp(BinOp binop1) {
      System.out.println("  ".repeat(this.depth) + "BinOp(" + this.method1(binop1) + ")");
      if (binop1.left() instanceof Str str3 && binop1.right() instanceof Str str4) {
         double value11 = binop1.operator().applyString(str3.val(), str4.val());
         System.out.println("  ".repeat(this.depth + 1) + "= " + value11);
         return value11;
      } else {
         this.depth++;
         System.out.println("  ".repeat(this.depth) + "Left");
         this.depth++;
         double value10 = this.evaluate(binop1.left());
         this.depth--;
         System.out.println("  ".repeat(this.depth) + "Right");
         this.depth++;
         double value5 = this.evaluate(binop1.right());
         this.depth--;
         double value7 = binop1.operator().apply(() -> value10, () -> value5);
         System.out.println("  ".repeat(this.depth) + "= " + value7);
         this.depth--;
         return value7;
      }
   }

   public Double visitCall(Call call1) {
      FunctionDefinition functiondefinition2 = new FunctionDefinition(call1.target(), call1.member());
      System.out.println("  ".repeat(this.depth) + "Call(" + this.method1(call1) + ")");
      Function function3 = this.field1.getFunction(functiondefinition2);
      if (function3 == null) {
         System.out.println("  ".repeat(this.depth + 1) + "= 0  | Function not found");
         return 0.0;
      }

      try {
         this.depth++;
         double value4 = function3.evaluate(call1.arguments().toArray(Expr[]::new), this.field1);
         System.out.println("  ".repeat(this.depth) + "= " + value4);
         this.depth--;
         return value4;
      } catch (Exception exception6) {
         System.out.println("  ".repeat(this.depth + 1) + "= 0  | " + exception6.getMessage());
         return 0.0;
      }
   }

   public Double visitCoalesce(Coalesce coalesce1) {
      System.out.println("  ".repeat(this.depth) + "Coalesce(" + this.method1(coalesce1) + ")");
      this.depth++;
      Double value2 = this.evaluateNullable(coalesce1.value());
      Double value3 = value2 == null ? this.evaluate(coalesce1.fallback()) : value2;
      System.out.println("  ".repeat(this.depth) + "= " + value3);
      this.depth--;
      return value3;
   }

   public Double visitConstant(Constant constant1) {
      System.out.println("  ".repeat(this.depth) + "Constant(" + this.method1(constant1) + ")");
      return constant1.value();
   }

   public Double visitNegate(Negate negate1) {
      System.out.println("  ".repeat(this.depth) + "Negate(" + this.method1(negate1) + ")");
      this.depth++;
      double value2 = -this.evaluate(negate1.value());
      System.out.println("  ".repeat(this.depth) + "= " + value2);
      this.depth--;
      return value2;
   }

   public Double visitNot(Not not1) {
      System.out.println("  ".repeat(this.depth) + "Not(" + this.method1(not1) + ")");
      this.depth++;
      Double value2 = this.evaluate(not1.value()) == 0.0 ? 1.0 : 0.0;
      System.out.println("  ".repeat(this.depth) + "= " + value2);
      this.depth--;
      return value2;
   }

   public Double visitConditional(Conditional conditional1) {
      System.out.println("  ".repeat(this.depth) + "Conditional(" + this.method1(conditional1) + ")");
      this.depth++;
      double value2 = this.evaluate(conditional1.condition());
      double value4 = value2 == 0.0 ? 0.0 : this.evaluate(conditional1.ifTrue());
      System.out.println("  ".repeat(this.depth) + "= " + value4);
      this.depth--;
      return value4;
   }

   public Double visitTernary(Ternary ternary1) {
      System.out.println("  ".repeat(this.depth) + "Ternary(" + this.method1(ternary1) + ")");
      this.depth++;
      Expr expr2 = this.evaluate(ternary1.condition()) == 0.0 ? ternary1.ifFalse() : ternary1.ifTrue();
      Double value3 = this.evaluate(expr2);
      System.out.println("  ".repeat(this.depth) + "= " + value3);
      this.depth--;
      return value3;
   }

   public Double visitSwitchContext(SwitchContext switchcontext1) {
      System.out.println("  ".repeat(this.depth) + "Switch(" + this.method1(switchcontext1) + ")");
      this.depth++;
      this.field1.contextStack.push(switchcontext1.left());
      Double value2 = this.evaluate(switchcontext1.right());
      this.field1.contextStack.pop();
      System.out.println("  ".repeat(this.depth) + "= " + value2);
      this.depth--;
      return value2;
   }

   public Double visitVariable(Variable variable1) {
      System.out.println("  ".repeat(this.depth) + "Variable(" + this.method1(variable1) + ")");
      this.depth++;
      RuntimeVariable runtimevariable2 = this.field1.getCachedVariable(variable1.flavor(), variable1.name());
      Double value3 = this.field1.getVariableMap().getOrDefault(runtimevariable2, 0.0);
      System.out.println("  ".repeat(this.depth) + "= " + value3);
      this.depth--;
      return value3;
   }

   public String visitString(Str str1) {
      System.out.println("  ".repeat(this.depth) + "String(" + this.method1(str1) + ")");
      return str1.val();
   }

   public String evaluateString(Expr expr1) {
      System.out.println("  ".repeat(this.depth) + "String(" + this.method1(expr1) + ")");
      this.depth++;
      String text2 = expr1 instanceof Str ? ((Str)expr1).val() : ((Double)expr1.accept(this)).toString();
      System.out.println("  ".repeat(this.depth) + "= " + text2);
      this.depth--;
      return text2;
   }

   private String method1(Expr expr1) {
      if (expr1 instanceof Str str2) {
         return "\"" + str2.val() + "\"";
      } else if (expr1 instanceof Access access3) {
         return access3.member();
      } else if (expr1 instanceof Call call4) {
         return this.method1(call4.target()) + "." + call4.member() + "()";
      } else if (expr1 instanceof Coalesce coalesce5) {
         return this.method1(coalesce5.value()) + " ?? " + this.method1(coalesce5.fallback());
      } else if (expr1 instanceof Struct struct6) {
         return struct6.parent() == null ? this.method1(struct6.target()) : this.method1(struct6.parent()) + "." + this.method1(struct6.target());
      } else if (expr1 instanceof Assignment assignment7) {
         return this.method1(assignment7.variable()) + "." + this.method1(assignment7.expression());
      } else if (expr1 instanceof BinOp binop8) {
         return this.method1(binop8.left()) + " " + binop8.operator() + " " + this.method1(binop8.right());
      } else if (expr1 instanceof Block) {
         return "";
      } else if (expr1 instanceof Ternary ternary9) {
         return this.method1(ternary9.condition()) + " ? " + this.method1(ternary9.ifTrue()) + " : " + this.method1(ternary9.ifFalse());
      } else if (expr1 instanceof Conditional conditional10) {
         return this.method1(conditional10.condition()) + " ? " + this.method1(conditional10.ifTrue());
      } else if (expr1 instanceof Constant constant11) {
         return constant11.value() + "";
      } else if (expr1 instanceof Group group12) {
         return "(" + this.method1(group12.value()) + ")";
      } else if (expr1 instanceof Negate negate13) {
         return "-" + this.method1(negate13.value());
      } else if (expr1 instanceof Not not14) {
         return "!" + this.method1(not14.value());
      } else if (expr1 instanceof SwitchContext switchcontext15) {
         return this.method1(switchcontext15.left()) + " <> " + this.method1(switchcontext15.right());
      } else if (expr1 instanceof Variable variable16) {
         return variable16.name() == null ? variable16.flavor().name() : variable16.flavor().name() + "." + variable16.name();
      } else {
         return expr1.toString();
      }
   }
}
