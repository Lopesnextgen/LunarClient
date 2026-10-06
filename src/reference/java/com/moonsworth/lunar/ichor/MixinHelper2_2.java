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

@Annotation3
public interface MixinHelper2_2 {
   @Annotation7
   default void method1(ClassNode var1, URLClassLoader var2) {
      if (Annotations.getInvisible(var1, Mixin.class) != null) {
         for (MethodNode var4 : var1.methods) {
            AnnotationNode var5 = Annotations.getInvisible(var4, Annotation6.class);
            if (var5 != null) {
               if (var4.visibleAnnotations == null) {
                  var4.visibleAnnotations = new ArrayList();
               }

               if (var4.invisibleAnnotations == null) {
                  var4.invisibleAnnotations = new ArrayList();
               }

               this.method2(var5, var4.visibleAnnotations, true);
               this.method2(var5, var4.invisibleAnnotations, false);
            }
         }

         for (FieldNode var7 : var1.fields) {
            AnnotationNode var8 = Annotations.getInvisible(var7, Annotation6.class);
            if (var8 != null) {
               if (var7.visibleAnnotations == null) {
                  var7.visibleAnnotations = new ArrayList();
               }

               if (var7.invisibleAnnotations == null) {
                  var7.invisibleAnnotations = new ArrayList();
               }

               this.method2(var8, var7.visibleAnnotations, true);
               this.method2(var8, var7.invisibleAnnotations, false);
            }
         }
      }
   }

   private void method2(AnnotationNode var1, List<AnnotationNode> var2, boolean var3) {
      String var4 = "smuggled" + (var3 ? "Visible" : "Invisible");
      List var5 = (List)Annotations.getValue(var1, var4, Collections.emptyList());
      Set var6 = ((List)Annotations.getValue(var1, "remove", Collections.emptyList()))
         .stream()
         .map(Type::getDescriptor)
         .collect(Collectors.toCollection(HashSet::new));
      var5.forEach(var1x -> var6.add(var1x.desc));
      var2.removeIf(var2x -> var2x == var1 || var6.contains(var2x.desc));
      var2.addAll(var5);
   }
}
