package com.moonsworth.lunar.bridge;

import java.util.Queue;
import java.util.Stack;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.TypeInsnNode;

public class ObjectAllocationEmitter implements BytecodeEmitter {
   private final com.moonsworth.lunar.ichor.mixin.MixinHelper3 field1;

   public ObjectAllocationEmitter(com.moonsworth.lunar.ichor.mixin.MixinHelper3 var1) {
      this.field1 = var1;
   }

   @Override
   public void method1(BridgeMethodGenerator var1, Queue<MethodParameter> queue, Stack<com.moonsworth.lunar.ichor.mixin.MixinHelper3> stack, InsnList list) {
      list.add(new TypeInsnNode(187, ((com.moonsworth.lunar.ichor.mixin.MixinHelper7)this.field1.method2()).name()));
      stack.push(this.field1);
      list.add(new InsnNode(89));
      stack.push(this.field1);
   }

   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method2() {
      return this.field1;
   }
}
