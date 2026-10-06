package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.mixin.MethodSignature;
import com.moonsworth.lunar.ichor.mixin.MixinHelper3;
import com.moonsworth.lunar.ichor.mixin.GenericSignatureParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

public class InvocationEmitter implements BytecodeEmitter {
   private final Bridge3_5 field1;

   public InvocationEmitter(Bridge3_5 bridge3_51) {
      this.field1 = bridge3_51;
   }

   public void method1(BridgeMethodGenerator bridge_671, Queue<MethodParameter> queue, Stack<MixinHelper3> stack3, InsnList instructions4) {
      MethodNode method5_ = this.field1.method2();
      MethodSignature mixinhelper26 = GenericSignatureParser.method1(Objects.requireNonNullElse(method5_.signature, method5_.desc), bridge_671.getTargetClassNode().signature, null);
      List list7 = mixinhelper26.method1().stream().map(arg0 -> Collections.emptyList()).toList();
      if (method5_.visibleParameterAnnotations != null) {
         list7 = Arrays.asList(method5_.visibleParameterAnnotations);
      }

      int index8 = 0;
      int number9 = this.field1.isStatic() ? 0 : 1;

      for (MixinHelper3 mixinhelper311 : mixinhelper26.method1()) {
         queue.add(new MethodParameter(mixinhelper311, number9, (List<AnnotationNode>)list7.get(index8)));
         index8++;
         number9 += mixinhelper311.method2().getSize();
      }

      ArrayList list16 = new ArrayList();

      for (Bridge6_6 bridge6_614 : Objects.requireNonNull(this.field1.method1().method3(bridge_671.method4()))) {
         List list15 = bridge6_614.method1(bridge_671, bridge_671.getTargetClassNode());
         list16.add(new ChainEmitter(list15, mixinhelper26.method2().equals(MixinHelper3.field1)));
      }

      if (!mixinhelper26.method2().equals(MixinHelper3.field1)) {
         bridge_671.method2(new GroupingEmitter(mixinhelper26.method2(), list16, bridge_671.method5()));
      } else {
         bridge_671.method2(new SequenceEmitter(list16));
      }

      bridge_671.method2(new ReturnEmitter(mixinhelper26.method2()));
      if (!stack3.isEmpty()) {
         throw new IllegalStateException("Lingering stack elements: " + stack3);
      }
   }

   public Bridge3_5 method2() {
      return this.field1;
   }
}
