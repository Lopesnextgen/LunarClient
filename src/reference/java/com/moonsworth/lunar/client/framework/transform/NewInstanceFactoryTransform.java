package com.moonsworth.lunar.client.framework.transform;

import com.moonsworth.lunar.ichor.TransformClass;
import com.moonsworth.lunar.ichor.IchorStage;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.ichor.util.AsmUtils;
import com.moonsworth.lunar.loader.PipelineStage;
import java.util.ArrayList;
import java.util.HashSet;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class NewInstanceFactoryTransform extends com.moonsworth.lunar.ichor.util.ClassNameRegexFilter {
   public NewInstanceFactoryTransform() {
      super("net.minecraft.*|com.mojang.*|[^\\/]+");
   }

   public IchorStage[] method2() {
      return new IchorStage[]{PipelineStage.PRE_META_MIXIN};
   }

   @TransformClass
   public void method2(ClassNode node1, URLClassLoader urlclassloader2) {
      if (!AsmUtils.method34(node1) && (node1.access & 1024) == 0 && !AsmUtils.method17(node1)) {
         HashSet set3 = new HashSet();
         ArrayList list4 = new ArrayList();

         for (MethodNode method6 : node1.methods) {
            if (method6.name.equals("newInstance")) {
               set3.add(this.method4(method6.desc));
            } else if (method6.name.equals("<init>")) {
               list4.add(method6);
            }
         }

         for (MethodNode method8 : list4) {
            if (!set3.contains(this.method4(method8.desc))) {
               node1.methods.add(this.method3(method8, node1));
            }
         }
      }
   }

   private MethodNode method3(final MethodNode method1, final ClassNode node2) {
      final Type[] items3 = Type.getArgumentTypes(method1.desc);
      MethodNode method4_ = new MethodNode(9, "newInstance", Type.getMethodDescriptor(Type.getObjectType(node2.name), items3), null, null);
      method4_.instructions = new InsnList() {
         {
            this.add(new TypeInsnNode(187, node2.name));
            this.add(new InsnNode(89));
            int number5 = 0;

            for (Type type9 : items3) {
               this.add(new VarInsnNode(type9.getOpcode(21), number5));
               number5 += type9.getSize();
            }

            this.add(new MethodInsnNode(183, node2.name, "<init>", method1.desc, false));
            this.add(new InsnNode(176));
         }
      };
      return method4_;
   }

   private String method4(String text1) {
      return text1.substring(0, text1.lastIndexOf(41) + 1);
   }
}
