package com.moonsworth.lunar.bridge.horsestats;

public class LanguageEntry {
   private final String field1;
   private final String field2;
   private final String field3;
   private final boolean field4;

   public LanguageEntry(String text, String text2, String text3, boolean flag) {
      this.field1 = text;
      this.field2 = text2;
      this.field3 = text3;
      this.field4 = flag;
   }

   public String method1() {
      return this.field1;
   }

   public String method2() {
      return this.field2;
   }

   public String name() {
      return this.field3;
   }

   public boolean method3() {
      return this.field4;
   }
}
