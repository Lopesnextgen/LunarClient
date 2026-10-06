package com.moonsworth.lunar.bridge;

import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;

public class ParameterDefaultEmitter implements BytecodeEmitter {
   private static final String field1 = Type.getDescriptor(BridgeParameterMapping.class);

   @Override
   public void method1(BridgeMethodGenerator bridgeMethodGenerator, Queue<MethodParameter> queue, Stack<com.moonsworth.lunar.ichor.mixin.MixinHelper3> stack, InsnList list) {
      MethodParameter var5 = (MethodParameter)queue.remove();
      list.add(var5.load());
      if (var5.getAnnotations().containsKey(field1)) {
         if (var5.method1().method2() instanceof com.moonsworth.lunar.ichor.mixin.MixinHelper5) {
            throw new UnsupportedOperationException("@Default is not applicable to primitive type " + var5.method1());
         }

         Bridge4_2 var6 = Bridge4_2.method1(var5.getAnnotations().get(field1));
         Bridge6_6[] var7 = Objects.requireNonNull(var6.method3(bridgeMethodGenerator.method4()));
         if (var7.length != 1) {
            throw new IllegalStateException("Expected 1 lens but got " + var7.length);
         }

         Bridge6_6 var8 = var7[0];
         List var9 = var8.method1(bridgeMethodGenerator, bridgeMethodGenerator.getTargetClassNode());
         LabelNode var10 = new LabelNode();
         LabelNode var11 = new LabelNode();
         list.add(new JumpInsnNode(199, var10));
         bridgeMethodGenerator.method2(new ChainEmitter(var9, false));
         bridgeMethodGenerator.method6().method2((com.moonsworth.lunar.ichor.mixin.MixinHelper3)stack.pop(), var5.method1(), list);
         list.add(new JumpInsnNode(167, var11));
         list.add(var10);
         list.add(var5.load());
         list.add(var11);
      }

      stack.push(var5.method1());
   }
}
