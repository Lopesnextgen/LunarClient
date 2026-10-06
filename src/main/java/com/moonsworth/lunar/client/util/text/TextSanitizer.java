package com.moonsworth.lunar.client.util.text;

public class TextSanitizer {
   public static final char[] ILLEGAL_FILENAME_CHARS = new char[]{'/', '\n', '\r', '\t', '\u0000', '\f', '`', '?', '*', '\\', '<', '>', '|', '"', ':'};

   public TextSanitizer() {
   }

   public static boolean isAllowedCharacter(char character0) {
      return character0 != 167 && character0 >= ' ' && character0 != 127;
   }

   public static String sanitize(String text) {
      StringBuilder builder1 = new StringBuilder();

      for (char character5 : text.toCharArray()) {
         if (isAllowedCharacter(character5)) {
            builder1.append(character5);
         }
      }

      return builder1.toString();
   }
}
