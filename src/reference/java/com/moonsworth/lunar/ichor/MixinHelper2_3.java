package com.moonsworth.lunar.ichor;

import java.lang.reflect.Method;

public class MixinHelper2_3 extends MixinHelper<Annotation5> {
   public MixinHelper2_3(Annotation5 var1, AutoCloseableIterator var2, Method var3) {
      super(var1, var2, var3);
   }

   @Override
   public boolean method1(IchorTransformer.Data3 var1) {
      byte[] var3 = var1.method1(this.field3.getDeclaringClass());
      byte[] var2;
      if (this.field3.getParameterCount() == 1) {
         var2 = (byte[])this.method1(null, null, new Object[]{var3});
      } else if (this.field3.getParameterCount() == 2) {
         var2 = (byte[])this.method1(null, null, new Object[]{var1.className(), var3});
      } else {
         var2 = (byte[])this.method1(null, null, new Object[]{var1.className(), var3, var1.method6()});
      }

      if (var2 != null) {
         var1.method2(var2);
         return true;
      } else {
         return false;
      }
   }
}
