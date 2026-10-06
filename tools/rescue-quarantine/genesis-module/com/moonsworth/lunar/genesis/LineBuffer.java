package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.common.annotations.GwtIncompatible;

@GwtIncompatible
abstract class LineBuffer {
   private StringBuilder line = new StringBuilder();
   private boolean sawReturn;

   LineBuffer() {
   }

   protected void add(char[] items1, int number2, int number3) {
      int index4 = number2;
      if (this.sawReturn && number3 > 0 && this.finishLine(items1[index4] == '\n')) {
         index4++;
      }

      int number5 = index4;

      for (int index6 = number2 + number3; index4 < index6; index4++) {
         switch (items1[index4]) {
            case '\n':
               this.line.append(items1, number5, index4 - number5);
               this.finishLine(true);
               number5 = index4 + 1;
               break;
            case '\r':
               this.line.append(items1, number5, index4 - number5);
               this.sawReturn = true;
               if (index4 + 1 < index6 && this.finishLine(items1[index4 + 1] == '\n')) {
                  index4++;
               }

               number5 = index4 + 1;
         }
      }

      this.line.append(items1, number5, number2 + number3 - number5);
   }

   @CanIgnoreReturnValue
   private boolean finishLine(boolean flag1) {
      String text2 = this.sawReturn ? (flag1 ? "\r\n" : "\r") : (flag1 ? "\n" : "");
      this.handleLine(this.line.toString(), text2);
      this.line = new StringBuilder();
      this.sawReturn = false;
      return flag1;
   }

   protected void finish() {
      if (this.sawReturn || this.line.length() > 0) {
         this.finishLine(false);
      }
   }

   protected abstract void handleLine(String text1, String text2);
}
