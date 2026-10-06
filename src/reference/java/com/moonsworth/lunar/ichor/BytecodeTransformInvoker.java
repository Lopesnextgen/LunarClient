package com.moonsworth.lunar.ichor;

import java.lang.reflect.Method;

public class BytecodeTransformInvoker extends MixinHelper<TransformBytecode> {
   public BytecodeTransformInvoker(TransformBytecode annotation51, AutoCloseableIterator autocloseableiterator2, Method method3) {
      super(annotation51, autocloseableiterator2, method3);
   }

   public boolean method1(ClassTransformContext autocloseableiterator2$data31) {
      byte[] items3 = autocloseableiterator2$data31.method1(this.field3.getDeclaringClass());
      byte[] items2;
      if (this.field3.getParameterCount() == 1) {
         items2 = (byte[])this.method1(null, null, new Object[]{items3});
      } else if (this.field3.getParameterCount() == 2) {
         items2 = (byte[])this.method1(null, null, new Object[]{autocloseableiterator2$data31.className(), items3});
      } else {
         items2 = (byte[])this.method1(null, null, new Object[]{autocloseableiterator2$data31.className(), items3, autocloseableiterator2$data31.method6()});
      }

      if (items2 != null) {
         autocloseableiterator2$data31.method2(items2);
         return true;
      } else {
         return false;
      }
   }
}
