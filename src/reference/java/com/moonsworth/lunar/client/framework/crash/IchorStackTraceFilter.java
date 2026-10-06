package com.moonsworth.lunar.client.framework.crash;

import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import com.moonsworth.lunar.ichor.util.StageMemberTracker;
import com.moonsworth.lunar.ichor.util.StageMemberTracker.Data;

public class IchorStackTraceFilter {
   public IchorStackTraceFilter() {
   }

   public static void method1(Throwable exception0) {
      IchorPipeline ichor71 = IchorAPI.getPipeline(IchorStackTraceFilter.class.getClassLoader()).orElseThrow();
      StackTraceElement[] items2 = exception0.getStackTrace();
      boolean flag3 = false;

      for (int index4 = 0; index4 < items2.length; index4++) {
         StackTraceElement stacktraceelement5 = items2[index4];
         if (!stacktraceelement5.isNativeMethod()) {
            String text6 = stacktraceelement5.getClassName();
            StageMemberTracker fatalichorerror137 = ichor71.method27(text6.replace('.', '/'));
            if (fatalichorerror137 != null) {
               String text8 = stacktraceelement5.getMethodName();
               Data data9 = fatalichorerror137.method3(text8);
               if (data9 != null) {
                  String text10 = stacktraceelement5.getFileName() + ":" + data9.method1().name();
                  items2[index4] = new StackTraceElement(stacktraceelement5.getClassName(), stacktraceelement5.getMethodName(), text10, stacktraceelement5.getLineNumber());
                  flag3 = true;
               }
            }
         }
      }

      if (flag3) {
         exception0.setStackTrace(items2);
      }
   }
}
