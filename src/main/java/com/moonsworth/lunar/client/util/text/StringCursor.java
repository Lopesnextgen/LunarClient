package com.moonsworth.lunar.client.util.text;

import lombok.Generated;

public class StringCursor {
   private final String string;
   private int cursor;

   public StringCursor(String text) {
      this.string = text;
   }

   public int getRemainingLength() {
      return this.string.length() - this.cursor;
   }

   public boolean canRead() {
      return this.cursor < this.string.length();
   }

   public boolean canRead(int number1) {
      return this.cursor + number1 <= this.string.length();
   }

   public char peek() {
      return this.string.charAt(this.cursor);
   }

   public char peek(int index1) {
      return this.string.charAt(this.cursor + index1);
   }

   public char read() {
      return this.string.charAt(this.cursor++);
   }

   public void skip() {
      this.cursor++;
   }

   public void skipWhitespace() {
      while (this.canRead() && this.peek() == ' ') {
         this.skip();
      }
   }

   public String getRemaining() {
      return this.string.substring(this.cursor);
   }

   @Generated
   public String getString() {
      return this.string;
   }

   @Generated
   public int getCursor() {
      return this.cursor;
   }

   @Generated
   public void setCursor(int number1) {
      this.cursor = number1;
   }
}
