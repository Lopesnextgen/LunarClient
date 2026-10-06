package com.moonsworth.lunar.ichor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.util.Annotations;

@PipelineHook
public interface SmuggleRestoreHook {
   @TransformClass
   default void method1(ClassNode node1, URLClassLoader urlclassloader2) {
      if (Annotations.getInvisible(node1, Mixin.class) != null) {
         for (MethodNode method4 : node1.methods) {
            AnnotationNode annotation5 = Annotations.getInvisible(method4, Smuggle.class);
            if (annotation5 != null) {
               if (method4.visibleAnnotations == null) {
                  method4.visibleAnnotations = new ArrayList();
               }

               if (method4.invisibleAnnotations == null) {
                  method4.invisibleAnnotations = new ArrayList();
               }

               this.method2(annotation5, method4.visibleAnnotations, true);
               this.method2(annotation5, method4.invisibleAnnotations, false);
            }
         }

         for (FieldNode field7 : node1.fields) {
            AnnotationNode annotation8 = Annotations.getInvisible(field7, Smuggle.class);
            if (annotation8 != null) {
               if (field7.visibleAnnotations == null) {
                  field7.visibleAnnotations = new ArrayList();
               }

               if (field7.invisibleAnnotations == null) {
                  field7.invisibleAnnotations = new ArrayList();
               }

               this.method2(annotation8, field7.visibleAnnotations, true);
               this.method2(annotation8, field7.invisibleAnnotations, false);
            }
         }
      }
   }

   private void method2(AnnotationNode annotation1, List<AnnotationNode> list2, boolean flag3) {
      String text4 = "smuggled" + (flag3 ? "Visible" : "Invisible");
      List list5 = (List)Annotations.getValue(annotation1, text4, Collections.emptyList());
      Set set6 = ((List)Annotations.getValue(annotation1, "remove", Collections.emptyList()))
         .stream()
         .map(Type::getDescriptor)
         .collect(Collectors.toCollection(HashSet::new));
      list5.forEach(arg1x -> set6.add(arg1x.desc));
      list2.removeIf(arg2x -> arg2x == annotation1 || set6.contains(arg2x.desc));
      list2.addAll(list5);
   }
}
