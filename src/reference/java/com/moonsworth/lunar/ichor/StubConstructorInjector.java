package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.AsmUtils;
import com.moonsworth.lunar.loader.PipelineStage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InnerClassNode;
import org.objectweb.asm.tree.MethodNode;

public class StubConstructorInjector implements IchorInjection {
   public StubConstructorInjector() {
   }

   public IchorStage[] method2() {
      return new IchorStage[]{PipelineStage.INIT};
   }

   @TransformClass
   public void method2(ClassNode node1, URLClassLoader urlclassloader2) {
      String text3 = this.method4(node1);
      if (text3 != null && !this.method5(node1, text3)) {
         node1.methods.add(this.method3(node1, text3));
      }
   }

   private MethodNode method3(ClassNode node1, String text2) {
      MethodNode method3_ = new MethodNode(1, "<init>", text2, null, null);
      method3_.instructions = new StubConstructorBody(this, node1);
      method3_.visibleAnnotations = new ArrayList<>(List.of(AsmUtils.method47(Stub.class, Map.of("value", "Stub Constructor"))));

      try {
         Class clazz4 = Class.forName("org.spongepowered.asm.mixin.transformer.meta.MixinMerged");
         method3_.visibleAnnotations
            .add(AsmUtils.method47(clazz4, Map.of("priority", Integer.MAX_VALUE, "mixin", "com.moonsworth.dummy.StubConstructorMixin")));
      } catch (ClassNotFoundException classnotfoundexception5) {
      }

      return method3_;
   }

   private String method4(ClassNode node1) {
      for (InnerClassNode innerclassnode3 : Objects.requireNonNullElse(node1.innerClasses, List.of())) {
         if (node1.name.equals(innerclassnode3.name)) {
            if (innerclassnode3.innerName == null) {
               return null;
            }

            if (!AsmUtils.isStatic(innerclassnode3.access)) {
               return "(L" + innerclassnode3.outerName + ";)V";
            }
         }
      }

      return "()V";
   }

   private boolean method5(ClassNode node1, String text2) {
      return (node1.access & 82432) != 0 || this.method6(node1, text2);
   }

   private boolean method6(ClassNode node1, String text2) {
      for (MethodNode method4_ : node1.methods) {
         if (method4_.name.equals("<init>") && method4_.desc.equals(text2)) {
            return true;
         }
      }

      return false;
   }
}
