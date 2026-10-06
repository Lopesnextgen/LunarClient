package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.mixin.MixinHelper3;
import java.util.Queue;
import java.util.Stack;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;

public class ReturnEmitter implements BytecodeEmitter {
   private final MixinHelper3 field1;

   public ReturnEmitter(MixinHelper3 mixinhelper31) {
      this.field1 = mixinhelper31;
   }

   public void method1(BridgeMethodGenerator bridge_671, Queue<MethodParameter> queue, Stack<MixinHelper3> stack3, InsnList instructions4) {
      if (this.field1.equals(MixinHelper3.field1)) {
         instructions4.add(new InsnNode(177));
      } else {
         bridge_671.method6().method2((MixinHelper3)stack3.pop(), this.field1, instructions4);
         instructions4.add(new InsnNode(this.field1.method2().getOpcode(172)));
      }
   }

   public MixinHelper3 method2() {
      return this.field1;
   }
}
