package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import com.moonsworth.lunar.loader.Ichor4Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InnerClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class Ichor2Iterator2 implements IchorInjection {
   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.INIT};
   }

   @Annotation7
   public void method2(ClassNode var1, URLClassLoader var2) {
      String var3 = this.method4(var1);
      if (var3 != null && !this.method5(var1, var3)) {
         var1.methods.add(this.method3(var1, var3));
      }
   }

   private MethodNode method3(final ClassNode var1, String var2) {
      MethodNode var3 = new MethodNode(1, "<init>", var2, null, null);
      var3.instructions = new InsnList() {
         {
            this.add(new VarInsnNode(25, 0));
            this.add(new MethodInsnNode(183, var1.superName, "<init>", "()V", false));
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
      };
      var3.visibleAnnotations = new ArrayList<>(List.of(FatalIchorError6.method47(Annotation13.class, Map.of("value", "Stub Constructor"))));

      try {
         Class var4 = Class.forName("org.spongepowered.asm.mixin.transformer.meta.MixinMerged");
         var3.visibleAnnotations
            .add(FatalIchorError6.method47(var4, Map.of("priority", Integer.MAX_VALUE, "mixin", "com.moonsworth.dummy.StubConstructorMixin")));
      } catch (ClassNotFoundException var5) {
      }

      return var3;
   }

   private String method4(ClassNode var1) {
      for (InnerClassNode var3 : Objects.requireNonNullElse(var1.innerClasses, List.of())) {
         if (var1.name.equals(var3.name)) {
            if (var3.innerName == null) {
               return null;
            }

            if (!FatalIchorError6.isStatic(var3.access)) {
               return "(L" + var3.outerName + ";)V";
            }
         }
      }

      return "()V";
   }

   private boolean method5(ClassNode var1, String var2) {
      return (var1.access & 82432) != 0 || this.method6(var1, var2);
   }

   private boolean method6(ClassNode var1, String var2) {
      for (MethodNode var4 : var1.methods) {
         if (var4.name.equals("<init>") && var4.desc.equals(var2)) {
            return true;
         }
      }

      return false;
   }
}
