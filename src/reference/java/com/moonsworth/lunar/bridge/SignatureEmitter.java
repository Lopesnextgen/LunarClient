package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.mixin.SignatureVisitorImpl;
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

public class SignatureEmitter implements BytecodeEmitter {
   private final Bridge3_5 field1;

   public SignatureEmitter(Bridge3_5 var1) {
      this.field1 = var1;
   }

   @Override
   public void method1(BridgeMethodGenerator var1, Queue<MethodParameter> queue, Stack<com.moonsworth.lunar.ichor.mixin.MixinHelper3> stack, InsnList list) {
      MethodNode var5 = this.field1.method2();
      com.moonsworth.lunar.ichor.mixin.MixinHelper2 var6 = SignatureVisitorImpl.method1(
         Objects.requireNonNullElse(var5.signature, var5.desc), var1.getTargetClassNode().signature, null
      );
      List var7 = var6.method1().stream().map(var0 -> Collections.emptyList()).toList();
      if (var5.visibleParameterAnnotations != null) {
         var7 = Arrays.asList(var5.visibleParameterAnnotations);
      }

      int var8 = 0;
      int var9 = this.field1.isStatic() ? 0 : 1;

      for (com.moonsworth.lunar.ichor.mixin.MixinHelper3 var11 : var6.method1()) {
         queue.add(new MethodParameter(var11, var9, (List<AnnotationNode>)var7.get(var8)));
         var8++;
         var9 += var11.method2().getSize();
      }

      ArrayList var16 = new ArrayList();

      for (Bridge6_6 var14 : Objects.requireNonNull(this.field1.method1().method3(var1.method4()))) {
         List var15 = var14.method1(var1, var1.getTargetClassNode());
         var16.add(new ChainEmitter(var15, var6.method2().equals(com.moonsworth.lunar.ichor.mixin.MixinHelper3.field1)));
      }

      if (!var6.method2().equals(com.moonsworth.lunar.ichor.mixin.MixinHelper3.field1)) {
         var1.method2(new GroupingEmitter(var6.method2(), var16, var1.method5()));
      } else {
         var1.method2(new SequenceEmitter(var16));
      }

      var1.method2(new MethodReturnEmitter(var6.method2()));
      if (!stack.isEmpty()) {
         throw new IllegalStateException("Lingering stack elements: " + stack);
      }
   }

   public Bridge3_5 method2() {
      return this.field1;
   }
}
