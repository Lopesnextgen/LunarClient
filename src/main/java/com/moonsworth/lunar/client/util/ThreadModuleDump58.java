package com.moonsworth.lunar.client.util;

public class ThreadModuleDump58 {
   public static final char[] field1 = new char[]{'/', '\n', '\r', '\t', '\u0000', '\f', '`', '?', '*', '\\', '<', '>', '|', '"', ':'};

   public static boolean method1(char var0) {
      return var0 != 167 && var0 >= ' ' && var0 != 127;
   }

   public static String method2(String var0) {
      StringBuilder var1 = new StringBuilder();

      for (char var5 : var0.toCharArray()) {
         if (method1(var5)) {
            var1.append(var5);
         }
      }

      return var1.toString();
   }
}
