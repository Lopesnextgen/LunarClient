package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.AsmUtils;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

@PipelineHook
public interface FieldAccessWideningHook {
   @TransformField
   default void method1(ClassNode node1, FieldNode fieldNode) {
      if ((!AsmUtils.method17(node1) || !AsmUtils.isStatic(fieldNode.access))
         && !AsmUtils.method21(fieldNode, "Lorg/spongepowered/asm/mixin/Shadow;")) {
         fieldNode.access = AsmUtils.method3(fieldNode.access);
      }
   }
}
