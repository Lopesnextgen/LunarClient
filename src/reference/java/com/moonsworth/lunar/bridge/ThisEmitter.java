package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.mixin.MixinHelper3;
import com.moonsworth.lunar.ichor.mixin.ClassTypeSignature;
import java.util.Queue;
import java.util.Stack;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.VarInsnNode;

public class ThisEmitter implements BytecodeEmitter {
   public ThisEmitter() {
   }

   public void method1(BridgeMethodGenerator bridge_671, Queue<MethodParameter> queue, Stack<MixinHelper3> stack3, InsnList instructions4) {
      instructions4.add(new VarInsnNode(25, 0));
      stack3.push(new MixinHelper3(new ClassTypeSignature(bridge_671.getTargetClassNode().name)));
   }
}
