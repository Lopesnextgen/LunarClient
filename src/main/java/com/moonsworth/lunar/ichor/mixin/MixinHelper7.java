package com.moonsworth.lunar.ichor.mixin;

public class MixinHelper7 implements MixinHelper {
   private final String field1;

   public MixinHelper7(String text) {
      this.field1 = text;
   }

   @Override
   public String getDescriptor() {
      return "L" + this.field1 + ";";
   }

   public String name() {
      return this.field1;
   }
}
