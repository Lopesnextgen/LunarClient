package com.moonsworth.lunar.ichor.mixin;

import org.cadixdev.bombe.type.BaseType;

public class PrimitiveTypeSignature implements DescriptorType {
   private final BaseType field1;

   public PrimitiveTypeSignature(char character1) {
      this(BaseType.getFromKey(character1));
   }

   public PrimitiveTypeSignature(BaseType baseType) {
      this.field1 = baseType;
   }

   @Override
   public String getDescriptor() {
      return String.valueOf(this.field1 == null ? 'V' : this.field1.getKey());
   }

   public BaseType method1() {
      return this.field1;
   }
}
