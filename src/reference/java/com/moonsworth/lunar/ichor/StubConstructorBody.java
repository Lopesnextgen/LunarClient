package com.moonsworth.lunar.ichor;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

class StubConstructorBody extends InsnList {
   StubConstructorBody(StubConstructorInjector ichor2iterator21, ClassNode node2) {
      this.field2 = ichor2iterator21;
      this.field1 = node2;
      this.add(new VarInsnNode(25, 0));
      this.add(new MethodInsnNode(183, this.field1.superName, "<init>", "()V", false));
      this.add(new TypeInsnNode(187, Type.getInternalName(AbstractMethodError.class)));
      this.add(new InsnNode(89));
      this.add(new LdcInsnNode("Stub constructors should not be called."));
      this.add(
         new MethodInsnNode(
            183,
            Type.getInternalName(AbstractMethodError.class),
            "<init>",
            Type.getMethodDescriptor(Type.VOID_TYPE, new Type[]{Type.getType(String.class)}),
            false
         )
      );
      this.add(new InsnNode(191));
   }
}
