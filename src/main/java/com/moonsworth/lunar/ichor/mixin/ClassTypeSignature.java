package com.moonsworth.lunar.ichor.mixin;

public class ClassTypeSignature implements DescriptorType {
   private final String field1;

   public ClassTypeSignature(String text) {
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
