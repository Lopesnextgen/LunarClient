package com.moonsworth.lunar.files;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Files4 {
   private final String field1;
   private static final SimpleDateFormat field2 = new SimpleDateFormat("HH:mm:ss");

   public Files4(String var1) {
      this.field1 = var1;
   }

   public static Files4 method1(String text) {
      return new Files4(text);
   }

   public void method2(Files4.Type var1, Throwable var2) {
      this.method3(Files4.Type.WARN, "Catching {}: {}", var2.getClass().getName(), var2.getMessage(), var2);
   }

   public void method3(Files4.Type var1, String var2, Object... var3) {
      PrintStream var4 = this.method5(var1);
      if (var4 != null) {
         String var5 = String.format(var2, var3);
         var4.printf("[%s] [%s/%s] %s%n", field2.format(new Date()), this.field1, var1, var5);
      }
   }

   public void method4(Files4.Type var1, String var2, Throwable var3) {
      PrintStream var4 = this.method5(var1);
      if (var4 != null) {
         var4.printf("[%s] [%s/%s] %s%n", field2.format(new Date()), this.field1, var1, var2);
         var3.printStackTrace(var4);
      }
   }

   public void info(String var1, Object... var2) {
      this.method3(Files4.Type.INFO, var1, var2);
   }

   public <T extends Throwable> T throwing(T var1) {
      this.method3(Files4.Type.WARN, "Throwing {}: {}", var1.getClass().getName(), var1.getMessage(), var1);
      return (T)var1;
   }

   private PrintStream method5(Files4.Type var1) {
      return var1 != Files4.Type.TRACE && var1 != Files4.Type.DEBUG ? System.out : null;
   }

   public String id() {
      return this.field1;
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
