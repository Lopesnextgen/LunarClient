package com.moonsworth.lunar.client.inventorymod;

import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import com.moonsworth.lunar.ichor.util.FatalIchorError13;

public class Inventorymod {
   public static void method1(Throwable throwable) {
      IchorPipeline var1 = IchorAPI.getPipeline(Inventorymod.class.getClassLoader()).orElseThrow();
      StackTraceElement[] var2 = throwable.getStackTrace();
      boolean var3 = false;

      for (int var4 = 0; var4 < var2.length; var4++) {
         StackTraceElement var5 = var2[var4];
         if (!var5.isNativeMethod()) {
            String var6 = var5.getClassName();
            FatalIchorError13 var7 = var1.method27(var6.replace('.', '/'));
            if (var7 != null) {
               String var8 = var5.getMethodName();
               FatalIchorError13.Data var9 = var7.method3(var8);
               if (var9 != null) {
                  String var10 = var5.getFileName() + ":" + var9.method1().name();
                  var2[var4] = new StackTraceElement(var5.getClassName(), var5.getMethodName(), var10, var5.getLineNumber());
                  var3 = true;
               }
            }
         }
      }

      if (var3) {
         throwable.setStackTrace(var2);
      }
   }
}
