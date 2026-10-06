package com.moonsworth.lunar.client.util.text;

public class FormattingCodes {
   public FormattingCodes() {
   }

   public static String getTextWithoutFormattingCodes(String text) {
      if (text == null) {
         return null;
      }

      int index1 = 0;
      int index2 = 0;

      for (int index3 = text.length() - 1; index2 < index3; index2++) {
         int number4 = text.codePointAt(index2);
         if (number4 == 167) {
            int number5 = text.codePointAt(index2 + 1);
            if (number5 >= 48 && number5 <= 57 || number5 >= 65 && number5 <= 70 || number5 >= 97 && number5 <= 102 || number5 == 79 || number5 == 111 || number5 == 82 || number5 == 114) {
               index1++;
               index2++;
            }
         }
      }

      if (index1 == 0) {
         return text;
      }

      int[] items8 = new int[text.length() - index1 * 2];
      int index9 = 0;
      int number10 = text.length();
      int index11 = 0;

      while (index9 < number10) {
         label65: {
            int number6 = text.codePointAt(index9);
            if (number6 == 167) {
               int number7 = text.codePointAt(index9 + 1);
               if (number7 >= 48 && number7 <= 57 || number7 >= 65 && number7 <= 70 || number7 >= 97 && number7 <= 102 || number7 == 79 || number7 == 111 || number7 == 82 || number7 == 114) {
                  index9++;
                  break label65;
               }
            }

            items8[index11++] = number6;
         }

         index9++;
      }

      return new String(items8, 0, items8.length);
   }
}
