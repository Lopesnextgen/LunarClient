package com.moonsworth.lunar.ichor;

import org.spongepowered.asm.service.IPropertyKey;

class PropertyKey implements IPropertyKey {
   private final String field1;

   PropertyKey(String text) {
      this.field1 = text;
   }

   @Override
   public String toString() {
      return this.field1;
   }
}
