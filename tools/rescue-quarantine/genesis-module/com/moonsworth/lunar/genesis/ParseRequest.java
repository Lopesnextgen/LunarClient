package com.moonsworth.lunar.genesis;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
final class ParseRequest {
   final String field1;
   final int field2;

   private ParseRequest(String text1, int number2) {
      this.field1 = text1;
      this.field2 = number2;
   }

   static ParseRequest method1(String text0) {
      if (text0.length() == 0) {
         throw new NumberFormatException("empty string");
      }

      char character3 = text0.charAt(0);
      String text1;
      byte number2;
      if (text0.startsWith("0x") || text0.startsWith("0X")) {
         text1 = text0.substring(2);
         number2 = 16;
      } else if (character3 == '#') {
         text1 = text0.substring(1);
         number2 = 16;
      } else if (character3 == '0' && text0.length() > 1) {
         text1 = text0.substring(1);
         number2 = 8;
      } else {
         text1 = text0;
         number2 = 10;
      }

      return new ParseRequest(text1, number2);
   }
}
