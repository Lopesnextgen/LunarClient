package com.moonsworth.lunar.client.config.profile.importer;

public class ParsedProfileColor {
   private final int red;
   private final int green;
   private final int blue;
   private final int alpha;
   private final boolean chroma;

   public ParsedProfileColor(int value, int value2, int value3, int number4, boolean flag) {
      this.red = value;
      this.green = value2;
      this.blue = value3;
      this.alpha = number4;
      this.chroma = flag;
   }

   public static ParsedProfileColor parse(String text0) {
      if (text0 != null && !text0.isEmpty()) {
         String[] items1 = text0.split("/");
         if (items1.length == 0) {
            return null;
         }

         boolean flag2 = false;
         byte index3 = 0;
         if (!isNumeric(items1[0])) {
            flag2 = Boolean.parseBoolean(items1[0]);
            index3 = 1;
         }

         int number4 = items1.length - index3;
         if (number4 != 3 && number4 != 4) {
            return null;
         }

         try {
            int number5 = clampChannel(Integer.parseInt(items1[index3].trim()));
            int number6 = clampChannel(Integer.parseInt(items1[index3 + 1].trim()));
            int number7 = clampChannel(Integer.parseInt(items1[index3 + 2].trim()));
            int number8 = number4 == 4 ? clampChannel(Integer.parseInt(items1[index3 + 3].trim())) : 255;
            return new ParsedProfileColor(number5, number6, number7, number8, flag2);
         } catch (NumberFormatException numberformatexception9) {
            return null;
         }
      } else {
         return null;
      }
   }

   public int toRgba() {
      return (this.alpha & 0xFF) << 24 | (this.red & 0xFF) << 16 | (this.green & 0xFF) << 8 | this.blue & 0xFF;
   }

   private static boolean isNumeric(String text0) {
      if (text0 != null && !text0.isEmpty()) {
         for (int index1 = 0; index1 < text0.length(); index1++) {
            if (!Character.isDigit(text0.charAt(index1))) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private static int clampChannel(int value) {
      return Math.max(0, Math.min(255, value));
   }

   public int red() {
      return this.red;
   }

   public int green() {
      return this.green;
   }

   public int blue() {
      return this.blue;
   }

   public int alpha() {
      return this.alpha;
   }

   public boolean isChroma() {
      return this.chroma;
   }
}
