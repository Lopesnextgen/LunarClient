package com.moonsworth.lunar.forge;

import com.moonsworth.lunar.ichor.Annotation7;
import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import com.moonsworth.lunar.loader.Ichor4Type;
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

public class Ichor2Iterator extends com.moonsworth.lunar.ichor.util.Ichor2Handler {
   private static final String field2 = "net/minecraftforge/fml/common/eventhandler/Event";

   public Ichor2Iterator() {
      super("net/minecraftforge/", "com/replaymod/", "me/Danker/", "com/github/lunatrius/");
   }

   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.POST_FORGE_PATCH};
   }

   @Annotation7
   public void method2(ClassNode var1) {
      String var2 = var1.name;
      if (Thread.currentThread().getContextClassLoader() instanceof URLClassLoader var3
         && FatalIchorError6.method13(var2, "net/minecraftforge/fml/common/eventhandler/Event", var3)) {
         this.method4(var1);
      }
   }

   @Annotation7
   public void method3(ClassNode var1) {
      boolean var2 = false;

      for (MethodNode var4 : var1.methods) {
         List var5 = var4.visibleAnnotations;
         if (var5 != null) {
            for (AnnotationNode var7 : var5) {
               if (var7.desc.equals("Lnet/minecraftforge/fml/common/eventhandler/SubscribeEvent;")) {
                  var4.access = FatalIchorError6.method3(var4.access);
                  var2 = true;
                  break;
               }
            }
         }
      }

      if (var2) {
         var1.access = FatalIchorError6.method4(var1.access, true);
      }
   }

   private boolean method4(ClassNode var1) {
      Type var2 = Type.getType("Lnet/minecraftforge/fml/common/eventhandler/ListenerList;");
      boolean var3 = false;
      boolean var4 = false;
      boolean var5 = false;
      boolean var6 = false;
      boolean var7 = false;
      boolean var8 = false;
      String var9 = Type.getMethodDescriptor(Type.VOID_TYPE, new Type[0]);
      String var10 = Type.getMethodDescriptor(Type.BOOLEAN_TYPE, new Type[0]);
      String var11 = var2.getDescriptor();
      String var12 = Type.getMethodDescriptor(var2, new Type[0]);

      for (MethodNode var14 : var1.methods) {
         if (var14.name.equals("setup") && var14.desc.equals(var9) && (var14.access & 4) == 4) {
            var4 = true;
         }

         if ((var14.access & 1) == 1) {
            if (var14.name.equals("getListenerList") && var14.desc.equals(var12)) {
               var5 = true;
            }

            if (var14.name.equals("isCancelable") && var14.desc.equals(var10)) {
               var7 = true;
            }

            if (var14.name.equals("hasResult") && var14.desc.equals(var10)) {
               var8 = true;
            }
         }

         if (var14.name.equals("<init>") && var14.desc.equals(var9)) {
            var6 = true;
         }
      }

      if (var1.visibleAnnotations != null) {
         for (AnnotationNode var18 : var1.visibleAnnotations) {
            if (!var8 && var18.desc.equals("Lnet/minecraftforge/fml/common/eventhandler/Event$HasResult;")) {
               MethodNode var22 = new MethodNode(1, "hasResult", var10, null, null);
               var22.instructions.add(new InsnNode(4));
               var22.instructions.add(new InsnNode(172));
               var1.methods.add(var22);
               var3 = true;
            } else if (!var7 && var18.desc.equals("Lnet/minecraftforge/fml/common/eventhandler/Cancelable;")) {
               MethodNode var15 = new MethodNode(1, "isCancelable", var10, null, null);
               var15.instructions.add(new InsnNode(4));
               var15.instructions.add(new InsnNode(172));
               var1.methods.add(var15);
               var3 = true;
            }
         }
      }

      if (var4) {
         return !var5 ? false : var3;
      }

      Type var17 = Type.getType("L" + var1.superName + ";");
      var1.fields.add(new FieldNode(10, "LISTENER_LIST", var11, null, null));
      if (!var6) {
         MethodNode var19 = new MethodNode(1, "<init>", var9, null, null);
         var19.instructions.add(new VarInsnNode(25, 0));
         var19.instructions.add(new MethodInsnNode(183, var17.getInternalName(), "<init>", var9, false));
         var19.instructions.add(new InsnNode(177));
         var1.methods.add(var19);
      }

      MethodNode var20 = new MethodNode(4, "setup", var9, null, null);
      var20.instructions.add(new VarInsnNode(25, 0));
      var20.instructions.add(new MethodInsnNode(183, var17.getInternalName(), "setup", var9, false));
      var20.instructions.add(new FieldInsnNode(178, var1.name, "LISTENER_LIST", var11));
      LabelNode var23 = new LabelNode();
      var20.instructions.add(new JumpInsnNode(198, var23));
      var20.instructions.add(new InsnNode(177));
      var20.instructions.add(var23);
      var20.instructions.add(new FrameNode(3, 0, null, 0, null));
      var20.instructions.add(new TypeInsnNode(187, var2.getInternalName()));
      var20.instructions.add(new InsnNode(89));
      var20.instructions.add(new VarInsnNode(25, 0));
      var20.instructions.add(new MethodInsnNode(183, var17.getInternalName(), "getListenerList", var12, false));
      var20.instructions.add(new MethodInsnNode(183, var2.getInternalName(), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE, new Type[]{var2}), false));
      var20.instructions.add(new FieldInsnNode(179, var1.name, "LISTENER_LIST", var11));
      var20.instructions.add(new InsnNode(177));
      var1.methods.add(var20);
      var20 = new MethodNode(1, "getListenerList", var12, null, null);
      var20.instructions.add(new FieldInsnNode(178, var1.name, "LISTENER_LIST", var11));
      var20.instructions.add(new InsnNode(176));
      var1.methods.add(var20);
      return true;
   }
}
