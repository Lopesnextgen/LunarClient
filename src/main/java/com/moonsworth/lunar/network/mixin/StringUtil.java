package com.moonsworth.lunar.network.mixin;

import java.util.Collection;
import java.util.Iterator;

public class StringUtil {
   public StringUtil() {
   }

   public static boolean method1(String[] items0, String text1) {
      for (String text5 : items0) {
         if (text1 == null && text5 == null) {
            return true;
         }

         if (text1 != null && text1.equalsIgnoreCase(text5)) {
            return true;
         }
      }

      return false;
   }

   public static String join(String[] items0, String text1) {
      int number2 = items0.length;
      if (number2 == 0) {
         return "";
      }

      StringBuilder builder3 = new StringBuilder();
      builder3.append(items0[0]);

      for (int index4 = 1; index4 < number2; index4++) {
         builder3.append(text1).append(items0[index4]);
      }

      return builder3.toString();
   }

   public static String method2(Collection<String> list, String text1) {
      Iterator iterator2 = list.iterator();
      StringBuilder builder3 = new StringBuilder();
      if (iterator2.hasNext()) {
         builder3.append((String)iterator2.next());
      }

      while (iterator2.hasNext()) {
         builder3.append(text1).append((String)iterator2.next());
      }

      return builder3.toString();
   }
}
