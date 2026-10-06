package com.moonsworth.lunar.genesis;
import com.google.common.escape.CharEscaper;
import com.google.common.escape.UnicodeEscaper;

final class Escapers$2 extends UnicodeEscaper {
   Escapers$2(CharEscaper mixinhelper33_31) {
      this.field3 = mixinhelper33_31;
   }

   protected char[] escape(int number1) {
      if (number1 < 65536) {
         return this.field3.escape((char)number1);
      }

      char[] items2 = new char[2];
      Character.toChars(number1, items2, 0);
      char[] items3 = this.field3.escape(items2[0]);
      char[] items4 = this.field3.escape(items2[1]);
      if (items3 == null && items4 == null) {
         return null;
      }

      int index5 = items3 != null ? items3.length : 1;
      int index6 = items4 != null ? items4.length : 1;
      char[] items7 = new char[index5 + index6];
      if (items3 != null) {
         for (int index8 = 0; index8 < items3.length; index8++) {
            items7[index8] = items3[index8];
         }
      } else {
         items7[0] = items2[0];
      }

      if (items4 != null) {
         for (int index9 = 0; index9 < items4.length; index9++) {
            items7[index5 + index9] = items4[index9];
         }
      } else {
         items7[index5] = items2[1];
      }

      return items7;
   }
}
