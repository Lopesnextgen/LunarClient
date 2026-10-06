package com.moonsworth.lunar.client.inventorymod;

import com.moonsworth.lunar.client.framework.crash.IgnoredExceptionPatterns;
import java.util.regex.Pattern;
import lombok.Generated;
import org.apache.commons.lang3.exception.ExceptionUtils;

public final class Inventorymod3 {
   public static boolean method1(Throwable var0) {
      if (var0.getCause() != null && !method1(var0.getCause())) {
         return false;
      }

      String var1 = ExceptionUtils.getStackTrace(var0);

      for (Pattern var3 : IgnoredExceptionPatterns.method2()) {
         if (var3.matcher(var1).find()) {
            return false;
         }
      }

      return !(var0 instanceof OutOfMemoryError);
   }

   public static Throwable method2(Throwable var0) {
      if (var0 == null) {
         return null;
      }

      method3(var0);
      String var1 = var0.getMessage();
      if (var1 != null) {
         String var2 = method4(var1);
         if (!var2.equals(var1)) {
            boolean var3 = var0.getCause() != null;
            Throwable var4 = var3 ? method2(var0.getCause()) : null;

            try {
               Throwable var5;
               if (var3) {
                  var5 = (Throwable)var0.getClass().getConstructor(String.class, Throwable.class).newInstance(var2, var4);
               } else {
                  var5 = (Throwable)var0.getClass().getConstructor(String.class).newInstance(var2);
               }

               var5.setStackTrace(var0.getStackTrace());
               var0 = var5;
            } catch (Throwable var6) {
               var0 = new InventorymodError(var0, var2, var4);
            }
         }
      }

      return var0;
   }

   private static void method3(Throwable var0) {
      StackTraceElement[] var1 = var0.getStackTrace();

      for (int var2 = 0; var2 < var1.length; var2++) {
         StackTraceElement var3 = var1[var2];
         var1[var2] = new StackTraceElement(
            var3.getClassLoaderName(),
            var3.getModuleName(),
            var3.getModuleVersion(),
            var3.getClassName(),
            method5(var3.getMethodName()),
            var3.getFileName(),
            var3.getLineNumber()
         );
      }

      var0.setStackTrace(var1);
   }

   private static String method4(String var0) {
      return var0.replaceAll("com\\.moonsworth\\.lunar(\\.[ICHOR]+)+", "Obf'd")
         .replaceAll("[ICHOR]{5,}", "Obf'd")
         .replaceAll("'Genesis' @[a-f0-9A-F]+", "Genesis");
   }

   private static String method5(String var0) {
      return var0.replaceFirst("handler\\$[A-Za-z0-9]+\\$", "handler_")
         .replaceFirst("redirect\\$[A-Za-z0-9]+\\$", "redirect_")
         .replaceFirst("proxy\\$[A-Za-z0-9]+\\$", "proxy_")
         .replaceFirst("wrapOperation\\$[A-Za-z0-9]+\\$", "wrapOperation_");
   }

   @Generated
   private Inventorymod3() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
