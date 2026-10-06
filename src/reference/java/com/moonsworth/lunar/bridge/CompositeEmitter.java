package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.mixin.MixinHelper3;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;

public class CompositeEmitter implements BytecodeEmitter {
   private final List<BytecodeEmitter> field1;

   public CompositeEmitter(List<BytecodeEmitter> list) {
      this.field1 = list;
   }

   public void method1(BridgeMethodGenerator bridge_671, Queue<MethodParameter> queue, Stack<MixinHelper3> stack3, InsnList instructions4) {
      for (BytecodeEmitter bridge_156 : this.field1) {
         int number7 = stack3.size();
         bridge_671.method2(bridge_156);
         if (stack3.size() - number7 == 1) {
            int number8 = ((MixinHelper3)stack3.pop()).method2().getSize();
            instructions4.add(new InsnNode(number8 == 2 ? 88 : 87));
         }
      }
   }

   public List<BytecodeEmitter> method2() {
      return this.field1;
   }
}
