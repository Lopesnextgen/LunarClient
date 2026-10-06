package com.moonsworth.lunar.client.util;

import lombok.Generated;

public class ThreadModuleDump27 {
   private final String field1;
   private int cursor;

   public ThreadModuleDump27(String var1) {
      this.field1 = var1;
   }

   public int method1() {
      return this.field1.length() - this.cursor;
   }

   public boolean method2() {
      return this.cursor < this.field1.length();
   }

   public boolean method3(int var1) {
      return this.cursor + var1 <= this.field1.length();
   }

   public char peek() {
      return this.field1.charAt(this.cursor);
   }

   public char peek(int var1) {
      return this.field1.charAt(this.cursor + var1);
   }

   public char method4() {
      return this.field1.charAt(this.cursor++);
   }

   public void skip() {
      this.cursor++;
   }

   public void skipWhitespace() {
      while (this.method2() && this.peek() == ' ') {
         this.skip();
      }
   }

   public String method5() {
      return this.field1.substring(this.cursor);
   }

   @Generated
   public String getString() {
      return this.field1;
   }

   @Generated
   public int getCursor() {
      return this.cursor;
   }

   @Generated
   public void setCursor(int var1) {
      this.cursor = var1;
   }
}
