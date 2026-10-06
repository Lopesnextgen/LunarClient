package com.moonsworth.lunar.client.replay.replaymod.forge.ichor;

import com.moonsworth.lunar.ichor.TransformMethod;
import com.moonsworth.lunar.ichor.TransformClass;
import com.moonsworth.lunar.ichor.IchorStage;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.loader.PipelineStage;
import java.util.ArrayList;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class Ichor2Handler2 extends com.moonsworth.lunar.ichor.util.ClassNameRegexFilter {
   public Ichor2Handler2() {
      super("com.replaymod.core.ReplayModMixinConfigPlugin|com.replaymod.core.ReplayModNonMMLauncher");
   }

   public IchorStage[] method2() {
      return new IchorStage[]{PipelineStage.INIT};
   }

   @TransformClass
   public void method2(ClassNode node1, URLClassLoader urlclassloader2) {
      node1.fields.removeIf(arg0 -> arg0.name.equals("logger"));
   }

   @TransformMethod("<init>")
   public void method3(ClassNode node1, MethodNode method2_) {
      int number3 = method2_.instructions.size();
      boolean flag4 = false;
      ArrayList list5 = new ArrayList(16);

      for (AbstractInsnNode instruction7 : method2_.instructions) {
         if (instruction7 instanceof FieldInsnNode fieldinsnnode8 && instruction7.getOpcode() == 180 && fieldinsnnode8.name.equals("logger")) {
            flag4 = true;
         }

         if (instruction7 instanceof MethodInsnNode methodinsnnode10 && instruction7.getOpcode() == 184 && methodinsnnode10.name.equals("getLogger")) {
            flag4 = true;
         }

         if (flag4) {
            list5.add(instruction7);
         }

         if (instruction7 instanceof MethodInsnNode methodinsnnode11 && instruction7.getOpcode() == 185 && methodinsnnode11.name.equals("debug")) {
            flag4 = false;
         }

         if (instruction7 instanceof FieldInsnNode fieldinsnnode12 && instruction7.getOpcode() == 181 && fieldinsnnode12.name.equals("logger")) {
            flag4 = false;
         }
      }

      list5.forEach(method2_.instructions::remove);
      int number9 = method2_.instructions.size();
      if (number3 == number9) {
         throw new IllegalStateException("Failed to remove logger usage from constructor: " + node1.name + " " + method2_.name + method2_.desc);
      }
   }
}
