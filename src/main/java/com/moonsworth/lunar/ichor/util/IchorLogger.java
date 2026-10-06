package com.moonsworth.lunar.ichor.util;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class IchorLogger {
   public static IchorLogger field1 = method2("Ichor");
   private final String field2;
   private Path field3 = null;
   private static final SimpleDateFormat field4 = new SimpleDateFormat("HH:mm:ss");

   public IchorLogger(String text1) {
      this.field2 = text1;
   }

   public static IchorLogger method1(Class<?> clazz0) {
      return new IchorLogger(clazz0.getSimpleName());
   }

   public static IchorLogger method2(String text) {
      return new IchorLogger(text);
   }

   public IchorLogger method3(@NotNull Path path1) {
      this.field3 = path1;

      try {
         Files.createDirectories(path1.getParent());
      } catch (IOException exception3) {
         exception3.printStackTrace();
      }

      return this;
   }

   public void method4(IchorLogger.Type type1, Throwable exception2) {
      this.method6(type1, "Catching " + exception2.getClass().getName() + ": " + exception2.getMessage(), exception2);
   }

   public void method5(IchorLogger.Type type1, String text2, Object... items3) {
      PrintStream stream4 = this.method8(type1);
      if (stream4 != null) {
         try {
            String text5 = String.format(text2, items3);
            stream4.printf("[%s] [%s/%s/%s] %s%n", field4.format(new Date()), this.field2, Thread.currentThread().getName(), type1, text5);
            this.method7(text5);
         } catch (Exception exception7) {
            String text6 = text2 + "[" + Arrays.stream(items3).map(Object::toString).collect(Collectors.joining(",")) + "]";
            stream4.printf("[%s] [%s/%s/%s] %s%n", field4.format(new Date()), this.field2, Thread.currentThread().getName(), type1, text6);
            this.method7(text6);
            this.method4(IchorLogger.Type.WARN, exception7);
            throw exception7;
         }
      }
   }

   public void method6(IchorLogger.Type type1, String text2, Throwable exception3) {
      PrintStream stream4 = this.method8(type1);
      if (stream4 != null) {
         stream4.printf("[%s] [%s/%s/%s] %s%n", field4.format(new Date()), this.field2, Thread.currentThread().getName(), type1, text2);
         exception3.printStackTrace(stream4);
         this.method7(
            text2 + "\n" + exception3.getMessage() + "\n" + String.join("\n", Arrays.stream(exception3.getStackTrace()).map(StackTraceElement::toString).toList()) + "\n"
         );
      }
   }

   private void method7(String text1) {
      if (this.field3 != null) {
         try {
            Files.write(this.field3, (text1 + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
         } catch (IOException exception3) {
            throw new RuntimeException(exception3);
         }
      }
   }

   public void info(String text1, Object... items2) {
      this.method5(IchorLogger.Type.INFO, text1, items2);
   }

   public void warn(String text1, Object... items2) {
      this.method5(IchorLogger.Type.WARN, text1, items2);
   }

   public void warn(String text1, Throwable exception2) {
      this.method6(IchorLogger.Type.WARN, text1, exception2);
   }

   public void fatal(String text1, Object... items2) {
      this.method5(IchorLogger.Type.FATAL, text1, items2);
   }

   public <T extends Throwable> T throwing(T t) {
      this.method5(IchorLogger.Type.WARN, "Throwing {}: {}", t.getClass().getName(), t.getMessage(), t);
      return (T)t;
   }

   private PrintStream method8(IchorLogger.Type type1) {
      return type1 != IchorLogger.Type.TRACE && type1 != IchorLogger.Type.DEBUG ? System.out : null;
   }

   @Generated
   public Path method9() {
      return this.field3;
   }

   public enum Type {
      FATAL,
      ERROR,
      WARN,
      INFO,
      DEBUG,
      TRACE;

      Type() {
      }
   }
}
