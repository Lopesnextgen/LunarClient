package com.moonsworth.lunar.genesis;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

@Annotation4
final class MixinHelper14$Data5 implements UncaughtExceptionHandler {
   private static final Logger field1 = Logger.getLogger(MixinHelper14$Data5.class.getName());
   private final Runtime field2;

   MixinHelper14$Data5(Runtime var1) {
      this.field2 = var1;
   }

   @Override
   public void uncaughtException(Thread var1, Throwable var2) {
      try {
         field1.log(Level.SEVERE, String.format(Locale.ROOT, "Caught an exception in %s.  Shutting down.", var1), var2);
      } catch (Throwable var7) {
         System.err.println(var2.getMessage());
         System.err.println(var7.getMessage());
      } finally {
         this.field2.exit(1);
      }
   }
}
