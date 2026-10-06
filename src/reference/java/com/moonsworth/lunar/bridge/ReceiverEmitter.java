package com.moonsworth.lunar.bridge;

import java.util.Queue;
import java.util.Stack;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.VarInsnNode;

public class ReceiverEmitter implements BytecodeEmitter {
   @Override
   public void method1(BridgeMethodGenerator var1, Queue<MethodParameter> var2, Stack<com.moonsworth.lunar.ichor.mixin.MixinHelper3> var3, InsnList var4) {
      var4.add(new VarInsnNode(25, 0));
      var3.push(new com.moonsworth.lunar.ichor.mixin.MixinHelper3(new com.moonsworth.lunar.ichor.mixin.MixinHelper7(var1.getTargetClassNode().name)));
   }
}
