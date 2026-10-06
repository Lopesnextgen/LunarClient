package com.moonsworth.lunar.bridge;

import java.util.Queue;
import java.util.Stack;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;

public class MethodReturnEmitter implements BytecodeEmitter {
   private final com.moonsworth.lunar.ichor.mixin.MixinHelper3 field1;

   public MethodReturnEmitter(com.moonsworth.lunar.ichor.mixin.MixinHelper3 var1) {
      this.field1 = var1;
   }

   @Override
   public void method1(BridgeMethodGenerator var1, Queue<MethodParameter> queue, Stack<com.moonsworth.lunar.ichor.mixin.MixinHelper3> stack, InsnList list) {
      if (this.field1.equals(com.moonsworth.lunar.ichor.mixin.MixinHelper3.field1)) {
         list.add(new InsnNode(177));
      } else {
         var1.method6().method2((com.moonsworth.lunar.ichor.mixin.MixinHelper3)stack.pop(), this.field1, list);
         list.add(new InsnNode(this.field1.method2().getOpcode(172)));
      }
   }

   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method2() {
      return this.field1;
   }
}
