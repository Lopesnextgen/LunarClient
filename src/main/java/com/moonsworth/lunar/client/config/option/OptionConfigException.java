package com.moonsworth.lunar.client.config.option;

public class OptionConfigException extends RuntimeException {
   public OptionConfigException(Object var1, String var2) {
      super(method1(var1.getClass()) + " - " + var2);
   }

   private static String method1(Class<?> type) {
      String var1 = type.getSimpleName();
      int var2 = var1.length();
      if (var2 <= 8) {
         String var3 = type.getName();
         int var4 = var3.lastIndexOf(46);
         return var4 != -1 && var3.length() - var4 >= 8 ? var3.substring(var4 + 1) : var3;
      } else {
         return var1;
      }
   }
}
