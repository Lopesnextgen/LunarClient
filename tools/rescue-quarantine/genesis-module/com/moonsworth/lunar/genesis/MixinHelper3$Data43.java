package com.moonsworth.lunar.genesis;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessControlException;
import com.google.common.collect.ImmutableMap;

final class MixinHelper3$Data43 implements java.lang.reflect.InvocationHandler {
   private static final ImmutableMap<String, Method> field1;
   private final MixinHelper3$Data42<?> field2;

   MixinHelper3$Data43(MixinHelper3$Data42<?> var1) {
      this.field2 = var1;
   }

   @Override
   public Object invoke(Object var1, Method var2, Object[] var3) {
      String var4 = var2.getName();
      Method var5 = field1.get(var4);
      if (var5 == null) {
         throw new UnsupportedOperationException(var4);
      }

      try {
         return var5.invoke(this.field2, var3);
      } catch (InvocationTargetException var7) {
         throw var7.getCause();
      }
   }

   static {
      ImmutableMap.Data2 var0 = ImmutableMap.method7();

      for (Method var4 : MixinHelper3$Data42.class.getMethods()) {
         if (var4.getDeclaringClass().equals(MixinHelper3$Data42.class)) {
            try {
               var4.setAccessible(true);
            } catch (AccessControlException var6) {
            }

            var0.method1(var4.getName(), var4);
         }
      }

      field1 = var0.method7();
   }
}
