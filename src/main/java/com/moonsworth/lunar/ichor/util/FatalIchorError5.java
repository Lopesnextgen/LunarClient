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

public class FatalIchorError5 {
   public static FatalIchorError5 field1 = method2("Ichor");
   private final String field2;
   private Path field3 = null;
   private static final SimpleDateFormat field4 = new SimpleDateFormat("HH:mm:ss");

   public FatalIchorError5(String var1) {
      this.field2 = var1;
   }

   public static FatalIchorError5 method1(Class<?> var0) {
      return new FatalIchorError5(var0.getSimpleName());
   }

   public static FatalIchorError5 method2(String var0) {
      return new FatalIchorError5(var0);
   }

   public FatalIchorError5 method3(@NotNull Path var1) {
      this.field3 = var1;

      try {
         Files.createDirectories(var1.getParent());
      } catch (IOException var3) {
         var3.printStackTrace();
      }

      return this;
   }

   public void method4(FatalIchorError5.Type var1, Throwable var2) {
      this.method6(var1, "Catching " + var2.getClass().getName() + ": " + var2.getMessage(), var2);
   }

   public void method5(FatalIchorError5.Type var1, String var2, Object... var3) {
      PrintStream var4 = this.method8(var1);
      if (var4 != null) {
         try {
            String var5 = String.format(var2, var3);
            var4.printf("[%s] [%s/%s/%s] %s%n", field4.format(new Date()), this.field2, Thread.currentThread().getName(), var1, var5);
            this.method7(var5);
         } catch (Exception var7) {
            String var6 = var2 + "[" + Arrays.stream(var3).map(Object::toString).collect(Collectors.joining(",")) + "]";
            var4.printf("[%s] [%s/%s/%s] %s%n", field4.format(new Date()), this.field2, Thread.currentThread().getName(), var1, var6);
            this.method7(var6);
            this.method4(FatalIchorError5.Type.WARN, var7);
            throw var7;
         }
      }
   }

   public void method6(FatalIchorError5.Type var1, String var2, Throwable var3) {
      PrintStream var4 = this.method8(var1);
      if (var4 != null) {
         var4.printf("[%s] [%s/%s/%s] %s%n", field4.format(new Date()), this.field2, Thread.currentThread().getName(), var1, var2);
         var3.printStackTrace(var4);
         this.method7(
            var2 + "\n" + var3.getMessage() + "\n" + String.join("\n", Arrays.stream(var3.getStackTrace()).map(StackTraceElement::toString).toList()) + "\n"
         );
      }
   }

   private void method7(String var1) {
      if (this.field3 != null) {
         try {
            Files.write(this.field3, (var1 + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
         } catch (IOException var3) {
            throw new RuntimeException(var3);
         }
      }
   }

   public void info(String var1, Object... var2) {
      this.method5(FatalIchorError5.Type.INFO, var1, var2);
   }

   public void warn(String var1, Object... var2) {
      this.method5(FatalIchorError5.Type.WARN, var1, var2);
   }

   public void warn(String var1, Throwable var2) {
      this.method6(FatalIchorError5.Type.WARN, var1, var2);
   }

   public void fatal(String var1, Object... var2) {
      this.method5(FatalIchorError5.Type.FATAL, var1, var2);
   }

   public <T extends Throwable> T throwing(T var1) {
      this.method5(FatalIchorError5.Type.WARN, "Throwing {}: {}", var1.getClass().getName(), var1.getMessage(), var1);
      return (T)var1;
   }

   private PrintStream method8(FatalIchorError5.Type var1) {
      return var1 != FatalIchorError5.Type.TRACE && var1 != FatalIchorError5.Type.DEBUG ? System.out : null;
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
   }
}
