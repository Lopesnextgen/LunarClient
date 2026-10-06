package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InnerClassNode;
import org.objectweb.asm.tree.MethodNode;

@Annotation3
public interface MixinHelper_2 {
   @Annotation7
   default void method1(ClassNode var1) {
      if (this.method4()) {
         var1.access = FatalIchorError6.method4(var1.access, true);
         var1.permittedSubclasses = null;

         for (InnerClassNode var3 : var1.innerClasses) {
            var3.access = FatalIchorError6.method4(var3.access, true);
         }
      }
   }

   @Annotation
   default void method2(ClassNode var1, MethodNode var2) {
      if (this.method4()) {
         boolean var3 = (var2.access & 64) != 0;
         boolean var4 = (var1.access & 4096) != 0 || !var3 && (var2.access & 4096) != 0;
         if (!var4) {
            boolean var5 = (var1.access & 512) != 0;
            var2.access = FatalIchorError6.method4(var2.access, !var5 && this.method4());
         }
      }
   }

   @Annotation11
   default void method3(ClassNode var1, FieldNode var2) {
      boolean var3 = (var1.access & 512) != 0;
      var2.access = FatalIchorError6.method4(var2.access, !var3 && this.method4());
   }

   default boolean method4() {
      return false;
   }
}
