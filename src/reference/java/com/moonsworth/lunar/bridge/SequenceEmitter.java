package com.moonsworth.lunar.bridge;

import java.util.List;
import java.util.Queue;
import java.util.Stack;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;

public class SequenceEmitter implements BytecodeEmitter {
   private final List<BytecodeEmitter> field1;

   public SequenceEmitter(List<BytecodeEmitter> var1) {
      this.field1 = var1;
   }

   @Override
   public void method1(BridgeMethodGenerator var1, Queue<MethodParameter> queue, Stack<com.moonsworth.lunar.ichor.mixin.MixinHelper3> stack, InsnList list) {
      for (BytecodeEmitter var6 : this.field1) {
         int var7 = stack.size();
         var1.method2(var6);
         if (stack.size() - var7 == 1) {
            int var8 = ((com.moonsworth.lunar.ichor.mixin.MixinHelper3)stack.pop()).method2().getSize();
            list.add(new InsnNode(var8 == 2 ? 88 : 87));
         }
      }
   }

   public List<BytecodeEmitter> method2() {
      return this.field1;
   }
}
