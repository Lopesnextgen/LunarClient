package com.moonsworth.lunar.client.cosmetics.molang;

import com.moonsworth.lunar.client.cosmetics.molang.MolangExprCompiler;
import com.moonsworth.lunar.ichor.util.KeepName;
import java.util.List;
import org.objectweb.asm.Type;

public interface MolangBuiltin extends MolangSymbol {
   boolean method1(int index1);

   default boolean method2(int number1) {
      return false;
   }

   default boolean method3(int number1) {
      return false;
   }

   @KeepName
   default double call(double... items1) {
      throw new UnsupportedOperationException("Not implemented! " + this.getClass().getSimpleName());
   }

   default void method4(List<MolangExprCompiler.MolangValueEmitter> list1, MolangClassBuilder fps82, VariablesMap fps33) {
      if (this.method3(list1.size())) {
         this.method7(list1, fps82, fps33);
      } else if (this.method2(list1.size())) {
         this.method6(list1, fps82, fps33);
      } else {
         this.method5(list1, fps82, fps33);
      }
   }

   default void method5(List<MolangExprCompiler.MolangValueEmitter> list1, MolangClassBuilder fps82, VariablesMap fps33) {
      MolangExprCompiler.method9(this, fps82);
      fps82.method2(arg1x -> {
         MolangExprCompiler.method11(arg1x, list1.size());
         arg1x.visitIntInsn(188, 7);
      });

      for (int index4 = 0; index4 < list1.size(); index4++) {
         int number5 = index4;
         fps82.method2(arg1x -> {
            arg1x.visitInsn(89);
            MolangExprCompiler.method11(arg1x, number5);
         });
         MolangExprCompiler.method8((MolangExprCompiler.MolangValueEmitter)list1.get(index4), fps82, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
         fps82.method2(arg0 -> arg0.visitInsn(82));
      }

      fps82.method2(
         arg1x -> arg1x.visitMethodInsn(
            182, Type.getInternalName(this.getClass()), "call", Type.getMethodDescriptor(Type.DOUBLE_TYPE, new Type[]{Type.getType(double[].class)}), false
         )
      );
   }

   default void method6(List<MolangExprCompiler.MolangValueEmitter> list1, MolangClassBuilder fps82, VariablesMap fps33) {
      MolangExprCompiler.method9(this, fps82);
      Type[] items4 = new Type[list1.size()];

      for (int index5 = 0; index5 < list1.size(); index5++) {
         MolangExprCompiler.method8((MolangExprCompiler.MolangValueEmitter)list1.get(index5), fps82, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
         items4[index5] = Type.DOUBLE_TYPE;
      }

      fps82.method2(arg2x -> arg2x.visitMethodInsn(182, Type.getInternalName(this.getClass()), "call", Type.getMethodDescriptor(Type.DOUBLE_TYPE, items4), false));
   }

   default void method7(List<MolangExprCompiler.MolangValueEmitter> list1, MolangClassBuilder fps82, VariablesMap fps33) {
      Type[] items4 = new Type[list1.size()];

      for (int index5 = 0; index5 < list1.size(); index5++) {
         MolangExprCompiler.method8((MolangExprCompiler.MolangValueEmitter)list1.get(index5), fps82, MolangExprCompiler.MolangValueType.DOUBLE_PRIM);
         items4[index5] = Type.DOUBLE_TYPE;
      }

      fps82.method2(arg2x -> arg2x.visitMethodInsn(184, Type.getInternalName(this.getClass()), "call", Type.getMethodDescriptor(Type.DOUBLE_TYPE, items4), false));
   }
}
