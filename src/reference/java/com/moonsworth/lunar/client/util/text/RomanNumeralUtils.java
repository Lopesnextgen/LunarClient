package com.moonsworth.lunar.client.util.text;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.regex.Pattern;

public final class RomanNumeralUtils {
   private static final Int2ObjectMap<String> ROMAN_CACHE = new Int2ObjectOpenHashMap();
   private static final Object2IntMap<String> VALUE_CACHE = new Object2IntOpenHashMap();
   private static final Pattern NUMERAL_PATTERN = Pattern.compile("M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})");
   private static final int[] VALUES = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
   private static final String[] NUMERALS = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

   public RomanNumeralUtils() {
   }

   public static String toRoman(int number0) {
      if (number0 <= 0) {
         return "";
      } else {
         return number0 >= 5000 ? String.valueOf(number0) : (String)field1.computeIfAbsent(number0, arg0x -> {
            StringBuilder builder1 = new StringBuilder();

            for (int index2 = 0; index2 < field4.length; index2++) {
               while (arg0x >= field4[index2]) {
                  builder1.append(field5[index2]);
                  arg0x -= field4[index2];
               }
            }

            return builder1.toString();
         });
      }
   }

   public static int romanToInt(String text0) {
      Integer number1 = (Integer)field2.computeIfAbsent(text0, arg0x -> {
         byte number1x = 0;
         short number2 = 0;

         for (char character6 : arg0x.toCharArray()) {
            short number7 = switch (character6) {
               case 'C' -> 100;
               case 'D' -> 500;
               default -> 0;
               case 'I' -> 1;
               case 'L' -> 50;
               case 'M' -> 1000;
               case 'V' -> 5;
               case 'X' -> 10;
            };
            if (number7 == 0) {
               return null;
            }

            number1x += number7 > number2 ? number7 - 2 * number2 : number7;
            number2 = number7;
         }

         return Integer.valueOf(number1x);
      });
      return number1 != null ? number1 : 0;
   }

   public static boolean isRomanNumeral(String text0) {
      return !text0.isEmpty() && field3.matcher(text0).matches();
   }
}
