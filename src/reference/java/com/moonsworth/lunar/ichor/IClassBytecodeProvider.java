package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.api.IchorClassLoader;
import com.moonsworth.lunar.ichor.util.AsmUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.util.Annotations;

public class IClassBytecodeProvider implements org.spongepowered.asm.service.IClassBytecodeProvider {
   private static final String field1 = "com/moonsworth/lunar/ichor/synthetic/DummyTarget";
   private static final String field2 = "com/moonsworth/lunar/ichor/synthetic/IDummyTarget";
   private final MixinTransformTask[] field3;

   public IClassBytecodeProvider(URLClassLoader urlclassloader1) {
      this.field3 = urlclassloader1.method1()
         .method30()
         .method21()
         .values()
         .stream()
         .flatMap(Collection::stream)
         .filter(arg0 -> arg0.method6() instanceof MixinTransformTask)
         .map(arg0 -> (MixinTransformTask)arg0.method6())
         .toArray(MixinTransformTask[]::new);
      if (this.field3.length == 0) {
         IchorPipeline.field3.warn("No MixinClassNodeHandlers found!", new Object[0]);
      }
   }

   public ClassNode getClassNode(String text1) {
      return this.getClassNode(text1, true);
   }

   public ClassNode getClassNode(String text1, boolean flag2) {
      if ("com/moonsworth/lunar/ichor/synthetic/DummyTarget".equals(text1)) {
         return this.method3();
      } else if ("com/moonsworth/lunar/ichor/synthetic/IDummyTarget".equals(text1)) {
         return this.method4();
      } else {
         ClassLoader classloader3 = MixinBootstrap.class.getClassLoader();
         if (!(classloader3 instanceof IchorClassLoader ichorapi24)) {
            throw new Error("Attempted to load Mixin from " + classloader3.getName());
         } else {
            URLClassLoader urlclassloader5 = ichorapi24.method4();
            ClassNode node6 = urlclassloader5.method6(text1, false);
            if (node6 == null) {
               throw new Error("Failed to get patched class for " + text1);
            }

            IchorPipeline ichor77 = urlclassloader5.method1();
            if (AsmUtils.method17(node6) && !this.method1(node6, ichor77)) {
               return this.method2(node6);
            }

            for (MixinTransformTask mixininternal11 : this.field3) {
               mixininternal11.method2(node6, urlclassloader5);
            }

            return node6;
         }
      }
   }

   public ClassNode getClassNode(String text1, boolean flag2, int number3) {
      return this.getClassNode(text1, flag2);
   }

   private boolean method1(ClassNode node1, IchorPipeline ichor72) {
      for (MixinTransformTask mixininternal6 : this.field3) {
         if (!mixininternal6.method1(node1, ichor72)) {
            return false;
         }
      }

      return true;
   }

   private ClassNode method2(ClassNode node1) {
      AnnotationNode annotation2 = Annotations.get(new ArrayList<>(MixinTargetRemapper.method3(node1).keySet()), Type.getDescriptor(Mixin.class));
      Annotations.setValue(annotation2, "value", List.of());
      List list3;
      if ((node1.access & 512) == 512) {
         list3 = List.of("com/moonsworth/lunar/ichor/synthetic/IDummyTarget");
      } else {
         list3 = List.of("com/moonsworth/lunar/ichor/synthetic/DummyTarget");
      }

      Annotations.setValue(annotation2, "targets", list3);
      node1.superName = "java/lang/Object";
      node1.interfaces.clear();
      return node1;
   }

   private ClassNode method3() {
      ClassNode node1 = new ClassNode();
      node1.name = "com/moonsworth/lunar/ichor/synthetic/DummyTarget";
      node1.access = 32;
      node1.version = 52;
      node1.superName = "java/lang/Object";
      return node1;
   }

   private ClassNode method4() {
      ClassNode node1 = new ClassNode();
      node1.name = "com/moonsworth/lunar/ichor/synthetic/IDummyTarget";
      node1.access = 512;
      node1.version = 52;
      return node1;
   }
}
