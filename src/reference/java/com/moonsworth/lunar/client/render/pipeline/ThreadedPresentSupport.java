package com.moonsworth.lunar.client.render.pipeline;

import com.moonsworth.lunar.client.util.LunarLogger;

public class ThreadedPresentSupport {
   private static final ThreadedPresentSupport field1 = new ThreadedPresentSupport();
   private boolean supported = false;

   public ThreadedPresentSupport() {
   }

   public void method1(boolean flag) {
      LunarLogger.method3("Threaded Present Supported %b", new Object[]{flag});
      this.supported = flag;
   }

   public boolean isSupported() {
      return this.supported;
   }

   public static ThreadedPresentSupport method3() {
      return field1;
   }
}
