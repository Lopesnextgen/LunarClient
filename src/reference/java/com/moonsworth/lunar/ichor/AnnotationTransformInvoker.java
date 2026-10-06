package com.moonsworth.lunar.ichor;

import java.lang.reflect.Method;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;

public class AnnotationTransformInvoker extends MixinHelper<Annotation4> {
   public AnnotationTransformInvoker(Annotation4 var1, AutoCloseableIterator var2, Method var3) {
      super(var1, var2, var3);
   }

   @Override
   public boolean method1(IchorTransformer.Data3 var1) {
      ClassNode var2 = var1.method3(this.field3.getDeclaringClass());
      boolean var3 = false;

      for (AnnotationNode var5 : var2.visibleAnnotations) {
         var3 |= this.method2(((Annotation4)this.field1).value(), var5.desc, new Object[]{var2, var5});
      }

      return var3;
   }
}
