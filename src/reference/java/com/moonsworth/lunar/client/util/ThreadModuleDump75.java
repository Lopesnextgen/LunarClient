package com.moonsworth.lunar.client.util;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.regex.Pattern;

public final class ThreadModuleDump75 {
   private static final Int2ObjectMap<String> TO_ROMAN_CACHE = new Int2ObjectOpenHashMap();
   private static final Object2IntMap<String> FROM_ROMAN_CACHE = new Object2IntOpenHashMap();
   private static final Pattern ROMAN_PATTERN = Pattern.compile("M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})");
   private static final int[] ROMAN_VALUES = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
   private static final String[] ROMAN_NUMERALS = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

   public static String toRoman(int var0) {
      if (var0 <= 0) {
         return "";
      } else {
         return var0 >= 5000 ? String.valueOf(var0) : (String)field1.computeIfAbsent(var0, var0x -> {
            StringBuilder var1 = new StringBuilder();

            for (int var2 = 0; var2 < field4.length; var2++) {
               while (var0x >= field4[var2]) {
                  var1.append(field5[var2]);
                  var0x -= field4[var2];
               }
            }

            return var1.toString();
         });
      }
   }

   public static int fromRoman(String var0) {
      Integer var1 = (Integer)field2.computeIfAbsent(var0, var0x -> {
         byte var1x = 0;
         short var2 = 0;

         for (char var6 : var0x.toCharArray()) {
            short var7 = switch (var6) {
               case 'C' -> 100;
               case 'D' -> 500;
               default -> 0;
               case 'I' -> 1;
               case 'L' -> 50;
               case 'M' -> 1000;
               case 'V' -> 5;
               case 'X' -> 10;
            };
            if (var7 == 0) {
               return null;
            }

            var1x += var7 > var2 ? var7 - 2 * var2 : var7;
            var2 = var7;
         }

         return Integer.valueOf(var1x);
      });
      return var1 != null ? var1 : 0;
   }

   public static boolean isRoman(String var0) {
      return !var0.isEmpty() && field3.matcher(var0).matches();
   }
}
