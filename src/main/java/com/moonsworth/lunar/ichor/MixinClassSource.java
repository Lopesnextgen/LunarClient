package com.moonsworth.lunar.ichor;

public class MixinClassSource {
   private final IchorStage field1;
   private final String field2;
   private final byte[] field3;

   public MixinClassSource(IchorStage ichor41, String text, byte[] items3) {
      this.field1 = ichor41;
      this.field2 = text;
      this.field3 = items3;
   }

   public IchorStage method1() {
      return this.field1;
   }

   public String className() {
      return this.field2;
   }

   public byte[] method2() {
      return this.field3;
   }
}
