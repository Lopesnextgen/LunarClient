package com.moonsworth.lunar.genesis;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

final class ThreadFactoryBuilder$1 implements ThreadFactory {
   ThreadFactoryBuilder$1(ThreadFactory threadfactory1, String text2, AtomicLong number3, Boolean flag4, Integer number5, UncaughtExceptionHandler uncaughtexceptionhandler6) {
      this.field1 = threadfactory1;
      this.field2 = text2;
      this.field3 = number3;
      this.field4 = flag4;
      this.field5 = number5;
      this.field6 = uncaughtexceptionhandler6;
   }

   @Override
   public Thread newThread(Runnable runnable1) {
      Thread thread2 = this.field1.newThread(runnable1);
      if (this.field2 != null) {
         thread2.setName(ThreadFactoryBuilder.access$000(this.field2, new Object[]{this.field3.getAndIncrement()}));
      }

      if (this.field4 != null) {
         thread2.setDaemon(this.field4);
      }

      if (this.field5 != null) {
         thread2.setPriority(this.field5);
      }

      if (this.field6 != null) {
         thread2.setUncaughtExceptionHandler(this.field6);
      }

      return thread2;
   }
}
