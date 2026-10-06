package com.moonsworth.lunar.legacy.wrapper;

import java.io.OutputStream;
import java.io.PrintStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class LoggerPrintStream extends PrintStream {
   public static Logger LOGGER = LogManager.getLogger();
   public String domain;

   public LoggerPrintStream(String text1, OutputStream stream) {
      super(stream);
      this.domain = text1;
   }

   @Override
   public void println(String text1) {
      this.method1(text1);
   }

   @Override
   public void println(Object obj1) {
      this.method1(String.valueOf(obj1));
   }

   @Override
   public void print(String text1) {
      this.method1(text1);
   }

   @Override
   public void print(Object obj1) {
      this.method1(String.valueOf(obj1));
   }

   @Override
   public PrintStream printf(@NotNull String text1, Object... items2) {
      if (text1.endsWith("%n")) {
         text1 = text1.substring(0, text1.length() - 2);
      }

      if (text1.endsWith("\n")) {
         text1 = text1.substring(0, text1.length() - 1);
      }

      this.method1(String.format(text1, items2));
      return this;
   }

   public void method1(String text1) {
      LOGGER.info("[{}]: {}", this.domain, text1);
   }
}
