package com.moonsworth.lunar.client.cosmetics.molang;

import com.eliotlash.molang.ast.Operator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MolangJitSignature {
   private final List<MolangJitSignature.MolangSigNode> field1 = new ArrayList<>();

   public MolangJitSignature() {
   }

   public void method1(MolangJitSignature.MolangSigNode extension1) {
      this.field1.add(extension1);
   }

   @Override
   public boolean equals(Object object) {
      if (object != null && this.getClass() == object.getClass()) {
         MolangJitSignature fps42 = (MolangJitSignature)object;
         return Objects.equals(this.field1, fps42.field1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(this.field1);
   }

   public class MolangSigWildcard implements MolangJitSignature.MolangSigNode {
   }

   public class MolangSigGroup implements MolangJitSignature.MolangSigNode {
   }

   public class MolangSigBlockEnd implements MolangJitSignature.MolangSigNode {
   }

   public class MolangSigIf implements MolangJitSignature.MolangSigNode {
   }

   public class MolangSigFunctionCall implements MolangJitSignature.MolangSigNode {
      private final Class<? extends MolangBuiltin> field1;

      public MolangSigFunctionCall(Class<? extends MolangBuiltin> clazz1) {
         this.field1 = clazz1;
      }

      public Class<? extends MolangBuiltin> method1() {
         return this.field1;
      }
   }

   public class MolangSigConstant implements MolangJitSignature.MolangSigNode {
      private final double field1;

      public MolangSigConstant(double value) {
         this.field1 = value;
      }

      public double value() {
         return this.field1;
      }
   }

   public class MolangSigBlockStart implements MolangJitSignature.MolangSigNode {
   }

   public class MolangSigNegate implements MolangJitSignature.MolangSigNode {
   }

   public class MolangSigExpressionStatement implements MolangJitSignature.MolangSigNode {
   }

   public class MolangSigVariableRead implements MolangJitSignature.MolangSigNode {
   }

   public class MolangSigAccessRead implements MolangJitSignature.MolangSigNode {
      private final int field1;

      public MolangSigAccessRead(int number1) {
         this.field1 = number1;
      }

      public int method1() {
         return this.field1;
      }
   }

   public class MolangSigLoop implements MolangJitSignature.MolangSigNode {
   }

   public class MolangSigAccessWrite implements MolangJitSignature.MolangSigNode {
      private final int field1;

      public MolangSigAccessWrite(int number1) {
         this.field1 = number1;
      }

      public int method1() {
         return this.field1;
      }
   }

   public class MolangSigElseIf implements MolangJitSignature.MolangSigNode {
   }

   public class MolangSigBinaryOperator implements MolangJitSignature.MolangSigNode {
      private final Operator field1;

      public MolangSigBinaryOperator(Operator operator1) {
         this.field1 = operator1;
      }

      public Operator operator() {
         return this.field1;
      }
   }

   public class MolangSigNot implements MolangJitSignature.MolangSigNode {
   }

   public class MolangSigReturn implements MolangJitSignature.MolangSigNode {
   }

   public class MolangSigTernary implements MolangJitSignature.MolangSigNode {
   }

   public interface MolangSigNode {
   }
}
