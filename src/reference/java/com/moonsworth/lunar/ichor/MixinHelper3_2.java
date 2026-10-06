package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

@Annotation3
public interface MixinHelper3_2 {
   @Annotation11
   default void method1(ClassNode type, FieldNode fieldNode) {
      if ((!FatalIchorError6.method17(type) || !FatalIchorError6.isStatic(fieldNode.access))
         && !FatalIchorError6.method21(fieldNode, "Lorg/spongepowered/asm/mixin/Shadow;")) {
         fieldNode.access = FatalIchorError6.method3(fieldNode.access);
      }
   }
}
