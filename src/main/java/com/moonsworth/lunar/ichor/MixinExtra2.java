package com.moonsworth.lunar.ichor;

public class MixinExtra2 {
   private final Ichor4 field1;
   private final String field2;
   private final byte[] field3;

   public MixinExtra2(Ichor4 ichor4, String text, byte[] items) {
      this.field1 = ichor4;
      this.field2 = text;
      this.field3 = items;
   }

   public Ichor4 method1() {
      return this.field1;
   }

   public String className() {
      return this.field2;
   }

   public byte[] method2() {
      return this.field3;
   }
}
