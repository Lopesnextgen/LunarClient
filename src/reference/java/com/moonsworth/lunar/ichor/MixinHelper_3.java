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

@Annotation3
public interface MixinHelper_3 {
   @Annotation7
   default void method1(ClassNode var1, URLClassLoader var2) {
      if (Annotations.getInvisible(var1, Mixin.class) != null) {
         for (MethodNode var4 : var1.methods) {
            AnnotationNode var5 = Annotations.getInvisible(var4, Annotation6.class);
            if (var5 != null) {
               if (var4.visibleAnnotations != null) {
                  this.method2(var5, var4.visibleAnnotations, true);
               }

               if (var4.invisibleAnnotations != null) {
                  this.method2(var5, var4.invisibleAnnotations, false);
               }
            }
         }

         for (FieldNode var7 : var1.fields) {
            AnnotationNode var8 = Annotations.getInvisible(var7, Annotation6.class);
            if (var8 != null) {
               if (var7.visibleAnnotations != null) {
                  this.method2(var8, var7.visibleAnnotations, true);
               }

               if (var7.invisibleAnnotations != null) {
                  this.method2(var8, var7.invisibleAnnotations, false);
               }
            }
         }
      }
   }

   private void method2(AnnotationNode var1, List<AnnotationNode> var2, boolean var3) {
      String var4 = "smuggled" + (var3 ? "Visible" : "Invisible");
      List var5 = (List)Annotations.getValue(var1, var4);
      if (var5 == null) {
         Annotations.setValue(var1, var4, var5 = new ArrayList());
      }

      if ((Boolean)Annotations.getValue(var1, "smuggle", Boolean.TRUE)) {
         ListIterator var6 = var2.listIterator();

         while (var6.hasNext()) {
            AnnotationNode var7 = (AnnotationNode)var6.next();
            if (!var7.desc.equals(Type.getDescriptor(Shadow.class)) && var7 != var1) {
               var5.add(var7);
               var6.remove();
            }
         }
      }

      if (var3) {
         AnnotationNode var8 = (AnnotationNode)Annotations.getValue(var1, "shadow");
         if (var8 != null) {
            var5.add(var8);
         }
      }
   }
}
