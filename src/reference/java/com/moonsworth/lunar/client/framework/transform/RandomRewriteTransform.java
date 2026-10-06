package com.moonsworth.lunar.client.framework.transform;

import com.moonsworth.lunar.client.util.math.RandomImpl;
import com.moonsworth.lunar.ichor.TransformMethod;
import com.moonsworth.lunar.ichor.IchorStage;
import com.moonsworth.lunar.loader.PipelineStage;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;

public class RandomRewriteTransform extends com.moonsworth.lunar.ichor.util.ClassPrefixFilter {
   private static final String field2 = Type.getInternalName(RandomImpl.class);

   public RandomRewriteTransform() {
      super(new String[]{"net/minecraft/client/"});
   }

   public IchorStage[] method2() {
      return new IchorStage[]{PipelineStage.FINAL};
   }

   @TransformMethod
   public void method2(ClassNode node1, MethodNode method2_) {
      InsnList instructions3 = method2_.instructions;

      for (AbstractInsnNode instruction5 : instructions3) {
         method3(instructions3, instruction5);
         method4(instructions3, instruction5);
      }
   }

   private static void method3(InsnList instructions0, AbstractInsnNode instruction1) {
      if (instruction1 instanceof MethodInsnNode methodinsnnode2 && methodinsnnode2.owner.equals("java/lang/Math") && methodinsnnode2.name.equals("random") && methodinsnnode2.desc.equals("()D")) {
         MethodInsnNode methodinsnnode3 = new MethodInsnNode(184, "java/util/concurrent/ThreadLocalRandom", "current", "()Ljava/util/concurrent/ThreadLocalRandom;");
         instructions0.insertBefore(methodinsnnode2, methodinsnnode3);
         methodinsnnode2.setOpcode(182);
         methodinsnnode2.owner = "java/util/concurrent/ThreadLocalRandom";
         methodinsnnode2.name = "nextDouble";
      }
   }

   private static void method4(InsnList instructions0, AbstractInsnNode instruction1) {
      if (instruction1 instanceof TypeInsnNode typeinsnnode2
         && typeinsnnode2.getOpcode() == 187
         && typeinsnnode2.desc.equals("java/util/Random")
         && instructions0.get(instructions0.indexOf(instruction1) + 2) instanceof MethodInsnNode methodinsnnode4
         && method5(methodinsnnode4)) {
         typeinsnnode2.desc = field2;
         methodinsnnode4.owner = field2;
      }
   }

   private static boolean method5(MethodInsnNode methodinsnnode0) {
      return methodinsnnode0.getOpcode() == 183 && methodinsnnode0.owner.equals("java/util/Random") && methodinsnnode0.name.equals("<init>") && methodinsnnode0.desc.equals("()V");
   }
}
