package com.moonsworth.lunar.client.config.option;

import lombok.Generated;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;

public enum BooleanOption implements OptionEnumValue {
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
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   public boolean orElse(boolean flag1) {
      return this == DEFAULT ? flag1 : this == TRUE;
   }

   @Generated
   BooleanOption(String text3) {
      this.id = text3;
   }
}
