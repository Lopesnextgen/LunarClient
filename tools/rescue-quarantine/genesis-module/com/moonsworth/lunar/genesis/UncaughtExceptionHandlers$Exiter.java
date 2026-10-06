package com.moonsworth.lunar.genesis;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.common.eventbus.Subscribe;

@Subscribe
final class UncaughtExceptionHandlers$Exiter implements UncaughtExceptionHandler {
   private static final Logger field1 = Logger.getLogger(UncaughtExceptionHandlers$Exiter.class.getName());
   private final Runtime field2;

   UncaughtExceptionHandlers$Exiter(Runtime runtime1) {
      this.field2 = runtime1;
   }

   @Override
   public void uncaughtException(Thread thread1, Throwable exception2) {
      try {
         field1.log(Level.SEVERE, String.format(Locale.ROOT, "Caught an exception in %s.  Shutting down.", thread1), exception2);
      } catch (Throwable exception7) {
         System.err.println(exception2.getMessage());
         System.err.println(exception7.getMessage());
      } finally {
         this.field2.exit(1);
      }
   }
}
