package com.moonsworth.lunar.ichor;

import java.lang.reflect.Method;

public final class MixinHelper7 extends MixinHelper<Annotation10> {
   public MixinHelper7(Annotation10 var1, AutoCloseableIterator var2, Method var3) {
      super(var1, var2, var3);
   }

   @Override
   public boolean method1(IchorTransformer.Data3 var1) {
      throw new IllegalStateException("Tried to handle resource as class, shouldn't happen.");
   }

   public void method2(MixinShared var1, URLClassLoader var2) {
      this.method3(((Annotation10)this.field1).value(), var1.getResourcePath(), var1);
   }

   private void method3(String var1, String var2, MixinShared var3) {
      if (var3 != null && this.field3 != null && AutoCloseableIterator.method5(var1).matcher(var2).matches()) {
         try {
            this.field3.invoke(this.field2.method6(), var3);
         } catch (Exception var5) {
            var5.printStackTrace();
         }
      }
   }
}
