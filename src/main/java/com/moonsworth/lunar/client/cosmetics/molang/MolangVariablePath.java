package com.moonsworth.lunar.client.cosmetics.molang;

import com.eliotlash.molang.ast.Accessible;
import com.eliotlash.molang.ast.Expr.Access;
import com.eliotlash.molang.ast.Expr.Call;
import com.eliotlash.molang.ast.Expr.Variable;
import com.eliotlash.molang.functions.FunctionDefinition;
import com.moonsworth.lunar.ichor.util.KeepName;
import java.util.ArrayList;

public class MolangVariablePath {
   public MolangVariablePath() {
   }

   public static String method1(Access access0) {
      return method5(access0.target(), access0.member());
   }

   public static String method2(Call call0) {
      return method5(call0.target(), call0.member());
   }

   public static String method3(FunctionDefinition functiondefinition0) {
      return method5(functiondefinition0.target(), functiondefinition0.member());
   }

   public static String method4(Variable variable0) {
      return method5(variable0, null);
   }

   public static String method5(Accessible accessible0, String text) {
      if (accessible0 instanceof Variable variable2) {
         ArrayList list3 = new ArrayList();
         if (variable2.flavor() != null) {
            list3.add(variable2.flavor().name);
         }

         if (variable2.name() != null) {
            list3.add(variable2.name().toLowerCase());
         }

         if (text != null) {
            list3.add(text.toLowerCase());
         }

         return String.join(".", list3);
      } else {
         throw new IllegalArgumentException("Not a variable access");
      }
   }

   @KeepName
   public static boolean epsilonEquals(double value0, double value2) {
      return epsilonEquals(value0, value2, 1.0E-5);
   }

   @KeepName
   public static boolean epsilonEquals(double value0, double value2, double value) {
      return Math.abs(value0 - value2) < value;
   }
}
