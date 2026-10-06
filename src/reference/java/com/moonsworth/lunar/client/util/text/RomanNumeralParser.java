package com.moonsworth.lunar.client.util.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RomanNumeralParser {
   private static final Pattern STRICT_NUMERAL_PATTERN = Pattern.compile("^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
   public static final Pattern NUMERAL_PATTERN = Pattern.compile(" ((?=[MDCLXVI])M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3}))\\w*");

   public RomanNumeralParser() {
   }

   public static String replaceRomanNumerals(String text0) {
      StringBuilder builder1 = new StringBuilder(text0);
      int number2 = 0;
      Matcher matcher3 = field2.matcher(text0);

      while (matcher3.find()) {
         String text4 = matcher3.group().trim();
         if (isRomanNumeral(text4)) {
            int number5 = matcher3.start(1);
            int number6 = matcher3.end(1);
            int number7 = romanToInt(text4);
            String text8 = String.valueOf(number7);
            int number9 = text4.length() - text8.length();
            builder1.replace(number5 + number2, number6 + number2, text8);
            number2 -= number9;
         }
      }

      return builder1.toString();
   }

   public static boolean isRomanNumeral(String text0) {
      return field1.matcher(text0).matches();
   }

   public static int romanToInt(String text0) {
      int number1 = 0;
      char[] items2 = text0.toCharArray();

      for (int index3 = 0; index3 < items2.length; index3++) {
         char character4 = items2[index3];
         RomanNumeralParser.Type type5 = RomanNumeralParser.Type.getFromChar(character4);
         if (index3 + 1 < items2.length) {
            RomanNumeralParser.Type type6 = RomanNumeralParser.Type.getFromChar(items2[index3 + 1]);
            int number7 = type6.value - type5.value;
            if (number7 > 0) {
               number1 += number7;
               index3++;
               continue;
            }
         }

         number1 += type5.value;
      }

      return number1;
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

      Type(int value2) {
         this.value = value2;
      }

      private static RomanNumeralParser.Type getFromChar(char character0) {
         try {
            return valueOf(Character.toString(character0));
         } catch (IllegalArgumentException illegalargumentexception2) {
            throw new IllegalArgumentException("Expected valid Roman numeral, received " + character0);
         }
      }
   }
}
