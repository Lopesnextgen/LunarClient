package com.moonsworth.lunar.genesis;
import com.google.common.escape.CharEscaper;

class CharEscaperBuilder$CharArrayDecorator extends CharEscaper {
   private final char[][] field3;
   private final int field4;

   CharEscaperBuilder$CharArrayDecorator(char[][] items1) {
      this.field3 = items1;
      this.field4 = items1.length;
   }

   @Override
   public String escape(String text1) {
      int number2 = text1.length();

      for (int index3 = 0; index3 < number2; index3++) {
         char character4 = text1.charAt(index3);
         if (character4 < this.field3.length && this.field3[character4] != null) {
            return this.method1(text1, index3);
         }
      }

      return text1;
   }

   @Override
   protected char[] escape(char character1) {
      return character1 < this.field4 ? this.field3[character1] : null;
   }
}
