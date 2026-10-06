package com.moonsworth.lunar.client.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.moonsworth.lunar.client.framework.Client;

public class Slayer {
   private static final Logger field1 = LogManager.getLogger("Lunar Client");

   public static void method1(Object var0, Object... var1) {
      field1.debug("[LC] " + String.format(var0.toString(), var1));
   }

   public static void method2(String var0, Object var1, Object... var2) {
      field1.debug("[LC " + var0 + "] " + String.format(var1.toString(), var2));
   }

   public static void method3(Object var0, Object... var1) {
      field1.info("[LC] " + String.format(var0.toString(), var1));
   }

   public static void method4(String var0, Object var1, Object... var2) {
      field1.info("[LC " + var0 + "] " + String.format(var1.toString(), var2));
   }

   public static void method5(Object var0, Object... var1) {
      field1.warn("[LC] " + String.format(var0.toString(), var1));
   }

   public static void warn(String var0, Throwable var1) {
      field1.warn("[LC] " + var0, var1);
   }

   public static void method6(String var0, Object var1, Object... var2) {
      field1.warn("[LC " + var0 + "] " + String.format(var1.toString(), var2));
   }

   public static void method7(Object var0, Object... var1) {
      field1.error("[LC] " + String.format(var0.toString(), var1));
   }

   public static void method8(String var0, Object var1, Object... var2) {
      field1.error("[LC " + var0 + "] " + String.format(var1.toString(), var2));
   }

   public static void error(String var0, Throwable var1) {
      field1.error("[LC Error] {}", new Object[]{var0, var1});
   }

   public static void method9(Throwable var0, String var1, Object... var2) {
      field1.error(String.format(var1, var2), var0);
   }
}
