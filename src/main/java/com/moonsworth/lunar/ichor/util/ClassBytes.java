package com.moonsworth.lunar.ichor.util;

public class ClassBytes {
   private final String field1;
   private final byte[] field2;

   public ClassBytes(String text, byte[] items2) {
      this.field1 = text;
      this.field2 = items2;
   }

   public String className() {
      return this.field1;
   }

   public byte[] method1() {
      return this.field2;
   }
}
