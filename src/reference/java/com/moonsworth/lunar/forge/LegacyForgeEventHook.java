package com.moonsworth.lunar.forge;

import com.moonsworth.lunar.ichor.TransformClass;
import com.moonsworth.lunar.ichor.IchorStage;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.ichor.util.AsmUtils;
import com.moonsworth.lunar.ichor.util.ClassPrefixFilter;
import com.moonsworth.lunar.loader.PipelineStage;
import java.util.List;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class LegacyForgeEventHook extends ClassPrefixFilter {
   private static final String field2 = "net/minecraftforge/fml/common/eventhandler/Event";

   public LegacyForgeEventHook() {
      super(new String[]{"net/minecraftforge/", "com/replaymod/", "me/Danker/", "com/github/lunatrius/"});
   }

   public IchorStage[] method2() {
      return new IchorStage[]{PipelineStage.POST_FORGE_PATCH};
   }

   @TransformClass
   public void method2(ClassNode node1) {
      String text2 = node1.name;
      if (Thread.currentThread().getContextClassLoader() instanceof URLClassLoader urlclassloader3
         && AsmUtils.method13(text2, "net/minecraftforge/fml/common/eventhandler/Event", urlclassloader3)) {
         this.method4(node1);
      }
   }

   @TransformClass
   public void method3(ClassNode node1) {
      boolean flag2 = false;

      for (MethodNode method4_ : node1.methods) {
         List list5 = method4_.visibleAnnotations;
         if (list5 != null) {
            for (AnnotationNode annotation7 : list5) {
               if (annotation7.desc.equals("Lnet/minecraftforge/fml/common/eventhandler/SubscribeEvent;")) {
                  method4_.access = AsmUtils.method3(method4_.access);
                  flag2 = true;
                  break;
               }
            }
         }
      }

      if (flag2) {
         node1.access = AsmUtils.method4(node1.access, true);
      }
   }

   private boolean method4(ClassNode node1) {
      Type type2 = Type.getType("Lnet/minecraftforge/fml/common/eventhandler/ListenerList;");
      boolean flag3 = false;
      boolean flag4 = false;
      boolean flag5 = false;
      boolean flag6 = false;
      boolean flag7 = false;
      boolean flag8 = false;
      String text9 = Type.getMethodDescriptor(Type.VOID_TYPE, new Type[0]);
      String text10 = Type.getMethodDescriptor(Type.BOOLEAN_TYPE, new Type[0]);
      String text11 = type2.getDescriptor();
      String text12 = Type.getMethodDescriptor(type2, new Type[0]);

      for (MethodNode method14 : node1.methods) {
         if (method14.name.equals("setup") && method14.desc.equals(text9) && (method14.access & 4) == 4) {
            flag4 = true;
         }

         if ((method14.access & 1) == 1) {
            if (method14.name.equals("getListenerList") && method14.desc.equals(text12)) {
               flag5 = true;
            }

            if (method14.name.equals("isCancelable") && method14.desc.equals(text10)) {
               flag7 = true;
            }

            if (method14.name.equals("hasResult") && method14.desc.equals(text10)) {
               flag8 = true;
            }
         }

         if (method14.name.equals("<init>") && method14.desc.equals(text9)) {
            flag6 = true;
         }
      }

      if (node1.visibleAnnotations != null) {
         for (AnnotationNode annotation18 : node1.visibleAnnotations) {
            if (!flag8 && annotation18.desc.equals("Lnet/minecraftforge/fml/common/eventhandler/Event$HasResult;")) {
               MethodNode method22 = new MethodNode(1, "hasResult", text10, null, null);
               method22.instructions.add(new InsnNode(4));
               method22.instructions.add(new InsnNode(172));
               node1.methods.add(method22);
               flag3 = true;
            } else if (!flag7 && annotation18.desc.equals("Lnet/minecraftforge/fml/common/eventhandler/Cancelable;")) {
               MethodNode method15 = new MethodNode(1, "isCancelable", text10, null, null);
               method15.instructions.add(new InsnNode(4));
               method15.instructions.add(new InsnNode(172));
               node1.methods.add(method15);
               flag3 = true;
            }
         }
      }

      if (flag4) {
         return !flag5 ? false : flag3;
      }

      Type type17 = Type.getType("L" + node1.superName + ";");
      node1.fields.add(new FieldNode(10, "LISTENER_LIST", text11, null, null));
      if (!flag6) {
         MethodNode method19 = new MethodNode(1, "<init>", text9, null, null);
         method19.instructions.add(new VarInsnNode(25, 0));
         method19.instructions.add(new MethodInsnNode(183, type17.getInternalName(), "<init>", text9, false));
         method19.instructions.add(new InsnNode(177));
         node1.methods.add(method19);
      }

      MethodNode method20 = new MethodNode(4, "setup", text9, null, null);
      method20.instructions.add(new VarInsnNode(25, 0));
      method20.instructions.add(new MethodInsnNode(183, type17.getInternalName(), "setup", text9, false));
      method20.instructions.add(new FieldInsnNode(178, node1.name, "LISTENER_LIST", text11));
      LabelNode labelnode23 = new LabelNode();
      method20.instructions.add(new JumpInsnNode(198, labelnode23));
      method20.instructions.add(new InsnNode(177));
      method20.instructions.add(labelnode23);
      method20.instructions.add(new FrameNode(3, 0, null, 0, null));
      method20.instructions.add(new TypeInsnNode(187, type2.getInternalName()));
      method20.instructions.add(new InsnNode(89));
      method20.instructions.add(new VarInsnNode(25, 0));
      method20.instructions.add(new MethodInsnNode(183, type17.getInternalName(), "getListenerList", text12, false));
      method20.instructions.add(new MethodInsnNode(183, type2.getInternalName(), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE, new Type[]{type2}), false));
      method20.instructions.add(new FieldInsnNode(179, node1.name, "LISTENER_LIST", text11));
      method20.instructions.add(new InsnNode(177));
      node1.methods.add(method20);
      method20 = new MethodNode(1, "getListenerList", text12, null, null);
      method20.instructions.add(new FieldInsnNode(178, node1.name, "LISTENER_LIST", text11));
      method20.instructions.add(new InsnNode(176));
      node1.methods.add(method20);
      return true;
   }
}
