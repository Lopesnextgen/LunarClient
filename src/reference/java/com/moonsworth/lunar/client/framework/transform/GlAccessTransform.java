package com.moonsworth.lunar.client.framework.transform;

import com.moonsworth.lunar.ichor.Annotation;
import com.moonsworth.lunar.ichor.IchorTransformer;
import com.moonsworth.lunar.ichor.IchorInjection;
import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.loader.Ichor4Type;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;

public class GlAccessTransform implements IchorInjection {
   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.INIT};
   }

   @Override
   public boolean method1(IchorTransformer.Data3 var1) {
      return var1.className().startsWith("com/moonsworth/");
   }

   @Annotation
   public void method3(ClassNode var1, MethodNode methodNode) {
      for (AbstractInsnNode var6 : methodNode.instructions.toArray()) {
         if (var6 instanceof MethodInsnNode var7 && var7.owner.startsWith("org/lwjgl/opengl/GL")) {
            InsnList var8 = new InsnList();
            Type[] var9 = Type.getArgumentTypes(var7.desc);

            for (Type var13 : var9) {
               if (var13 != Type.LONG_TYPE && var13 != Type.DOUBLE_TYPE) {
                  var8.add(new InsnNode(87));
               } else {
                  var8.add(new InsnNode(88));
               }
            }

            TypeInsnNode var14 = new TypeInsnNode(187, "java/lang/RuntimeException");
            var8.add(var14);
            var8.add(new InsnNode(89));
            var8.add(new LdcInsnNode("Tried to use an opengl function despite no opengl context present!"));
            var8.add(new MethodInsnNode(183, "java/lang/RuntimeException", "<init>", "(Ljava/lang/String;)V", false));
            var8.add(new InsnNode(191));
            methodNode.instructions.insert(var6, var8);
            methodNode.instructions.remove(var6);
         }
      }
   }
}
