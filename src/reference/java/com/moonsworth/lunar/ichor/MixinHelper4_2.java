package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

@Annotation3
public interface MixinHelper4_2 {
   @Annotation
   default void method1(ClassNode var1, MethodNode var2) {
      if (var1.superName != null) {
         if (!var1.superName.contains("/")
            || var1.superName.startsWith("net/minecraft")
            || var1.superName.startsWith("com/mojang")
            || var1.superName.startsWith("net/optifine")) {
            var2.access = FatalIchorError6.method3(var2.access);
         }
      }
   }

   @Annotation11
   default void method2(ClassNode var1, FieldNode var2) {
      if (var1.superName != null) {
         if (!var1.superName.contains("/")
            || var1.superName.startsWith("net/minecraft")
            || var1.superName.startsWith("com/mojang")
            || var1.superName.startsWith("net/optifine")) {
            var2.access = FatalIchorError6.method3(var2.access);
         }
      }
   }
}
