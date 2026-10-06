package com.moonsworth.lunar.ichor;

import java.lang.reflect.Method;
import org.objectweb.asm.tree.ClassNode;

public class ClassTransformInvoker extends MixinHelper<Annotation7> {
   public ClassTransformInvoker(Annotation7 var1, AutoCloseableIterator var2, Method method) {
      super(var1, var2, method);
   }

   @Override
   public boolean method1(IchorTransformer.Data3 var1) {
      ClassNode var2 = var1.method3(this.field3.getDeclaringClass());
      return this.field3.getParameterCount() == 1
         ? this.method2(null, null, new Object[]{var2})
         : this.method2(null, null, new Object[]{var2, var1.method6()});
   }
}
