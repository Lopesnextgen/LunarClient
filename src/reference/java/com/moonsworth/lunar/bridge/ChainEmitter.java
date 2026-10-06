package com.moonsworth.lunar.bridge;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import org.objectweb.asm.tree.InsnList;

public class ChainEmitter implements BytecodeEmitter {
   private final List<TargetPathMember> path;
   private final boolean field1;

   public ChainEmitter(List<TargetPathMember> var1, boolean var2) {
      this.path = var1;
      this.field1 = var2;
   }

   @Override
   public void method1(BridgeMethodGenerator var1, Queue<MethodParameter> var2, Stack<com.moonsworth.lunar.ichor.mixin.MixinHelper3> var3, InsnList var4) {
      TargetPathMember var5 = this.path.get(this.path.size() - 1);
      BytecodeEmitter var6 = new ReceiverEmitter();

      for (TargetPathMember var8 : this.path) {
         if (var8.method5() != null) {
            ArrayList var9 = new ArrayList();

            for (com.moonsworth.lunar.ichor.mixin.MixinHelper3 var11 : var8.method6().method1()) {
               var9.add(new ParameterDefaultEmitter());
            }

            var6 = new BridgeIterator(
               var8.method3(),
               var8.method5(),
               var8.method6(),
               var8.isInterface(),
               var8.method5().name.equals("<init>") ? new ObjectAllocationEmitter(var8.method3()) : var6,
               var9
            );
         } else {
            if (var8.method4() == null) {
               throw new UnsupportedOperationException("Invalid target node " + var8);
            }

            if (var8 == var5 && this.field1) {
               var6 = new FieldWriteEmitter(var8.method3(), var8.method4(), var8.method1(), var6, new ParameterDefaultEmitter());
            } else {
               var6 = new FieldReadEmitter(var8.method3(), var8.method4(), var8.method1(), var6);
            }
         }
      }

      var1.method2(var6);
   }

   public List<TargetPathMember> getPath() {
      return this.path;
   }

   public boolean method3() {
      return this.field1;
   }
}
