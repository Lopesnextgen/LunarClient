package com.moonsworth.lunar.client.config.option;

public class OptionException extends RuntimeException {
   public OptionException(Object object, String text) {
      super(method1(object.getClass()) + " - " + text);
   }

   private static String method1(Class<?> clazz0) {
      String text1 = clazz0.getSimpleName();
      int number2 = text1.length();
      if (number2 <= 8) {
         String text3 = clazz0.getName();
         int index4 = text3.lastIndexOf(46);
         return index4 != -1 && text3.length() - index4 >= 8 ? text3.substring(index4 + 1) : text3;
      } else {
         return text1;
      }
   }
}
