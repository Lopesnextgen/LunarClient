package com.moonsworth.lunar.legacy.wrapper;

import java.io.OutputStream;
import java.io.PrintStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class PrintStreamImpl extends PrintStream {
   public static Logger LOGGER = LogManager.getLogger();
   public String domain;

   public PrintStreamImpl(String var1, OutputStream var2) {
      super(var2);
      this.domain = var1;
   }

   @Override
   public void println(String var1) {
      this.method1(var1);
   }

   @Override
   public void println(Object var1) {
      this.method1(String.valueOf(var1));
   }

   @Override
   public void print(String var1) {
      this.method1(var1);
   }

   @Override
   public void print(Object var1) {
      this.method1(String.valueOf(var1));
   }

   @Override
   public PrintStream printf(@NotNull String var1, Object... var2) {
      if (var1.endsWith("%n")) {
         var1 = var1.substring(0, var1.length() - 2);
      }

      if (var1.endsWith("\n")) {
         var1 = var1.substring(0, var1.length() - 1);
      }

      this.method1(String.format(var1, var2));
      return this;
   }

   public void method1(String var1) {
      LOGGER.info("[{}]: {}", this.domain, var1);
   }
}
