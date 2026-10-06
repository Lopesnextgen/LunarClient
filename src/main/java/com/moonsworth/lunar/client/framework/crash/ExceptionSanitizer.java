package com.moonsworth.lunar.client.framework.crash;

import com.moonsworth.lunar.client.framework.crash.IgnoredExceptionPatterns;
import java.util.regex.Pattern;
import lombok.Generated;
import org.apache.commons.lang3.exception.ExceptionUtils;

public final class ExceptionSanitizer {
   public static boolean method1(Throwable exception0) {
      if (exception0.getCause() != null && !method1(exception0.getCause())) {
         return false;
      }

      String text1 = ExceptionUtils.getStackTrace(exception0);

      for (Pattern pattern3 : IgnoredExceptionPatterns.method2()) {
         if (pattern3.matcher(text1).find()) {
            return false;
         }
      }

      return !(exception0 instanceof OutOfMemoryError);
   }

   public static Throwable method2(Throwable exception0) {
      if (exception0 == null) {
         return null;
      }

      method3(exception0);
      String text1 = exception0.getMessage();
      if (text1 != null) {
         String text2 = method4(text1);
         if (!text2.equals(text1)) {
            boolean flag3 = exception0.getCause() != null;
            Throwable exception4 = flag3 ? method2(exception0.getCause()) : null;

            try {
               Throwable exception5;
               if (flag3) {
                  exception5 = (Throwable)exception0.getClass().getConstructor(String.class, Throwable.class).newInstance(text2, exception4);
               } else {
                  exception5 = (Throwable)exception0.getClass().getConstructor(String.class).newInstance(text2);
               }

               exception5.setStackTrace(exception0.getStackTrace());
               exception0 = exception5;
            } catch (Throwable exception6) {
               exception0 = new CrashReportError(exception0, text2, exception4);
            }
         }
      }

      return exception0;
   }

   private static void method3(Throwable exception0) {
      StackTraceElement[] items1 = exception0.getStackTrace();

      for (int index2 = 0; index2 < items1.length; index2++) {
         StackTraceElement stacktraceelement3 = items1[index2];
         items1[index2] = new StackTraceElement(
            stacktraceelement3.getClassLoaderName(),
            stacktraceelement3.getModuleName(),
            stacktraceelement3.getModuleVersion(),
            stacktraceelement3.getClassName(),
            method5(stacktraceelement3.getMethodName()),
            stacktraceelement3.getFileName(),
            stacktraceelement3.getLineNumber()
         );
      }

      exception0.setStackTrace(items1);
   }

   private static String method4(String text0) {
      return text0.replaceAll("com\\.moonsworth\\.lunar(\\.[ICHOR]+)+", "Obf'd")
         .replaceAll("[ICHOR]{5,}", "Obf'd")
         .replaceAll("'Genesis' @[a-f0-9A-F]+", "Genesis");
   }

   private static String method5(String text0) {
      return text0.replaceFirst("handler\\$[A-Za-z0-9]+\\$", "handler_")
         .replaceFirst("redirect\\$[A-Za-z0-9]+\\$", "redirect_")
         .replaceFirst("proxy\\$[A-Za-z0-9]+\\$", "proxy_")
         .replaceFirst("wrapOperation\\$[A-Za-z0-9]+\\$", "wrapOperation_");
   }

   @Generated
   private ExceptionSanitizer() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
