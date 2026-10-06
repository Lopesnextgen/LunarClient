package com.moonsworth.lunar.ichor.mixin;

import org.cadixdev.bombe.type.BaseType;

public class MixinHelper5 implements MixinHelper {
   private final BaseType field1;

   public MixinHelper5(char var1) {
      this(BaseType.getFromKey(var1));
   }

   public MixinHelper5(BaseType var1) {
      this.field1 = var1;
   }

   @Override
   public String getDescriptor() {
      return String.valueOf(this.field1 == null ? 'V' : this.field1.getKey());
   }

   public BaseType method1() {
      return this.field1;
   }
}
