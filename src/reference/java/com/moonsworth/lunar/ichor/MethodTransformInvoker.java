package com.moonsworth.lunar.ichor;

import java.lang.reflect.Method;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class MethodTransformInvoker extends MixinHelper<Annotation> {
   public MethodTransformInvoker(Annotation var1, AutoCloseableIterator var2, Method var3) {
      super(var1, var2, var3);
   }

   @Override
   public boolean method1(IchorTransformer.Data3 var1) {
      ClassNode var2 = var1.method3(this.field3.getDeclaringClass());
      boolean var3 = false;

      for (MethodNode var5 : var2.methods) {
         var3 |= this.method2(((Annotation)this.field1).value(), var5.name, new Object[]{var2, var5});
      }

      return var3;
   }
}
