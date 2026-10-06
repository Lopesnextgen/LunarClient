package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import com.moonsworth.lunar.loader.Ichor4Type;
import org.objectweb.asm.Handle;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InvokeDynamicInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class Ichor2Iterator3 implements IchorInjection {
   private final String field1 = FatalIchorError6.class.getName().replace('.', '/');

   @Override
   public Ichor4[] method2() {
      return new Ichor4Type[]{Ichor4Type.POST_REMAP};
   }

   @Annotation
   public void method2(ClassNode type, MethodNode methodNode) {
      for (AbstractInsnNode var4 : methodNode.instructions) {
         if (var4 instanceof MethodInsnNode var10) {
            if (var10.getOpcode() == 182 && var10.owner.equals("java/lang/Class") && var10.name.equals("isRecord") && var10.desc.equals("()Z")) {
               var10.setOpcode(184);
               var10.owner = this.field1;
               var10.name = "isRecordLazy";
               var10.desc = "(Ljava/lang/Class;)Z";
            }
         } else if (var4 instanceof InvokeDynamicInsnNode var5) {
            int var6 = 0;

            for (int var7 = var5.bsmArgs.length; var6 < var7; var6++) {
               if (var5.bsmArgs[var6] instanceof Handle var9
                  && var9.getTag() == 5
                  && var9.getOwner().equals("java/lang/Class")
                  && var9.getName().equals("isRecord")
                  && var9.getDesc().equals("()Z")) {
                  var5.bsmArgs[var6] = new Handle(6, this.field1, "isRecordLazy", "(Ljava/lang/Class;)Z", false);
               }
            }
         }
      }
   }
}
