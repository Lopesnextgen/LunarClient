package com.moonsworth.lunar.ichor;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.util.Annotations;

@PipelineHook
public interface SmuggleHideHook {
   @TransformClass
   default void method1(ClassNode node1, URLClassLoader urlclassloader2) {
      if (Annotations.getInvisible(node1, Mixin.class) != null) {
         for (MethodNode method4 : node1.methods) {
            AnnotationNode annotation5 = Annotations.getInvisible(method4, Smuggle.class);
            if (annotation5 != null) {
               if (method4.visibleAnnotations != null) {
                  this.method2(annotation5, method4.visibleAnnotations, true);
               }

               if (method4.invisibleAnnotations != null) {
                  this.method2(annotation5, method4.invisibleAnnotations, false);
               }
            }
         }

         for (FieldNode field7 : node1.fields) {
            AnnotationNode annotation8 = Annotations.getInvisible(field7, Smuggle.class);
            if (annotation8 != null) {
               if (field7.visibleAnnotations != null) {
                  this.method2(annotation8, field7.visibleAnnotations, true);
               }

               if (field7.invisibleAnnotations != null) {
                  this.method2(annotation8, field7.invisibleAnnotations, false);
               }
            }
         }
      }
   }

   private void method2(AnnotationNode annotation1, List<AnnotationNode> list2, boolean flag3) {
      String text4 = "smuggled" + (flag3 ? "Visible" : "Invisible");
      List list5 = (List)Annotations.getValue(annotation1, text4);
      if (list5 == null) {
         Annotations.setValue(annotation1, text4, list5 = new ArrayList());
      }

      if ((Boolean)Annotations.getValue(annotation1, "smuggle", Boolean.TRUE)) {
         ListIterator iterator6 = list2.listIterator();

         while (iterator6.hasNext()) {
            AnnotationNode annotation7 = (AnnotationNode)iterator6.next();
            if (!annotation7.desc.equals(Type.getDescriptor(Shadow.class)) && annotation7 != annotation1) {
               list5.add(annotation7);
               iterator6.remove();
            }
         }
      }

      if (flag3) {
         AnnotationNode annotation8 = (AnnotationNode)Annotations.getValue(annotation1, "shadow");
         if (annotation8 != null) {
            list5.add(annotation8);
         }
      }
   }
}
