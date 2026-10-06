package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.AsmUtils;
import com.moonsworth.lunar.loader.PipelineStage;
import org.objectweb.asm.Handle;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InvokeDynamicInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class IsRecordRewriter implements IchorInjection {
   private final String field1 = AsmUtils.class.getName().replace('.', '/');

   public IsRecordRewriter() {
   }

   public IchorStage[] method2() {
      return new PipelineStage[]{PipelineStage.POST_REMAP};
   }

   @TransformMethod
   public void method2(ClassNode node1, MethodNode method2_) {
      for (AbstractInsnNode instruction4 : method2_.instructions) {
         if (instruction4 instanceof MethodInsnNode methodinsnnode10) {
            if (methodinsnnode10.getOpcode() == 182 && methodinsnnode10.owner.equals("java/lang/Class") && methodinsnnode10.name.equals("isRecord") && methodinsnnode10.desc.equals("()Z")) {
               methodinsnnode10.setOpcode(184);
               methodinsnnode10.owner = this.field1;
               methodinsnnode10.name = "isRecordLazy";
               methodinsnnode10.desc = "(Ljava/lang/Class;)Z";
            }
         } else if (instruction4 instanceof InvokeDynamicInsnNode invokedynamicinsnnode5) {
            int index6 = 0;

            for (int index7 = invokedynamicinsnnode5.bsmArgs.length; index6 < index7; index6++) {
               if (invokedynamicinsnnode5.bsmArgs[index6] instanceof Handle handle9
                  && handle9.getTag() == 5
                  && handle9.getOwner().equals("java/lang/Class")
                  && handle9.getName().equals("isRecord")
                  && handle9.getDesc().equals("()Z")) {
                  invokedynamicinsnnode5.bsmArgs[index6] = new Handle(6, this.field1, "isRecordLazy", "(Ljava/lang/Class;)Z", false);
               }
            }
         }
      }
   }
}
