package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.AsmUtils;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

@PipelineHook
public interface ExternalAccessWideningHook {
   @TransformMethod
   default void method1(ClassNode node1, MethodNode method2_) {
      if (node1.superName != null) {
         if (!node1.superName.contains("/")
            || node1.superName.startsWith("net/minecraft")
            || node1.superName.startsWith("com/mojang")
            || node1.superName.startsWith("net/optifine")) {
            method2_.access = AsmUtils.method3(method2_.access);
         }
      }
   }

   @TransformField
   default void method2(ClassNode node1, FieldNode fieldNode) {
      if (node1.superName != null) {
         if (!node1.superName.contains("/")
            || node1.superName.startsWith("net/minecraft")
            || node1.superName.startsWith("com/mojang")
            || node1.superName.startsWith("net/optifine")) {
            fieldNode.access = AsmUtils.method3(fieldNode.access);
         }
      }
   }
}
