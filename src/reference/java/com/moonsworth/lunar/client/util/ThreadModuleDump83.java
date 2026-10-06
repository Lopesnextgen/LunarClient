package com.moonsworth.lunar.client.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThreadModuleDump83 {
   private static final Pattern ROMAN_PATTERN = Pattern.compile("^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
   public static final Pattern ROMAN_TOKEN_PATTERN = Pattern.compile(" ((?=[MDCLXVI])M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3}))\\w*");

   public static String replaceRomanNumerals(String var0) {
      StringBuilder var1 = new StringBuilder(var0);
      int var2 = 0;
      Matcher var3 = field2.matcher(var0);

      while (var3.find()) {
         String var4 = var3.group().trim();
         if (isRoman(var4)) {
            int var5 = var3.start(1);
            int var6 = var3.end(1);
            int var7 = parseRoman(var4);
            String var8 = String.valueOf(var7);
            int var9 = var4.length() - var8.length();
            var1.replace(var5 + var2, var6 + var2, var8);
            var2 -= var9;
         }
      }

      return var1.toString();
   }

   public static boolean isRoman(String var0) {
      return field1.matcher(var0).matches();
   }

   public static int parseRoman(String var0) {
      int var1 = 0;
      char[] var2 = var0.toCharArray();

      for (int var3 = 0; var3 < var2.length; var3++) {
         char var4 = var2[var3];
         ThreadModuleDump83.Type var5 = ThreadModuleDump83.Type.getFromChar(var4);
         if (var3 + 1 < var2.length) {
            ThreadModuleDump83.Type var6 = ThreadModuleDump83.Type.getFromChar(var2[var3 + 1]);
            int var7 = var6.value - var5.value;
            if (var7 > 0) {
               var1 += var7;
               var3++;
               continue;
            }
         }

         var1 += var5.value;
      }

      return var1;
   }

   private enum Type {
      I(1),
      V(5),
      X(10),
      L(50),
      C(100),
      D(500),
      M(1000);

      private final int value;

      Type(int var3) {
         this.value = var3;
      }

      private static ThreadModuleDump83.Type getFromChar(char var0) {
         try {
            return valueOf(Character.toString(var0));
         } catch (IllegalArgumentException var2) {
            throw new IllegalArgumentException("Expected valid Roman numeral, received " + var0);
         }
      }
   }
}
