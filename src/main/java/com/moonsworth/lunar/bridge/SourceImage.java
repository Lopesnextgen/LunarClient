package com.moonsworth.lunar.bridge;

public class SourceImage {
   private final int[] field1;
   private final int field2;
   private final int field3;

   public SourceImage(int[] items1, int value, int value2) {
      this.field1 = items1;
      this.field2 = value;
      this.field3 = value2;
   }

   public int[] method1() {
      return this.field1;
   }

   public int method2() {
      return this.field2;
   }

   public int method3() {
      return this.field3;
   }
}
