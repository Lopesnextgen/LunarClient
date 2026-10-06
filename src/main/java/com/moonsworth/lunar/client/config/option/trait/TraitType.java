package com.moonsworth.lunar.client.config.option.trait;

import lombok.Generated;

public class TraitType<T> {
   private final int field1;

   @Generated
   public int getId() {
      return this.field1;
   }

   @Generated
   public TraitType(int value) {
      this.field1 = value;
   }
}
