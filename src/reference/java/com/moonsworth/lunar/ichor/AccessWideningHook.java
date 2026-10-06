package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.AsmUtils;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InnerClassNode;
import org.objectweb.asm.tree.MethodNode;

@PipelineHook
public interface AccessWideningHook {
   @TransformClass
   default void method1(ClassNode node1) {
      if (this.method4()) {
         node1.access = AsmUtils.method4(node1.access, true);
         node1.permittedSubclasses = null;

         for (InnerClassNode innerclassnode3 : node1.innerClasses) {
            innerclassnode3.access = AsmUtils.method4(innerclassnode3.access, true);
         }
      }
   }

   @TransformMethod
   default void method2(ClassNode node1, MethodNode method2_) {
      if (this.method4()) {
         boolean flag3 = (method2_.access & 64) != 0;
         boolean flag4 = (node1.access & 4096) != 0 || !flag3 && (method2_.access & 4096) != 0;
         if (!flag4) {
            boolean flag5 = (node1.access & 512) != 0;
            method2_.access = AsmUtils.method4(method2_.access, !flag5 && this.method4());
         }
      }
   }

   @TransformField
   default void method3(ClassNode node1, FieldNode fieldNode) {
      boolean flag3 = (node1.access & 512) != 0;
      fieldNode.access = AsmUtils.method4(fieldNode.access, !flag3 && this.method4());
   }

   default boolean method4() {
      return false;
   }
}
