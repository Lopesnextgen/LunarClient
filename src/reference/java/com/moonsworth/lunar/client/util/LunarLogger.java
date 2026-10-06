package com.moonsworth.lunar.client.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.moonsworth.lunar.client.framework.Client;

public class LunarLogger {
   private static final Logger field1 = LogManager.getLogger("Lunar Client");

   public LunarLogger() {
   }

   public static void method1(Object obj0, Object... items1) {
      field1.debug("[LC] " + String.format(obj0.toString(), items1));
   }

   public static void method2(String text0, Object obj1, Object... items2) {
      field1.debug("[LC " + text0 + "] " + String.format(obj1.toString(), items2));
   }

   public static void method3(Object obj0, Object... items1) {
      field1.info("[LC] " + String.format(obj0.toString(), items1));
   }

   public static void method4(String text0, Object obj1, Object... items2) {
      field1.info("[LC " + text0 + "] " + String.format(obj1.toString(), items2));
   }

   public static void method5(Object obj0, Object... items1) {
      field1.warn("[LC] " + String.format(obj0.toString(), items1));
   }

   public static void warn(String text0, Throwable exception1) {
      field1.warn("[LC] " + text0, exception1);
   }

   public static void method6(String text0, Object obj1, Object... items2) {
      field1.warn("[LC " + text0 + "] " + String.format(obj1.toString(), items2));
   }

   public static void method7(Object obj0, Object... items1) {
      field1.error("[LC] " + String.format(obj0.toString(), items1));
   }

   public static void method8(String text0, Object obj1, Object... items2) {
      field1.error("[LC " + text0 + "] " + String.format(obj1.toString(), items2));
   }

   public static void error(String text0, Throwable exception1) {
      field1.error("[LC Error] {}", new Object[]{text0, exception1});
   }

   public static void method9(Throwable exception0, String text, Object... items2) {
      field1.error(String.format(text, items2), exception0);
   }
}
