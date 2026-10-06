package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.mixin.MixinHelper3;
import com.moonsworth.lunar.ichor.mixin.PrimitiveTypeSignature;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;

public class ParameterConversionEmitter implements BytecodeEmitter {
   private static final String field1 = Type.getDescriptor(BridgeParameterTarget.class);

   public ParameterConversionEmitter() {
   }

   public void method1(BridgeMethodGenerator bridge_671, Queue<MethodParameter> queue, Stack<MixinHelper3> stack3, InsnList instructions4) {
      MethodParameter bridge35 = (MethodParameter)queue.remove();
      instructions4.add(bridge35.load());
      if (bridge35.getAnnotations().containsKey(field1)) {
         if (bridge35.method1().method2() instanceof PrimitiveTypeSignature) {
            throw new UnsupportedOperationException("@Default is not applicable to primitive type " + bridge35.method1());
         }

         Bridge4_2 bridge4_26 = Bridge4_2.method1((AnnotationNode)bridge35.getAnnotations().get(field1));
         Bridge6_6[] items7 = Objects.requireNonNull(bridge4_26.method3(bridge_671.method4()));
         if (items7.length != 1) {
            throw new IllegalStateException("Expected 1 lens but got " + items7.length);
         }

         Bridge6_6 bridge6_68 = items7[0];
         List list9 = bridge6_68.method1(bridge_671, bridge_671.getTargetClassNode());
         LabelNode labelnode10 = new LabelNode();
         LabelNode labelnode11 = new LabelNode();
         instructions4.add(new JumpInsnNode(199, labelnode10));
         bridge_671.method2(new ChainEmitter(list9, false));
         bridge_671.method6().method2((MixinHelper3)stack3.pop(), bridge35.method1(), instructions4);
         instructions4.add(new JumpInsnNode(167, labelnode11));
         instructions4.add(labelnode10);
         instructions4.add(bridge35.load());
         instructions4.add(labelnode11);
      }

      stack3.push(bridge35.method1());
   }
}
