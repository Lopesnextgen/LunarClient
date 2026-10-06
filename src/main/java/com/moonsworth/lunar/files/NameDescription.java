package com.moonsworth.lunar.files;

class NameDescription {
   private final String field1;
   private final String field2;

   NameDescription(String text, String text2) {
      this.field1 = text;
      this.field2 = text2;
   }

   public String name() {
      return this.field1;
   }

   public String method1() {
      return this.field2;
   }
}
