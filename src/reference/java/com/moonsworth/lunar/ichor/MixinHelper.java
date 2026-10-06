package com.moonsworth.lunar.ichor;

import java.lang.reflect.Method;

public abstract class MixinHelper<Nectar extends java.lang.annotation.Annotation> {
   protected final Nectar field1;
   protected AutoCloseableIterator field2;
   protected final Method field3;

   public MixinHelper(Nectar var1, AutoCloseableIterator var2, Method var3) {
      this.field1 = (Nectar)var1;
      this.field2 = var2;
      this.field3 = var3;
   }

   public abstract boolean method1(IchorTransformer.Data3 var1);

   protected boolean method2(String var1, String var2, Object... var3) {
      if (var1 == null || AutoCloseableIterator.method5(var1).matcher(var2).matches()) {
         try {
            this.field3.invoke(this.field2.method6(), var3);
            return true;
         } catch (Exception var5) {
            System.err.println("Failed to apply " + this.getClass().getName() + " to " + var2);
            var5.printStackTrace();
         }
      }

      return false;
   }

   protected <T> T method3(String var1, String var2, Object... var3) {
      if (var1 == null || AutoCloseableIterator.method5(var1).matcher(var2).matches()) {
         try {
            return (T)this.field3.invoke(this.field2.method6(), var3);
         } catch (Exception var5) {
            System.err.println("Failed to apply " + this.getClass().getName() + " to " + var2);
            var5.printStackTrace();
         }
      }

      return null;
   }

   @Override
   public String toString() {
      return "@" + this.field1.getClass().getName() + "(" + this.field2.method6().getClass().getName() + "," + this.field3.getName() + ")";
   }

   public String getMethodName() {
      return this.field3.getName();
   }
}
