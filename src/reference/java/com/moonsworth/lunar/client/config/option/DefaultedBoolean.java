package com.moonsworth.lunar.client.config.option;

import lombok.Generated;

public enum DefaultedBoolean implements OptionEnumValue {
   FALSE("false"),
   DEFAULT("default"),
   TRUE("true");

   private final String id;

   @Override
   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   public boolean orElse(boolean flag) {
      return this == DEFAULT ? flag : this == TRUE;
   }

   @Generated
   DefaultedBoolean(String text) {
      this.id = text;
   }
}
