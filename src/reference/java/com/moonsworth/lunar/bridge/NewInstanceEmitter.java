package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.mixin.ClassTypeSignature;
import java.util.Queue;
import java.util.Stack;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.TypeInsnNode;

public class NewInstanceEmitter implements BytecodeEmitter {
   private final com.moonsworth.lunar.ichor.mixin.MixinHelper3 field1;

   public NewInstanceEmitter(com.moonsworth.lunar.ichor.mixin.MixinHelper3 mixinhelper31) {
      this.field1 = mixinhelper31;
   }

   public void method1(BridgeMethodGenerator bridge_671, Queue<MethodParameter> queue, Stack<com.moonsworth.lunar.ichor.mixin.MixinHelper3> stack3, InsnList instructions4) {
      instructions4.add(new TypeInsnNode(187, ((ClassTypeSignature)this.field1.method2()).name()));
      stack3.push(this.field1);
      instructions4.add(new InsnNode(89));
      stack3.push(this.field1);
   }

   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method2() {
      return this.field1;
   }
}
