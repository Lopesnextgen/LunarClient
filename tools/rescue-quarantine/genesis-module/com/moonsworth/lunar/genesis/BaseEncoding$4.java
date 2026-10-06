package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;

final class BaseEncoding$4 implements Appendable {
   int charsUntilSeparator;

   BaseEncoding$4(int number1, Appendable appendable2, String text3) {
      this.field1 = number1;
      this.field2 = appendable2;
      this.field3 = text3;
      this.charsUntilSeparator = this.field1;
   }

   @Override
   public Appendable append(char character1) {
      if (this.charsUntilSeparator == 0) {
         this.field2.append(this.field3);
         this.charsUntilSeparator = this.field1;
      }

      this.field2.append(character1);
      this.charsUntilSeparator--;
      return this;
   }

   @Override
   public Appendable append(@Nullable CharSequence text1, int number2, int number3) {
      throw new UnsupportedOperationException();
   }

   @Override
   public Appendable append(@Nullable CharSequence text1) {
      throw new UnsupportedOperationException();
   }
}
